package com.teacher.game.state;

import com.teacher.framework.util.Painter;
import com.teacher.newsimpleandroidgdf.GameMainActivity;
import android.view.MotionEvent;

public abstract class State {
	
	public void setCurrentState(State newState) {
		GameMainActivity.sGame.setCurrentState(newState);
	}
	public abstract void init();
	public abstract void update(float delta);
	public abstract void render(Painter g);
	public abstract boolean onTouch(MotionEvent e,int scaleX,int scaleY);

}
