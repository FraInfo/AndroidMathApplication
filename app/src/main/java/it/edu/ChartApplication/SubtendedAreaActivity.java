package it.edu.ChartApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;


import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;

import it.edu.ChartApplication.Functions.SubtendedArea;

public class SubtendedAreaActivity extends AppCompatActivity {

    private CombinedChart combinedChart;
    private EditText editTextFunctionA;
    private Switch switchAnimate;
    private Switch switchABS;
    private EditText editTextPoint;
    private EditText _editTextA;
    private EditText _editTextB;

    boolean abs = false;
    boolean animate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtended_area);

        combinedChart = findViewById(R.id.combined_chart);
        editTextFunctionA = findViewById(R.id.editTextFunctionA);
        editTextPoint = findViewById(R.id.editTextNumberPoints);
        _editTextA = findViewById(R.id.editTextIntervalA);
        _editTextB = findViewById(R.id.editTextIntervalB);
        switchAnimate = findViewById(R.id.switchAnimate);
        switchABS = findViewById(R.id.switchABS);

    }

    public void sendValueCalc(View view) {
        drawFunction();
        combinedChart.setVisibility(View.VISIBLE);
    }

    public void drawFunction() {
        try {
            SubtendedArea subtendedArea = new SubtendedArea();

            float _a = Float.parseFloat(String.valueOf(_editTextA.getText()));
            float _b = Float.parseFloat(String.valueOf(_editTextB.getText()));

            String function = "x=0;" + editTextFunctionA.getText();

            int pointsNumber = Integer.parseInt(String.valueOf(editTextPoint.getText()));

            abs = switchABS.isChecked() ? true : false;
            if (abs) {
                subtendedArea.getChart(_a, _b, pointsNumber, true, function);
            } else {
                subtendedArea.getChart(_a, _b, pointsNumber, false, function);
            }

            LineDataSet lineDataSet = new LineDataSet(subtendedArea.getLineEntries(), "Function");
            LineData lineData = new LineData (lineDataSet);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setLineWidth(2f);
            ScatterDataSet scatterDataSet = new ScatterDataSet(subtendedArea.getScatterEntries(), "Points");
            scatterDataSet.setColor(Color.RED);
            scatterDataSet.setScatterShapeSize(2f);
            ScatterData scatterData = new ScatterData(scatterDataSet);
            CombinedData combinedData = new CombinedData();
            combinedData.setData(lineData);
            combinedData.setData(scatterData);
            combinedChart.getDescription().setText("Subtended area");
            combinedChart.setData(combinedData);
            combinedChart.invalidate();

            animate = switchAnimate.isChecked() ? true : false;

            if (animate){
                combinedChart.animateX(5000);
                combinedChart.animateY(5000);
            }

        } catch (Exception e) {
            Log.e("SubtendedAreaActivity","Exception trying to set up chart");
            e.printStackTrace();
        }
    }

}