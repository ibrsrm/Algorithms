/*

Author: ibrsrm@gmail.com
Task score : 100%
ref: https://app.codility.com/programmers/challenges/cutting_complexity2018/

*/

class Solution {

    public class Node {
        public Node next;
        public char data;

        public Node(char ch) {
            this.data = ch;
            this.next = null;
        }
    }

    private class CustomLinkedList {
        private Node head;
        private Node tail;
        private int mCount;
        private int count;
        private int size;
        private char lastRemoved;

        CustomLinkedList(int size) {
            this.head = null;
            this.tail = null;
            this.mCount = 0;
            this.count = 0;
            this.size = size;
            this.lastRemoved = 0;
        }

        public void insert(char ch) {
            /* Insert tail, remove head if size is bigger than K */
            Node node = new Node(ch);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            if (ch == 'M') {
                mCount++;
            }
            if (count < size) {
                count++;
                return;
            }
            if (head.data == 'M') {
                mCount--;
            }
            lastRemoved = head.data;
            head = head.next;
        }

        public int getmCount() {
            return mCount;
        }

        public char getLastRemoved() {
            return lastRemoved;
        }
    }

    public int getCharCount(String S, char ch) {
        int count = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            if (S.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public int getSubStringDividerCount(String S, int K) {
        int length = S.length();
        int count = 0, index = 0, consecutive = 0;
        while (index < length) {
            char ch = S.charAt(index);
            if (ch == 'M') {
                consecutive++;
            } else if (ch == 'L') {
                count += (consecutive / (K + 1));
                consecutive = 0;
            }
            index++;
        }
        count += (consecutive / (K + 1));
        return count;
    }

    public int solution(String S, int K) {
        int length = S.length();
        if (K == 0) {
            return getCharCount(S, 'M');
        }
        if (K == length) {
            return getCharCount(S, 'L');
        }
        int dividerCount = getSubStringDividerCount(S, K);
        if (dividerCount > 0) {
            return dividerCount;
        }

        int min_count = Integer.MAX_VALUE;
        CustomLinkedList list = new CustomLinkedList(K);
        for (int i = 0; i < length; i++) {
            char ch = S.charAt(i);
            list.insert(ch);
            if (i < (K - 1)) {
                continue;
            }
            int mCount = list.getmCount();
            char lastRemoved = list.getLastRemoved();
            char next = 0;
            if (i != (length - 1)) {
                next = S.charAt(i + 1);
            }
            int countToChange = K - mCount;
            if (lastRemoved == 'M') {
                countToChange++;
            }
            if (next == 'M') {
                countToChange++;
            }
            if (countToChange < min_count) {
                min_count = countToChange;
            }
        }
        return min_count;
    }

}