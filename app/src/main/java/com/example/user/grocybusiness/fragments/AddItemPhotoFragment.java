package com.example.user.grocybusiness.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.adapters.ItemViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddItemPhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddItemPhotoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ViewPager viewPager;
    TabLayout tabLayout;
    ItemCustomImageFragment itemCustomImageFragment;
    ItemDefaultImageFragment itemDefaultImageFragment;
    static Bundle bundle2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddItemPhotoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddItemPhoto.
     */
    // TODO: Rename and change types and number of parameters
    public static AddItemPhotoFragment newInstance(String param1, String param2) {
        AddItemPhotoFragment fragment = new AddItemPhotoFragment();
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
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_add_item_photo, container, false);

        viewPager= view.findViewById(R.id.view_pager);
        tabLayout= view.findViewById(R.id.tab_layout);

        tabLayout.setupWithViewPager(viewPager);

        itemCustomImageFragment=new ItemCustomImageFragment();
        itemDefaultImageFragment=new ItemDefaultImageFragment();

        ItemViewPagerAdapter itemViewPagerAdapter=new ItemViewPagerAdapter(getChildFragmentManager(),0);
        itemViewPagerAdapter.addFragment(itemDefaultImageFragment,"Default images");
        itemViewPagerAdapter.addFragment(itemCustomImageFragment,"Custom images");
        viewPager.setAdapter(itemViewPagerAdapter);


        return view;
    }
}