package com.teacher.framework.util;

import com.teacher.game.state.State;
import com.teacher.newsimpleandroidgdf.GameMainActivity;


import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class InputHandler implements OnTouchListener {
	
    private State currentState;
	
	public void setCurrentState(State currentState) {
		this.currentState=currentState;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int scaleX=(int)((event.getX()/v.getWidth())*GameMainActivity.GAME_WIDTH);
		int scaleY=(int)((event.getY()/v.getHeight())*GameMainActivity.GAME_HEIGHT);
		return currentState.onTouch(event, scaleX, scaleY);
	}

}
