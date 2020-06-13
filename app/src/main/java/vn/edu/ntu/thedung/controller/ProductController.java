package vn.edu.ntu.thedung.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.thedung.model.Product;

public class ProductController extends Application implements IProductController {
    List<Product> listProducts = new ArrayList<>();
    List<Product> shoppingCart = new ArrayList<>();

    public ProductController() {
        listProducts.add(new Product("Khoai lang","2500","Khoa lang luộc"));
        listProducts.add(new Product("Khoai sọ","2500","Khoa sọ luộc"));
        listProducts.add(new Product("Khoai tím","2500","Khoa tím luộc"));
        listProducts.add(new Product("Khoai tây","2500","Khoa tây luộc"));
        listProducts.add(new Product("Khoai mì","2500","Khoa mì luộc"));
        listProducts.add(new Product("Khoai lang","2500","Khoa lang luộc"));
        listProducts.add(new Product("Khoai sọ","2500","Khoa sọ luộc"));

    }

    @Override
    public List<Product> getAllProduct() {
        return listProducts;
    }

    @Override
    public boolean addToCart(Product p) {
        if(shoppingCart.contains(p))
            return false;
        shoppingCart.add(p);
        return true;
    }

    @Override
    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void ClearShoppingCart() {
        shoppingCart.clear();
    }

    @Override
    public void AddProduct(Product p) {
        listProducts.add(p);
    }

    @Override
    public int size() {
        return 0;
    }
}
