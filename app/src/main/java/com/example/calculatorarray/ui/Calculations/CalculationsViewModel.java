package com.example.calculatorarray.ui.Calculations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CalculationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}