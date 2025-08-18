package com.example.layeredarchitecture.BO.custom;

//Factory Design Pattern -> Singleton Design Pattern Has Used To Create this Design Pattern

import com.example.layeredarchitecture.BO.SuperBO;

public class BOFactory {
    //do it as homework
    private static BOFactory instance;
    public static BOFactory getInstance(){
        if(instance==null){
            instance=new BOFactory();

        }
        return instance;
    }
    private BOFactory(){

    }
    public enum BOtypes{
        CUSTOMER,ITEM,ORDER
    }
    public SuperBO getBO(BOFactory.BOtypes bo){
        switch(bo){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            default:
                return null;
        }
    }
}
