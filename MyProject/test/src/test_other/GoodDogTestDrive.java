package test_other;

class GoodDog{
    private int size;
    public int getSize(){
        return size;
    }
    public void setSize(int s){
        size = s;
    }

    void bark(){
        if (size > 60){
            System.out.println("Woof Woof");
        }else if (size > 40){
            System.out.println("Ruff Ruff");
        }else {
            System.out.println("Yip Yip");
        }
    }
}

public class GoodDogTestDrive {
    public static void main(String[] args) {
        GoodDog one = new GoodDog();
        one.setSize(70);
        GoodDog two = new GoodDog();
        two.setSize(8);
        System.out.println("test_other.Dog one : " + one.getSize());
        System.out.println("test_other.Dog two : " + two.getSize());
        one.bark();
        two.bark();
    }
}
