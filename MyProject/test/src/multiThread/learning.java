package multiThread;

class Thread1 extends Thread{
    private String name;
    public Thread1(String name){
        this.name = name;
    }
    public void run(){
        for (int i=0; i<4; i++){
            System.out.println(name + "运行： " + i);
            try {
                sleep((int)Math.random()*10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class Thread2 implements Runnable{
    private String name;
    public Thread2(String name){
        this.name = name;
    }
    public void run(){
        for (int i=0; i<5; i++){
            System.out.println(name + "运行： " + i);
        }
        try {
            Thread.sleep((int)Math.random()*10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class learning {
    public static void main(String[] args) {
        Thread1 mth1 = new Thread1("A");
        Thread1 mth2 = new Thread1("B");
        mth1.start();
        mth2.start();
        Thread2 mth3 = new Thread2("C");
        Thread2 mth4 = new Thread2("D");
        mth3.run();
        mth4.run();

    }
}
