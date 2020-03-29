package com.krzem.pacman;



import java.awt.Graphics2D;
import java.lang.Math;
import java.util.ArrayList;



public class Board extends Constants{
	public static final Rectangle RECT=new Rectangle(WINDOW_SIZE.width/2-(BOARD_WIDTH-1)*BOARD_TILE_SIZE/2,WINDOW_SIZE.height/2-(BOARD_HEIGHT-1)*BOARD_TILE_SIZE/2,(BOARD_WIDTH-1)*BOARD_TILE_SIZE,(BOARD_HEIGHT-1)*BOARD_TILE_SIZE);
	public Main cls;
	public Pacman pacman;
	public ArrayList<Ghost> gl;
	public int[] board;



	public Board(Main cls){
		this.cls=cls;
		this._gen();
	}



	public void update(){
		for (Ghost gh:this.gl){
			gh.update();
		}
		this.pacman.update();
	}



	public void draw(Graphics2D g){
		g.setColor(BG_COLOR);
		g.fillRect(0,0,WINDOW_SIZE.width,WINDOW_SIZE.height);
		g.setColor(WALL_COLOR);
		g.setStroke(WALL_STROKE);
		for (int j=0;j<BOARD_HEIGHT;j++){
			for (int i=0;i<BOARD_WIDTH;i++){
				int v=this.board[j*BOARD_WIDTH+i]+0;
				int x=RECT.x+i*BOARD_TILE_SIZE;
				int y=RECT.y+j*BOARD_TILE_SIZE;
				if (v>=16){
					v-=16;
					g.setColor(COOKIE_COLOR);
					g.fillPolygon(new int[]{x,x+COOKIE_SIZE/2,x,x-COOKIE_SIZE/2},new int[]{y-COOKIE_SIZE/2,y,y+COOKIE_SIZE/2,y},4);
					g.setColor(WALL_COLOR);
				}
				if (v>0){
					if (v>=8){
						v-=8;
						g.drawLine(x-WALL_WIDTH/2,y-WALL_WIDTH/2,x-BOARD_TILE_SIZE/2,y-WALL_WIDTH/2);
						g.drawLine(x-WALL_WIDTH/2,y+WALL_WIDTH/2,x-BOARD_TILE_SIZE/2,y+WALL_WIDTH/2);
					}
					else{
						g.drawLine(x-WALL_WIDTH/2,y-WALL_WIDTH/2,x-WALL_WIDTH/2,y+WALL_WIDTH/2);
					}
					if (v>=4){
						v-=4;
						g.drawLine(x-WALL_WIDTH/2,y+WALL_WIDTH/2,x-WALL_WIDTH/2,y+BOARD_TILE_SIZE/2);
						g.drawLine(x+WALL_WIDTH/2,y+WALL_WIDTH/2,x+WALL_WIDTH/2,y+BOARD_TILE_SIZE/2);
					}
					else{
						g.drawLine(x-WALL_WIDTH/2,y+WALL_WIDTH/2,x+WALL_WIDTH/2,y+WALL_WIDTH/2);
					}
					if (v>=2){
						v-=2;
						g.drawLine(x+WALL_WIDTH/2,y-WALL_WIDTH/2,x+BOARD_TILE_SIZE/2,y-WALL_WIDTH/2);
						g.drawLine(x+WALL_WIDTH/2,y+WALL_WIDTH/2,x+BOARD_TILE_SIZE/2,y+WALL_WIDTH/2);
					}
					else{
						g.drawLine(x+WALL_WIDTH/2,y-WALL_WIDTH/2,x+WALL_WIDTH/2,y+WALL_WIDTH/2);
					}
					if (v>=1){
						v-=1;
						g.drawLine(x-WALL_WIDTH/2,y-WALL_WIDTH/2,x-WALL_WIDTH/2,y-BOARD_TILE_SIZE/2);
						g.drawLine(x+WALL_WIDTH/2,y-WALL_WIDTH/2,x+WALL_WIDTH/2,y-BOARD_TILE_SIZE/2);
					}
					else{
						g.drawLine(x-WALL_WIDTH/2,y-WALL_WIDTH/2,x+WALL_WIDTH/2,y-WALL_WIDTH/2);
					}
				}
			}
		}
		for (Ghost gh:this.gl){
			gh.draw(g);
		}
		this.pacman.draw(g);
	}



	private void _gen(){
		this.board=new int[BOARD_WIDTH*BOARD_HEIGHT];
		for (int i=0;i<BOARD_WIDTH;i++){
			this.board[i]+=(i<BOARD_WIDTH-1?2:0)+(i>0?8:0);
			this.board[i+(BOARD_HEIGHT-1)*BOARD_WIDTH]+=(i<BOARD_WIDTH-1?2:0)+(i>0?8:0);
		}
		for (int j=0;j<BOARD_HEIGHT;j++){
			this.board[j*BOARD_WIDTH]+=(j<BOARD_HEIGHT-1?4:0)+(j>0?1:0);
			this.board[j*BOARD_WIDTH+BOARD_WIDTH-1]+=(j<BOARD_HEIGHT-1?4:0)+(j>0?1:0);
		}
		this._gen_b(this.board);
		this.gl=new ArrayList<Ghost>();
		for (int j=0;j<BOARD_HEIGHT;j++){
			for (int i=0;i<BOARD_WIDTH;i++){
				int v=this.board[j*BOARD_WIDTH+i];
				if (v>=64){
					v-=64;
					this.gl.add(new Ghost(this.cls,this,i,j));
				}
				if (v>=32){
					v-=32;
					this.pacman=new Pacman(this.cls,this,i,j);
				}
				this.board[j*BOARD_WIDTH+i]=v;
			}
		}
	}



	private void _gen_b(int[] wl){
		// https://stanford.edu/~cpiech/cs221/homework/prog/pacman/pacman.html

		// %%%%%%%%%%%%%%%%%%%%
		// %o...%........%....%
		// %.%%.%.%%%%%%.%.%%.%
		// %.%..............%.%
		// %.%.%%.%%  %%.%%.%.%
		// %......%G  G%......%
		// %.%.%%.%%%%%%.%%.%.%
		// %.%..............%.%
		// %.%%.%.%%%%%%.%.%%.%
		// %....%...P....%...o%
		// %%%%%%%%%%%%%%%%%%%%

		// %%%%%%%%%%%%%%%%%%%%%%%%%%%%
		// %............%%............%
		// %.%%%%.%%%%%.%%.%%%%%.%%%%.%
		// %o%%%%.%%%%%.%%.%%%%%.%%%%o%
		// %.%%%%.%%%%%.%%.%%%%%.%%%%.%
		// %..........................%
		// %.%%%%.%%.%%%%%%%%.%%.%%%%.%
		// %.%%%%.%%.%%%%%%%%.%%.%%%%.%
		// %......%%....%%....%%......%
		// %%%%%%.%%%%% %% %%%%%.%%%%%%
		// %%%%%%.%%%%% %% %%%%%.%%%%%%
		// %%%%%%.%            %.%%%%%%
		// %%%%%%.% %%%%  %%%% %.%%%%%%
		// %     .  %G  GG  G%  .     %
		// %%%%%%.% %%%%%%%%%% %.%%%%%%
		// %%%%%%.%            %.%%%%%%
		// %%%%%%.% %%%%%%%%%% %.%%%%%%
		// %............%%............%
		// %.%%%%.%%%%%.%%.%%%%%.%%%%.%
		// %.%%%%.%%%%%.%%.%%%%%.%%%%.%
		// %o..%%.......  .......%%..o%
		// %%%.%%.%%.%%%%%%%%.%%.%%.%%%
		// %%%.%%.%%.%%%%%%%%.%%.%%.%%%
		// %......%%....%%....%%......%
		// %.%%%%%%%%%%.%%.%%%%%%%%%%.%
		// %.............P............%
		// %%%%%%%%%%%%%%%%%%%%%%%%%%%%

		this._set(wl,5,0,2);
		this._set(wl,5,1,0,2);
		this._set(wl,5,2,0);
		this._set(wl,5,10,0);
		this._set(wl,5,9,0,2);
		this._set(wl,5,8,2);

		this._set(wl,2,2,1,2);
		this._set(wl,3,2,3);
		this._set(wl,2,3,0,2);
		this._set(wl,2,4,0);
		this._set(wl,2,8,0,1);
		this._set(wl,3,8,3);
		this._set(wl,2,7,0,2);
		this._set(wl,2,6,2);

		this._set(wl,4,4,1);
		this._set(wl,5,4,3);
		this._set(wl,4,6,1);
		this._set(wl,5,6,3);

		this._set(wl,7,2,1);
		this._set(wl,8,2,1,3);
		this._set(wl,9,2,1,3);
		this._set(wl,7,8,1);
		this._set(wl,8,8,1,3);
		this._set(wl,9,8,1,3);

		this._set(wl,7,4,1,2);
		this._set(wl,8,4,1,3);
		this._set(wl,9,4,3);
		this._set(wl,7,5,0,2);
		this._set(wl,7,6,0,1);
		this._set(wl,8,6,1,3);
		this._set(wl,9,6,1,3);



		this._set(wl,14,0,2);
		this._set(wl,14,1,0,2);
		this._set(wl,14,2,0);
		this._set(wl,14,10,0);
		this._set(wl,14,9,0,2);
		this._set(wl,14,8,2);

		this._set(wl,17,2,2,3);
		this._set(wl,16,2,1);
		this._set(wl,17,3,0,2);
		this._set(wl,17,4,0);
		this._set(wl,17,8,0,3);
		this._set(wl,16,8,1);
		this._set(wl,17,7,0,2);
		this._set(wl,17,6,2);

		this._set(wl,15,4,3);
		this._set(wl,14,4,1);
		this._set(wl,15,6,3);
		this._set(wl,14,6,1);

		this._set(wl,12,2,3);
		this._set(wl,11,2,1,3);
		this._set(wl,10,2,1,3);
		this._set(wl,12,8,3);
		this._set(wl,11,8,1,3);
		this._set(wl,10,8,1,3);

		this._set(wl,12,4,2,3);
		this._set(wl,11,4,1,3);
		this._set(wl,10,4,1);
		this._set(wl,12,5,0,2);
		this._set(wl,12,6,0,3);
		this._set(wl,11,6,1,3);
		this._set(wl,10,6,1,3);

		this._set(wl,9,9,5);
		this._set(wl,8,5,6);
		this._set(wl,11,5,6);
		for (int j=0;j<BOARD_HEIGHT;j++){
			for (int i=0;i<BOARD_WIDTH;i++){
				if (wl[j*BOARD_WIDTH+i]==0){
					wl[j*BOARD_WIDTH+i]+=16;
				}
			}
		}
		this._reset(wl,9,4);
		this._reset(wl,9,5);
		this._reset(wl,10,4);
		this._reset(wl,10,5);
	}



	private void _set(int[] wl,int x,int y,int... vl){
		int tv=0;
		for (int v:vl){
			tv+=Math.pow(2,v);
		}
		wl[y*BOARD_WIDTH+x]+=tv;
	}



	private void _reset(int[] wl,int x,int y){
		wl[y*BOARD_WIDTH+x]=0;
	}
}