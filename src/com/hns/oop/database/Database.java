package com.hns.oop.database;

import com.hns.oop.exceptions.DatabaseException;
import java.util.ArrayList;

public interface Database<T> {
    public void insert(T object) throws DatabaseException;
    public ArrayList<T> find(String condition) throws DatabaseException;
}
