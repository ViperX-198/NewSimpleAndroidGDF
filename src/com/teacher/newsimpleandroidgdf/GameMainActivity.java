package com.teacher.newsimpleandroidgdf;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;

public class GameMainActivity extends Activity {
	
	public static final int GAME_WIDTH=800;
	public static final int GAME_HEIGHT=450;
	public static GameView sGame;
	public static AssetManager assets;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		assets=getAssets();
		sGame=new GameView(this,GAME_WIDTH,GAME_HEIGHT);
		setContentView(sGame);
	}

}
