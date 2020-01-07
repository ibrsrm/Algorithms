/*

Author: ibrsrm@gmail.com
Task score : 100%
ref : https://app.codility.com/programmers/challenges/ruthenium2019/

*/


#define MAX 100001

#include <string.h>
#include <stdbool.h>

struct range {
    int start;
    int end;
    int count;
};

int maxConsecutive(int A[], int N) {
    int max = 1, consecutive = 1;
    for (int i = 1; i < N; i++) {
        if (A[i] == A[i - 1]) {
            consecutive++;
        } else {
            max = (consecutive > max) ? consecutive : max;
            consecutive = 1;
        }
    }
    max = (consecutive > max) ? consecutive : max;
    return max;
}

int searchSubstring(int A[], int N, int K, struct range *bkt) {
    int firstPos = bkt->start, endPos = bkt->end + 1;
    if ((endPos - firstPos) <= (bkt->count + K)) {
        return bkt->count + K;
    }
    int max = 0, i, count = 0;
    int number = A[firstPos];

    /* Populate K items */
    for (i = 0; i < K; i++) {
        if (A[firstPos + i] == number) {
            count++;
        }
    }
    /* Populate count items that are not equal to number */
    i += firstPos;
    for (; i < endPos; i++) {
        if (A[i] != number) {
            if (count == 0) {
                break;
            }
            count--;
        }
    }
    max = (i - firstPos);

    while (i < endPos) {
        int j = 1;
        bool found = false;
        while ((firstPos + j) < endPos) {
            if (A[firstPos + j] == number && A[firstPos + j - 1] != number) {
                found = true;
                break;
            }
            if (A[firstPos + j] != number) {
                count++;
            }
            j++;
        }
        if (found == false) {
            break;
        }
        firstPos += j;
        for (; i < endPos; i++) {
            if (A[i] != number) {
                if (count == 0) {
                    break;
                }
                count--;
            }
        }
        int compare = i - firstPos;
        if (i == endPos && count > 0) {
            compare += count;
        }
        max = (compare > max) ? (compare) : max;
    }
    return max;
}

int solution(int A[], int N, int K) {
    if (K == 0) {
        return maxConsecutive(A, N);
    }
    if (K >= (N - 1)) {
        return N;
    }

    int max = K + 1;
    struct range * record;
    record = (struct range *) malloc(sizeof(struct range) * MAX);
    memset(record, 0, sizeof(struct range) * (MAX));

    for (int i = 0; i < N; i++) {
        /* calculate ranges */
        if (record[A[i]].count == 0) {
            record[A[i]].start = i;
            record[A[i]].count = 1;
        } else if (record[A[i]].count == 1) {
            int discontinuity = (i - record[A[i]].start) - 1;
            if (discontinuity > K) {
                record[A[i]].start = i;
                record[A[i]].count = 1;
            } else {
                record[A[i]].end = i;
                record[A[i]].count++;
            }
        } else {
            int discontinuity = (i - record[A[i]].end) - 1;
            if (discontinuity > K) {
		        record[A[i]].end = i;
                record[A[i]].count++;
		        int result = searchSubstring(A, N, K, &record[A[i]]);
                max = (result > max) ? result : max;
                record[A[i]].start = i;
                record[A[i]].count = 1;
            } else {
                record[A[i]].end = i;
                record[A[i]].count++;
            }
        }

        /* check for max */
        if ((record[A[i]].count + K) >= N) {
	        free(record);
            return N;
        }
    }

    for (int i = 0; i < MAX; i++) {
        if (record[i].count <= 1) {
	        continue;
	    }
        int difference = 1 + record[i].end - record[i].start;
        int total = record[i].count + K;
        if (difference <= total) {
            max = (total > max) ? total : max;
            continue;
        }
        if (max >= difference) {
            continue;
        }
        int result = searchSubstring(A, N, K, &record[i]);
        max = (result > max) ? result : max;
    }
    free(record);
    return max;
}