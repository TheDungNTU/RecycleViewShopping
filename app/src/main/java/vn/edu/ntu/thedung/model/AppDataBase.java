package vn.edu.ntu.thedung.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract DaoProduct getProductDao();
}
