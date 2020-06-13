package vn.edu.ntu.thedung.controller;

import android.content.Context;

import androidx.room.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.thedung.model.AppDataBase;

import vn.edu.ntu.thedung.model.DaoProduct;
import vn.edu.ntu.thedung.model.Product;

public class ProductControllerDB implements  IProductController{

    AppDataBase dataBase;
    List<Product> listShoppingProduct = new ArrayList<>();
    DaoProduct daoProduct;

    public ProductControllerDB(Context context) {
        dataBase = Room.databaseBuilder(context,
                AppDataBase.class, "appdb")
                .allowMainThreadQueries()
                .build();
        daoProduct = dataBase.getProductDao();
    }

    @Override
    public List<Product> getAllProduct() {
        return daoProduct.getListProduct();
    }

    @Override
    public boolean addToCart(Product p) {

        if(listShoppingProduct.contains(p))
            return false;
        listShoppingProduct.add(p);
        return true;
    }

    @Override
    public List<Product> getShoppingCart() {
        return listShoppingProduct;
    }

    @Override
    public void ClearShoppingCart() {
        listShoppingProduct.clear();
    }

    @Override
    public void AddProduct(Product p) {
        daoProduct.insertProduct(p);
    }

    @Override
    public int size() {
        return daoProduct.getListProduct().size();
    }
}
