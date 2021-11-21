package it.edu.ChartApplication.Functions;

import com.github.mikephil.charting.data.Entry;
import com.itis.libs.parserng.android.expressParser.MathExpression;

import java.util.ArrayList;

public class Bisection {

    String function;
    static final double eps = 0.00001;
    static final long JMAX = 1000;

    public Bisection(String function){
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

    public double getBisection (double a, double b) {
        boolean end = false;

        double Xa = 0, Xb = 0, Fa, Fb, Xm = 0, Fm, sc;

        if (a > b) {
            sc = a;
            a = b;
            b = sc;
        }

        Fa = solveFunction(a);
        Fb = solveFunction(b);

        if (Fa * Fb >= 0) {
            return -1;
        } else {
            Xa = a;
            Xb = b;
        }

        for (int i = 0; i < JMAX && !end; i++) {

            Fa = solveFunction(Xa);
            Fb = solveFunction(Xb);

            Xm = (Xa + Xb) * 0.5;
            Fm = solveFunction(Xm);

            if (Fm * Fa >= 0) Xa = Xm;

            if (Xa != Xm) {
                Xb = Xm;
            }
            if ((Math.abs(Xb - Xa) < eps) || (Fm == 0)) {
                    end = true;
                }
                if (end) {
                    break;
                } else {
                    return -1;
                }
            }
            System.out.println(Xm);
            return Xm;
    }

}
