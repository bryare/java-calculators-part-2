package com.example.calculatorarray.ui.TaxCalculator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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

public class TaxCalculatorFragment extends Fragment {

    private TaxCalcViewModel taxCalcViewModel;

    TextView totalSalesTax, totalTaxBalance;
    EditText itemCost, taxInterestRate;
    Button calculateTax, clearTax, saveTax;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        taxCalcViewModel =
                ViewModelProviders.of(this).get(TaxCalcViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);

        totalTaxBalance = root.findViewById(R.id.totalTaxBalance);
        totalSalesTax = root.findViewById(R.id.totalSalesTax);
        itemCost = root.findViewById(R.id.itemCost);
        taxInterestRate = root.findViewById(R.id.taxInterestRate);


        calculateTax = root.findViewById(R.id.calculateTax);
        clearTax = root.findViewById(R.id.clearTax);
        saveTax = root.findViewById(R.id.saveTax);

        calculateTax.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                double tax, balance, cost, rate;
                cost = (Double.parseDouble(itemCost.getText().toString()));
                rate = (Double.parseDouble(taxInterestRate.getText().toString()));

                rate/=100;
                tax = rate*cost;


                totalSalesTax.setText("Total Sales Tax: " + String.valueOf(tax));
                balance = tax + cost;

                totalTaxBalance.setText("Total Tax Balance: " +String.valueOf(balance));
            }
        });


        clearTax.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                itemCost.setText("");
                taxInterestRate.setText("");


                itemCost.setHint(itemCost.getHint());
                taxInterestRate.setHint(taxInterestRate.getHint());

                totalSalesTax.setText("Total Sales Tax");
                totalTaxBalance.setText("Total Balance");
            }
        });


        saveTax.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Data Saved",
                        "\n" + getTotalSalesTax() + "\n" + getTotalTaxBalance() );
                clipboard.setPrimaryClip(clip);

                MainActivity activity = (MainActivity) getActivity();
                activity.setPasteData(clip.toString());
            }
        });

        taxCalcViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    public String getTotalSalesTax(){
        // Inflate the menu; this adds items to the action bar if it is present.
        return totalSalesTax.getText().toString();
    }

    public String getTotalTaxBalance(){
        // Inflate the menu; this adds items to the action bar if it is present.
        return totalTaxBalance.getText().toString();

    }
}