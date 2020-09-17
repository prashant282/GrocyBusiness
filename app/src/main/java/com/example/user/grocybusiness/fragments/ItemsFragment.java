package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.adapters.AllItemAdapter;
import com.example.user.grocybusiness.adapters.ItemSearchAdapter;
import com.example.user.grocybusiness.adapters.ItemViewPagerAdapter;
import com.example.user.grocybusiness.models.ItemModel;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionButton addItem;

    private AllItemFragment allItemFragment;
    private OutOfStockItemFragment outOfStockItemFragment;
    private AddItemFragment addItemFragment;
    public static BottomSheetDialog bottomSheetDialog;
    RecyclerView item_search_recycler;
    ItemSearchAdapter itemSearchAdapter;
    ArrayList<ItemModel> search_list;
    private ImageView search_image;

    public ItemsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemsFragment newInstance(String param1, String param2) {
        ItemsFragment fragment = new ItemsFragment();
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
        View view;
        view = inflater.inflate(R.layout.fragment_items, container, false);
        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);
        addItem = view.findViewById(R.id.fab);
        search_image = view.findViewById(R.id.search_image);

        allItemFragment = new AllItemFragment();
        outOfStockItemFragment = new OutOfStockItemFragment();
        addItemFragment = new AddItemFragment();

        tabLayout.setupWithViewPager(viewPager);

        ItemViewPagerAdapter itemViewPagerAdapter = new ItemViewPagerAdapter(getChildFragmentManager(), 0);
        itemViewPagerAdapter.addFragment(allItemFragment, "All Items");
        itemViewPagerAdapter.addFragment(outOfStockItemFragment, "Out Of Stock");
        viewPager.setAdapter(itemViewPagerAdapter);
        viewPager.setOffscreenPageLimit(5);



        BadgeDrawable badgeDrawable1=tabLayout.getTabAt(0).getOrCreateBadge();
        badgeDrawable1.setVisible(true);
        badgeDrawable1.setNumber(21);

        BadgeDrawable badgeDrawable=tabLayout.getTabAt(1).getOrCreateBadge();
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(11);


        addItem.setOnClickListener(view1 -> {


            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_frame_layout, addItemFragment);
            AddItemFragment.shop = new HashMap();
            fragmentTransaction.commit();
        });

        search_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_list = (ArrayList<ItemModel>) AllItemAdapter.items_list.clone();
                bottomSheetDialog = new BottomSheetDialog(view.getContext());
                bottomSheetDialog.setContentView(R.layout.bottomsheet_item_search);
                EditText etSearch = bottomSheetDialog.findViewById(R.id.search_edit_text);
                item_search_recycler = bottomSheetDialog.findViewById(R.id.item_search_recycler);

                item_search_recycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
                item_search_recycler.setHasFixedSize(false);

                itemSearchAdapter = new ItemSearchAdapter(view.getContext(), search_list);

                item_search_recycler.setAdapter(itemSearchAdapter);
//                CoordinatorLayout coordinatorLayout= bottomSheetDialog.findViewById(R.id.bottomsheet_frame_layout);
//                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(coordinatorLayout);
//                bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
//                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetDialog.show();

                etSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        filter(s.toString());
                    }
                });

            }
        });

        return view;
    }

    private void filter(String search_text) {
        ArrayList<ItemModel> filteredList = new ArrayList();

        for (ItemModel item : search_list) {
            if (item.getItemsProductName().toLowerCase().contains(search_text)) {
                filteredList.add(item);
            }
        }

        itemSearchAdapter
                .filteredList(filteredList);
    }


}