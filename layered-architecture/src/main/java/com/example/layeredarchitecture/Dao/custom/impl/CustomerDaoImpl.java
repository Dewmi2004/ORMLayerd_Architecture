package com.example.layeredarchitecture.Dao.custom.impl;

import com.example.layeredarchitecture.Dao.SQLUtil;
import com.example.layeredarchitecture.Dao.custom.CustomerDao;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.exicute("SELECT * FROM Customer");
        ArrayList<Customer> customers = new ArrayList<>();

        while (rst.next()) {
            Customer customerentity = new Customer(rst.getString("id"),rst.getString("name"),rst.getString("address"));
            customers.add(customerentity);
        }
        return customers;
    }

    @Override
    public boolean Save(Customer entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate("INSERT INTO Customer (id,name, address) VALUES (?,?,?)"
                ,entity.getId(),entity.getName(),entity.getAddress());

    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?",entity.getName(),entity.getAddress(),entity.getId());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.exicute("SELECT id FROM Customer WHERE id=?",id);
        return rst.next();
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public String Genaratenewid() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.exicute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public Customer search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.exicute("SELECT * FROM Customer WHERE id = ?",newValue);
        rst.next();
        Customer customerentity = new Customer(newValue + "", rst.getString("name"), rst.getString("address"));

        return customerentity;
    }

}
