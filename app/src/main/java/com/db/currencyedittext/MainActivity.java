package com.db.currencyedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import customlibraries.db.com.currencyedittext.CurrencyEditText;

public class MainActivity extends AppCompatActivity implements CurrencyEditView {
    CurrencyEditText currencyEditText;
    String amount;
    CurrencyEditTextConditions currencyEditTextConditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currencyEditTextConditions = new CurrencyEditTextConditions(this);
        currencyEditText = findViewById(R.id.price);
        currencyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    amount = s.toString().substring(1);
                    amount = currencyEditText.getValueString();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Toast.makeText(getApplication(), "You have entered :" + currencyEditText.getText(),
                        Toast.LENGTH_LONG).show();
                System.out.println(currencyEditText.getText().toString());
                currencyEditTextConditions.CurrencyTextValues();
            }

        });
    }


    @Override
    public String getCurrencyText() {
        return currencyEditText.getText().toString();
    }

    @Override
    public void showCurrencyError(int resId) {
        currencyEditText.setError(getString(resId));
    }

    @Override
    public void showCurrencySymbolError(int resId) {
        currencyEditText.setError(getString(resId));
    }

    @Override
    public void showAlphabetsError(int resId) {
        currencyEditText.setError(getString(resId));
    }
}
