#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

void askUserData(int*);
int *createDinamicArray(int);

void fillArrWRandVar(int*,int);
void showArr(int*,int);

double insertSort(int*,int);
void showMsg(int);

void showTime(double);
void freeMemory(int*);


int main(int argc, char const *argv[])
{
    srand(time(NULL));
    int n, *arrElements;
    askUserData(&n);
    arrElements = createDinamicArray(n);
    fillArrWRandVar(arrElements,n);
    showArr(arrElements,n);
    double time_spent = insertSort(arrElements,n);
    showArr(arrElements,n);

    showTime(time_spent);

    freeMemory(arrElements);
    return 0;
}

void askUserData(int *n){
    printf("How many elements?: ");
    scanf("%d",n);
}

int *createDinamicArray(int n){
    int *array;
    array = (int*)malloc(sizeof(int)*n);
    if(array == NULL){
        showMsg(0);
        exit(-1);
    }
    return array;
}

void fillArrWRandVar(int *arr,int n){
    for (int i = 0; i < n; i++)
        arr[i] = rand()%100;
}

void showArr(int *arr, int n){
    int i, showN = n;

    if(n > 50) showN = 50;

    for (i = 0; i < showN; i++) {
        printf("Num. [%d]: %d\n", i+1, arr[i]);
    }
    printf("\n\n");
}


double insertSort(int *arr,int n){
    int j,i,key;
    double time_spent = 0.0;

    clock_t t0 = clock();

    for (j = 1; j < n; j++){
        i =j;
        key = arr[j];
        while (i > 0 && arr[i-1] > key){
            arr[i] = arr[i-1];
            i--;
        }
        arr[i] = key;
    }

    clock_t t1 = clock();
    time_spent+=(double)(t1-t0) / CLOCKS_PER_SEC;

    return time_spent;
}

void showTime(double time_spent){
    printf("The computing time it takes to the insertion algorithm is [%f]s\n",time_spent);
}

void showMsg(int n){
    char *errors[] = {
        "Memory Error allocating the array...",
        "The memory has been sucessfully liberated"
    };
    printf("Msg: [%s]",errors[n]);
}

void freeMemory(int *arr){
    free(arr);
    showMsg(1);
}