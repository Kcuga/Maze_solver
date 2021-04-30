import maze.Maze;
import maze.Tile;
import maze.routing.RouteFinder;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.geometry.Pos;

public class MazeApplication extends Application
{
  private Maze maze;
  private RouteFinder rf;
  private GridPane pane = new GridPane();

  public void showMaze()
  {

    Image image = new Image("File:" + System.getProperty("user.dir") + "/src/black_square.png");

    Text entrance = new Text(100,100,"E");
    entrance.setFont(Font.font ("Verdana", 15));
    Text exit = new Text(20,20,"X");
    exit.setFont(Font.font ("Verdana", 15));

    pane.getChildren().clear();
    for(int i=0; i<=maze.getTiles().size()-1; i++)
    {
      List<Tile> row = maze.getTiles().get(i);
      for(int j=0; j<=row.size()-1; j++)
      {
        Tile tile = maze.getTiles().get(i).get(j);
        switch(tile.getType())
        {
          case WALL:
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(35);
            imageView.setPreserveRatio(true);
            pane.add(imageView, j, i);
            break;
          case ENTRANCE:
            pane.add(entrance, j, i);
            break;
          case EXIT:
            pane.add(exit, j, i);
            break;
          case CORRIDOR:
            if(rf.getRoute().contains(tile))
            {
              Text path = new Text(20,20,"*");
              path.setFont(Font.font ("Verdana", 15));
              pane.add(path, j, i);
              break;
            }
            else if(rf.getChecked().contains(tile))
            {
              Text path = new Text(20,20,"-");
              path.setFont(Font.font ("Verdana", 15));
              pane.add(path, j, i);
              break;
            }
            else
            {
              Text path = new Text(20,20," ");
              path.setFont(Font.font ("Verdana", 15));
              pane.add(path, j, i);
              break;
            }
        }
      }
    }
  }

  @Override
  public void start (Stage stage)
  {
    FileChooser fileChooser = new FileChooser();

    VBox root = new VBox(10);
    root.setBackground(Background.EMPTY);
    root.setAlignment(Pos.CENTER);

    Text error = new Text(20,20,"");
    error.setFont(Font.font ("Verdana", 15));

    Button LoadMapButton = new Button("Load Map");
    LoadMapButton.setOnAction(e->{
      error.setText("");
      File selectedFile = fileChooser.showOpenDialog(stage);
      try
      {
        maze = maze.fromTxt(selectedFile.getAbsolutePath());
        rf = new RouteFinder(maze);
        showMaze();
      }
      catch(Exception er)
      {
        error.setText("Invalid Choice!");
      }
    });

    Button LoadRouteButton = new Button("Load Route");
    LoadRouteButton.setOnAction(e->{
      error.setText("");
      File selectedFile = fileChooser.showOpenDialog(stage);
      try
      {
        rf = RouteFinder.load(selectedFile.getAbsolutePath());
        maze = rf.getMaze();
        showMaze();
      }
      catch(Exception er)
      {
        error.setText("Invalid Choice!");
      }
    });

    Button SaveRouteButton = new Button("Save Route");
    SaveRouteButton.setOnAction(e->{
      error.setText("");
      if(rf != null)
      {
        File selectedFile = fileChooser.showSaveDialog(stage);
        try
        {
          rf.save(selectedFile.getAbsolutePath());
        }
        catch(Exception er)
        {
          error.setText(er.getMessage());
        }
      }
      else
      {
        error.setText("No maze found to save!");
      }
    });

    Button StepButton = new Button("Step");
    StepButton.setOnAction(e->{
      error.setText("");
      if(rf != null)
      {
        try
        {
          rf.step();
          showMaze();
        }
        catch (Exception er)
        {
          error.setText(er.getMessage());
        }
      }
      else
      {
        error.setText("No maze found!");
      }
    });

    root.getChildren().addAll(LoadMapButton, LoadRouteButton, SaveRouteButton, pane, StepButton, error);

    Scene scene = new Scene(root, 750, 900);
    stage.setScene(scene);
    stage.setTitle("Maze solver");
    stage.show();
  }

  public static void main(String args[])
  {
    launch(args);
  }
}
