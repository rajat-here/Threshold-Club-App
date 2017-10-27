package com.miapp.club.threshold;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Tribune extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_tribune, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.magazine_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);

        List<Magazines> magazines = new ArrayList<>();
        Tribune_Adapter madapter = new Tribune_Adapter(magazines, getContext());
        recyclerView.setAdapter(madapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        madapter.notifyDataSetChanged();
        return view;
    }
}
