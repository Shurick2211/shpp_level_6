package com.shpp.p2p.cs.onimko.assignment6.tm;

import java.util.Arrays;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        for (int ii = 0; ii < result.length; ii++)
            for (int i = 0; i < toneMatrix.length; i++) {
                if (toneMatrix[i][column] == true)
                   result[ii] += samples[i][ii];
            }
        normalization(result);
        return result;
    }

    /**
     * Method normalisations array of according sound
     * @param input the input array
     */
    private static void normalization(double[] input) {
        double min = Arrays.stream(input).min().getAsDouble();
        double max = Arrays.stream(input).max().getAsDouble();
        double maxDiv = -min > max ? min : max;
        if (maxDiv == 0.0) maxDiv = 1.0;
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i] / maxDiv;
            if (input[i] < -1.0) input[i] = -1.0;
            if (input[i] > 1.0) input[i] = 1.0;
        }
    }
}
