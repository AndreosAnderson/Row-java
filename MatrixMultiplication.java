package org.example;

public class MatrixMultiplication {
	public void execute(double[][] A, double[][] B, double[][] C, int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				C[i][j] = 0;
				for (int k = 0; k < N; k++) {
					C[i][j] += A[i][k] * B[k][j];  // Correct row-major access
				}
			}
		}
	}
}
