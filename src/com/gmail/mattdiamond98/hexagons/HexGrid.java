package com.gmail.mattdiamond98.hexagons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import com.gmail.mattdiamond98.hexagons.animation.HexagonFrame;

public class HexGrid extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 8274163344108532553L;
	
	static double t = System.currentTimeMillis();
	static double i = 100;
	
	static boolean lineMode = true;
	
	private Set<Hexagon> hexagons;
	public Point center;
	
	public static HexGrid shapedAsDiamond(Point center, int size) {
		HexGrid grid = new HexGrid();
		grid.center = AxialPoint.fromPoint(center).round().toPoint();
		Hexagon c = new Hexagon(center);
		for (int i = -size; i <= size; i++)
			for (int j = -size; j <= size; j++) {
				grid.hexagons.add(c.hexTo(i, j));
			}
		return grid;
	}
	
	public static HexGrid shapedAsHex(int size) {
		HexGrid grid = new HexGrid();
		//TODO: implementation
		return grid;
	}
	
	public static HexGrid shapedFit() {
		HexGrid grid = new HexGrid();
		grid.center = new Point(Hexagon.WIDTH / 2, Hexagon.HEIGHT / 2);
		for (int i=-100;i<100;i++)
			for (int j=-100;j<100;j++) {
				Hexagon hex = new Hexagon(0,0).hexTo(i, j);
				if (hex.center.isWithin(HexagonFrame.BOUNDS)) {
					grid.hexagons.add(hex);
				}
			}
		return grid;
	}
	
	public HexGrid() {
		hexagons = new HashSet<>();
	}
	
	public void draw(Graphics g) {
		for (Hexagon hex : hexagons) {
			hex.drawOn(g);
		}
	}
	
	public Set<Hexagon> getHexagons() {
		return hexagons;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		this.requestFocusInWindow();
		super.paintComponent(g);
		this.setBackground(new Color(255,255,255));
		HexGrid grid = HexGrid.shapedFit();
		grid.draw(g);
//		new Hexagon(0,0).hexTo(hx, hy)
		java.awt.Point mp = MouseInfo.getPointerInfo().getLocation();
		mp.translate((int)-this.getLocationOnScreen().getX(),(int)-this.getLocationOnScreen().getY());
		AxialPoint mouse = grid.pixelToHex(new Point(mp));
		Hexagon hex = new Hexagon(mouse);
		hex.color = new Color(201, 201, 201);
		hex.drawOn(g);

		i += t - System.currentTimeMillis();
		t = System.currentTimeMillis();
		if (i <= 0) {
			i += 100;
			paintComponent(g);
		}
		
		if (lineMode) {
			//TODO: Implementation ***
			Hexagon h1 = new Hexagon(0,0).hexTo(3, -6);
			Hexagon h2 = new Hexagon(grid.pixelToHex(new Point(mp)));
			Point p1 = h1.center;
			Point p2 = h2.center;
			grid.drawHexLine(grid, h1, h2, g);
			g.setColor(new Color(0,0,0));



			g.drawString("Distance: " + grid.hexDistance(grid.pixelToHex(p1), grid.pixelToHex(p2)), 10, 76);
		}
		
		g.setColor(new Color(10,10,10));
		g.drawString("Mouse x: " + (MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX()), 10, 20);
		g.drawString("Mouse y: " + (MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY()), 10, 34);
		g.drawString("Line Mode: " + lineMode, 10, 48);
		g.drawString("Position: " + mouse, 10, 62);

		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				repaint();
			}
		});
		t.start();
	}
	
	public void drawHexLine(HexGrid gr, Hexagon h1, Hexagon h2, Graphics g) {
		Point p1 = h1.center;
		Point p2 = h2.center;
		if (h1.axial == null) System.out.println("h1 null");
		if (h2.axial == null) System.out.println("h2 null");
		int dist = gr.hexDistance(h1.axial, h2.axial);
		List<Point> points = new ArrayList<>();
		Vector lerp = new Vector(p2.x - p1.x, p2.y - p1.y).divide(dist);
		for (int i = 0; i < dist; i++) {
			points.add(lerp.multiply(i).addTo(new Point(p1.x, p1.y)));
			
//			g.drawString("lerp" + i + ": " + lerp.multiply(i).addTo(new Point(p1.x, p1.y)), 14,  90 + (13 * i));
		}
		for (Point p : points) {
			Hexagon hex = new Hexagon(gr.pixelToHex(p.add(new Point(0.0000001, 0.0000001))));

			hex.color = new Color(201, 201, 201);
			hex.drawOn(g);

		}
		g.setColor(new Color(10,10,10));

		g.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
		points.add(p2);
		for (Point p : points) {
			g.setColor(new Color(255,255,255));
			g.fillOval((int)p.x-2, (int)p.y-2, 4,4);
		}
	}
	
	static class Vector {
		
		double i,j;
		public Vector(double i, double j) {
			this.i = i;
			this.j = j;
		}
		
		public Vector multiply(int n) {
			return new Vector(i * n, j * n);
		}
		
		public Vector divide(int n) {
			return new Vector(i / n, j / n);
		}
		
		public Point addTo(Point p) {
			return new Point(p.x + i, p.y + j);
		}
		
	}
	
//	List<Hexagon> line(AxialPoint p1, AxialPoint p2) {
//		List<Hexagon> line = new ArrayList<>();
//		int n = hexDistance(p1, p2);
//		for (int i=0; i <= n; i++) {
//			double t = (n == 0) ? 0.0 : i / n;
//			line.add(new Hexagon(0,0).hexTo(, hy))
//		}
//	}
	
	int hexDistance(AxialPoint a, AxialPoint b) {
		return (int) (Math.abs(a.q - b.q)
				+ Math.abs(a.q - a.r - b.q + b.r)
				+ Math.abs(a.r - b.r)) / 2;
	}
	
//	double diagonalDist(Point p1, Point p2) {
//		return Math.max(Math.abs(p2.x - p1.x), Math.abs(p2.y - p1.y));
//	}
	
	public AxialPoint pixelToHex(Point p) {
//		Point c = new Point(0,0);
		double distanceSquared = Double.MAX_VALUE;
		Hexagon hexagon = null;
		for (Hexagon hex : hexagons) {
			if (hex.center.distanceSquared(p) < distanceSquared) {
				distanceSquared = hex.center.distanceSquared(p);
				hexagon = hex;
			}
		}
		return hexagon.axial;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e);

		if (e.getKeyChar() == ' ') {
			lineMode = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e);

		if (e.getKeyChar() == ' ') {
			lineMode = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e);
	}
	
//	public AxialPoint pixelToHex(java.awt.geom.Point2D pixel, Point center) {
//		float q = (float) ((pixel.getX() - this.getLocationOnScreen().getX() - center.x) * 2/3 / Hexagon.SIZE);
//		float r = (float) (((pixel.getX() - this.getLocationOnScreen().getX() - center.x) / 3 + Math.sqrt(3)/3 * (pixel.getY()  - this.getLocationOnScreen().getY()- center.y)) / Hexagon.SIZE);
//		return new AxialPoint(q, r).round();
//	}

	
}
