package com.miapp.club.threshold;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Feed_Adapter extends RecyclerView.Adapter<Feed_Adapter.FeedViewHolder> {

    private final List<Messages> messages;
    private final Context context;
    private final DatabaseReference mFeedsDatabaseReference;

    public Feed_Adapter(List<Messages> messages, Context context) {
        this.messages = messages;
        this.context = context;
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFeedsDatabaseReference= mFirebaseDatabase.getReference().child("/feed");
        mFeedsDatabaseReference.keepSynced(true);
        updatelist();
    }

    private void updatelist() {
        ChildEventListener mChildEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                messages.add(0,dataSnapshot.getValue(Messages.class));
                notifyItemInserted(0);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                messages.add(0,dataSnapshot.getValue(Messages.class));
                notifyItemInserted(0);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                messages.add(0,dataSnapshot.getValue(Messages.class));
                notifyItemInserted(0);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                messages.add(0,dataSnapshot.getValue(Messages.class));
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
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item,
                parent, false);
        return new FeedViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder feedViewHolder, final int position) {

        feedViewHolder.name.setText(messages.get(position).name);
        feedViewHolder.message.setText(messages.get(position).message);
        feedViewHolder.date_time.setText(messages.get(position).date);
    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final Context context;
        final TextView message;
        final TextView name;
        final TextView date_time;

        public FeedViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(this);
            name = (TextView) itemView.findViewById(R.id.sender);
            message = (TextView) itemView.findViewById(R.id.message);
            date_time = (TextView) itemView.findViewById(R.id.time);
        }


        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Sent at "+messages.get(getAdapterPosition()).time, Toast.LENGTH_SHORT).show();
        }
    }
}
