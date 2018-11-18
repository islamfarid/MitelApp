package com.example.islam.mitelapp.wether;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.islam.mitelapp.R;
import com.example.islam.mitelapp.common.ItemTouchHelperAdapter;
import com.example.islam.mitelapp.data.models.CurrentWeatherModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectedLocationsListAdapter extends RecyclerView.Adapter<SelectedLocationsListAdapter.VideosViewHolder> implements ItemTouchHelperAdapter {
    private Context mContext;
    private ArrayList<CurrentWeatherModel> mCurrentWeatherModels;
    private OnSelectedListChanged mOnSelectedListChanged;

    SelectedLocationsListAdapter(Context context, ArrayList<CurrentWeatherModel> currentWeatherModels , OnSelectedListChanged onSelectedListChanged) {
        this.mContext = context;
        this.mCurrentWeatherModels = currentWeatherModels;
        this.mOnSelectedListChanged = onSelectedListChanged;
    }

    @NonNull
    @Override
    public VideosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_selected_location_item, parent, false);
        return new VideosViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VideosViewHolder holder, int position) {
        holder.locationNameText.setText(mCurrentWeatherModels.get(position).getLocation().getName());
        holder.locationCondition.setText(mCurrentWeatherModels.get(position).getCurrent().getCondition().getText());
        holder.locationTemperature.setText(mContext.getString(R.string.temp, String.valueOf(mCurrentWeatherModels.get(position).getCurrent().getTempC())));
        Glide.with(mContext)
                .load(mContext.getString(R.string.weather_condition_urlmCurrentWeatherModels,
                        mCurrentWeatherModels.get(position).getCurrent().getCondition().getIcon()))
                .into(holder.weatherConditionImage);
    }

    @Override
    public int getItemCount() {
        return mCurrentWeatherModels.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mCurrentWeatherModels, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mCurrentWeatherModels, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        mOnSelectedListChanged.onItemsChanged(mCurrentWeatherModels);
    }

    @Override
    public void onItemDismiss(int position) {
        mCurrentWeatherModels.remove(position);
        notifyItemRemoved(position);
        mOnSelectedListChanged.onItemsChanged(mCurrentWeatherModels);
    }

    public class VideosViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_weather_condition)
        ImageView weatherConditionImage;
        @BindView(R.id.tv_location_name)
        AppCompatTextView locationNameText;
        @BindView(R.id.tv_location_condition)
        AppCompatTextView locationCondition;
        @BindView(R.id.tv_location_temperature)
        AppCompatTextView locationTemperature;

        public VideosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public void updateWeatherListItems(List<CurrentWeatherModel> currentWeatherModels) {
//        final WeatherDiffCallBack diffCallback = new WeatherDiffCallBack(this.mCurrentWeatherModels, currentWeatherModels);
//        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.mCurrentWeatherModels = new ArrayList<>();
        this.mCurrentWeatherModels.addAll(currentWeatherModels);
        notifyDataSetChanged();
//        diffResult.dispatchUpdatesTo(this);
    }

}

