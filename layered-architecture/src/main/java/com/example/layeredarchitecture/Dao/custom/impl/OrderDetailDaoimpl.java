package com.example.layeredarchitecture.Dao.custom.impl;

import com.example.layeredarchitecture.Dao.SQLUtil;
import com.example.layeredarchitecture.Dao.custom.OrderDetailDao;
import com.example.layeredarchitecture.entity.OrderDetail;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public  class OrderDetailDaoimpl implements OrderDetailDao {

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Save(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate( "INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)"
                ,entity.getOrderId(),entity.getItemCode(),entity.getUnitPrice(),entity.getQty());
    }

    @Override
    public boolean update(OrderDetail customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String Genaratenewid() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public OrderDetail search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

}