package com.krzem.pacman;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;



public class Constants{
	public static final int DISPLAY_ID=0;
	public static final GraphicsDevice SCREEN=GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[DISPLAY_ID];
	public static final Rectangle WINDOW_SIZE=SCREEN.getDefaultConfiguration().getBounds();
	public static final int MAX_FPS=60;

	public static final Color BG_COLOR=new Color(0,0,0);

	public static final int BOARD_WIDTH=20;
	public static final int BOARD_HEIGHT=11;
	public static final int BOARD_TILE_SIZE=100;

	public static final int WALL_WIDTH=26;
	public static final Color WALL_COLOR=new Color(0,0,200);
	public static final BasicStroke WALL_STROKE=new BasicStroke(6);

	public static final int COOKIE_SIZE=30;
	public static final Color COOKIE_COLOR=new Color(230,230,230);

	public static final double PACMAN_SPEED_MULT=3;
	public static final double PACMAN_SPEED_EASING_PROC=0.8;
	public static final int PACMAN_RADIUS=50-13;
	public static final double PACMAN_TURN_THRESHOLD=5;
}
