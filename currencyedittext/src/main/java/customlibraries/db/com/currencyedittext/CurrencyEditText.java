package customlibraries.db.com.currencyedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;


import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

import customlibraries.db.com.currencyedittext.utils.CommonUtil;
import customlibraries.db.com.currencyedittext.widgets.EditText;


public class CurrencyEditText extends EditText {
    private String _currencySymbol;
    private boolean _showCurrency;
    private boolean _showCommas;

    /**
     * Constructor
     *
     * @param context allows access to application-specific resources and classes
     */
    public CurrencyEditText(Context context) {
        super(context);
        initView(context, null);
    }

    /**
     * @param context allows access to application-specific resources and classes
     * @param attrs   collection of attributes
     * @see #initTextWatchers()
     * <p>
     * Sets the Default Parameters, checks for the attributes(if null or not) and Attribute initialization accordingly.
     */
    private void initView(Context context, AttributeSet attrs) {
        _currencySymbol = Currency.getInstance(Locale.getDefault()).getSymbol();
        _showCurrency = true;
        _showCommas = true;

        if (attrs != null) {
            final TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.CurrencyWidgets, 0, 0);
            try {
                String currency = attrArray.getString(R.styleable.CurrencyWidgets_currency_symbol);
                if (currency == null)
                    currency = Currency.getInstance(Locale.getDefault()).getSymbol();
                setCurrency(currency);

                _showCurrency = attrArray.getBoolean(R.styleable.CurrencyWidgets_show_currency, true);
                _showCommas = attrArray.getBoolean(R.styleable.CurrencyWidgets_show_commas, true);
            } finally {
                attrArray.recycle();
            }
        }

        initTextWatchers();
    }

    /**
     * Sets the currency symbol for the edit text. (Default is US Dollar $).
     *
     * @param newSymbol custom new symbol of currency in string.
     * @see #updateValue(String)
     */
    public void setCurrency(String newSymbol) {
        _currencySymbol = newSymbol;
        updateValue(getText().toString());
    }

    /**
     * Applying Text Watchers ,storing value to apply  Decimal formatting for use later.
     */
    private void initTextWatchers() {
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                CurrencyEditText.this.removeTextChangedListener(this);
                String backupString = charSequence.toString();

                try {
                    String originalString = charSequence.toString();

                    long longval;

                    originalString = getValueString();
                    longval = (Long.parseLong(originalString));
                    String formattedString = getDecoratedStringFromNumber(longval);

                    //setting text after format to EditText
                    setText(formattedString);
                    setSelection(getText().length());

                } catch (NumberFormatException nfe) {
//                    nfe.printStackTrace();
                    setText(backupString);

                    String valStr = getValueString();

                    if (valStr.equals("") || getValueInt() <= 0) {
                        long val = 0;
                        setText("");
                        setHint(getDecoratedStringFromNumber(val));
                    } else {
                        // Some decimal number
                        if (valStr.contains(".")) {
                            if (valStr.indexOf(".") == valStr.length() - 1) {
                                // decimal has been currently put
                                String front = getDecoratedStringFromNumber(Long.parseLong(valStr.substring(0, valStr.length() - 1)));
                                setText(front + ".");
                            } else {
                                String[] nums = getValueString().split("\\.");
                                String front = getDecoratedStringFromNumber(Long.parseLong(nums[0]));
                                setText(front + "." + nums[1]);
                            }
                        }
                    }
                    setSelection(getText().length());
                }

                CurrencyEditText.this.addTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * Sets the Complete String with the new Currecny Value.
     *
     * @param text updated value with the new currency symbol.
     */
    private void updateValue(String text) {
        setText(text);
    }

    /**
     * Get the value of the text without any commas and currency.
     * For example, if the editText value is $ 1,34,000.60 then this method will return 134000.60
     *
     * @return The text field value in String format
     */
    public String getValueString() {

        String string = getText().toString();

        if (string.contains(",")) {
            string = string.replace(",", "");
        }
        if (string.contains(" ")) {
            string = string.substring(string.indexOf(" ") + 1, string.length());
        }
        return string;
    }

    /**
     * Get the value of the text without any commas and currency.
     * For example, if the edit text value is $ 1,34,000.60 then this method will return 134000.60
     *
     * @return the text field value in integer form
     */
    public int getValueInt() {
        return CommonUtil.getIntValue(getValueString());
    }

    /**
     * Show the number i.e. long value in proper commas separated format.
     * For example, if the long value is 1234567 then this method will return 1,234,567
     */
    private String getDecoratedStringFromNumber(long number) {
        String numberPattern = "#,###,###,###";
        String decoStr = "";

        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());
        if (_showCommas && !_showCurrency)
            formatter.applyPattern(numberPattern);
        else if (_showCommas && _showCurrency)
            formatter.applyPattern(_currencySymbol + " " + numberPattern);
        else if (!_showCommas && _showCurrency)
            formatter.applyPattern(_currencySymbol + " ");
        else if (!_showCommas && !_showCurrency) {
            decoStr = number + "";
            return decoStr;
        }

        decoStr = formatter.format(number);

        return decoStr;
    }

    /**
     * @param context allows access to application-specific resources and classes
     * @param attrs   collection of attributes
     * @see #init(Context, AttributeSet)
     */
    public CurrencyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    /**
     * Checks whether the user has stopped entering value or if the container has reached its maximum limit
     *
     * @see #updateValue(String)
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateValue(getText().toString());
    }

    /**
     * Get the value of the text with formatted commas and currency.
     * For example, if the edit text value is 13400060 then this method will return exactly $ 1,34,000.60
     *
     * @return the text field value in a Formatted String format.
     */
    public String getFormattedString() {
        return getText().toString();
    }

    /**
     * Set the currency symbol for the edit text. (Default is US Dollar $).
     *
     * @param locale the locale of new symbol. (Default is Locale.US)
     */
    public void setCurrency(Locale locale) {
        setCurrency(Currency.getInstance(locale).getSymbol());
    }

    /**
     * Set the currency symbol for the edit text. (Default is US Dollar $).
     *
     * @param currency the currency object of the custom new symbol. (Default is Locale.US)
     */
    public void setCurrency(Currency currency) {
        setCurrency(currency.getSymbol());
    }

    /**
     * Whether currency is shown in the text or not. (Default is true)
     *
     * @return true if the currency is shown otherwise false.
     */
    public boolean isShowCurrency() {
        return _showCurrency;
    }

    /**
     * Whether currency is shown in the text or not. (Default is true)
     *
     * @see #updateValue(String)
     */
    private void setShowCurrency(boolean value) {
        _showCurrency = value;
        updateValue(getText().toString());
    }

    /**
     * Shows the currency in the text. (Default is shown).
     *
     * @see #setShowCurrency(boolean)
     */
    public void showCurrencySymbol() {
        setShowCurrency(true);
    }

    /**
     * Hides the currency in the text. (Default is shown).
     *
     * @see #setShowCurrency(boolean)
     */
    public void hideCurrencySymbol() {
        setShowCurrency(false);
    }

    /**
     * Shows the commas in the text. (Default is shown).
     * <p>
     * * @see #updateValue(String)
     */
    public void showCommas() {
        _showCommas = true;
        updateValue(getText().toString());
    }

    /**
     * Hides the commas in the text. (Default is shown).
     * <p>
     * * @see #updateValue(String)
     */
    public void hideCommas() {
        _showCommas = false;
        updateValue(getText().toString());
    }
}
