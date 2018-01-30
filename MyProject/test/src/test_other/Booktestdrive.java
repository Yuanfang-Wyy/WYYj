package test_other;

class Books{
    String title;
    String author;
}
public class Booktestdrive {
    public static void main(String[] args) {
        Books [] mybook = new Books[3];
        int x = 0;
        mybook[0] = new Books();
        mybook[1] = new Books();
        mybook[2] = new Books();

        mybook[0].title = "c";
        mybook[0].author = "wyy";
        mybook[1].title = "c++";
        mybook[1].author = "wyy1";
        mybook[2].title = "java";
        mybook[2].author = "wyy2";

        while (x < 3){
            System.out.print(mybook[x].title);
            System.out.print(" by ");
            System.out.println(mybook[x].author);
            x += 1;
        }
    }
}
