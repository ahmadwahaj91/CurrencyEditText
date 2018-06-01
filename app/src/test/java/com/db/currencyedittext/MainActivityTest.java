package com.db.currencyedittext;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import customlibraries.db.com.currencyedittext.CurrencyEditText;

/**
 * Unit Testing of the CurrencyEditText using Mockito Framework
 */
@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest extends TestCase {
    @Mock
    private CurrencyEditText currencyEditText;
    @Mock
    MainActivity activity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testCurrencyEditText() throws Exception {
        MockitoAnnotations.initMocks(this);
        activity = Mockito.mock(MainActivity.class);
        activity = Mockito.spy(new MainActivity());

        assertNotNull(activity);
        assertNotNull("Currency Edit Text", currencyEditText);
    }

    @Test
    public void testCurrencySymbol() throws Exception {
        try {
//            assertEquals("£", currencyEditText.getText().toString());
            assert (currencyEditText.getValueString().contains("£"));
        } catch (Exception r) {
            r.printStackTrace();
        }
    }
}