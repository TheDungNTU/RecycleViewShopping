package vn.edu.ntu.thedung.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

@Entity(tableName = "Product")
public class Product {
    @PrimaryKey(autoGenerate = true)
    int id;
    @NonNull String name;
    @NonNull String price;
    String desc;

    public Product() {
    }

    public Product(String name, String price, String desc) {
        this.name = name;
        this.price = price;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
