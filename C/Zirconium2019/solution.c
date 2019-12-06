/*

Author: ibrsrm@gmail.com
Task score : 100%
https://app.codility.com/programmers/challenges/zirconium2019/

*/

#define MAX_LENGTH 1000

struct bucket {
    int suma;
    int sumb;
    int count;
};

int solution(int A[], int B[], int N, int F) {
    int i, diff, max = 0;
    struct bucket recordA[MAX_LENGTH + 1];
    struct bucket recordB[MAX_LENGTH];
    memset(recordA, 0, sizeof(struct bucket) * (MAX_LENGTH + 1));
    memset(recordB, 0, sizeof(struct bucket) * (MAX_LENGTH));

    for (i = 0; i < N; i++) {
        diff = A[i] - B[i];
        if (diff >= 0) {
            recordA[diff].suma += A[i];
            recordA[diff].sumb += B[i];
            recordA[diff].count++;
        } else {
            diff *= (-1);
            recordB[diff - 1].suma += A[i];
            recordB[diff - 1].sumb += B[i];
            recordB[diff - 1].count++;
        }
    }
    for (i = MAX_LENGTH; i >= 0; i--) {
        if (recordA[i].count <= 0) {
            continue;
        }
        if (F > 0) {
            if (recordA[i].count <= F) {
                max += recordA[i].suma;
                F -= recordA[i].count;
            } else {
                diff = recordA[i].suma - recordA[i].sumb;
                diff /= recordA[i].count;
                max += (recordA[i].sumb + (diff * F));
                F = 0;
            }
        } else {
            max += recordA[i].sumb;
        }
    }
    for (i = 0; i < MAX_LENGTH; i++) {
        if (recordB[i].count <= 0) {
            continue;
        }
        if (F > 0) {
            if (recordB[i].count <= F) {
                max += recordB[i].suma;
                F -= recordB[i].count;
            } else {
                diff = recordB[i].sumb - recordB[i].suma;
                diff /= recordB[i].count;
                max += (recordB[i].sumb - (diff * F));
                F = 0;
            }
        } else {
            max += recordB[i].sumb;
        }
    }
    return max;
}
