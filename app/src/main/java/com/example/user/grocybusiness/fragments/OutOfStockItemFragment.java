package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.adapters.OutOfStockItemAdapter;
import com.example.user.grocybusiness.adapters.OutOfStockItemCategoryAdapter;
import com.example.user.grocybusiness.models.ItemCategoryModel;
import com.example.user.grocybusiness.models.ItemModel;
import com.example.user.grocybusiness.models.ItemVariantsModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OutOfStockItemFragment extends Fragment {

    RecyclerView recyclerView;
    public static OutOfStockItemCategoryAdapter outOfStockItemCategoryAdapter;
    public static ArrayList<ItemCategoryModel> arrayList1 = new ArrayList();
    HashMap<String, Object> all_items = new HashMap();
    SortedSet<String> item_categories_name = new TreeSet();
    OutOfStockItemAdapter outOfStockItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_out_of_stock_item, container, false);
        recyclerView = view.findViewById(R.id.out_of_stock_item_recycler);


        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        DocumentReference documentReference = firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);
        Query query = documentReference.collection("Items").whereEqualTo("inStock", false);
        FirestoreRecyclerOptions<ItemModel> options = new FirestoreRecyclerOptions.Builder<ItemModel>()
                .setQuery(query, ItemModel.class).build();


        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

        outOfStockItemAdapter = new OutOfStockItemAdapter(options, view.getContext());

        recyclerView.setAdapter(outOfStockItemAdapter);

//        setAdapter(view);

        return view;
    }

    private void dataBaseCall(View view) {


        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


        DocumentReference documentReference = firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);

        Query query = documentReference.collection("Items");
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot document : queryDocumentSnapshots) {
                    String itemID = document.getId();
                    all_items.put(document.getId(), document.getData());
                    item_categories_name.add((String) document.getData().get("itemCategory"));

                    DocumentReference variantReference = documentReference.collection("Items").document(document.getId());
                    Query query1 = variantReference.collection("Variants");

                    query1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                HashMap<String, HashMap> itemVariants = new HashMap<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    HashMap<String, Object> temp = (HashMap<String, Object>) document.getData();
                                    temp.put("itemId", itemID);
                                    itemVariants.put(document.getId(), temp);
                                }
                                HashMap<String, Object> hm = (HashMap) all_items.get(itemID);
                                if (itemVariants.size() != 0) {
                                    ArrayList<ItemVariantsModel> variantsList = new ArrayList();
                                    for (Map.Entry mapElement : itemVariants.entrySet()) {
                                        String key = (String) mapElement.getKey();
                                        HashMap item = (HashMap) mapElement.getValue();
                                        ItemVariantsModel itemVariantsModel = new ItemVariantsModel();
                                        itemVariantsModel.setItemPrice((String) item.get("itemPrice"));
                                        itemVariantsModel.setItemQuantity((String) item.get("itemQuantity"));
                                        itemVariantsModel.setItemID((String) item.get("itemId"));
                                        itemVariantsModel.setVariant_id(key);
                                        itemVariantsModel.setInStock((Boolean) item.get("inStock"));
                                        variantsList.add(itemVariantsModel);
                                    }
                                    hm.put("Variants", variantsList);
                                } else {
                                    hm.put("Variants", null);
                                }
                                setAdapter(view);

                            }
                        }
                    });

                }
            }
        });
    }


    private void setAdapter(View view) {
        arrayList1 = new ArrayList();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

        outOfStockItemCategoryAdapter = new OutOfStockItemCategoryAdapter(view.getContext(), arrayList1);

        recyclerView.setAdapter(outOfStockItemCategoryAdapter);

        arrayList1.clear();

        for (String category : item_categories_name) {
            ItemCategoryModel shopItemsCategoryModel = new ItemCategoryModel();
            shopItemsCategoryModel.setItem_category_name(category);
            ArrayList<ItemModel> items = new ArrayList();
            for (Map.Entry mapElement : all_items.entrySet()) {
                String key = (String) mapElement.getKey();
                HashMap item = (HashMap) mapElement.getValue();
                if (((String) item.get("itemCategory")).equals(category)) {
                    ItemModel itemModel = new ItemModel();
                    itemModel.setInStock((Boolean) item.get("inStock"));
                    itemModel.setItemsImage((String) item.get("itemsImage"));
                    itemModel.setItemsPrice((String) item.get("itemsPrice"));
                    itemModel.setItemsProductDescription((String) item.get("itemsProductDescription"));
                    itemModel.setItemsProductName((String) item.get("itemsProductName"));
                    itemModel.setItemsQuantity((String) item.get("itemsQuantity"));
                    itemModel.setItemCategory((String) item.get("itemCategory"));
                    itemModel.setItemId(key);
                    itemModel.setItemVariants((ArrayList<ItemVariantsModel>) item.get("Variants"));
                    items.add(itemModel);
                }
            }
            shopItemsCategoryModel.setItems_list(items);
            arrayList1.add(shopItemsCategoryModel);
        }

        outOfStockItemCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        outOfStockItemAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        outOfStockItemAdapter.stopListening();
    }

}