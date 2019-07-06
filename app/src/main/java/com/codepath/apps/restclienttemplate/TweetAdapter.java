package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    List<Tweet> mTweets;
    Context context;
    RestClient client;
    TweetAdapter thisOb;

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
        thisOb = this;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        // get data using position
        final Tweet tweet = mTweets.get(i);

        // populate views
        viewHolder.tvUsername.setText(tweet.user.name);
        viewHolder.tvBody.setText(tweet.body);
        viewHolder.tvDate.setText(tweet.date);
        viewHolder.tvHandle.setText(tweet.user.handle);

        Glide.with(context)
                .load(tweet.user.profileImageUrl)
                .into(viewHolder.ivProfileImage);



        viewHolder.btLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(v.getContext(), "test", Toast.LENGTH_LONG).show();
                client = RestApplication.getRestClient(context);
                if (tweet.liked) {
                    client.unLikeTweet(tweet.uid, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            viewHolder.btLike.setColorFilter(Color.argb(255, 130, 130, 130));
                            if ((Integer.parseInt(viewHolder.likeCount.getText().toString())-1) < 0) {
                                viewHolder.likeCount.setText(0 + "");
                            } else {
                                viewHolder.likeCount.setText(((Integer.parseInt(viewHolder.likeCount.getText().toString())-1)+""));
                            }
                            tweet.liked = false;
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("liking", "error unliking");
                            error.printStackTrace();
                        }
                    });
                } else {
                    client.likeTweet(tweet.uid, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            viewHolder.btLike.setColorFilter(Color.argb(255, 255, 0, 0));
                            viewHolder.likeCount.setText(((Integer.parseInt(viewHolder.likeCount.getText().toString())+1)+""));
                            tweet.liked = true;
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("liking", "error liking");
                            error.printStackTrace();
                        }
                    });
                }
            }
        });

        viewHolder.btRetweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(v.getContext(), "test", Toast.LENGTH_LONG).show();
                client = RestApplication.getRestClient(context);
                if (tweet.retweeted) {
                    client.unretweet(tweet.uid, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            viewHolder.btRetweet.setColorFilter(Color.argb(255, 130, 130, 130));
                            if ((Integer.parseInt(viewHolder.retweetCount.getText().toString())-1) < 0) {
                                viewHolder.retweetCount.setText(0 + "");
                            } else {
                                viewHolder.retweetCount.setText(((Integer.parseInt(viewHolder.retweetCount.getText().toString())-1)+""));
                            }
                            tweet.retweeted = false;
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("liking", "error unretweeting");
                            error.printStackTrace();
                        }
                    });
                } else {
                    client.retweet(tweet.uid, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            viewHolder.btRetweet.setColorFilter(Color.argb(255, 100, 100, 255));
                            viewHolder.retweetCount.setText(((Integer.parseInt(viewHolder.retweetCount.getText().toString())+1)+""));
                            tweet.retweeted = true;
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("liking", "error retweeting");
                            error.printStackTrace();
                        }
                    });
                }
            }
        });

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
            viewHolder.btLike.setColorFilter(Color.argb(255, 255, 0, 0));
        } else {
            viewHolder.btLike.setColorFilter(Color.argb(255, 130, 130, 130));
        }

        viewHolder.retweetCount.setText(tweet.retweetCount + "");
        if (!tweet.retweeted) {
            viewHolder.btRetweet.setColorFilter(Color.argb(255, 130, 130, 130));
        } else {
            viewHolder.btRetweet.setColorFilter(Color.argb(255, 100, 100, 255));
        }

    }

    @Override
    public int getItemCount() {
        return  mTweets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        @BindView(R.id.ivProfileImage) public ImageView ivProfileImage;
        @BindView(R.id.tvUserName) public TextView tvUsername;
        @BindView(R.id.tvBody) public  TextView tvBody;
        @BindView(R.id.tvDate) public  TextView tvDate;
        @BindView(R.id.ivTweetImg) public ImageView ivTweetImg;
        @BindView(R.id.btLike) public ImageButton btLike;
        @BindView(R.id.likeCount) public TextView likeCount;
        @BindView(R.id.btRetweet) public ImageButton btRetweet;
        @BindView(R.id.retweetCount) public TextView retweetCount;
        @BindView(R.id.tvHandle) public TextView tvHandle;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            final int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Tweet tempTweet = mTweets.get(position);
                client = RestApplication.getRestClient(context);
                client.deleteTweet(tempTweet.uid, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        thisOb.notifyItemRemoved(position);
                        Toast.makeText(context, "Tweet deleted.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(context, "You must be the author of this tweet to delete it.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return true;
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }
}
