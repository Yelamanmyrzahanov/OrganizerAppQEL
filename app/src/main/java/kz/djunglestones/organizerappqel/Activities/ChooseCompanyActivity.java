package kz.djunglestones.organizerappqel.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import kz.djunglestones.organizerappqel.Adapter.ChooseCompanyRecyclerAdapter;
import kz.djunglestones.organizerappqel.Models.Company;
import kz.djunglestones.organizerappqel.R;

public class ChooseCompanyActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<Company> companyList;

    private ChooseCompanyRecyclerAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_company);

        initUI();

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        addCompanies();

        listAdapter = new ChooseCompanyRecyclerAdapter(ChooseCompanyActivity.this,
                companyList,
                new ChooseCompanyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(Company company, int position) {
                        Intent data = new Intent();
                        data.putExtra("company_name", company.companyName);
                        setResult(RESULT_OK, data);
                    }
                }
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(ChooseCompanyActivity.this));
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        int selectedItemPosition = listAdapter.getSelectedItemPosition();
        outState.putInt("selectedItemPosition", selectedItemPosition);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int selectedItemPosition = savedInstanceState.getInt("selectedItemPosition", -1);
        if (listAdapter != null) {
            listAdapter.setSelectedItemPosition(selectedItemPosition);
            listAdapter.notifyDataSetChanged();
        }
    }

    private void addCompanies() {
        companyList = new ArrayList<>();
        companyList.add(new Company("MSLM Company"));
        companyList.add(new Company("Google"));
        companyList.add(new Company("Yandex"));
    }

    private void initUI() {
        toolbar = findViewById(R.id.choose_company_toolbar);
        recyclerView = findViewById(R.id.company_list_recyclerview);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
