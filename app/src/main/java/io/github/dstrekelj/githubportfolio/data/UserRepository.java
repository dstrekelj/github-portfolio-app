package io.github.dstrekelj.githubportfolio.data;

import android.content.Context;

import io.github.dstrekelj.githubportfolio.data.sources.IUserDataSource;
import io.github.dstrekelj.githubportfolio.data.sources.UserLocalDataSource;
import io.github.dstrekelj.githubportfolio.data.sources.UserRemoteDataSource;
import io.github.dstrekelj.githubportfolio.models.User;
import io.github.dstrekelj.githubportfolio.util.GitHubService;
import io.github.dstrekelj.githubportfolio.util.IGitHubServiceAPI;

/**
 * Created by Domagoj on 3.7.2016..
 */
public class UserRepository implements IUserDataSource {
    private static final String TAG = UserRepository.class.getSimpleName();

    private static UserRepository sInstance = null;

    private final IUserDataSource mUserRemoteDataSource;
    private final IUserDataSource mUserLocalDataSource;

    User mCachedUser;

    private UserRepository(IUserDataSource mUserRemoteDataSource,
                           IUserDataSource mUserLocalDataSource) {
        this.mUserRemoteDataSource = mUserRemoteDataSource;
        this.mUserLocalDataSource = mUserLocalDataSource;
    }

    public static UserRepository getInstance(Context context) {
        if (sInstance == null) {
            IGitHubServiceAPI gitHubServiceAPI = GitHubService.getInstance().getGitHubServiceAPI();
            IUserDataSource mUserRemoteDataSource = UserRemoteDataSource.getsInstance(gitHubServiceAPI);
            IUserDataSource mUserLocalDataSource = UserLocalDataSource.getInstance(context);
            sInstance = new UserRepository(mUserRemoteDataSource, mUserLocalDataSource);
        }
        return sInstance;
    }

    @Override
    public void getUser(GetUserCallback callback) {
        if (callback != null) {
            if (mCachedUser != null) {
                callback.onSuccess(mCachedUser);
            } else {
                mUserRemoteDataSource.getUser(callback);
            }
        }
    }

    @Override
    public void setUser(User user) {
        if (user != null) {
            mCachedUser = user;
            mUserRemoteDataSource.setUser(user);
            mUserLocalDataSource.setUser(user);
        }
    }
}
