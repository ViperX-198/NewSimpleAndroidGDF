package com.teacher.game.state;

import com.teacher.framework.util.Painter;
import com.teacher.newsimpleandroidgdf.Assets;

import android.util.Log;

import android.view.MotionEvent;

public class LoadState extends State {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		Log.d("LoadState","��ʼ��ȡ��Դ");
		Assets.load();
		Log.d("LoadState","��ȡ��Դ���");

	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		setCurrentState(new MenuState());

	}

	@Override
	public void render(Painter g) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onTouch(MotionEvent e, int scaleX, int scaleY) {
		// TODO Auto-generated method stub
		return false;
	}

}
