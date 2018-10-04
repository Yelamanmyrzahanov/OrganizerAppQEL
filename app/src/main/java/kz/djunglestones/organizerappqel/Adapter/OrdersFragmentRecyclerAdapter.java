package kz.djunglestones.organizerappqel.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kz.djunglestones.organizerappqel.Activities.OrderTicketInfoActivity;
import kz.djunglestones.organizerappqel.Models.Orders;
import kz.djunglestones.organizerappqel.R;

public class OrdersFragmentRecyclerAdapter extends RecyclerView.Adapter<OrdersFragmentRecyclerAdapter.MyViewHolder>{
    Context context;
    List<Orders> ordersList;

    public OrdersFragmentRecyclerAdapter(Context context, List<Orders> ordersList) {
        this.context = context;
        this.ordersList = ordersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_order_fragment_tickets, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.username.setText(ordersList.get(position).getUsername());
        holder.email.setText(ordersList.get(position).getEmail());
        holder.order_date.setText(ordersList.get(position).getDate());
        holder.order_num.setText("Заказ №"+ordersList.get(position).getOrderNumber());
        holder.order_price.setText(String.valueOf(ordersList.get(position).getPrice())+" ₸");
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderTicketInfoActivity.class);
                intent.putExtra("OrderObject", (Parcelable) ordersList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username,email,order_num,order_date,order_price;
        ConstraintLayout constraintLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username_order_fragment);
            email = itemView.findViewById(R.id.email_order_fragment);
            order_num = itemView.findViewById(R.id.order_num_order_fragment);
            order_date = itemView.findViewById(R.id.order_date_order_fragment);
            order_price = itemView.findViewById(R.id.order_price);
            constraintLayout = itemView.findViewById(R.id.main_layout_order_fragments);
        }
    }
}
