package com.db.currencyedittext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyEditTextConditionsTest {

    @Mock
    private CurrencyEditView view;
    private CurrencyEditTextConditions presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new CurrencyEditTextConditions(view);
    }

    @Test
    public void shouldShowErrorMessageWhenCurrencyEditTextIsEmpty() throws Exception {
        when(view.getCurrencyText()).thenReturn("");
        presenter.CurrencyTextValues();

        verify(view).showCurrencyError(R.string.empty_error);
    }

    @Test
    public void shouldShowWhenCurrencyEditTextContainsSymbol() throws Exception {
        when(view.getCurrencyText()).thenReturn("£");
        presenter.CurrencyTextValues();

        verify(view).showCurrencySymbolError(R.string.symbol_error);
    }

    @Test
    public void shouldShowWhenCurrencyEditTextContainsAlphabets() throws Exception {
        when(view.getCurrencyText()).thenReturn("abcde");
        presenter.CurrencyTextValues();

        verify(view).showAlphabetsError(R.string.text_error);
    }

}