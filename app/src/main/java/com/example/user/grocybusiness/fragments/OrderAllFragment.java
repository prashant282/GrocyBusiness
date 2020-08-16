package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.adapters.OrdersAllAdapter;
import com.example.user.grocybusiness.models.OrdersAllModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
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

public class OrderAllFragment extends Fragment {

    RecyclerView ordersAllRecycler;
    public static OrdersAllAdapter ordersAllAdapter;
    HashMap<String, Object> order_data = new HashMap();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order_all, container, false);
        ordersAllRecycler = view.findViewById(R.id.recyclerview_orders_all);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        CollectionReference collectionReference = firebaseFirestore.collection("ShopKeeper").document(firebaseAuth.getUid()).collection("MyOrders");

        Query query = collectionReference.whereEqualTo("shopId", MainActivity.selectedShop);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    for (DocumentSnapshot document : value.getDocuments()) {
                        order_data.put(document.getId(), document.getData());
                    }
                    setAdapter(view);
                }
            }
        });
//        setAdapter(view);

        return view;


    }

    private void setAdapter(View view) {
        OrdersFragment.arrayList = new ArrayList<>();
        ordersAllRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        ordersAllRecycler.setHasFixedSize(false);

        ordersAllAdapter = new OrdersAllAdapter(view.getContext(), OrdersFragment.arrayList);

        ordersAllRecycler.setAdapter(ordersAllAdapter);

        for (Map.Entry mapElement : order_data.entrySet()) {
            String key = (String) mapElement.getKey();
            HashMap<String, Object> value = (HashMap<String, Object>) mapElement.getValue();

            OrdersAllModel ordersAllModel = new OrdersAllModel();
            ordersAllModel.setOrderId((String) value.get("orderNumberId"));
            ordersAllModel.setOrderTime((String) value.get("dateTime"));
            ordersAllModel.setOrderStatus((String) value.get("orderStatus"));
            ordersAllModel.setUserDetails((String) value.get("userName"));
            ordersAllModel.setOrderPrice("" + value.get("orderAmount"));
            ordersAllModel.setOrderDocumentId(key);
            OrdersFragment.arrayList.add(ordersAllModel);

        }
        OrderUnderPackagingFragment.ordersUnderPackagingAdapter.orders_list = OrdersFragment.arrayList;
        OrderDeliveredFragment.ordersDeliveredAdapter.orders_list = OrdersFragment.arrayList;
        OrderPickedFragment.ordersPickedAdapter.orders_list = OrdersFragment.arrayList;
        OrderReadyFragment.ordersReadyAdapter.orders_list = OrdersFragment.arrayList;


        ordersAllAdapter.notifyDataSetChanged();
        OrderUnderPackagingFragment.ordersUnderPackagingAdapter.notifyDataSetChanged();
        OrderDeliveredFragment.ordersDeliveredAdapter.notifyDataSetChanged();
        OrderPickedFragment.ordersPickedAdapter.notifyDataSetChanged();
        OrderReadyFragment.ordersReadyAdapter.notifyDataSetChanged();
    }
}