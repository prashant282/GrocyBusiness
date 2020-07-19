package com.example.user.grocybusiness.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.user.grocybusiness.R;

public class AddShopActivity extends AppCompatActivity {

    AutoCompleteTextView shopCategory;
    TextView shopName, shopGst, shopAddress, shopCity, shopState, shopTimings, shopDiscount, shopDescription;
    Button btnNext;
    Bundle bundle;
    String sName, sAddress, sCity, sGst, sState, sTimings, sCategory, sDiscription;
    ScrollView scrollView;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        shopCategory=findViewById(R.id.reg_shop_category);
        shopName=findViewById(R.id.reg_name);
        shopAddress=findViewById(R.id.reg_address);
        shopCity=findViewById(R.id.reg_city);
        shopGst=findViewById(R.id.reg_gst);

        shopState=findViewById(R.id.reg_state);


        btnNext=findViewById(R.id.btnRegNext);
        scrollView= findViewById(R.id.scroll_view);

        bundle=new Bundle();


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.shop_category,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shopCategory.setAdapter(adapter);



        shopCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopCategory.showDropDown();

            }
        });



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sName=shopName.getText().toString();
                sAddress=shopAddress.getText().toString();
                sGst=shopGst.getText().toString();
                sTimings=shopTimings.getText().toString();
                sCity=shopCity.getText().toString();
                sCategory=shopCategory.getText().toString();
                sState=shopState.getText().toString();
                sDiscription=shopDescription.getText().toString();


                if(sName.isEmpty()){
                    shopName.setError("Shop Name is Mandatory");
                    scrollView.pageScroll(View.FOCUS_UP);
                }

                else if(sGst.isEmpty()){
                    shopGst.setError("GST No is Mandatory");
                    scrollView.pageScroll(View.FOCUS_UP);
                }
                else if (sCategory.isEmpty()){

                    shopCategory.setError("Shop Category is Mandatory");
                    scrollView.pageScroll(View.FOCUS_UP);
                }
                else if (sTimings.isEmpty()){

                    shopTimings.setError("Shop Timings is Mandatory");
                    scrollView.pageScroll(View.FOCUS_UP);
                }
                else if (shopDiscount.getText().toString().isEmpty()){

                    shopDiscount.setError("Discount Off is Mandatory");
                    scrollView.pageScroll(View.FOCUS_UP);
                }
                else if (sAddress.isEmpty()){

                    shopAddress.setError("Shop Address is Mandatory");
                    scrollView.pageScroll(View.FOCUS_UP);
                }
                else if (sCity.isEmpty()){

                    shopCity.setError("City is Mandatory");
                    scrollView.pageScroll(View.FOCUS_UP);
                }
                else if (sState.isEmpty()){

                    shopState.setError("State is Mandatory");
                    scrollView.pageScroll(View.FOCUS_UP);
                }
                else if (sDiscription.isEmpty()){

                    shopDescription.setError("Short description is Mandatory");
                    scrollView.pageScroll(View.FOCUS_UP);
                }
                else if(!checkBox.isChecked()){
                    checkBox.setError("Accept terms and conditions to register");
                    Toast.makeText(AddShopActivity.this, "Please Accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent =new Intent(AddShopActivity.this, UploadDocActivity.class);
                    bundle.putString("sName",sName);
                    bundle.putString("sAddress",sAddress);
                    bundle.putString("sState",sState);
                    bundle.putString("sGst",sGst);
                    bundle.putString("sTimings",sTimings);
                    bundle.putString("sCity",sCity);
                    bundle.putString("sDescription",sDiscription);
                    intent.putExtras(bundle);
                    startActivity(intent);


                }


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(AddShopActivity.this,WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}