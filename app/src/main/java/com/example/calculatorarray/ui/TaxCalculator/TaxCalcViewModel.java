package com.example.calculatorarray.ui.TaxCalculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TaxCalcViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TaxCalcViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}