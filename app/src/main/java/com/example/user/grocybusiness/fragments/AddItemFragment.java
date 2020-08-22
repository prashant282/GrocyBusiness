package com.example.user.grocybusiness.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.activities.UploadDocActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    
    private static final String ARG_PARAM2 = "param2";

    private ImageButton imageView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AddItemPhotoFragment addItemPhotoFragment;
    private TextView itemCategory, itemName, itemPrice, itemDes, itemWeight;
    Button btnAddItem;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    DocumentReference documentReference;
    static Map shop=new HashMap<>();
    int c=0;
    ItemsFragment itemsFragment;
    ProgressDialog progressDialog;


    public AddItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddItemFragment newInstance(String param1, String param2) {
        AddItemFragment fragment = new AddItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_add_item, container, false);
        imageView=view.findViewById(R.id.img_item);
        itemCategory=view.findViewById(R.id.item_category);
        itemDes=view.findViewById(R.id.item_des);
        itemName=view.findViewById(R.id.item_name);
        itemPrice=view.findViewById(R.id.item_price);
        itemWeight=view.findViewById(R.id.item_weight);
        btnAddItem=view.findViewById(R.id.btn_add_item);

        addItemPhotoFragment =new AddItemPhotoFragment();
        itemsFragment=new ItemsFragment();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        firebaseFirestore=FirebaseFirestore.getInstance();




        if (!shop.isEmpty()){
            itemCategory.setText(shop.get("itemCategory").toString());
            itemName.setText(shop.get("itemsProductName").toString());
            itemDes.setText(shop.get("itemsProductDescription").toString());
            itemPrice.setText(shop.get("itemsPrice").toString());
            itemWeight.setText(shop.get("itemsQuantity").toString());
            btnAddItem.setEnabled(true);
            c++;
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), ItemCustomImageFragment.filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);
//            imageView.setImageAlpha((Integer) shop.get("image"));

        }
        imageView.setOnClickListener(view1 -> {
            shop.put("itemsProductName",itemName.getText().toString());
            shop.put("itemCategory",itemCategory.getText().toString());
            shop.put("itemsProductDescription",itemDes.getText().toString());
            shop.put("itemsPrice",itemPrice.getText().toString());
            shop.put("itemsQuantity",itemWeight.getText().toString());
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_frame_layout, addItemPhotoFragment);
            fragmentTransaction.commit();
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

                if (!itemName.getText().toString().isEmpty() && !itemCategory.getText().toString().isEmpty() && !itemDes.getText().toString().isEmpty() && !itemWeight.getText().toString().isEmpty() && !itemPrice.getText().toString().isEmpty() && c>0) {

                    btnAddItem.setEnabled(true);
                } else {

                    btnAddItem.setEnabled(false);
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

                if (!itemName.getText().toString().isEmpty() && !itemCategory.getText().toString().isEmpty() && !itemDes.getText().toString().isEmpty() && !itemWeight.getText().toString().isEmpty() && !itemPrice.getText().toString().isEmpty() && c>0) {

                    btnAddItem.setEnabled(true);
                } else {

                    btnAddItem.setEnabled(false);
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

                if (!itemName.getText().toString().isEmpty() && !itemCategory.getText().toString().isEmpty() && !itemDes.getText().toString().isEmpty() && !itemWeight.getText().toString().isEmpty() && !itemPrice.getText().toString().isEmpty() && c>0) {

                    btnAddItem.setEnabled(true);
                } else {

                    btnAddItem.setEnabled(false);
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

                if (!itemName.getText().toString().isEmpty() && !itemCategory.getText().toString().isEmpty() && !itemDes.getText().toString().isEmpty() && !itemWeight.getText().toString().isEmpty() && !itemPrice.getText().toString().isEmpty() && c>0) {

                    btnAddItem.setEnabled(true);
                } else {

                    btnAddItem.setEnabled(false);
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

                if (!itemName.getText().toString().isEmpty() && !itemCategory.getText().toString().isEmpty() && !itemDes.getText().toString().isEmpty() && !itemWeight.getText().toString().isEmpty() && !itemPrice.getText().toString().isEmpty() && c>0) {

                    btnAddItem.setEnabled(true);
                } else {

                    btnAddItem.setEnabled(false);
                }
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog = new ProgressDialog(view.getContext());
                progressDialog.show();
                progressDialog.setContentView(R.layout.process_dialog);
                Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(
                        android.R.color.transparent);



                StorageReference reference=storageReference.child("Item images/"+ UUID.randomUUID().toString());
                reference.putFile(ItemCustomImageFragment.filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> task = Objects.requireNonNull(Objects.requireNonNull(taskSnapshot.getMetadata()).getReference()).getDownloadUrl();
                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                shop.put("itemsImage",uri.toString());
                                shop.put("inStock",true);
                                documentReference=firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);
                                documentReference.collection("Items")
                                        .add(shop).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(view.getContext(), "Item Added Successfully", Toast.LENGTH_SHORT).show();
                                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                        fragmentTransaction.replace(R.id.main_frame_layout, itemsFragment);
                                        fragmentTransaction.commit();
                                        progressDialog.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                });
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });




        return view;
    }
}