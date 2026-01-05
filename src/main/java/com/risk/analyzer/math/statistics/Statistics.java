package com.risk.analyzer.math.statistics;

/**
 * Statistical calculations for financial time series.
 * Handles basic descriptive statistics needed for risk analysis.
 */

public class Statistics {
    public static double mean(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int size = values.length;
        double sum = 0;

        for(double v : values) {
            sum += v;
        }

        return sum/size;
    }

    public static double variance(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
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
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return Math.sqrt(variance(values));
    }


    public static double covariance(double[] x, double[] y) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
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
        double stdX = standardDeviation(x);
        double stdY = standardDeviation(y);

        if (stdX == 0.0 || stdY == 0.0) {
            return 0.0;  // No correlation if no variance
        }

        return covariance(x, y) / (stdX * stdY);
    }
}

