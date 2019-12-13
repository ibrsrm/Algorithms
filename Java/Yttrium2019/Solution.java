/*

Author: ibrsrm@gmail.com
Task score : 100%
ref: https://app.codility.com/programmers/challenges/yttrium2019/

*/

public class Solution {

    private static final int MAX_CHAR = 26;
    private Range[] ranges;

    private class Range {
        private int start;
        private int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

    }

    public int solution(String S, int K) {
        if (K == 0) {
            return S.length();
        }

        int min = Integer.MAX_VALUE;
        int character_count = 0;
        ranges = new Range[MAX_CHAR];

        /* First pass, find different char count, start and end indexes */
        for (int i = 0; i < S.length(); i++) {
            int index = S.charAt(i) - 'a';
            if (ranges[index] == null) {
                ranges[index] = new Range(i, i);
                character_count++;
            } else {
                ranges[index].setEnd(i);
            }
        }
        if (character_count < K) {
            return -1;
        }
        if (character_count == K) {
            return 0;
        }
        int character_remove = character_count - K;

        for (int i = 0; i < MAX_CHAR; i++) {
            if (ranges[i] == null) {
                continue;
            }
            int start_index = ranges[i].getStart();
            for (int j = 0; j < MAX_CHAR; j++) {
                if (ranges[j] == null) {
                    continue;
                }
                int end_index = ranges[j].getEnd();
                int count = 0;
                for (int k = 0; k < MAX_CHAR; k++) {
                    if (ranges[k] == null) {
                        continue;
                    }
                    if (start_index <= ranges[k].getStart() && ranges[k].getEnd() <= end_index) {
                        count++;
                    }
                }
                if (count == character_remove) {
                    int tmp = end_index - start_index + 1;
                    min = Math.min(min, tmp);
                }
            }
        }
        return min;
    }

}
