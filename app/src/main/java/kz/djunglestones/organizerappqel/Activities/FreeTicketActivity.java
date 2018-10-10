package kz.djunglestones.organizerappqel.Activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import kz.djunglestones.organizerappqel.Fragments.DatePickerFragment;
import kz.djunglestones.organizerappqel.Fragments.TimePickerFragment;
import kz.djunglestones.organizerappqel.R;

public class FreeTicketActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener{
    private ConstraintLayout choose_sales_start_constraint;
    private Toolbar toolbar;
    private ConstraintLayout sales_end_constraint,by_date_and_time_constraint,visibility_constraint,check_box_constraint,sales_end_dates_constraint;
    private CheckBox hide_before_checkbox,hide_after_checkbox;
    private Button hide_before_date_picker_btn,hide_after_date_picker_btn;
    private Button hide_before_time_picker_btn,hide_after_time_picker_btn;
    private boolean hide_before_date_picker_btn_clicked,hide_after_date_picker_btn_clicked,hide_before_time_picker_btn_clicked,hide_after_time_picker_btn_clicked,by_date_and_time_constraint_clicked,by_time_constraint_clicked,sales_end_dates_constraint_clicked,sales_end_time_constraint_clicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_ticket);
        setStatusBarSettings();
        initUI();
        setupToolbar();

        choose_sales_start_constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] dayOfWeekArr = FreeTicketActivity.this.getResources().getStringArray(R.array.startOfSalesArr);
                final TextView choose_date_and_time_tv = findViewById(R.id.choose_sales_start_tv);
                final AlertDialog.Builder builder = new AlertDialog.Builder(FreeTicketActivity.this);
                builder.setTitle(R.string.startOfSales)
                        .setItems(R.array.startOfSalesArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item

                                if (which==2){
                                    by_date_and_time_constraint.setVisibility(View.GONE);
                                    final AlertDialog.Builder builderTicketEnd = new AlertDialog.Builder(FreeTicketActivity.this);
                                    builderTicketEnd.setTitle("Билеты")
                                            .setItems(R.array.dayOfWeekArr, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // The 'which' argument contains the index position
                                                    // of the selected item
                                                }
                                            });
                                    builderTicketEnd.create();
                                    builderTicketEnd.show();
                                }
                                else if (which==1){
                                    android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                                    datePicker.show(getSupportFragmentManager(), "date picker");
                                    by_date_and_time_constraint_clicked = true;
                                    by_date_and_time_constraint.setVisibility(View.VISIBLE);
                                }
                                else if (which==0){
                                    by_date_and_time_constraint.setVisibility(View.GONE);
                                }
                                choose_date_and_time_tv.setText(dayOfWeekArr[which]);
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        sales_end_constraint.setOnClickListener(v -> {
            final String[] salesEndArr = FreeTicketActivity.this.getResources().getStringArray(R.array.endOfSalesArr);
            final TextView choose_sales_end_tv = findViewById(R.id.choose_sales_end_tv);
            final AlertDialog.Builder builder = new AlertDialog.Builder(FreeTicketActivity.this);
            builder.setTitle(R.string.endOfSales)
                    .setItems(R.array.endOfSalesArr, (dialog, which) -> {
                        // The 'which' argument contains the index position
                        // of the selected item
                        if (which == 2){
                            android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                            datePicker.show(getSupportFragmentManager(), "date picker");
                            sales_end_dates_constraint_clicked = true;
                            sales_end_dates_constraint.setVisibility(View.VISIBLE);
                        }else{
                            sales_end_dates_constraint.setVisibility(View.GONE);
                        }
                        choose_sales_end_tv.setText(salesEndArr[which]);
                    });
            builder.create();
            builder.show();
        });

        visibility_constraint.setOnClickListener(v -> {
            final String[] visibilityChooseArr = FreeTicketActivity.this.getResources().getStringArray(R.array.visibilityChooseArr);
            final TextView choose_visibility_tv = findViewById(R.id.choose_visibility_tv);
            final AlertDialog.Builder builder = new AlertDialog.Builder(FreeTicketActivity.this);
            builder.setTitle(R.string.visibilityChoose)
                    .setItems(R.array.visibilityChooseArr, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // The 'which' argument contains the index position
                            // of the selected item
                            if (which==2){
                                NestedScrollView nestedScrollView = findViewById(R.id.nested_scroll_view);
                                nestedScrollView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        nestedScrollView.fullScroll(NestedScrollView.FOCUS_DOWN);
                                        nestedScrollView.scrollTo(nestedScrollView.getScrollX(),nestedScrollView.getBottom());
                                        nestedScrollView.smoothScrollBy(0,nestedScrollView.getBottom());
                                    }
                                });
                                check_box_constraint.setVisibility(View.VISIBLE);

                            }else{
                                check_box_constraint.setVisibility(View.GONE);
                            }
                            choose_visibility_tv.setText(visibilityChooseArr[which]);
                        }
                    });
            builder.create();
            builder.show();
        });

        hide_after_checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){

            }
        });

        hide_before_checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){

            }

        });

        hide_after_time_picker_btn.setOnClickListener(v -> {
            hide_after_time_picker_btn_clicked = true;
            android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
        });

        hide_before_time_picker_btn.setOnClickListener(v -> {
            hide_before_time_picker_btn_clicked = true;
            android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
        });

        hide_before_date_picker_btn.setOnClickListener(v -> {
            hide_before_date_picker_btn_clicked = true;
            android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        });


        hide_after_date_picker_btn.setOnClickListener(v -> {
            hide_after_date_picker_btn_clicked = true;
            android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        });






    }

    private void initUI() {
        toolbar = findViewById(R.id.free_tickets_toolbar);
        choose_sales_start_constraint = findViewById(R.id.choose_sales_start_constraint);
        sales_end_constraint = findViewById(R.id.sales_end_constraint);
        hide_after_checkbox = findViewById(R.id.hide_after_checkbox);
        hide_before_checkbox = findViewById(R.id.hide_before_checkbox);
        hide_before_date_picker_btn =findViewById(R.id.hide_before_date_picker_btn);
        hide_after_date_picker_btn = findViewById(R.id.hide_after_date_picker_btn);
        hide_before_time_picker_btn = findViewById(R.id.hide_before_time_picker_btn);
        hide_after_time_picker_btn = findViewById(R.id.hide_after_time_picker_btn);
        by_date_and_time_constraint = findViewById(R.id.by_date_and_time_constraint);
        visibility_constraint= findViewById(R.id.visibility_constraint);
        check_box_constraint = findViewById(R.id.check_box_constraint);
        sales_end_dates_constraint = findViewById(R.id.sales_end_dates_constraint);

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
        Intent intent = getIntent();
        if (intent.getStringExtra("ticket_type").isEmpty()){
            getSupportActionBar().setTitle(intent.getStringExtra("Билет"));
        }else{
            getSupportActionBar().setTitle(intent.getStringExtra("ticket_type"));

        }
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
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentMonthString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        String month1 = c.getDisplayName(c.MONTH, c.LONG, Locale.getDefault());
        int day1 = c.get(c.DAY_OF_MONTH);
        String user_date = day1 + " " + month1;

        if (hide_before_date_picker_btn_clicked){
            hide_before_date_picker_btn.setText(user_date);
            hide_before_date_picker_btn_clicked = false;
        }
        if (hide_after_date_picker_btn_clicked){
            hide_after_date_picker_btn.setText(user_date);
            hide_after_date_picker_btn_clicked = false;
        }
        if (by_date_and_time_constraint_clicked){
            TextView date_tv = findViewById(R.id.date_tv);
            date_tv.setText(user_date);
            android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
            by_date_and_time_constraint_clicked = false;
            by_time_constraint_clicked = true;
        }
        if (sales_end_dates_constraint_clicked){
            TextView sales_end_dates_tv = findViewById(R.id.sales_end_dates_tv);
            sales_end_dates_tv.setText(user_date);
            android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
            sales_end_dates_constraint_clicked = false;
            sales_end_time_constraint_clicked = true;
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        int hourPicked = c.get(c.HOUR_OF_DAY);
        int minutePicked = c.get(c.MINUTE);
        if (hide_after_time_picker_btn_clicked){
            hide_after_time_picker_btn.setText(String.valueOf(hourPicked )+ " : "+String.valueOf(checkDigit(minutePicked)));
            hide_after_time_picker_btn_clicked = false;
        }if (hide_before_time_picker_btn_clicked){
            hide_before_time_picker_btn.setText(String.valueOf(hourPicked )+ " : "+String.valueOf(checkDigit(minutePicked)));
            hide_before_time_picker_btn_clicked = false;
        }
        if (by_time_constraint_clicked){
            TextView time_tv = findViewById(R.id.time_tv);
            time_tv.setText(String.valueOf(hourPicked)+ " : "+String.valueOf(checkDigit(minutePicked)));
            by_time_constraint_clicked = false;
        }
        if (sales_end_time_constraint_clicked){
            TextView sales_end_dates_time_tv = findViewById(R.id.sales_end_dates_time_tv);
            sales_end_dates_time_tv.setText(String.valueOf(hourPicked)+ " : "+String.valueOf(checkDigit(minutePicked)));
            sales_end_time_constraint_clicked = false;
        }
    }


    private String checkDigit(int minutePicked) {
        return minutePicked<= 9 ? "0" + minutePicked : String.valueOf(minutePicked);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.free_ticket_activity_menu, menu);
        MenuItem save = menu.findItem(R.id.save);
        return super.onCreateOptionsMenu(menu);
    }
}
