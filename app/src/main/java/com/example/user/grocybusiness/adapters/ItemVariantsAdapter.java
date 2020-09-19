package com.example.user.grocybusiness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.models.ItemVariantsModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemVariantsAdapter extends RecyclerView.Adapter<ItemVariantsAdapter.ItemVariantsViewHolder> {

    Context context;
    ArrayList<ItemVariantsModel> variants_list = new ArrayList();
    int parent_position;

    public ItemVariantsAdapter(Context context, ArrayList<ItemVariantsModel> variants_list, int parent_position) {
        this.context = context;
        this.variants_list = variants_list;
        this.parent_position = parent_position;
    }


    @NonNull
    @Override
    public ItemVariantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_variants_item, parent, false);
        return new ItemVariantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVariantsViewHolder holder, int position) {
        ItemVariantsModel itemVariantsModel = variants_list.get(position);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop).collection("Items")
                .document(itemVariantsModel.getItemID());
        holder.variant_weight.setText(itemVariantsModel.getItemQuantity() + "g");
        holder.variant_price.setText("Rs. " + itemVariantsModel.getItemPrice());

        if (itemVariantsModel.isInStock()) {
            holder.variantSwitch.setChecked(true);
        } else {
            holder.variantSwitch.setChecked(false);
        }

        holder.variantSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.variantSwitch.isChecked()) {
                    documentReference.collection("Variants").document(itemVariantsModel.getVariant_id())
                            .update("inStock", true).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            itemVariantsModel.setInStock(true);
//                            notifyItemChanged(position);
                            int onCount = 0;
                            for (int i = 0; i < variants_list.size(); i++) {
                                if (variants_list.get(i).isInStock() == false) {
                                    onCount++;
                                }
                            }
                            if (onCount <= 0) {
                                documentReference.update("inStock", true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
//                                        AllItemAdapter.items_list.get(parent_position).setInStock(true);
//                                        ItemCategoryAdapter.allItemAdapter.notifyItemChanged(parent_position);
//                                        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//                                        AllItemAdapter.items_list.get(parent_position).setInStock(false);
//                                        ItemCategoryAdapter.allItemAdapter.notifyItemChanged(parent_position);
//                                        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                                    }
                                });
                            } else {
                                documentReference.update("inStock", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
//                                        AllItemAdapter.items_list.get(parent_position).setInStock(false);
//                                        ItemCategoryAdapter.allItemAdapter.notifyItemChanged(parent_position);
//                                        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//                                        AllItemAdapter.items_list.get(parent_position).setInStock(false);
//                                        ItemCategoryAdapter.allItemAdapter.notifyItemChanged(parent_position);
//                                        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    itemVariantsModel.setInStock(false);
                                    holder.variantSwitch.setChecked(false);
//                                    notifyItemChanged(position);
                                }
                            });
                } else {
                    documentReference.collection("Variants").document(itemVariantsModel.getVariant_id())
                            .update("inStock", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            itemVariantsModel.setInStock(false);
                            notifyItemChanged(position);
                            int onCount = 0;
                            for (int i = 0; i < variants_list.size(); i++) {
                                if (variants_list.get(i).isInStock()) {
                                    onCount++;
                                }
                            }
                            if (onCount > 0) {
                                documentReference.update("inStock", true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
//                                        AllItemAdapter.items_list.get(parent_position).setInStock(true);
//                                        ItemCategoryAdapter.allItemAdapter.notifyItemChanged(parent_position);
//                                        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//                                        AllItemAdapter.items_list.get(parent_position).setInStock(false);
//                                        ItemCategoryAdapter.allItemAdapter.notifyItemChanged(parent_position);
//                                        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                                    }
                                });
                            } else {
                                documentReference.update("inStock", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
//                                        AllItemAdapter.items_list.get(parent_position).setInStock(false);
//                                        ItemCategoryAdapter.allItemAdapter.notifyDataSetChanged();
//                                        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//                                        AllItemAdapter.items_list.get(parent_position).setInStock(false);
//                                        ItemCategoryAdapter.allItemAdapter.notifyItemChanged(parent_position);
//                                        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    itemVariantsModel.setInStock(true);
                                    holder.variantSwitch.setChecked(true);
//                                    notifyItemChanged(position);
                                }
                            });
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return variants_list.size();
    }

    public class ItemVariantsViewHolder extends RecyclerView.ViewHolder {

        TextView variant_weight;
        TextView variant_price;
        SwitchMaterial variantSwitch;

        public ItemVariantsViewHolder(@NonNull View itemView) {
            super(itemView);
            variant_weight = itemView.findViewById(R.id.variant_weight);
            variant_price = itemView.findViewById(R.id.variant_price);
            variantSwitch = itemView.findViewById(R.id.variant_btn_switch);
        }
    }
}
