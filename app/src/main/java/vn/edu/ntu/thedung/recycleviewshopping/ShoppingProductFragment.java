package vn.edu.ntu.thedung.recycleviewshopping;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.thedung.controller.IProductController;
import vn.edu.ntu.thedung.model.Product;


public class ShoppingProductFragment extends Fragment {
    TextView txtShoppingCart;
    Button btnOK, btnCannel;
    List<Product> listProducts = new ArrayList<>();
    NavController controller;


    public ShoppingProductFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shopping_product,container,false);
        controller = NavHostFragment.findNavController(ShoppingProductFragment.this);
        ((MainActivity)getActivity()).controller = controller;
        AddViews(view);
        AddAction();
        return view;
    }

    private void AddViews(View view){

        txtShoppingCart = view.findViewById(R.id.txtShoppingCart);
        btnOK = view.findViewById(R.id.btnOK);
        btnCannel = view.findViewById(R.id.btnCannel);

        IProductController controller = ((MainActivity)getActivity()).productController;
        listProducts = controller.getShoppingCart();

        StringBuilder builder = new StringBuilder();

        for(Product p:listProducts){

            builder.append(p.getName());
            builder.append("||");
            builder.append(p.getPrice());
            builder.append("||");
            builder.append(p.getDesc());
            builder.append("\n");
        }
        txtShoppingCart.setText(builder.toString());
    }

    private void AddAction(){

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_shoppingProductFragment_to_orderProductFragment);
            }
        });

        btnCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IProductController controller = (IProductController) ShoppingProductFragment.this.getActivity().getApplication();
                controller.ClearShoppingCart();
                txtShoppingCart.setText("Không có mặt hàng nào");
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}