package com.mindhack;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {

	private static MediaPlayer mp = null;
	
	/**
	 * Stop old song and start new one
	 * @param context
	 * @param musicPath
	 */
	public static void play(Context context, int musicPath) {
		stop(context);

		// Start music only if not disable in preference
		if (Prefs.getMusic(context)) {
			mp = MediaPlayer.create(context, musicPath);
			mp.setLooping(true);
			mp.start();
		}
	}

	/**
	 * Stop the music
	 * @param context
	 */
	public static void stop(Context context) {
		if(mp != null){
			mp.stop();
			mp.release();
			mp = null;
		}
			
	}

}
