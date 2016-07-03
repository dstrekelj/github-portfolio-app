package io.github.dstrekelj.githubportfolio.data.sources;

import android.content.Context;

import java.io.File;

import io.github.dstrekelj.githubportfolio.models.User;

/**
 * Created by Domagoj on 3.7.2016..
 */
public class UserLocalDataSource implements IUserDataSource {
    public static final String TAG = UserLocalDataSource.class.getSimpleName();

    private static UserLocalDataSource sInstance = null;

    private Context mContext;
    private File cacheDir;

    private UserLocalDataSource(Context context) {
        mContext = context;
        cacheDir = context.getCacheDir();
    }

    public static UserLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UserLocalDataSource(context);
        }
        return sInstance;
    }

    @Override
    public void getUser(GetUserCallback callback) {

    }

    @Override
    public void setUser(User user) {

    }
}
