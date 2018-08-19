package ht.queeny.nbpharma;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.backendless.Backendless;

import ht.queeny.nbpharma.Settings.BackendlessSettings;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIMER=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Backendless.initApp(getApplicationContext(),
                BackendlessSettings.APPLICATION_ID,
                BackendlessSettings.API_KEY);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        }, SPLASH_TIMER);


    }
}
