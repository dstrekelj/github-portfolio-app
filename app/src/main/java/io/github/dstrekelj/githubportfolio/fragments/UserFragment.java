package io.github.dstrekelj.githubportfolio.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.dstrekelj.githubportfolio.R;
import io.github.dstrekelj.githubportfolio.models.User;
import io.github.dstrekelj.githubportfolio.presenters.HomePresenter;
import io.github.dstrekelj.githubportfolio.presenters.IHomePresenter;
import io.github.dstrekelj.githubportfolio.views.IUserView;

/**
 * Created by Domagoj on 3.7.2016..
 */
public class UserFragment extends Fragment implements IUserView {
    public static final String TAG = UserFragment.class.getSimpleName();

    private IHomePresenter mHomePresenter;

    @BindView(R.id.image_avatar)
    ImageView ivAvatar;

    @BindView(R.id.text_email)
    TextView tvEmail;

    @BindView(R.id.text_login)
    TextView tvLogin;

    @BindView(R.id.text_name)
    TextView tvName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showUser(User user) {
        Log.d(TAG, "showUser");
        ImageLoader.getInstance().displayImage(user.getAvatarUrl(), ivAvatar);
        tvEmail.setText(user.getEmail());
        tvName.setText(user.getName());
        tvLogin.setText(user.getLogin());
    }

    @Override
    public void setPresenter(IHomePresenter homePresenter) {
        mHomePresenter = homePresenter;
    }
}
