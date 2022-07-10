package org.blackbox.store.mappers.dao;

public interface BaseDao<T> {

    public int insert(T t);

    public T queryOneByPrimaryKey();
}
