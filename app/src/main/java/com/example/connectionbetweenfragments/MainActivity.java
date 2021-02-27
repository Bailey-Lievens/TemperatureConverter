package com.example.connectionbetweenfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
    implements FragmentCelsius.FragmentCelsiusListener, FragmentFahrenheit.FragmentFahrenheitListener, FragmentKelvin.FragmentKelvinListener {

    private FragmentCelsius fragmentCelsius;
    private FragmentFahrenheit fragmentFahrenheit;
    private FragmentKelvin fragmentKelvin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentCelsius = new FragmentCelsius();
        fragmentFahrenheit = new FragmentFahrenheit();
        fragmentKelvin = new FragmentKelvin();

        getSupportFragmentManager().beginTransaction()
            .replace(R.id.layoutCelsius, fragmentCelsius)
            .replace(R.id.layoutFahrenheit, fragmentFahrenheit)
            .replace(R.id.layoutKelvin, fragmentKelvin)
            .commit();
    }

    @Override
    public void onInputFahrenheitSent(String input) {
        fragmentCelsius.updateCelsius(input, 'f');
        fragmentKelvin.updateKelvin(input, 'f');
    }

    @Override
    public void onInputCelsiusSent(String input) {
        fragmentFahrenheit.updateFahrenheit(input, 'c');
        fragmentKelvin.updateKelvin(input, 'c');
    }

    @Override
    public void onInputKelvinSent(String input){
        fragmentCelsius.updateCelsius(input, 'k');
        fragmentFahrenheit.updateFahrenheit(input, 'k');
    }
}