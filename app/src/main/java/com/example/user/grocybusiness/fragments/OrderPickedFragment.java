package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.adapters.OrdersPickedAdapter;
import com.example.user.grocybusiness.models.OrdersAllModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderPickedFragment extends Fragment {

    RecyclerView ordersPickedRecycler;
    OrdersPickedAdapter ordersPickedAdapter;
    HashMap<String, Object> order_data = new HashMap();

    ArrayList<OrdersAllModel> arrayList = new ArrayList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_picked, container, false);

        ordersPickedRecycler = view.findViewById(R.id.recyclerview_orders_picked);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        CollectionReference collectionReference = firebaseFirestore.collection("ShopKeeper").document(firebaseAuth.getUid()).collection("MyOrders");

        Query query = collectionReference.whereEqualTo("shopId", MainActivity.selectedShop)
                .whereEqualTo("orderStatus", "Picked");
        FirestoreRecyclerOptions<OrdersAllModel> options = new FirestoreRecyclerOptions.Builder<OrdersAllModel>()
                .setQuery(query, OrdersAllModel.class).build();
        ordersPickedRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        ordersPickedRecycler.setHasFixedSize(false);
        ordersPickedAdapter = new OrdersPickedAdapter(options);
        ordersPickedAdapter.notifyDataSetChanged();
        ordersPickedRecycler.setAdapter(ordersPickedAdapter);


        return view;
    }
//
//    private void setAdapter(View view) {
//        arrayList = new ArrayList();
//        ordersPickedRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
//
//        ordersPickedRecycler.setHasFixedSize(false);
//
//        ordersPickedAdapter = new OrdersPickedAdapter(view.getContext(), arrayList);
//
//        ordersPickedRecycler.setAdapter(ordersPickedAdapter);
//
//        for (Map.Entry mapElement : order_data.entrySet()) {
//            String key = (String) mapElement.getKey();
//            HashMap<String, Object> value = (HashMap<String, Object>) mapElement.getValue();
//
//            OrdersAllModel ordersAllModel = new OrdersAllModel();
//            ordersAllModel.setOrderId((String) value.get("orderNumberId"));
//            ordersAllModel.setOrderTime((String) value.get("dateTime"));
//            ordersAllModel.setOrderStatus((String) value.get("orderStatus"));
//            ordersAllModel.setUserDetails((String) value.get("userName"));
//            ordersAllModel.setOrderPrice("" + value.get("orderAmount"));
//            ordersAllModel.setOrderDocumentId(key);
//            arrayList.add(ordersAllModel);
//
//        }
//
//
//        ordersPickedAdapter.notifyDataSetChanged();
//
//    }

    @Override
    public void onStart() {
        super.onStart();
        ordersPickedAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        ordersPickedAdapter.stopListening();
    }

}