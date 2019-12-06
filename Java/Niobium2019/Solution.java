/*

Author: ibrsrm@gmail.com
Task score : 100%
ref: https://app.codility.com/programmers/challenges/niobium2019/

*/

import java.util.*;

class Solution {

    public int solution(int[][] A) {
        int max = 0;
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        int row = A.length;
        int column = A[0].length;
        if (row == 1) {
            return 1;
        }
        if (column == 1) {
            return row;
        }

        for (int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sbNot = new StringBuilder();
            for (int j = 0; j < column; j++) {
                sb.append(A[i][j]);
                sbNot.append(1 ^ A[i][j]);
            }
            int temp = 0;
            String s = sb.toString();
            String sNot = sbNot.toString();
            if (map.containsKey(s)) {
                temp = map.get(s);
                temp++;
            } else {
                temp = 1;
                map.put(s, temp);
            }
            map.put(s, temp);
            if (map.containsKey(sNot)) {
                temp += map.get(sNot);
            }
            max = Math.max(temp, max);
        }
        return max;
    }
}