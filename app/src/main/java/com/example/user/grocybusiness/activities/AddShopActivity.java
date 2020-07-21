package com.example.user.grocybusiness.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.user.grocybusiness.R;

import androidx.appcompat.app.AppCompatActivity;

public class AddShopActivity extends AppCompatActivity {


    TextView shopName, shopGst, shopAddress, shopCity, shopState, shopCategory, shopPinCode;
    Button btnNext;
    Bundle bundle;
    String sName, sAddress, sCity, sGst, sState, sCategory, sPinCode;
    ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        shopCategory=findViewById(R.id.reg_shop_category);
        shopName=findViewById(R.id.reg_shopName);
        shopAddress=findViewById(R.id.reg_shop_address);
        shopCity=findViewById(R.id.reg_city_name);
        shopGst=findViewById(R.id.reg_gst_number);
        shopPinCode=findViewById(R.id.reg_pin_code);
        shopState=findViewById(R.id.reg_state);

        shopState = findViewById(R.id.reg_state);
//        String string= "Shop Name ";
//        String string1= "*";
//        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder();
//        spannableStringBuilder.append(string);
//        int n= spannableStringBuilder.length();
//        spannableStringBuilder.append(string1);
//        int n1= spannableStringBuilder.length();
//        spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.lightRed)),n,n1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        shopName.setHint(spannableStringBuilder);


        btnNext = findViewById(R.id.btn_reg_next);
        scrollView = findViewById(R.id.scroll_view);

//        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.shop_category,
//                android.R.layout.simple_spinner_item);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//
//        shopCategorySpinner.setAdapter(adapter);


        bundle = getIntent().getExtras();

        sName = shopName.getText().toString();
        sAddress = shopAddress.getText().toString();
        sGst = shopGst.getText().toString();
//                sTimings=shopTimings.getText().toString();
        sCity = shopCity.getText().toString();
        sCategory = shopCategory.getText().toString();
        sState=shopState.getText().toString();
        sPinCode=shopPinCode.getText().toString();


        shopName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!shopAddress.getText().toString().isEmpty() && !shopCategory.getText().toString().isEmpty() && !shopCity.getText().toString().isEmpty() && !shopGst.getText().toString().isEmpty() && !shopState.getText().toString().isEmpty() && !shopName.getText().toString().isEmpty() && !shopPinCode.getText().toString().isEmpty()){

                    btnNext.setEnabled(true);
                }
                else {

                    btnNext.setEnabled(false);
                }
            }
        });

        shopPinCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!shopAddress.getText().toString().isEmpty() && !shopCategory.getText().toString().isEmpty() && !shopCity.getText().toString().isEmpty() && !shopGst.getText().toString().isEmpty() && !shopState.getText().toString().isEmpty() && !shopName.getText().toString().isEmpty() && !shopPinCode.getText().toString().isEmpty()){

                    btnNext.setEnabled(true);
                }
                else {

                    btnNext.setEnabled(false);
                }
            }
        });
        shopAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!shopAddress.getText().toString().isEmpty() && !shopCategory.getText().toString().isEmpty() && !shopCity.getText().toString().isEmpty() && !shopGst.getText().toString().isEmpty() && !shopState.getText().toString().isEmpty() && !shopName.getText().toString().isEmpty() && !shopPinCode.getText().toString().isEmpty()){

                    btnNext.setEnabled(true);
                }
                else {

                    btnNext.setEnabled(false);
                }
            }
        });
        shopCategory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!shopAddress.getText().toString().isEmpty() && !shopCategory.getText().toString().isEmpty() && !shopCity.getText().toString().isEmpty() && !shopGst.getText().toString().isEmpty() && !shopState.getText().toString().isEmpty() && !shopName.getText().toString().isEmpty() && !shopPinCode.getText().toString().isEmpty()){

                    btnNext.setEnabled(true);
                }
                else {

                    btnNext.setEnabled(false);
                }
            }
        });
        shopCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!shopAddress.getText().toString().isEmpty() && !shopCategory.getText().toString().isEmpty() && !shopCity.getText().toString().isEmpty() && !shopGst.getText().toString().isEmpty() && !shopState.getText().toString().isEmpty() && !shopName.getText().toString().isEmpty() && !shopPinCode.getText().toString().isEmpty()){

                    btnNext.setEnabled(true);
                }
                else {

                    btnNext.setEnabled(false);
                }
            }
        });
        shopGst.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!shopAddress.getText().toString().isEmpty() && !shopCategory.getText().toString().isEmpty() && !shopCity.getText().toString().isEmpty() && !shopGst.getText().toString().isEmpty() && !shopState.getText().toString().isEmpty() && !shopName.getText().toString().isEmpty() && !shopPinCode.getText().toString().isEmpty()){

                    btnNext.setEnabled(true);
                }
                else {

                    btnNext.setEnabled(false);
                }
            }
        });
        shopState.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!shopAddress.getText().toString().isEmpty() && !shopCategory.getText().toString().isEmpty() && !shopCity.getText().toString().isEmpty() && !shopGst.getText().toString().isEmpty() && !shopState.getText().toString().isEmpty() && !shopName.getText().toString().isEmpty() && !shopPinCode.getText().toString().isEmpty()){

                    btnNext.setEnabled(true);
                }
                else {

                    btnNext.setEnabled(false);
                }
            }
        });


//                sDiscription=shopDescription.getText().toString();


//        if(sName.isEmpty()){
////                    shopName.setError("Shop Name is Mandatory");
////                    scrollView.pageScroll(View.FOCUS_UP);
//        }
//
//        else if (sCategory.isEmpty()){
//
////                    shopCategory.setError("Shop Category is Mandatory");
////                    scrollView.pageScroll(View.FOCUS_UP);
//        }
//
//        else if(sGst.isEmpty()){
////                    shopGst.setError("GST No is Mandatory");
////                    scrollView.pageScroll(View.FOCUS_UP);
//        }
//
////                else if (sTimings.isEmpty()){
////
////                    shopTimings.setError("Shop Timings is Mandatory");
////                    scrollView.pageScroll(View.FOCUS_UP);
////                }
////                else if (shopDiscount.getText().toString().isEmpty()){
////
////                    shopDiscount.setError("Discount Off is Mandatory");
////                    scrollView.pageScroll(View.FOCUS_UP);
////                }
//        else if (sAddress.isEmpty()){
//
////                    shopAddress.setError("Shop Address is Mandatory");
////                    scrollView.pageScroll(View.FOCUS_UP);
//        }
//        else if (sCity.isEmpty()){
//
////                    shopCity.setError("City is Mandatory");
////                    scrollView.pageScroll(View.FOCUS_UP);
//        }
//        else if (sState.isEmpty()){
//
////                    shopState.setError("State is Mandatory");
////                    scrollView.pageScroll(View.FOCUS_UP);
//        }
////                else if (sDiscription.isEmpty()){
////
////                    shopDescription.setError("Short description is Mandatory");
////                    scrollView.pageScroll(View.FOCUS_UP);
////                }
////                else if(!checkBox.isChecked()){
////                    checkBox.setError("Accept terms and conditions to register");
////                    Toast.makeText(AddShopActivity.this, "Please Accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
////                }
//        else {
//
//            btnNext.setEnabled(true);
//
//        }
//

//        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.shop_category,android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        shopCategory.setAdapter(adapter);
//
//
//
//        shopCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shopCategory.showDropDown();
//
//            }
//        });



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent =new Intent(AddShopActivity.this, UploadDocActivity.class);
                    bundle.putString("sName",sName);
                    bundle.putString("sAddress",sAddress);
                    bundle.putString("sState",sState);
                    bundle.putString("sGst",sGst);
                    bundle.putString("sCategory",sCategory);
//                    bundle.putString("sTimings",sTimings);
                    bundle.putString("sCity",sCity);
//                    bundle.putString("sDescription",sDiscription);
                    intent.putExtras(bundle);
                    startActivity(intent);



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