package com.example.onlineelectronicequipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity {

    private ImageView asus,hp,iphone,iphones,sony,niko;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

            hp= (ImageView) findViewById(R.id.imageView);
            asus= (ImageView) findViewById(R.id.imageView2);
            iphone = (ImageView) findViewById(R.id.imageView3);
            iphones = (ImageView) findViewById(R.id.imageView4);
            niko= (ImageView) findViewById(R.id.imageView5);
            sony = (ImageView) findViewById(R.id.imageView6);

            hp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomePage.this, Addingproducts.class);
                    intent.putExtra("category", "Hp");
                    startActivity(intent);
                }
            });

        asus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, Addingproducts.class);
                intent.putExtra("category", "Asus");
                startActivity(intent);
            }
        });
        iphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, Addingproducts.class);
                intent.putExtra("category", "iphone");
                startActivity(intent);
            }
        });
        iphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, Addingproducts.class);
                intent.putExtra("category", "iphones");
                startActivity(intent);
            }
        });
        niko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, Addingproducts.class);
                intent.putExtra("category", "niko");
                startActivity(intent);
            }
        });
        sony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, Addingproducts.class);
                intent.putExtra("category", "sony");
                startActivity(intent);
            }
        });
    }
}