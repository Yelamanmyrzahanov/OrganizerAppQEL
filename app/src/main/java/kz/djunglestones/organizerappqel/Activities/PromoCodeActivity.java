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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kz.djunglestones.organizerappqel.Adapter.PromoCodeRecyclerAdapter;
import kz.djunglestones.organizerappqel.Models.PromoCode;
import kz.djunglestones.organizerappqel.R;

public class PromoCodeActivity extends AppCompatActivity {
    private final int PROMOCODE_REQUEST_CODE = 1059;
    private List<PromoCode> promoCodeList = new ArrayList<>();
    private RelativeLayout relative_layout_promocode;
    private RecyclerView promocode_recyclerview;
    boolean isVisibleRecycler = false;
    private PromoCodeRecyclerAdapter promoCodeRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_code);


        Toolbar toolbar = findViewById(R.id.promo_code_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Check in");


        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        relative_layout_promocode = findViewById(R.id.relative_layout_promocode);
        promocode_recyclerview = findViewById(R.id.promocode_recyclerview);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.promo_code_activity_menu,menu);
        MenuItem addPromocode = menu.findItem(R.id.add_promo_code);
        addPromocode.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(PromoCodeActivity.this,AddPromoCodeActivity.class);
                startActivityForResult(intent,1059);
                return true;
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

    private void hideRelLayout(){
        relative_layout_promocode.setVisibility(View.GONE);
        promocode_recyclerview.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1059 && resultCode == Activity.RESULT_OK){
            Log.d("promocodeList", "onActivityResult: "+data.getSerializableExtra("promocodeList"));
//            Toast.makeText(PromoCodeActivity.this,"Data is received",Toast.LENGTH_SHORT).show();
            isVisibleRecycler = true;
            List<PromoCode> promoCodeList1 = (List<PromoCode>) data.getSerializableExtra("promocodeList");
            promoCodeList.addAll(promoCodeList1);
            hideRelLayout();
            promoCodeRecyclerAdapter = new PromoCodeRecyclerAdapter(promoCodeList,PromoCodeActivity.this);
            promocode_recyclerview.setLayoutManager(new LinearLayoutManager(PromoCodeActivity.this));
            promocode_recyclerview.setAdapter(promoCodeRecyclerAdapter);
        }
    }
}
