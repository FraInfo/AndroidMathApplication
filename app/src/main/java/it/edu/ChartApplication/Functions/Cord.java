package it.edu.ChartApplication.Functions;

import com.github.mikephil.charting.data.Entry;
import com.itis.libs.parserng.android.expressParser.MathExpression;

import java.util.ArrayList;

public class Cord {

    String function;
    static final double eps = 0.00001;
    static final long JMAX = 1000;

    public Cord (String function){
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

    public double Kc (double Xi, double Xa, double Xb)
    {
        double lim, cond;
        cond = solveFunction(Xi) * solveFunction(Xa);
        if (cond < 0) lim = Xa;
        else lim = Xb;
        return (solveFunction(Xi) - solveFunction(lim)) / (Xi - lim);
    }

    public double getCord (double a, double b) {
        int i;
        boolean end = false;
        double Fa, Fb, Xi = 0, Fi, Xold, sc;
        if (a > b )
        {
            sc = a;
            a = b;
            b = sc;
        }
        Fa = solveFunction(a);
        Fb = solveFunction(b);
        if (Fa * Fb >= 0) return -1;
        Xold = (a + b) * 0.5;

        for (i=0; i<JMAX && !end; i++) {
            Xi = -solveFunction(Xold) / Kc(Xold,a,b) + Xold;
            Fi = solveFunction(Xi);
            if ((Math.abs(Xi - Xold) < eps) || (Fi==0)) end = true;
            else Xold = Xi; }
        if (end) return Xi;
        else return -1;
    }
}