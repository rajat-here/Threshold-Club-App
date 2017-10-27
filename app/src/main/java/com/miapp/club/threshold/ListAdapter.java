package com.miapp.club.threshold;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.*;
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private final List<Events> events;
    private final Context context;
    private final DatabaseReference mFeedsDatabaseReference;

    ListAdapter(List<Events> events, Context context){
        this.events = events;
        this.context=context;
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFeedsDatabaseReference= mFirebaseDatabase.getReference().child("/events");
        mFeedsDatabaseReference.keepSynced(true);
        updatelist();
    }


    private void updatelist() {
        ChildEventListener mChildEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                events.add(0,dataSnapshot.getValue(Events.class));
                notifyItemInserted(0);
        }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                events.add(0,dataSnapshot.getValue(Events.class));
                notifyItemInserted(0);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                events.add(0,dataSnapshot.getValue(Events.class));
                notifyItemInserted(0);
            }
            @Override

            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                events.add(0,dataSnapshot.getValue(Events.class));
                notifyItemInserted(0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Can't Load","Club messages couldn't load");
            }

        };
        mFeedsDatabaseReference.addChildEventListener(mChildEventListener);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,
                viewGroup, false);
        return new MyViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        myViewHolder.personName.setText(events.get(i).event_name);
        Glide.with(context)
                .load(events.get(i).photo_id)
                .into(myViewHolder.personPhoto);
    }

    @Override
    public int getItemCount() {
        return events == null ? 0 : events.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView personPhoto;
        public final TextView personName;
        final CardView cv;
        final Context context;

        public MyViewHolder(View itemView,Context context) {
            super(itemView);
            this.context=context;
            itemView.setOnClickListener(this);
            cv = (CardView)itemView.findViewById(R.id.cardlist_item);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(this.context,diff_events.class);
            intent.putExtra("eve_pos",getAdapterPosition());
            intent.putExtra("eve_id",events.get(getAdapterPosition()).event_id);
            intent.putExtra("eve_name",events.get(getAdapterPosition()).event_name);
            context.startActivity(intent);
            notifyDataSetChanged();
        }
    }

}

