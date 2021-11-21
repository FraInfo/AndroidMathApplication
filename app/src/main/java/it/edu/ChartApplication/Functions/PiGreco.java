package it.edu.ChartApplication.Functions;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Random;

public class PiGreco {

    public ArrayList<Entry> scatterEntries = new ArrayList<>();

    public ArrayList<Entry> getScatterEntries() {
        return scatterEntries;
    }

    public double findPI(int totalPoint) {
        double x, y;
        int insidePoint = 0;
        Random random = new Random();
        if ((totalPoint > 0) && (totalPoint < 50000))
            for (int i = 0; i < totalPoint; i++) {
                x = random.nextDouble();
                y = random.nextDouble();
                getEntries(x, y);
                if ((x * x + y * y) < 1) {
                    insidePoint++;
                }
            }
        else {
            totalPoint = 10000;
            findPI(totalPoint);
        }
        return calculatePI(insidePoint, totalPoint);
    }

    public double calculatePI(int insidePoint, int totalPoint) {
        double pi = (4 * insidePoint) / (double) totalPoint;
        return pi;
    }

    private void getEntries(double a, double b) {
        scatterEntries.add(new BarEntry((float) a, (float) b));

    }

}
