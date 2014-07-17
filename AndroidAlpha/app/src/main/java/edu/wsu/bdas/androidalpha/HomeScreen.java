package edu.wsu.bdas.androidalpha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;

import edu.wsu.bdas.androidalpha.R;

public class HomeScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        Button pBtn = (Button) findViewById(R.id.cadenceP);
        pBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), CadencePushups.class);
                startActivity(myIntent);
            }
        });

        Button sBtn = (Button) findViewById(R.id.sitP);
        sBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), SitUPS.class);
                startActivity(myIntent);
            }
        });

        Button rBtn = (Button) findViewById(R.id.runP);
        rBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), RunnerLog.class);
                startActivity(myIntent);
            }
        });

        Button setBtn = (Button) findViewById(R.id.setP);
        setBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        Button exitBtn = (Button) findViewById(R.id.exit);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
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
}
