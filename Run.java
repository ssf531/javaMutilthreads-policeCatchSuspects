
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Run {

	public static void main(String[] args) throws Exception {

		AtomicInteger freeP = new AtomicInteger(0);
		AtomicInteger freeS = new AtomicInteger(0);
		String line = "";
		ArrayList<Police> policeUnits = new ArrayList<>();
		ArrayList<Suspects> suspects = new ArrayList<>();

		policeUnits = readPoliceCSV("police.csv");
		suspects = readSuspectCSV("suspects.csv");

		ExecutorService excutor = Executors.newCachedThreadPool();
		int i = freeP.get();
		for (; i > 0; i--) {
			excutor.submit(new Process(policeInfo, suspectsInfo, freeP, freeS));
			// Thread.sleep(1000);
		}

		excutor.shutdown();
	}

	public static class Process implements Runnable {

		ArrayList<String> policeInfo;
		ArrayList<String> suspectsInfo;
		private AtomicInteger freeP;
		private AtomicInteger freeS;
		String[] lineSplit;

		public Process(ArrayList<String> p, ArrayList<String> s, AtomicInteger P, AtomicInteger S) {
			this.policeInfo = p;
			this.suspectsInfo = s;
			this.freeP = P;
			this.freeS = S;
		}

		public int getPoliceNumber() {
			int i = freeP.get();
			return i;
		}

		@Override
		public void run() {

			synchronized ("") {

				// System.out.println(getPoliceNumber());
				if (getPoliceNumber() > 0) {
					int currentSuspect = 0;

					for (int i = 0; i < suspectsInfo.size(); i++) {
						lineSplit = suspectsInfo.get(i).split(",");
						if (lineSplit.length < 5) {
							currentSuspect = i;
							break;
						}
					}

					int s_x = Integer.parseInt(lineSplit[1]);
					int s_y = Integer.parseInt(lineSplit[2]);
					Suspects s = new Suspects(lineSplit[0], s_x, s_y, lineSplit[3], null);
					int closest = 1000;
					int closest_p = 0;
					int p_x = 0, p_y = 0;
					for (int i = 0; i < policeInfo.size(); i++) {
						lineSplit = policeInfo.get(i).split(",");
						if (lineSplit.length < 6) {
							p_x = Integer.parseInt(lineSplit[1]);
							p_y = Integer.parseInt(lineSplit[2]);
							int distance = Math.abs(s_x - p_x) + Math.abs(s_y - p_y);
							if (distance < closest) {
								closest = distance;
								closest_p = i;
							}
						}
					}
					lineSplit = policeInfo.get(closest_p).split(",");
					String newsInfo = suspectsInfo.get(currentSuspect) + lineSplit[0];
					suspectsInfo.set(currentSuspect, newsInfo);
					System.out.println(suspectsInfo.get(currentSuspect));
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					freeS.decrementAndGet();
					String newPInfo = policeInfo.get(closest_p) + s.getSuspectID();
					policeInfo.set(closest_p, newPInfo);
					System.out.println(policeInfo.get(closest_p));
					freeP.decrementAndGet();

				}
			}
		}

	}

	public static ArrayList<Police> readPoliceCSV(String filename) throws IOException {
		FileReader in = new FileReader(filename);
		BufferedReader reader = new BufferedReader(in);
		ArrayList<Police> police = new ArrayList<>();
		String line = "";
		reader.readLine();
		while ((line = reader.readLine()) != null) {
			String[] lineS;
			lineS = line.split(",");
			Point p = new Point(Integer.parseInt(lineS[1]), Integer.parseInt(lineS[2]));
			police.add(new Police(lineS[0], p, lineS[3], lineS[4], lineS[5]));
		}
		reader.close();
		return police;
	}

	public static ArrayList<Suspects> readSuspectCSV(String filename) throws Exception {
		FileReader in = new FileReader(filename);
		BufferedReader reader = new BufferedReader(in);
		ArrayList<Suspects> suspects = new ArrayList<>();
		String line = "";
		reader.readLine();
		while ((line = reader.readLine()) != null) {
			String[] lineS;
			lineS = line.split(",");
			Point p = new Point(Integer.parseInt(lineS[1]), Integer.parseInt(lineS[2]));
			suspects.add(new Suspects(lineS[0], p, lineS[3], lineS[4]));
		}
		reader.close();
		return suspects;
	}

	public static Point getClosestPoint(Point source, ArrayList<Point> pts) {

		Point target = null;
		double closest = 10000;
		for (int i = 0; i < pts.size(); i++) {
			double distance = Math.pow(Math.abs(pts.get(i).x - source.x), 2)
					+ Math.pow(Math.abs(pts.get(i).y - source.y), 2);
			if (distance < closest) {
				closest = distance;
				target = pts.get(i);
			}
		}
		return target;
	}
	public static int calculateMaxUnits(int size) {
		 double s = (double) size/4.0;
		    int si =size/4;
		    if(s != (double) si)	        
		        return ++si;
		    else
		        return si;
	}
	public static int calculateMaxDogs(int size) {    
		double s = (double) size/2.0;
	    int si =size/2;
	    if(s != (double) si)	        
	        return ++si;
	    else
	        return si;
	}
}
