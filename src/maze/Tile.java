package maze;

public class Tile
{

	public enum Type
	{
	CORRIDOR,ENTRANCE,EXIT,WALL;
	}

	private Type type;

	private Tile(Type t)
	{
		type = t;
	}

	protected static Tile fromChar(char c) throws InvalidMazeException
	{
		Type type;

		switch(c)
		{
			case '.':
				type = Type.CORRIDOR;
				break;
			case '#':
				type = Type.WALL;
				break;
			case 'e':
				type = Type.ENTRANCE;
				break;
			case 'x':
				type = Type.EXIT;
				break;
			default:
				throw new InvalidMazeException("Symbol invalid! Please try again!");
		}
		return new Tile(type);
	}

	public Type getType()
	{
		return type;
	}

	public boolean isNavigable()
	{
		if (type == Type.CORRIDOR)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public String toString()
	{
		String s = "";

		switch(type)
		{
			case CORRIDOR:
				s = ".";
				break;
			case WALL:
				s = "#";
				break;
			case ENTRANCE:
				s = "e";
				break;
			case EXIT:
				s = "x";
				break;
		}
		return s;
	}
}
