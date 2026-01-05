package com.risk.analyzer.risk.var;

public class HistoricalVaRTest {
    public static void main(String[] args) {
        System.out.println("=== Testing Historical VaR ===\n");
        
        // Simple test: 20 returns
        double[] returns = {
            -0.08, -0.06, -0.05, -0.04, -0.03,  // 5 worst
            -0.02, -0.01, 0.00, 0.01, 0.02,      // middle
            0.03, 0.04, 0.05, 0.06, 0.07,        // better
            0.08, 0.09, 0.10, 0.11, 0.12         // best 5
        };
        
        double portfolioValue = 100000.0;  // $100k portfolio
        double confidence = 0.95;          // 95% confidence
        
        VaRResult result = HistoricalVaR.calculate(returns, portfolioValue, confidence);
        
        System.out.println(result);
        System.out.println("\nInterpretation: You could lose $" + String.format("%.2f", result.getValue()) 
                          + " in one day (with 5% probability of worse)");
        
        // Test with 10-day horizon
        VaRResult result10day = HistoricalVaR.calculate(returns, portfolioValue, confidence, 10);
        System.out.println("\n10-day VaR: " + result10day);
    }
}
