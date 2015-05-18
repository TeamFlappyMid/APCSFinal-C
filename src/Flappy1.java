import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Flappy1 extends Application{
	
	private ImageView bkgrd = null;
	private ImageView flappy = null;
	private Button button = null;
	private Button startButton = null;
	private Group root = null;
	private int rotations = 0;
	private Timeline timeline = new Timeline();
	
    //Whee button
	private void addActionEventHandler(){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	flappy.rotateProperty().set(90*(rotations%4));
            	rotations++;
            }
        });
        
		/*startButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				TranslateTransition birdFall = new TranslateTransition(Duration.millis(3000),flappy);
				birdFall.setFromY(0);
				birdFall.setToY(125);
				birdFall.setInterpolator(Interpolator.LINEAR);
				birdFall.play();
				birdFall.playFromStart();
			}
		});*/
        
        startButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				KeyValue kv = new KeyValue(flappy.yProperty(), 45, new Interpolator(){
					protected double curve (double t){
						double a = -0.9;
						return -a*t*t+(1-a)*t;
					}
				});
				KeyFrame fall = new KeyFrame(Duration.millis(1000), kv);
				timeline.getKeyFrames().add(fall);
				timeline.play();
				timeline.playFromStart();
			}
        });
    }
    
    private void addMouseEventHandler(){
    	/*root.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                flappy.translateXProperty().set(event.getSceneX()-175);
                flappy.translateYProperty().set(event.getSceneY()-175);
            	
        		String url = getClass().getResource("/flap.mp3").toString();
        		Media m = new Media(url);
        		MediaPlayer player = new MediaPlayer(m);
        		player.play();
            }
        });*/
    	
    	/*root.onMouseClickedProperty().set(new EventHandler<MouseEvent>(){
    		@Override
    		public void handle(MouseEvent event){
    			TranslateTransition flap = new TranslateTransition(Duration.millis(750),flappy);
    			flap.setToY(flappy.getY()+150);
    			flap.setInterpolator(new Interpolator(){
    				protected double curve(double t){
    					double a = 0.9;
    					return -a*t*t+(1-a)*t;
    				}
    			});
    			flap.play();
    		}
    	});*/
    	
    	root.onMouseClickedProperty().set(new EventHandler<MouseEvent>(){
    		@Override
    		public void handle(MouseEvent event){
    			KeyValue kv = new KeyValue(flappy.yProperty(),0, new Interpolator(){
        			protected double curve(double t){
	    				double a = 0.9;
	    				return -a*t*t+(1-a)*t;
        			}
    			});
    			KeyFrame flap = new KeyFrame(Duration.millis(750), kv);
    			timeline.getKeyFrames().add(flap);
    			timeline.play();
    		}
    	});
    }	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Create a Group 
		root = new Group();

		//TODO 1: add background
		bkgrd = new ImageView("background.png") ;

		
		//TODO 2: add Flappy
		flappy = new ImageView("flappy.png") ;
		flappy.layoutXProperty().set(175);
		flappy.layoutYProperty().set(175);
		
		//TODO 3: add Button
		button = new Button ("Whee!");		
        button.layoutXProperty().set(75);
        button.layoutYProperty().set(350);
		
        startButton = new Button ("START");
        startButton.layoutXProperty().set(250);
        startButton.layoutYProperty().set(350);
		
		//Add controls
		root.getChildren().add( bkgrd );
		root.getChildren().add( flappy );
		root.getChildren().add( button );
		root.getChildren().add( startButton );
		
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
