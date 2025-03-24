package no.uib.inf102.wordle.model.word;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import no.uib.inf102.wordle.model.Dictionary;

/**
 * This class represents an answer to a Wordle puzzle.
 * 
 * The answer must be one of the words in the LEGAL_WORDLE_LIST.
 */
public class WordleAnswer {

    private final String WORD;

    private Dictionary dictionary;

    private static Random random = new Random();

    /**
     * Creates a WordleAnswer object with a random word from the answer word list
     */
    public WordleAnswer(Dictionary dictionary) {
        this(random, dictionary);
    }

    /**
     * Creates a WordleAnswer object with a random word from the answer word list
     * using a specified random object.
     * This gives us the opportunity to set a seed so that tests are repeatable.
     */
    public WordleAnswer(Random random, Dictionary dictionary) {
        this(getRandomWordleAnswer(random, dictionary), dictionary);
    }

    /**
     * Creates a WordleAnswer object with a given word.
     * 
     * @param answer
     */
    public WordleAnswer(String answer, Dictionary dictionary) {
        this.WORD = answer.toLowerCase();
        this.dictionary = dictionary;
    }

    /**
     * Gets a random wordle answer
     * 
     * @param random
     * @return
     */
    private static String getRandomWordleAnswer(Random random, Dictionary dictionary) {
        List<String> possibleAnswerWords = dictionary.getAnswerWordsList();
        int randomIndex = random.nextInt(possibleAnswerWords.size());
        String newWord = possibleAnswerWords.get(randomIndex);
        return newWord;
    }

    /**
     * Guess the Wordle answer. Checks each character of the word guess and gives
     * feedback on which that is in correct position, wrong position and which is
     * not in the answer word.
     * This is done by updating the AnswerType of each WordleCharacter of the
     * WordleWord.
     * 
     * @param wordGuess
     * @return wordleWord with updated answertype for each character.
     */
    public WordleWord makeGuess(String wordGuess) {
        if (!dictionary.isLegalGuess(wordGuess))
            throw new IllegalArgumentException("The word '" + wordGuess + "' is not a legal guess");

        WordleWord guessFeedback = matchWord(wordGuess, WORD);
        return guessFeedback;
    }

    /**
     * Generates a WordleWord showing the match between <code>guess</code> and
     * <code>answer</code>
     * 
     * @param guess
     * @param answer
     * @return
     */
    public static WordleWord matchWord(String guess, String answer) { // total big O(k)
        int wordLength = answer.length(); // O(1)
        if (guess.length() != wordLength) // O(1)
            throw new IllegalArgumentException("Guess and answer must have same number of letters but guess = " + guess
                    + " and answer = " + answer);

        // new hashmap, charCounter
        HashMap<Character, Integer> answerCharCounter = new HashMap<Character, Integer>(); // O(1)
        for(int i = 0; i < wordLength; i++){ // O(k)
            answerCharCounter.put(answer.charAt(i), answerCharCounter.getOrDefault(answer.charAt(i), 0)+1); // k times O(1)
        }

        // new answerType, feedBack
        // default feedback is wrong
        AnswerType[] feedback = new AnswerType[wordLength];
        for(int i = 0; i < wordLength; i++){ // O(k)
            feedback[i] = AnswerType.WRONG; // k times O(1) 
        }

        for(int i = 0; i < wordLength; i++){ // O(k)
            // if placed correctly
            if(guess.charAt(i)==answer.charAt(i)){ // k times O(1) 
                feedback[i] = AnswerType.CORRECT;  // k times O(1) 
                // -1 at charCounter hashmap
                answerCharCounter.put(answer.charAt(i), answerCharCounter.get(guess.charAt(i))-1); // k times O(1) 
            }
        }
        for(int i = 0; i < wordLength; i++){ // O(k)
            // if misplaced
            if(guess.charAt(i)!=answer.charAt(i) && answerCharCounter.getOrDefault(guess.charAt(i), 0)>=1){ // k times O(1) 
                feedback[i]=AnswerType.MISPLACED; // k times O(1) 
                // -1 at charCounter hashmap
                answerCharCounter.put(guess.charAt(i), answerCharCounter.get(guess.charAt(i))-1); // k times O(1) 
            }
        }
        return new WordleWord(guess,feedback); // O(1)
    }
}
