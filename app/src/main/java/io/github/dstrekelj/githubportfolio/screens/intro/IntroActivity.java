package io.github.dstrekelj.githubportfolio.screens.intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.github.dstrekelj.githubportfolio.R;
import io.github.dstrekelj.githubportfolio.screens.home.HomeActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        try {
            Thread.sleep(5000);
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
