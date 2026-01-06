---

# Portfolio Risk Analyzer

## Overview

The **Portfolio Risk Analyzer** is a backend-focused analytical system that estimates the **potential downside risk of a financial portfolio**.
It uses statistical and probabilistic methods to estimate **worst-case losses** over a chosen time horizon based on historical return data.

The project emphasizes **core risk computation logic** rather than UI or visualization, reflecting how real-world risk engines are typically designed.

---

## Problem Statement

In financial and analytical systems, understanding **how much a portfolio could lose under adverse conditions** is critical.

This project answers questions such as:

* What is the maximum expected loss over the next *N* days?
* How does confidence level affect risk estimation?
* How does combining multiple assets change overall portfolio risk?

---

## Key Features

### Implemented Risk Models

* **Historical Value at Risk (VaR)**
  Estimates risk using empirical quantiles of historical returns without assuming any distribution.

* **Gaussian (Parametric) VaR**
  Estimates risk under a normal distribution assumption using volatility and confidence levels.

* **Portfolio Gaussian VaR**
  Extends parametric VaR to multi-asset portfolios using covariance matrices and asset weights.

### Time Horizon Support

* Supports **multi-day risk estimation** using square-root-of-time scaling.

### Flexible Confidence Levels

* Supports **arbitrary confidence levels** (e.g., 0.90, 0.95, 0.99) via inverse normal CDF.

### Interactive Console Interface

* Menu-driven CLI to:

  * enter portfolio value
  * choose confidence level and time horizon
  * select different VaR models
* Designed for clarity and demonstration purposes.

---

## Data Usage

* The current implementation uses **representative historical return samples** embedded in the program.
* The system operates on **returns (not prices)**, making it asset-agnostic.
* The architecture is designed so **real market data (CSV files or APIs)** can be integrated without modifying core risk logic.

---

## Project Structure

```
portfolio-risk-analyzer/
├── README.md
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── risk
    │               └── analyzer
    │                   ├── analytics
    │                   │   └── correlation
    │                   │       └── CovarianceEstimator.java
    │                   ├── math
    │                   │   ├── matrix
    │                   │   │   └── Matrix.java
    │                   │   └── statistics
    │                   │       ├── Statistics.java
    │                   │       └── NormalDistribution.java
    │                   ├── risk
    │                   │   └── var
    │                   │       ├── HistoricalVaR.java
    │                   │       ├── GaussianVaR.java
    │                   │       ├── PortfolioVaR.java
    │                   │       └── VaRResult.java
    │                   └── ui
    │                       └── ConsoleUI.java
    └── test
        └── java
            └── com
                └── risk
                    └── analyzer
                        ├── math
                        │   └── StatisticsTest.java
                        └── risk
                            └── var
                                └── HistoricalVaRTest.java
```

### Package Overview

* **math** – Statistical utilities and matrix operations
* **analytics** – Correlation and covariance estimation
* **risk** – Risk models and Value-at-Risk calculations
* **ui** – Console-based demonstration interface
* **test** – Unit tests for validating statistical and risk logic

Some packages are reserved for future extensions such as data ingestion and services.

---

## How to Run the Project

Compile all source files:

```bash
javac $(find src/main/java -name "*.java")
```

Run the console application:

```bash
java -cp src/main/java com.risk.analyzer.ui.ConsoleUI
```

Follow the on-screen prompts to compute portfolio risk estimates.

---

## Running Tests

Compile main source files:

```bash
javac $(find src/main/java -name "*.java")
```

Compile test files:

```bash
javac -cp src/main/java $(find src/test/java -name "*.java")
```

Run tests manually:

```bash
java -cp src/main/java:src/test/java com.risk.analyzer.math.StatisticsTest
```

```bash
java -cp src/main/java:src/test/java com.risk.analyzer.risk.var.HistoricalVaRTest
```

---

## Design Philosophy

* **Backend-first**: Focus on correctness of analytical logic
* **Modular architecture**: Easy to extend with new models or data sources
* **Minimal UI**: Console interface used to clearly demonstrate functionality

---

## Future Extensions

* Expected Shortfall (CVaR)
* Monte Carlo–based VaR
* Stress testing and scenario analysis
* Real market data ingestion (CSV / APIs)
* Graphical user interface (JavaFX)

---

## Summary

This project demonstrates a **foundational risk analysis engine** that estimates worst-case portfolio losses under uncertainty.
The emphasis is on **core analytical logic**, making the system suitable for further extension into more advanced financial risk applications.

---

