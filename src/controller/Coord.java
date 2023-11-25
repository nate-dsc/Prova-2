package controller;
import java.util.Random;

public class Coord {

	private int x;
	private int y;
	
	Coord()
	{
		this.x = 0;
		this.y = 0;
	}
	
	void setCoord(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public void aleatoria()
	{
		Random rnd = new Random();
		this.setCoord(rnd.nextInt(1, 16), rnd.nextInt(1, 16));
	}
	
	public void mostrarCoord()
	{
		System.out.print("(" + this.x + ", " + this.y + ") ");
	}
}
