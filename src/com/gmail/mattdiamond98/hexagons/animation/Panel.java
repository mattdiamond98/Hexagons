//package com.gmail.mattdiamond98.hexagons.animation;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.MouseInfo;
//
//import javax.swing.JPanel;
//
//import com.gmail.mattdiamond98.hexagons.AxialPoint;
//import com.gmail.mattdiamond98.hexagons.HexGrid;
//import com.gmail.mattdiamond98.hexagons.Hexagon;
//import com.gmail.mattdiamond98.hexagons.Point;
//
//public class Panel extends JPanel {
//
//	private static final long serialVersionUID = 8274163344108532553L;
//	
//	static double t = System.currentTimeMillis();
//	static double i = 100;
//	@Override
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		this.setBackground(new Color(255,255,255));
//		HexGrid grid = HexGrid.shapedAsDiamond(new Point(0, 0), 8);
//		grid.draw(g);
////		new Hexagon(0,0).hexTo(hx, hy)
//		Hexagon hex = new Hexagon(pixelToHex(MouseInfo.getPointerInfo().getLocation(), grid.center));
//		hex.color = new Color(201, 201, 201);
//		hex.drawOn(g);
//
//		i += t - System.currentTimeMillis();
//		t = System.currentTimeMillis();
//		if (i <= 0) {
//			i += 100;
//			paintComponent(g);
//		}
//		
//		
//		g.setColor(new Color(10,10,10));
//		g.drawString("Mouse x: " + (MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX()), 50, 60);
//		g.drawString("Mouse y: " + (MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY()), 50, 74);
//		
//		g.drawString("Position: " + pixelToHex(MouseInfo.getPointerInfo().getLocation(), grid.center), 50, 98);
//		
//		
//		Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				repaint();
//			}
//		});
//		t.start();
//	}
//	
//	public AxialPoint pixelToHex(java.awt.geom.Point2D pixel, Point center) {
//		float q = (float) ((pixel.getX() - this.getLocationOnScreen().getX() - center.x) * 2/3 / Hexagon.SIZE);
//		float r = (float) (((pixel.getX() - this.getLocationOnScreen().getX() - center.x) / 3 + Math.sqrt(3)/3 * (pixel.getY()  - this.getLocationOnScreen().getY()- center.y)) / Hexagon.SIZE);
//		return new AxialPoint(q, r).round();
//	}
//	
//}
