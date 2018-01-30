package jihe;

import java.text.SimpleDateFormat;
import java.util.Date;

public class array_sort {
    public static void bubbleSort(int[] number){
        int tmp = 0;
        int size = number.length;
        for (int i=0; i<size-1; i++){
            for (int j=0; j<size-1-i; j++){
                if (number[j] > number[j+1]){
                    tmp = number[j];
                    number[j] = number[j+1];
                    number[j+1] = tmp;
                }
            }
        }
    }

    public static int getMiddle(int[] number, int low , int high){
        int tmp = number[low];

        while (low < high){
            while (low < high && number[high] > tmp){
                high--;
            }
            number[low] = number[high];
            while (low < high && number[low] < tmp){
                low++;
            }
            number[high] = number[low];
        }
        number[low] = tmp;
        return low;
    }
    public static void quick(int[] numbers)
    {
        if(numbers.length > 0)   //查看数组是否为空
        {
            QuickSort(numbers, 0, numbers.length-1);
        }
    }
    public static void QuickSort(int[] number,int low, int high){
        if (low < high){
            int middle = getMiddle(number,low,high);
            QuickSort(number,low,middle-1);
            QuickSort(number,middle+1,high);
        }
    }

    public static void printArr(int[] number){
        for (int i=0; i<number.length; i++){
            System.out.print(number[i] + ",");
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        int[] number = new int[] {10,20,15,0,6,7,2,1,-5,55};
        System.out.print("排序前: ");
        printArr(number);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前时间: " + df.format(new Date()));
        //bubbleSort(number);

        quick(number);
        System.out.println("排序后时间: " + df.format(new Date()));
        System.out.print("排序后: ");
        printArr(number);

    }
}
