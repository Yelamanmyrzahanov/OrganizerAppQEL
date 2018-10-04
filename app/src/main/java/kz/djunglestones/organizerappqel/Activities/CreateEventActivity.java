package kz.djunglestones.organizerappqel.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import kz.djunglestones.organizerappqel.Fragments.DatePickerFragment;
import kz.djunglestones.organizerappqel.Fragments.TimePickerFragment;
import kz.djunglestones.organizerappqel.R;

public class CreateEventActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {
    private Toolbar toolbar;
    private static final int COMPANY_REQUEST_CODE = 111;

    private Button start_date_btn_picker, start_date_time_btn_picker, end_date_button_picker, end_date_time_button_picker;
    private boolean start_date_btn_clicked, end_date_btn_clicked, start_date_time_btn_clicked, end_date_time_btn_clicked;
    private TextView company_name_edittext, event_theme_tv, event_description_tv, start_date_tv;
    private TextView location_name, location_address;
    private TextView event_privacy_choose_spinner_tv, add_event_tickets_tv, choose_theme_tv, location_spinner_tv;
    private ConstraintLayout constraint_event_privacy, constraint_event_tickets, constraint_event_theme, constraint_event_location;
    private ImageView imageView2, imageView3, imageView5;
    private ImageView image_create_event_frag;
    private Uri mainImageURI = null;
    private RelativeLayout relativeLayout;
    private boolean repeated_start_time_picker_btn_clicked,repeated_end_time_picker_btn_clicked,start_date_btn_picker_repeated_clicked,end_date_button_picker_repeated_clicked;
    private ImageView more_options_calendar, more_options_location, more_options_tickets;
    private EditText location_city;
    private CardView cardview_event_calendar_repeated,cardview_event_calendar;
    private ImageView more_options_calendar_repeated;
    private Button how_often_btn;
    private TextView choose_week_day_tv;
    private Button choose_week_day_btn;
    private Button repeated_start_time_picker_btn,repeated_end_time_picker_btn;
    private Button start_date_btn_picker_repeated,end_date_button_picker_repeated;
    private List<String> checkedDayList = new ArrayList<>();
    private List<String> checkedMonthDayList = new ArrayList<>();
    private TextView occurs_every_tv,day_of_the_month_tv,repeating_every_tv,choose_occur_week;
    private Button occurs_every_button,repeating_every_day_pick_btn,repeating_every_week_day_pick_btn;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private boolean[] weekItemsChecked = {false,false,false,false,false,false,false};
    private boolean[] monthDaysItemsChecked = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        initUI();


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_toggle, R.string.close_toggle);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.createEventNavView);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
//            navigationView.setCheckedItem(R.id.myEvents);
        }

        start_date_btn_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_date_btn_clicked = true;
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        end_date_button_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end_date_btn_clicked = true;
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        start_date_time_btn_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_date_time_btn_clicked = true;
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        end_date_time_button_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end_date_time_btn_clicked = true;
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        repeated_start_time_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeated_start_time_picker_btn_clicked = true;
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        repeated_end_time_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeated_end_time_picker_btn_clicked = true;
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        start_date_btn_picker_repeated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_date_btn_picker_repeated_clicked = true;
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        end_date_button_picker_repeated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end_date_button_picker_repeated_clicked = true;
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        choose_occur_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choose_occur_week.getText().toString().equals("Выбрать по дню недели")){
                    repeating_every_tv.setVisibility(View.VISIBLE);
                    repeating_every_week_day_pick_btn.setVisibility(View.VISIBLE);
                    repeating_every_day_pick_btn.setVisibility(View.VISIBLE);
                    occurs_every_tv.setVisibility(View.GONE);
                    day_of_the_month_tv.setVisibility(View.GONE);
                    occurs_every_button.setVisibility(View.GONE);
                    choose_occur_week.setText("Выбрать по дню месяца");
                }else{
                    occurs_every_tv.setVisibility(View.VISIBLE);
                    day_of_the_month_tv.setVisibility(View.VISIBLE);
                    occurs_every_button.setVisibility(View.VISIBLE);
                    repeating_every_tv.setVisibility(View.GONE);
                    repeating_every_week_day_pick_btn.setVisibility(View.GONE);
                    repeating_every_day_pick_btn.setVisibility(View.GONE);
                    choose_occur_week.setText("Выбрать по дню недели");
                }
            }
        });

        company_name_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateEventActivity.this, ChooseCompanyActivity.class);
                intent.putExtra("company_name", company_name_edittext.getText().toString());
                startActivityForResult(intent, COMPANY_REQUEST_CODE);
            }
        });

        image_create_event_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    if (ContextCompat.checkSelfPermission(CreateEventActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        Toast.makeText(CreateEventActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(CreateEventActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    } else {
                        CropImage.activity()
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setAspectRatio(16, 9)
                                .start(CreateEventActivity.this);

                    }
                }
            }
        });

        List<String> spinnerListLocaion = new ArrayList<>();
        spinnerListLocaion.add("Место");
        spinnerListLocaion.add("Онлайн-мероприятие");

        constraint_event_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setTitle(R.string.locationOfEvent)
                        .setItems(R.array.locationOfEventArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                if (which == 0) {
                                    location_name.setVisibility(View.VISIBLE);
                                    location_address.setVisibility(View.VISIBLE);
                                    location_city.setVisibility(View.VISIBLE);
                                    imageView2.setVisibility(View.VISIBLE);
                                    constraint_event_location.setVisibility(View.VISIBLE);
                                    location_spinner_tv.setText("Место");
                                } else {
                                    location_name.setVisibility(View.GONE);
                                    location_address.setVisibility(View.GONE);
                                    location_city.setVisibility(View.GONE);
                                    imageView2.setVisibility(View.GONE);
                                    constraint_event_location.setVisibility(View.VISIBLE);
                                    location_spinner_tv.setText("Онлайн-мероприятие");
                                    Toast.makeText(CreateEventActivity.this, "Онлайн-мероприятие", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        constraint_event_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setTitle(R.string.privacyOfEvent)
                        .setItems(R.array.privacyOfEventArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                if (which == 0) {
                                    event_privacy_choose_spinner_tv.setText("Публичное");
                                } else {
                                    event_privacy_choose_spinner_tv.setText("Частное");
                                }

                            }
                        });
                builder.create();
                builder.show();
            }
        });

        event_theme_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] cities = getResources().getStringArray(R.array.themeOfEventArr);
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setTitle(R.string.themeOfEvent)
                        .setItems(R.array.themeOfEventArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                event_theme_tv.setText(cities[which]);
                                event_theme_tv.setTextColor(Color.parseColor("#212121"));
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        TextView add_event_tickets_tv = findViewById(R.id.add_event_tickets_tv);
        add_event_tickets_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateEventActivity.this,CreateTicketsActivity.class);
                startActivity(intent);
            }
        });
        ImageView imageView4 = findViewById(R.id.imageView4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateEventActivity.this,CreateTicketsActivity.class);
                startActivity(intent);
            }
        });

        event_description_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateEventActivity.this, "New activity should be opened", Toast.LENGTH_SHORT).show();
            }
        });

        more_options_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setTitle(R.string.typeOfEventCalendar)
                        .setItems(R.array.typeOfEventCalendarArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                if (which == 1) {
                                    cardview_event_calendar_repeated.setVisibility(View.VISIBLE);
                                    cardview_event_calendar.setVisibility(View.GONE);
                                } else {
                                    cardview_event_calendar_repeated.setVisibility(View.GONE);
                                    cardview_event_calendar.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        more_options_calendar_repeated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setTitle(R.string.typeOfEventCalendar)
                        .setItems(R.array.typeOfEventCalendarArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                if (which == 1) {
                                    cardview_event_calendar_repeated.setVisibility(View.VISIBLE);
                                    cardview_event_calendar.setVisibility(View.GONE);
                                } else {
                                    cardview_event_calendar_repeated.setVisibility(View.GONE);
                                    cardview_event_calendar.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        how_often_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setTitle(R.string.howOftenEvent)
                        .setItems(R.array.howOftenEventArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                if (which == 0) {
                                    how_often_btn.setText("Ежедневно");
                                    choose_week_day_tv.setVisibility(View.GONE);
                                    choose_week_day_btn.setVisibility(View.GONE);
                                    occurs_every_tv.setVisibility(View.GONE);
                                    occurs_every_button.setVisibility(View.GONE);
                                    day_of_the_month_tv.setVisibility(View.GONE);
                                    repeating_every_tv.setVisibility(View.GONE);
                                    repeating_every_day_pick_btn.setVisibility(View.GONE);
                                    repeating_every_week_day_pick_btn.setVisibility(View.GONE);
                                    choose_occur_week.setVisibility(View.GONE);
                                    occurs_every_tv.setVisibility(View.GONE);
                                } else if (which==1){
                                    how_often_btn.setText("Еженедельно");
                                    choose_week_day_tv.setVisibility(View.VISIBLE);
                                    choose_week_day_btn.setVisibility(View.VISIBLE);
                                    occurs_every_button.setVisibility(View.GONE);
                                    day_of_the_month_tv.setVisibility(View.GONE);
                                    repeating_every_tv.setVisibility(View.GONE);
                                    repeating_every_day_pick_btn.setVisibility(View.GONE);
                                    repeating_every_week_day_pick_btn.setVisibility(View.GONE);
                                    choose_occur_week.setVisibility(View.GONE);
                                    occurs_every_tv.setVisibility(View.GONE);
                                }else if (which == 2){
                                    how_often_btn.setText("Ежемесячно");
                                    choose_week_day_tv.setVisibility(View.GONE);
                                    choose_week_day_btn.setVisibility(View.GONE);
                                    occurs_every_tv.setVisibility(View.VISIBLE);
                                    occurs_every_button.setVisibility(View.VISIBLE);
                                    day_of_the_month_tv.setVisibility(View.VISIBLE);
                                    repeating_every_tv.setVisibility(View.GONE);
                                    repeating_every_day_pick_btn.setVisibility(View.GONE);
                                    repeating_every_week_day_pick_btn.setVisibility(View.GONE);
                                    choose_occur_week.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                builder.create();
                builder.show();
            }
        });


        choose_week_day_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] dayOfWeekArr = CreateEventActivity.this.getResources().getStringArray(R.array.dayOfWeekArr);
                final AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setTitle(R.string.dayOfWeek)
                        .setMultiChoiceItems(R.array.dayOfWeekArr, weekItemsChecked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    checkedDayList.add(dayOfWeekArr[which]);
                                    weekItemsChecked[which] = true;
                                } else if (checkedDayList.contains(dayOfWeekArr[which])) {
                                    // Else, if the item is already in the array, remove it
                                    checkedDayList.remove(dayOfWeekArr[which]);
                                    weekItemsChecked[which] = false;
                                }
                            }
                        })
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int counter = 0;
                                StringBuilder daysSelected = new StringBuilder();
                                if (checkedDayList.size()>2){
                                    for (int i=0;i<checkedDayList.size();i++){
                                        counter+=1;
                                    }
                                    choose_week_day_btn.setText(String.valueOf(counter)+" из "+"7 дней");
                                }else{
                                    for (int i=0;i<checkedDayList.size();i++){
                                        if (i>=1){
                                            daysSelected.append(", ");
                                        }
                                        daysSelected.append(checkedDayList.get(i));

                                    }
                                    choose_week_day_btn.setText(daysSelected);
                                }
                                dialog.dismiss();
                            }
                        }).setNegativeButton(R.string.close_toggle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });



        occurs_every_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] dayOfMonth = CreateEventActivity.this.getResources().getStringArray(R.array.occursEveryMonthDayArr);
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setTitle(R.string.occursEveryMonthDay)
                        .setMultiChoiceItems(R.array.occursEveryMonthDayArr, monthDaysItemsChecked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked){
                            checkedMonthDayList.add(dayOfMonth[which]);
                            monthDaysItemsChecked[which] = true;
                        }else if (checkedDayList.contains(dayOfMonth[which])){
                            checkedMonthDayList.remove(dayOfMonth[which]);
                            monthDaysItemsChecked[which] = false;
                        }
                    }
                }).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int counter = 0;
                        StringBuilder daysSelected = new StringBuilder();
                        for (int i=0;i<checkedMonthDayList.size();i++){
                            if (i>=1){
                                daysSelected.append(", ");
                            }
                            Collections.sort(checkedMonthDayList);
                            daysSelected.append(checkedMonthDayList.get(i));

                        }
                        occurs_every_button.setText(daysSelected);
                        dialog.dismiss();
                    }
                }).setNegativeButton(R.string.close_toggle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
//                        .setItems(R.array.occursEveryMonthDayArr, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                occurs_every_button.setText(dayOfMonth[which]);
//                            }
//                        });
                builder.create();
                builder.show();
            }
        });

        repeating_every_day_pick_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] dayOfMonth = CreateEventActivity.this.getResources().getStringArray(R.array.repeatingEveryDayArr);
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setTitle(R.string.repeatingEveryDay)
                        .setItems(R.array.repeatingEveryDayArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                repeating_every_day_pick_btn.setText(dayOfMonth[which]);
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        repeating_every_week_day_pick_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] dayOfWeek = CreateEventActivity.this.getResources().getStringArray(R.array.dayOfWeekArr);
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setTitle(R.string.dayOfWeek)
                        .setItems(R.array.dayOfWeekArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                repeating_every_week_day_pick_btn.setText(dayOfWeek[which]);
                            }
                        });
                builder.create();
                builder.show();
            }
        });



        more_options_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setTitle(R.string.placeOfEvent)
                        .setItems(R.array.placeOfEventArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        more_options_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setTitle(R.string.ticketsOfEvent)
                        .setItems(R.array.ticketsOfEventArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                            }
                        });
                builder.create();
                builder.show();
            }
        });

//        location_city.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                final String[] cities = CreateEventActivity.this.getResources().getStringArray(R.array.cityOfEventArr);
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle(R.string.cityOfEvent)
//                        .setItems(R.array.cityOfEventArr, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // The 'which' argument contains the index position
//                                // of the selected item
//                                location_city.setText(cities[which]);
//                            }
//                        });
//                builder.create();
//                builder.show();
//                return true;
//            }
//        });

        location_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] cities = CreateEventActivity.this.getResources().getStringArray(R.array.cityOfEventArr);
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setTitle(R.string.cityOfEvent)
                        .setItems(R.array.cityOfEventArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                location_city.setText(cities[which]);
                            }
                        });
                builder.create();
                builder.show();
            }
        });

    }


    private void initUI() {
        drawerLayout = findViewById(R.id.activity_create_event_drawer_layout);
        toolbar = findViewById(R.id.toolbar_create_event);
        start_date_btn_picker = findViewById(R.id.start_date_btn_picker);
        start_date_time_btn_picker = findViewById(R.id.start_date_time_btn_picker);
        end_date_button_picker = findViewById(R.id.end_date_button_picker);
        end_date_time_button_picker = findViewById(R.id.end_date_time_button_picker);
        company_name_edittext = findViewById(R.id.company_name_edittext);
        location_city = findViewById(R.id.location_city);
        location_address = findViewById(R.id.location_address);
        location_name = findViewById(R.id.location_name);
        imageView2 = findViewById(R.id.imageView2);
        image_create_event_frag = findViewById(R.id.image_create_event_frag);
        relativeLayout = findViewById(R.id.image_create_event_frag_rel_layout);
        more_options_tickets = findViewById(R.id.more_options_tickets);
        more_options_calendar = findViewById(R.id.more_options_calendar);
        more_options_location = findViewById(R.id.more_options_location);
        event_theme_tv = findViewById(R.id.choose_theme_tv);
        imageView3 = findViewById(R.id.imageView3);
        event_description_tv = findViewById(R.id.event_description_tv);
        constraint_event_privacy = findViewById(R.id.constraint_event_privacy);
        constraint_event_tickets = findViewById(R.id.constraint_event_tickets);
        constraint_event_location = findViewById(R.id.constraint_event_location);
        location_spinner_tv = findViewById(R.id.location_spinner_tv);
        event_privacy_choose_spinner_tv = findViewById(R.id.event_privacy_choose_spinner_tv);
        imageView5 = findViewById(R.id.imageView5);
        constraint_event_theme = findViewById(R.id.constraint_event_theme);
        start_date_tv = findViewById(R.id.start_date_tv);
        cardview_event_calendar = findViewById(R.id.cardview_event_calendar);
        cardview_event_calendar_repeated = findViewById(R.id.cardview_event_calendar_repeated);
        more_options_calendar_repeated = findViewById(R.id.more_options_calendar_repeated);
        how_often_btn = findViewById(R.id.how_often_btn);
        choose_week_day_tv = findViewById(R.id.choose_week_day_tv);
        choose_week_day_btn = findViewById(R.id.choose_week_day_btn);
        repeated_start_time_picker_btn = findViewById(R.id.repeated_start_time_picker_btn);
        repeated_end_time_picker_btn = findViewById(R.id.repeated_end_time_picker_btn);
        start_date_btn_picker_repeated = findViewById(R.id.start_date_btn_picker_repeated);
        end_date_button_picker_repeated = findViewById(R.id.end_date_button_picker_repeated);
        occurs_every_tv = findViewById(R.id.occurs_every_tv);
        day_of_the_month_tv = findViewById(R.id.day_of_the_month_tv);
        repeating_every_tv = findViewById(R.id.repeating_every_tv);
        choose_occur_week = findViewById(R.id.choose_occur_week);
        occurs_every_button = findViewById(R.id.occurs_every_button);
        repeating_every_day_pick_btn = findViewById(R.id.repeating_every_day_pick_btn);
        repeating_every_week_day_pick_btn = findViewById(R.id.repeating_every_week_day_pick_btn);
    }

//    @Override
//    public void setDate(int year, int month, int dayOfMonth) {
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.YEAR, year);
//        c.set(Calendar.MONTH, month);
//        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//        String currentMonthString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
//        String month1 = c.getDisplayName(c.MONTH, c.LONG, Locale.getDefault());
//        int day1 = c.get(c.DAY_OF_MONTH);
//        String user_date = day1 + " " + month1;
////        String[] choosen_date = currentMonthString.split(",");
////        String[] cur_date = choosen_date[1].split(" ");
//
//
//        if (start_date_btn_clicked) {
//            start_date_btn_picker.setText(user_date);
//            start_date_btn_clicked = false;
//        }
//        if (end_date_btn_clicked) {
//            end_date_button_picker.setText(user_date);
//            end_date_btn_clicked = false;
//        }
//        if (start_date_btn_picker_repeated_clicked){
//            start_date_btn_picker_repeated.setText(user_date);
//            start_date_btn_picker_repeated_clicked =false;
//        }
//        if (end_date_button_picker_repeated_clicked){
//            end_date_button_picker_repeated.setText(user_date);
//            end_date_button_picker_repeated_clicked = false;
//        }
//    }
//
//    @Override
//    public void setTime(int hour, int minute) {
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.HOUR_OF_DAY,hour);
//        c.set(Calendar.MINUTE,minute);
//        int hourPicked = c.get(c.HOUR_OF_DAY);
//        int minutePicked = c.get(c.MINUTE);
//
//        if (start_date_time_btn_clicked) {
//            start_date_time_btn_picker.setText(hourPicked + " : "+checkDigit(minutePicked));
//            start_date_time_btn_clicked = false;
//        }
//        if (end_date_time_btn_clicked) {
//            end_date_time_button_picker.setText(hourPicked + " : "+checkDigit(minutePicked));
//            end_date_time_btn_clicked = false;
//        }
//        if (repeated_start_time_picker_btn_clicked){
//            repeated_start_time_picker_btn.setText(hourPicked + " : "+checkDigit(minutePicked));
//            repeated_start_time_picker_btn_clicked = false;
//        }
//        if (repeated_end_time_picker_btn_clicked){
//            repeated_end_time_picker_btn.setText(hourPicked + " : "+checkDigit(minutePicked));
//            repeated_end_time_picker_btn_clicked =false;
//        }
//    }

    private String checkDigit(int minutePicked) {
        return minutePicked<= 9 ? "0" + minutePicked : String.valueOf(minutePicked);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mainImageURI = result.getUri();
                image_create_event_frag.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                image_create_event_frag.setImageURI(mainImageURI);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        } else if (requestCode == COMPANY_REQUEST_CODE && resultCode == RESULT_OK) {
            String name = data.getStringExtra("company_name");
            company_name_edittext.setText(name);
        }
    }
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.create_event_fragment_menu, menu);
//        MenuItem editCompany = menu.findItem(R.id.editCompany);
//        editCompany.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Intent intent = new Intent(CreateEventActivity.this,EditCompanyActivity.class);
//                startActivity(intent);
//                return true;
//            }
//        });
//        super.onCreateOptionsMenu(menu, inflater);
//
//    }

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
//        String[] choosen_date = currentMonthString.split(",");
//        String[] cur_date = choosen_date[1].split(" ");


        if (start_date_btn_clicked) {
            start_date_btn_picker.setText(user_date);
            start_date_btn_clicked = false;
        }
        if (end_date_btn_clicked) {
            end_date_button_picker.setText(user_date);
            end_date_btn_clicked = false;
        }
        if (start_date_btn_picker_repeated_clicked){
            start_date_btn_picker_repeated.setText(user_date);
            start_date_btn_picker_repeated_clicked =false;
        }
        if (end_date_button_picker_repeated_clicked){
            end_date_button_picker_repeated.setText(user_date);
            end_date_button_picker_repeated_clicked = false;
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        int hourPicked = c.get(c.HOUR_OF_DAY);
        int minutePicked = c.get(c.MINUTE);

        if (start_date_time_btn_clicked) {
            start_date_time_btn_picker.setText(hourPicked + " : "+checkDigit(minutePicked));
            start_date_time_btn_clicked = false;
        }
        if (end_date_time_btn_clicked) {
            end_date_time_button_picker.setText(hourPicked + " : "+checkDigit(minutePicked));
            end_date_time_btn_clicked = false;
        }
        if (repeated_start_time_picker_btn_clicked){
            repeated_start_time_picker_btn.setText(hourPicked + " : "+checkDigit(minutePicked));
            repeated_start_time_picker_btn_clicked = false;
        }
        if (repeated_end_time_picker_btn_clicked){
            repeated_end_time_picker_btn.setText(hourPicked + " : "+checkDigit(minutePicked));
            repeated_end_time_picker_btn_clicked =false;
        }
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return false;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_event_fragment_menu, menu);
        MenuItem editCompany = menu.findItem(R.id.editCompany);
        editCompany.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(CreateEventActivity.this,EditCompanyActivity.class);
                startActivity(intent);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }if (item.getItemId() == android.R.id.home) {
            finish();
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
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                Intent mainIntent = new Intent(CreateEventActivity.this,MainActivity.class);
                mainIntent.putExtra("fragment_name","EditFragment");
                startActivity(mainIntent);
                drawerLayout.closeDrawers();
                break;
            case R.id.myEvents:
                Intent eventsIntent = new Intent(CreateEventActivity.this,MainActivity.class);
                eventsIntent.putExtra("fragment_name","MyEventsFragment");
                startActivity(eventsIntent);
                drawerLayout.closeDrawers();
                break;
            case R.id.statistics:
                Intent statsIntent = new Intent(CreateEventActivity.this,MainActivity.class);
                statsIntent.putExtra("fragment_name","StatisticsFragment");
                startActivity(statsIntent);
                drawerLayout.closeDrawers();
                break;
            case R.id.orders:
                Intent ordersIntent = new Intent(CreateEventActivity.this,MainActivity.class);
                ordersIntent.putExtra("fragment_name","OrdersFragment");
                startActivity(ordersIntent);
                drawerLayout.closeDrawers();
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
