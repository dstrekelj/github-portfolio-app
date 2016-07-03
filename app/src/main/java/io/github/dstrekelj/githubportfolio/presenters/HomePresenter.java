package io.github.dstrekelj.githubportfolio.presenters;

import android.util.Log;

import io.github.dstrekelj.githubportfolio.data.UserRepository;
import io.github.dstrekelj.githubportfolio.data.sources.IUserDataSource;
import io.github.dstrekelj.githubportfolio.models.User;
import io.github.dstrekelj.githubportfolio.views.IUserView;

/**
 * Created by Domagoj on 3.7.2016..
 */
public class HomePresenter implements IHomePresenter {
    public static final String TAG = HomePresenter.class.getSimpleName();

    private final UserRepository mUserRepository;
    private final IUserView mUserView;

    public HomePresenter(UserRepository userRepository, IUserView userView) {
        mUserRepository = userRepository;
        mUserView = userView;
        mUserView.setPresenter(this);

        userRepository.getUser(new IUserDataSource.GetUserCallback() {
            @Override
            public void onSuccess(User user) {
                Log.d(TAG, user.getEmail());
                mUserView.showUser(user);
            }

            @Override
            public void onFailure(String error) {
                Log.e(TAG, error);
            }
        });
    }
}
