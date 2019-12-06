/*

Author: ibrsrm@gmail.com
Task score : 100%
ref : https://app.codility.com/programmers/challenges/cuprum2018/

*/

import java.util.HashMap;

public class Solution {

    private final int LETTER_MAX = 26;
    private final int NUMBER_MAX = 9;

    public long insertChar(char ch, long check) {
        int index = 0;
        if (ch <= '9' && ch >= '0') {
            index = LETTER_MAX + ch - '0';
        } else {
            index = ch - 'a';
        }
        if (index < 0 || index > (LETTER_MAX + NUMBER_MAX)) {
            return -1;
        }
        check = check ^ ((long) 1 << index);
        return check;
    }

    public int solution(String S) {
        int max = 0;
        int position = 0;
        long check = 0;
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();

        map.put(check, position);
        int length = S.length();
        for (int i = 0; i < length; i++) {
            check = insertChar(S.charAt(i), check);
            if (check == -1) {
                return 0;
            }
            if (map.containsKey(check)) {
                int oldPos = map.get(check);
                max = Math.max(max, (i + 1 - oldPos));
            } else {
                map.put(check, (i + 1));
            }
        }
        return max;
    }

}
