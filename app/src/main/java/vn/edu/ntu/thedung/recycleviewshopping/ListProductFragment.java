package vn.edu.ntu.thedung.recycleviewshopping;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.thedung.controller.IProductController;
import vn.edu.ntu.thedung.model.Product;


public class ListProductFragment extends Fragment {
    RecyclerView rvListProduct;
    ProductAdapter adapter;
    List<Product> listProducts = new ArrayList<>();
    FloatingActionButton floatingActionButton;
    NavController controller;

    public ListProductFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.my_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.mnuClose:
                this.getActivity().finish();
            case R.id.mnu_shopping_cart:
                showShoppingCart();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showShoppingCart(){
        NavController navController = NavHostFragment.findNavController(ListProductFragment.this);
        navController.navigate(R.id.action_listProductFragment_to_shoppingProductFragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_product,container,false);
        controller = NavHostFragment.findNavController(ListProductFragment.this);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        AddViews(view);
        AddActions();
        return view;
    }

    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void AddViews(View view){
        rvListProduct = view.findViewById(R.id.rvListProduct);

        IProductController controller = ((MainActivity)getActivity()).productController;

        listProducts = controller.getAllProduct();
        adapter = new ProductAdapter(listProducts);

        rvListProduct.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvListProduct.setAdapter(adapter);

        floatingActionButton = view.findViewById(R.id.floatingActionButton);

    }

    private void AddActions(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_listProductFragment_to_addProductFragment);
            }
        });

    }

    private class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView txtName, txtPrice, txtDesc;
        ImageView imgAddToCart,imgEditProduct;
        Product p;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            imgAddToCart = itemView.findViewById(R.id.imgAddToCart);

            imgAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IProductController controller = ((MainActivity)getActivity()).productController;
                    if(controller.addToCart(p)){
                        String text = p.getName() + " đã thêm giỏ hàng";
                        Toast.makeText(ListProductFragment.this.getActivity(), text, Toast.LENGTH_LONG).show();
                    }
                    else{
                        String text = p.getName() + " đã có trong giỏ hàng";
                        Toast.makeText(ListProductFragment.this.getActivity(),"Mặt hàng đã có", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        public void bind(Product p){
            this.p = p;
            txtName.setText(p.getName());
            txtPrice.setText(p.getPrice());
            txtDesc.setText(p.getDesc());
        }
    }

    //Tạo adapter cho RecycleView

    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{

        List<Product> listProducts = new ArrayList<>();

        public ProductAdapter(List<Product> listProducts) {
            this.listProducts = listProducts;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.product,parent,false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(listProducts.get(position));
        }

        @Override
        public int getItemCount() {
            return listProducts.size();
        }
    }

}