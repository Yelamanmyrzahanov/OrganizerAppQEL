package kz.djunglestones.organizerappqel;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment implements MyInterface {
    private Button start_date_btn_picker,start_date_time_btn_picker,end_date_button_picker,end_date_time_button_picker;
    View v;
    private boolean start_date_btn_clicked,end_date_btn_clicked,start_date_time_btn_clicked,end_date_time_btn_clicked;
    private TextView company_name_edittext,event_theme_tv,event_description_tv;
    private EditText location_name,location_address,location_city;
    private ImageView imageView2,imageView3;
    private ImageView  image_create_event_frag;
    private Uri mainImageURI = null;
    private RelativeLayout relativeLayout;
    private ImageView more_options_calendar,more_options_location,more_options_tickets;

    public CreateEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_create_event, container, false);
        initUI();
        start_date_btn_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_date_btn_clicked = true;
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getActivity().getSupportFragmentManager(),"date picker");
            }
        });

        end_date_button_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end_date_btn_clicked = true;
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getActivity().getSupportFragmentManager(),"date picker");
            }
        });

        start_date_time_btn_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_date_time_btn_clicked = true;
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(),"time picker");
            }
        });

        end_date_time_button_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end_date_time_btn_clicked = true;
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(),"time picker");
            }
        });

        company_name_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ChooseCompanyActivity.class);
                intent.putExtra("company_name",company_name_edittext.getText().toString());
                startActivityForResult(intent,111);
            }
        });

        image_create_event_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

                        Toast.makeText(getContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                    }
                    else{
                        CropImage.activity()
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setAspectRatio(1,1)
                                .start(getActivity());

                    }
                }
            }
        });


        List<String> spinnerListLocaion = new ArrayList<>();
        spinnerListLocaion.add("Место");
        spinnerListLocaion.add("Онлайн-мероприятие");
//        Spinner location_spinner = v.findViewById(R.id.location_spinner_tv);
//        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, spinnerListLocaion);
//        customSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        location_spinner.setAdapter(customSpinnerAdapter);
//        location_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String text = parent.getItemAtPosition(position).toString();
////                Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
//                if (text.equals("Онлайн-мероприятие")){
//                    location_name.setVisibility(View.GONE);
//                    location_address.setVisibility(View.GONE);
//                    location_city.setVisibility(View.GONE);
//                    imageView2.setVisibility(View.INVISIBLE);
//                }else{
//                    location_name.setVisibility(View.VISIBLE);
//                    location_address.setVisibility(View.VISIBLE);
//                    location_city.setVisibility(View.VISIBLE);
//                    imageView2.setVisibility(View.INVISIBLE);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        event_theme_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Должен выходить поп-ап",Toast.LENGTH_SHORT).show();
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Должен выходить поп-ап",Toast.LENGTH_SHORT).show();
            }
        });




//        Spinner theme_spinner = v.findViewById(R.id.theme_spinner);
//        theme_spinner.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Toast.makeText(getContext(),"Spinner Selected",Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

//        List<String> spinnerListThemes = new ArrayList<>();
//        spinnerListThemes.add("Выберите Тему");
//        spinnerListThemes.add("Тема 1");
//        spinnerListThemes.add("Тема 2");
//        spinnerListThemes.add("Тема 3");
//        spinnerListThemes.add("Тема 4");
//        CustomSpinnerAdapter customSpinnerAdapterTheme = new CustomSpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, spinnerListThemes);
//        customSpinnerAdapterTheme.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        theme_spinner.setAdapter(customSpinnerAdapterTheme);
//        theme_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String text = parent.getItemAtPosition(position).toString();
////                Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });




        TextView add_event_tickets_tv = v.findViewById(R.id.add_event_tickets_tv);
        add_event_tickets_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"New activity should be opened",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imageView4 = v.findViewById(R.id.imageView4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Arrow down clicked",Toast.LENGTH_SHORT).show();
            }
        });

        event_description_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"New activity should be opened",Toast.LENGTH_SHORT).show();
            }
        });
//        List<String> spinnerAddTickets = new ArrayList<>();
//        spinnerAddTickets.add("Добавьте билеты");
//        Spinner add_event_tickets_spinner = v.findViewById(R.id.add_event_tickets_spinner);
//        CustomSpinnerAdapter customSpinnerAdapterTickets = new CustomSpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, spinnerAddTickets);
//        add_event_tickets_spinner.setAdapter(customSpinnerAdapterTickets);
//        add_event_tickets_spinner.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Toast.makeText(getContext(),"Tickets Clicked",Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

        List<String> privacy_spinner_list = new ArrayList<>();
        privacy_spinner_list.add("Публичное");
        privacy_spinner_list.add("Частное");

        Spinner event_privacy_choose_spinner = v.findViewById(R.id.event_privacy_choose_spinner);
        CustomSpinnerAdapter customSpinnerAdapterPrivacy = new CustomSpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, privacy_spinner_list);
        customSpinnerAdapterPrivacy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        event_privacy_choose_spinner.setAdapter(customSpinnerAdapterPrivacy);
        event_privacy_choose_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
                TextView linearLayout = (TextView) ((LinearLayout) parent.getChildAt(0)).getChildAt(0);
                linearLayout.setTextColor(Color.parseColor("#212121"));
                linearLayout.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

                if (parent.getItemAtPosition(position).toString().equals("Частное")){
                    Toast.makeText(getContext(),"New activity should be opened",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        more_options_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.typeOfEventCalendar)
                        .setItems(R.array.typeOfEventCalendarArr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        more_options_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

//        DroppyMenuPopup.Builder droppyBuilderCalendar = new DroppyMenuPopup.Builder(getContext(), more_options_calendar);
//        droppyBuilderCalendar.addMenuItem(new DroppyMenuItem("Повторяющееся мероприятие"))
//                .addSeparator();
//
//        droppyBuilderCalendar.setOnClick(new DroppyClickCallbackInterface() {
//            @Override
//            public void call(View v, int id) {
//                Log.d("Clicked on ", String.valueOf(id));
//            }
//        });
//
//        DroppyMenuPopup droppyMenuCalendar = droppyBuilderCalendar.build();
//
//
//        DroppyMenuPopup.Builder droppyBuilderTickets = new DroppyMenuPopup.Builder(getContext(), more_options_tickets);
//        droppyBuilderTickets.addMenuItem(new DroppyMenuItem("Интерактивная схема зала"))
//                .addSeparator();
//
//        droppyBuilderTickets.setOnClick(new DroppyClickCallbackInterface() {
//            @Override
//            public void call(View v, int id) {
//                Log.d("Clicked on ", String.valueOf(id));
//            }
//        });
//
//        DroppyMenuPopup droppyMenuTickets = droppyBuilderTickets.build();
//
//
//
//        DroppyMenuPopup.Builder droppyBuilderLocation = new DroppyMenuPopup.Builder(getContext(), more_options_location);
//        droppyBuilderLocation.addMenuItem(new DroppyMenuItem("Меню1")).addMenuItem(new DroppyMenuItem("Меню2"))
//                .addSeparator();
//
//        droppyBuilderLocation.setOnClick(new DroppyClickCallbackInterface() {
//            @Override
//            public void call(View v, int id) {
//                Log.d("Clicked on ", String.valueOf(id));
//            }
//        });
//
//        DroppyMenuPopup droppyMenuLocation = droppyBuilderLocation.build();

















        return v;
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
    }

    @Override
    public void setDate(int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentMonthString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        String month1 = c.getDisplayName(c.MONTH, c.LONG, Locale.getDefault());
        int day1 = c.get(c.DAY_OF_MONTH);
        String user_date = day1+" "+month1;
//        String[] choosen_date = currentMonthString.split(",");
//        String[] cur_date = choosen_date[1].split(" ");


        if (start_date_btn_clicked){
            start_date_btn_picker.setText(user_date);
            start_date_btn_clicked =false;
        }
        if(end_date_btn_clicked){
            end_date_button_picker.setText(user_date);
            end_date_btn_clicked = false;
        }
    }

    @Override
    public void setTime(int hour, int minute) {
        if (start_date_time_btn_clicked){
            start_date_time_btn_picker.setText(String.valueOf(hour)+" : "+String.valueOf(minute));
            start_date_time_btn_clicked =false;
        }
        if(end_date_time_btn_clicked){
            end_date_time_button_picker.setText(String.valueOf(hour)+" : "+String.valueOf(minute));
            end_date_time_btn_clicked = false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mainImageURI = result.getUri();
                View view = v.findViewById(R.id.view_background);
                view.setVisibility(View.INVISIBLE);
                image_create_event_frag.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
                image_create_event_frag.setImageURI(mainImageURI);
//                image_create_event_frag.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));


//                avatarView5.bind("Yelaman Myrzakhanov", String.valueOf(mainImageUri));
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
