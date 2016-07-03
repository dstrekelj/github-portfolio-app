package io.github.dstrekelj.githubportfolio.activities;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.dstrekelj.githubportfolio.R;
import io.github.dstrekelj.githubportfolio.data.sources.IUserDataSource;
import io.github.dstrekelj.githubportfolio.data.UserRepository;
import io.github.dstrekelj.githubportfolio.fragments.ContentFragment;
import io.github.dstrekelj.githubportfolio.fragments.MenuFragment;
import io.github.dstrekelj.githubportfolio.fragments.UserFragment;
import io.github.dstrekelj.githubportfolio.models.User;
import io.github.dstrekelj.githubportfolio.presenters.HomePresenter;
import io.github.dstrekelj.githubportfolio.presenters.IHomePresenter;

public class HomeActivity extends AppCompatActivity implements
        MenuFragment.OnFragmentInteractionListener, ContentFragment.OnFragmentInteractionListener {
    public static final String TAG = HomeActivity.class.getSimpleName();

    private HomePresenter mHomePresenter;
    private MenuFragment mMenuFragment;
    private UserFragment mUserFragment;

    @BindView(R.id.pane_layout)
    SlidingPaneLayout splPanes;

    @BindView(R.id.pane_master)
    FrameLayout flMaster;

    @BindView(R.id.pane_detail)
    FrameLayout flDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        UserRepository.getInstance(getApplicationContext()).getUser(new IUserDataSource.GetUserCallback() {
            @Override
            public void onSuccess(User user) {
                Log.d(TAG, user.getName());
            }

            @Override
            public void onFailure(String error) {
                Log.d(TAG, error);
            }
        });

        mMenuFragment = new MenuFragment();
        mUserFragment = new UserFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.pane_master, mMenuFragment);
        ft.add(R.id.pane_detail, mUserFragment);
        ft.commit();

        mHomePresenter = new HomePresenter(UserRepository.getInstance(getApplicationContext()), mUserFragment);

        splPanes.setSliderFadeColor(Color.TRANSPARENT);
        splPanes.setPanelSlideListener(new SlidingPaneLayout.SimplePanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                super.onPanelSlide(panel, slideOffset);
            }

            @Override
            public void onPanelOpened(View panel) {
                super.onPanelOpened(panel);
                switch (panel.getId()) {
                    case R.id.pane_detail:
                        getSupportFragmentManager().findFragmentById(R.id.pane_detail).setHasOptionsMenu(false);
                        getSupportFragmentManager().findFragmentById(R.id.pane_master).setHasOptionsMenu(true);
                    default:
                        break;
                }
            }

            @Override
            public void onPanelClosed(View panel) {
                super.onPanelClosed(panel);
                switch (panel.getId()) {
                    case R.id.pane_detail:
                        getSupportFragmentManager().findFragmentById(R.id.pane_detail).setHasOptionsMenu(true);
                        getSupportFragmentManager().findFragmentById(R.id.pane_master).setHasOptionsMenu(false);
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
