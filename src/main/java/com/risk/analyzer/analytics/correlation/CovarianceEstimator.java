package com.risk.analyzer.analytics.correlation;

import com.risk.analyzer.math.matrix.Matrix;
import com.risk.analyzer.math.statistics.Statistics;

public class CovarianceEstimator {
    
    /**
     * Calculate covariance matrix from asset returns.
     * 
     * @param returns 2D array where returns[i] is the return series for asset i
     *                Example: returns[0] = [0.01, -0.02, 0.03] for Asset 1
     *                         returns[1] = [0.02, -0.01, 0.02] for Asset 2
     * @return Matrix representing the covariance matrix
     * 
     * Formula: Cov(i,j) = E[(X_i - μ_i)(X_j - μ_j)]
     * 
     * The diagonal contains variances (covariance with itself).
     * The matrix is symmetric: Cov(i,j) = Cov(j,i)
     */
    public static Matrix calculateCovarianceMatrix(double[][] returns) {
        if (returns == null || returns.length == 0) {
            throw new IllegalArgumentException("Returns array cannot be null or empty");
        }
        
        int numAssets = returns.length;
        int numPeriods = returns[0].length;
        
        for (int i = 0; i < numAssets; i++) {
            if (returns[i] == null || returns[i].length != numPeriods) {
                throw new IllegalArgumentException("All assets must have same number of return periods");
            }
        }
        
        double[][] covMatrix = new double[numAssets][numAssets];
        
        for (int i = 0; i < numAssets; i++) {
            for (int j = 0; j < numAssets; j++) {
                // Use Statistics.covariance() we already built
                covMatrix[i][j] = Statistics.covariance(returns[i], returns[j]);
            }
        }
        
        return new Matrix(covMatrix);
    }
    
    /**
     * Calculate correlation matrix from asset returns.
     * 
     * @param returns 2D array where returns[i] is the return series for asset i
     * @return Matrix representing the correlation matrix
     * 
     * Correlation is normalized covariance: Corr(i,j) = Cov(i,j) / (σ_i * σ_j)
     * 
     * Values range from -1 (perfect negative correlation) to +1 (perfect positive).
     * Diagonal is always 1 (asset perfectly correlated with itself).
     */
    public static Matrix calculateCorrelationMatrix(double[][] returns) {
        if (returns == null || returns.length == 0) {
            throw new IllegalArgumentException("Returns array cannot be null or empty");
        }
        
        int numAssets = returns.length;
        double[][] corrMatrix = new double[numAssets][numAssets];
        
        // Calculate correlation for each pair
        for (int i = 0; i < numAssets; i++) {
            for (int j = 0; j < numAssets; j++) {
                corrMatrix[i][j] = Statistics.correlation(returns[i], returns[j]);
            }
        }
        
        return new Matrix(corrMatrix);
    }
    
    /**
     * Calculate standard deviations for each asset.
     * 
     * @param returns 2D array where returns[i] is the return series for asset i
     * @return Array of standard deviations, one per asset
     * 
     * Standard deviation measures volatility (risk) of each asset individually.
     */
    public static double[] calculateStandardDeviations(double[][] returns) {
        if (returns == null || returns.length == 0) {
            throw new IllegalArgumentException("Returns array cannot be null or empty");
        }
        
        int numAssets = returns.length;
        double[] stdDevs = new double[numAssets];
        
        for (int i = 0; i < numAssets; i++) {
            stdDevs[i] = Statistics.standardDeviation(returns[i]);
        }
        
        return stdDevs;
    }
    
    /**
     * Calculate mean returns for each asset.
     * 
     * @param returns 2D array where returns[i] is the return series for asset i
     * @return Array of mean returns, one per asset
     */
    public static double[] calculateMeanReturns(double[][] returns) {
        if (returns == null || returns.length == 0) {
            throw new IllegalArgumentException("Returns array cannot be null or empty");
        }
        
        int numAssets = returns.length;
        double[] means = new double[numAssets];
        
        for (int i = 0; i < numAssets; i++) {
            means[i] = Statistics.mean(returns[i]);
        }
        
        return means;
    }
}
