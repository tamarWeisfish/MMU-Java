package com.hit.services;

import com.hit.dm.DataModel;
/*Abstraction layer between CacheUnitService and HandleRequest*/
public class CacheUnitController<T> implements Runnable {
    CacheUnitService cacheUnitService;

    public CacheUnitController() {
        cacheUnitService = new CacheUnitService();
    }

    public boolean delete(DataModel<T>[] dataModels) {
        return cacheUnitService.delete(dataModels);
    }

    public DataModel<T>[] get(DataModel<T>[] dataModels) {
        return cacheUnitService.get(dataModels);
    }

    public boolean update(DataModel<T>[] dataModels) {
        return cacheUnitService.update(dataModels);
    }
    public Boolean Stop(){return cacheUnitService.Stop();}

    @Override
    public void run() {

    }
    public String showStatistics(){
        return cacheUnitService.showStatistics();
    }
}
