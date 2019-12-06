/*

Author: ibrsrm@gmail.com
Task score : 94%
ref: https://app.codility.com/programmers/challenges/krypton2018/

 */

class Solution {

    public class Node {
        /* Node Values */
        public int prime2Count;
        public int prime5Count;
        boolean pathExits;

        /* Cumulative min values */
        private int min2 = Integer.MAX_VALUE;
        private int min5 = Integer.MAX_VALUE;
        private int min2ConditionMin5 = Integer.MAX_VALUE;
        private int min5ConditionMin2 = Integer.MAX_VALUE;

        public Node (int _prime2Count, int _prime5Count) {
            prime2Count = _prime2Count;
            prime5Count = _prime5Count;
            pathExits = true;
        }

        public boolean checkNode(int twoCount, int fiveCount) {
            boolean check = false;
            if (twoCount < min2) {
                min2 = twoCount;
                min5ConditionMin2 = fiveCount;
                check = true;
            } else if (twoCount == min2) {
                if (min5ConditionMin2 > fiveCount) {
                    min5ConditionMin2 = fiveCount;
                    check = true;
                }
            }
            if (fiveCount < min5) {
                min5 = fiveCount;
                min2ConditionMin5 = twoCount;
                check = true;
            } else if (fiveCount == min5) {
                if (min2ConditionMin5 > twoCount) {
                    min2ConditionMin5 = twoCount;
                    check = true;
                }
            }
            return check;
        }

        public int minPrime () {
            return (min2 < min5) ? min2 : min5;
        }

    }

    public Node factorize (int value) {
        int temp = value;
        int prime2Count = 0;
        int prime5Count = 0;
        while ((temp & 0x1) == 0) {
            prime2Count++;
            temp /= 2;
        }
        while (temp % 5 == 0) {
            prime5Count++;
            temp /= 5;
        }
        return new Node(prime2Count, prime5Count);
    }

    public int traversePath(int[][] A, int length, int startX, int startY, int prime2Count, int prime5Count) {
        if (A[startX][startY] == 0) {
            return -1;
        }
        int minPrime = Math.min(prime2Count, prime5Count);
        Node node = nodes[startX][startY];
        if (node.checkNode(prime2Count, prime5Count) == false) {
            return -1;
        }

        if (startY < length && startX < length) {
            int prime2LeftCount = 0, prime5LeftCount = 0;
            int prime2RightCount = 0, prime5RightCount = 0;
            int minRight, minLeft;

            if (A[startX][startY + 1] == 0) {
                minRight = 1;
            } else {
                prime2RightCount = prime2Count + nodes[startX][startY + 1].prime2Count;
                prime5RightCount = prime5Count + nodes[startX][startY + 1].prime5Count;
                minRight = Math.min(prime2RightCount, prime5RightCount);
            }

            if (A[startX + 1][startY] == 0) {
                minLeft = 1;
            } else {
                prime2LeftCount = prime2Count + nodes[startX + 1][startY].prime2Count;
                prime5LeftCount = prime5Count + nodes[startX + 1][startY].prime5Count;
                minLeft = Math.min(prime2LeftCount, prime5LeftCount);
            }

            if (minLeft <= minRight) {
                traversePath(A, length, startX + 1, startY, prime2LeftCount, prime5LeftCount);
                if (min <= Math.min(prime2RightCount, prime5RightCount)) {
                    return 0;
                }
                traversePath(A, length, startX, startY + 1, prime2RightCount, prime5RightCount);
            } else {
                traversePath(A, length, startX, startY + 1, prime2RightCount, prime5RightCount);
                if (min <= Math.min(prime2LeftCount, prime5LeftCount)) {
                    return 0;
                }
                traversePath(A, length, startX + 1, startY, prime2LeftCount, prime5LeftCount);
            }
        } else if (startY < length) {
            int prime2RightCount, prime5RightCount;
            prime2RightCount = prime2Count + nodes[startX][startY + 1].prime2Count;
            prime5RightCount = prime5Count + nodes[startX][startY + 1].prime5Count;
            traversePath(A, length, startX, startY + 1, prime2RightCount, prime5RightCount);
        } else if (startX < length) {
            int prime2LeftCount, prime5LeftCount;
            prime2LeftCount = prime2Count + nodes[startX + 1][startY].prime2Count;
            prime5LeftCount = prime5Count + nodes[startX + 1][startY].prime5Count;
            traversePath(A, length, startX + 1, startY, prime2LeftCount, prime5LeftCount);
        } else {
            // startY == length && startX == length
            min = Math.min(minPrime, min);
            return 0;
        }
        return 0;
    }

    Node [][] nodes;
    int min = Integer.MAX_VALUE;

    public int solution(int[][] A) {
        int size = A.length;
        if (A[0][0] == 0 || A[size - 1][size - 1] == 0) {
            return 1;
        }
        nodes = new Node[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (A[i][j] == 0) {
                    min = 1;
                } else {
                    nodes[i][j] = factorize(A[i][j]);
                }
            }
        }
        if (size == 1) {
            return Math.min(nodes[0][0].prime2Count, nodes[0][0].prime5Count);
        }
        traversePath(A, size - 1, 0, 0, nodes[0][0].prime2Count, nodes[0][0].prime5Count);
        return min;
    }
}