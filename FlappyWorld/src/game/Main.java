package game;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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

public class Main extends Application {
	
	private ImageView background = null;
	private ImageView flappy = null;
	private Button button = null;
	private Group root = null;
	
    private void addActionEventHandler() {
    	
    	button.setOnAction(new EventHandler<ActionEvent>() {
    		
    		@Override
    		public void handle(ActionEvent event) {
    			
    			Timeline timeline = new Timeline();
    			final double height = 200;
    			final double duration = Math.sqrt(height/4.8);
    			KeyValue kv = new KeyValue(flappy.translateYProperty(), 400, new Interpolator() {
    				
    				protected double curve(double t) {
    					
    					double time = t * duration;
    					double dist = 0; //TODO
    					double elapsed = dist / height;
    					return elapsed;
    					
    				}
    				
    			}
    			);
    			KeyFrame moveFlappy = new KeyFrame(Duration.seconds(duration), kv);
    			timeline.getKeyFrames().add(moveFlappy);
    			timeline.setAutoReverse(false);
    			timeline.play();
    			
    		}
    		
		}
    	
    	);
    	
    }
    
    private void addMouseEventHandler() {
    	
    	root.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
    		
    		
            @Override
            public void handle(MouseEvent event) {
            	
            	
            	
            }
        }
    	
    	);
    	
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		root = new Group();

		background = new ImageView("background.png");
		
		flappy = new ImageView("flappy.png");
		flappy.xProperty().set(190);
		flappy.yProperty().set(200);
		
		button = new Button("Start!");
		button.layoutXProperty().set(175);
		
		root.getChildren().add(background);
		root.getChildren().add(flappy);
		root.getChildren().add(button);
		
		addActionEventHandler();

		addMouseEventHandler();
		
		
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		
		Application.launch(args);
		
	}

}
