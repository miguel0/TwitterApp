package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private List<Tweet> mTweets;
    Context context;

    public  TweetAdapter(List<Tweet> tweets) {
        mTweets = tweets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tweetView = inflater.inflate(R.layout.item_tweet, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // get data using position
        Tweet tweet = mTweets.get(i);

        // populate views
        viewHolder.tvUsername.setText(tweet.user.name);
        viewHolder.tvBody.setText(tweet.body);
        viewHolder.tvDate.setText(tweet.date);

        Glide.with(context)
                .load(tweet.user.profileImageUrl)
                .into(viewHolder.ivProfileImage);

        if (tweet.imageUrl.length() > 0) {
            Glide.with(context)
                    .load(tweet.imageUrl)
                    .bitmapTransform(new RoundedCornersTransformation(context, 15, 1))
                    .into(viewHolder.ivTweetImg);
        } else {
            Glide.with(context).load("").into(viewHolder.ivTweetImg);
        }

        viewHolder.likeCount.setText(tweet.favCount + "");
        if (tweet.liked) {
            viewHolder.btLike.setColorFilter(Color.argb(195, 234, 36, 100));
        } else {
            viewHolder.btLike.setColorFilter(Color.argb(255, 224, 36, 94));
        }
    }

    @Override
    public int getItemCount() {
        return  mTweets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivProfileImage) public ImageView ivProfileImage;
        @BindView(R.id.tvUserName) public TextView tvUsername;
        @BindView(R.id.tvBody) public  TextView tvBody;
        @BindView(R.id.tvDate) public  TextView tvDate;
        @BindView(R.id.ivTweetImg) public ImageView ivTweetImg;
        @BindView(R.id.btLike) public ImageButton btLike;
        @BindView(R.id.likeCount) public TextView likeCount;

        public ViewHolder(View itemView) {
            super((itemView));

            ButterKnife.bind(this, itemView);
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }
}
