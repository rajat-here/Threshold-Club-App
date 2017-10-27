package com.miapp.club.threshold;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

public class Coordinator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Team Members");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.coordinatorsr);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);


        List<Cord_details> details = new ArrayList<>();
        details.add(new Cord_details("Divyanshu Khandewal","Overall Coordinator","divyanshu.ch15@iitp.ac.in","+91-9468822096",R.drawable.diyanshu));
        details.add(new Cord_details("Aman","Media and Public Relations Coordinator","aman.ch15@iitp.ac.in","+91-8581903693",R.drawable.aman));
        details.add(new Cord_details("Akhil Jain","Events Coordinator","akhil.ch15@iitp.ac.in","+91-7295819201",R.drawable.akhil));
        details.add(new Cord_details("Vijay Yadav","Industrial Relations Coordinator","vijay.ch15@iitp.ac.in","+91-9455061806",R.drawable.vijay));
        details.add(new Cord_details("Parth Patel","Project Manger","patel.cb16@iitp.ac.in","+91-8140989334",R.drawable.parth));
        details.add(new Cord_details("Devansh Jaiswal","Project Manager","devansh.cb16@iitp.ac.in","+91-8765113312",R.drawable.devansh));
        details.add(new Cord_details("Rajat Gupta","Creative & Design","rajat.cb16@iitp.ac.in","+91-9955529931",R.drawable.rajat));
        details.add(new Cord_details("Vikas Singh","Creative & Design","vikas.cb16@iitp.ac.in","+91-8004046034",R.drawable.guru));
        details.add(new Cord_details("Yash Choudhary","Media & Public Relations","yash.cb16@iitp.ac.in","+91-7509116834",R.drawable.yash));
        details.add(new Cord_details("Sunil Kumar Yadav","Media & Public Relations","sunil.cb16@iitp.ac.in","+91-9473543503",R.drawable.sunil));
        details.add(new Cord_details("Nishant Kulshrestha","Events","nishant.cb16@iitp.ac.in","+91-9958724205",R.drawable.nishant));
        details.add(new Cord_details("Ashish Kumar Singh","Events","ashish.cb16@iitp.ac.in","+91-7634880080",R.drawable.ashish));
        details.add(new Cord_details("Aswin Krishna","Industrial Relations","aswin.cb16@iitp.ac.in","+91-8129450246",R.drawable.aswin));
        details.add(new Cord_details("Ayush Inani","Industrial Relations","ayush.cb16@iitp.ac.in","+91-9462728970",R.drawable.ayush));
        details.add(new Cord_details("Dheeraj Kalani","Magazine","dheeraj.cb16@iitp.ac.in","+91-8239393924",R.drawable.dheeraj));

        Cord_adapter madapter = new Cord_adapter(details, this);
        recyclerView.setAdapter(madapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
