import java.util.ArrayList;


public class GameBoard_USA extends GameBoard {
	public GameBoard_USA() {
		
		// Build the board connections
		tracks = new ArrayList<Connection>();
		
		tracks.add(new Connection(City.VANCOUVER, City.CALGARY, TrackColor.GRAY, 3));
		
		tracks.add(new Connection(City.VANCOUVER, City.SEATTLE, TrackColor.GRAY, 1));
		tracks.add(new Connection(City.VANCOUVER, City.SEATTLE, TrackColor.GRAY, 1));
		
		tracks.add(new Connection(City.SEATTLE, City.PORTLAND, TrackColor.GRAY, 1));
		tracks.add(new Connection(City.SEATTLE, City.PORTLAND, TrackColor.GRAY, 1));
		
		tracks.add(new Connection(City.SEATTLE, City.CALGARY, TrackColor.GRAY, 4));
		
		tracks.add(new Connection(City.PORTLAND, City.SAN_FRANCISCO, TrackColor.GREEN, 5));
		tracks.add(new Connection(City.PORTLAND, City.SAN_FRANCISCO, TrackColor.PINK, 5));
		
		tracks.add(new Connection(City.SAN_FRANCISCO, City.LOS_ANGELES, TrackColor.PINK, 3));
		tracks.add(new Connection(City.SAN_FRANCISCO, City.LOS_ANGELES, TrackColor.YELLOW, 3));
		
		tracks.add(new Connection(City.LOS_ANGELES, City.EL_PASO, TrackColor.BLACK, 6));
		
		tracks.add(new Connection(City.LOS_ANGELES, City.LAS_VEGAS, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.EL_PASO, City.HOUSTON, TrackColor.GREEN, 6));
		
		tracks.add(new Connection(City.HOUSTON, City.NEW_ORLEANS, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.HOUSTON, City.DALLAS, TrackColor.GRAY, 1));
		tracks.add(new Connection(City.HOUSTON, City.DALLAS, TrackColor.GRAY, 1));
		
		tracks.add(new Connection(City.NEW_ORLEANS, City.MIAMI, TrackColor.RED, 6));
		
		tracks.add(new Connection(City.DALLAS, City.LITTLE_ROCK, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.DALLAS, City.OKLAHOMA_CITY, TrackColor.GRAY, 2));
		tracks.add(new Connection(City.DALLAS, City.OKLAHOMA_CITY, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.KANSAS_CITY, City.OKLAHOMA_CITY, TrackColor.GRAY, 2));
		tracks.add(new Connection(City.KANSAS_CITY, City.OKLAHOMA_CITY, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.KANSAS_CITY, City.OMAHA, TrackColor.GRAY, 1));
		tracks.add(new Connection(City.KANSAS_CITY, City.OMAHA, TrackColor.GRAY, 1));
		
		tracks.add(new Connection(City.DULUTH, City.OMAHA, TrackColor.GRAY, 2));
		tracks.add(new Connection(City.DULUTH, City.OMAHA, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.PORTLAND, City.SALT_LAKE_CITY, TrackColor.BLUE, 6));
		
		tracks.add(new Connection(City.SAN_FRANCISCO, City.SALT_LAKE_CITY, TrackColor.WHITE, 5));
		tracks.add(new Connection(City.SAN_FRANCISCO, City.SALT_LAKE_CITY, TrackColor.ORANGE, 5));
		
		tracks.add(new Connection(City.LAS_VEGAS, City.SALT_LAKE_CITY, TrackColor.ORANGE, 3));
		
		tracks.add(new Connection(City.DULUTH, City.WINNIPEG, TrackColor.BLACK, 4));
		
		tracks.add(new Connection(City.CALGARY, City.WINNIPEG, TrackColor.WHITE, 6));
		
		tracks.add(new Connection(City.CALGARY, City.HELENA, TrackColor.GRAY, 4));
		
		tracks.add(new Connection(City.SEATTLE, City.HELENA, TrackColor.YELLOW, 6));
		
		tracks.add(new Connection(City.SALT_LAKE_CITY, City.HELENA, TrackColor.PINK, 3));
		
		tracks.add(new Connection(City.HELENA, City.WINNIPEG, TrackColor.BLUE, 4));
		
		tracks.add(new Connection(City.HELENA, City.DULUTH, TrackColor.ORANGE, 6));
		
		tracks.add(new Connection(City.HELENA, City.DENVER, TrackColor.GREEN, 4));
		
		tracks.add(new Connection(City.HELENA, City.OMAHA, TrackColor.RED, 5));
		
		tracks.add(new Connection(City.LOS_ANGELES, City.PHOENIX, TrackColor.GRAY, 3));
		
		tracks.add(new Connection(City.EL_PASO, City.PHOENIX, TrackColor.GRAY, 3));
		
		tracks.add(new Connection(City.LAS_VEGAS, City.DENVER, TrackColor.WHITE, 5));
		
		tracks.add(new Connection(City.SALT_LAKE_CITY, City.DENVER, TrackColor.RED, 3));
		tracks.add(new Connection(City.SALT_LAKE_CITY, City.DENVER, TrackColor.YELLOW, 3));
		
		tracks.add(new Connection(City.OMAHA, City.DENVER, TrackColor.PINK, 4));
		
		tracks.add(new Connection(City.KANSAS_CITY, City.DENVER, TrackColor.BLACK, 4));
		tracks.add(new Connection(City.KANSAS_CITY, City.DENVER, TrackColor.ORANGE, 4));
		
		tracks.add(new Connection(City.PHOENIX, City.SANTA_FE, TrackColor.GRAY, 3));
		
		tracks.add(new Connection(City.EL_PASO, City.SANTA_FE, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.DENVER, City.SANTA_FE, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.OKLAHOMA_CITY, City.DENVER, TrackColor.RED, 4));
		
		tracks.add(new Connection(City.OKLAHOMA_CITY, City.SANTA_FE, TrackColor.BLUE, 3));
		
		tracks.add(new Connection(City.EL_PASO, City.OKLAHOMA_CITY, TrackColor.YELLOW, 5));
		
		tracks.add(new Connection(City.EL_PASO, City.DALLAS, TrackColor.RED, 4));
		
		tracks.add(new Connection(City.WINNIPEG, City.SAULT_ST_MARY, TrackColor.GRAY, 6));
		
		tracks.add(new Connection(City.DULUTH, City.SAULT_ST_MARY, TrackColor.GRAY, 3));
		
		tracks.add(new Connection(City.DULUTH, City.TORONTO, TrackColor.PINK, 6));
		
		tracks.add(new Connection(City.DULUTH, City.CHICAGO, TrackColor.RED, 3));
		
		tracks.add(new Connection(City.OMAHA, City.CHICAGO, TrackColor.BLUE, 4));
		
		tracks.add(new Connection(City.KANSAS_CITY, City.SAINT_LOUIS, TrackColor.BLUE, 2));
		tracks.add(new Connection(City.KANSAS_CITY, City.SAINT_LOUIS, TrackColor.PINK, 2));
		
		tracks.add(new Connection(City.OKLAHOMA_CITY, City.LITTLE_ROCK, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.SAULT_ST_MARY, City.MONTREAL, TrackColor.BLACK, 5));
		
		tracks.add(new Connection(City.SAULT_ST_MARY, City.TORONTO, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.MONTREAL, City.TORONTO, TrackColor.GRAY, 3));
		
		tracks.add(new Connection(City.MONTREAL, City.BOSTON, TrackColor.GRAY, 2));
		tracks.add(new Connection(City.MONTREAL, City.BOSTON, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.MONTREAL, City.NEW_YORK, TrackColor.BLUE, 3));
		
		tracks.add(new Connection(City.BOSTON, City.NEW_YORK, TrackColor.YELLOW, 2));
		tracks.add(new Connection(City.BOSTON, City.NEW_YORK, TrackColor.RED, 2));
		
		tracks.add(new Connection(City.CHICAGO, City.TORONTO, TrackColor.WHITE, 4));
		
		tracks.add(new Connection(City.PITTSBURGH, City.TORONTO, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.PITTSBURGH, City.CHICAGO, TrackColor.BLACK, 3));
		tracks.add(new Connection(City.PITTSBURGH, City.CHICAGO, TrackColor.ORANGE, 3));
		
		tracks.add(new Connection(City.SAINT_LOUIS, City.CHICAGO, TrackColor.GREEN, 2));
		tracks.add(new Connection(City.SAINT_LOUIS, City.CHICAGO, TrackColor.WHITE, 2));
		
		tracks.add(new Connection(City.SAINT_LOUIS, City.LITTLE_ROCK, TrackColor.GRAY, 2));
		tracks.add(new Connection(City.SAINT_LOUIS, City.NASHVILLE, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.NEW_ORLEANS, City.LITTLE_ROCK, TrackColor.GREEN, 3));
		
		tracks.add(new Connection(City.NASHVILLE, City.LITTLE_ROCK, TrackColor.WHITE, 3));
		
		tracks.add(new Connection(City.NEW_ORLEANS, City.ATLANTA, TrackColor.ORANGE, 4));
		tracks.add(new Connection(City.NEW_ORLEANS, City.ATLANTA, TrackColor.YELLOW, 4));
		
		tracks.add(new Connection(City.NASHVILLE, City.ATLANTA, TrackColor.GRAY, 1));
		
		tracks.add(new Connection(City.CHARLESTON, City.ATLANTA, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.CHARLESTON, City.MIAMI, TrackColor.PINK, 4));
		
		tracks.add(new Connection(City.ATLANTA, City.MIAMI, TrackColor.BLUE, 5));
		
		tracks.add(new Connection(City.ATLANTA, City.RALEIGH, TrackColor.GRAY, 2));
		tracks.add(new Connection(City.ATLANTA, City.RALEIGH, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.CHARLESTON, City.RALEIGH, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.WASHINGTON, City.RALEIGH, TrackColor.GRAY, 2));
		tracks.add(new Connection(City.WASHINGTON, City.RALEIGH, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.PITTSBURGH, City.RALEIGH, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.NASHVILLE, City.RALEIGH, TrackColor.BLACK, 3));
		
		tracks.add(new Connection(City.PITTSBURGH, City.WASHINGTON, TrackColor.GRAY, 2));
		
		tracks.add(new Connection(City.PITTSBURGH, City.NEW_YORK, TrackColor.WHITE, 2));
		tracks.add(new Connection(City.PITTSBURGH, City.NEW_YORK, TrackColor.GREEN, 2));
		
		tracks.add(new Connection(City.PITTSBURGH, City.SAINT_LOUIS, TrackColor.GREEN, 5));
		tracks.add(new Connection(City.PITTSBURGH, City.NASHVILLE, TrackColor.YELLOW, 4));
		
		tracks.add(new Connection(City.WASHINGTON, City.NEW_YORK, TrackColor.ORANGE, 2));
		tracks.add(new Connection(City.WASHINGTON, City.NEW_YORK, TrackColor.BLACK, 2));
	}
}


