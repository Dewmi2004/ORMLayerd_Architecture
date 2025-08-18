package com.example.layeredarchitecture.BO.custom;

import com.example.layeredarchitecture.BO.custom.impl.CustomerBO;
import com.example.layeredarchitecture.Dao.DAOFactory;
import com.example.layeredarchitecture.Dao.custom.CustomerDao;
import com.example.layeredarchitecture.Dao.custom.impl.CustomerDaoImpl;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDao customerDao = (CustomerDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.CUSTOMER);

    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer>entity =customerDao.getAll();
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        for(Customer customer : entity){
            customerDTOs.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDTOs;
    }

    @Override
    public boolean SaveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDao.Save(
        new Customer(customer.getId(),customer.getName(),customer.getAddress()));
    }

    @Override
    public boolean DeleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.Delete(id);
    }

    @Override
    public boolean UpdateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(customer.getId(),customer.getName(),customer.getAddress()));
    }

    @Override
    public boolean ExistCustomer(String id) throws SQLException, ClassNotFoundException {

        return customerDao.exist(id);
    }

    @Override
    public String GetNewIdCustomer() throws SQLException, ClassNotFoundException {
        return customerDao.Genaratenewid();
    }


}