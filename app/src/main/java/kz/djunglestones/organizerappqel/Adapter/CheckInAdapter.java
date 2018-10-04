package kz.djunglestones.organizerappqel.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import kz.djunglestones.organizerappqel.Models.CheckIn;
import kz.djunglestones.organizerappqel.R;

public class CheckInAdapter extends RecyclerView.Adapter<CheckInAdapter.MyViewHolder>{
    Context context;
    private List<CheckIn> checkInList;

    public CheckInAdapter(Context context, List<CheckIn> checkInList) {
        this.context = context;
        this.checkInList = checkInList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_check_in, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.username_check_in.setText(checkInList.get(position).getUsername());
        holder.ticket_type_check_in.setText(checkInList.get(position).getTicketType());
//        checkInList.get(position).setCheckedIn();
        if (checkInList.get(position).isCheckedIn()==1){
            holder.check_in_color.setBackgroundColor(Color.parseColor("#63be87"));
        }else{
            holder.check_in_color.setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public int getItemCount() {
        return checkInList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView username_check_in,ticket_type_check_in;
        View check_in_color;
        public MyViewHolder(View itemView) {
            super(itemView);
            username_check_in = itemView.findViewById(R.id.username_check_in);
            ticket_type_check_in = itemView.findViewById(R.id.ticket_type_check_in);
            check_in_color = itemView.findViewById(R.id.check_in_color);
        }
    }

    public void setChecInViewLineColor(int pos,boolean isCheckedIn){
        if (isCheckedIn){
            checkInList.get(pos).setCheckedIn(1);
        }
        else{
            checkInList.get(pos).setCheckedIn(2);
        }

    }


}
