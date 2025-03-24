package no.uib.inf102.wordle.controller.AI;

import no.uib.inf102.wordle.model.word.WordleWordList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WordleUtils{
    // Count how many times each letter appear in each position
    public static Map<Character, Integer>[] letterFrequencies(int wordLength, WordleWordList guesses){ // O(m*k)
        // An array of maps, one for each position
        Map<Character, Integer>[] letterFrequencies = new HashMap[wordLength]; // O(1)
        // New HashMap for each position
        for (int i = 0; i < wordLength; i++) { // O(n)
            letterFrequencies[i] = new HashMap<>(); // O(1) n times
        }
        // Count frequncies for each letter in each postition
        // Iterate through possible answers
        for (String word : guesses.possibleAnswers()) { // O(m*k) 
            // Convert word to a character array to avoid repeated charAt()
            char[] wordChars = word.toCharArray(); // O(k) m times
            // Count frequencies for each letter at each position
            for (int i = 0; i < wordChars.length; i++) { // O(k) m times
                char letter = wordChars[i]; // O(1) n times
                // Update the frequency count
                letterFrequencies[i].put(letter, letterFrequencies[i].getOrDefault(letter, 0) + 1); // O(1) k times
        }
    }
        return letterFrequencies; // O(1)
    }

    // Gives each word a score based on probability of green letter
    // Score is equal to sum of letter frequencies in word
    public static int wordScore(String word, Map<Character, Integer>[] positionFrequencies){ // O(k)
        int score = 0; // O(1)
        // Convert word to a character array to avoid repeated charAt()
        char[] wordChars = word.toCharArray(); // O(k)
        // Sum the frequency of each letter at its position
        for (int i = 0; i < word.length(); i++) { // O(k)
            char letter = wordChars[i]; // O(1) k times
            score += positionFrequencies[i].getOrDefault(letter, 0); // O(1) k times
        }
        return score;
    }
    
    // Returns expected amount of remaing words after guess
    public static double calculateExpectedRemainingWords(String guess, List<String> answers) {
        // Map storing all feedback for each word
        Map<String, Integer> feedbackPatternCount = new ConcurrentHashMap<>();
        // Get feedback for every possible guess
        answers.parallelStream().forEach(answer -> {
            String feedbackPattern = generateFeedbackPattern(guess, answer);
            feedbackPatternCount.merge(feedbackPattern, 1, Integer::sum);
        });
        // Calculate the average of remaining words for each feedback pattern
        double expectedRemainingWords = 0;
        int totalAnswers = answers.size();
        for (int count : feedbackPatternCount.values()) {
            double probability = (double) count / totalAnswers;
            expectedRemainingWords += probability * count;
        }
        return expectedRemainingWords;
    }

    private static String generateFeedbackPattern(String guess, String answer) {
        char[] feedback = new char[guess.length()];
        // Create a frequency map for letters in the answer
        Map<Character, Integer> answerLetterCounts = new HashMap<>();
        for (char c : answer.toCharArray()) {
            answerLetterCounts.put(c, answerLetterCounts.getOrDefault(c, 0) + 1);
        }
        // Green letters
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                feedback[i] = 'G';
                answerLetterCounts.put(guess.charAt(i), answerLetterCounts.get(guess.charAt(i)) - 1);
            } else {
                feedback[i] = '_'; 
            }
        }
        // Yellow letters
        for (int i = 0; i < guess.length(); i++) {
            if (feedback[i] == '_') {
                char guessChar = guess.charAt(i);
                if (answerLetterCounts.getOrDefault(guessChar, 0) > 0) {
                    feedback[i] = 'Y';
                    answerLetterCounts.put(guessChar, answerLetterCounts.get(guessChar) - 1);
                }
            }
        }
        return new String(feedback);
    }
    
}
