package com.example.layeredarchitecture.BO.custom;

import com.example.layeredarchitecture.BO.custom.impl.OrderBO;
import com.example.layeredarchitecture.Dao.DAOFactory;
import com.example.layeredarchitecture.Dao.custom.CustomerDao;
import com.example.layeredarchitecture.Dao.custom.ItemDao;
import com.example.layeredarchitecture.Dao.custom.OrderDao;
import com.example.layeredarchitecture.Dao.custom.OrderDetailDao;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.entity.Order;
import com.example.layeredarchitecture.entity.OrderDetail;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDao orderDao = (OrderDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.ORDER);
CustomerDao customerDao = (CustomerDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.CUSTOMER);
ItemDao itemDao = (ItemDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.ITEM);
OrderDetailDao orderDetailDao = (OrderDetailDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.ORDERDETAILS);

    @Override
    public String GetNewIdOrder() throws SQLException, ClassNotFoundException {
        return orderDao.Genaratenewid();
    }
    @Override
    public Customer searchCustomer(String s) throws SQLException, ClassNotFoundException {
        return customerDao.search(s);
    }
    @Override
    public Item searchItem(String s) throws SQLException, ClassNotFoundException {
        return itemDao.search(s);
    }

    @Override
    public boolean ExistItem(String id) throws SQLException, ClassNotFoundException {
        return itemDao.exist(id);
    }

    @Override
    public boolean ExistCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.exist(id);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> entity = customerDao.getAll();
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : entity) {
            customerDTOs.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDTOs;
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> entity = itemDao.getAll();
        ArrayList<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : entity) {
            itemDTOs.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return itemDTOs;
    }

    @Override
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        connection= DBConnection.getDbConnection().getConnection();
        //exits order id?
        boolean b1=orderDao.exist(orderId);
        /*if order id already exist*/
        if (b1) {
            return false;
        }
        connection.setAutoCommit(false);
        boolean b2=orderDao.Save(new Order(orderId,orderDate,customerId));
        //save oder
        if (!b2) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        //save order details
        for (OrderDetailDTO detail : orderDetails) {
            boolean b3=orderDetailDao.Save(new OrderDetail(detail.getOrderId(),detail.getItemCode(),detail.getQty(),detail.getUnitPrice()));

            if (!b3) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            //Search & Update Item
            Item item = findItem(detail.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

            //update item
            boolean b4=itemDao.update(new Item(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));

            if (!b4) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public Item findItem(String id) throws SQLException, ClassNotFoundException {
        try {
            return itemDao.search(id);
        }catch (SQLException e){
            throw new RuntimeException("failed to find item" + id,e);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
