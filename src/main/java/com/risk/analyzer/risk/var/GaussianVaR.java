package com.risk.analyzer.risk.var;

import com.risk.analyzer.math.statistics.Statistics;
import com.risk.analyzer.math.statistics.NormalDistribution;

public class GaussianVaR {

    public static VaRResult calculate(
            double[] returns,
            double portfolioValue,
            double confidenceLevel,
            int timeHorizon
    ) {
        if (returns == null || returns.length == 0) {
            throw new IllegalArgumentException("Returns cannot be null or empty");
        }

        double mean = Statistics.mean(returns);
        double std = Statistics.standardDeviation(returns);

        double z = NormalDistribution.inverseCDF(confidenceLevel);

        double scaledStd = std * Math.sqrt(timeHorizon);
        double var = Math.abs(mean - z * scaledStd) * portfolioValue;

        return new VaRResult(var, confidenceLevel, timeHorizon, "Gaussian");
    }

    public static VaRResult calculate(
            double[] returns,
            double portfolioValue,
            double confidenceLevel
    ) {
        return calculate(returns, portfolioValue, confidenceLevel, 1);
    }
}

