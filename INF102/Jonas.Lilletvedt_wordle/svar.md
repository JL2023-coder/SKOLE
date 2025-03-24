# Runtime Analysis
For each method of the tasks give a runtime analysis in Big-O notation and a description of why it has this runtime.

**If you have implemented new methods not listed you must add these as well, e.g. any helper methods. You need to show how you analyzed any methods used by the methods listed below.**

The runtime should be expressed using these three parameters:
   * `n` - number of words in the list allWords
   * `m` - number of words in the list possibleWords
   * `k` - number of letters in the wordleWords


## Task 1 - matchWord
* `WordleAnswer::matchWord`: O(k)
    * *The method has four for loops which run k times each, each line inside every for loop has a runtime of O(k), this gives a total runtime of O(k). Every line in wordleAnswer::matchWord has a described runtime, for more information.* 

## Task 2 - EliminateStrategy
* `WordleWordList::eliminateWords`: O(m*k)
    * *The method eliminate words contains one for loop which is run m through all possible answers, so m times. Inside of the for loop each line equates to O(1), except the method isPossibleWord, which takes inn matchWord, which is O(k), this equals to a big O of 
    O(m*k). Every line in WordleWordList::eliminateWords has a described runtime, for more information.*

## Task 3 - FrequencyStrategy
* `FrequencyStrategy::makeGuess`: O(m*k)
    * *The method makeGuess, has two help functions letterFrequencies, and wordScore in WordleUtils. It also uses eliminatewords, and has one for loop. It uses eliminate words, outside of any loop giving a runtime of O(k*m). LetterFrequencies is outside of any loop, giving a runtime of O(m*k). Word score is inside the for loop, wordScore in itself has a runtime of O(k), the for loop run m times giving a O(k*m) for the for loop. This in total equates to 3*O(k*m), so O(k*m). Every line in FrequencyStrategy::makeGuess and its help methods, has a described runtime, for more information.*



# Task 4 - Make your own (better) AI
For this task you do not need to give a runtime analysis. 
Instead, you must explain your code. What was your idea for getting a better result? What is your strategy?

*Write your answer here*
