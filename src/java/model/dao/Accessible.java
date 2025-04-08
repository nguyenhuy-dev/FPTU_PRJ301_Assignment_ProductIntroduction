/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;

public interface Accessible<T> {
    int insert(T obj);
    int delete(T obj);
    int update(T obj);
    T getObjectById(String id);
    List<T> listAll();
}
