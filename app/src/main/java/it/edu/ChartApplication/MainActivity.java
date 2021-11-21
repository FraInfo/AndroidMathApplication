package it.edu.ChartApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.SubtendedAreaButton);

    }

    public void onClickButtonBisection(View view) {
        startActivity(new Intent(getApplicationContext(), BisectionActivity.class));
    }

    public void onClickButtonNewtonRaphson(View view) {
        startActivity(new Intent(getApplicationContext(), NewtonRaphsonActivity.class));
    }

    public void onClickButtonSecant(View view) {
        startActivity(new Intent(getApplicationContext(), SecantActivity.class));
    }

    public void onClickButtonCord(View view) {
        startActivity(new Intent(getApplicationContext(), CordActivity.class));
    }

    public void onClickButtonSubtendedArea(View view) {
        startActivity(new Intent(getApplicationContext(), SubtendedAreaActivity.class));
    }

}