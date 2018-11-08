package kz.djunglestones.organizerappqel.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kz.djunglestones.organizerappqel.Adapter.ChoosePromocodeRecyclerAdapter;
import kz.djunglestones.organizerappqel.Models.TicketsForChoosePromocode;
import kz.djunglestones.organizerappqel.R;

public class ChoosePromocodeTicketActivity extends AppCompatActivity {
    private List<TicketsForChoosePromocode> ticketsForChoosePromocodeList;
    private ChoosePromocodeRecyclerAdapter choosePromocodeRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_promocode_ticket);
        Toolbar toolbar = findViewById(R.id.choose_promocode_toolbar);
        CheckBox checkBox = findViewById(R.id.apply_to_all_checkbox);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Выбрать билет");

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        addTickets();
        RecyclerView recyclerView = findViewById(R.id.recycler_choose_promocode);

        choosePromocodeRecyclerAdapter = new ChoosePromocodeRecyclerAdapter(ChoosePromocodeTicketActivity.this, ticketsForChoosePromocodeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChoosePromocodeTicketActivity.this));
        recyclerView.setAdapter(choosePromocodeRecyclerAdapter);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                for (TicketsForChoosePromocode ticket : ticketsForChoosePromocodeList) {
                    ticket.setChecked(checkBox.isChecked());
                }
                choosePromocodeRecyclerAdapter.notifyDataSetChanged();

            }
        });

    }

    private void addTickets() {
        ticketsForChoosePromocodeList = new ArrayList<>();
        ticketsForChoosePromocodeList.add(new TicketsForChoosePromocode("Стандарт", true));
        ticketsForChoosePromocodeList.add(new TicketsForChoosePromocode("Luxury", false));
        ticketsForChoosePromocodeList.add(new TicketsForChoosePromocode("Normal", false));
        ticketsForChoosePromocodeList.add(new TicketsForChoosePromocode("Luxury2", false));
        ticketsForChoosePromocodeList.add(new TicketsForChoosePromocode("Normal2", true));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.choose_promocode_activity_menu, menu);
        MenuItem done = menu.findItem(R.id.done);
        done.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(ChoosePromocodeTicketActivity.this,String.valueOf(choosePromocodeRecyclerAdapter.getResultTicketsForChoosePromocodeList().size()),Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                String ticketNames = "";
                for (TicketsForChoosePromocode tickets:choosePromocodeRecyclerAdapter.getResultTicketsForChoosePromocodeList()){
                    ticketNames +=tickets.getTicketName();
                    ticketNames +=", ";
                }
                intent.putExtra("ticket_names",ticketNames);
                setResult(Activity.RESULT_OK,intent);
                finish();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
