import maze.Maze;
import maze.routing.RouteFinder;

public class MazeDriver {
    public static void main(String args[])
    {
    	Maze maze;
      RouteFinder rf;
      try
      {

        maze = Maze.fromTxt("/home/csimage/University/Programming 2/Coursework 2/comp16412-coursework-2_a97490rp/resources/mazes/maze1.txt");
        rf = new RouteFinder(maze);

        while(!rf.isFinished())
        {
        rf.step();
        System.out.println(rf);
        }
      }
      catch(Exception e)
      {
        System.out.println(e.toString());
      }
    }
}
