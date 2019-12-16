package com.sbaars.adventofcode2019.common;

import java.awt.Point;
import java.util.Arrays;

public enum Direction {
	NORTH(1), EAST(4), SOUTH(2), WEST(3);
	
	public final int num;
	
	private Direction(int num) {
		this.num = num;
	}
	
	public char directionCode() {
		return name().charAt(0);
	}
	
	public static Direction getByDirCode(char code) {
		return Arrays.stream(values()).filter(e -> e.directionCode() == code).findAny().get();
	}
	
	public Direction turn(boolean right) {
		int cur = ordinal() + (right ? 1 : -1);
		if(cur == Direction.values().length) cur = 0;
		else if(cur == -1) cur = 3;
		return Direction.values()[cur];
	}
	
	public Point move(Point currentLocation) {
		switch (this) {
			case NORTH: return new Point(currentLocation.x, currentLocation.y+1);
			case SOUTH: return new Point(currentLocation.x, currentLocation.y-1);
			case EAST: return new Point(currentLocation.x+1, currentLocation.y);
			case WEST: return new Point(currentLocation.x-1, currentLocation.y);
		}
		throw new IllegalStateException("Non-existent Direction: "+this);
	}

	public Direction opposite() {
		switch (this) {
			case NORTH: return SOUTH;
			case SOUTH: return NORTH;
			case EAST: return WEST;
			case WEST: return EAST;
		}
		throw new IllegalStateException("Non-existent Direction: "+this);
	}

	public static Direction getByMove(Point from, Point to) {
		if(to.x > from.x) return EAST;
		else if(to.x < from.x) return WEST;
		else if(to.y > from.y) return SOUTH;
		else if(to.y < from.y) return NORTH;
		throw new IllegalStateException("From and to location are the same: "+from+", "+to);
	}
}