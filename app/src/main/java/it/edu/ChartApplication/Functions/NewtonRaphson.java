package it.edu.ChartApplication.Functions;

import com.github.mikephil.charting.data.Entry;
import com.itis.libs.parserng.android.expressParser.MathExpression;

import java.util.ArrayList;

public class NewtonRaphson {

    String function;
    static final double eps = 0.00001;
    static final long JMAX = 1000;

    public NewtonRaphson (String function){
        this.function = function;
    }

    private ArrayList<Entry> lineEntries = new ArrayList<>();

    public ArrayList<Entry> getLineEntries() {
        return lineEntries;
    }

    public void drawFunction(float xMin, float xMax, String function) {
        MathExpression mathExpression = new MathExpression(function);
        for (float i = xMin; i < xMax; i += .1) {
            mathExpression.setValue("x", String.valueOf(i));

            lineEntries.add(new Entry(i, Float.parseFloat(mathExpression.solve())));

        }
    }

    public double solveFunction (double a){
        MathExpression mathExpression = new MathExpression(function);
        mathExpression.setValue("x", String.valueOf(a));
        return Double.parseDouble(mathExpression.solve());
    }

    double Kt (double Xi) {
        double DELTA = 0.001;
        return (solveFunction(Xi + DELTA) - solveFunction(Xi)) / DELTA;}

    public double getMethod (double a, double b) {
        int i;
        boolean end=false;
        double Fa, Fb, Xi = 0, Fi, Xold, sc;

        if (a > b) {
            sc = a;
            a = b;
            b = sc;
        }

        Fa = solveFunction(a);
        Fb = solveFunction(b);

        if (Fa * Fb >= 0) {
            return -1;
        }

        Xold = (a + b) * 0.5;

        for (i=0; i < JMAX && !end; i++) {
            Xi = -solveFunction(Xold) / Kt (Xold) + Xold;
            Fi = solveFunction(Xi);

            if ((Math.abs(Xi - Xold) < eps) || (Fi == 0)) end=true;
            else Xold = Xi;
        }

        if (end) return Xi;
        else return -1;
    }
}
