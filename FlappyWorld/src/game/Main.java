package game;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;


	
	private ImageView background = null;
	private ImageView flappy = null;
	private Button button = null;
	private Group root = null;
	
    private void addActionEventHandler(){
    	button.setOnAction(new EventHandler<ActionEvent>() {
    		TranslateTransition tt = new TranslateTransition(Duration.millis(1500), flappy);
    		@Override
    		public void handle(ActionEvent event) {
    			tt.setToY(176);
    			tt.setCycleCount(Timeline.INDEFINITE);
    			tt.setInterpolator(Interpolator.EASE_IN);
    			tt.play();
    		}
		});
    }
    
    private void addMouseEventHandler() {
    	
    	
    	
    }	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Create a Group 
		root = new Group();

		//TODO 1: add background
		background = new ImageView("background.png");
		
		
		//TODO 2: add Flappy
		flappy = new ImageView("flappy.png");
		flappy.xProperty().set(190);
		flappy.yProperty().set(200);
		
		//TODO 3: add Button
		button = new Button("Start!");
		button.layoutXProperty().set(175);
		
		//Add controls
		root.getChildren().add(background);
		root.getChildren().add(flappy);
		root.getChildren().add(button);
		
		//TODO 4: add action handler to the button
		addActionEventHandler();

		//TODO 5: add mouse handler to the scene
		addMouseEventHandler();
		
		
		//Create scene and add to stage
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		
		Application.launch(args);
		
	}

}
