package jihe;

import java.util.*;

public class array {
    public static void main(String[] args) {
        int arr1[] = new int[]{1,2,3,4,5};
        String[] arr2 = {"马超","关羽","马云","刘备","张飞"};
        String[] ary3 = {"黄渤","黄磊","罗志祥","孙红雷","张艺兴"};
        int[] score = new int[3];
        for (int i=0; i<score.length; i++){
            score[i] = i+1;
            //System.out.println(score[i]);
        }
        for (int i=0; i<score.length; i++){
            System.out.println(score[i]);
        }
        System.out.println("sore length: "+ score.length);
        //int数组转string数组-->[1, 2, 3]
        String arr1String = Arrays.toString(score);
        System.out.println(arr1String);

        //从array中创建arraylist(动态数组)
        ArrayList<String> arraylist = new ArrayList<String>(Arrays.asList(arr2));
        System.out.println(arraylist);
        //将数组转为set集合
        Set<String> set1 = new HashSet<String>(Arrays.asList(arr2));
        System.out.println(set1);
        //将数组转为list集合
        List<String> list1 = new ArrayList<String>();
        for (int i=0; i<arr2.length; i++){
            list1.add(arr2[i]);
            System.out.println(list1);
        }

        //填充数组
        int[] arr4 = new int[5];
        Arrays.fill(arr4,10);
        for (int i=0; i<arr4.length; i++){
            System.out.print(arr4[i] + " ");
        }
        System.out.println();

        //数组排序
        int[] arr5 = {1,5,9,7,3};
        Arrays.sort(arr5);
        for (int i=0; i<arr5.length; i++){
            System.out.print(arr5[i] + " ");
        }
        System.out.println();
        int[] arr6 = {3,7,2,1};
        int[] arr7 = Arrays.copyOf(arr6,10);

        int[] arr8 = Arrays.copyOfRange(arr6,1,3);
        for (int i=0; i<arr8.length; i++){
            System.out.print(arr8[i] + " ");
        }
        System.out.println();

        int[] arr9 = {1,2,3,4,5,6,7,8,9,0};
        boolean arr10 = Arrays.equals(arr6,arr9);
        System.out.println(arr10);

        int[] arr11 = {1, 2, 3, 4,5,6,7,8,9,0,3,2,4,5,6,7,4,32,2,1,1,4,6,3};
        Set<Integer> set2 = new HashSet<Integer>();
        for (int i=0; i<arr11.length; i++){
            set2.add(arr11[i]);
        }
        System.out.println(set2);

        int[] arr12 = {10, 2, 3, 4,5,6,7,8,9,0,3,2,4,5,6,7,4,32,2,1,1,4,6,3};
        int max = 0;

        for (int i=1; i<arr12.length; i++){
            if (arr12[i] > max){
                max = arr12[i];
            }
        }
        System.out.println("max arr12 is : " + max);

        int min = arr12[0];
        for (int i=1; i<arr12.length; i++){
            if (arr12[i] < min){
                min = arr12[i];
            }
        }
        System.out.println("min arr12 is : " + min);


    }
}
