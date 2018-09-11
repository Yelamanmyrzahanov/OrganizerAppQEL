package kz.djunglestones.organizerappqel;


import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment implements MyInterface {
    private Button start_date_btn_picker,start_date_time_btn_picker,end_date_button_picker,end_date_time_button_picker;
    View v;
    private boolean start_date_btn_clicked,end_date_btn_clicked,start_date_time_btn_clicked,end_date_time_btn_clicked;
    private TextView company_name_edittext;
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
                getContext().startActivity(intent);
            }
        });



        return v;
    }

    private void initUI() {
        start_date_btn_picker = v.findViewById(R.id.start_date_btn_picker);
        start_date_time_btn_picker = v.findViewById(R.id.start_date_time_btn_picker);
        end_date_button_picker = v.findViewById(R.id.end_date_button_picker);
        end_date_time_button_picker = v.findViewById(R.id.end_date_time_button_picker);
        company_name_edittext = v.findViewById(R.id.company_name_edittext);
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
}
