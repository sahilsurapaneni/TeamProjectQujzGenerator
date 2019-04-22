package application;
	
import java.util.ArrayList;

import javafx.application.Application;
//import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Scene primaryScene;
	private Scene addQScene;
	private Scene addJSONScene;
	private Scene quizStartScene;
	private Scene mainQuizScene;
	private Scene resultsScene;
	private Label label;
	
	private int questionNum = 1;
	private ArrayList<String> choices= new ArrayList<String>();
	
	private Stage stage;
	
	public void start(Stage primaryStage) {
		stage = primaryStage;
		
		primaryScene = createPrimaryScene();
		addQScene = createAddQScene();
		addJSONScene = createAddJSONScene();
		quizStartScene = createQuizStartScene();
		mainQuizScene = createMainQuizScene();
		resultsScene = createResultsScene();
		
		
		stage.setScene(primaryScene);
		stage.show();
	}
	
	private Scene createPrimaryScene() {
		VBox design = new VBox(10);
		design.setStyle("-fx-background-color: cornsilk; -fx-padding: 15;");
			
		Label label = new Label();
		label.setText("QUIZ GENERATOR SYSTEM");
		label.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
		label.setAlignment(Pos.BASELINE_CENTER);
		
		HBox labelBox = new HBox();
		labelBox.getChildren().setAll(label);
		labelBox.setAlignment(Pos.BASELINE_CENTER);
		
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
						stage.setScene(quizStartScene);
						stage.show();
						System.out.println("Pressed");
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
				
		design.getChildren().setAll(labelBox,hbox,tempBox);
		return new Scene(design);
		
		
	}
	
	@SuppressWarnings("unused")
	private Scene createAddQScene() {
		VBox design = new VBox(10);
		design.setStyle("-fx-background-color: cornsilk; -fx-padding: 15;");
		
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
		
		HBox questionLine = new HBox();
		questionLine.setSpacing(10);
		questionLine.setPadding(new Insets(0,0,7,0));
		questionLine.getChildren().setAll(question, questionBox);
		
		
		Label choice = new Label();
		choice.setText("Enter Choices: ");
		choice.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		Label choiceInstructions = new Label();
		choiceInstructions.setText("(select correct option)");
		choiceInstructions.setStyle("-fx-font: italic 10px Georgia, serif;");
		
		VBox choices = new VBox();
		choices.getChildren().setAll(choice,choiceInstructions);
		
		HBox choiceLine = new HBox();
		choiceLine.setSpacing(20);
		choiceLine.setPadding(new Insets(0,0,7,0));
		
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
					public void handle(ActionEvent t) {
						System.out.println(topicBox.getText());
						topicBox.setText("");
						System.out.println(questionBox.getText());
						questionBox.setText("");
						for(int i = 0; i<arrText.size();i++) {
							System.out.println(arrText.get(i).getText());
							arrText.get(i).setText("");
						}
						stage.setScene(primaryScene);
						stage.show();
						System.out.println("Pressed");
					}
				}
		);
		HBox submit = new HBox();
		submit.getChildren().setAll(submitButton);
		submit.setAlignment(Pos.BASELINE_CENTER);
			
		
		design.getChildren().setAll(topLine,topicLine,questionLine,choiceLine,submit);
		return new Scene(design);
	}
	
	private Scene createAddJSONScene() {
		VBox design = new VBox(10);
		design.setStyle("-fx-background-color: cornsilk; -fx-padding: 15;");
		
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
		
		HBox JSONLine = new HBox();
		JSONLine.setSpacing(15);
		JSONLine.setPadding(new Insets(0,0,20,0));
		JSONLine.getChildren().setAll(JSONFile, JSONBox);
		
		
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
						System.out.println(JSONBox.getText());
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
	
	private Scene createQuizStartScene() {
		VBox design = new VBox();
		design.setStyle("-fx-background-color: cornsilk; -fx-padding: 15;");
		
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
		
		MenuButton menuButton = new MenuButton();
		menuButton.setPrefSize(100, 30);
		menuButton.setStyle("-fx-mark-color: #058984; -fx-background-color:lightblue;-fx-font: 10px Georgia, serif;");
		menuButton.setOnMouseEntered(e->menuButton.setStyle("-fx-mark-color: lightblue; -fx-background-color:#058984;-fx-font: 10px Georgia, serif;"));
		menuButton.setOnMouseExited(e->menuButton.setStyle("-fx-mark-color: #058984; -fx-background-color:lightblue;-fx-font: 10px Georgia, serif;"));
		ArrayList<CheckBox> listCheckMenu= new ArrayList<CheckBox>();
		ArrayList<CustomMenuItem> listMenuItem= new ArrayList<CustomMenuItem>();
		
		for(int i = 0; i<5;i++) {
			listCheckMenu.add(new CheckBox("Topic: " + i));
			listCheckMenu.get(i).setStyle("-fx-font: 10px Georgia, serif;");
			listMenuItem.add(new CustomMenuItem(listCheckMenu.get(i)));
			listMenuItem.get(i).setHideOnClick(false);
			menuButton.getItems().add(listMenuItem.get(i));
		}
		
		for(int i = 0; i<listCheckMenu.size();i++) {
			listCheckMenu.get(i).setOnAction(
				new EventHandler<ActionEvent>() {
					int i = 0;
					int fontSize = 10;
					public void handle(ActionEvent t) {
						menuButton.setText(menuButton.getText() + " | " + listCheckMenu.get(i).getText());
						i++;
					}
				}
			);
		}
		
		HBox topicBox = new HBox();
		topicBox.setSpacing(20);
		topicBox.getChildren().setAll(topics,menuButton);
		//topLine.setAlignment(Pos.BASELINE_CENTER);
		topicBox.setPadding(new Insets(0,0,20,0));
		
		
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
						for(int i = 0; i<listCheckMenu.size();i++) {
							if(listCheckMenu.get(i).isSelected()) {
								System.out.println(listCheckMenu.get(i).getText());
								listCheckMenu.get(i).setSelected(false);;
							}
						}
						//System.out.println(Integer.parseInt(sampleText.getText()));
						sampleText.setText("");
						stage.setScene(mainQuizScene);
						stage.show();
					}
				}
		);
		HBox submit = new HBox();
		submit.getChildren().setAll(submitButton);
		submit.setAlignment(Pos.BASELINE_CENTER);

		design.getChildren().setAll(topLine,topicBox,qBox,submit);
		return new Scene(design);
		
	}
	
	private Scene createMainQuizScene() {
		Label questionNumber = new Label();
		questionNumber.setText("Question " + questionNum);
		questionNumber.setStyle("-fx-font-weight: bold; -fx-font-size: 40px;");
			
		ToggleGroup choiceGroup = new ToggleGroup();
		
		ToggleButton choiceOne = new ToggleButton();
		choiceOne.setToggleGroup(choiceGroup);
		choiceOne.setMinSize(250, 100);
		choiceOne.setMaxSize(250, 100);
		choiceOne.setText("choice 1");
		String initStyle1 = "-fx-background-color: #4CAF50;-fx-border: none; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		String hoverStyle1 = "-fx-background-color: #439747;-fx-border: 15px; -fx-border-color: black; -fx-border-style: solid; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		choiceOne.setOnAction(e->{
			if(choiceOne.isSelected()) {
				choiceOne.setStyle(hoverStyle1);
			}
			else{
				choiceOne.setStyle(initStyle1);
			}
		});
		choiceOne.setStyle(initStyle1);
		
		ToggleButton choiceTwo = new ToggleButton();
		choiceTwo.setToggleGroup(choiceGroup);
		choiceTwo.setMinSize(250, 100);
		choiceTwo.setMaxSize(250, 100);
		choiceTwo.setText("choice 2");
		String initStyle2 = "-fx-background-color: #007AFF;-fx-border: none; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white; -fx-background-radius: 5px;";
		String hoverStyle2 = "-fx-background-color: #045BBA;-fx-border: 15px; -fx-border-color: black; -fx-border-style: solid; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		choiceTwo.setOnAction(e->{
			if(choiceTwo.isSelected()) {
				choiceTwo.setStyle(hoverStyle2);
			}
			else{
				choiceTwo.setStyle(initStyle2);
			}
		});
		choiceTwo.setStyle(initStyle2);
		
		ToggleButton choiceThree = new ToggleButton();
		choiceThree.setToggleGroup(choiceGroup);
		choiceThree.setMinSize(250, 100);
		choiceThree.setMaxSize(250, 100);
		choiceThree.setText("choice 3");
		String initStyle3 = "-fx-background-color: #FF003E;-fx-border: none; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		String hoverStyle3 =  "-fx-background-color: #D00335;-fx-border: 15px; -fx-border-color: black; -fx-border-style: solid; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		choiceThree.setOnAction(e->{
			if(choiceThree.isSelected()) {
				choiceThree.setStyle(hoverStyle3);
			}
			else{
				choiceThree.setStyle(initStyle3);
			}
		});
		choiceThree.setStyle(initStyle3);
		
		ToggleButton choiceFour = new ToggleButton();
		choiceFour.setToggleGroup(choiceGroup);
		choiceFour.setMinSize(250, 100);
		choiceFour.setMaxSize(250, 100);
		choiceFour.setText("choice 4");
		String initStyle4 = "-fx-background-color: #FFAD00;-fx-border: none; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		String hoverStyle4 =  "-fx-background-color: #E09903;-fx-border: 15px; -fx-border-color: black; -fx-border-style: solid; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		choiceFour.setOnAction(e->{
			if(choiceFour.isSelected()) {
				choiceFour.setStyle(hoverStyle4);
			}
			else{
				choiceFour.setStyle(initStyle4);
			}
		});
		choiceFour.setStyle(initStyle4);
		
		ToggleButton choiceFive = new ToggleButton();
		choiceFive.setToggleGroup(choiceGroup);
		choiceFive.setMinSize(250, 100);
		choiceFive.setMaxSize(250, 100);
		choiceFive.setText("choice 5");
		String initStyle5 = "-fx-background-color: #B600FF;-fx-border: none; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		String hoverStyle5 =  "-fx-background-color: #8705BB;-fx-border: 15px; -fx-border-color: black; -fx-border-style: solid; -fx-color: white; -fx-font-weight: bold;"
				+ "-fx-text-align: left; -fx-text-decoration: none; fx-font-size: 20px; -fx-text-fill: white;-fx-background-radius: 5px;";
		choiceFive.setOnAction(e->{
			if(choiceFive.isSelected()) {
				choiceFive.setStyle(hoverStyle5);
			}
			else{
				choiceFive.setStyle(initStyle5);
			}
		});
		choiceFive.setStyle(initStyle5);
		
		HBox topLineChoice = new HBox();
		topLineChoice.setSpacing(7);
		topLineChoice.setPadding(new Insets(0,20,10,20));
		topLineChoice.setAlignment(Pos.BASELINE_CENTER);
		topLineChoice.getChildren().setAll(choiceOne,choiceTwo,choiceThree);
		
		HBox bottomLineChoice = new HBox();
		bottomLineChoice.setSpacing(7);
		bottomLineChoice.setPadding(new Insets(0,20,30,20));
		bottomLineChoice.setAlignment(Pos.BASELINE_CENTER);
		bottomLineChoice.getChildren().setAll(choiceFour,choiceFive);
		
		Button returnButton = new Button();
		String initStyle = "-fx-font-weight: bold; -fx-background-color: D5D6D6;-fx-font-size: 24px;-fx-background-radius: 10px";
		String hoverStyle = "-fx-font-weight: bold; -fx-background-color: C5C6C6;-fx-font-size: 24px;-fx-background-radius: 10px";
		returnButton.setText("Prev");
		returnButton.setMaxSize(225, 150);
		returnButton.setStyle(initStyle);
		returnButton.setOnMouseEntered(e->returnButton.setStyle(hoverStyle));
		returnButton.setOnMouseExited(e->returnButton.setStyle(initStyle));
		returnButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						if(questionNum>1)
							questionNum--;
							questionNumber.setText("Question " + questionNum);
					}
				}
		);
		
		Button nextButton = new Button();
		String initStyleN = "-fx-font-weight: bold; -fx-background-color: D5D6D6;-fx-font-size: 24px;-fx-background-radius: 10px";
		String hoverStyleN = "-fx-font-weight: bold; -fx-background-color: C5C6C6;-fx-font-size: 24px;-fx-background-radius: 10px";
		nextButton.setText("Next");
		nextButton.setMaxSize(225, 150);
		nextButton.setStyle(initStyleN);
		nextButton.setOnMouseEntered(e->nextButton.setStyle(hoverStyleN));
		nextButton.setOnMouseExited(e->nextButton.setStyle(initStyleN));
		nextButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						questionNum++;
						questionNumber.setText("Question " + questionNum);
						if(choiceOne.isSelected()) {
							choices.add(choiceOne.getText());
							choiceOne.setSelected(false);
							choiceOne.setStyle(initStyle1);
						}
						else if(choiceTwo.isSelected()) {
							choices.add(choiceTwo.getText());
							choiceTwo.setSelected(false);
							choiceTwo.setStyle(initStyle2);
						}
						else if(choiceThree.isSelected()) {
							choices.add(choiceThree.getText());
							choiceThree.setSelected(false);
							choiceThree.setStyle(initStyle3);
						}
						else if(choiceFour.isSelected()) {
							choices.add(choiceFour.getText());
							choiceFour.setSelected(false);
							choiceFour.setStyle(initStyle4);
						}
						else if(choiceFive.isSelected()) {
							choices.add(choiceFive.getText());
							choiceFive.setSelected(false);
							choiceFive.setStyle(initStyle5);
						}
					}
				}
		);
		
		HBox topLine = new HBox();
		topLine.setSpacing(100);
		topLine.setAlignment(Pos.BASELINE_CENTER);
		topLine.setPadding(new Insets(30,30,25,30));
		topLine.getChildren().setAll(returnButton,questionNumber,nextButton);
		
		Image image = new Image("quizTemp.jpg");
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(200);
		imageView.setFitWidth(200);
		
		HBox imageBox = new HBox();
		imageBox.setSpacing(10);
		imageBox.setAlignment(Pos.BASELINE_CENTER);
		imageBox.getChildren().setAll(imageView);
		imageBox.setPadding(new Insets(0,0,25,0));
		
		Label questionText = new Label();
		questionText.setText("\"Question Text\"");
		questionText.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
		
		HBox questionBox = new HBox();
		questionBox.getChildren().setAll(questionText);
		questionBox.setPadding(new Insets(0,20,20,20));
		questionBox.setAlignment(Pos.BASELINE_CENTER);
		
		
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
						for(int i = 0; i<choices.size();i++) {
							System.out.println(choices.get(i));
						}
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
		design.getChildren().setAll(topLine,questionBox,imageBox,topLineChoice,bottomLineChoice,submit);
		return new Scene(design);
		
	}
	
	private Scene createResultsScene() {
		Label questionNumber = new Label();
		questionNumber.setText("Quiz Results");
		questionNumber.setStyle("-fx-font-weight: bold; -fx-font-size: 40px;");
		
		HBox topLine = new HBox();
		topLine.getChildren().setAll(questionNumber);
		topLine.setPadding(new Insets(0,20,35,20));
		topLine.setAlignment(Pos.CENTER);
		
		
		Label correct = new Label();
		correct.setText("Correct: ");
		correct.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		Label incorrect = new Label();
		incorrect.setText("Incorrect: ");
		incorrect.setStyle("-fx-font: italic 17px Georgia, serif;");
		
		VBox questionResults = new VBox();
		questionResults.setSpacing(25);
		questionResults.getChildren().setAll(correct,incorrect);
		questionResults.setPadding(new Insets(0,20,40,20));
		
		
		Button returnQuizButton = new Button();
		returnQuizButton.setText("Retake Quiz");
		returnQuizButton.setMaxSize(120, 50);
		returnQuizButton.setMinSize(120, 50);
		String init = "-fx-background-color: lightblue; -fx-color: black; -fx-font-weight: bold;"
				+ "-fx-background-radius: 5px; -fx-text-align: center; -fx-font-weight: bold;-fx-text-fill: black; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		String hover = "-fx-background-color: #058984; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-background-radius: 5px; -fx-text-align: center; -fx-text-fill: white; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		returnQuizButton.setStyle(init);
		returnQuizButton.setOnMouseEntered(e->returnQuizButton.setStyle(hover));
		returnQuizButton.setOnMouseExited(e->returnQuizButton.setStyle(init));
		returnQuizButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						stage.setScene(quizStartScene);
						stage.show();
					}
				}
		);
		
		Button quitButton = new Button();
		quitButton.setText("Quit");
		quitButton.setMaxSize(120, 50);
		quitButton.setMinSize(120, 50);
		String init1 = "-fx-background-color: red; -fx-color: black; -fx-font-weight: bold;"
				+ "-fx-background-radius: 5px; -fx-text-align: center;-fx-font-weight: bold; -fx-text-fill: black; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		String hover1 = "-fx-background-color: #CA1010; -fx-color: black; -fx-border: 5px; -fx-border-color: #4CAF50; -fx-border-style: solid;"
				+ "-fx-background-radius: 5px; -fx-text-align: center; -fx-text-fill: white; -fx-text-decoration: none; -fx-display: inline-block; -fx-font-size: 14px;";
		quitButton.setStyle(init1);
		quitButton.setOnMouseEntered(e->quitButton.setStyle(hover1));
		quitButton.setOnMouseExited(e->quitButton.setStyle(init1));
		quitButton.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						stage.setScene(primaryScene);
						stage.show();
					}
				}
		);
		
		VBox design = new VBox(topLine);
		design.setStyle("-fx-background-color: cornsilk; -fx-padding: 15;");
	
		HBox botLine = new HBox();
		botLine.getChildren().setAll(returnQuizButton,quitButton);
		botLine.setPadding(new Insets(0,20,20,20));
		botLine.setSpacing(100);
		
		design.getChildren().setAll(topLine,questionResults,botLine);
		return new Scene(design);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
