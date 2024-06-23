package com.teacher.game.state;

import com.teacher.framework.util.Painter;
import com.teacher.newsimpleandroidgdf.Assets;

import android.util.Log;
import android.view.MotionEvent;

public class MenuState extends State {

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		//Log.d("MenuState","经过时间"+delta+"秒");
		Assets.runAnim.update(delta);
	}

	@Override
	public void render(Painter g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.welcome, 0, 0);
		Assets.runAnim.render(g, 100, 100);

	}

	@Override
	public boolean onTouch(MotionEvent e, int scaleX, int scaleY) {
		// TODO Auto-generated method stub
		Log.d("MenuState","点击游戏坐标("+scaleX+","+scaleY+")");
		return true;
	}

}
