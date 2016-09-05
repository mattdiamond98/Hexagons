package com.gmail.mattdiamond98.hexagons;

public class AxialPoint {
	
	public float r,q;
	
	//q is up and down
	//r is downward to the side
	public AxialPoint(float q, float r) {
		this.q = q;
		this.r = r;
	}
	
	public static AxialPoint fromPoint(Point p) {
		float q = (float) ((p.x * Hexagon.R3 / 3 - p.y / 3) / Hexagon.SIZE);
		float r = (float) (p.y * 2/3 / Hexagon.SIZE);
		return new AxialPoint(q, r);
	}
	
	public Point toPoint() {
		return new Point(Hexagon.SIZE * Hexagon.R3 * (q + r/2), Hexagon.SIZE * 3/2 * r);
	}
	
	public CubePoint toCube() {
		return new CubePoint(q, r, -q-r);
	}
	
	public AxialPoint round() {
		return toCube().round().toAxial();
	}
	
	public String toString() {
		return Math.round(q) + "," + Math.round(-r);
	}
	
}
