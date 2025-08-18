package com.example.layeredarchitecture.BO.custom;

import com.example.layeredarchitecture.BO.custom.impl.ItemBO;
import com.example.layeredarchitecture.Dao.DAOFactory;
import com.example.layeredarchitecture.Dao.custom.ItemDao;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
ItemDao itemDao = (ItemDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.ITEM);
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOs = new ArrayList<>();
        ArrayList<Item> entity = itemDao.getAll();
        for (Item item : entity) {
            itemDTOs.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return itemDTOs;
    }

    @Override
    public boolean SaveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        return itemDao.Save(new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand()));
    }


    @Override
    public boolean DeleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDao.Delete(id);
    }

    @Override
    public boolean UpdateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDao.update(new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand()));
    }


    @Override
    public boolean ExistItem(String id) throws SQLException, ClassNotFoundException {
        return itemDao.exist(id);
    }

    @Override
    public String GetNewIdItem() throws SQLException, ClassNotFoundException {
        return itemDao.Genaratenewid();
    }


}
