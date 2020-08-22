package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.adapters.AllItemAdapter;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllItemFragment extends Fragment {

    public static AllItemAdapter allItemAdapter;
    RecyclerView recyclerView;
    ArrayList<ItemModel> arrayList;
    static HashMap<String, Object> all_items = new HashMap();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        ArrayList<ItemModel> arrayList=new ArrayList();

        View view = inflater.inflate(R.layout.fragment_all_item, container, false);
        recyclerView = view.findViewById(R.id.all_item_recycler);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


        DocumentReference documentReference = firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);
        Query query = documentReference.collection("Items");
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot document : queryDocumentSnapshots) {
                    String itemID = document.getId();
                    all_items.put(document.getId(), document.getData());

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
        return view;
    }

    private void setAdapter(View view) {
        arrayList = new ArrayList();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

        allItemAdapter = new AllItemAdapter(view.getContext(), arrayList);

        recyclerView.setAdapter(allItemAdapter);

        for (Map.Entry mapElement : all_items.entrySet()) {
            String key = (String) mapElement.getKey();
            HashMap<String, Object> item = (HashMap<String, Object>) mapElement.getValue();

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
            arrayList.add(itemModel);
        }

        allItemAdapter.notifyDataSetChanged();
        OutOfStockItemFragment.outOfStockItemAdapter.notifyDataSetChanged();

    }
}