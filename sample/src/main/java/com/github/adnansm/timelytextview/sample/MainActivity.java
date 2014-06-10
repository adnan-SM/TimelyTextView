package com.github.adnansm.timelytextview.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import com.github.adnansm.timelytextview.NumberView;

public class MainActivity extends Activity {
    private NumberView numView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numView = (NumberView) findViewById(R.id.textView1);

        numView.setAnimationType("loop");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
