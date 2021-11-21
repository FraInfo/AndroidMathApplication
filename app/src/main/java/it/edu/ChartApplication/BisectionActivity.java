package it.edu.ChartApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import it.edu.ChartApplication.Functions.Bisection;

public class BisectionActivity extends AppCompatActivity {

    private CombinedChart combinedChart;
    private EditText editTextFunctionA;
    private Switch switchAnimate;
    public TextView textViewBisection;
    private EditText _editTextA;
    private EditText _editTextB;
    private EditText _editTextA2;
    private EditText _editTextB2;

    boolean animate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisection);

        combinedChart = findViewById(R.id.combined_chart);
        editTextFunctionA = findViewById(R.id.editTextFunctionA);
        textViewBisection = findViewById(R.id.textViewBisection);
        _editTextA = findViewById(R.id.editTextIntervalA);
        _editTextB = findViewById(R.id.editTextIntervalB);
        _editTextA2 = findViewById(R.id.editTextIntervalA2);
        _editTextB2 = findViewById(R.id.editTextIntervalB2);
        switchAnimate = findViewById(R.id.switchAnimate);
    }



    public void sendValueCalc(View view) {
        drawFunction();
        combinedChart.setVisibility(View.VISIBLE);
    }

    public void drawFunction() {
        try {
            String function = "x=0;" + editTextFunctionA.getText();

            Bisection bisection = new Bisection(function);

            float _a = Float.parseFloat(String.valueOf(_editTextA.getText()));
            float _b = Float.parseFloat(String.valueOf(_editTextB.getText()));
            float a = Float.parseFloat(String.valueOf(_editTextA2.getText()));
            float b = Float.parseFloat(String.valueOf(_editTextB2.getText()));

            bisection.drawFunction(_a, _b, function);
            textViewBisection.setText("Zero of function: " + Float.parseFloat(String.valueOf(Double.valueOf(bisection.getBisection(a,b)))));

            LineDataSet lineDataSet = new LineDataSet(bisection.getLineEntries(), "Function");
            LineData lineData = new LineData (lineDataSet);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setLineWidth(2f);
            CombinedData combinedData = new CombinedData();
            combinedData.setData(lineData);
            combinedChart.getDescription().setText("Bisection");
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