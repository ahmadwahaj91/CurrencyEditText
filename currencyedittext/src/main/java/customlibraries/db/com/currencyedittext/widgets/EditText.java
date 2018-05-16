package customlibraries.db.com.currencyedittext.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;


public class EditText extends AppCompatEditText {

    /**
     * Constructor
     *
     * @param context allows access to application-specific resources and classes
     * @see #init(Context, AttributeSet)
     */
    public EditText(Context context) {
        super(context);
        init(context, null);
    }

    /**
     * @param context allows access to application-specific resources and classes
     * @param attrs   collection of attributes
     *                <p>
     * Checks whether the View is in Editable mode.
     */
    private void init(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }
    }

    /**
     * @param context allows access to application-specific resources and classes
     * @param attrs   collection of attributes
     * @see #init(Context, AttributeSet)
     */
    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * @param context  allows access to application-specific resources and classes
     * @param attrs    collection of attributes
     * @param defStyle contains a reference to a style resource that supplies defaults values for the StyledAttributes.
     * @see #init(Context, AttributeSet)
     */
    public EditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }
}
