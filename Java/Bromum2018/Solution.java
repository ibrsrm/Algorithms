/*

Author: ibrsrm@gmail.com
Task score : 100%
ref : https://app.codility.com/programmers/challenges/bromum2018/

*/

import java.util.*;

public class Solution {

    public int solution(int N, int Q, int[] B, int[] C) {
        if (Q == 1) {
            return 0;
        }
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        for (int i = 0; i < B.length; i++) {
            long number = ((long) B[i]) << 32 | C[i];
            if (map.containsKey(number) == false) {
                map.put(number, 1);
                continue;
            }
            int occurence = map.get(number) + 1;
            if (occurence == Q) {
                return i;
            }
            map.put(number, occurence);
        }
        return -1;
    }
}
