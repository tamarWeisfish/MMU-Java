package com.hit.dao;

import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class DaoFileImpl<T>
        extends java.lang.Object
        implements IDao<Long, DataModel<T>> {
    private java.lang.String filePath;
    private int capacity;

    private ArrayList<DataModel<T>> fileContent;

    /**
     * constructor
     *
     * @param filePath
     * @param capacity
     */
    public DaoFileImpl(java.lang.String filePath, int capacity) {
        this.filePath = filePath;
        this.capacity = capacity;
        try {
            readFile();
        } catch (Exception e) {
        }
    }

    /**
     * constructor
     *
     * @param filePath
     */
    public DaoFileImpl(java.lang.String filePath) {
        this.filePath = filePath;
        this.capacity = 256;
        try {
            readFile();
        } catch (Exception e) {
        }

    }

    /**
     * Deletes a given entity.
     *
     * @param entity - given entity.
     *               Throws:
     *               java.lang.IllegalArgumentException - in case the given entity is null.
     */
    @Override
    public void delete(DataModel<T> entity) {
        fileContent.removeIf(item -> item.getId().equals(entity.getId()));
        try {
            writeFile();
        } catch (Exception ignored) {
        }
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param aLong - must not be null.
     * @return the entity with the given id or null if none found
     * Throws:
     * java.lang.IllegalArgumentException - if id is null
     */
    @Override
    public DataModel<T> find(Long aLong) {
        for (DataModel<T> elem : fileContent) {
            if (elem.getId().equals(aLong)) {
                return elem;
            }
        }
        return null;
    }

    /**
     * Saves a given entity.
     *
     * @param entity - given entity
     */
    @Override
    public void save(DataModel<T> entity) {
        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).getId().equals(entity.getId())) {
                fileContent.remove(i);
            }
        }
        fileContent.add(entity);
        try {
            writeFile();
        } catch (Exception e) {
        }
    }

    /**
     * Auxiliary function for reading from the file
     *
     * @throws Exception
     */
    private void readFile() throws Exception {
        Gson gson = new Gson();
        try {
            Type listType = new TypeToken<ArrayList<DataModel<T>>>() {
            }.getType();
            FileReader fileReader = new FileReader(filePath);
            ArrayList<DataModel<T>> fileArray = gson.fromJson(fileReader, listType);
            if (fileArray != null) {
                fileContent = fileArray;
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Auxiliary function for writing from the file
     *
     * @throws Exception
     */
    private void writeFile() throws Exception {
        Gson gson = new Gson();
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            gson.toJson(fileContent, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




