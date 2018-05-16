package customlibraries.db.com.currencyedittext.utils;

import android.text.TextUtils;

public class CommonUtil {

    /**
     * Get the value of the text without any commas and currency.
     * For example, if the edit text value is $ 1,34,000.60 then this method will return 134000.60
     *
     * @return the text field value in integer form
     */
    public static int getIntValue(String value) {
        if (!TextUtils.isEmpty(value)) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {

            }
        }
        return 0;
    }

    /**
     * Get the value of the text entered, in decimal format without rounding off the number.
     *
     * @return a double value specified in the String variable
     */
    public static double getDoubleValue(String value) {
        if (!TextUtils.isEmpty(value)) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {

            }
        }
        return 0.0;
    }

    /**
     * Get the value of the text entered, in decimal format without rounding off the number.
     *
     * @return a double value specified in the String variable
     */
    public static float getFloatValue(String value) {
        if (!TextUtils.isEmpty(value)) {
            try {
                return Float.parseFloat(value);
            } catch (NumberFormatException e) {

            }
        }
        return 0.0f;
    }
}
