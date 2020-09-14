package com.example.user.grocybusiness.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.fragments.AllItemFragment;
import com.example.user.grocybusiness.fragments.ItemCustomImageFragment;
import com.example.user.grocybusiness.fragments.ItemsFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class EditItemActivity extends AppCompatActivity {

    private TextView itemCategory, itemName, itemPrice, itemDes, itemWeight;
    Button btnEditItem;
    private ImageButton imageView;
    Bundle bundle;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    ProgressDialog progressDialog;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    static Map shop=new HashMap<>();


    ItemsFragment itemsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        imageView=findViewById(R.id.img_item);
        itemCategory=findViewById(R.id.item_category);
        itemDes=findViewById(R.id.item_des);
        itemName=findViewById(R.id.item_name);
        itemPrice=findViewById(R.id.item_price);
        itemWeight=findViewById(R.id.item_weight);
        btnEditItem=findViewById(R.id.btn_edit_item);

        itemsFragment=new ItemsFragment();

        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        firebaseFirestore=FirebaseFirestore.getInstance();


        bundle=getIntent().getExtras();

        if (!shop.isEmpty()){
            itemCategory.setText(Objects.requireNonNull(shop.get("itemCategory")).toString());
            itemName.setText(Objects.requireNonNull(shop.get("itemsProductName")).toString());
            itemDes.setText(Objects.requireNonNull(shop.get("itemsProductDescription")).toString());
            itemPrice.setText(Objects.requireNonNull(shop.get("itemsPrice")).toString());
            itemWeight.setText(Objects.requireNonNull(shop.get("itemsQuantity")).toString());
            btnEditItem.setEnabled(true);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), AddItemPhotoActivity.filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);



        }
        else {
            assert bundle != null;
            itemCategory.setText(bundle.getString("itemCategory"));
            itemName.setText(bundle.getString("itemName"));
            itemDes.setText(bundle.getString("itemDes"));
            itemWeight.setText(bundle.getString("itemQuantity"));
            itemPrice.setText(bundle.getString("itemPrice"));

            Glide.with(imageView.getContext()).load(bundle.getString("itemImage")).into(imageView);



        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shop.put("itemsProductName",itemName.getText().toString());
                shop.put("itemCategory",itemCategory.getText().toString());
                shop.put("itemsProductDescription",itemDes.getText().toString());
                shop.put("itemsPrice",itemPrice.getText().toString());
                shop.put("itemsQuantity",itemWeight.getText().toString());

                Intent intent=new Intent(EditItemActivity.this,AddItemPhotoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
//                startActivity(new Intent(EditItemActivity.this,AddItemPhotoActivity.class));
                finish();
            }
        });

        itemName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!itemName.getText().toString().isEmpty() && !itemCategory.getText().toString().isEmpty() && !itemDes.getText().toString().isEmpty() && !itemWeight.getText().toString().isEmpty() && !itemPrice.getText().toString().isEmpty()) {

                    btnEditItem.setEnabled(true);
                } else {

                    btnEditItem.setEnabled(false);
                }
            }
        });
        itemCategory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!itemName.getText().toString().isEmpty() && !itemCategory.getText().toString().isEmpty() && !itemDes.getText().toString().isEmpty() && !itemWeight.getText().toString().isEmpty() && !itemPrice.getText().toString().isEmpty()) {

                    btnEditItem.setEnabled(true);
                } else {

                    btnEditItem.setEnabled(false);
                }
            }
        });
        itemDes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!itemName.getText().toString().isEmpty() && !itemCategory.getText().toString().isEmpty() && !itemDes.getText().toString().isEmpty() && !itemWeight.getText().toString().isEmpty() && !itemPrice.getText().toString().isEmpty()) {

                    btnEditItem.setEnabled(true);
                } else {

                    btnEditItem.setEnabled(false);
                }
            }
        });
        itemPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!itemName.getText().toString().isEmpty() && !itemCategory.getText().toString().isEmpty() && !itemDes.getText().toString().isEmpty() && !itemWeight.getText().toString().isEmpty() && !itemPrice.getText().toString().isEmpty()) {

                    btnEditItem.setEnabled(true);
                } else {

                    btnEditItem.setEnabled(false);
                }
            }
        });
        itemWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!itemName.getText().toString().isEmpty() && !itemCategory.getText().toString().isEmpty() && !itemDes.getText().toString().isEmpty() && !itemWeight.getText().toString().isEmpty() && !itemPrice.getText().toString().isEmpty()) {

                    btnEditItem.setEnabled(true);
                } else {

                    btnEditItem.setEnabled(false);
                }
            }
        });


        btnEditItem.setOnClickListener(view -> {

            progressDialog = new ProgressDialog(view.getContext());
            progressDialog.show();
            progressDialog.setContentView(R.layout.process_dialog);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.dismiss();
            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(
                    android.R.color.transparent);


            if (!shop.isEmpty()){
                String uiid=UUID.randomUUID().toString();
                StorageReference reference=storageReference.child("Item images/"+uiid );
                reference.putFile(AddItemPhotoActivity.filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> task = Objects.requireNonNull(Objects.requireNonNull(taskSnapshot.getMetadata()).getReference()).getDownloadUrl();
                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                shop.put("itemsImage",uri.toString());
                                String itemId=bundle.getString("itemId");

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditItemActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditItemActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
            else{

                shop.put("itemsProductName",itemName.getText().toString());
                shop.put("itemCategory",itemCategory.getText().toString());
                shop.put("itemsProductDescription",itemDes.getText().toString());
                shop.put("itemsPrice",itemPrice.getText().toString());
                shop.put("itemsQuantity",itemWeight.getText().toString());


                documentReference=firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);
                documentReference.collection("Items").document(bundle.getString("itemId"))
                        .update(shop).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(EditItemActivity.this, "Item Updated!!", Toast.LENGTH_SHORT).show();
                                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                            fragmentTransaction.replace(android.R.id.content, itemsFragment);
                                            fragmentTransaction.commit();
//                        AllItemFragment.allItemAdapter.notifyDataSetChanged();
                        shop=new HashMap<>();
                        finish();
                        progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String err= e.getMessage();
                        Toast.makeText(EditItemActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}