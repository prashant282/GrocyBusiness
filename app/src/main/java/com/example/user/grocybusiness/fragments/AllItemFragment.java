package com.example.user.grocybusiness.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.adapters.AllItemAdapter;
import com.example.user.grocybusiness.models.ItemModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllItemFragment extends Fragment {

    AllItemAdapter allItemAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllItemFragment newInstance(String param1, String param2) {
        AllItemFragment fragment = new AllItemFragment();
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
    public void onStart() {
        super.onStart();
        allItemAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        allItemAdapter.stopListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        ArrayList<ItemModel> arrayList=new ArrayList();

        View view=inflater.inflate(R.layout.fragment_all_item, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.all_item_recycler);

        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();



        DocumentReference documentReference = firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);
        Query query = documentReference.collection("Items");
        FirestoreRecyclerOptions<ItemModel> options= new FirestoreRecyclerOptions.Builder<ItemModel>()
                .setQuery(query, ItemModel.class).build();
        allItemAdapter=new AllItemAdapter(options);
        allItemAdapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(allItemAdapter);

//        ItemModel itemModel=new ItemModel("Haldiram Aloo Bhujia", "400gm","80", R.drawable.bhujia);
//        arrayList.add(itemModel);
//        itemModel=new ItemModel("Toor Dal", "1000gm","210", R.drawable.toor_dal);
//        arrayList.add(itemModel);
//        itemModel=new ItemModel("India Gate Basmati Rice", "5kg","600", R.drawable.rice);
//        arrayList.add(itemModel);
//        itemModel=new ItemModel("Tata Salt", "1kg","50", R.drawable.salt);
//        arrayList.add(itemModel);
//        itemModel=new ItemModel("Madhur Sugar", "1kg","60", R.drawable.sugar);
//        arrayList.add(itemModel);





        return view;
    }
}