package it.edu.ChartApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;

import it.edu.ChartApplication.Functions.PiGreco;

public class PiGrecoActivity extends AppCompatActivity {

    private EditText pointsNumber;
    private TextView textPI;
    private ScatterChart scatterChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pi_greco);

        pointsNumber = findViewById(R.id.editTextNumberPoints);
        textPI = findViewById(R.id.textView17);
        scatterChart = findViewById(R.id.scatter_chart);

    }

    public void sendValue(View view) {
        try {
            scatterChart.setVisibility(View.VISIBLE);
            PiGreco piGreco = new PiGreco();
            textPI.setText("PI Greco value: " + (Double.valueOf(Double.valueOf(piGreco.calculatePI(insidePoint(), Integer.parseInt(String.valueOf(pointsNumber.getText())))))));
            scatterChart.invalidate();
            ScatterDataSet scatterDataSet = new ScatterDataSet(piGreco.getScatterEntries(), "Point");
            ScatterData scatterData = new ScatterData(scatterDataSet);
            scatterDataSet.setScatterShapeSize((float) 10);
            scatterChart.setScaleMinima(1f, 1f);
            YAxis yAxis = scatterChart.getAxisLeft();
            yAxis.setAxisMinimum(0f);
            YAxis YAxisRight = scatterChart.getAxisRight();
            YAxisRight.setAxisMinimum(0f);
            scatterChart.getXAxis().setAxisMinimum(0f);
            scatterChart.getXAxis().setAxisMaximum(1f);
            scatterChart.setData(scatterData);
            scatterChart.getDescription().setText("Monte Carlo method");
        } catch (Exception e) {
            Log.e("ChartActivity","Exception trying to set up chart");
            e.printStackTrace();
        }

    }

    public int insidePoint (){
        return Integer.parseInt(String.valueOf(pointsNumber.getText()));
    }

}