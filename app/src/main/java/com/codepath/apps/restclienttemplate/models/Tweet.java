package com.codepath.apps.restclienttemplate.models;

import android.text.format.DateUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Parcel
public class Tweet {
    // attributes of a tweet
    public String body;
    public String uid; // id for the tweet
    public String createdAt;
    public String date;
    public User user;
    public String imageUrl;
    public boolean liked;
    public int favCount;

    public Tweet() {}

    // deserealize the json
    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        // extraxt values from json
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getString("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        tweet.date = getRelativeTimeAgo(jsonObject.getString("created_at"));
        tweet.imageUrl = getImageUrl(jsonObject);
        tweet.liked = jsonObject.getBoolean("favorited");
        tweet.favCount = jsonObject.getInt("favorite_count");

        return tweet;
    }

    private static String getImageUrl(JSONObject obj) {
        String res;
        try {
            res = obj.getJSONObject("entities").getJSONArray("media").getJSONObject(0).getString("media_url_https");
        } catch (JSONException e) {
            res = "";
        }
        return res;
    }

    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
