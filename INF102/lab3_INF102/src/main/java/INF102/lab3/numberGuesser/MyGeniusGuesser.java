package INF102.lab3.numberGuesser;


public class MyGeniusGuesser implements IGuesser {
    private int lowerbound;
	private int upperbound;

	@Override
    public int findNumber(RandomNumber number) {
        int numberGuess;
        int result;
        // Lowest possible number
        lowerbound = number.getLowerbound();
        // Largest possible number
        upperbound = number.getUpperbound();
            do {
                // Help function
                numberGuess = makeGuess();
                // Result either -1, 0, 1
                result = number.guess(numberGuess);
                // If result is -1, hidden number has to be bigger than lowerbound
                if(result==-1){
                    lowerbound = numberGuess+1;
                }
                // If result is 1, hidden number has to be smaller than upperbound
                if(result==1){
                    upperbound = numberGuess-1;
                }
            // Runs until hidden number is found
            } while (result != 0);
            return numberGuess;
        }

    private int makeGuess() {
        // Binary search
		return lowerbound+(upperbound-lowerbound)/2;
	}
}
