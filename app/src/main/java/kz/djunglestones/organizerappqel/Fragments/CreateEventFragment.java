package kz.djunglestones.organizerappqel.Fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import kz.djunglestones.organizerappqel.Activities.EditCompanyActivity;
import kz.djunglestones.organizerappqel.Activities.ChooseCompanyActivity;
import kz.djunglestones.organizerappqel.MyInterface;
import kz.djunglestones.organizerappqel.R;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment implements MyInterface {

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
    private boolean repeated_start_time_picker_btn_clicked,repeated_end_time_picker_btn_clicked,start_date_btn_picker_repeated_clicked,end_date_button_picker_repeated_clicked;
    View v;
    private RelativeLayout relativeLayout;
    private ImageView more_options_calendar, more_options_location, more_options_tickets;
    private EditText location_city;
    private CardView cardview_event_calendar_repeated,cardview_event_calendar;
    private ImageView more_options_calendar_repeated;
    private Button how_often_btn;
    private TextView choose_week_day_tv;
    private Button choose_week_day_btn;
    private Button repeated_start_time_picker_btn,repeated_end_time_picker_btn;
    private Button start_date_btn_picker_repeated,end_date_button_picker_repeated;
    private List<String> checkedDayList;
    private TextView occurs_every_tv,day_of_the_month_tv,repeating_every_tv,choose_occur_week;
    private Button occurs_every_button,repeating_every_day_pick_btn,repeating_every_week_day_pick_btn;

    public CreateEventFragment() {
        // Required empty public constructor
    }
    private void initUI() {
        start_date_btn_picker = v.findViewById(R.id.start_date_btn_picker);
        start_date_time_btn_picker = v.findViewById(R.id.start_date_time_btn_picker);
        end_date_button_picker = v.findViewById(R.id.end_date_button_picker);
        end_date_time_button_picker = v.findViewById(R.id.end_date_time_button_picker);
        company_name_edittext = v.findViewById(R.id.company_name_edittext);
        location_city = v.findViewById(R.id.location_city);
        location_address = v.findViewById(R.id.location_address);
        location_name = v.findViewById(R.id.location_name);
        imageView2 = v.findViewById(R.id.imageView2);
        image_create_event_frag = v.findViewById(R.id.image_create_event_frag);
        relativeLayout = v.findViewById(R.id.image_create_event_frag_rel_layout);
        more_options_tickets = v.findViewById(R.id.more_options_tickets);
        more_options_calendar = v.findViewById(R.id.more_options_calendar);
        more_options_location = v.findViewById(R.id.more_options_location);
        event_theme_tv = v.findViewById(R.id.choose_theme_tv);
        imageView3 = v.findViewById(R.id.imageView3);
        event_description_tv = v.findViewById(R.id.event_description_tv);
        constraint_event_privacy = v.findViewById(R.id.constraint_event_privacy);
        constraint_event_tickets = v.findViewById(R.id.constraint_event_tickets);
        constraint_event_location = v.findViewById(R.id.constraint_event_location);
        location_spinner_tv = v.findViewById(R.id.location_spinner_tv);
        event_privacy_choose_spinner_tv = v.findViewById(R.id.event_privacy_choose_spinner_tv);
        imageView5 = v.findViewById(R.id.imageView5);
        constraint_event_theme = v.findViewById(R.id.constraint_event_theme);
        start_date_tv = v.findViewById(R.id.start_date_tv);
        cardview_event_calendar = v.findViewById(R.id.cardview_event_calendar);
        cardview_event_calendar_repeated = v.findViewById(R.id.cardview_event_calendar_repeated);
        more_options_calendar_repeated = v.findViewById(R.id.more_options_calendar_repeated);
        how_often_btn = v.findViewById(R.id.how_often_btn);
        choose_week_day_tv = v.findViewById(R.id.choose_week_day_tv);
        choose_week_day_btn = v.findViewById(R.id.choose_week_day_btn);
        repeated_start_time_picker_btn = v.findViewById(R.id.repeated_start_time_picker_btn);
        repeated_end_time_picker_btn = v.findViewById(R.id.repeated_end_time_picker_btn);
        start_date_btn_picker_repeated = v.findViewById(R.id.start_date_btn_picker_repeated);
        end_date_button_picker_repeated = v.findViewById(R.id.end_date_button_picker_repeated);
        occurs_every_tv = v.findViewById(R.id.occurs_every_tv);
        day_of_the_month_tv = v.findViewById(R.id.day_of_the_month_tv);
        repeating_every_tv = v.findViewById(R.id.repeating_every_tv);
        choose_occur_week = v.findViewById(R.id.choose_occur_week);
        occurs_every_button = v.findViewById(R.id.occurs_every_button);
        repeating_every_day_pick_btn = v.findViewById(R.id.repeating_every_day_pick_btn);
        repeating_every_week_day_pick_btn = v.findViewById(R.id.repeating_every_week_day_pick_btn);

    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_create_event, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);
        initUI();
        start_date_btn_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_date_btn_clicked = true;
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getActivity().getSupportFragmentManager(), "date picker");
            }
        });

        end_date_button_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end_date_btn_clicked = true;
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getActivity().getSupportFragmentManager(), "date picker");
            }
        });

        start_date_time_btn_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_date_time_btn_clicked = true;
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
            }
        });

        end_date_time_button_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end_date_time_btn_clicked = true;
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
            }
        });

        repeated_start_time_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeated_start_time_picker_btn_clicked = true;
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
            }
        });
        repeated_end_time_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeated_end_time_picker_btn_clicked = true;
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
            }
        });

        start_date_btn_picker_repeated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_date_btn_picker_repeated_clicked = true;
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getActivity().getSupportFragmentManager(), "date picker");
            }
        });

        end_date_button_picker_repeated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end_date_button_picker_repeated_clicked = true;
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getActivity().getSupportFragmentManager(), "date picker");
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
                Intent intent = new Intent(getActivity(), ChooseCompanyActivity.class);
                intent.putExtra("company_name", company_name_edittext.getText().toString());
                startActivityForResult(intent, COMPANY_REQUEST_CODE);
            }
        });

        image_create_event_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    } else {
                        CropImage.activity()
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setAspectRatio(16, 9)
                                .start(requireContext(), CreateEventFragment.this);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                                    Toast.makeText(getContext(), "Онлайн-мероприятие", Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                final String[] cities = getContext().getResources().getStringArray(R.array.themeOfEventArr);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "Должен выходить поп-ап", Toast.LENGTH_SHORT).show();
            }
        });


        TextView add_event_tickets_tv = v.findViewById(R.id.add_event_tickets_tv);
        add_event_tickets_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "New activity should be opened", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imageView4 = v.findViewById(R.id.imageView4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Arrow down clicked", Toast.LENGTH_SHORT).show();
            }
        });

        event_description_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "New activity should be opened", Toast.LENGTH_SHORT).show();
            }
        });



        more_options_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                checkedDayList = new ArrayList<>();
                final String[] dayOfWeekArr = getContext().getResources().getStringArray(R.array.dayOfWeekArr);
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.dayOfWeek)
                        .setMultiChoiceItems(R.array.dayOfWeekArr, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    checkedDayList.add(dayOfWeekArr[which]);
                                } else if (checkedDayList.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    checkedDayList.remove(Integer.valueOf(which));
                                }
                            }
                        })
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Days", "onClick: "+checkedDayList);
                        int counter = 0;
                        StringBuilder daysSelected = new StringBuilder();
                        if (checkedDayList.size()>2){
                            for (int i=0;i<checkedDayList.size();i++){
                                counter+=1;
                            }
                            choose_week_day_btn.setText(String.valueOf(counter)+" из "+" 7 дней");
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
                final String[] dayOfMonth = getContext().getResources().getStringArray(R.array.occursEveryMonthDayArr);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.occursEveryMonthDay)
                        .setItems(R.array.occursEveryMonthDayArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                occurs_every_button.setText(dayOfMonth[which]+"-e");
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        repeating_every_day_pick_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] dayOfMonth = getContext().getResources().getStringArray(R.array.repeatingEveryDayArr);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                final String[] dayOfWeek = getContext().getResources().getStringArray(R.array.dayOfWeekArr);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
//                final String[] cities = getContext().getResources().getStringArray(R.array.cityOfEventArr);
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
                final String[] cities = getContext().getResources().getStringArray(R.array.cityOfEventArr);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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

        return v;
    }



    @Override
    public void setDate(int year, int month, int dayOfMonth) {
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
    public void setTime(int hour, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hour);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.create_event_fragment_menu, menu);
        MenuItem editCompany = menu.findItem(R.id.editCompany);
        editCompany.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(getContext(),EditCompanyActivity.class);
                startActivity(intent);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }
}
