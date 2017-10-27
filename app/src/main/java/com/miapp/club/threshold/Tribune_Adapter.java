package com.miapp.club.threshold;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Tribune_Adapter extends RecyclerView.Adapter<Tribune_Adapter.ViewHolder> {

    private final List<Magazines> magazines;
    private final Context context;
    private final DatabaseReference mFeedsDatabaseReference;

    Tribune_Adapter(List<Magazines> magazines, Context context) {
        this.context = context;
        this.magazines = magazines;
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFeedsDatabaseReference= mFirebaseDatabase.getReference().child("/magazines");
        mFeedsDatabaseReference.keepSynced(true);
        updatelist();
    }
    private void updatelist() {
        ChildEventListener mChildEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                magazines.add(dataSnapshot.getValue(Magazines.class));
                notifyItemInserted(0);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                magazines.add(dataSnapshot.getValue(Magazines.class));
                notifyItemInserted(0);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                magazines.add(dataSnapshot.getValue(Magazines.class));
                notifyItemInserted(0);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                magazines.add(dataSnapshot.getValue(Magazines.class));
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
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_tribune_list,
                viewGroup, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.mag_name.setText(magazines.get(position).name_magazine);
    }

    @Override
    public int getItemCount() {
        return magazines == null ? 0 : magazines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView mag_name;
        final CardView cv;
        final Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            cv = (CardView) itemView.findViewById(R.id.cardlist_item3);
            mag_name = (TextView) itemView.findViewById(R.id.magazine);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(this.context,PDF_View.class);
            intent.putExtra("mag_pos",getAdapterPosition());
            intent.putExtra("mag_id",magazines.get(getAdapterPosition()).magazine_id);
            intent.putExtra("mag_name",magazines.get(getAdapterPosition()).name_magazine);
            context.startActivity(intent);
            notifyDataSetChanged();
        }


    }
}

