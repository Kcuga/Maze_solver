import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class test extends Application
{
  @Override
  public void start (Stage stage)
  {
    VBox root = new VBox(10);
    root.setBackground(Background.EMPTY);
    root.setAlignment(Pos.CENTER);

    Button LoadMapButton = new Button("Load Map");

    Button LoadRouteButton = new Button("Load Route");

    Button SaveRouteButton = new Button("Save Route");

    Button StepButton = new Button("Step");

    root.getChildren().addAll(LoadMapButton, LoadRouteButton, SaveRouteButton, StepButton);

    Scene scene = new Scene(root, 250, 275, Color.WHITE);
    stage.setScene(scene);
    stage.setTitle("Maze solver");
  }

  public static void main(String args[])
  {
    launch(args);
  }
}
