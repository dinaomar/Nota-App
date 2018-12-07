package sound.record;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import dina.nota.main.MainActivity;

public class SoundRecorder {

	private MediaRecorder mRecorder = null;
	private static String mFileName = null;
	private MediaPlayer mPlayer;

	public void startRecording() {
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(setAudioFileName());
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e("##", "prepare() failed");
		}

		mRecorder.start();

	}

	public String setAudioFileName() {
		mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
		int note_number = MainActivity.all_notes.size() + 1;
		mFileName += "/note" + note_number + ".3gp";
		return mFileName;
	}

	public void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;

	}

	public void startPlaying() {
		mPlayer = new MediaPlayer();
		try {
			mPlayer.setDataSource(mFileName);
			mPlayer.prepare();
			mPlayer.start();
		} catch (IOException e) {
			Log.e("!!!!", "prepare() failed");
		}
	}

}
