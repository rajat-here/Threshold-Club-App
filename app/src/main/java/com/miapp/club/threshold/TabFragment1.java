package com.miapp.club.threshold;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.*;


public class TabFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(
                R.id.fragment_list_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        List<Events> event = new ArrayList<>();
        ListAdapter mAdapter = new ListAdapter(event, getContext());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        return view;
    }

}



