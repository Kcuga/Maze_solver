package maze;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;



public class Maze
{

	public class Coordinate
	{
		private int x;
		private int y;

		public Coordinate(int xIN, int yIN)
		{
			x = xIN;
			y = yIN;
		}

		public int getX()
		{
			return x;
		}

		public int getY()
		{
			return y;
		}

		public String toString()
		{
			String coord = "";
			coord += "(";
			coord += Integer.toString(x);
			coord += ", ";
			coord += Integer.toString(y);
			coord += ")";
			return coord;
		}
	}

	public enum Direction
	{
	NORTH,SOUTH,EAST,WEST;
	}

	private Tile entrance;
	private Tile exit;
	private List<List<Tile>> tiles;

	private Maze()
	{
	tiles = new ArrayList<List<Tile>>();
	}

	public static Maze fromTxt(String s) throws FileNotFoundException, IOException, InvalidMazeException
	{
		String line;
		Tile tile;
		List<Tile> row;
		Maze maze = new Maze();

		BufferedReader reader = new BufferedReader (new FileReader(s));
		line = reader.readLine();
		while(line != null)
		{
			row = new ArrayList<Tile>();
			for(int i=0; i<=line.length()-1; i++)
			{
				tile = Tile.fromChar(line.charAt(i));

				if(line.charAt(i)=='e')
				{
					maze.entrance = tile;
				}
				else if(line.charAt(i)=='x')
				{
					maze.exit = tile;
				}
				row.add(tile);
			}
			maze.tiles.add(row);
			line = reader.readLine();
		}
		return maze;

	}

	public Tile getAdjacentTile(Tile t, Direction d)
	{
		List<Tile> row;

		for (int i=0; i<=tiles.size()-1; i++)
		{
			row = tiles.get(i);
			if(row.contains(t))
			{
				int j = row.indexOf(t);
				switch(d)
				{
					case NORTH:
						if(i==0)
							return null;
						else
							return tiles.get(i-1).get(j);
					case SOUTH:
						if(i==tiles.size()-1)
							return null;
						else
							return tiles.get(i+1).get(j);
					case EAST:
						if(j==row.size()-1)
							return null;
						else
							return tiles.get(i).get(j+1);
					case WEST:
						if(j==0)
							return null;
						else
							return tiles.get(i).get(j-1);
				}
			}
		}
		return null;
	}

	public Tile getEntrance()
	{
		return entrance;
	}

	public Tile getExit()
	{
		return exit;
	}

	public Tile getTileAtLocation(Coordinate c)
	{
		int x = c.getX();
		int y = c.getY();

		return tiles.get(tiles.size()-y).get(x);
	}

	public Coordinate getTileLocation(Tile t)
	{
		int i,j;
		for (i=0; i<=tiles.size()-1; i++)
		{
			List<Tile> row = tiles.get(i);
			if(row.contains(t))
			{
				j = row.indexOf(t);
				int x = j;
				int y = tiles.size() - i;

				return new Coordinate(x,y);
			}
		}
		return null;

	}

	public List<List<Tile>> getTiles()
	{
		return tiles;
	}

	private void setEntrance(Tile t) throws InvalidMazeException
	{

		for (int i=0; i<=tiles.size()-1; i++)
		{
			List<Tile> row = tiles.get(i);
			if(row.contains(t))
			{
				entrance = t;
				return;
			}
		}
		throw new InvalidMazeException("Entrance out of bounds!");
	}

	private void setExit(Tile t) throws InvalidMazeException
	{
		for (int i=0; i<=tiles.size()-1; i++)
		{
			List<Tile> row = tiles.get(i);
			if(row.contains(t))
			{
				exit = t;
				return;
			}
		}
		throw new InvalidMazeException("Exit out of bounds!");
	}

	public String toString()
	{
		String s = "";
		Tile tile;
		List<Tile> row;

		for(int i=0; i<=tiles.size()-1; i++)
		{
			row = tiles.get(i);
			for(int j=0; j<=row.size()-1; j++)
			{
				tile = tiles.get(i).get(j);
				s += tile.toString();
			}
			s += "\n";
		}
		return s;
	}

}
