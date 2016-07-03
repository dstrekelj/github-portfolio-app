package io.github.dstrekelj.githubportfolio.views;

import io.github.dstrekelj.githubportfolio.models.User;
import io.github.dstrekelj.githubportfolio.presenters.HomePresenter;
import io.github.dstrekelj.githubportfolio.presenters.IHomePresenter;

/**
 * Created by Domagoj on 3.7.2016..
 */
public interface IUserView {
    void showUser(User user);
    void setPresenter(IHomePresenter homePresenter);
}
