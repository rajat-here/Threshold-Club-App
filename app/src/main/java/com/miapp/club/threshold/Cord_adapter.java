package com.miapp.club.threshold;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class Cord_adapter extends RecyclerView.Adapter<Cord_adapter.ViewHolder> {

    private final List<Cord_details> details;
    private final Context context;
    Cord_adapter( List<Cord_details> details, Context context) {
        this.context=context;
        this.details = details;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cord_list,
                viewGroup, false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position) {
        viewHolder.name.setText(details.get(position).cordname);
        viewHolder.pos.setText(details.get(position).position);
        viewHolder.numb.setText(details.get(position).number);
        viewHolder.email.setText(details.get(position).email);
        viewHolder.photo.setImageResource(details.get(position).photo);
    }

    @Override
    public int getItemCount() {
        return details == null ? 0 : details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final ImageView photo;
        final TextView name;
        final TextView pos;
        final TextView numb;
        final TextView email;
        final CardView cv;
        final Context context;

        public ViewHolder(View itemView,Context context) {
            super(itemView);
            this.context=context;
            cv = (CardView)itemView.findViewById(R.id.cardlist_item2);
            name=(TextView)itemView.findViewById(R.id.cord_name);
            pos=(TextView)itemView.findViewById(R.id.cord_position);
            numb=(TextView)itemView.findViewById(R.id.cord_number);
            email=(TextView)itemView.findViewById(R.id.cord_email);
            photo=(ImageView)itemView.findViewById(R.id.cord_photo);
            itemView.setOnClickListener(this);
            numb.setOnClickListener(this);
            email.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId()==numb.getId()){
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+details.get(getAdapterPosition()).number));
                if(intent.resolveActivity(context.getPackageManager())!=null){
                    context.startActivity(intent);}
            }
            if(v.getId()==email.getId()){
                Intent intent=new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto",details.get(getAdapterPosition()).email,null));
                if(intent.resolveActivity(context.getPackageManager())!=null){
                    context.startActivity(Intent.createChooser(intent,"Send via"));}
            }
        }


    }
}
