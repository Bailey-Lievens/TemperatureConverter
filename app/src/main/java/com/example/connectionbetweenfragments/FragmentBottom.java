package com.example.connectionbetweenfragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentBottom extends Fragment {

    private EditText etFahrenheit;
    private FragmentBottomListener listener;

    public interface FragmentBottomListener {
        void onInputBottomSent(String input);
    }

    public FragmentBottom() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fahrenheit, container, false);

        etFahrenheit = v.findViewById(R.id.et_fahrenheit);
        v.findViewById(R.id.btn_ToCelcius).setOnClickListener(bv -> {
            String input = etFahrenheit.getText().toString();

            //Stuur naar andere fragment
            listener.onInputBottomSent(input);
        });

        return v;
    }

    //ontvangt data van buitenaf
    public void updateFahrenheit(String input){

        Double convertedInput = Double.parseDouble(input);

        convertedInput = convertedInput * 1.8 +32;

        etFahrenheit.setText(convertedInput.toString());
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if (context instanceof FragmentBottomListener){
            listener = (FragmentBottomListener)context;
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