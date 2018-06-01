package com.db.currencyedittext;


public class CurrencyEditTextConditions {
    private CurrencyEditView view;

    public CurrencyEditTextConditions(CurrencyEditView view) {
        this.view = view;
    }

    public void CurrencyTextValues() {
        String currencyEditText = view.getCurrencyText();
        if (currencyEditText.isEmpty()) {
            view.showCurrencyError(R.string.empty_error);
            return;
        }
        if (!currencyEditText.contains("£")) {
            view.showCurrencySymbolError(R.string.symbol_error);
            return;
        }
        if (currencyEditText.contains("[a-zA-Z ]+")) {
            view.showAlphabetsError(R.string.text_error);
            return;
        }

    }
}
