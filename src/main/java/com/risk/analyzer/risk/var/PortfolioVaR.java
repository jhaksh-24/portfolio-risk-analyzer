package com.risk.analyzer.risk.var;

import com.risk.analyzer.analytics.correlation.CovarianceEstimator;
import com.risk.analyzer.math.matrix.Matrix;
import com.risk.analyzer.math.statistics.NormalDistribution;

public class PortfolioVaR {

    public static VaRResult gaussianVaR(
            double[][] assetReturns,
            double[] weights,
            double portfolioValue,
            double confidenceLevel,
            int timeHorizon
    ) {
        if (assetReturns == null || weights == null) {
            throw new IllegalArgumentException("Inputs cannot be null");
        }

        if (assetReturns.length != weights.length) {
            throw new IllegalArgumentException("Weights must match number of assets");
        }

        Matrix cov = CovarianceEstimator.calculateCovarianceMatrix(assetReturns);

        Matrix w = new Matrix(new double[][] { weights }).transpose();
        Matrix wT = w.transpose();

        Matrix variance = wT.multiply(cov).multiply(w);
        double portfolioStd =
                Math.sqrt(variance.get(0, 0)) * Math.sqrt(timeHorizon);

        double z = NormalDistribution.inverseCDF(confidenceLevel);
        double var = z * portfolioStd * portfolioValue;

        return new VaRResult(var, confidenceLevel, timeHorizon, "Portfolio Gaussian");
    }
}

