package it.edu.ChartApplication.Functions;

import com.github.mikephil.charting.data.Entry;
import com.itis.libs.parserng.android.expressParser.MathExpression;

import java.util.ArrayList;

public class SubtendedArea {

    float yMax;
    private ArrayList<Entry> lineEntries = new ArrayList<>();
    private ArrayList<Entry> scatterEntries = new ArrayList<>();

    public ArrayList<Entry> getLineEntries() {
        return lineEntries;
    }
    public ArrayList<Entry> getScatterEntries() {
        return scatterEntries;
    }

    public void drawFunction(float xMin, float xMax, boolean y, String function){
        MathExpression mathExpression = new MathExpression(function);
        for (float i = xMin; i < xMax; i += .1){
            mathExpression.setValue("x", String.valueOf(i));
            if (y){
                lineEntries.add(new Entry(i, Math.abs(Float.parseFloat(mathExpression.solve()))));
            } else {
                lineEntries.add(new Entry(i, Float.parseFloat(mathExpression.solve())));
            }
        }
    }

    float findMax (float a, float b, String function){
        MathExpression mathExpression = new MathExpression(function);
        mathExpression.setValue("x", String.valueOf(a));

        yMax = Float.parseFloat(mathExpression.solve());

        for (float i = a; i < b; i += .1){
            mathExpression.setValue("x", String.valueOf(i));
            yMax = Float.parseFloat(mathExpression.solve()) > yMax ? Float.parseFloat(mathExpression.solve()) : yMax;
        }
        return yMax;
    }

    public float randomPointX (float a, float b){
        return (float) Math.random() * (b - a) + a;
    }

    public float randomPointY (){
        return (float) Math.random() * (yMax);
    }

    public void drawPoint (float a, float b, int n, String function){
        MathExpression mathExpression = new MathExpression(function);

        for (;n-->0;){
            float x = randomPointX(a,b);
            float y = randomPointY();

            mathExpression.setValue("x", String.valueOf(x));

            if ( y <= Math.abs(Float.parseFloat(mathExpression.solve()))){
                scatterEntries.add(new Entry(x,y));
            }
        }
    }

    public void getChart (float a, float b, int n, boolean y, String function){
        findMax(a,b,function);
        drawFunction(a,b,y,function);
        drawPoint(a,b,n,function);
    }

}