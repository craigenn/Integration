package ZigZag;

public class movements 
{
	double duration;
	int speed;
	//constructor 
	public movements(double duration, int speed)
	{
	this.duration= duration;
	this.speed= speed;
	}
	//Setting my finch LED and buzzer
	public void setting1() 
	{
		finchtask.myfinch.setLED(0, 255, 0);
		finchtask.myfinch.buzz(5000, (int) duration);
	}
	//Setting my finch LED and buzzer
	public void setting2()
	{
		finchtask.myfinch.setLED(0, 0, 255);
		finchtask.myfinch.buzz(7000, (int) duration);
	}
	//Method to move the finch forward
	public  void forward() 
	{
		finchtask.myfinch.setWheelVelocities(this.speed, this.speed, (int) this.duration);
		try
		{
		    Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
	//Method to turn the finch right
	public void turnRight() 
	{
		finchtask.myfinch.setWheelVelocities(100,0,1500);
	}
	//Method to turn the finch left 
	public void turnLeft() 
	{
		finchtask.myfinch.setWheelVelocities(0,100,1500);
	}
	//Method to turn the finch 180 degrees 
	public void turnAround() 
	{
		finchtask.myfinch.setWheelVelocities(100,0,3000);
	}
}
