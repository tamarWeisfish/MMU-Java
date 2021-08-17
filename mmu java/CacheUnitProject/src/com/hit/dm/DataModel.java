package com.hit.dm;

import java.io.Serializable;
import java.util.Objects;
/* Each instance of the class is a memory unit.
The class is built according to the api*/
public class DataModel<T>
        extends Object
        implements Serializable {

    private Long id;
    private T content;

    public DataModel(Long dataModelId, T content) {
        this.id = dataModelId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "id=" + id +
                ", content=" + content +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataModel)) return false;
        DataModel<?> dataModel = (DataModel<?>) o;
        return getId().equals(dataModel.getId()) && getContent().equals(dataModel.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent());
    }

    public void setId(Long id) {
        this.id = id;
    }


    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public DataModel() {
    }
}
