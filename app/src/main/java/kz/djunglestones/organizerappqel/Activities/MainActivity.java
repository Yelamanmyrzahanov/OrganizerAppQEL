package kz.djunglestones.organizerappqel.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import kz.djunglestones.organizerappqel.Fragments.CheckInFragment;
import kz.djunglestones.organizerappqel.Fragments.CreateEventFragment;
import kz.djunglestones.organizerappqel.Fragments.EditCompanyFragment;
import kz.djunglestones.organizerappqel.Fragments.MyEventsFragment;
import kz.djunglestones.organizerappqel.Fragments.OrdersFragment;
import kz.djunglestones.organizerappqel.Fragments.StatisticsFragment;
import kz.djunglestones.organizerappqel.MyInterface;
import kz.djunglestones.organizerappqel.R;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

//        DatePickerDialog.OnDateSetListener,
//        TimePickerDialog.OnTimeSetListener

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    private MyInterface myInterface;

    private Toolbar toolbar;
    private boolean isDrawerOpen = false;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();




        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_toggle, R.string.close_toggle){
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset > .55 && !isDrawerOpen){
                    onDrawerOpened(drawerView);
                    isDrawerOpen = true;
                } else if(slideOffset < .45 && isDrawerOpen) {
                    onDrawerClosed(drawerView);
                    isDrawerOpen = false;
                }
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.mainNavView);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            Object objectFrag = new MyEventsFragment();
            Object fragId = R.id.myEvents;
            if (!(getIntent().getExtras() == null)){
                String frag_name =  getIntent().getStringExtra("fragment_name");
                switch (frag_name){
//                    case "EditFragment":
//                        objectFrag = new CreateEventFragment();
//                        fragId = R.id.edit;
//                        break;
                    case "OrdersFragment":
                        objectFrag = new OrdersFragment();
                        fragId = R.id.orders;
                        break;
                    case "StatisticsFragment":
                        objectFrag = new StatisticsFragment();
                        fragId = R.id.statistics;
                        break;
                    case "MyEventsFragment":
                        objectFrag = new MyEventsFragment();
                        fragId = R.id.myEvents;
                        break;
                    default:
                        break;
                }
            }



            Object finalObjectFrag = objectFrag;

            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, (android.support.v4.app.Fragment) finalObjectFrag, "CreateEventFragment").commit();
            new Handler().postDelayed(() -> drawerLayout.closeDrawer(GravityCompat.START), 150);
            navigationView.setCheckedItem((Integer) fragId);
        }




    }

    private void initUI() {
        toolbar = findViewById(R.id.main_toolbar);
        drawerLayout = findViewById(R.id.activity_main_drawer_layout);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Object objectFrag = new MyEventsFragment();
        Object fragId = R.id.myEvents;
        switch (item.getItemId()) {

//            case R.id.edit:
//                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new CreateEventFragment()).commit();
//                break;
//            case R.id.edit:
//                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,new EditCompanyFragment()).commit();
//                break;
            case R.id.check_in:
                objectFrag = new CheckInFragment();
                fragId = R.id.check_in;
                break;
            case R.id.orders:
                objectFrag = new OrdersFragment();
                fragId = R.id.orders;
                break;
            case R.id.myEvents:
                objectFrag = new MyEventsFragment();
                fragId = R.id.myEvents;
                break;
            case R.id.statistics:
                objectFrag = new StatisticsFragment();
                fragId = R.id.statistics;
                break;
            default:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
        Object finalObjectFrag = objectFrag;
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, (android.support.v4.app.Fragment) finalObjectFrag, "CreateEventFragment").commit();
        new Handler().postDelayed(() -> {
            drawerLayout.closeDrawer(GravityCompat.START);

        }, 150);

        navigationView.setCheckedItem((Integer) fragId);



        return true;
    }

//    public void setListener(MyInterface listener) {
//        this.myInterface = listener;
//    }

//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        CreateEventFragment frag = (CreateEventFragment) getVisibleFragment();
//        setListener(frag);
//
//        myInterface.setDate(year, month, dayOfMonth);
//    }
//
//    @Override
//    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//        CreateEventFragment frag = (CreateEventFragment) getVisibleFragment();
//        setListener(frag);
//
//        myInterface.setTime(hourOfDay, minute);
//
//    }

//    public Fragment getVisibleFragment() {
//        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
//        List<Fragment> fragments = fragmentManager.getFragments();
//        if (fragments != null) {
//            for (Fragment fragment : fragments) {
//                if (fragment != null && fragment.isVisible())
//                    return fragment;
//            }
//        }
//        return null;
//    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
