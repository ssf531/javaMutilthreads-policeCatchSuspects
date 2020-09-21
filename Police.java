import java.awt.Point;
import java.util.ArrayList;

public class Police implements Runnable{
	private Point p;
	private String policeID,status,assignedSuspectID,dog;

	int locationX,locationY;
	
	public Police(String policeID,Point p,String status,String dog,String suspectID){
		this.policeID = policeID;
		this.p = p;
		this.status = status;
		this.dog = dog;
		this.assignedSuspectID = suspectID;
	}
	
	public String getPoliceID(){
		return this.policeID;
	}
	public String getSuspectID(){
		return this.assignedSuspectID;
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

	public boolean hasPoliceDog(){
		if(this.dog.equals("No")){
			return false;
		}else
			return true;
	}
	public Point getLocation(){
		return new Point(this.getX(),this.getY());
	}
	public boolean moveToPoint(Point p, int moves) throws Exception{
		
		for(;moves>0;moves--){
			if(p.x!=this.p.x) {
			if(p.x>this.p.x) 
				this.p.x ++; 
			else
				this.p.x --;			
			}else if(p.y!=this.p.y&&moves!= 0){
				if(p.y>this.p.y) 
					this.p.y ++; 
				else
					this.p.y --;
			}else
			    return true;				
		}
		if(p.y==this.p.y)
			return true;
		else
			return false;
	}
	public Suspects getClosestUnassignedSuspect(ArrayList<Suspects> s) {
		Point source = this.getLocation();
		Suspects target = null;
		double closest = 10000;
		for (int i = 0; i < s.size(); i++) {
			double distance = Math.pow(s.get(i).getLocation().x - source.x, 2)
					+ Math.pow(s.get(i).getLocation().y - source.y, 2);
			if (distance < closest&& s.get(i).getStatus().equals("Unassigned")) {
				closest = distance;
				target = s.get(i);
			}
		}
		return target;
	}
	public Station getClosestHasSpaceStation(ArrayList<Station> s) {
		Point source = this.getLocation();
		Station target = null;
		double closest = 10000;
		for (int i = 0; i < s.size(); i++) {
			double distance = Math.pow(s.get(i).getLocation().x - source.x, 2)
					+ Math.pow(s.get(i).getLocation().y - source.y, 2);
			if (distance < closest&& s.get(i).hasSpace()) {
				closest = distance;
				target = s.get(i);
			}
		}
		return target;
	}
	public void moveToSuspect() {
		if(this.moveToPoint(su.getLocation(), 4)){
		    this.status = "At Scene";
		    su.setStatus("Caught");
		}
	}
	@Override
	public void run(){
		System.out.println(this.policeID+"move");
	}
}
