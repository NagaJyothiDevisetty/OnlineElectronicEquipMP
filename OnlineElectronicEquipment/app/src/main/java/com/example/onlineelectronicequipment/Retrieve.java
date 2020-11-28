package com.example.onlineelectronicequipment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineelectronicequipment.model.Products;
import com.example.onlineelectronicequipment.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Retrieve extends AppCompatActivity {

    TextView info;
    Button logoutbtn;
    FirebaseFirestore db;
    String userID;
    private String childDbName="Addingproducts";
    private ProgressDialog loadingBar;
    private FirebaseApp signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

            info = (TextView)findViewById(R.id.info);
            logoutbtn = (Button)findViewById(R.id.logoutbtn);
            db = FirebaseFirestore.getInstance();

            logoutbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GetData();
                }
            });
        }
        public void GetData(){
            db.collection("Products").document(userID).collection("Userproducts")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                String resultStr = "";

                                for(DocumentSnapshot document : task.getResult()){
                                    Products products = document.toObject(Products.class);
                                    resultStr += "Name: "+products.getProductname() + "\nDescription: "+products.getDescription() + "\nPrice: " +products.getPrice()
                                            + "\n\n";
                                }
                                info.setText(resultStr);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Error "+e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
        }

    }


