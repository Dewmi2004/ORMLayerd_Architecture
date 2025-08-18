package com.example.layeredarchitecture.BO.custom.impl;

import com.example.layeredarchitecture.BO.SuperBO;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    public boolean SaveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public boolean DeleteCustomer( String id) throws SQLException, ClassNotFoundException;
    public boolean UpdateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public boolean ExistCustomer(String id) throws SQLException, ClassNotFoundException;
    public String GetNewIdCustomer() throws SQLException, ClassNotFoundException;

}
