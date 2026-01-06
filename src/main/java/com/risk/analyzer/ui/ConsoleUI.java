package com.risk.analyzer.ui;

import com.risk.analyzer.risk.var.*;
import java.util.Scanner;

public class ConsoleUI {

    private static final double[][] RETURNS = {
        { 0.01, -0.02, 0.015, -0.01, 0.02 },
        { 0.008, -0.01, 0.012, -0.009, 0.018 },
        { 0.012, -0.015, 0.02, -0.013, 0.025 }
    };

    private static final double[] WEIGHTS = {0.4, 0.35, 0.25};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("        PORTFOLIO RISK ANALYZER");
        System.out.println("=========================================\n");

        System.out.print("Enter Portfolio Value (₹): ");
        double portfolioValue = sc.nextDouble();

        System.out.print("Enter Confidence Level (e.g., 0.95, 0.99): ");
        double confidence = sc.nextDouble();

        System.out.print("Enter Time Horizon (days): ");
        int timeHorizon = sc.nextInt();

        while (true) {
            System.out.println("\nChoose Risk Model:");
            System.out.println("1. Historical Value at Risk (VaR)");
            System.out.println("2. Gaussian (Parametric) VaR");
            System.out.println("3. Portfolio Gaussian VaR");
            System.out.println("4. Exit");

            System.out.print("Your choice: ");
            int choice = sc.nextInt();

            VaRResult result = null;

            if (choice == 1) {
                result = HistoricalVaR.calculate(
                        RETURNS[0], portfolioValue, confidence, timeHorizon
                );
            }
            else if (choice == 2) {
                result = GaussianVaR.calculate(
                        RETURNS[0], portfolioValue, confidence, timeHorizon
                );
            }
            else if (choice == 3) {
                result = PortfolioVaR.gaussianVaR(
                        RETURNS, WEIGHTS, portfolioValue, confidence, timeHorizon
                );
            }
            else if (choice == 4) {
                System.out.println("\nExiting Risk Analyzer. Thank you.");
                break;
            }
            else {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            printResult(result);
        }

        sc.close();
    }

    private static void printResult(VaRResult res) {
        System.out.println("\n-----------------------------------------");
        System.out.println("Portfolio Risk Result");
        System.out.println("-----------------------------------------");
        System.out.printf("Model            : %s%n", res.getMethod());
        System.out.printf("Confidence Level : %.1f %% %n", res.getConfidenceLevel() * 100);
        System.out.printf("Time Horizon     : %d days%n", res.getTimeHorizon());
        System.out.printf("Estimated VaR    : ₹%,.2f%n", res.getValue());
        System.out.println("-----------------------------------------");
    }
}

