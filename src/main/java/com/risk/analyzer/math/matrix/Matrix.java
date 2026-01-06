package com.risk.analyzer.math.matrix;

public class Matrix {
    double[][] matrix;

    public Matrix(double[][] data) {
        this.matrix  = data;
    }

    public int getRows() {
        return this.matrix.length;
    }

    public int getCols() {
        return this.matrix[0].length;
    }

    public double get(int i, int j) {
        return this.matrix[i][j];
    }

    public void set(int i, int j, double value) {
        this.matrix[i][j] = value;
    }

    public double[][] getData() {
        return matrix;
    }

    public Matrix multiply(Matrix other) {
        if (this.getCols() != other.getRows()) {
            throw new IllegalArgumentException("Matrices are not compatible for multiplication");
        }

        int rows = this.getRows();
        int cols = other.getCols();
        int common = this.getCols();

        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double sum = 0.0;
                for (int k = 0; k < common; k++) {
                    sum += this.get(i, k) * other.get(k, j);
                }
                result[i][j] = sum;
            }
        }

        return new Matrix(result);
    }

    public Matrix transpose() {
        int rows = this.getRows();
        int cols = this.getCols();

        double[][] result = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = this.get(i, j);
            }
        }

        return new Matrix(result);
    }

    public static Matrix identity(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }

        double[][] data = new double[n][n];

        for (int i = 0; i < n; i++) {
            data[i][i] = 1.0;
        }

        return new Matrix(data);
    }

    public Matrix add(Matrix other) {
        if (this.getRows() != other.getRows() || this.getCols() != other.getCols()) {
            throw new IllegalArgumentException("Matrices must have same dimensions");
        }

        int rows = this.getRows();
        int cols = this.getCols();

        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = this.get(i, j) + other.get(i, j);
            }
        }

        return new Matrix(result);
    }

    public Matrix scale(double scalar) {
        int rows = this.getRows();
        int cols = this.getCols();

        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = this.get(i, j) * scalar;
            }
        }

        return new Matrix(result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getRows(); i++) {
            sb.append("[");
            for (int j = 0; j < getCols(); j++) {
                sb.append(String.format("%.2f", get(i, j)));
                if (j < getCols() - 1) sb.append(", ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
