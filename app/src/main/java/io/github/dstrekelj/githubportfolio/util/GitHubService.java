package io.github.dstrekelj.githubportfolio.util;

import io.github.dstrekelj.githubportfolio.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Domagoj on 30.6.2016..
 */
public interface GitHubService {
    @GET("users/{user}")
    Call<User> getUser(@Path("user") String user);
}
