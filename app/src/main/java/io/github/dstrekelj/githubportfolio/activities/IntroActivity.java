package io.github.dstrekelj.githubportfolio.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.github.dstrekelj.githubportfolio.R;
import io.github.dstrekelj.githubportfolio.data.sources.IUserDataSource;
import io.github.dstrekelj.githubportfolio.data.UserRepository;
import io.github.dstrekelj.githubportfolio.models.User;

public class IntroActivity extends AppCompatActivity {
    public static final String TAG = IntroActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        UserRepository userRepository = UserRepository.getInstance(getApplicationContext());
        userRepository.refresh(new IUserDataSource.RefreshCallback() {
            @Override
            public void onSuccess() {
                switchState();
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    private void switchState() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
