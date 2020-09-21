import java.awt.Point;
import java.util.ArrayList;

public class Station {
	
	private Point p;
	private String name;
	int maxUnit,cSpace;
	ArrayList<Station> stations = new ArrayList<Station>();
	
	int []locationX = {25, 80, 10, 70 };
	int []locationY = {5, 30, 90, 80 };
	int []space = {2, 2, -1, 2};
	String [] names = {"Downtown","Midtown","Uptown","Lazytown"};
	public Station() {
		for (int i = 0; i<locationX.length; i++){
			stations.add(new Station(names[i], new Point(locationX[i], locationY[i]), space[i]));
		}
	}
	public Station(String name, Point p,int space) {
		this.name = name;
		this.p=p;
		this.cSpace = space;
	}
	public boolean hasSpace() {
		if (this.cSpace>0)
			return true;
		else
			return false;
	
	}
	public Point getLocation(){
		return this.p;
	}
	
}
