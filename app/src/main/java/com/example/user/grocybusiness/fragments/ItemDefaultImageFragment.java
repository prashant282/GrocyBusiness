package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.adapters.ItemDefaultImageAdapter;
import com.example.user.grocybusiness.models.ItemDefaultImageModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemDefaultImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDefaultImageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static String category;
    RecyclerView item_default_image_recycler;
    ItemDefaultImageAdapter itemDefaultImageAdapter;
    ArrayList<ItemDefaultImageModel> arrayList;
    HashMap<String, Object> item_default_images = new HashMap();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    View view;

    public ItemDefaultImageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemDefaultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemDefaultImageFragment newInstance(String param1, String param2) {
        ItemDefaultImageFragment fragment = new ItemDefaultImageFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_default_image, container, false);
        item_default_image_recycler = view.findViewById(R.id.item_default_image_recycler);

        DocumentReference documentReference = firebaseFirestore.collection("DefaultImages").document("DefaultItemImages");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                item_default_images = (HashMap<String, Object>) documentSnapshot.getData();
                setAdapter();
            }
        });

        return view;
    }

    private void setAdapter() {
        arrayList = new ArrayList();
        item_default_image_recycler.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        item_default_image_recycler.setHasFixedSize(false);
        item_default_image_recycler.setViewCacheExtension(null);

        itemDefaultImageAdapter = new ItemDefaultImageAdapter(view.getContext(), arrayList);

        item_default_image_recycler.setAdapter(itemDefaultImageAdapter);

        ArrayList<String> itemImg = (ArrayList<String>) item_default_images.get(AddItemFragment.shop.get("itemCategory"));

        for (int i = 0; i < itemImg.size(); i++) {
            ItemDefaultImageModel itemDefaultImageModel = new ItemDefaultImageModel();
            itemDefaultImageModel.setItemImage(itemImg.get(i));
            itemDefaultImageModel.setSelected(false);
            arrayList.add(itemDefaultImageModel);
        }

        itemDefaultImageAdapter.notifyDataSetChanged();

    }
}