package com.hit.services;


import com.hit.dao.DaoFileImpl;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;
import com.mbj.algorithm.IAlgoCache;
import com.mbj.algorithm.LRUAlgoCacheImpl;
import com.mbj.algorithm.MFUAlgoCacheImpl;
//import main.java.com.hit.algorithm.IAlgoCache;
//import main.java.com.hit.algorithm.MFUAlgoCacheImpl;

import java.util.Arrays;
import java.util.Map;
/* */
public class CacheUnitService<T> {
    LRUAlgoCacheImpl<Long, DataModel<T>> iAlgoCache;
    CacheUnit<T> cacheUnit;
    private final DaoFileImpl<T> dao;
    private static final int CAPACITY = 2;
    private static int numberRequests=0;
    private static int numberData=0;
    private static int numberSwaps=0;

    public CacheUnitService() {
        iAlgoCache = new LRUAlgoCacheImpl<>(CAPACITY);
        cacheUnit = new CacheUnit<T>(iAlgoCache);
        dao = new DaoFileImpl<>("src/main/resources/datasourse.json");
    }
/*Deletes the value from the cache and hard memory */
    public boolean delete(DataModel<T>[] dataModels) {
        numberData=numberData+dataModels.length;
        numberRequests=numberRequests+1;
        try {
            for (int i = 0; i < dataModels.length; i++) {
                dao.delete(dataModels[i]);
            }

            Long[] ids = new Long[dataModels.length];
            for (int i = 0; i < dataModels.length; i++) {
                ids[i] = dataModels[i].getId();
            }
            DataModel<T>[] allGet = new DataModel[dataModels.length];
            allGet = cacheUnit.getDataModels(ids);
            for (int i = 0; i < dataModels.length; i++) {
                if (allGet[i] != null) {
                    cacheUnit.removeDataModels(ids);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
/* Checks if the value exists in the cache and if so returns it,
otherwise reads the value from the hard memory returns it ,
and saves it in the cache*/
    public DataModel<T>[] get(DataModel<T>[] dataModels) {
        numberData=numberData+dataModels.length;
        numberRequests=numberRequests+1;
        Long[] ids = new Long[dataModels.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = dataModels[i].getId();
        }
        DataModel<T>[] models = new DataModel[dataModels.length];
        models = cacheUnit.getDataModels(ids);
        for (int i = 0; i < dataModels.length; i++) {
            if (models[i] == null) {
                models[i] = dao.find(dataModels[i].getId());
            }
        }
        return models;
    }




/*Updates the value in the cache ,
and if organs are trampled it keeps the trampled organs in the hard memory*/
    public boolean update(DataModel<T>[] dataModels) {
        numberData=numberData+dataModels.length;
        numberRequests=numberRequests+1;
        DataModel<T>[] dataModelTemp = cacheUnit.putDataModels(dataModels);
        for (DataModel<T> elem : dataModelTemp
        ) {
            if (elem != null) {
                numberSwaps=numberSwaps+1;
                dao.save(elem);
            }
        }
        return true;
    }


/*A function called at closing the server,
 and updating the entire cache to the hard memory*/
    public Boolean Stop() {
        Map<Long, DataModel<T>> allDataModels = cacheUnit.getAllModel();
        for (Map.Entry<Long, DataModel<T>> entry : allDataModels.entrySet()) {
            dao.save(entry.getValue());
        }
        return true;
    }
    /*Function that gives the usage statistics*/
    public String showStatistics(){
        String capacity=String.valueOf(iAlgoCache.getCapacity());
        String data="CAPACITY:"+capacity+"\n"+"algorithm:LRU\n"+"Total number of requests:"+numberRequests+"\n"+"Total number of DataMaodels:" +
               numberData+ "\n"+"Total number of DataMaodel swaps:"+numberSwaps+"\n";
        return data;
    }
}
