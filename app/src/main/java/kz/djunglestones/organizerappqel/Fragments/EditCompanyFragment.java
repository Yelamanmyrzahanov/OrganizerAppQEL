package kz.djunglestones.organizerappqel.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import kz.djunglestones.organizerappqel.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditCompanyFragment extends Fragment {
    View v;
    private Switch switchShowNumber;
    private EditText company_number_edittext_edit;

    public EditCompanyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.fragment_edit_company, container, false);
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        initUI();
        switchShowNumber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    company_number_edittext_edit.setTextColor(Color.GRAY);
                    company_number_edittext_edit.setEnabled(false);
                }else{
                    company_number_edittext_edit.setTextColor(Color.parseColor("#212121"));
                    company_number_edittext_edit.setEnabled(true);
                }
            }
        });

        return v;
    }

    private void initUI() {

        switchShowNumber = v.findViewById(R.id.show_number_switch);
        company_number_edittext_edit = v.findViewById(R.id.company_number_edittext_edit);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.edit_company_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
