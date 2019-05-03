package application;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: TeamProjectQuizGenerator
//Files: Choice.java, Main.java, Question.java, Quiz.java, Topic.java, HashTableQuiz.java
//Course: CS 400
//
//Author: Sahil Surapaneni,Maurya Adhitya, Chad Spalding, Sathvik Gurupalli, Varun Sudhakaran
//Email: surapaneni2@wisc.edu, mpulipati@wisc.edu, ctspalding@wisc.edu, vsudhakaran@wisc.edu, gurupalli@wisc.edu
//Lecturer: Professor Debra Deppeler, Andrew Kuemmel
//Lecturer Number: 001, 002, 004.
//Due Date: 5/3/19
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * GUI Class for Quiz Generator
 * @author Sahil Surapaneni, Maurya Pulipati, Chad Spalding, Sathvik Gurupalli, Varun Sudhakaran
 *
 */
public class Main extends Application {
	
	private Scene primaryScene; //Scene loaded when program first ran
	private Scene addQScene; //Scene for manual question entry
	private Scene addJSONScene; //Scene for question entry based on JSON
	private Scene quizStartScene; //Scene for quiz options
	private Scene resultsScene; // Scene displays result of scene
	private Scene saveJSONScene; //Scene to write questions to JSON File
	
	private int currQuestion = 0; //current question number user is on when quiz starts
	private ArrayList<String> choices= new ArrayList<String>(); //choices made by user in quiz
	
	private Stage stage; //Scene on stage gets displayed  to user
	
	private Quiz quiz; //Backend to GUI that stores and pushes data entered by user
	private List<Question> quizQuestions; //list of questions used in quiz
	String selectedChoice; //choice user makes in quiz
	int numCorrect; //number of correct choices made by user
	
	int numQuestions = 0; //number of questions added by user
	
	/**
	 * Initializes the GUI
	 */
	public void start(Stage primaryStage) {
		stage = primaryStage;
		
		quiz = new Quiz();	
		quizQuestions = new ArrayList<Question>();
		selectedChoice = "";
		numCorrect = 0;
		
		//Initializes all the Scenes
		primaryScene = createPrimaryScene();
		addQScene = createAddQScene();
		addJSONScene = createAddJSONScene();
		quizStartScene = createQuizStartScene();
		saveJSONScene = createSaveJSONScene();
		
		//shows the scene
		stage.setScene(primaryScene);
		stage.show();
	}
	
	/**
	 * @return Scene first displayed to user
	 */
	private Scene createPrimaryScene() {
		
		//holds overall scene
		VBox design = new VBox(10);
		design.setStyle("-fx-background-color: cornsilk; -fx-padding: 15;");
		
		//title of Scene
		Label label = new Label();
		label.setText("QUIZ GENERATOR SYSTEM");
		label.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
		label.setAlignment(Pos.BASELINE_CENTER);
		
		HBox labelBox = new HBox();
		labelBox.getChildren().setAll(label);
		labelBox.setAlignment(Pos.BASELINE_CENTER);
		
		//Button to go to addQuestion Scene
		Button qButton = new Button();
		qButton.setText("Add Question");
		String initStyleTxt = "-fx-background-color: #4CAF50;-fx-border: none; -fx-color: white; -fx-padding: 10px 25px; "
				+ "-fx-text-align: center; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 20px; -fx-background-radius: 5px";
		String hoverStyleText = "-fx-background-color: #35A10B; -fx-color:white; -fx-border:10px; -fx-border-color: black;"
				+ "-fx-padding: 10px 25px;-fx-text-align: center; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 20px;-fx-background-radius: 5px";
		qButton.setMinSize(200,150);
		qButton.setMaxSize(200,150);
		qButton.setStyle(initStyleTxt);
		qButton.setOnMouseEntered(e->qButton.setStyle(hoverStyleText));
		qButton.setOnMouseExited(e->qButton.setStyle(initStyleTxt));
		qButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						stage.setScene(addQScene);
						stage.show();
						System.out.println("Pressed");
					}
				}
		);
		
		//Button to go addQuestion With JSONFile
		Button JSONButton = new Button();
		JSONButton.setText("Add JSON File");
		JSONButton.setMinSize(200,150);
		JSONButton.setMaxSize(200,150);
		JSONButton.setStyle(initStyleTxt);
		JSONButton.setOnMouseEntered(e->JSONButton.setStyle(hoverStyleText));
		JSONButton.setOnMouseExited(e->JSONButton.setStyle(initStyleTxt));
		JSONButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						stage.setScene(addJSONScene);
						stage.show();
						System.out.println("Pressed");
					}
				}
		);
		
		//Button to start quiz
		Button quizButton = new Button();
		quizButton.setText("start quiz");
		quizButton.setMaxSize(150, 100);
		String init = "-fx-background-color: #33FFDC; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-border-radius: 2px; -fx-text-align: center; -fx-text-fill: black; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		String hover = "-fx-background-color: #058984; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-border-radius: 2px; -fx-text-align: center; -fx-text-fill: white; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		quizButton.setStyle(init);
		quizButton.setOnMouseEntered(e->quizButton.setStyle(hover));
		quizButton.setOnMouseExited(e->quizButton.setStyle(init));
		quizButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						if(quiz.getNumTopics()<=0) {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("ERROR FOUND");
							alert.setHeaderText(null);
							alert.setContentText("No Question Added | Quiz Not Started");
							alert.showAndWait();
							stage.setScene(primaryScene);
							stage.show();
						}
						else {
							stage.setScene(quizStartScene);
							stage.show();
							System.out.println("Pressed");
						}
						
					}
				}
		);
		
		HBox tempBox = new HBox();
		tempBox.getChildren().setAll(quizButton);
		tempBox.setAlignment(Pos.BASELINE_CENTER);
		
		HBox hbox = new HBox();
		hbox.setSpacing(5);
		hbox.setPadding(new Insets(0,0,10,0));
		hbox.getChildren().setAll(qButton,JSONButton);
				
		//sets all the elements into the scene
		design.getChildren().setAll(labelBox,hbox,tempBox);
		return new Scene(design);
		
		
	}
	
	/**
	 * @return Scene to manually enter a question
	 */
	@SuppressWarnings("unused")
	private Scene createAddQScene() {
		VBox design = new VBox(10);
		design.setStyle("-fx-background-color: cornsilk; -fx-padding: 15;");
		
		//Button to return to home screen
		Button returnButton = new Button();
		String initStyle = "-fx-font-weight: bold; -fx-background-color: transparent;-fx-font-size: 18px;";
		String hoverStyle = "-fx-background-color: D5D6D6;-fx-font-size: 15px;"
				+ "-fx-border: 1px; -fx-border-color: black; -fx-border-style: solid;";
		returnButton.setText("<--");
		returnButton.setMaxSize(175, 100);
		returnButton.setStyle(initStyle);
		returnButton.setOnMouseEntered(e->returnButton.setStyle(hoverStyle));
		returnButton.setOnMouseExited(e->returnButton.setStyle(initStyle));
		returnButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						stage.setScene(primaryScene);
						stage.show();
						System.out.println("Pressed");
					}
				}
		);
		
		//Title of Scene
		Label label = new Label();
		label.setText("ADD QUESTION");
		label.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
		
		HBox topLine = new HBox();
		topLine.setSpacing(30);
		topLine.getChildren().setAll(returnButton,label);
		topLine.setAlignment(Pos.BASELINE_CENTER);
		topLine.setPadding(new Insets(0,10,15,0));
		
		Label topic = new Label();
		topic.setText("Enter Topic: ");
		topic.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		TextArea topicBox = new TextArea();
		topicBox.setPrefColumnCount(10);
		topicBox.setPrefRowCount(1);
		topicBox.setStyle("-fx-border: 2px; -fx-border-color: #4CAF50;-fx-border-radius: 5px;");
		
		//HBox to enter topic, storing both label and textArea
		HBox topicLine = new HBox();
		topicLine.setSpacing(35);
		topicLine.setPadding(new Insets(0,0,7,0));
		topicLine.getChildren().setAll(topic, topicBox);
		
		Label question = new Label();
		question.setText("Enter Question: ");
		question.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		TextArea questionBox = new TextArea();
		questionBox.setPrefColumnCount(10);
		questionBox.setPrefRowCount(1);
		questionBox.setStyle("-fx-border: 2px; -fx-border-color: #4CAF50;-fx-border-radius: 5px;");
		
		//HBox to enter questionText, storing both label and textArea
		HBox questionLine = new HBox();
		questionLine.setSpacing(10);
		questionLine.setPadding(new Insets(0,0,7,0));
		questionLine.getChildren().setAll(question, questionBox);
		
		Label image = new Label();
		image.setText("Image Path: ");
		image.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		TextArea imageBox = new TextArea();
		imageBox.setPrefColumnCount(10);
		imageBox.setPrefRowCount(1);
		imageBox.setStyle("-fx-border: 2px; -fx-border-color: #4CAF50;-fx-border-radius: 5px;");
		
		Label imageInstructions = new Label();
		imageInstructions.setText("(enter \"none\" if N/A)");
		imageInstructions.setStyle("-fx-font: italic 10px Georgia, serif;");
		
		VBox images = new VBox();
		images.getChildren().setAll(image,imageInstructions);
		
		//HBox to enter imagePath, storing both label and textArea
		HBox imageLine = new HBox();
		imageLine.setSpacing(36);
		imageLine.setPadding(new Insets(0,0,7,0));
		imageLine.getChildren().setAll(images, imageBox);
		
		Label choice = new Label();
		choice.setText("Enter Choices: ");
		choice.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		Label choiceInstructions = new Label();
		choiceInstructions.setText("(select correct option)");
		choiceInstructions.setStyle("-fx-font: italic 10px Georgia, serif;");
		
		VBox choices = new VBox();
		choices.getChildren().setAll(choice,choiceInstructions);
		
		//HBox to enter choices, storing both label and textArea
		HBox choiceLine = new HBox();
		choiceLine.setSpacing(20);
		choiceLine.setPadding(new Insets(0,0,7,0));
		
		//initializes Radio Button to store correct choice.
		VBox textAreas = new VBox();
		textAreas.setPadding(new Insets(0,0,13,0));
		ToggleGroup group = new ToggleGroup();
		ArrayList<TextArea> arrText = new ArrayList<TextArea>();
		ArrayList<HBox> arrHBox = new ArrayList<HBox>();
		ArrayList<RadioButton> arrRadio = new ArrayList<RadioButton>();	
		for(int i = 0; i<5; i++) {
			arrText.add(new TextArea()); 
			arrText.get(i).setPrefColumnCount(10);
			arrText.get(i).setPrefRowCount(1);
			arrText.get(i).setStyle("-fx-border: 2px; -fx-border-color: #4CAF50;-fx-border-radius: 5px;"); 
			arrRadio.add(new RadioButton());
			arrRadio.get(i).setToggleGroup(group);
			arrHBox.add(new HBox());
			arrHBox.get(i).setSpacing(10);
			arrHBox.get(i).getChildren().addAll(arrText.get(i),arrRadio.get(i));
			textAreas.getChildren().add(arrHBox.get(i));
		}
		choiceLine.getChildren().setAll(choices,textAreas);
		
		//Button to submit question to be stored
		Button submitButton = new Button();
		submitButton.setText("Enter Question");
		submitButton.setMaxSize(150, 100);
		String init = "-fx-background-color: #33FFDC; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-border-radius: 2px; -fx-text-align: center; -fx-text-fill: black; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		String hover = "-fx-background-color: #058984; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-border-radius: 2px; -fx-text-align: center; -fx-text-fill: white; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		submitButton.setStyle(init);
		submitButton.setOnMouseEntered(e->submitButton.setStyle(hover));
		submitButton.setOnMouseExited(e->submitButton.setStyle(init));
		submitButton.setOnAction(
				new EventHandler<ActionEvent>() {
					Boolean selected = false;
					public void handle(ActionEvent t) {
						String topicText = topicBox.getText();
						topicBox.setText("");
						String questionText = questionBox.getText();
						questionBox.setText("");
						String imageText = imageBox.getText();
						imageBox.setText("");
						List<Choice> choices = new ArrayList<Choice>();
						for(int i = 0; i<arrText.size();i++) {
							choices.add(new Choice(false,arrText.get(i).getText()));			
						}
						boolean otherAlert = false;
						if(topicText.length()<=0 || questionText.length()<=0 || choices.size() == 0) {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("ERROR FOUND");
							alert.setHeaderText(null);
							alert.setContentText("Invalid Entry | Question Not Added");
							alert.showAndWait();
							topicBox.setText("");
							questionBox.setText("");
							imageBox.setText("");
							choices.clear();
							stage.setScene(addQScene);
						}
						else {
							try {
								outerloop:
								for(int i = 0; i<arrRadio.size();i++) {
									if(arrRadio.get(i).isSelected()) {
										try {
											if(choices.get(i).getChoiceText().length()==0) {
												System.out.println("LL" + choices.get(i).getChoiceText());
												throw new Exception();
											}
										} catch (Exception e) {
											Alert alert = new Alert(Alert.AlertType.INFORMATION);
											alert.setTitle("ERROR FOUND");
											alert.setHeaderText(null);
											alert.setContentText("Invalid Choice | Question Not Added");
											alert.showAndWait();
											topicBox.setText("");
											questionBox.setText("");
											imageBox.setText("");
											choices.clear();
											otherAlert = true;
											stage.setScene(addQScene);
											break outerloop;
										}
										choices.get(i).setIsCorrect(true);
										System.out.println(choices.get(i).choiceText);
										selected = true;
									}
									arrRadio.get(i).setSelected(false);
								}
								
								choices.clear();
								for(int i = 0; i<arrText.size();i++) {
									if(arrText.get(i).getText().length()>0) {
										choices.add(new Choice(false,arrText.get(i).getText()));
									}							
									arrText.get(i).setText("");
								}
								
								if(!selected && !otherAlert) {
									Alert alert = new Alert(Alert.AlertType.INFORMATION);
									alert.setTitle("ERROR FOUND");
									alert.setHeaderText(null);
									alert.setContentText("No Choice Made | Question Not Added");
									alert.showAndWait();
									topicBox.setText("");
									questionBox.setText("");
									imageBox.setText("");
									choices.clear();
									stage.setScene(addQScene);
								}
							} catch(IndexOutOfBoundsException e) {
								Alert alert = new Alert(Alert.AlertType.INFORMATION);
								alert.setTitle("ERROR FOUND");
								alert.setHeaderText(null);
								alert.setContentText("Invalid Choice | Question Not Added");
								alert.showAndWait();
								stage.setScene(primaryScene);
							}
							if(selected) {
								quiz.addQuestion(topicText, questionText, choices, imageText);
								stage.setScene(primaryScene);
								stage.show();
								System.out.println("Pressed");
							}
						}
					}
				}
		);
		
		HBox submit = new HBox();
		submit.getChildren().setAll(submitButton);
		submit.setAlignment(Pos.BASELINE_CENTER);
		
		design.getChildren().setAll(topLine,topicLine,questionLine,imageLine,choiceLine,submit);
		return new Scene(design);
	}
	
	/**
	 * 
	 * @return Scene to add questions from a JSON File
	 */
	private Scene createAddJSONScene() {
		VBox design = new VBox(10);
		design.setStyle("-fx-background-color: cornsilk; -fx-padding: 15;");
		
		//Return Button to go back to home screen
		Button returnButton = new Button();
		String initStyle = "-fx-font-weight: bold; -fx-background-color: transparent;-fx-font-size: 18px;";
		String hoverStyle = "-fx-background-color: D5D6D6;-fx-font-size: 15px;"
				+ "-fx-border: 1px; -fx-border-color: black; -fx-border-style: solid;";
		returnButton.setText("<--");
		returnButton.setMaxSize(175, 100);
		returnButton.setStyle(initStyle);
		returnButton.setOnMouseEntered(e->returnButton.setStyle(hoverStyle));
		returnButton.setOnMouseExited(e->returnButton.setStyle(initStyle));
		returnButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						stage.setScene(primaryScene);
						stage.show();
						System.out.println("Pressed");
					}
				}
		);
			
		Label label = new Label();
		label.setText("ADD JSON FILE");
		label.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
		
		//Stores title and return button
		HBox topLine = new HBox();
		topLine.setSpacing(75);
		topLine.getChildren().setAll(returnButton,label);
		//topLine.setAlignment(Pos.BASELINE_CENTER);
		topLine.setPadding(new Insets(0,10,15,0));
		
		
		Label JSONFile = new Label();
		JSONFile.setText("Enter JSONFile path: ");
		JSONFile.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		TextArea JSONBox = new TextArea();
		JSONBox.setPrefColumnCount(10);
		JSONBox.setPrefRowCount(1);
		JSONBox.setStyle("-fx-border: 2px; -fx-border-color: #4CAF50;-fx-border-radius: 5px;");
		
		//Stores JSON File path
		HBox JSONLine = new HBox();
		JSONLine.setSpacing(15);
		JSONLine.setPadding(new Insets(0,0,20,0));
		JSONLine.getChildren().setAll(JSONFile, JSONBox);
		
		//Shows sample JSON File
		String sampleJSON = "{ \"questionArray\":\n [\n  {\n   \"meta-data\":\"unused\",\n   "
				+ "\"questionText\": \"If a good hash function is found and a reasonable table size is used for a hash table, then the operations of put\n  , "
				+ "remove, and get should achieve an average time complexity of _____ where $N$ is the number of items and $TS$ is the size of the table.\","
				+ " \n   \"topic\": \"hash table\", \n   \"image\":\"goodhash2_AK.jpg\",\n   \"choiceArray\": \n    [\n     {\"iscorrect\":\"T\",\"choice\":\"$O(1)$\"},"
				+ "\n     {\"iscorrect\":\"F\",\"choice\":\"$O(log N)$\"},"
				+ "\n     {\"iscorrect\":\"F\",\"choice\":\"$O(log_{TS} N)$\"},"
				+ "\n     {\"iscorrect\":\"F\",\"choice\":\"$O(N^{TS})$\"},"
				+ "\n     {\"iscorrect\":\"F\",\"choice\":\"$O(N)$\"}"
				+ "\n   ]\n  }\n ]\n}";
		
		VBox JSONSample = new VBox();
		JSONSample.setSpacing(10);
		Label json = new Label();
		json.setText("Sample JSON File:");
		json.setStyle("-fx-font: italic 14px Georgia, serif;");
		TextArea sampleText = new TextArea();
		sampleText.setText(sampleJSON);
		sampleText.setStyle("-fx-opacity: 1;");
		JSONSample.getChildren().setAll(json,sampleText);
			
		//submits question to be stored
		Button submitButton = new Button();
		submitButton.setText("Enter JSON File");
		submitButton.setMaxSize(150, 100);
		String init = "-fx-background-color: #33FFDC; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-border-radius: 2px; -fx-text-align: center; -fx-text-fill: black; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		String hover = "-fx-background-color: #058984; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-border-radius: 2px; -fx-text-align: center; -fx-text-fill: white; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		submitButton.setStyle(init);
		submitButton.setOnMouseEntered(e->submitButton.setStyle(hover));
		submitButton.setOnMouseExited(e->submitButton.setStyle(init));
		submitButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						String filePath = JSONBox.getText();
						try {
							quiz.addQuestion(filePath);
						} catch(IllegalArgumentException e) {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("ERROR FOUND");
							alert.setHeaderText(null);
							alert.setContentText("Invalid JSON Filepath | Question(s) Not Added");
							alert.showAndWait();
							stage.setScene(primaryScene);
						}
						JSONBox.setText("");
						sampleText.setText(sampleJSON);
						stage.setScene(primaryScene);
						stage.show();
						System.out.println("Pressed");
					}
				}
		);
		HBox submit = new HBox();
		submit.getChildren().setAll(submitButton);
		submit.setAlignment(Pos.BASELINE_CENTER);
		
		
		design.getChildren().setAll(topLine,JSONLine,JSONSample,submit);
		return new Scene(design);
		
		
	}
	
	//Quiz Options Scene
	private Scene createQuizStartScene() {
		VBox design = new VBox();
		design.setStyle("-fx-background-color: cornsilk; -fx-padding: 15;");
		
		//Button to go back to home screen
		Button returnButton = new Button();
		String initStyle = "-fx-font-weight: bold; -fx-background-color: transparent;-fx-font-size: 18px;";
		String hoverStyle = "-fx-background-color: D5D6D6;-fx-font-size: 15px;"
				+ "-fx-border: 1px; -fx-border-color: black; -fx-border-style: solid;";
		returnButton.setText("<--");
		returnButton.setMaxSize(175, 100);
		returnButton.setStyle(initStyle);
		returnButton.setOnMouseEntered(e->returnButton.setStyle(hoverStyle));
		returnButton.setOnMouseExited(e->returnButton.setStyle(initStyle));
		returnButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						stage.setScene(primaryScene);
						stage.show();
						System.out.println("Pressed");
					}
				}
		);
		
		
		//Title
		Label label = new Label();
		label.setText("QUIZ OPTIONS");
		label.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
		
		HBox topLine = new HBox();
		topLine.setSpacing(20);
		topLine.getChildren().setAll(returnButton,label);
		//topLine.setAlignment(Pos.BASELINE_CENTER);
		topLine.setPadding(new Insets(0,0,25,0));
		
		Label topics = new Label();
		topics.setText("Select Topics: ");
		topics.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		//Drop down menu to pick topics
		MenuButton menuButton = new MenuButton();
		menuButton.setPrefSize(100, 30);
		menuButton.setStyle("-fx-mark-color: #058984; -fx-background-color:lightblue;-fx-font: 10px Georgia, serif;");
		menuButton.setOnMouseEntered(e->menuButton.setStyle("-fx-mark-color: lightblue; -fx-background-color:#058984;-fx-font: 10px Georgia, serif;"));
		menuButton.setOnMouseExited(e->menuButton.setStyle("-fx-mark-color: #058984; -fx-background-color:lightblue;-fx-font: 10px Georgia, serif;"));
		ArrayList<CheckBox> listCheckMenu= new ArrayList<CheckBox>();
		ArrayList<CustomMenuItem> listMenuItem= new ArrayList<CustomMenuItem>();
			 
		menuButton.setOnMouseEntered(e ->{
			menuButton.setStyle("-fx-mark-color: lightblue; -fx-background-color:#058984;-fx-font: 10px Georgia, serif;");
			List<String> topic = quiz.getTopicNames();
			
			listCheckMenu.clear();
			listMenuItem.clear();
			
			//Loops through all topics and adds them to drop down box
			for(int k = 0; k<topic.size();k++) {
				listCheckMenu.add(new CheckBox(topic.get(k)));
				System.out.println(topic.get(k));
				listCheckMenu.get(k).setStyle("-fx-font: 10px Georgia, serif;");
				listMenuItem.add(new CustomMenuItem(listCheckMenu.get(k)));
				listMenuItem.get(k).setHideOnClick(false);			
			
			}
			menuButton.getItems().setAll();
			menuButton.getItems().setAll(listMenuItem);
		});
		
		
		HBox topicBox = new HBox();
		topicBox.setSpacing(20);
		topicBox.getChildren().setAll(topics,menuButton);
		//topLine.setAlignment(Pos.BASELINE_CENTER);
		topicBox.setPadding(new Insets(0,0,20,0));
		
		//Stores number of questions user wants
		Label numQ = new Label();
		numQ.setText("# of Questions: ");
		numQ.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		TextArea sampleText = new TextArea();
		sampleText.setPrefColumnCount(7);
		sampleText.setPrefRowCount(1);
		sampleText.setStyle("-fx-border: 2px; -fx-border-color: lightblue;-fx-border-radius: 5px;");
		
		HBox qBox = new HBox();
		qBox.setSpacing(10);
		qBox.getChildren().setAll(numQ,sampleText);
		qBox.setPadding(new Insets(0,0,35,0));
		
		//Button to submit quiz options
		Button submitButton = new Button();
		submitButton.setText("Take Quiz");
		submitButton.setMaxSize(150, 100);
		String init = "-fx-background-color: lightblue; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-border-radius: 2px; -fx-text-align: center; -fx-text-fill: black; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		String hover = "-fx-background-color: #058984; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-border-radius: 2px; -fx-text-align: center; -fx-text-fill: white; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		submitButton.setStyle(init);
		submitButton.setOnMouseEntered(e->submitButton.setStyle(hover));
		submitButton.setOnMouseExited(e->submitButton.setStyle(init));
		submitButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						List<Topic> topics = new ArrayList<Topic>();
						int size = 0;
						//gets topics selected and number of questions
						for(int i = 0; i<listCheckMenu.size();i++) {
							if(listCheckMenu.get(i).isSelected()) {
								topics.add(quiz.getTopic(listCheckMenu.get(i).getText()));
								size+= quiz.getTopic(listCheckMenu.get(i).getText()).getQuestionList().size();
							}
							
						}
						
						try {
							numQuestions = Integer.parseInt(sampleText.getText());
						} catch (Exception e) {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("ERROR FOUND");
							alert.setHeaderText(null);
							alert.setContentText("Number of Question Invalid | Quiz Not Started");
							alert.showAndWait();
							stage.setScene(quizStartScene);
						}
						
						try {
							if(numQuestions == 0) {
								throw new Exception();
							}
						} catch (Exception e) {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("ERROR FOUND");
							alert.setHeaderText(null);
							alert.setContentText("Number of Questions Can't Be Zero");
							alert.showAndWait();
							stage.setScene(quizStartScene);
						}
						
						if(numQuestions > size) {
							numQuestions = size;
						}
						
						//calls startQuiz method to generate quiz
						sampleText.setText("");
						quizQuestions = quiz.startQuiz(topics, numQuestions);
						
						if(topics.size()==0) {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("ERROR FOUND");
							alert.setHeaderText(null);
							alert.setContentText("No Topics Selected | Quiz Not Started");
							alert.showAndWait();
							stage.setScene(quizStartScene);
						}
						//calls scene to show quiz
						else {
							quizLoop(0);
						}

							
					}
				}
		);
		HBox submit = new HBox();
		submit.getChildren().setAll(submitButton);
		submit.setAlignment(Pos.BASELINE_CENTER);

		design.getChildren().setAll(topLine,topicBox,qBox,submit);
		return new Scene(design);
		
	}		
	
	private void quizLoop(int currentQuestion) {
		List<ToggleButton> toggleGroup = new ArrayList<ToggleButton>();
		List<String> styles = new ArrayList<String>();
		//Sets Question Number
		Label questionNumber = new Label();
		questionNumber.setText("Question " + (currQuestion+1));
		questionNumber.setStyle("-fx-font-weight: bold; -fx-font-size: 40px;");
			
		ToggleGroup choiceGroup = new ToggleGroup();
		
		//Creates five possible choices
		ToggleButton choiceOne = new ToggleButton();
		toggleGroup.add(choiceOne);
		choiceOne.setToggleGroup(choiceGroup);
		choiceOne.setMinSize(250, 100);
		choiceOne.setMaxSize(250, 100);
		String initStyle1 = "-fx-background-color: #4CAF50;-fx-border: none; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		String hoverStyle1 = "-fx-background-color: #439747;-fx-border: 15px; -fx-border-color: black; -fx-border-style: solid; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		styles.add(initStyle1);
		choiceOne.setStyle(initStyle1);
		
		ToggleButton choiceTwo = new ToggleButton();
		toggleGroup.add(choiceTwo);
		choiceTwo.setToggleGroup(choiceGroup);
		choiceTwo.setMinSize(250, 100);
		choiceTwo.setMaxSize(250, 100);
		String initStyle2 = "-fx-background-color: #007AFF;-fx-border: none; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white; -fx-background-radius: 5px;";
		String hoverStyle2 = "-fx-background-color: #045BBA;-fx-border: 15px; -fx-border-color: black; -fx-border-style: solid; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		styles.add(initStyle2);
		choiceTwo.setStyle(initStyle2);
		
		ToggleButton choiceThree = new ToggleButton();
		toggleGroup.add(choiceThree);
		choiceThree.setToggleGroup(choiceGroup);
		choiceThree.setMinSize(250, 100);
		choiceThree.setMaxSize(250, 100);
		String initStyle3 = "-fx-background-color: #FF003E;-fx-border: none; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		String hoverStyle3 =  "-fx-background-color: #D00335;-fx-border: 15px; -fx-border-color: black; -fx-border-style: solid; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		styles.add(initStyle3);
		choiceThree.setStyle(initStyle3);
		
		ToggleButton choiceFour = new ToggleButton();
		toggleGroup.add(choiceFour);
		choiceFour.setToggleGroup(choiceGroup);
		choiceFour.setMinSize(250, 100);
		choiceFour.setMaxSize(250, 100);
		String initStyle4 = "-fx-background-color: #FFAD00;-fx-border: none; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		String hoverStyle4 =  "-fx-background-color: #E09903;-fx-border: 15px; -fx-border-color: black; -fx-border-style: solid; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		styles.add(initStyle4);
		choiceFour.setStyle(initStyle4);
		
		ToggleButton choiceFive = new ToggleButton();
		toggleGroup.add(choiceFive);
		choiceFive.setToggleGroup(choiceGroup);
		choiceFive.setMinSize(250, 100);
		choiceFive.setMaxSize(250, 100);
		String initStyle5 = "-fx-background-color: #B600FF;-fx-border: none; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		String hoverStyle5 =  "-fx-background-color: #8705BB;-fx-border: 15px; -fx-border-color: black; -fx-border-style: solid; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		styles.add(initStyle5);
		choiceFive.setStyle(initStyle5);
		
		//allows only for one choice to be selected
		choiceOne.setOnAction(e->{
			if(choiceOne.isSelected()) {
				choiceOne.setStyle(hoverStyle1);
				selectedChoice = choiceOne.getText();
			}
			else{
				choiceOne.setStyle(initStyle1);
			}
			
			choiceTwo.setSelected(false);
			choiceTwo.setStyle(initStyle2);
			
			choiceThree.setSelected(false);
			choiceThree.setStyle(initStyle3);
			
			choiceFour.setSelected(false);
			choiceFour.setStyle(initStyle4);
			
			choiceFive.setSelected(false);
			choiceFive.setStyle(initStyle5);
		});
		
		choiceTwo.setOnAction(e->{
			if(choiceTwo.isSelected()) {
				choiceTwo.setStyle(hoverStyle2);
				selectedChoice = choiceTwo.getText();
			}
			else{
				choiceTwo.setStyle(initStyle2);
			}
			choiceOne.setSelected(false);
			choiceOne.setStyle(initStyle1);
			
			choiceThree.setSelected(false);
			choiceThree.setStyle(initStyle3);
			
			choiceFour.setSelected(false);
			choiceFour.setStyle(initStyle4);
			
			choiceFive.setSelected(false);
			choiceFive.setStyle(initStyle5);
		});
		
		choiceThree.setOnAction(e->{
			if(choiceThree.isSelected()) {
				choiceThree.setStyle(hoverStyle3);
				selectedChoice = choiceThree.getText();
			}
			else{
				choiceThree.setStyle(initStyle3);
			}
			choiceTwo.setSelected(false);
			choiceTwo.setStyle(initStyle2);
			
			choiceOne.setSelected(false);
			choiceOne.setStyle(initStyle1);
			
			choiceFour.setSelected(false);
			choiceFour.setStyle(initStyle4);
			
			choiceFive.setSelected(false);
			choiceFive.setStyle(initStyle5);
		});
		choiceThree.setStyle(initStyle3);
		
		
		choiceFour.setOnAction(e->{
			if(choiceFour.isSelected()) {
				choiceFour.setStyle(hoverStyle4);
				selectedChoice = choiceFour.getText();
			}
			else{
				choiceFour.setStyle(initStyle4);
			}
			choiceTwo.setSelected(false);
			choiceTwo.setStyle(initStyle2);
			
			choiceThree.setSelected(false);
			choiceThree.setStyle(initStyle3);
			
			choiceOne.setSelected(false);
			choiceOne.setStyle(initStyle1);
			
			choiceFive.setSelected(false);
			choiceFive.setStyle(initStyle5);
		});
		
		choiceFive.setOnAction(e->{
			if(choiceFive.isSelected()) {
				choiceFive.setStyle(hoverStyle5);
				selectedChoice = choiceFive.getText();
			}
			else{
				choiceFive.setStyle(initStyle5);
			}
			choiceTwo.setSelected(false);
			choiceTwo.setStyle(initStyle2);
			
			choiceThree.setSelected(false);
			choiceThree.setStyle(initStyle3);
			
			choiceFour.setSelected(false);
			choiceFour.setStyle(initStyle4);
			
			choiceOne.setSelected(false);
			choiceOne.setStyle(initStyle1);
		});	
		
		//Sets the text for however many choices a question has
		int size = 0;
		System.out.println(" efrfedede" + quizQuestions.get(currQuestion).getChoices().size());
		try {
			for(int j = 0; j<quizQuestions.get(currQuestion).getChoices().size();j++) {
				toggleGroup.get(j).setText(quizQuestions.get(currQuestion).getChoices().get(j).getChoiceText());
			}
		} catch (Exception e) {
			for(int j = 0; j<quizQuestions.get(currQuestion).getChoices().size();j++) {
				toggleGroup.get(j).setText(quizQuestions.get(currQuestion).getChoices().get(j).getChoiceText());
			}
		}
		//if all choices not used remove the extra choice buttons
		for(int k = 0; k< toggleGroup.size();k++) {
			if(toggleGroup.get(k).getText().length()<=0) {
				toggleGroup.get(k).setStyle("-fx-background-color: white;-fx-border: none; -fx-color: white;");
				toggleGroup.get(k).setDisable(true);
			}
			else {
				size++;
			}
		}
		
		HBox topLineChoice = new HBox();
		topLineChoice.setSpacing(7);
		topLineChoice.setPadding(new Insets(0,20,10,20));
		topLineChoice.setAlignment(Pos.BASELINE_CENTER);
		
			
		HBox bottomLineChoice = new HBox();
		bottomLineChoice.setSpacing(7);
		bottomLineChoice.setPadding(new Insets(0,20,30,20));
		bottomLineChoice.setAlignment(Pos.BASELINE_CENTER);
		
		//formats the choice button depending on number of choices the question has
		if(size == 1) {
			topLineChoice.getChildren().setAll(choiceOne);
		}
		
		if(size == 2) {
			topLineChoice.getChildren().setAll(choiceOne,choiceTwo);
		}
		
		if(size == 3) {
			topLineChoice.getChildren().setAll(choiceOne,choiceTwo);
			bottomLineChoice.getChildren().setAll(choiceThree);
		}
		
		if(size == 4) {
			topLineChoice.getChildren().setAll(choiceOne,choiceTwo);
			bottomLineChoice.getChildren().setAll(choiceThree,choiceFour);
		}
		
		if(size == 5) {
			topLineChoice.getChildren().setAll(choiceOne,choiceTwo,choiceThree);
			bottomLineChoice.getChildren().setAll(choiceFour,choiceFive);
		}
				
		//but to go to next question
		Button nextButton = new Button();
		String initStyleN = "-fx-font-weight: bold; -fx-background-color: D5D6D6;-fx-font-size: 24px;-fx-background-radius: 10px";
		String hoverStyleN = "-fx-font-weight: bold; -fx-background-color: C5C6C6;-fx-font-size: 24px;-fx-background-radius: 10px";
		nextButton.setText("Next");
		nextButton.setMaxSize(225, 150);
		nextButton.setStyle(initStyleN);
		nextButton.setOnMouseEntered(e->nextButton.setStyle(hoverStyleN));
		nextButton.setOnMouseExited(e->nextButton.setStyle(initStyleN));
		nextButton.setOnAction(e -> {
					//base case to see if its past num questions in this quiz
					if(currQuestion >= numQuestions) {
						resultsScene = createResultsScene();
						stage.setScene(resultsScene);
						stage.show();
					}
					else{
						//checks if answer is correct
						if(selectedChoice.trim().equals(quizQuestions.get(currQuestion).correctChoice().trim())){
							numCorrect++;
						}
						//special case for last question
						if(currQuestion == numQuestions -1) {
							quizLoop(currQuestion);
							resultsScene = createResultsScene();
							stage.setScene(resultsScene);
							stage.show();
						}
						//recursively calls quizLoop with next question
						else {
							currQuestion++;
							quizLoop(currQuestion);
						}
					}
				}
		);
		
		HBox topLine = new HBox();
		topLine.setSpacing(100);
		topLine.setAlignment(Pos.BASELINE_RIGHT);
		topLine.setPadding(new Insets(0,25,25,0));
		topLine.getChildren().setAll(nextButton);
		
		HBox top2Line = new HBox();
		top2Line.setSpacing(100);
		top2Line.setAlignment(Pos.BASELINE_CENTER);
		top2Line.setPadding(new Insets(0,100,25,50));
		top2Line.getChildren().setAll(questionNumber);

		//Sets image to be displayed in quiz
		Image image;
		//checks if an image url was entered, if not use temp image
		if(quizQuestions.get(currQuestion).getImageString().trim().equals("none") || quizQuestions.get(currQuestion).getImageString().trim().length() == 0){
			image = new Image("quizTemp.jpg");
		}
		else {
			//tries displaying given image. If error is caused use default image.
			try {
				image = new Image(quizQuestions.get(currQuestion).getImageString());
			}
			catch(IllegalArgumentException e) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("ERROR FOUND");
				alert.setHeaderText(null);
				alert.setContentText("Invalid Image URL | Default Image Shown");
				alert.showAndWait();
				image = new Image("quizTemp.jpg");
			}
		}
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(200);
		imageView.setFitWidth(200);
		
		HBox imageBox = new HBox();
		imageBox.setSpacing(10);
		imageBox.setAlignment(Pos.BASELINE_CENTER);
		imageBox.getChildren().setAll(imageView);
		imageBox.setPadding(new Insets(0,0,25,0));
		
		Label questionText = new Label();
		questionText.setText(quizQuestions.get(currQuestion).getQuestionText());
		questionText.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
		
		HBox questionBox = new HBox();
		questionBox.getChildren().setAll(questionText);
		questionBox.setPadding(new Insets(0,20,20,20));
		questionBox.setAlignment(Pos.BASELINE_CENTER);
		
		//Submitting quiz skips rest of question and goes to result screen
		Button submitButton = new Button();
		submitButton.setText("Submit Quiz");
		submitButton.setMaxSize(150, 100);
		String init = "-fx-background-color: lightblue; -fx-color: black; "
				+ "-fx-background-radius: 5px; -fx-text-align: center; -fx-text-fill: black; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		String hover = "-fx-background-color: #058984; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-background-radius: 5px; -fx-text-align: center; -fx-text-fill: white; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		submitButton.setStyle(init);
		submitButton.setOnMouseEntered(e->submitButton.setStyle(hover));
		submitButton.setOnMouseExited(e->submitButton.setStyle(init));
		submitButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						if(selectedChoice.equals(quizQuestions.get(currQuestion).correctChoice())){
							numCorrect++;
						}
						resultsScene = createResultsScene();
						stage.setScene(resultsScene);
						stage.show();
					}
				}
		);
		
		HBox submit = new HBox();
		submit.getChildren().setAll(submitButton);
		submit.setPadding(new Insets(0,50,20,50));
		submit.setAlignment(Pos.BASELINE_RIGHT);
		
		
		VBox design = new VBox();
		design.setStyle("-fx-background-color: white; -fx-padding: 15;");
		design.getChildren().setAll(topLine,top2Line,questionBox,imageBox,topLineChoice,bottomLineChoice,submit);
		Scene quiz = new Scene(design);
		stage.setScene(quiz);
	}
	
	/**
	 * @return results of user's quiz
	 */
	private Scene createResultsScene() {
		Label questionNumber = new Label();
		questionNumber.setText("Quiz Results");
		questionNumber.setStyle("-fx-font-weight: bold; -fx-font-size: 40px;");
		
		HBox topLine = new HBox();
		topLine.getChildren().setAll(questionNumber);
		topLine.setPadding(new Insets(0,20,35,20));
		topLine.setAlignment(Pos.CENTER);
		
		//Number correct questions
		Label correct = new Label();
		correct.setText("Correct: " + numCorrect);
		correct.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		//Number incorrect questions
		Label incorrect = new Label();
		incorrect.setText("Incorrect: " + (numQuestions-numCorrect));
		incorrect.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		/**
		 * Percent of correct questions
		 */
		Label percentCorrect = new Label();
		percentCorrect.setText("% Correct: " + (int)((double)(numCorrect)/(double)(numQuestions) * 100) + "%");
		percentCorrect.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		VBox questionResults = new VBox();
		questionResults.setSpacing(25);
		questionResults.getChildren().setAll(correct,incorrect,percentCorrect);
		questionResults.setPadding(new Insets(0,20,40,20));
		
		//Button to go back to home screen
		Button addQuestionButton = new Button();
		addQuestionButton.setText("Add Questions");
		addQuestionButton.setMaxSize(120, 50);
		addQuestionButton.setMinSize(120, 50);
		String init = "-fx-background-color: palegreen; -fx-color: black; -fx-font-weight: bold;"
				+ "-fx-background-radius: 5px; -fx-text-align: center; -fx-font-weight: bold;-fx-text-fill: black; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		String hover = "-fx-background-color: mediumseagreen; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-background-radius: 5px; -fx-text-align: center; -fx-text-fill: white; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		addQuestionButton.setStyle(init);
		addQuestionButton.setOnMouseEntered(e->addQuestionButton.setStyle(hover));
		addQuestionButton.setOnMouseExited(e->addQuestionButton.setStyle(init));
		addQuestionButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						currQuestion = 0;
						quizQuestions.clear();
						numCorrect = 0;
						choices.clear();
						stage.setScene(primaryScene);
						stage.show();
					}
				}
		);		
		
		//Button to change quiz options
		Button returnQuizButton = new Button();
		returnQuizButton.setText("Retake Quiz");
		returnQuizButton.setMaxSize(120, 50);
		returnQuizButton.setMinSize(120, 50);
		String init1 = "-fx-background-color: lightblue; -fx-color: black; -fx-font-weight: bold;"
				+ "-fx-background-radius: 5px; -fx-text-align: center; -fx-font-weight: bold;-fx-text-fill: black; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		String hover1 = "-fx-background-color: #058984; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-background-radius: 5px; -fx-text-align: center; -fx-text-fill: white; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		returnQuizButton.setStyle(init1);
		returnQuizButton.setOnMouseEntered(e->returnQuizButton.setStyle(hover1));
		returnQuizButton.setOnMouseExited(e->returnQuizButton.setStyle(init1));
		returnQuizButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						currQuestion = 0;
						quizQuestions.clear();
						numCorrect = 0;
						choices.clear();
						stage.setScene(quizStartScene);
						stage.show();
					}
				}
		);
		
		//Button to quit Program, goes to screen to add json file of questions
		Button quitButton = new Button();
		quitButton.setText("Quit");
		quitButton.setMaxSize(120, 50);
		quitButton.setMinSize(120, 50);
		String init2 = "-fx-background-color: red; -fx-color: black; -fx-font-weight: bold;"
				+ "-fx-background-radius: 5px; -fx-text-align: center;-fx-font-weight: bold; -fx-text-fill: black; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		String hover2 = "-fx-background-color: #CA1010; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-background-radius: 5px; -fx-text-align: center; -fx-text-fill: white; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		quitButton.setStyle(init2);
		quitButton.setOnMouseEntered(e->quitButton.setStyle(hover2));
		quitButton.setOnMouseExited(e->quitButton.setStyle(init2));
		quitButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						currQuestion = 0;
						quizQuestions.clear();
						numCorrect = 0;
						choices.clear();
						stage.setScene(saveJSONScene);
						stage.show();
					}
				}
		);
		
		
		VBox design = new VBox(topLine);
		design.setStyle("-fx-background-color: cornsilk; -fx-padding: 15;");
			
		HBox botLine = new HBox();
		botLine.getChildren().setAll(addQuestionButton, returnQuizButton,quitButton);
		botLine.setPadding(new Insets(0,20,20,20));
		botLine.setSpacing(100);
		
		design.getChildren().setAll(topLine,questionResults,botLine);
		return new Scene(design);
	}
	
	/**
	 * 
	 * @return Scene to add JSON File name if they want to store questions
	 */
	public Scene createSaveJSONScene() {
		
		Label JSONTitle = new Label();
		JSONTitle.setText("Save Questions to JSON File");
		JSONTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 40px;");
		
		HBox topLine = new HBox();
		topLine.getChildren().setAll(JSONTitle);
		topLine.setPadding(new Insets(0,20,35,20));
		topLine.setAlignment(Pos.CENTER);
		
		Label JSONFile = new Label();
		JSONFile.setText("Enter JSONFile Name: ");
		JSONFile.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		TextArea JSONBox = new TextArea();
		JSONBox.setPrefColumnCount(10);
		JSONBox.setPrefRowCount(1);
		JSONBox.setStyle("-fx-border: 2px; -fx-border-color: #4CAF50;-fx-border-radius: 5px;");
		
		HBox JSONLine = new HBox();
		JSONLine.setSpacing(15);
		JSONLine.setPadding(new Insets(30,50,50,50));
		JSONLine.getChildren().setAll(JSONFile, JSONBox);
		
		//Button to save questions and exit program
		Button saveButton = new Button();
		saveButton.setText("Save");
		saveButton.setMaxSize(120, 50);
		saveButton.setMinSize(120, 50);
		String init1 = "-fx-background-color: lightblue; -fx-color: black; -fx-font-weight: bold;"
				+ "-fx-background-radius: 5px; -fx-text-align: center; -fx-font-weight: bold;-fx-text-fill: black; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		String hover1 = "-fx-background-color: #058984; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-background-radius: 5px; -fx-text-align: center; -fx-text-fill: white; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		saveButton.setStyle(init1);
		saveButton.setOnMouseEntered(e->saveButton.setStyle(hover1));
		saveButton.setOnMouseExited(e->saveButton.setStyle(init1));
		saveButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						try {
							quiz.toJSONFile(JSONBox.getText().trim());
						} catch (IOException e) {}
						currQuestion = 0;
						quizQuestions.clear();
						numCorrect = 0;
						choices.clear();
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("SAVE QUESTIONS TO JSON FILE");
						alert.setHeaderText(null);
						alert.setContentText("Questions Saved to " + JSONBox.getText().trim());
						alert.showAndWait();
						System.exit(0);
					}
				}
		);
		
		//Button to quit program
		Button quitButton = new Button();
		quitButton.setText("Exit w/o Saving");
		quitButton.setMaxSize(120, 50);
		quitButton.setMinSize(120, 50);
		String init2 = "-fx-background-color: red; -fx-color: black; -fx-font-weight: bold;"
				+ "-fx-background-radius: 5px; -fx-text-align: center;-fx-font-weight: bold; -fx-text-fill: black; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 12px;";
		String hover2 = "-fx-background-color: #CA1010; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-background-radius: 5px; -fx-text-align: center; -fx-text-fill: white; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 12px;";
		quitButton.setStyle(init2);
		quitButton.setOnMouseEntered(e->quitButton.setStyle(hover2));
		quitButton.setOnMouseExited(e->quitButton.setStyle(init2));
		quitButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						currQuestion = 0;
						quizQuestions.clear();
						numCorrect = 0;
						choices.clear();
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("QUESTIONS NOT SAVED");
						alert.setHeaderText(null);
						alert.setContentText("Exiting Without Saving Questions");
						alert.showAndWait();
						System.exit(0);
					}
				}
		);
		
		HBox buttonLine = new HBox();
		buttonLine.setSpacing(150);
		buttonLine.getChildren().setAll(saveButton,quitButton);
		buttonLine.setPadding(new Insets(0,20,35,20));
		buttonLine.setAlignment(Pos.CENTER);
		
		VBox design = new VBox();
		design.setStyle("-fx-background-color: cornsilk; -fx-padding: 15;");
		design.getChildren().setAll(topLine,JSONLine,buttonLine);
		return new Scene(design);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
