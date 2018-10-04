package kz.djunglestones.organizerappqel.Activities;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import kz.djunglestones.organizerappqel.Adapter.CreateTicketsActivityAdapter;
import kz.djunglestones.organizerappqel.Models.CreateTickets;
import kz.djunglestones.organizerappqel.R;

public class CreateTicketsActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private List<CreateTickets> createTicketsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tickets);

        setStatusBarSettings();

        initUI();
        setupToolbar();

        addTickets();

        RecyclerView recyclerView = findViewById(R.id.create_tickets_recyclerview);
        CreateTicketsActivityAdapter adapter = new CreateTicketsActivityAdapter(CreateTicketsActivity.this,createTicketsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(CreateTicketsActivity.this));
        recyclerView.setAdapter(adapter);



    }

    private void addTickets() {
        createTicketsList = new ArrayList<>();
        createTicketsList.add(new CreateTickets("Студенческий",2,10,0));
        createTicketsList.add(new CreateTickets("VIP",13,75,5000));
    }

    private void setStatusBarSettings() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Билеты");
    }

    private void initUI() {
        toolbar = findViewById(R.id.create_tickets_toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_tickets_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
