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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.fragments.AllItemFragment;
import com.example.user.grocybusiness.models.ItemModel;
import com.example.user.grocybusiness.models.ItemVariantsModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//
//public class OutOfStockItemAdapter extends RecyclerView.Adapter<OutOfStockItemAdapter.OutofStockViewHolder> {
//
//    Context context;
//    ArrayList<ItemModel> items_list;
//    FirebaseFirestore firebaseFirestore;
//    ArrayList<ItemCategoryModel> category_wise_items_list = new ArrayList();
//    int parent_position;
//
//
//    public OutOfStockItemAdapter(Context context, ArrayList<ItemModel> items_list) {
//        super();
//        this.context = context;
//        this.items_list = items_list;
//    }
//
//    public OutOfStockItemAdapter(Context context, ArrayList<ItemModel> items_list, ArrayList<ItemCategoryModel> category_wise_items_list, int parent_position) {
//        super();
//        this.context = context;
//        this.items_list = items_list;
//        this.category_wise_items_list = category_wise_items_list;
//        this.parent_position = parent_position;
//    }
//
//    @NonNull
//    @Override
//    public OutOfStockItemAdapter.OutofStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_out_of_stock_items, parent, false);
//        return new OutOfStockItemAdapter.OutofStockViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull OutOfStockItemAdapter.OutofStockViewHolder holder, int position) {
//        ItemModel itemModel = items_list.get(position);
//
//        if (itemModel.getInStock() == false) {
//            holder.itemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
//            DocumentReference documentReference = firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);
//
//            holder.itemName.setText(itemModel.getItemsProductName());
//            holder.itemDes.setText(itemModel.getItemsProductDescription());
//            holder.itemPrice.setText(itemModel.getItemsPrice());
//            holder.itemQuantity.setText(itemModel.getItemsQuantity());
//
//            if (itemModel.getInStock()) {
//                holder.switchMaterial.setChecked(true);
//            } else {
//                holder.switchMaterial.setChecked(false);
//            }
//
//
//            Glide.with(holder.itemImage.getContext()).load(itemModel.getItemsImage()).into(holder.itemImage);
//
//
//            holder.btnSwitch.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (itemModel.getItemVariants() != null) {
//                        View variantLayout = LayoutInflater.from(context).inflate(R.layout.layout_variants_dialog, holder.viewGroup, false);
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
//                        builder.setView(variantLayout);
//
//                        AlertDialog alertDialog = builder.create();
//                        ImageView close_dialog = variantLayout.findViewById(R.id.close_dialog);
//                        RecyclerView itemVariantsRecycler = variantLayout.findViewById(R.id.item_variants_recycler);
//                        ArrayList<ItemVariantsModel> arrayList = itemModel.getItemVariants();
////                    for (Map.Entry mapElement : itemModel.getItemVariants().entrySet()) {
////                        String key = (String) mapElement.getKey();
////                        HashMap item = (HashMap) mapElement.getValue();
////                        ItemVariantsModel itemVariantsModel = new ItemVariantsModel();
////                        itemVariantsModel.setItemPrice((String) item.get("itemPrice"));
////                        itemVariantsModel.setItemQuantity((String) item.get("itemQuantity"));
////                        itemVariantsModel.setItemID((String) item.get("itemId"));
////                        itemVariantsModel.setVariant_id(key);
////                        itemVariantsModel.setInStock((Boolean) item.get("inStock"));
////                        arrayList.add(itemVariantsModel);
////                    }
//                        OutOfStockItemVariantsAdapter itemVariantsAdapter = new OutOfStockItemVariantsAdapter(context, arrayList, position);
//                        itemVariantsRecycler.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
//                        itemVariantsRecycler.setHasFixedSize(false);
//                        itemVariantsRecycler.setAdapter(itemVariantsAdapter);
//                        itemVariantsAdapter.notifyDataSetChanged();
//                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                        alertDialog.show();
//                        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                            @Override
//                            public void onDismiss(DialogInterface dialog) {
//                                int onCount = 0;
//                                for (int i = 0; i < arrayList.size(); i++) {
//                                    if (arrayList.get(i).isInStock() == true) {
//                                        onCount++;
//                                    }
//                                }
//                                if (onCount <= 0) {
//                                    holder.btnSwitch.setChecked(false);
//                                    documentReference.collection("Items").document(itemModel.getItemId())
//                                            .update("inStock", false).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        @Override
//                                        public void onSuccess(Void aVoid) {
//                                            itemModel.setInStock(false);
//                                        }
//                                    }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//                                            itemModel.setInStock(true);
//                                            holder.btnSwitch.setChecked(true);
//                                        }
//                                    });
//                                } else {
//                                    holder.btnSwitch.setChecked(true);
//                                    documentReference.collection("Items").document(itemModel.getItemId())
//                                            .update("inStock", true).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        @Override
//                                        public void onSuccess(Void aVoid) {
//                                            itemModel.setInStock(true);
//                                        }
//                                    }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//                                            itemModel.setInStock(false);
//                                            holder.btnSwitch.setChecked(false);
//                                        }
//                                    });
//                                }
//                            }
//                        });
//                        close_dialog.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                alertDialog.dismiss();
//                            }
//                        });
//                    } else {
//                        if (holder.btnSwitch.isChecked()) {
//                            documentReference.collection("Items").document(itemModel.getItemId())
//                                    .update("inStock", true).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    itemModel.setInStock(true);
//                                    int i=0;
//                                    for(i = 0; i< AllItemFragment.arrayList1.size(); i++){
//                                        ArrayList<ItemModel> items= AllItemFragment.arrayList1.get(i).getItems_list();
//                                        for(int j=0;j<items.size();j++){
//                                            if(itemModel.getItemId().equals(items.get(j).getItemId())){
//                                                items.get(j).setInStock(true);
//                                                AllItemFragment.itemCategoryAdapter.notifyItemChanged(i);
//                                                OutOfStockItemFragment.outOfStockItemCategoryAdapter.notifyItemChanged(i);
//                                                notifyItemChanged(position);
//                                                break;
//                                            }
//                                        }
//                                    }
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//                                    itemModel.setInStock(false);
//                                    holder.btnSwitch.setChecked(false);
//                                }
//                            });
//                        } else {
//                            documentReference.collection("Items").document(itemModel.getItemId())
//                                    .update("inStock", false).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    itemModel.setInStock(false);
//                                    int i=0;
//                                    for(i = 0; i< AllItemFragment.arrayList1.size(); i++){
//                                        ArrayList<ItemModel> items= AllItemFragment.arrayList1.get(i).getItems_list();
//                                        for(int j=0;j<items.size();j++){
//                                            if(itemModel.getItemId().equals(items.get(j).getItemId())){
//                                                items.get(j).setInStock(false);
//                                                AllItemFragment.itemCategoryAdapter.notifyItemChanged(i);
//                                                OutOfStockItemFragment.outOfStockItemCategoryAdapter.notifyItemChanged(i);
//                                                notifyItemChanged(position);
//                                                break;
//                                            }
//                                        }
//                                    }
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//                                    itemModel.setInStock(true);
//                                    holder.btnSwitch.setChecked(true);
//                                }
//                            });
//                        }
//                    }
//                }
//            });
//        } else {
//            holder.itemView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return items_list.size();
//    }
//
//
//    public class OutofStockViewHolder extends RecyclerView.ViewHolder {
//
//        TextView itemName, itemDes, itemPrice, itemQuantity;
//        ImageView itemImage;
//        SwitchMaterial switchMaterial;
//        SwitchMaterial btnSwitch;
//        ViewGroup viewGroup;
//
//        public OutofStockViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            itemName = itemView.findViewById(R.id.item_name);
//            itemDes = itemView.findViewById(R.id.item_des);
//            itemPrice = itemView.findViewById(R.id.item_price);
//            itemImage = itemView.findViewById(R.id.item_img);
//            itemQuantity = itemView.findViewById(R.id.item_quantity);
//            switchMaterial = itemView.findViewById(R.id.btn_switch);
//            btnSwitch = itemView.findViewById(R.id.btn_switch);
//
//            viewGroup = itemView.findViewById(android.R.id.content);
//
//        }
//    }
//}
public class OutOfStockItemAdapter extends FirestoreRecyclerAdapter<ItemModel, OutOfStockItemAdapter.OutofStockViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    ArrayList<ItemVariantsModel> arrayList = new ArrayList();

    public OutOfStockItemAdapter(@NonNull FirestoreRecyclerOptions<ItemModel> options) {
        super(options);
    }

    public OutOfStockItemAdapter(@NonNull FirestoreRecyclerOptions<ItemModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull OutofStockViewHolder holder, int position, @NonNull ItemModel model) {

        String modelId = getSnapshots().getSnapshot(position).getId();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);

        holder.itemName.setText(model.getItemsProductName());
        holder.itemDes.setText(model.getItemsProductDescription());
        holder.itemPrice.setText(model.getItemsPrice());
        holder.itemQuantity.setText(model.getItemsQuantity());

        if (model.getInStock()) {
            holder.switchMaterial.setChecked(true);
        } else {
            holder.switchMaterial.setChecked(false);
        }


        Glide.with(holder.itemImage.getContext()).load(model.getItemsImage()).into(holder.itemImage);


        holder.btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.isVariants() != false) {
                    View variantLayout = LayoutInflater.from(context).inflate(R.layout.layout_variants_dialog, holder.viewGroup, false);

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setView(variantLayout);

                    AlertDialog alertDialog = builder.create();
                    ImageView close_dialog = variantLayout.findViewById(R.id.close_dialog);
                    RecyclerView itemVariantsRecycler = variantLayout.findViewById(R.id.item_variants_recycler);
                    arrayList = new ArrayList<>();
                    int i = 0;
                    for (i = 0; i < AllItemFragment.arrayList1.size(); i++) {
                        ArrayList<ItemModel> items = AllItemFragment.arrayList1.get(i).getItems_list();
                        for (int j = 0; j < items.size(); j++) {
                            if (modelId.equals(items.get(j).getItemId())) {
                                arrayList = items.get(j).getItemVariants();
                                break;
                            }
                        }
                    }
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
                                if (arrayList.get(i).isInStock() == true) {
                                    onCount++;
                                }
                            }
                            if (onCount <= 0) {
                                holder.btnSwitch.setChecked(false);
                                documentReference.collection("Items").document(modelId)
                                        .update("inStock", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        model.setInStock(false);
                                        int i = 0;
                                        for (i = 0; i < AllItemFragment.arrayList1.size(); i++) {
                                            ArrayList<ItemModel> items = AllItemFragment.arrayList1.get(i).getItems_list();
                                            for (int j = 0; j < items.size(); j++) {
                                                if (modelId.equals(items.get(j).getItemId())) {
                                                    items.get(j).setInStock(true);
                                                    AllItemFragment.itemCategoryAdapter.notifyItemChanged(i);
                                                    notifyItemChanged(position);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                        model.setInStock(true);
                                        holder.btnSwitch.setChecked(true);
                                    }
                                });
                            } else {
                                holder.btnSwitch.setChecked(true);
                                documentReference.collection("Items").document(modelId)
                                        .update("inStock", true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        model.setInStock(true);
                                        int i = 0;
                                        for (i = 0; i < AllItemFragment.arrayList1.size(); i++) {
                                            ArrayList<ItemModel> items = AllItemFragment.arrayList1.get(i).getItems_list();
                                            for (int j = 0; j < items.size(); j++) {
                                                if (modelId.equals(items.get(j).getItemId())) {
                                                    items.get(j).setInStock(true);
                                                    AllItemFragment.itemCategoryAdapter.notifyItemChanged(i);
                                                    notifyItemChanged(position);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                        model.setInStock(false);
                                        holder.btnSwitch.setChecked(false);
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
                        documentReference.collection("Items").document(modelId)
                                .update("inStock", true).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                model.setInStock(true);
                                int i = 0;
                                for (i = 0; i < AllItemFragment.arrayList1.size(); i++) {
                                    ArrayList<ItemModel> items = AllItemFragment.arrayList1.get(i).getItems_list();
                                    for (int j = 0; j < items.size(); j++) {
                                        if (modelId.equals(items.get(j).getItemId())) {
                                            items.get(j).setInStock(true);
                                            AllItemFragment.itemCategoryAdapter.notifyItemChanged(i);
                                            notifyItemChanged(position);
                                            break;
                                        }
                                    }
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                model.setInStock(false);
                                holder.btnSwitch.setChecked(false);
                            }
                        });
                    } else {
                        documentReference.collection("Items").document(modelId)
                                .update("inStock", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                model.setInStock(false);
                                int i = 0;
                                for (i = 0; i < AllItemFragment.arrayList1.size(); i++) {
                                    ArrayList<ItemModel> items = AllItemFragment.arrayList1.get(i).getItems_list();
                                    for (int j = 0; j < items.size(); j++) {
                                        if (modelId.equals(items.get(j).getItemId())) {
                                            items.get(j).setInStock(false);
                                            AllItemFragment.itemCategoryAdapter.notifyItemChanged(i);
                                            notifyItemChanged(position);
                                            break;
                                        }
                                    }
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                model.setInStock(true);
                                holder.btnSwitch.setChecked(true);
                            }
                        });
                    }
                }
            }
        });
    }

    @NonNull
    @Override
    public OutofStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_out_of_stock_items, parent, false);
        return new OutOfStockItemAdapter.OutofStockViewHolder(view);
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