package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.adapters.NotificationAdapter;
import com.example.user.grocybusiness.adapters.OutOfStockItemAdapter;
import com.example.user.grocybusiness.models.NotificationModel;
import com.example.user.grocybusiness.models.NotificationModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    NotificationAdapter notificationAdapter;
    RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        notificationAdapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        notificationAdapter.startListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView=view.findViewById(R.id.notification_recycler);


        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();



        DocumentReference documentReference = firebaseFirestore.collection("ShopKeeper").document(firebaseAuth.getUid());
//        documentReference.collection("Items").whereEqualTo("inStock",false);
        Query query = documentReference.collection("Notifications").whereEqualTo("freshNotification", true);
        FirestoreRecyclerOptions<NotificationModel> options= new FirestoreRecyclerOptions.Builder<NotificationModel>()
                .setQuery(query, NotificationModel.class).build();
        notificationAdapter=new NotificationAdapter(options);
        notificationAdapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(notificationAdapter);


        return view;
    }
}