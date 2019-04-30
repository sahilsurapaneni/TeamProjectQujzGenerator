package application;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
  private HashTableQuiz questions;
  private int numCorrect;
  private int numIncorrect;
  
  public Quiz() {
    questions = new HashTableQuiz();
    numCorrect = 0;
    numIncorrect = 0;
  }
  
  public void addQuestion(String filepath) {
    
  }
  
  public void addQuestion(String question, List<Choice> choices) {
    
  }
  
  public List<Question> startQuiz(List<Topic> topics, int numQuestions) {
    return null;
  }
  
  public boolean checkCorrect(String question) {
    return false;
  }
  
  public int getCorrect() {
    return numCorrect;
  }
  
  public int getIncorrect() {
    return numIncorrect;
  }
}
