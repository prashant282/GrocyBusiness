package com.example.user.grocybusiness.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.AddItemVariant;
import com.example.user.grocybusiness.activities.EditItemActivity;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.fragments.OutOfStockItemFragment;
import com.example.user.grocybusiness.models.ItemModel;
import com.example.user.grocybusiness.models.ItemVariantsModel;
import com.example.user.grocybusiness.models.NotificationModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.ArrayList;


public class AllItemAdapter extends RecyclerView.Adapter<AllItemAdapter.AllItemViewHolder> {

    Context context;

    Bundle bundle;

    static ArrayList<ItemModel> items_list = new ArrayList();

    public AllItemAdapter(Context context, ArrayList<ItemModel> items_list) {
        this.context = context;
        this.items_list = items_list;
    }

    @NonNull
    @Override
    public AllItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_all_items, parent, false);
        return new AllItemAdapter.AllItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AllItemViewHolder holder, int position) {

        ItemModel itemModel = items_list.get(position);
        bundle=new Bundle();
        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        DocumentReference documentReference=firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        DocumentReference documentReference = firebaseFirestore.collection("ShopsMain").document("qAeielILTRnO7hAIeiS7");


        holder.itemName.setText(itemModel.getItemsProductName());
        holder.itemDes.setText(itemModel.getItemsProductDescription());
        holder.itemPrice.setText(itemModel.getItemsPrice());
        holder.itemQuantity.setText(itemModel.getItemsQuantity());
        if (itemModel.getInStock()) {
            holder.btnSwitch.setChecked(true);
        } else {
            holder.btnSwitch.setChecked(false);
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
                    ItemVariantsAdapter itemVariantsAdapter = new ItemVariantsAdapter(context, arrayList, position);
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
                                if (arrayList.get(i).isInStock()) {
                                    onCount++;
                                }
                            }
                            if (onCount > 0) {
                                holder.btnSwitch.setChecked(true);
                                documentReference.collection("Items").document(itemModel.getItemId())
                                        .update("inStock", true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        itemModel.setInStock(true);
                                        notifyDataSetChanged();
                                        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                        itemModel.setInStock(false);
                                        notifyDataSetChanged();
                                        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                                    }
                                });
                            } else {
                                holder.btnSwitch.setChecked(false);
                                documentReference.collection("Items").document(itemModel.getItemId())
                                        .update("inStock", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        itemModel.setInStock(false);
                                        notifyDataSetChanged();
                                        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                        itemModel.setInStock(true);
                                        notifyDataSetChanged();
                                        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
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
                                OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                itemModel.setInStock(false);
                                notifyDataSetChanged();
                                OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                            }
                        });
                    } else {
                        documentReference.collection("Items").document(itemModel.getItemId())
                                .update("inStock", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                itemModel.setInStock(false);
                                notifyDataSetChanged();
                                OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                itemModel.setInStock(true);
                                notifyDataSetChanged();
                                OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
            }
        });



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VariantAdapter variantAdapter;
                RecyclerView recyclerView;
                TextView addVariant;

                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(holder.cardView.getContext());
                bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet_item_variant);
//                bottomSheetDialog.setCanceledOnTouchOutside(false);


                recyclerView=bottomSheetDialog.findViewById(R.id.item_variants);
                addVariant=bottomSheetDialog.findViewById(R.id.add_variant_txt);





//                FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
//                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();



//                DocumentReference documentReference = firebaseFirestore.collection("ShopKeeper").document(firebaseAuth.getUid());
//        documentReference.collection("Items").whereEqualTo("inStock",false);
//                DocumentReference documentReference1=documentReference.collection("Items").document(itemModel.getItemId());
//                Query query = documentReference1.collection("Variants");
//                FirestoreRecyclerOptions<ItemVariantsModel> options= new FirestoreRecyclerOptions.Builder<ItemVariantsModel>()
//                        .setQuery(query, ItemVariantsModel.class).build();
//                variantAdapter=new VariantAdapter(options);
//                variantAdapter.notifyDataSetChanged();
//                recyclerView.setHasFixedSize(true);
//                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//                recyclerView.setAdapter(variantAdapter);

                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

                variantAdapter=new VariantAdapter(view.getContext(),itemModel.getItemVariants());

                recyclerView.setHasFixedSize(false);


                recyclerView.setAdapter(variantAdapter);
                variantAdapter.notifyDataSetChanged();

                addVariant.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intentAddVariant =new Intent(holder.cardView.getContext(), AddItemVariant.class);
                        intentAddVariant.putExtras(bundle);
                        holder.cardView.getContext().startActivity(intentAddVariant);

                    }
                });







//                TextView editVariant=bottomSheetDialog.findViewById(R.id.edit_item_variant);
//                TextView deleteVariant=bottomSheetDialog.findViewById(R.id.delete_item_variant);

//                editVariant.set
//                OnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        holder.cardView.getContext().startActivity(new Intent(holder.cardView.getContext(),AddItemVariant.class));
//                    }
//                });
                bottomSheetDialog.show();
            }
        });




        holder.cardView.setOnLongClickListener(view -> {

            bundle.putString("itemCategory",itemModel.getItemCategory());
            bundle.putString("itemName", itemModel.getItemsProductName());
            bundle.putString("itemPrice", itemModel.getItemsPrice());
            bundle.putString("itemImage", itemModel.getItemsImage());
            bundle.putString("itemDes", itemModel.getItemsProductDescription());
            bundle.putString("itemQuantity", itemModel.getItemsQuantity());
            bundle.putString("itemId",itemModel.getItemId());

//

            PopupMenu popupMenu=new PopupMenu(holder.cardView.getContext(),view);
            popupMenu.inflate(R.menu.popup_item_feature);
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.edit_item:
                            Intent intentEditItem =new Intent(holder.cardView.getContext(), EditItemActivity.class);
                            intentEditItem.putExtras(bundle);
                            holder.cardView.getContext().startActivity(intentEditItem);
                            return true;

                        case R.id.add_variant:
                            Intent intentAddVariant =new Intent(holder.cardView.getContext(), AddItemVariant.class);
                            intentAddVariant.putExtras(bundle);
                            holder.cardView.getContext().startActivity(intentAddVariant);
                            return true;


                        case R.id.delete_item:



                            LayoutInflater layoutInflater=LayoutInflater.from(holder.cardView.getContext());
                            View view1=layoutInflater.inflate(R.layout.layout_logout_alert_dialog,null);

                            Button yesButton=view1.findViewById(R.id.btn_yes);
                            Button cancelButton=view1.findViewById(R.id.btn_cancel);

                            final androidx.appcompat.app.AlertDialog alertDialog=new androidx.appcompat.app.AlertDialog.Builder(holder.cardView.getContext())
                                    .setView(view1)
                                    .create();
                            alertDialog.show();
                            yesButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    documentReference.collection("Items").document(itemModel.getItemId())
                                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                                            alertDialog.dismiss();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            alertDialog.dismiss();
                                        }
                                    });

                                }
                            });
                            cancelButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    alertDialog.dismiss();
                                }
                            });


                            return true;

                        default:
                            return false;
                    }
                }
            });

            return false;
        });

    }

    @Override
    public int getItemCount() {
        return items_list.size();
    }


    public static class AllItemViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemDes, itemPrice, itemQuantity;
        ImageView itemImage;
        SwitchMaterial btnSwitch;
        ViewGroup viewGroup;
//        RecyclerView itemVariantsRecycler;

        CardView cardView;

        public AllItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_name);
            itemDes = itemView.findViewById(R.id.item_des);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemImage = itemView.findViewById(R.id.item_img);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            btnSwitch = itemView.findViewById(R.id.btn_switch);

            cardView=itemView.findViewById(R.id.layout_all_item);

            viewGroup = itemView.findViewById(android.R.id.content);
//            itemVariantsRecycler = itemView.findViewById(R.id.item_variants_recycler);


        }

    }

}


