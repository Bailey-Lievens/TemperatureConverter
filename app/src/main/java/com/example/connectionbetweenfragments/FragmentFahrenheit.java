package com.example.connectionbetweenfragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentFahrenheit extends Fragment {

    private EditText etFahrenheit;
    private FragmentFahrenheitListener listener;

    public interface FragmentFahrenheitListener {
        void onInputFahrenheitSent(String input);
    }

    public FragmentFahrenheit() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fahrenheit, container, false);

        etFahrenheit = v.findViewById(R.id.et_fahrenheit);
        v.findViewById(R.id.btn_Celsius).setOnClickListener(bv -> {

            try {
                String input = etFahrenheit.getText().toString();
                listener.onInputFahrenheitSent(input);
            } catch(NumberFormatException e){
                etFahrenheit.setError("Please enter a number");
                return;
            }
        });

        return v;
    }

    //ontvangt data van buitenaf
    public void updateFahrenheit(String input, char originTemperature){

        Double convertedInput = 0.0;
        convertedInput = Double.parseDouble(input);

        switch(originTemperature){
            case 'c': //If the origin is celsius
                convertedInput = convertedInput*1.8+32;
                break;

            case 'k': //if the origin is kelvin
                convertedInput = (convertedInput-273.15)*1.8+32;
                break;
        }

        convertedInput = Math.round(convertedInput*100.0)/100.0;


        etFahrenheit.setError(null);
        etFahrenheit.setText(convertedInput.toString());
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if (context instanceof FragmentFahrenheitListener){
            listener = (FragmentFahrenheitListener)context;
        } else {
            throw new RuntimeException(
                    String.format("%s must implement FragmentBottomListener", context.toString())
            );
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }
}