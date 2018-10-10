package kz.djunglestones.organizerappqel.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kz.djunglestones.organizerappqel.Adapter.CreateTicketsActivityAdapter;
import kz.djunglestones.organizerappqel.Models.CreateTickets;
import kz.djunglestones.organizerappqel.R;
import kz.djunglestones.organizerappqel.ThrottleTrackingBus;


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
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//        int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
//        int findFirstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
//        int findLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
//        int findLastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
//        Log.d("FirsVisPos", "firstVisiblePosition "+ firstVisiblePosition);
//        Log.d("FirsVisPos", "findFirstCompletelyVisibleItemPosition  "+ findFirstCompletelyVisibleItemPosition );
//        Log.d("FirsVisPos", "findLastVisibleItemPosition "+ findLastVisibleItemPosition);
//        Log.d("FirsVisPos", "findLastCompletelyVisibleItemPosition "+ findLastCompletelyVisibleItemPosition);
        recyclerView.setAdapter(adapter);
//        ThrottleTrackingBus throttleTrackingBus = new ThrottleTrackingBus(null,null);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                ThrottleTrackingBus.VisibleState visibleState = new ThrottleTrackingBus.VisibleState(layoutManager.findFirstCompletelyVisibleItemPosition(),layoutManager.findLastCompletelyVisibleItemPosition());

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab_create_tickets);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] chooseTicketTypeArr = CreateTicketsActivity.this.getResources().getStringArray(R.array.chooseTicketTypeArr);
                final AlertDialog.Builder builder = new AlertDialog.Builder(CreateTicketsActivity.this);
                builder.setTitle(R.string.chooseTicketType)
                        .setItems(R.array.chooseTicketTypeArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                if (which==0){
                                    Intent intent = new Intent(CreateTicketsActivity.this,FreeTicketActivity.class);
                                    intent.putExtra("ticket_type",chooseTicketTypeArr[0]+" билет");
                                    startActivity(intent);
                                }else if (which==1){
                                    Intent intent = new Intent(CreateTicketsActivity.this,FreeTicketActivity.class);
                                    intent.putExtra("ticket_type",chooseTicketTypeArr[1]+" билет");
                                    startActivity(intent);
                                }else if(which ==2){
                                    Intent intent = new Intent(CreateTicketsActivity.this,FreeTicketActivity.class);
                                    intent.putExtra("ticket_type",chooseTicketTypeArr[2]);
                                    startActivity(intent);
                                }
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        


    }

    private void addTickets() {
        createTicketsList = new ArrayList<>();
        createTicketsList.add(new CreateTickets("Студенческий",2,10,0));
        createTicketsList.add(new CreateTickets("VIP",13,75,5000));
        createTicketsList.add(new CreateTickets("Студенческий",2,10,0));
        createTicketsList.add(new CreateTickets("VIP",13,75,5000));
        createTicketsList.add(new CreateTickets("Студенческий",2,10,0));
        createTicketsList.add(new CreateTickets("VIP",13,75,5000));
        createTicketsList.add(new CreateTickets("Студенческий",2,10,0));
        createTicketsList.add(new CreateTickets("VIP",13,75,5000));
        createTicketsList.add(new CreateTickets("Студенческий",2,10,0));
        createTicketsList.add(new CreateTickets("VIP",13,75,5000));
        createTicketsList.add(new CreateTickets("Студенческий",2,10,0));
        createTicketsList.add(new CreateTickets("VIP",13,75,5000));
        createTicketsList.add(new CreateTickets("Студенческий",2,10,0));
        createTicketsList.add(new CreateTickets("VIP",13,75,5000));
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
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Билеты");
    }

    private void initUI() {
        toolbar = findViewById(R.id.create_tickets_toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_tickets_menu, menu);
        MenuItem save = menu.findItem(R.id.check);
        save.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                finish();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
