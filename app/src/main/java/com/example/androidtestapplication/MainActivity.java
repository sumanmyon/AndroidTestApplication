package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.distribute.Distribute;

public class MainActivity extends AppCompatActivity {

    private Button crashButton;
    private Button reviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCenter.start(getApplication(), "40079d62-7e47-46b6-9caa-53e11cc105ff",
                Analytics.class, Crashes.class);
        AppCenter.start(getApplication(), "40079d62-7e47-46b6-9caa-53e11cc105ff", Distribute.class);

        crashButton = findViewById(R.id.crashButton);
        reviewButton = findViewById(R.id.reviewButton);

        crashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crashNow();

            }
        });

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ReviewActivity.class);
                startActivity(i);

            }
        });
    }


    private void crashNow(){
        throw new RuntimeException("App crashed.");
    }
}