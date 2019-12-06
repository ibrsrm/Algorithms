/*

Author: ibrsrm@gmail.com
Task score : 100%
ref: https://app.codility.com/programmers/challenges/strontium2019/

*/

public class Solution {
    private final static int LETTER_COUNT = 26;

    public class Max2 {
        public int[] pos;
        public int[] max;

        Max2() {
            pos = new int[2];
            max = new int[2];
        }
    }

    private int searchLeft(String s) {
        int leftCount = 1;
        int length = s.length();
        for (int i = 1; i < length; i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                break;
            }
            leftCount++;
        }
        return leftCount;
    }

    private int searchRight(String s) {
        int rightCount = 1;
        int length = s.length();
        for (int i = length - 2; i >= 0; i--) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                break;
            }
            rightCount++;
        }
        return rightCount;
    }

    private int searchMiddle(String s, int start, int end) {
        int max = 1, temp = 1;
        for (int i = start + 1; i < end; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                temp++;
            } else {
                max = Math.max(max, temp);
                temp = 1;
            }
        }
        max = Math.max(max, temp);
        return max;
    }

    public int solution(String[] words) {
        int max_middle = 0;
        int[] single = new int[LETTER_COUNT];
        Max2[] left = new Max2[LETTER_COUNT];
        Max2[] right = new Max2[LETTER_COUNT];

        for (int i = 0; i < LETTER_COUNT; i++) {
            left[i] = new Max2();
            right[i] = new Max2();
        }
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            int length = s.length();
            int leftCount = searchLeft(s);
            if (leftCount == length) {
                int pos = s.charAt(0) - 'a';
                single[pos] += length;
                continue;
            }
            int rightCount = searchRight(s);
            if (leftCount + rightCount < length) {
                int temp = searchMiddle(s, leftCount, length - rightCount);
                max_middle = Math.max(max_middle, temp);
            }
            int leftPos = s.charAt(0) - 'a';
            if (leftCount > left[leftPos].max[0]) {
                left[leftPos].max[1] = left[leftPos].max[0];
                left[leftPos].pos[1] = left[leftPos].pos[0];
                left[leftPos].max[0] = leftCount;
                left[leftPos].pos[0] = i;
            } else if (leftCount > left[leftPos].max[1]) {
                left[leftPos].max[1] = leftCount;
                left[leftPos].pos[1] = i;
            }
            int rightPos = s.charAt(length - 1) - 'a';
            if (rightCount > right[rightPos].max[0]) {
                right[rightPos].max[1] = right[rightPos].max[0];
                right[rightPos].pos[1] = right[rightPos].pos[0];
                right[rightPos].max[0] = rightCount;
                right[rightPos].pos[0] = i;
            } else if (rightCount > right[rightPos].max[1]) {
                right[rightPos].max[1] = rightCount;
                right[rightPos].pos[1] = i;
            }
        }

        int max = 0;
        for (int i = 0; i < LETTER_COUNT; i++) {
            int total = single[i];
            if (left[i].pos[0] != right[i].pos[0]) {
                total += left[i].max[0];
                total += right[i].max[0];
            } else {
                int n1 = left[i].max[0] + right[i].max[1];
                int n2 = left[i].max[1] + right[i].max[0];
                total += Math.max(n1, n2);
            }
            max = Math.max(max, total);
        }
        max = Math.max(max, max_middle);
        return max;
    }
}
