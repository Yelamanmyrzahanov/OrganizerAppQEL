package kz.djunglestones.organizerappqel.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kz.djunglestones.organizerappqel.Fragments.DiscountPromocodeDialog;
import kz.djunglestones.organizerappqel.Models.PromoCode;
import kz.djunglestones.organizerappqel.R;

public class AddPromoCodeActivity extends AppCompatActivity implements DiscountPromocodeDialog.DiscountResultInterface {
    private Toolbar toolbar;
    private boolean isValidPromocode = false;
    private List<PromoCode> promoCodeList;
    private TextView promocode_name;
    private TextView discount_type;
    private TextView which_tickets_tv;
    private ConstraintLayout constraint_promocode_name, constraint_discount, constraint_choose_ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_promo_code);
        toolbar = findViewById(R.id.toolbar_promo_code);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Добавить промокод");

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        promocode_name = findViewById(R.id.promocode_name_edittext);
        discount_type = findViewById(R.id.discount_type_edittext);
        which_tickets_tv = findViewById(R.id.which_tickets_tv);
        constraint_choose_ticket = findViewById(R.id.constraint_choose_ticket);
        constraint_discount = findViewById(R.id.constraint_discount);
        constraint_promocode_name = findViewById(R.id.constraint_promocode_name);

        constraint_promocode_name.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(AddPromoCodeActivity.this);
            builder.setTitle("Название промокода");
            LayoutInflater inflater = AddPromoCodeActivity.this.getLayoutInflater();
            View promocodeView = inflater.inflate(R.layout.pop_up_promocode_name, null);
            EditText pop_up_promocode_edittext = promocodeView.findViewById(R.id.pop_up_promocode_edittext);
            if (!promocode_name.getText().toString().equals("Введите уникальный промокод")) {
                pop_up_promocode_edittext.setText(promocode_name.getText().toString());
            }
            builder.setView(promocodeView)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (!pop_up_promocode_edittext.getText().toString().isEmpty()) {
                                String promocode = pop_up_promocode_edittext.getText().toString();
                                promocode_name.setText(promocode);
                            } else {
                                Toast toast = Toast.makeText(AddPromoCodeActivity.this, "Поле пустое", Toast.LENGTH_SHORT);
                                TextView textView = toast.getView().findViewById(android.R.id.message);
                                textView.setTextColor(Color.RED);
                                toast.show();
                            }


                        }
                    })
                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            builder.create().show();
        });

        constraint_discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        constraint_choose_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPromoCodeActivity.this, ChoosePromocodeTicketActivity.class);
                if (!which_tickets_tv.getText().toString().equals("Выберите, на какой билет(ы) применяется промокод")){
                    intent.putExtra("tickets_name_choosen",which_tickets_tv.getText().toString());
                }else{
                    intent.putExtra("tickets_name_choosen","empty");
                }
                startActivityForResult(intent, 157);
            }
        });


    }

    private void openDialog() {
        DiscountPromocodeDialog discountPromocodeDialog = new DiscountPromocodeDialog();
        Bundle bundle = new Bundle();
        bundle.putString("result", discount_type.getText().toString());
        discountPromocodeDialog.setArguments(bundle);
        discountPromocodeDialog.show(getSupportFragmentManager(), "discount dialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_company_menu, menu);
        MenuItem save = menu.findItem(R.id.save_edit_company);
        save.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (validateData()) {
                    String promocode_name_text = promocode_name.getText().toString();
                    int discount = Integer.parseInt(discount_type.getText().toString().replace(" %", ""));
                    String ticketName = which_tickets_tv.getText().toString();
                    promoCodeList.add(new PromoCode(promocode_name_text, discount, ticketName));
                    Intent returnIntent = getIntent();
                    returnIntent.putExtra("promocodeList", (Serializable) promoCodeList);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    Toast toast = Toast.makeText(AddPromoCodeActivity.this, "Заполните все поля", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    v.setTextColor(Color.RED);
                    toast.show();
                }

                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private boolean validateData() {
        if (!promocode_name.getText().toString().isEmpty() && !discount_type.getText().toString().isEmpty() && !which_tickets_tv.getText().toString().equals("Выберите, на какой билет(ы) применяется промокод")) {
            promoCodeList = new ArrayList<>();
            isValidPromocode = true;

        } else {
//            if (!promocode_name_edittext.getText().toString().isEmpty() && !discount_type_edittext.getText().toString().isEmpty() && which_tickets_tv.getText().toString().equals("Выберите, на какой билет(ы) применяется промокод")){
//                Toast toast = Toast.makeText(AddPromoCodeActivity.this,"Выберите билет",Toast.LENGTH_SHORT);
//                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
//                v.setTextColor(Color.RED);
//                toast.show();
//            }else
            if (promocode_name.getText().toString().isEmpty()) {
                Toast toast = Toast.makeText(AddPromoCodeActivity.this, "Введите уникальный промокод", Toast.LENGTH_SHORT);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                v.setTextColor(Color.RED);
                toast.show();
            } else if (discount_type.getText().toString().isEmpty()) {
                Toast toast = Toast.makeText(AddPromoCodeActivity.this, "Выберите сумму или процент", Toast.LENGTH_SHORT);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                v.setTextColor(Color.RED);
                toast.show();
            } else if (which_tickets_tv.getText().toString().equals("Выберите, на какой билет(ы) применяется промокод")) {
                Toast toast = Toast.makeText(AddPromoCodeActivity.this, "Выберите билет", Toast.LENGTH_SHORT);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                v.setTextColor(Color.RED);
                toast.show();
            }
            isValidPromocode = false;
        }
        return isValidPromocode;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 157 && resultCode == Activity.RESULT_OK) {
            String ticketNames = data.getStringExtra("ticket_names").substring(0, data.getStringExtra("ticket_names").length() - 2);
            which_tickets_tv.setText(ticketNames);
        }
    }

    @Override
    public void setResult(String result) {
        discount_type.setText(result);
    }
}
