package com.example.layeredarchitecture.BO.custom.impl;

import com.example.layeredarchitecture.BO.SuperBO;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface OrderBO extends SuperBO {
    public String GetNewIdOrder() throws SQLException, ClassNotFoundException;
    public Customer searchCustomer(String s) throws SQLException, ClassNotFoundException;
    public Item searchItem(String s) throws SQLException, ClassNotFoundException;
    public boolean ExistItem(String id) throws SQLException, ClassNotFoundException;
    public boolean ExistCustomer(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
    public Item findItem(String id) throws SQLException, ClassNotFoundException;
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException ;
    }
