package com.db.currencyedittext;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;

import customlibraries.db.com.currencyedittext.CurrencyEditText;

/**
 * Instrumentation Testing of the CurrencyEditText using JUnit
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mMockMainActivity;
    private CurrencyEditText currencyEditText;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();

        mMockMainActivity = this.getActivity();
        currencyEditText = mMockMainActivity.findViewById(R.id.price);
    }

    public void testPreconditions() {
        assertNotNull("mTestActivity is null", mMockMainActivity);
        assertNotNull("currency is not null", currencyEditText);
    }

    public void testView() throws Exception {
        assertEquals("", mMockMainActivity.currencyEditText.getValueString());
    }
}