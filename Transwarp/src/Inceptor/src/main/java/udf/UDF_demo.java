package Inceptor.src.main.java.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class UDF_demo extends UDF {
    /*
 * ��IP��ַת�������ֱ�ʾ
 */
    public static long evaluate(String ipNum) {
        String[] arr = ipNum.split("\\.");
        if (arr.length == 4) {
            return Integer.parseInt(arr[0]) * 256 * 256 * 256l
                    + Integer.parseInt(arr[1]) * 256 * 256l
                    + Integer.parseInt(arr[2]) * 256l
                    + Integer.parseInt(arr[3]);
        } else {
            return 0l;
        }

    }
/*  public static void main(String[] args) {
	  System.out.println(evaluate("10.0.0.1"));
}*/
}
