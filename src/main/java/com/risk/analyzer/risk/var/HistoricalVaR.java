package com.risk.analyzer.risk.var;

import java.util.Arrays;

class HistoricalVaR {
    public static VaRResult calculate(double[] returns, double portfolioValue, double confidenceLevel, int timeHorizon) {
        if (returns == null || returns.length == 0) {
            throw new IllegalArgumentException("returns should have a size > 0");
        }
        if (confidenceLevel <= 0 || confidenceLevel >= 1) {
            throw new IllegalArgumentException("confidenceLevel should be between 0 & 1");
        }

        double[] sorted = sortedCopy(returns);

        double alpha = 1.0 - confidenceLevel;
        int index = (int)(alpha * sorted.length);

        if (index < 0) {
            index = 0;
        }
        if (index >= sorted.length) {
            index = sorted.length - 1;
        }

        double percentileReturn = sorted[index];

        double scaledReturn = percentileReturn * Math.sqrt(timeHorizon);

        double var = Math.abs(scaledReturn) * portfolioValue;

        return new VaRResult(var, confidenceLevel, timeHorizon, "Historical");
    }

    public static VaRResult calculate(double[] returns, double portfolioValue, double confidenceLevel) {
        return calculate(returns, portfolioValue, confidenceLevel, 1);
    }

    private static double[] sortedCopy(double[] original) {
        double[] copy = original.clone();
        Arrays.sort(copy);
        return copy;
    }
}
