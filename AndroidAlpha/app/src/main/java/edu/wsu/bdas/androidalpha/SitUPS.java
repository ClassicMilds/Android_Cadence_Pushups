package edu.wsu.bdas.androidalpha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.view.MenuItem;
import android.os.CountDownTimer;

import java.util.concurrent.TimeUnit;

import edu.wsu.bdas.androidalpha.R;

public class SitUPS extends Activity {


    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button sitB;
    public TextView text;
    private final long startTime = 300 * 1000;
    private final long interval = 1 * 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sit_ups);

        sitB = (Button) this.findViewById(R.id.sitBtn);
        sitB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), HomeScreen.class);
                startActivity(myIntent);
            }
        });

        text = (TextView) this.findViewById(R.id.sitText);

        countDownTimer = new MyCountDownTimer(startTime, interval);
        countDownTimer.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sit_u, menu);
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

        @Override
        public void onTick(long millisUntilFinished) {
            text.setText(String.format("%d min, %d sec",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                    )
            );
        }
    }
}
