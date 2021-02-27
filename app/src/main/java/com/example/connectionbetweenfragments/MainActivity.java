package com.example.connectionbetweenfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
    implements FragmentTop.FragmentTopListener, FragmentBottom.FragmentBottomListener {

    private FragmentTop fragmentTop;
    private FragmentBottom fragmentBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentTop = new FragmentTop();
        fragmentBottom = new FragmentBottom();

        getSupportFragmentManager().beginTransaction()
            .replace(R.id.layoutTop, fragmentTop)
            .replace(R.id.layoutBottom, fragmentBottom)
            .commit();
    }

    @Override
    public void onInputBottomSent(String input) {
        fragmentTop.updateCelcius(input);
    }

    @Override
    public void onInputTopSent(String input) {
        fragmentBottom.updateFahrenheit(input);
    }
}