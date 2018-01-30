package Inceptor.src.test.java.udtf;

import java.io.IOException;

/**
 * Created by weicheng.huang on 2017/3/7.
 */
public class InUDTFTest {
    public static void main(String[] args) throws IOException{
        String input = "weicheng:huang;12:34r5";
        String[] test = input.split(";");
        for (int i=0; i<test.length;i++){
            try {
                String[] result = test[i].split(":");
                System.out.println(result[0]+"  "+result[1]);
            }catch(Exception e){
                continue;
            }
        }
    }
}