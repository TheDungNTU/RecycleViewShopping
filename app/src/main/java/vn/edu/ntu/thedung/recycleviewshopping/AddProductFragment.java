package vn.edu.ntu.thedung.recycleviewshopping;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.ntu.thedung.controller.IProductController;
import vn.edu.ntu.thedung.model.Product;

public class AddProductFragment extends Fragment {
    EditText edtName, edtPrice, edtDesc;
    Button btnAddmit;
    public AddProductFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product,container,false);
        AddViews(view);
        AddActions();
        return view;

    }

    private void AddViews(View view){
        edtName = view.findViewById(R.id.edtName);
        edtDesc = view.findViewById(R.id.edtDesc);
        edtPrice = view.findViewById(R.id.edtPrice);
        btnAddmit = view.findViewById(R.id.btnAddmit);
        IProductController controller = ((MainActivity)getActivity()).productController;
//        Integer id = controller.getAllProduct().size();
//        edtID.setText(id.toString());
    }

    private void AddActions(){

        btnAddmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = new Product();

                IProductController controller = ((MainActivity)getActivity()).productController;

                p.setName(edtName.getText().toString());
                p.setDesc(edtDesc.getText().toString());
                p.setPrice(edtPrice.getText().toString());

                controller.AddProduct(p);
                Integer id = controller.getAllProduct().size();
                edtName.setText("");
                edtDesc.setText("");
                edtPrice.setText("");

                Toast.makeText(AddProductFragment.this.getActivity(),"Đã thêm mặt hàng",Toast.LENGTH_LONG).show();

            }
        });
    }
}