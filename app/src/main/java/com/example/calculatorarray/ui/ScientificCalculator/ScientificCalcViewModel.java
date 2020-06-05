package com.example.calculatorarray.ui.ScientificCalculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScientificCalcViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ScientificCalcViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}