package com.backend.parcial.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IDao<T> {
    T registrar(T t);

    List<T> listarTodos();
    //T buscarPorId(int id);
}
