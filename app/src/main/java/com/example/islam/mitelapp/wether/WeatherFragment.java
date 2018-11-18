package com.example.islam.mitelapp.wether;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.islam.mitelapp.R;
import com.example.islam.mitelapp.app.BaseFragment;
import com.example.islam.mitelapp.common.OnListItemSelected;
import com.example.islam.mitelapp.common.SimpleItemTouchHelperCallback;
import com.example.islam.mitelapp.data.models.CurrentWeatherModel;
import com.example.islam.mitelapp.data.models.LocationModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class WeatherFragment extends BaseFragment implements WeatherContract.View {
    @BindView(R.id.sr_selected_locations)
    SwipeRefreshLayout mSelectedLocationSwipeRefresh;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_searched_locations)
    RecyclerView mSearchedLocationsList;
    @BindView(R.id.rv_selected_locations)
    RecyclerView mSelectedLocationsList;
    @BindView(R.id.rl_no_location_selected)
    RelativeLayout mNoLocationSelectedLayout;
    private SearchedLocationsListAdapter mSearchedLocationsListAdapter;
    private SelectedLocationsListAdapter mSelectedLocationsListAdapter;
    private String mSearchKeyword;
    private MenuItem mSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View superOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        return superOnCreateView;
    }

    @Override
    protected void init() {
        setRetainInstance(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((WeatherContract.Presenter) mPresenter).getInitScenario();
        mSelectedLocationSwipeRefresh.setOnRefreshListener(() -> {
            ((WeatherContract.Presenter) mPresenter).onSwipeToRefresh();
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_weather;
    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void displaySearchLocationsList(ArrayList<LocationModel> locationsListItems) {
        if (mSearchedLocationsListAdapter == null) {
            mSearchedLocationsListAdapter = new SearchedLocationsListAdapter(getActivity(), new OnLocationSelected(), locationsListItems);
            mSearchedLocationsList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            mSearchedLocationsList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mSearchedLocationsList.setAdapter(mSearchedLocationsListAdapter);

        } else {
            mSearchedLocationsListAdapter.updateLocationsListItems(locationsListItems);
        }
    }

    private class OnLocationSelected implements OnListItemSelected<LocationModel> {
        @Override
        public void onVideoSelected(LocationModel item) {
            ((WeatherContract.Presenter) mPresenter).onLocationSeleted(item, mSearchKeyword);
        }
    }

    @Override
    public void showSearchScenario() {
        mSearchedLocationsList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSearchScenario() {
        if (mSearch != null)
            mSearch.collapseActionView();
        if (mSearchedLocationsListAdapter != null) {
            mSearchedLocationsListAdapter.updateLocationsListItems(new ArrayList<>());
        }
        mSearchedLocationsList.setVisibility(View.GONE);
    }

    @Override
    public void selectedLOcationsScenario(ArrayList<CurrentWeatherModel> currentWeatherModels) {
        if (mSelectedLocationsListAdapter == null) {
            mSelectedLocationsListAdapter = new SelectedLocationsListAdapter(getActivity(), currentWeatherModels, new OnSelectedListChanged() {
                @Override
                public void onItemsChanged(ArrayList<CurrentWeatherModel> currentWeatherModels) {
                    ((WeatherContract.Presenter) mPresenter).onItemsChanged(currentWeatherModels);
                }
            });
            mSelectedLocationsList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            ItemTouchHelper.Callback callback =
                    new SimpleItemTouchHelperCallback(mSelectedLocationsListAdapter);
            ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(mSelectedLocationsList);
            mSelectedLocationsList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mSelectedLocationsList.setAdapter(mSelectedLocationsListAdapter);

        } else {
            mSelectedLocationsListAdapter.updateWeatherListItems(currentWeatherModels);
        }
        mSelectedLocationSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void showSelectedLocationsScenarion() {
        mSelectedLocationsList.setVisibility(View.VISIBLE);
        mSelectedLocationSwipeRefresh.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSelectedLocationsScenarion() {
        mSelectedLocationsList.setVisibility(View.GONE);
        mSelectedLocationSwipeRefresh.setVisibility(View.GONE);
    }

    @Override
    public void showNoLcationsSelectedScenario() {
        mNoLocationSelectedLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoLocationsSelectedScenario() {
        mNoLocationSelectedLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideSearchList() {
        mSearchedLocationsList.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar, menu);
        mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint(getString(R.string.search));
        mSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                ((WeatherContract.Presenter) mPresenter).onMenuExpanded();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                ((WeatherContract.Presenter) mPresenter).onMenuCollapsed();

                return true;
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mSearchKeyword = newText;
                ((WeatherContract.Presenter) mPresenter).getLocationsListForKeyweord(newText);
                return true;
            }
        });

    }

}
