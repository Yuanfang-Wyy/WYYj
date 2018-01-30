package test_other;

class Dog1{
    String name;

    void bark(int size){
        if (size > 60){
            System.out.println("Wooof! Wooof!");
        }else if (size > 40){
            System.out.println("Ruff! Ruff!");
        }else {
            System.out.println("Yip! Yip!");
        }
    }
}
public class Dogtestdrive {
    public static void main(String[] args) {
        Dog1 one = new Dog1();
        Dog1 two = new Dog1();
        Dog1 three = new Dog1();

/*        one.size = 70;
        two.size = 50;
        three.size = 30;*/

        one.bark(70);
        two.bark(50);
        three.bark(30);
    }
}
