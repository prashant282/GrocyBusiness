package com.example.user.grocybusiness.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.ItemModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;


public class AllItemAdapter extends FirestoreRecyclerAdapter<ItemModel, AllItemAdapter.MyViewHolder> {

    public AllItemAdapter(@NonNull FirestoreRecyclerOptions<ItemModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull ItemModel model) {

        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        DocumentReference documentReference=firebaseFirestore.collection("ShopsMain").document(
                "qAeielILTRnO7hAIeiS7");


        holder.itemName.setText(model.getItemsProductName());
        holder.itemDes.setText(model.getItemsProductDescription());
        holder.itemPrice.setText(model.getItemsPrice());
        holder.itemQuantity.setText(model.getItemsQuantity());
        if(model.getInStock()){
            holder.btnSwitch.setChecked(true);
        }
        else {
            holder.btnSwitch.setChecked(false);
        }
        Glide.with(holder.itemImage.getContext()).load(model.getItemsImage()).into(holder.itemImage);


        holder.btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    documentReference.collection("Items").whereEqualTo("itemsProductName",model.getItemsProductName()).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                    Objects.requireNonNull(documentReference.collection("Items").document(queryDocumentSnapshots.getDocuments().get(0).getId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            if (documentSnapshot.getBoolean("inStock")==false){
                                                documentReference.collection("Items").document(queryDocumentSnapshots.getDocuments().get(0).getId())
                                                        .update("inStock",true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {

                                                    }
                                                });
                                            }
//                                            else {
//                                                compoundButton.setChecked(false);
//                                            }

                                        }
                                    }));






                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }
                else {
                    documentReference.collection("Items").whereEqualTo("itemsProductName",model.getItemsProductName()).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                    documentReference.collection("Items").document(queryDocumentSnapshots.getDocuments().get(0).getId()).get()
                                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                    if (documentSnapshot.getBoolean("inStock")==true){

                                                        documentReference.collection("Items").document(queryDocumentSnapshots.getDocuments().get(0).getId())
                                                                .update("inStock",false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {

                                                            }
                                                        });
                                                    }
//                                                    else {
//                                                        compoundButton.setChecked(true);
//                                                    }
                                                }
                                            });









                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }
            }
        });
//        holder.itemImage.setImage(model.getItemImage());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_all_items, parent, false);
        return new  AllItemAdapter.MyViewHolder(view);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemDes, itemPrice, itemQuantity;
        ImageView itemImage;
        SwitchMaterial btnSwitch;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_name);
            itemDes = itemView.findViewById(R.id.item_des);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemImage = itemView.findViewById(R.id.item_img);
            itemQuantity=itemView.findViewById(R.id.item_quantity);
            btnSwitch=itemView.findViewById(R.id.btn_switch);

        }
    }
}


