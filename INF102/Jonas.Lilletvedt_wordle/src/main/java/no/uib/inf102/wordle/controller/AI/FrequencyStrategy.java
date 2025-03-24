package no.uib.inf102.wordle.controller.AI;

import java.util.HashMap;
import java.util.Map;

import no.uib.inf102.wordle.model.Dictionary;
import no.uib.inf102.wordle.model.word.WordleWord;
import no.uib.inf102.wordle.model.word.WordleWordList;

/**
 * This strategy finds the word within the possible words which has the highest
 * expected
 * number of green matches.
 */
public class FrequencyStrategy implements IStrategy {

    private Dictionary dictionary;
    private WordleWordList guesses;

    public FrequencyStrategy(Dictionary dictionary) {
        this.dictionary = dictionary;
        reset();
    }

    @Override
    public String makeGuess(WordleWord feedback) { // O(m*k)
        // Removes words based on feedback
        if (feedback != null) { // O(1)
            guesses.eliminateWords(feedback); // O(k*m)
        }
        // Calculate letter frequencies for each position
        int wordLength = guesses.possibleAnswers().get(0).length(); // O(1)
        Map<Character, Integer>[] letterFrequencies = WordleUtils.letterFrequencies(wordLength, guesses); // O(m*k)
        // Best word an score is null
        String bestWord = null; // O(1)
        int bestScore = 0; // O(1)
        // Checks which word has highest score
        for (String word : guesses.possibleAnswers()) { // O(k*m)
            int score = WordleUtils.wordScore(word, letterFrequencies); // O(k) m times
            if (score > bestScore) { // O(1) m times
                bestScore = score; // O(1) m times
                bestWord = word; // O(1) m times
            }
        }
        return bestWord; // O(1)
    }

    @Override
    public void reset() {
        guesses = new WordleWordList(dictionary);
    }
}