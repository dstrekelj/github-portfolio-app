package io.github.dstrekelj.githubportfolio.data.sources;

import io.github.dstrekelj.githubportfolio.models.User;

/**
 * Created by Domagoj on 3.7.2016..
 */
public interface IUserDataSource {
    interface GetUserCallback {
        void onSuccess(User user);
        void onFailure(String error);
    }

    interface RefreshCallback {
        void onSuccess();
        void onFailure(String error);
    }

    void getUser(GetUserCallback callback);

    void setUser(User user);

    void refresh(RefreshCallback callback);
}
