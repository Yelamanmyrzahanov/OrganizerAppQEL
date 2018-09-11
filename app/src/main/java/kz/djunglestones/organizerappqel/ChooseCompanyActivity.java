package kz.djunglestones.organizerappqel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ChooseCompanyActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<Company> companyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_company);

        initUI();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        
        addCompanies();

        ChooseCompanyRecyclerAdapter chooseCompanyRecyclerAdapter = new ChooseCompanyRecyclerAdapter(ChooseCompanyActivity.this,companyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChooseCompanyActivity.this));
        recyclerView.setAdapter(chooseCompanyRecyclerAdapter);


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



}
