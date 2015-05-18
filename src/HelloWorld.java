import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HelloWorld extends Application{
	
	private Button button = null;
	private Text text = null ;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Create a Group 
		Group root = new Group();

		//Add background
		ImageView img=new ImageView("background.png");
		root.getChildren().add(img);		
		
		//Create Text node
		text = new Text("Hello Dey're");
        text.xProperty().set(75);
        text.yProperty().set(100);
        text.setFont(Font.font ("Times New Roman", 48));
		root.getChildren().add(text);

        //TODO Activity 1: set font color and reflection
        text.setFill(Color.BLUE);
        
        //Set effect
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        text.setEffect(r);
        
        //Translate & Rotate
        text.translateXProperty().set(100);
        text.translateYProperty().set(100);
        text.rotateProperty().set(90);
        
        //TODO Activity 2: Add a Button to scene
        button = new Button("Say Hai!");
        button.layoutXProperty().set(150);
        button.layoutYProperty().set(300);
        root.getChildren().add(button);
        
        
        
		//Create scene
		Scene scene = new Scene(root, 400, 400);
		
		//TODO activity 3: add event handler
		addActionEventHandler();
		addMouseEventHandler();
		
		// Set scene to the stage and show
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
    
	private int clicks = 0;
	private String[] hellos = {"Hello", "Bonjour", "你好"};
	private String[] sounds = {"hello.mp3", "bonjour.mp3", "nihao.mp3"};
	
    private void addActionEventHandler(){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
    			text.setText(hellos[clicks%3]);
    			playSound();
    			clicks++;
            }
        });
    }
    
    private void addMouseEventHandler(){
    	text.onMouseClickedProperty().set(new EventHandler<MouseEvent>(){
    		public void handle(MouseEvent event){
    			int n = (int)(255*Math.random());
    			text.setFill(Color.rgb(n, n, n));
    		}
    	});
    }	
	
	private void playSound(){
		String url = getClass().getResource(sounds[clicks%3]).toString();
		Media m = new Media(url);
		MediaPlayer player = new MediaPlayer(m);
		player.play();
	}
}
