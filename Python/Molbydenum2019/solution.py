

#Author: ibrsrm@gmail.com
#Task score : 100%
#ref: https://app.codility.com/programmers/challenges/molybdenum2019/


def checkEntry(length, number, counter, kcounter, result):
    count = (counter[number] + kcounter[number - 1] - kcounter[number])
    if count > (length / 2) :
        if number not in result :
            result.append(number)

def solution(K, M, A):
    length = len(A)
    s = set();
    result = []
    counter = [0] * (M + 2)
    kcounter = [0] * (M + 2)

    for i in range(length):
        counter[A[i]] = counter[A[i]] + 1
        if (counter[A[i]] + counter[A[i] - 1] > length / 2) :
            if (A[i] in s) == False :
                s.add(A[i])
        if (counter[A[i]] + counter[A[i] + 1] > length / 2) :
            if ((A[i] + 1) in s) == False :
                s.add(A[i] + 1);
                
    for i in range(K):
        kcounter[A[i]] = kcounter[A[i]] + 1
    for x in s:
        checkEntry(length, x, counter, kcounter, result)
    for i in range(K, length):
        kcounter[A[i]] = kcounter[A[i]] + 1
        kcounter[A[i - K]] = kcounter[A[i - K]] - 1
        for x in s:
            checkEntry(length, x, counter, kcounter, result)
            
    result.sort() 
    return result;

