package com.teacher.newsimpleandroidgdf;

import java.io.IOException;
import java.io.InputStream;

import com.teacher.framework.animation.Animation;
import com.teacher.framework.animation.Frame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.media.SoundPool;
import android.media.AudioManager;


public class Assets {
	
	private static SoundPool soundPool;
	
    public static Bitmap welcome;
    
    public static Animation runAnim;
	
	public static void load() {
		welcome=loadBitmap("welcome.png",false);
		
		Frame f1=new Frame(loadBitmap("run_anim1.png",true),0.1f);
		Frame f2=new Frame(loadBitmap("run_anim2.png",true),0.1f);
		Frame f3=new Frame(loadBitmap("run_anim3.png",true),0.1f);
		Frame f4=new Frame(loadBitmap("run_anim4.png",true),0.1f);
		Frame f5=new Frame(loadBitmap("run_anim5.png",true),0.1f);
		
		runAnim=new Animation(f1,f2,f3,f4,f5,f3,f2);
	}
	private static Bitmap loadBitmap(String filename,boolean transparency) {
		InputStream inputStream=null;
		try {
			inputStream=GameMainActivity.assets.open(filename);
		}catch(IOException e) {
			e.printStackTrace();
		}
		Options options=new Options();
		if(transparency) {
			options.inPreferredConfig=Config.ARGB_8888;
		}else {
			options.inPreferredConfig=Config.RGB_565;
		}
		Bitmap bitmap=BitmapFactory.decodeStream(inputStream,null,options);
		return bitmap;
	}
	
	private static int loadSound(String filename) {
		int soundID=0;
		if(soundPool==null) {
			soundPool=new SoundPool(25,AudioManager.STREAM_MUSIC,0);
		}
		try {
			soundID=soundPool.load(GameMainActivity.assets.openFd(filename),1);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return soundID;
	}
	
	public static void playSound(int soundID) {
		soundPool.play(soundID, 1, 1,1, 0, 1);
	}

}
