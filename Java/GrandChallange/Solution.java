/*

Author: ibrsrm@gmail.com
Task score : 100%
ref: https://app.codility.com/programmers/challenges/grand2018/

*/

import java.util.*;

public class Solution {

    private int max = 0;

    private int searchSubstring(String S, int index) {
        char first = 0, second = 0;
        int firstOccurence = 0, secondOccurence = 0;
        int firstConsecutive = 0, secondConsecutive = 0;

        /* <DifferenceOccurence, Position> */
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, index);

        int length = S.length();
        for (int i = index; i < length; i++) {
            char ch = S.charAt(i);
            if (first == ch) {
                firstOccurence++;
                firstConsecutive++;
                secondConsecutive = 0;
            } else if (second == ch) {
                secondOccurence++;
                secondConsecutive++;
                firstConsecutive = 0;
            } else if (first == 0) {
                first = ch;
                firstOccurence = 1;
                firstConsecutive = 1;
                secondConsecutive = 0;
            } else if (second == 0) {
                second = ch;
                secondOccurence = 1;
                secondConsecutive = 1;
                firstConsecutive = 0;
            } else {
                if (firstConsecutive != 0) {
                    return i - firstConsecutive;
                } else {
                    return i - secondConsecutive;
                }
            }

            int position = i + 1;
            int key = firstOccurence - secondOccurence;
            if (map.containsKey(key)) {
                int old_position = map.get(key);
                int distance = position - old_position;
                max = Math.max(max, distance);
            } else {
                map.put(key, position);
            }
        }
        return -1;
    }

    public int solution(String S) {
        int index = 0;

        while (true) {
            index = searchSubstring(S, index);
            if (index == -1) {
                break;
            }
        }
        return max;
    }

}
