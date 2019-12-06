/*

Author: ibrsrm@gmail.com
Task score : 100%
ref: https://app.codility.com/programmers/challenges/digital_gold/

*/

public class Solution {

    private int calculateDirection(int[] A, int length, int per_gold_count) {
        int sum = 0, count = 0;
        for (int i = 0; i < length; i++) {
            sum += A[i];
            if (sum > per_gold_count) {
                break;
            }
            if (sum == per_gold_count) {
                count++;
            }
        }
        return count;
    }

    public int solution(int N, int M, int[] X, int[] Y) {
        int row_count = X.length;
        int column_count = Y.length;
        if (row_count % 2 != 0) {
            return 0;
        }
        int per_gold_count = row_count / 2;

        int[] row = new int[N];
        int[] column = new int[M];
        for (int i = 0; i < row_count; i++) {
            row[X[i]]++;
        }
        for (int i = 0; i < column_count; i++) {
            column[Y[i]]++;
        }

        int horizontal = calculateDirection(row, N, per_gold_count);
        int vertical = calculateDirection(column, M, per_gold_count);
        return horizontal + vertical;
    }

}
