package java.src;

import java.util.Arrays;

/**
 * Created by weicheng.huang on 2017/6/11.
 */
public class test {
    public static void main(String[] args){
//        randomTraining();
//        stringTraining();
//        randomCharacter();
        ArrayTraining();


    }

    private static void ArrayTraining() {
        double[] numbers = {6.0,4.4,1.9,2.9,3.4,3.5};
        Arrays.sort(numbers);
        Arrays.toString(numbers);


    }

    private static void randomCharacter() {
        char randChar = 0;
        randChar = (char) ((int)'a' + Math.random() * ((int)'z' - (int)'a' + 1));
        System.out.println(randChar);
    }

    private static void stringTraining() {
        String message = " welcome to Java   ";
        System.out.println(message.length());
        //charAt(m),m必须是0 - message.length()-1之间的数，不支持 -1 这种表示法
        System.out.println(message.charAt(7) + "   " + message.charAt(message.length()-6));
        System.out.println(message.concat(", hello pthwch"));

        System.out.println(message.toLowerCase());
        System.out.println(message.toUpperCase());
        //trim() 空格、\t、\f、\r、\n
        System.out.println(message.trim());
    }


    private static void randomTraining() {
        System.out.println("This is randomFun ...");
        for (int i = 0; i < 100; i++) {
            Double temp = null;
            temp = Math.random();
            System.out.println(temp + "\t" + (int)(temp * 100));
        }
        System.out.println("randomFun is ended");
    }
}
