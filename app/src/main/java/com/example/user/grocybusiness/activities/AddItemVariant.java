package com.example.user.grocybusiness.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.fragments.ItemsFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AddItemVariant extends AppCompatActivity {

    Button btnAddVariant;
    TextView variantQuantity, variantPrice;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    DocumentReference documentReference, documentReference1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_variant);

        btnAddVariant=findViewById(R.id.btn_add_item_variant);
        variantQuantity=findViewById(R.id.item_variant_quantity);
        variantPrice=findViewById(R.id.item_variant_price);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        variantQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!variantPrice.getText().toString().isEmpty() && !variantQuantity.getText().toString().isEmpty()) {

                    btnAddVariant.setEnabled(true);
                } else {

                    btnAddVariant.setEnabled(false);
                }
            }
        });


        variantPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!variantPrice.getText().toString().isEmpty() && !variantQuantity.getText().toString().isEmpty()) {

                    btnAddVariant.setEnabled(true);
                } else {

                    btnAddVariant.setEnabled(false);
                }
            }
        });


        btnAddVariant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map itemVariant=new HashMap<>();
                itemVariant.put("inStock",true);
                itemVariant.put("itemPrice", variantPrice.getText().toString().trim());
                itemVariant.put("itemQuantity",variantQuantity.getText().toString().trim());
                documentReference=firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);
                documentReference.collection("Items").whereEqualTo("itemsImage",getIntent().getStringExtra("itemImage"))
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                       documentReference1= documentReference.collection("Items").document(queryDocumentSnapshots.getDocuments().get(0).getId());
                       documentReference1.collection("Variants").add(itemVariant).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                           @Override
                           public void onSuccess(DocumentReference documentReference) {
                               Toast.makeText(AddItemVariant.this, "Item Variant Added Successfully", Toast.LENGTH_SHORT).show();
//
//                               getSupportFragmentManager().beginTransaction().replace(R.id.add_variant_layout,new ItemsFragment()).commit();

                               FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                               fragmentTransaction.replace(android.R.id.content, new ItemsFragment());
                               fragmentTransaction.commit();
                               finish();
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {

                               Toast.makeText(AddItemVariant.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                           }
                       });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(AddItemVariant.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}