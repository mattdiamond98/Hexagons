package com.gmail.mattdiamond98.hexagons.animation;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;

import com.gmail.mattdiamond98.hexagons.HexGrid;

public class HexagonFrame extends JFrame {
	
	private static final long serialVersionUID = -1412971367033616886L;
	
	public static final int HEIGHT = 700;
	public static final int WIDTH = 700;
	public static final Rectangle BOUNDS = new Rectangle(0,0,WIDTH,HEIGHT);
	
	
	public HexagonFrame() {
		HexGrid grid = new HexGrid();
		
		add(grid);
		
		setResizable(false);
		pack();
		
		setTitle("Hexagon Example 1");
		setBounds(BOUNDS);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame j = new HexagonFrame();
				j.setVisible(true);
			}
		});
	}
	
}
