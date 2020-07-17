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

public class RegisterActivity extends AppCompatActivity {

    AutoCompleteTextView shopCategory;
    TextView shopName, shopGst, shopAddress, shopCity, shopState, shopEmail, shopTimings, shopDiscount, signIn, shopDescription;
    Button btnNext;
    Bundle bundle;
    String sName, sAddress, sDiscount, sCity, sEmail, sGst, sState, sTimings, sCategory, sDiscription;
    ScrollView scrollView;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        shopCategory=findViewById(R.id.reg_shop_category);
        shopName=findViewById(R.id.reg_name);
        shopAddress=findViewById(R.id.reg_address);
        shopCity=findViewById(R.id.reg_city);
        shopDiscount=findViewById(R.id.reg_discount);
        shopEmail=findViewById(R.id.reg_email);
        shopGst=findViewById(R.id.reg_gst);
        shopTimings=findViewById(R.id.reg_timings);
        shopState=findViewById(R.id.reg_state);
        shopDescription=findViewById(R.id.reg_description);
        signIn=findViewById(R.id.reg_signin);
        btnNext=findViewById(R.id.btnRegNext);
        scrollView=(ScrollView)findViewById(R.id.scroll_view);
        checkBox=findViewById(R.id.checkBox);

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

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sName=shopName.getText().toString();
                sAddress=shopAddress.getText().toString();
                sGst=shopGst.getText().toString();
                sEmail=shopEmail.getText().toString();
                sTimings=shopTimings.getText().toString();
                sCity=shopCity.getText().toString();
                sCategory=shopCategory.getText().toString();
                sState=shopState.getText().toString();
                sDiscount=shopDiscount.getText().toString()+" %";
                sDiscription=shopDescription.getText().toString();


                if(sName.isEmpty()){
                    shopName.setError("Shop Name is Mandatory");
                    scrollView.pageScroll(View.FOCUS_UP);
                }
                else if(sEmail.isEmpty()){
                    shopEmail.setError("Email is Mandatory");
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
                    Toast.makeText(RegisterActivity.this, "Please Accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent =new Intent(RegisterActivity.this, UploadDocActivity.class);
                    bundle.putString("sName",sName);
                    bundle.putString("sAddress",sAddress);
                    bundle.putString("sDiscount",sDiscount);
                    bundle.putString("sState",sState);
                    bundle.putString("sEmail",sEmail);
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
        Intent intent=new Intent(RegisterActivity.this,WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}