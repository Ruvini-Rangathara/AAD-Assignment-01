package com.example.demo.service.custom;


import com.example.demo.dto.SuperDTO;

import java.io.Serializable;
import java.util.List;

public interface SuperService <T extends SuperDTO, ID extends Serializable>{
    boolean save(T dto);
    boolean update(T dto);
    boolean delete(T dto);
    T search(ID id);
    List<T> getAll();
    ID getLastId();
}
