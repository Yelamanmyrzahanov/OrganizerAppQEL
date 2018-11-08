package kz.djunglestones.organizerappqel.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import kz.djunglestones.organizerappqel.R;

public class DiscountPromocodeDialog extends AppCompatDialogFragment {
    private ConstraintLayout constraint_amount,constraint_percentage;
    private RadioButton amount_radiobtn,percentage_radiobtn;
    private EditText amount_edittext,percentage_edittext;
    private TextView tenge_tv,percentage_tv;
    private boolean amount_checked,percentage_checked;
    private String result="";
    private DiscountResultInterface discountResultInterface;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);

        this.result = args.getString("result");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.pop_up_discount_promocode,null);
        constraint_amount = view.findViewById(R.id.constraint_amount);
        constraint_percentage= view.findViewById(R.id.constraint_percentage);
        amount_radiobtn= view.findViewById(R.id.amount_radiobtn);
        percentage_radiobtn= view.findViewById(R.id.percentage_radiobtn);
        amount_edittext= view.findViewById(R.id.amount_edittext);
        percentage_edittext= view.findViewById(R.id.percentage_edittext);
        tenge_tv= view.findViewById(R.id.tenge_tv);
        percentage_tv= view.findViewById(R.id.percentage_tv);

        if (!(result =="Выберите сумму или процент")){
            if (result.endsWith("%")){
                setVisible("percentage");
                percentage_edittext.setText(result);
            }else if (result.endsWith("₸")){
                setVisible("amount");
                amount_edittext.setText(result);
            }
        }

        amount_radiobtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setVisible("amount");
            }

        });
        percentage_radiobtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                setVisible("percentage");
            }

        });
        builder.setView(view)
                .setTitle("Дисконт")
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (percentage_checked){
                            if (!percentage_edittext.getText().toString().isEmpty()){
                                result = percentage_edittext.getText().toString()+" %";
                                discountResultInterface.setResult(result);
                            }
                        }else if (amount_checked){
                            if (!amount_edittext.getText().toString().isEmpty()){
                                result = amount_edittext.getText().toString()+" ₸";
                                discountResultInterface.setResult(result);
                            }
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

        percentage_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    ((AlertDialog) dialog).getButton(
                            AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }else{
                    ((AlertDialog) dialog).getButton(
                            AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
            }
        });

        amount_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    ((AlertDialog) dialog).getButton(
                            AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }else{
                    ((AlertDialog) dialog).getButton(
                            AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
            }
        });


//        if (percentage_edittext.getText().toString().isEmpty() || amount_edittext.getText().toString().isEmpty()){
//            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE)
//                    .setEnabled(false);
//        }else {
//            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE)
//                    .setEnabled(true);
//        }
        return dialog;
    }

    private void setVisible(String which) {
        if (which.equals("amount")){
            amount_radiobtn.setChecked(true);
            percentage_radiobtn.setChecked(false);
            percentage_edittext.setVisibility(View.GONE);
            amount_edittext.setVisibility(View.VISIBLE);
            percentage_tv.setVisibility(View.GONE);
            tenge_tv.setVisibility(View.VISIBLE);
            percentage_radiobtn.setChecked(false);
            percentage_checked = false;
            amount_checked = true;
        }else if (which.equals("percentage")){
            amount_radiobtn.setChecked(false);
            percentage_radiobtn.setChecked(true);
            percentage_edittext.setVisibility(View.VISIBLE);
            amount_edittext.setVisibility(View.GONE);
            percentage_tv.setVisibility(View.VISIBLE);
            tenge_tv.setVisibility(View.GONE);
            amount_radiobtn.setChecked(false);
            percentage_checked = true;
            amount_checked = false;
        }
    }

    public interface DiscountResultInterface{
        void setResult(String result);
    }

    public interface  DialogDataListener{
        void onClosed(String discountResult);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        discountResultInterface = (DiscountResultInterface) context;
    }
}
