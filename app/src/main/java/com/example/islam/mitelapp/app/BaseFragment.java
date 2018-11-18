package com.example.islam.mitelapp.app;


/**
 * Created by islam on 23/05/17.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ProgressBar;

import com.example.islam.mitelapp.R;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

/**
 * when the project has a lot of business requirements we need to put the common fragments code
 */
public abstract class BaseFragment extends Fragment {
    private final int NO_LAYOUT_SPECIFIED = 0;

    protected BasePresenter mPresenter;
    protected View rootView;
    ProgressBar mProgressBar;
    ViewStub contentViewStub;
    AppCompatTextView mTVGeneralError;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_base, container, false);
        mProgressBar = rootView.findViewById(R.id.pb_loading);
        contentViewStub = rootView.findViewById(R.id.vs_content);
        mTVGeneralError = rootView.findViewById(R.id.tv_error_general);
        contentViewStub.setLayoutResource(getLayoutId());
        contentViewStub.inflate();
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    public void setPresenter(BasePresenter presenter) {
        mPresenter = presenter;
    }

    public void showLoading() {
//        contentViewStub.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
//        contentViewStub.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    public void showError(String errorMsg) {
        contentViewStub.setVisibility(View.GONE);
        mTVGeneralError.setVisibility(View.VISIBLE);
        mTVGeneralError.setText(errorMsg);
    }

    public void showGeneralError() {
        contentViewStub.setVisibility(View.GONE);
        mTVGeneralError.setVisibility(View.VISIBLE);
        mTVGeneralError.setText(getString(R.string.error_general));
    }

    protected abstract void init();

    /**
     * @return layout resource layout
     */
    @LayoutRes
    public abstract int getLayoutId();

    @Override
    public void onDestroyView() {

        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }

        super.onDestroyView();
    }
}
