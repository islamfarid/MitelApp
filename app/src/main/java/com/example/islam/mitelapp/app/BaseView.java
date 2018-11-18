package com.example.islam.mitelapp.app;


/**
 * Created by Islam Farid on 02/12/16.
 */
public interface BaseView<T> {
    void setPresenter(T presenter);

    void showLoading();

    void hideLoading();
    void showError(String errorMsg);
    void showGeneralError();

}
