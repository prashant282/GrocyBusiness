package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.adapters.AllItemAdapter;
import com.example.user.grocybusiness.adapters.ItemCategoryAdapter;
import com.example.user.grocybusiness.models.ItemCategoryModel;
import com.example.user.grocybusiness.models.ItemModel;
import com.example.user.grocybusiness.models.ItemVariantsModel;
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

public class AllItemFragment extends Fragment {

    public static AllItemAdapter allItemAdapter;
    public static RecyclerView recyclerView;
    ArrayList<ItemModel> arrayList;
    static HashMap<String, Object> all_items = new HashMap();
    View view;
    SortedSet<String> item_categories_name = new TreeSet();
    ArrayList<ItemCategoryModel> arrayList1;

    ItemCategoryAdapter itemCategoryAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        ArrayList<ItemModel> arrayList=new ArrayList();

        view = inflater.inflate(R.layout.fragment_all_item, container, false);
        recyclerView = view.findViewById(R.id.item_category_recycler);

        dataBaseCall(view);
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

//        allItemAdapter = new AllItemAdapter(view.getContext(), arrayList1);
        itemCategoryAdapter = new ItemCategoryAdapter(view.getContext(), arrayList1);

        recyclerView.setAdapter(itemCategoryAdapter);
//        recyclerView.setViewCacheExtension(null);

        itemCategoryAdapter.notifyDataSetChanged();
        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();

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
//                        arrayList.add(itemModel);
                    items.add(itemModel);
                }
            }
            shopItemsCategoryModel.setItems_list(items);
            arrayList1.add(shopItemsCategoryModel);
        }

        itemCategoryAdapter.notifyDataSetChanged();
//        allItemAdapter.notifyDataSetChanged();
//        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResume() {
        super.onResume();
        dataBaseCall(view);
    }
}