package cn.isif.videolist.video;

import android.media.MediaPlayer;

public class MediaHandler {
	private static MediaPlayer mPlayer;

	public synchronized static MediaPlayer getInstance() {
		if (mPlayer == null) {
			mPlayer = new MediaPlayer();
		}
		return mPlayer;
	}

	/**
	 * MediaPlayer resume （重新开始）
	 */
	public static void resume() {
		if (mPlayer != null) {
			mPlayer.start();
		}

	}

	/**
	 * MediaPlayer pause （暂定播放）
	 */
	public static void pause() {
		if (mPlayer != null) {
			mPlayer.pause();
		}
	}

	/**
	 * MediaPlayer release （播出）
	 */
	public static void release() {
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
}
