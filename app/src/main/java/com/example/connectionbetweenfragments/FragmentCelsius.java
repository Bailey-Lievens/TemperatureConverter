package com.example.connectionbetweenfragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentCelsius extends Fragment {

    private EditText etCelsius;
    private FragmentCelsiusListener listener;

    public interface FragmentCelsiusListener {
        void onInputCelsiusSent(String input);
    }

    public FragmentCelsius() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_celsius, container, false);

        etCelsius = v.findViewById(R.id.et_celsius);
        v.findViewById(R.id.btn_Celsius).setOnClickListener(bv -> {

            try {
                String input = etCelsius.getText().toString();
                listener.onInputCelsiusSent(input);
            }catch (NumberFormatException e){
                etCelsius.setError("Please enter a number");
                return;
            }


        });

        return v;
    }

    //ontvangt data van buitenaf
    public void updateCelsius(String input, char originTemperature){

        Double convertedInput = 0.0;
        convertedInput = Double.parseDouble(input);

        switch(originTemperature){
            case 'f': //If the origin is fahrenheit
                convertedInput = (convertedInput -32)/1.8;
                break;

            case 'k': //if the origin is kelvin
                convertedInput = convertedInput - 273.15;
                break;
        }

        convertedInput = Math.round(convertedInput*100.0)/100.0;

        etCelsius.setError(null);
        etCelsius.setText(convertedInput.toString());
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if (context instanceof FragmentCelsiusListener){ //instanceof kan je checken of het type gelijk is aan
            listener = (FragmentCelsiusListener)context;
        } else {
            throw new RuntimeException(
                    String.format("%s must implement FragmentTopListener", context.toString())
            );
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }
}