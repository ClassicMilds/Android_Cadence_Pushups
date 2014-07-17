package edu.wsu.bdas.androidalpha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.media.SoundPool.OnLoadCompleteListener;
import android.view.MenuItem;
import android.media.SoundPool;
import android.media.AudioManager;
import android.os.CountDownTimer;
import edu.wsu.bdas.androidalpha.R;

public class CadencePushups extends Activity {

    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button stopB;
    public TextView text;
    private final long startTime = 180 * 1000;
    private final long interval = 1 * 1000;
    private SoundPool spool;
    boolean ready;
    private int sid1;
    private int sid2;
    private int sid3;
    private int sid4;
    private int sid5;
    AudioManager audioManager;
    float volume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadence_pushups);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        volume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        stopB = (Button) this.findViewById(R.id.cadenceBtn);
        stopB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                spool.unload(sid1);
                spool.unload(sid2);
                spool.unload(sid3);
                spool.unload(sid4);
                spool.unload(sid5);
                spool.release();
                finish();
            }
        });

        text = (TextView) this.findViewById(R.id.cadenceText);

        countDownTimer = new MyCountDownTimer(startTime, interval);
        countDownTimer.start();

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        spool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        sid1 = spool.load(this, R.raw.redd, 1);
        sid2 = spool.load(this, R.raw.set, 2);
        sid3 = spool.load(this, R.raw.go, 3);
        sid4 = spool.load(this, R.raw.down, 4);
        sid5 = spool.load(this, R.raw.up, 5);
        spool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                ready = true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cadence_pushups, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            text.setText("Time's up!");
        }

        public void onTick(long millisUntilFinished) {
            if (text.getText().length()==4) {
                spool.play(sid5, volume, volume, 1, 0, 1f);
                text.setText("Up");
            } else if (text.getText().length()==2) {
                spool.play(sid4, volume, volume, 1, 0, 1f);
                text.setText("Down");
            } else if (text.getText().length()==13) {

                if (ready) {
                    spool.play(sid1, volume, volume, 1, 0, 1f);
                    text.setText("Ready");

                }
            } else if (text.getText().length()==3) {
                spool.play(sid3, volume, volume, 1, 0, 1f);
                text.setText("Go!!!!");
            } else if (text.getText().length()==5) {
                spool.play(sid2, volume, volume, 1, 0, 1f);
                text.setText("Set");
            } else if (text.getText().length()==6) {
                spool.play(sid4,volume,volume,1,0,1f);
                text.setText("Down");
            }
        }
    }
}
