/*

Author: ibrsrm@gmail.com
Task score : 100%
ref: https://app.codility.com/programmers/challenges/technetium2019/

*/

import java.util.*;

public class Solution {

    private HashSet<Integer> iterateChilds(int[][] A, int row, int column, HashSet<Integer> set, StringBuilder sb) {
        HashSet<Integer> childs = new HashSet<Integer>();
        int max = 0;
        int temp;
        int x, y, s;

        for (Integer p : set) {
            x = p >> 16;
            y = p & 0XFFFF;
            if (x < row) {
                temp = A[x + 1][y];
                if (temp == max) {
                    s = ((x + 1) << 16) | y;
                    if (childs.contains(s) == false) {
                        childs.add(s);
                    }
                } else if (temp > max) {
                    s = ((x + 1) << 16) | y;
                    childs.clear();
                    childs.add(s);
                    max = temp;
                }
            }
            if (y < column) {
                temp = A[x][y + 1];
                if (temp == max) {
                    s = (x << 16) | (y + 1);
                    if (childs.contains(s) == false) {
                        childs.add(s);
                    }
                } else if (temp > max) {
                    s = (x << 16) | (y + 1);
                    childs.clear();
                    childs.add(s);
                    max = temp;
                }
            }
        }
        sb.append(max);
        return childs;
    }

    public String solution(int[][] A) {
        int row = A.length - 1;
        int column = A[0].length - 1;

        StringBuilder sb = new StringBuilder();
        sb.append(A[0][0]);

        HashSet<Integer> set = new HashSet<Integer>();
        set.add(0);

        int end = (row << 16) | column;
        while (set.contains(end) == false) {
            set = iterateChilds(A, row, column, set, sb);
        }
        return sb.toString();
    }

}
