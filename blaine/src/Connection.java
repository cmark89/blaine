
public class Connection {
	City city1;
	City city2;
	TrackColor color;
	int length;
	boolean occupied;
	
	public Connection(City c1, City c2, TrackColor col, int leng) {
		city1 = c1;
		city2 = c2;
		color = col;
		length = leng;
		
		occupied = false;
	}
}
