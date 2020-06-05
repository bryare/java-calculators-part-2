package com.example.calculatorarray.ui.Calculations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.Button;

import com.example.calculatorarray.MainActivity;



import com.example.calculatorarray.R;

public class CalculationsFragment extends Fragment {

    private CalculationsViewModel calculationsViewModel;

    ListView listView;
    Button addItem;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calculationsViewModel =
                ViewModelProviders.of(this).get(CalculationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        calculationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });

        addItem = root.findViewById(R.id.addItem);

        listView = root.findViewById(R.id.listview);
        final ArrayList<String> arrayList = new ArrayList<>();
        final ArrayAdapter arrayAdapter = new ArrayAdapter(getParentFragment().getContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);


        addItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                MainActivity activity = (MainActivity) getActivity();
                String paste = activity.getPasteData();
                // Gets the clipboard as text.

                arrayList.add(paste);
                arrayAdapter.notifyDataSetChanged();
            }


        });

        return root;
    }
}