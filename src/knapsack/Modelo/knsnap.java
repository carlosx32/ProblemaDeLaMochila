/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack.Modelo;

/**
 *
 * @author carandy
 */
public class knsnap {

    void knapSack01(int maxWeight, boolean[] pack,
            int[] weight, int[] value) {
        int n = pack.length - 1;
        boolean[][] trial = new boolean[maxWeight + 1][n + 1];
        int[] bestVal = new int[maxWeight + 1];
        int wt, k;

        for (wt = 1; wt <= maxWeight; wt++) {
            int bestK = 0, testWt;

            // Initial guess:  the knapsack for wt-1.
            bestVal[wt] = bestVal[wt - 1];
            for (k = 1; k <= n; k++) {
                testWt = wt - weight[k];
                if (testWt >= 0 && !trial[testWt][k]) {
                    if (bestVal[wt] < value[k] + bestVal[testWt]) {
                        bestK = k;
                        bestVal[wt] = value[k] + bestVal[testWt];
                    }
                }
            }
            if (bestK > 0) {
                testWt = wt - weight[bestK];
                System.arraycopy(trial[testWt], 0, trial[wt], 0, n + 1);
                trial[wt][bestK] = true;
            } else // Can't get here, so finish using the wt-1 solution
            {
                System.arraycopy(trial[wt - 1], 0, trial[wt], 0, n + 1);
            }
        }
        System.arraycopy(trial[maxWeight], 0, pack, 0, n + 1);
    }
    
    void showKnapSack(int maxWeight, boolean[] pack,
            int[] weight, int[] value) {
        int sumWeight = 0, sumValue = 0,
                n = pack.length - 1;

        System.out.println("Optimal knapsack for " + maxWeight);
        for (int k = 1; k <= n; k++) {
            if (pack[k]) {
                sumWeight += weight[k];
                sumValue += value[k];
                System.out.println("(" + weight[k] + ", " + value[k] + ")");
            }
        }
        System.out.println("Total weight:  " + sumWeight);
        System.out.println("Total value:   " + sumValue);
    }

}
