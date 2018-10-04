package kz.djunglestones.organizerappqel.Activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import kz.djunglestones.organizerappqel.Models.Orders;
import kz.djunglestones.organizerappqel.R;

public class OrderTicketInfoActivity extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ticket_info);
        Orders orders = (Orders) getIntent().getParcelableExtra("OrderObject");
        Toast.makeText(OrderTicketInfoActivity.this, ""+orders.getOrderNumber(), Toast.LENGTH_SHORT).show();
        TextView textView = findViewById(R.id.textviewOrderInfo);
        textView.setText(orders.getUsername()+"\n"+orders.getEmail()+"\n"+orders.getOrderNumber()+"\n"+orders.getDate()+"\n"+String.valueOf(orders.getPrice()));
    }
}
