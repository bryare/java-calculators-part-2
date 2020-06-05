package com.example.calculatorarray.ui.MortgageCalculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MortCalcViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MortCalcViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Mortgage Calculator");
    }

    public LiveData<String> getText() {
        return mText;
    }
}