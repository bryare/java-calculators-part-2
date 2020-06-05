package com.example.calculatorarray.ui.MortgageCalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.calculatorarray.MainActivity;
import com.example.calculatorarray.R;

import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.widget.Toast;


public class MortgageCalculatorFragment extends Fragment {

    private MortCalcViewModel mortCalcViewModel;
    TextView totalBalance, monthlyPayment;
    EditText mortgageAmount, interestRate, mortgagePeriod;
    Button calculate, clear, save;

    double total, mortAmt, rate, mortPeriod;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mortCalcViewModel =
                ViewModelProviders.of(this).get(MortCalcViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);

        totalBalance = root.findViewById(R.id.totalBalance);
        monthlyPayment = root.findViewById(R.id.monthlyPayment);
        mortgageAmount = root.findViewById(R.id.mortgageAmount);
        interestRate = root.findViewById(R.id.interestRate);
        mortgagePeriod = root.findViewById(R.id.mortgagePeriod);

        calculate = root.findViewById(R.id.calculate);
        clear = root.findViewById(R.id.clear);
        save = root.findViewById(R.id.save);

        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                double base = 0;
                double months = 0;
                double mPay = 0;
                mortAmt = (Double.parseDouble(mortgageAmount.getText().toString()));
                rate = (Double.parseDouble(interestRate.getText().toString()));
                mortPeriod = (Double.parseDouble(mortgagePeriod.getText().toString()));


                if(mortgageAmount.getText().toString().matches("") || interestRate.getText().toString().matches("") || mortgagePeriod.getText().toString().matches("") || mortAmt == 0){

                    Toast.makeText(getActivity(),"Enter all fields",Toast.LENGTH_SHORT).show();


                    mortPeriod = 0;
                    mortAmt = 0;
                    rate = 0;


                }else {

                    rate = (rate / 100) / 12;
                    base = (rate + 1);
                    months = mortPeriod * 12;
                    mPay = mortAmt * (rate * (Math.pow(base, months)) /
                            ((Math.pow(base, months)) - 1));

                    monthlyPayment.setText("Monthly Payment: " + String.valueOf(mPay));

                    total = (mPay * (mortPeriod * 12));

                    totalBalance.setText("Total Balance: " + String.valueOf(total));
                }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Data Saved",
                        "\n" + getTotalBalance() + "\n" + getMonthlyPayment());
                clipboard.setPrimaryClip(clip);

                MainActivity activity = (MainActivity) getActivity();
                activity.setPasteData(clip.toString());
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mortgageAmount.setText("");
                interestRate.setText("");
                mortgagePeriod.setText("");

                mortgageAmount.setHint(mortgageAmount.getHint());
                interestRate.setHint(interestRate.getHint());
                mortgagePeriod.setHint(mortgagePeriod.getHint());

                monthlyPayment.setText("Monthly Payment");
                totalBalance.setText("Total Balance");
            }
        });

        mortCalcViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        return root;
    }

    public String getTotalBalance(){
        // Inflate the menu; this adds items to the action bar if it is present.
        return totalBalance.getText().toString();
    }

    public String getMonthlyPayment(){
        // Inflate the menu; this adds items to the action bar if it is present.
        return monthlyPayment.getText().toString();

    }


}

