package kz.djunglestones.organizerappqel.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import kz.djunglestones.organizerappqel.Activities.FreeTicketActivity;
import kz.djunglestones.organizerappqel.Activities.PaidTicketActivity;
import kz.djunglestones.organizerappqel.Models.CreateTickets;
import kz.djunglestones.organizerappqel.R;

public class CreateTicketsActivityAdapter extends RecyclerView.Adapter<CreateTicketsActivityAdapter.MyViewHolder>{
    Context context;
    List<CreateTickets> createTicketsList;

    public CreateTicketsActivityAdapter(Context context, List<CreateTickets> createTicketsList) {
        this.context = context;
        this.createTicketsList = createTicketsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_view_create_tickets,parent,false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.sold_out_ticket_amount_tv.setText(String.valueOf(createTicketsList.get(position).getSoldOutAmount())+" / "+String.valueOf(createTicketsList.get(position).getTotalTicketAmount())+"   Продано");
        holder.ticket_type_tv.setText(createTicketsList.get(position).getTicketType());
        if (createTicketsList.get(position).getPrice()>0){
            holder.ticket_price_tv.setText(String.valueOf(createTicketsList.get(position).getPrice())+" ₸");
        }else {
            holder.ticket_price_tv.setText("Бесплатно");
        }
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (createTicketsList.get(position).getPrice()==0){
                    Intent intent = new Intent(context, FreeTicketActivity.class);
                    intent.putExtra("ticket_type",createTicketsList.get(position).getTicketType()+" билет");
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context, PaidTicketActivity.class);
                    intent.putExtra("ticket_type",createTicketsList.get(position).getTicketType()+" билет");
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return createTicketsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ticket_type_tv,sold_out_ticket_amount_tv,ticket_price_tv;
        ConstraintLayout constraintLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            ticket_price_tv = itemView.findViewById(R.id.ticket_price_tv);
            ticket_type_tv = itemView.findViewById(R.id.ticket_type_tv);
            sold_out_ticket_amount_tv = itemView.findViewById(R.id.sold_out_ticket_amount_tv);
            constraintLayout = itemView.findViewById(R.id.create_tickets_main_constraint);
        }
    }

}
