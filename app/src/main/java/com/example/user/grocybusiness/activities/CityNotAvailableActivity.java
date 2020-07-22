package com.example.user.grocybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.user.grocybusiness.R;

public class CityNotAvailableActivity extends AppCompatActivity {

    TextView stayTuned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_not_available);
        stayTuned=findViewById(R.id.stay_tuned);

        stayTuned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CityNotAvailableActivity.this,AddShopActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(CityNotAvailableActivity.this,AddShopActivity.class);
        startActivity(intent);
        finish();
    }
}