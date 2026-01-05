package com.risk.analyzer.math.statistics;

/**
 * Statistical calculations for financial time series.
 * Handles basic descriptive statistics needed for risk analysis.
 */

public class Statistics {
    public static double mean(double[] values) {
        int size = values.length;
        double sum = 0;

        for(double v : values) {
            sum += v;
        }

        return sum/size;
    }

    public static double variance(double[] values) {
        double mean = mean(values);
        double sum = 0;
        int size = values.length;

        for (double v : values) {
            sum += Math.pow(v - mean, 2);
        }

        return sum/size;

        // In further refinements will use Welford’s algorithm to ensure numerical stability, streaming capability, and future extensibility for financial risk calculations. 
    }

    public static double standardDeviation(double[] values) {
        return Math.sqrt(variance(values));
    }


    public static double covariance(double[] x, double[] y) {
        int sizeX = x.length;
        int sizeY = y.length;

        if (sizeX != sizeY) {
            throw new IllegalArgumentException("Array lengths must be equal");
        }

        double meanX = mean(x);
        double meanY = mean(y);
        double sum = 0;

        for (int index = 0; index < sizeX; index++) {
            sum += (x[index] - meanX)*(y[index] - meanY);
        }

        return sum/sizeX;
    }

    public static double correlation(double[] x, double[] y) {
        return covariance(x, y)/(standardDeviation(x) * standardDeviation(y));
    }
}

