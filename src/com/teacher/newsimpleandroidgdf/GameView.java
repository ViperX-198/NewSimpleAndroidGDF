package com.teacher.newsimpleandroidgdf;

import com.teacher.framework.util.InputHandler;
import com.teacher.framework.util.Painter;
import com.teacher.game.state.LoadState;
import com.teacher.game.state.State;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

public class GameView extends SurfaceView implements Runnable{
	
	private volatile State currentState;
	
	private Bitmap gameImage;
	private Rect gameImageSrc;
	private Rect gameImageDst;
	private Canvas gameCanvas;
	private Painter graphics;
	
	private Thread gameThread;
	private volatile boolean running;
	
    private InputHandler inputHandler;
	
	private void initInput() {
		if(inputHandler==null)
			inputHandler=new InputHandler();
		setOnTouchListener(inputHandler);
	}
	
	public void setCurrentState(State newState) {
		// TODO Auto-generated method stub
		System.gc();
		newState.init();
		currentState=newState;
		inputHandler.setCurrentState(currentState);
	}
	
	public GameView(Context context,int gameWidth,int gameHeight) {
		super(context);
		
		gameImage=Bitmap.createBitmap(gameWidth,gameHeight,Bitmap.Config.RGB_565);
		gameImageSrc=new Rect(0,0,gameWidth,gameHeight);
		gameImageDst=new Rect();
		gameCanvas=new Canvas(gameImage);
		graphics=new Painter(gameCanvas);
		
		SurfaceHolder holder=getHolder();
		holder.addCallback(new Callback() {
			public void surfaceChanged(SurfaceHolder holder,int format,int width,int height) {
				
			}
			
			public void surfaceCreated(SurfaceHolder holder) {
				//Log.d("GameView","视图创建");
				initInput();
				if(currentState==null)
					setCurrentState(new LoadState());
				initGame();
			}
			
			public void surfaceDestroyed(SurfaceHolder holder) {
				//Log.d("GameView","视图销毁");
				pauseGame();
			}
		});
	}

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		long updateDurationMillis=0;
		long sleepDurationMillis=0;
		while(running) {
			long beforeUpdateRender=System.nanoTime();
			long deltaMillis=updateDurationMillis+sleepDurationMillis;
			
			updateAndRender(deltaMillis);
			
			updateDurationMillis=(System.nanoTime()-beforeUpdateRender)/1000000L;
			sleepDurationMillis=Math.max(2, 17-updateDurationMillis);
			
			try {
				Thread.sleep(sleepDurationMillis);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	private void updateAndRender(long deltaMillis) {
		// TODO Auto-generated method stub
		currentState.update(deltaMillis/1000f);
		currentState.render(graphics);
		renderGameImage();
	}

	private void renderGameImage() {
		// TODO Auto-generated method stub
		Canvas screen=getHolder().lockCanvas();
		if(screen !=null) {
			screen.getClipBounds(gameImageDst);
			screen.drawBitmap(gameImage, gameImageSrc, gameImageDst,null);
			getHolder().unlockCanvasAndPost(screen);
		}
	}

	private void initGame() {
		running=true;
		gameThread=new Thread(this,"游戏线程");
		gameThread.start();
	}
	
	private void pauseGame() {
		running=false;
		while(gameThread.isAlive()) {
			try {
				gameThread.join();
			    break;
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	}

	

}
