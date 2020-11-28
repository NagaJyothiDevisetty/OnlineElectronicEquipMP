package com.example.onlineelectronicequipment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onlineelectronicequipment.model.Products;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Addingproducts extends AppCompatActivity {

    Button addproductBtn;
    Button viewproductBtn;
    ImageView add;
    EditText productName, descriptions, prices;
    FirebaseFirestore db;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addingproducts);


        getSupportActionBar().setTitle("Addingproducts");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadingBar = new ProgressDialog(Addingproducts.this);
        db = FirebaseFirestore.getInstance();

            addproductBtn = (Button) findViewById(R.id.addproduct);
            viewproductBtn = (Button)findViewById(R.id.retreive);

            productName = (EditText) findViewById(R.id.productname);
            descriptions = (EditText) findViewById(R.id.description);
            prices = (EditText) findViewById(R.id.price);
            addproductBtn=findViewById(R.id.addproduct);
            viewproductBtn=findViewById(R.id.retreive);
            add=findViewById(R.id.add);

            viewproductBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Addingproducts.this,ProductDetails.class);
                    startActivity(intent);
                }
            });

            addproductBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AddData();

                }
            });
    }

    private void AddData() {

                    String productname = productName.getText().toString();
                    String description = descriptions.getText().toString();
                    String price = prices.getText().toString();


                    if (TextUtils.isEmpty(productname)) {
                        Toast.makeText(this, "Please Enter productname", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(description)) {
                        Toast.makeText(this, "Please write product description", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(price)) {
                        Toast.makeText(this, "Please write  product price", Toast.LENGTH_SHORT).show();
                    } else {
                        loadingBar.setTitle("Addingproducts");
                        loadingBar.setMessage("Please wait, while we are checking the Product details .");
                        loadingBar.setCanceledOnTouchOutside(false);
                        loadingBar.show();

                        ValidateData(productname, description, price);
                    }
                }

                    private void ValidateData(String productname, String description, String price) {


       HashMap<String, Object> productdataMap = new HashMap<>();
        productdataMap.put("productname",productname);
        productdataMap.put("description", description);
        productdataMap.put("price", price);


        db.collection("Products")
                .add(productdataMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
        @Override
        public void onSuccess(DocumentReference documentReference) {
            Toast.makeText(getApplicationContext(),"products are added Successfully",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Addingproducts.this,ProductDetails.class);
            startActivity(intent);
            finish();
        }
    })
            .addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }
    });

    }
}
