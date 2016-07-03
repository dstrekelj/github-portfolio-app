package io.github.dstrekelj.githubportfolio.data.sources;

import android.util.Log;

import io.github.dstrekelj.githubportfolio.models.User;
import io.github.dstrekelj.githubportfolio.util.IGitHubServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Domagoj on 3.7.2016..
 */
public class UserRemoteDataSource implements IUserDataSource {
    public static final String TAG = UserRemoteDataSource.class.getSimpleName();

    private static UserRemoteDataSource sInstance = null;

    private IGitHubServiceAPI mGitHubServiceAPI;

    private UserRemoteDataSource(IGitHubServiceAPI gitHubServiceAPI) {
        mGitHubServiceAPI = gitHubServiceAPI;
    }

    public static UserRemoteDataSource getsInstance(IGitHubServiceAPI gitHubServiceAPI) {
        if (sInstance == null) {
            sInstance = new UserRemoteDataSource(gitHubServiceAPI);
        }
        return sInstance;
    }

    @Override
    public void getUser(final GetUserCallback callback) {
        Call<User> userCall = mGitHubServiceAPI.getUser("dstrekelj");
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, response.body().getEmail());
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, t.toString());
                callback.onFailure(t.toString());
            }
        });
    }

    @Override
    public void setUser(User user) {
        // Do nothing
    }
}
