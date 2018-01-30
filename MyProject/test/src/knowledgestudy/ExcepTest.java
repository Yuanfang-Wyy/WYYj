package knowledgestudy;

public class ExcepTest {
    public static void main(String[] args) {
        int a = 1;
        int b = 0;
        try {
            if (b == 0) throw new ArithmeticException();
            System.out.println("a/b的值是: " + (float)a/b);
            System.out.println("this will not be printed");
        }catch (ArithmeticException e){
            System.out.println("程序出现异常，变量b不能为空！");
        }
    }
/*    public static void main(String[] args) {
        int [] a = new int[2];
        try {
            System.out.println("access element three: " + a[3]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Exception thrown : " + e);
        }finally {
            a[0] = 6;
            System.out.println("first element value: " + a[0]);
            System.out.println("The finally element is executed");
        }
        System.out.println("out of the block");
    }*/
}
