package com.db.currencyedittext;


public interface CurrencyEditView {
    String getCurrencyText();

    void showCurrencyError(int resId);

    void showCurrencySymbolError(int resId);

    void showAlphabetsError(int resId);
}
