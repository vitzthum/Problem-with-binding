package com.vitzi.ringtonescheduler;


import android.app.IntentService;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Looper;
import android.util.Log;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by vitzt on 15.10.2015.
 */
public class Service extends IntentService {

	public Service() {
		super("");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d("Log", "onHandleIntent");
		final AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);

		Handler handler = new Handler(Looper.getMainLooper());

		handler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(Service.this, "RingerMode set to: " + audioManager.getRingerMode(), Toast.LENGTH_LONG).show();
			}
		});
	}


}
