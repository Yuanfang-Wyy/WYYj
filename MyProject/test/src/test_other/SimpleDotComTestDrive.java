package test_other;

public class SimpleDotComTestDrive {
    public static void main(String[] args) {
        int numOfGuesses = 0;
        GameHelper helper = new GameHelper();

        SimpleDotCom theDotCom = new SimpleDotCom();
        int randomnum = (int)(Math.random() * 5);

        int [] locations = {randomnum,randomnum+1,randomnum+2};
        theDotCom.setLocationCells(locations);
        boolean isAlive = true;

        while (isAlive){
            String guess = helper.getUserInput("enter a number");
            String result = theDotCom.checkYourself(guess);
            numOfGuesses++;

            if (result.equals("kill")){
                isAlive = true;
                System.out.println("You took " + numOfGuesses + " guesses");

            }
        }

    }
/*    public static void main(String[] args) {
        test_other.SimpleDotCom dot = new test_other.SimpleDotCom();
        int [] locations = {2,3,4};
        dot.setLocationCells(locations);
        String UserGuess = "2";
        String result = dot.checkYourself(UserGuess);
    }*/
}
