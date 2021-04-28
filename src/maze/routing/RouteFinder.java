package maze.routing;

import maze.Maze;
import maze.Tile;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class RouteFinder implements Serializable
{
  private Maze maze;
  private Stack<Tile> route;
  private boolean finished;

  private Tile entranceTile;
  private Tile exitTile;
  private ArrayList<Tile> checked;

  public RouteFinder(Maze mazeIn)
  {
    maze = mazeIn;
    entranceTile = maze.getEntrance();
    exitTile = maze.getExit();
    checked = new ArrayList<Tile>();
    route = new Stack<Tile>();

    route.push(entranceTile);

    checked.add(entranceTile);
  }

  public Maze getMaze()
  {
    return maze;
  }

  public List<Tile> getRoute()
  {
    return route;
  }

  public boolean isFinished()
  {
    return finished;
  }

  public static RouteFinder load(String s) throws IOException, ClassNotFoundException
  {
    RouteFinder rf;
    ObjectInputStream load = new ObjectInputStream(new FileInputStream(s));
    rf = (RouteFinder)load.readObject();
    return rf;
  }

  public void save(String s) throws IOException
  {
    ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(s));
    save.writeObject(this);
    save.close();
  }

  public boolean step() throws NoRouteFoundException
  {
    Tile currentTile, nextTile;
    boolean newTile;

    currentTile = route.peek();
    newTile = true;
    nextTile = maze.getAdjacentTile(currentTile, Maze.Direction.EAST);
    if (currentTile == exitTile)
    {
      finished = true;
      return finished;
    }

    if (nextTile!= null && nextTile.isNavigable())
    {
      for (int i=0; i<=checked.size()-1; i++)
      {
        if(checked.get(i)==nextTile)
        {
          newTile = false;
          break;
        }
      }
      if(newTile)
      {
        route.push(nextTile);
        checked.add(nextTile);
        return finished;
      }
    }
    newTile = true;
    nextTile = maze.getAdjacentTile(currentTile, Maze.Direction.SOUTH);
    if (nextTile!= null && nextTile.isNavigable())
    {
      for (int i=0; i<=checked.size()-1; i++)
      {
        if(checked.get(i)==nextTile)
        {
          newTile = false;
          break;
        }
      }
      if(newTile)
      {
        route.push(nextTile);
        checked.add(nextTile);
        return finished;
      }
    }
    newTile = true;
    nextTile = maze.getAdjacentTile(currentTile, Maze.Direction.WEST);
    if (nextTile!= null && nextTile.isNavigable())
    {
      for (int i=0; i<=checked.size()-1; i++)
      {
        if(checked.get(i)==nextTile)
        {
          newTile = false;
          break;
        }
      }
      if(newTile)
      {
        route.push(nextTile);
        checked.add(nextTile);
        return finished;
      }
    }
    newTile = true;
    nextTile = maze.getAdjacentTile(currentTile,Maze.Direction.NORTH);
    if (nextTile!= null && nextTile.isNavigable())
    {
      for (int i=0; i<=checked.size()-1; i++)
      {
        if(checked.get(i)==nextTile)
        {
          newTile = false;
          break;
        }
      }
      if(newTile)
      {
        route.push(nextTile);
        checked.add(nextTile);
        return finished;
      }
    }

    if (route.isEmpty())
    {
      throw new NoRouteFoundException("Maze has no solution!");
    }
    route.pop();
    return finished;
  }

  public String toString()
  {
    String s = "";
    Tile tile;
    List<Tile> row;

    for(int i=0; i<=maze.getTiles().size()-1; i++)
    {
      row = maze.getTiles().get(i);
      for(int j=0; j<=row.size()-1; j++)
      {
        tile = maze.getTiles().get(i).get(j);
        if(route.contains(tile))
        {
          s += "*";
        }
        else if(checked.contains(tile))
        {
          s += "-";
        }
        else
        {
          s += tile.toString();
        }
      }
      s += "\n";
    }
    return s;
  }
}
