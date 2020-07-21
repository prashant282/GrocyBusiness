package com.example.user.grocybusiness.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.grocybusiness.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    TextView ownerName, ownerEmail, ownerNumber, ownerCity, signIn;
    Button btnNext;
    ScrollView scrollView;
    CheckBox checkBox;
    Bundle bundle;
    String oName, oNumber, oEmail, oCity;

    FirebaseFirestore db;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ownerName = findViewById(R.id.reg_owner_name);
        ownerEmail = findViewById(R.id.reg_owner_email);
        ownerCity = findViewById(R.id.reg_city_name);
        ownerNumber = findViewById(R.id.reg_owner_number);
        btnNext = findViewById(R.id.btn_reg_next);
        scrollView = findViewById(R.id.scroll_view);
        checkBox = findViewById(R.id.check_box);
        signIn = findViewById(R.id.sign_in);
        progressBar = findViewById(R.id.progressBar);
        db = FirebaseFirestore.getInstance();


        bundle = new Bundle();

        ownerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!ownerName.getText().toString().isEmpty() && !ownerEmail.getText().toString().isEmpty() && (ownerNumber.getText().toString().length() == 10) && !ownerCity.getText().toString().isEmpty() && checkBox.isChecked()) {

                    btnNext.setEnabled(true);
                } else {

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

                if (!ownerName.getText().toString().isEmpty() && !ownerEmail.getText().toString().isEmpty() && (ownerNumber.getText().toString().length() == 10) && !ownerCity.getText().toString().isEmpty() && checkBox.isChecked()) {

                    btnNext.setEnabled(true);
                } else {

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

                if (!ownerName.getText().toString().isEmpty() && !ownerEmail.getText().toString().isEmpty() && (ownerNumber.getText().toString().length() == 10) && !ownerCity.getText().toString().isEmpty() && checkBox.isChecked()) {

                    btnNext.setEnabled(true);
                } else {

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

                if (!ownerName.getText().toString().isEmpty() && !ownerEmail.getText().toString().isEmpty() && (ownerNumber.getText().toString().length() == 10) && !ownerCity.getText().toString().isEmpty() && checkBox.isChecked()) {

                    btnNext.setEnabled(true);
                } else {

                    btnNext.setEnabled(false);
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!ownerName.getText().toString().isEmpty() && !ownerEmail.getText().toString().isEmpty() && (ownerNumber.getText().toString().length() == 10) && !ownerCity.getText().toString().isEmpty() && checkBox.isChecked()) {

                    btnNext.setEnabled(true);

                } else {

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

                progressBar.setVisibility(View.VISIBLE);
                btnNext.setEnabled(false);

                oName = ownerName.getText().toString();
                oEmail = ownerEmail.getText().toString();
                oNumber = ownerNumber.getText().toString();
                oCity = ownerCity.getText().toString();


                final String phone_number = "+91" + oNumber.substring(oNumber.length() - 10);

                Query query = db.collection("Shopkeeper").whereEqualTo("pNumber", phone_number);

//                if (!ownerCity.getText().toString().equalsIgnoreCase("Delhi")){
//
//                    Intent intent=new Intent(RegisterActivity.this,CityNotAvailableActivity.class);
//                    startActivity(intent);
//                    finish();
//                }

                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().getDocuments().size() > 0) {
                                Intent signIn_intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                Toast.makeText(RegisterActivity.this, "You are already Registered !", Toast.LENGTH_LONG).show();
                                startActivity(signIn_intent);
                                finish();
                            } else {
                                Intent otp_intent = new Intent(RegisterActivity.this, OtpActivity.class);

                                bundle.putString("oNumber", phone_number);
                                bundle.putString("oCity", oCity);
                                bundle.putString("oName", oName);
                                bundle.putString("oEmail", oEmail);
                                otp_intent.putExtras(bundle);
                                otp_intent.putExtra("phone_number", phone_number);
                                startActivity(otp_intent);
                                finish();
                                Toast.makeText(RegisterActivity.this, "Otp will be sent to:- " + phone_number, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                            btnNext.setEnabled(true);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });



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