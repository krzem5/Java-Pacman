package com.krzem.pacman;



import java.awt.Graphics2D;
import java.lang.Math;



public class Pacman extends Constants{
	public Main cls;
	public Board b;
	public double x;
	public double y;
	public int vx;
	public int vy;
	public double dvx;
	public double dvy;



	public Pacman(Main cls,Board b,int x,int y){
		this.cls=cls;
		this.b=b;
		this.x=x*BOARD_TILE_SIZE;
		this.y=y*BOARD_TILE_SIZE;
		this.vx=0;
		this.vy=0;
		this.dvx=0;
		this.dvy=0;
	}



	public void update(){
		if (this.cls.KEYBOARD.pressed(37)){
			this.vx=-1;
			this.vy=0;
		}
		if (this.cls.KEYBOARD.pressed(38)){
			this.vx=0;
			this.vy=-1;
		}
		if (this.cls.KEYBOARD.pressed(39)){
			this.vx=1;
			this.vy=0;
		}
		if (this.cls.KEYBOARD.pressed(40)){
			this.vx=0;
			this.vy=1;
		}
		if (!this._empty(this.vx,this.vy)){
			this.vx=0;
			this.vy=0;
			this.dvx=0;
			this.dvy=0;
		}
		this.dvx=this._ease(this.dvx,this.vx);
		this.dvy=this._ease(this.dvy,this.vy);
		this.x+=this.dvx*PACMAN_SPEED_MULT;
		this.y+=this.dvy*PACMAN_SPEED_MULT;
	}



	public void draw(Graphics2D g){
		g.setColor(java.awt.Color.yellow);
		g.fillRect(this.b.RECT.x+(int)this.x-PACMAN_RADIUS,this.b.RECT.y+(int)this.y-PACMAN_RADIUS,PACMAN_RADIUS*2,PACMAN_RADIUS*2);
	}



	private boolean _empty(int x,int y){
		int ax=(int)Math.round((int)(this.x+BOARD_TILE_SIZE/2)/BOARD_TILE_SIZE-0.5)+x;
		int ay=(int)Math.round((int)(this.y+BOARD_TILE_SIZE/2)/BOARD_TILE_SIZE-0.5)+y;
		int bx=(int)Math.round((int)(this.x+x*PACMAN_SPEED_MULT+BOARD_TILE_SIZE/2)/BOARD_TILE_SIZE-0.5)+x;
		int by=(int)Math.round((int)(this.y+y*PACMAN_SPEED_MULT+BOARD_TILE_SIZE/2)/BOARD_TILE_SIZE-0.5)+y;
		if (ax==bx&&ay==by){
			return true;
		}
		if (this.b.board[ay*BOARD_WIDTH+ax]%16==0){
			return true;
		}
		return false;
	}



	private double _ease(double v,double dv){
		return v*(1-PACMAN_SPEED_EASING_PROC)+dv*PACMAN_SPEED_EASING_PROC;
	}
}