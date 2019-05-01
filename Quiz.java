package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Quiz {

  private HashTableQuiz questions;
  private int numCorrect;
  private int numIncorrect;

  /**
   * No-argument constructor
   */
  public Quiz() {
    questions = new HashTableQuiz();
    numCorrect = 0;
    numIncorrect = 0;
  }

  /**
   * Parses through JSON file to create new questions
   * 
   * @param filepath
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ParseException
   */
  public void addQuestion(String filepath)
      throws FileNotFoundException, IOException, ParseException {
    JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader(filepath)); // get the JSON
                                                                                   // object
    JSONArray questions = (JSONArray) jo.get("questionArray"); // array of all questions in file
    Iterator<JSONObject> it1 = questions.iterator();
    while (it1.hasNext()) { // iterate through questions
      JSONObject nextQuestion = it1.next();
      // get the question, topic, and image
      String questionText = (String) nextQuestion.get("questionText");
      String topic = (String) nextQuestion.get("topic");
      String image = (String) nextQuestion.get("image");
      // get the choices
      JSONArray choices = (JSONArray) nextQuestion.get("choiceArray");
      ArrayList<Choice> choiceList = new ArrayList<Choice>(); // to contain choices
      Iterator<JSONObject> it2 = choices.iterator();
      while (it2.hasNext()) { // iterate through choices
        Boolean trueOrFalse;
        JSONObject nextChoice = it2.next();
        String correct = (String) nextChoice.get("isCorrect"); // T or F
        String choiceText = (String) nextChoice.get("choice");
        if (correct.equals("T")) {
          trueOrFalse = true;
        } else { // TODO: i think we should throw an exception here if isCorrect doesn't say "T" or
                 // "F" but idk what kind so i left it like this
          trueOrFalse = false;
        }
        choiceList.add(new Choice(trueOrFalse, choiceText)); // add to the choice list
      }
      addQuestion(topic, questionText, choiceList, image); // add question to hashtable
    }
  }

  /**
   * Add a question
   * 
   * @param topic
   * @param question
   * @param choices
   * @param image
   */
  public void addQuestion(String topic, String question, ArrayList<Choice> choices, String image) {
    Topic t = questions.get(topic);
    if (t == null) { // see if topic already exists in table
      t = new Topic(topic);
      questions.insert(t);
    }
    Question q = new Question(question, choices, image); // new question with parameters
    t.addQuestion(q); // add question to hashtable
  }

  /**
   * Start a quiz with a specified amount of questions
   * 
   * @param topics
   * @param numQuestions
   * @return quiz questions list
   */
  public List<Question> startQuiz(List<Topic> topics, int numQuestions) {
    ArrayList<Question> allQuestions = new ArrayList<Question>(); // to hold all questions
    ArrayList<Question> questions = new ArrayList<Question>(); // to hold quiz questions
    for (Topic t : topics) { // iterate through all topics
      for (Question q : t.getQuestionList()) { // iterate through questions in each topic
        allQuestions.add(q); // add every question
      }
    }
    Random randGen = new Random(allQuestions.size()); // used to pull a random question from all
                                                      // questions
    // add as many questions as desired to return list
    for (int i = 0; i < numQuestions; i++) { 
      questions.add(allQuestions.get(randGen.nextInt()));
    }
    return questions;
  }

  public boolean checkCorrect(String question) {

    return false;
  }
  
  /**
   * Returns number of correct answers
   * 
   * @return numCorrect
   */
  public int getCorrect() {
    return numCorrect;
  }

  /**
   * Returns number of incorrect answers
   * 
   * @return numIncorrect
   */
  public int getIncorrect() {
    return numIncorrect;
  }
}
