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
    public double getValue(double value)
    {
        return this.value
    }
    public double getConfidenceLevel(double confidenceLevel)
    {
        return this.confidenceLevel;
    }
    public int getTimeHorizon(int timeHorizon)
    {
        return this.timeHorizon;
    }
    public String getMethod(String method)
    {
        return this.method;
    }

    @Override
    public String toString() {
        return String.format("VaR[%s]: $%.2f (%.1f%% confidence, %d-day horizon)",
                method, value, confidenceLevel * 100, timeHorizon);
    }
}

