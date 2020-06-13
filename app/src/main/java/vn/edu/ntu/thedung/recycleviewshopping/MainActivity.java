package vn.edu.ntu.thedung.recycleviewshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.View;

import vn.edu.ntu.thedung.controller.IProductController;
import vn.edu.ntu.thedung.controller.ProductController;
import vn.edu.ntu.thedung.controller.ProductControllerDB;

public class MainActivity extends AppCompatActivity {
    NavController controller;
    IProductController productController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigateUp();
            }
        });
        productController = new ProductControllerDB(this);
    }


}