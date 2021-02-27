package com.example.connectionbetweenfragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentKelvin extends Fragment {

    private EditText etKelvin;
    private FragmentKelvinListener listener;

    public interface FragmentKelvinListener {
        void onInputKelvinSent(String input);
    }

    public FragmentKelvin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kelvin, container, false);

        etKelvin = v.findViewById(R.id.et_kelvin);
        v.findViewById(R.id.btn_Kelvin).setOnClickListener(bv -> {

            try {
                String input = etKelvin.getText().toString();
                listener.onInputKelvinSent(input);
            } catch(NumberFormatException e){
                etKelvin.setError("Please enter a number");
                return;
            }
        });

        return v;
    }

    //ontvangt data van buitenaf
    public void updateKelvin(String input, char originTemperature){

        Double convertedInput = 0.0;
        convertedInput = Double.parseDouble(input);

        switch(originTemperature){
            case 'c': //If the origin is celsius
                convertedInput = convertedInput+273.15;
                break;

            case 'f': //if the origin is fahrenheit
                convertedInput = ((convertedInput-32)/1.8)+273.15;
                break;
        }

        convertedInput = Math.round(convertedInput*100.0)/100.0;

        etKelvin.setError(null);
        etKelvin.setText(convertedInput.toString());
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if (context instanceof FragmentKelvinListener){
            listener = (FragmentKelvinListener)context;
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