package com.gmail.mattdiamond98.hexagons;

public class CubePoint {
	
	public float x,y,z;
	
	public CubePoint(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public AxialPoint toAxial() {
		return new AxialPoint(z, x);
	}
	
	//rx + ry + rz = theta
	public CubePoint round() {
		float rx = Math.round(x);
		float ry = Math.round(y);
		float rz = Math.round(z);
		
		float xdiff = Math.abs(rx - x);
		float ydiff = Math.abs(ry - y);
		float zdiff = Math.abs(rz - z);
		
		if (xdiff > ydiff && xdiff > zdiff) {
			rx = -ry-rz;
		} else if (ydiff > zdiff) {
			ry = -rx-rz;
		} else {
			rz = -rx-ry;
		}
		
		return new CubePoint(rx, ry, rz);
	}
	
}
