package com.example.user.grocybusiness.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.fragments.AllItemFragment;
import com.example.user.grocybusiness.models.ItemModel;
import com.example.user.grocybusiness.models.ItemVariantsModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OutOfStockItemAdapter extends RecyclerView.Adapter<OutOfStockItemAdapter.OutofStockViewHolder> {

    Context context;
    static ArrayList<ItemModel> items_list;
    FirebaseFirestore firebaseFirestore;

    public OutOfStockItemAdapter(Context context) {
        super();
        this.context = context;
//        this.items_list = items_list;
    }


    public OutOfStockItemAdapter(ArrayList<ItemModel> items_list) {
        this.items_list = items_list;
    }


    public OutOfStockItemAdapter(Context context, ArrayList<ItemModel> items_list) {
        super();
        this.context = context;
        this.items_list = items_list;
    }

    @NonNull
    @Override
    public OutOfStockItemAdapter.OutofStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_out_of_stock_items, parent, false);
        return new OutOfStockItemAdapter.OutofStockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutOfStockItemAdapter.OutofStockViewHolder holder, int position) {
        ItemModel itemModel = AllItemFragment.arrayList.get(position);

        if (itemModel.getInStock() == false) {
            holder.itemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            DocumentReference documentReference = firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);

            holder.itemName.setText(itemModel.getItemsProductName());
            holder.itemDes.setText(itemModel.getItemsProductDescription());
            holder.itemPrice.setText(itemModel.getItemsPrice());
            holder.itemQuantity.setText(itemModel.getItemsQuantity());

            if (itemModel.getInStock()) {
                holder.switchMaterial.setChecked(true);
            } else {
                holder.switchMaterial.setChecked(false);
            }


            Glide.with(holder.itemImage.getContext()).load(itemModel.getItemsImage()).into(holder.itemImage);


            holder.btnSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemModel.getItemVariants() != null) {
                        View variantLayout = LayoutInflater.from(context).inflate(R.layout.layout_variants_dialog, holder.viewGroup, false);

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                        builder.setView(variantLayout);

                        AlertDialog alertDialog = builder.create();
                        ImageView close_dialog = variantLayout.findViewById(R.id.close_dialog);
                        RecyclerView itemVariantsRecycler = variantLayout.findViewById(R.id.item_variants_recycler);
                        ArrayList<ItemVariantsModel> arrayList = itemModel.getItemVariants();
//                    for (Map.Entry mapElement : itemModel.getItemVariants().entrySet()) {
//                        String key = (String) mapElement.getKey();
//                        HashMap item = (HashMap) mapElement.getValue();
//                        ItemVariantsModel itemVariantsModel = new ItemVariantsModel();
//                        itemVariantsModel.setItemPrice((String) item.get("itemPrice"));
//                        itemVariantsModel.setItemQuantity((String) item.get("itemQuantity"));
//                        itemVariantsModel.setItemID((String) item.get("itemId"));
//                        itemVariantsModel.setVariant_id(key);
//                        itemVariantsModel.setInStock((Boolean) item.get("inStock"));
//                        arrayList.add(itemVariantsModel);
//                    }
                        OutOfStockItemVariantsAdapter itemVariantsAdapter = new OutOfStockItemVariantsAdapter(context, arrayList, position);
                        itemVariantsRecycler.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
                        itemVariantsRecycler.setHasFixedSize(false);
                        itemVariantsRecycler.setAdapter(itemVariantsAdapter);
                        itemVariantsAdapter.notifyDataSetChanged();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.show();
                        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                int onCount = 0;
                                for (int i = 0; i < arrayList.size(); i++) {
                                    if (arrayList.get(i).isInStock() == true) {
                                        onCount++;
                                    }
                                }
                                if (onCount <= 0) {
                                    holder.btnSwitch.setChecked(false);
                                    documentReference.collection("Items").document(itemModel.getItemId())
                                            .update("inStock", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            itemModel.setInStock(false);
                                            notifyDataSetChanged();
                                            AllItemFragment.itemCategoryAdapter.notifyDataSetChanged();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                            itemModel.setInStock(true);
                                            holder.btnSwitch.setChecked(true);
//                                            notifyDataSetChanged();
                                            AllItemFragment.itemCategoryAdapter.notifyDataSetChanged();
                                        }
                                    });
                                } else {
                                    holder.btnSwitch.setChecked(true);
                                    documentReference.collection("Items").document(itemModel.getItemId())
                                            .update("inStock", true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            itemModel.setInStock(true);
                                            notifyDataSetChanged();
                                            AllItemFragment.itemCategoryAdapter.notifyDataSetChanged();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                            itemModel.setInStock(false);
                                            holder.btnSwitch.setChecked(false);
                                            notifyDataSetChanged();
                                            AllItemFragment.itemCategoryAdapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                            }
                        });
                        close_dialog.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                    } else {
                        if (holder.btnSwitch.isChecked()) {
                            documentReference.collection("Items").document(itemModel.getItemId())
                                    .update("inStock", true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    itemModel.setInStock(true);
                                    notifyDataSetChanged();
                                    AllItemFragment.itemCategoryAdapter.notifyDataSetChanged();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                    itemModel.setInStock(false);
                                    holder.btnSwitch.setChecked(false);
                                    notifyDataSetChanged();
                                    AllItemFragment.itemCategoryAdapter.notifyDataSetChanged();
                                }
                            });
                        } else {
                            documentReference.collection("Items").document(itemModel.getItemId())
                                    .update("inStock", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    itemModel.setInStock(false);
                                    notifyDataSetChanged();
                                    AllItemFragment.itemCategoryAdapter.notifyDataSetChanged();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                    itemModel.setInStock(true);
                                    holder.btnSwitch.setChecked(true);
                                    notifyDataSetChanged();
                                    AllItemFragment.itemCategoryAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                }
            });
        } else {
            holder.itemView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        }
    }

    @Override
    public int getItemCount() {
        return AllItemFragment.arrayList.size();
    }


    public class OutofStockViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemDes, itemPrice, itemQuantity;
        ImageView itemImage;
        SwitchMaterial switchMaterial;
        SwitchMaterial btnSwitch;
        ViewGroup viewGroup;

        public OutofStockViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_name);
            itemDes = itemView.findViewById(R.id.item_des);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemImage = itemView.findViewById(R.id.item_img);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            switchMaterial = itemView.findViewById(R.id.btn_switch);
            btnSwitch = itemView.findViewById(R.id.btn_switch);

            viewGroup = itemView.findViewById(android.R.id.content);

        }
    }
}
