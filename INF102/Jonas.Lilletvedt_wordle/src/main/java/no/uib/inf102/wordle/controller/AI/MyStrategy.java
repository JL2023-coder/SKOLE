package no.uib.inf102.wordle.controller.AI;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import no.uib.inf102.wordle.model.Dictionary;
import no.uib.inf102.wordle.model.word.WordleWord;
import no.uib.inf102.wordle.model.word.WordleWordList;

public class MyStrategy implements IStrategy {
    private Dictionary dictionary;
    private WordleWordList guesses;
    // Possible answers left before switching strategy
    private static int ANSWERS_LEFT_SWITCH_STRATEGY = 1000;

    public MyStrategy(Dictionary dictionary) {
        this.dictionary = dictionary;
        reset();
    }

    @Override
    public String makeGuess(WordleWord feedback) {
        // Removes words based on feedback
        if (feedback != null) {
            guesses.eliminateWords(feedback);
        }
        if(guesses.possibleAnswers().size()>ANSWERS_LEFT_SWITCH_STRATEGY){
            // Calculate letter frequencies for each position
            int wordLength = guesses.possibleAnswers().get(0).length();
            Map<Character, Integer>[] letterFrequencies = WordleUtils.letterFrequencies(wordLength, guesses);
            String bestWord = null;
            int bestScore = 0;
            // Checks which word has highest score
            for (String word : guesses.possibleAnswers()) {
                int score = WordleUtils.wordScore(word, letterFrequencies);
                if (score > bestScore) {
                    bestScore = score;
                    bestWord = word;
                }
            }
            return bestWord;
        }
        else{
            // Best word and score
            String bestWord = null;
            double bestExpectedElimination = Double.MAX_VALUE;
            // Calculate the word that eliminates the most options and has high feedback diversity
            for (String word : guesses.possibleAnswers().parallelStream().collect(Collectors.toList())) {
                double expectedRemainingWords = WordleUtils.calculateExpectedRemainingWords(word, guesses.possibleAnswers());
                // Prioritize word that removes most words from possibleanswers
                if (expectedRemainingWords < bestExpectedElimination){
                    bestExpectedElimination = expectedRemainingWords;
                    bestWord = word;
                }
            }
            return bestWord;
        }
    }

    @Override
    public void reset() {
        guesses = new WordleWordList(dictionary);
    }
}
