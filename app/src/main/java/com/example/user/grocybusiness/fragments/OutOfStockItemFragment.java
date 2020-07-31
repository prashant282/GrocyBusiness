package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.adapters.OutOfStockItemAdapter;
import com.example.user.grocybusiness.models.ItemModel;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OutOfStockItemFragment extends Fragment {

    OutOfStockItemAdapter outOfStockItemAdapter;
    RecyclerView recyclerView;
    ArrayList<ItemModel> arrayList;
    HashMap<String, Object> all_items = new HashMap();

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

        DocumentReference documentReference = firebaseFirestore.collection("ShopsMain").document("qAeielILTRnO7hAIeiS7");
        Query query = documentReference.collection("Items");
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(view.getContext(), "Error Occured :-" + error.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    for (DocumentSnapshot document : value) {
                        all_items.put(document.getId(), document.getData());
                    }
                    setAdapter(view);
                }
            }
        });

        return view;
    }


    private void setAdapter(View view) {
        arrayList = new ArrayList();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

        outOfStockItemAdapter = new OutOfStockItemAdapter(view.getContext(), arrayList);

        recyclerView.setAdapter(outOfStockItemAdapter);

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
            itemModel.setItemId(key);
            if (!(Boolean) item.get("inStock")) {
                arrayList.add(itemModel);
            }
        }

        outOfStockItemAdapter.notifyDataSetChanged();

    }

}