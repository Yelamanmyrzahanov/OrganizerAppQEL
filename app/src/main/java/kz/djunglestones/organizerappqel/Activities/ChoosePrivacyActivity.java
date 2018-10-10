package kz.djunglestones.organizerappqel.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Objects;

import kz.djunglestones.organizerappqel.R;

public class ChoosePrivacyActivity extends AppCompatActivity {
    private ConstraintLayout user_receive_message_constraint,user_must_enter_password_constraint;
    private CheckBox user_receive_message_checbox;
    private Switch user_must_enter_password_switch;
    private EditText password_edittext;
    private TextView password_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_privacy);

        setupStatusBar();
        setupToolbar();

        initUI();
        final boolean[] checkboxChecked = {false};
        user_receive_message_constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkboxChecked[0]){
                    checkboxChecked[0] = false;
                    user_receive_message_checbox.setChecked(false);
                }else{
                    checkboxChecked[0] = true;
                    user_receive_message_checbox.setChecked(true);
                }
            }
        });
        final boolean[] switchChecked = {false};
        user_must_enter_password_constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchChecked[0]){
                    switchChecked[0] = false;
                    user_must_enter_password_switch.setChecked(false);
                    password_tv.setVisibility(View.GONE);
                    password_edittext.setVisibility(View.GONE);
                }else{
                    switchChecked[0] = true;
                    user_must_enter_password_switch.setChecked(true);
                    password_tv.setVisibility(View.VISIBLE);
                    password_edittext.setVisibility(View.VISIBLE);
                }

            }
        });

        user_must_enter_password_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    switchChecked[0] = true;
                    user_must_enter_password_switch.setChecked(true);
                    password_tv.setVisibility(View.VISIBLE);
                    password_edittext.setVisibility(View.VISIBLE);

                } else{
                    switchChecked[0] = false;
                    user_must_enter_password_switch.setChecked(false);
                    password_tv.setVisibility(View.GONE);
                    password_edittext.setVisibility(View.GONE);
                }
            }
        });

        password_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


    }

    private void initUI() {
        user_receive_message_constraint = findViewById(R.id.user_receive_message_constraint);
        user_must_enter_password_constraint = findViewById(R.id.user_must_enter_password_constraint);
        user_receive_message_checbox = findViewById(R.id.user_receive_message_checbox);
        user_must_enter_password_switch = findViewById(R.id.user_must_enter_password_switch);
        password_edittext = findViewById(R.id.password_edittext);
        password_tv = findViewById(R.id.password_tv);
    }

    private void setupToolbar() {
        Toolbar toolbar  = findViewById(R.id.toolbar_choose_privacy);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Приватность");
    }

    private void setupStatusBar() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
