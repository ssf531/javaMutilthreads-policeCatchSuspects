import java.awt.Point;

public class Suspects implements Runnable{
	private String suspectID,status,assignedPoliceID;
	private Point p;
	
	public Suspects(String suspectID,Point p,String status,String policeID){
		this.suspectID = suspectID;
		this.p = p;
		this.status = status;
		this.assignedPoliceID = policeID;
	}
	
	public String getSuspectID(){
		return this.suspectID;
	}
	public String getPoliceID(){
		return this.assignedPoliceID;
	}
	public int getX(){
		return this.p.x;
	}
	public int getY(){
		return this.p.y;
	}
	
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String statu){
		 this.status=statu;
	}
	public Point getLocation(){
		return new Point(this.getX(),this.getY());
	}
	@Override
	public void run(){
		System.out.println(this.suspectID+"move");
	}
}
