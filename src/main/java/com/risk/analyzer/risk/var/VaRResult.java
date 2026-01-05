package com.risk.analyzer.risk.var;

class VaRResult {
    private double value;          // The VaR number
    private double confidenceLevel;  // 0.95 or 0.99
    private int timeHorizon;      // Days (1, 10, etc)
    private String method;        // "Historical", "Parametric", "MonteCarlo"

    public VaRResult(double value, double confidenceLevel, int timeHorizon, String method)
    {
        this.value = value;
        this.confidenceLevel = confidenceLevel;
        this.timeHorizon = timeHorizon;
        this.method = method;
    }

    // Getters
    public double getValue()
    {
        return this.value;
    }
    public double getConfidenceLevel()
    {
        return this.confidenceLevel;
    }
    public int getTimeHorizon()
    {
        return this.timeHorizon;
    }
    public String getMethod()
    {
        return this.method;
    }

    @Override
    public String toString() {
        return String.format("VaR[%s]: $%.2f (%.1f%% confidence, %d-day horizon)",
                method, value, confidenceLevel * 100, timeHorizon);
    }
}

