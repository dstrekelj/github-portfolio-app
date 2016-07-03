package io.github.dstrekelj.githubportfolio.util;

import io.github.dstrekelj.githubportfolio.models.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Domagoj on 30.6.2016..
 */
public class GitHubService {
    public static final String TAG = GitHubService.class.getSimpleName();

    private static GitHubService sInstance = null;

    private IGitHubServiceAPI gitHubServiceAPI;

    private GitHubService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gitHubServiceAPI = retrofit.create(IGitHubServiceAPI.class);
    }

    public static GitHubService getInstance() {
        if (sInstance == null) {
            sInstance = new GitHubService();
        }
        return sInstance;
    }

    public IGitHubServiceAPI getGitHubServiceAPI() {
        return gitHubServiceAPI;
    }
}
