package com.example.user.grocybusiness.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.grocybusiness.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button buttonSendOTP;
    EditText editTextNumber;
    FirebaseFirestore db;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonSendOTP = findViewById(R.id.btnSendOtp);
        editTextNumber = findViewById(R.id.reg_NumText);
        progressBar = findViewById(R.id.progressBar);
        db = FirebaseFirestore.getInstance();
        String number_from_register = getIntent().getStringExtra("phone_number");
        if (number_from_register != null) {
            editTextNumber.setText(number_from_register.substring(number_from_register.length() - 10));
            buttonSendOTP.setEnabled(true);
        }
        editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 10) {
                    buttonSendOTP.setEnabled(true);
                }
            }
        });

        buttonSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                buttonSendOTP.setEnabled(false);
                String uphone = editTextNumber.getText().toString();
                final String phone_number = "+91" + uphone.substring(uphone.length() - 10);
                Query query = db.collection("ShopKeeper").whereEqualTo("pNumber", phone_number);

                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().getDocuments().size() > 0) {
                                Intent otp_intent = new Intent(LoginActivity.this, OtpActivity.class);

                                otp_intent.putExtra("phone_number", phone_number);
                                startActivity(otp_intent);
                                finish();
                                Toast.makeText(LoginActivity.this, "Otp sent! to number:- " + phone_number, Toast.LENGTH_LONG).show();
                            } else {
                                Intent signUp_intent = new Intent(LoginActivity.this, RegisterActivity.class);
                                Toast.makeText(LoginActivity.this, "You are not Registered Yet!!", Toast.LENGTH_LONG).show();
                                startActivity(signUp_intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                            buttonSendOTP.setEnabled(true);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });

            }
        });
    }
//
//    private void showProgress() {
//        progressDialog = new ProgressDialog(LoginActivity.this);
//        progressDialog.show();
//        progressDialog.setContentView(R.layout.process_dialog);
//        progressDialog.getWindow().setBackgroundDrawableResource(
//                android.R.color.transparent
//        );
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}