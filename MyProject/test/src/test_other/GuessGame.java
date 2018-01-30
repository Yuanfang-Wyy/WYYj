package test_other;

public class GuessGame {
    Player p1;
    Player p2;
    Player p3;

    public void startGame(){
        p1 = new Player();
        p2 = new Player();
        p3 = new Player();

        int guessp1 = 0;
        int guessp2 = 0;
        int guessp3 = 0;
        boolean p1isright = false;
        boolean p2isright = false;
        boolean p3isright = false;

        int targetNumber = (int)(Math.random() * 10);
        System.out.println("i am thinging of a number between 0 and 9 ...");

        while (true){
            System.out.println("Number to guess is  " + targetNumber);
            p1.guess();
            p2.guess();
            p3.guess();

            guessp1 = p1.number;
            guessp2 = p2.number;
            guessp3 = p3.number;

            System.out.println("test_other.Player one guessed " + guessp1);
            System.out.println("test_other.Player two guessed " + guessp2);
            System.out.println("test_other.Player three guessed " + guessp3);

            if (guessp1 == targetNumber){
                p1isright = true;
            }
            if (guessp2 == targetNumber){
                p2isright = true;
            }
            if (guessp3 == targetNumber){
                p3isright = true;
            }

            if (p1isright || p2isright || p3isright){
                System.out.println("We have winer");
                System.out.println("test_other.Player one got it right? " + p1isright);
                System.out.println("test_other.Player two got is ritht? " + p2isright);
                System.out.println("test_other.Player three got is right? "+ p3isright);
                System.out.println("Game is over.");
                break;
            }else {
                System.out.println("Players will have to try again.");
            }

        }
    }
}
