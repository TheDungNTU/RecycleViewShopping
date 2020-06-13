package vn.edu.ntu.thedung.controller;

import java.util.List;

import vn.edu.ntu.thedung.model.Product;

public interface IProductController {

    public List<Product> getAllProduct();
    public boolean addToCart(Product p);
    public List<Product> getShoppingCart();
    public void ClearShoppingCart();
    public void AddProduct(Product p);
    public int size();
}
