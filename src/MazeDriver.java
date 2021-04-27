import maze.Maze;

public class MazeDriver {
    public static void main(String args[])
    {
    	Maze maze;
      try
      {
        maze = Maze.fromTxt("/home/csimage/University/Programming 2/Coursework 2/comp16412-coursework-2_a97490rp/resources/mazes/maze1.txt");
        System.out.println(maze.toString());
      }
      catch(Exception e)
      {
        System.out.println(e.toString());
      }
    }
}
