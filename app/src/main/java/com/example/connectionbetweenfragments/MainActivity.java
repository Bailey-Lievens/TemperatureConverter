package com.example.connectionbetweenfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
    implements FragmentCelsius.FragmentCelsiusListener, FragmentFahrenheit.FragmentFahrenheitListener {

    private FragmentCelsius fragmentCelsius;
    private FragmentFahrenheit fragmentFahrenheit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentCelsius = new FragmentCelsius();
        fragmentFahrenheit = new FragmentFahrenheit();

        getSupportFragmentManager().beginTransaction()
            .replace(R.id.layoutTop, fragmentCelsius)
            .replace(R.id.layoutBottom, fragmentFahrenheit)
            .commit();
    }

    @Override
    public void onInputFahrenheitSent(String input) {
        fragmentCelsius.updateCelsius(input);
    }

    @Override
    public void onInputCelsiusSent(String input) {
        fragmentFahrenheit.updateFahrenheit(input);
    }
}