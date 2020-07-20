package com.example.user.grocybusiness.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.grocybusiness.R;

public class RegisterActivity extends AppCompatActivity {

    TextView ownerName, ownerEmail, ownerNumber, ownerCity, signIn;
    Button btnNext;
    ScrollView scrollView;
    CheckBox checkBox;
    Bundle bundle;
    String oName,oNumber,oEmail,oCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ownerName=findViewById(R.id.reg_owner_name);
        ownerEmail=findViewById(R.id.reg_owner_email);
        ownerCity=findViewById(R.id.reg_city_name);
        ownerNumber=findViewById(R.id.reg_owner_number);
        btnNext=findViewById(R.id.btn_reg_next);
        scrollView=findViewById(R.id.scroll_view);
        checkBox=findViewById(R.id.check_box);
        signIn=findViewById(R.id.sign_in);
        bundle=new Bundle();

        oName=ownerName.getText().toString();
        oEmail=ownerName.getText().toString();
        oNumber=ownerName.getText().toString();
        oCity=ownerName.getText().toString();

        ownerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!ownerName.getText().toString().isEmpty() && !ownerEmail.getText().toString().isEmpty() && !ownerNumber.getText().toString().isEmpty() && !ownerCity.getText().toString().isEmpty() && checkBox.isChecked()){

                    btnNext.setEnabled(true);
                }
                else {

                    btnNext.setEnabled(false);
                }
            }
        });

        ownerEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!ownerName.getText().toString().isEmpty() && !ownerEmail.getText().toString().isEmpty() && !ownerNumber.getText().toString().isEmpty() && !ownerCity.getText().toString().isEmpty() && checkBox.isChecked()){

                    btnNext.setEnabled(true);
                }
                else {

                    btnNext.setEnabled(false);
                }
            }
        });

        ownerNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!ownerName.getText().toString().isEmpty() && !ownerEmail.getText().toString().isEmpty() && !ownerNumber.getText().toString().isEmpty() && !ownerCity.getText().toString().isEmpty() && checkBox.isChecked()){

                    btnNext.setEnabled(true);
                }
                else {

                    btnNext.setEnabled(false);
                }
            }
        });

        ownerCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!ownerName.getText().toString().isEmpty() && !ownerEmail.getText().toString().isEmpty() && !ownerNumber.getText().toString().isEmpty() && !ownerCity.getText().toString().isEmpty() && checkBox.isChecked()){

                    btnNext.setEnabled(true);
                }
                else {

                    btnNext.setEnabled(false);
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!ownerName.getText().toString().isEmpty() && !ownerEmail.getText().toString().isEmpty() && !ownerNumber.getText().toString().isEmpty() && !ownerCity.getText().toString().isEmpty() && checkBox.isChecked()) {

                    btnNext.setEnabled(true);

                }
                else {

                    btnNext.setEnabled(false);

                }

            }
        });


//        if(ownerName.getText().toString().isEmpty()){
////                    ownerName.setError("Shop Name is Mandatory");
////                    scrollView.pageScroll(View.FOCUS_UP);
//        }
//        else if(ownerEmail.getText().toString().isEmpty()){
////                    ownerEmail.setError("Shop Name is Mandatory");
////                    scrollView.pageScroll(View.FOCUS_UP);
//        }
//        else if(ownerNumber.getText().toString().isEmpty()){
////                    ownerNumber.setError("Shop Name is Mandatory");
////                    scrollView.pageScroll(View.FOCUS_UP);
//        }
//        else if(ownerCity.getText().toString().isEmpty()){
////                    ownerCity.setError("Shop Name is Mandatory");
////                    scrollView.pageScroll(View.FOCUS_UP);
//        }
//        else if(!checkBox.isChecked()){
////                    checkBox.setError("Accept terms and conditions first!!");
//            Toast.makeText(RegisterActivity.this, "Accept terms and conditions first!!", Toast.LENGTH_SHORT).show();
//        }
//
//        else {
//
//            btnNext.setEnabled(true);
//
//        }

            btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 if (!ownerCity.getText().toString().equalsIgnoreCase("Delhi")){

                    Intent intent=new Intent(RegisterActivity.this,CityNotAvailableActivity.class);
                    startActivity(intent);
                    finish();
                }

                 else {

                     bundle.putString("oNumber", oNumber);
                     bundle.putString("oCity", oCity);
                     bundle.putString("oName", oName);
                     bundle.putString("oEmail", oEmail);
                     Intent intent=new Intent(RegisterActivity.this,AddShopActivity.class);
                     intent.putExtras(bundle);
                     startActivity(intent);


                 }



                }

        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }
}