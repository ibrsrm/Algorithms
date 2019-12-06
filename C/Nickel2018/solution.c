/*

Author: ibrsrm@gmail.com
Task score : 100%
ref : https://app.codility.com/programmers/challenges/nickel2018/

*/

#define MAX 1000000000

unsigned long long calculateZeros (int consecutive) {
    unsigned long long result = consecutive;
    result = (result * (consecutive + 1)) / 2;
    return result;
}

int solution(int P[], int N) {
    int consecutive = 0;
    unsigned long long total = N;
    total = (total * (N + 1)) / 2;

    for (int i = 0; i < N; i++) {
        if (P[i] == 0) {
            consecutive++;
            continue;
        }
        if (consecutive != 0) {
            total -= calculateZeros(consecutive);
            consecutive = 0;
        }
    }
    if (consecutive != 0) {
        total -= calculateZeros(consecutive);
    }
    return (total > MAX) ? MAX: total;
}

