package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {
    // attributes of an user
    public String name;
    public  long uid;
    public String screenName;
    public String profileImageUrl;
    public  String handle;

    public User() {}

    // deserealize json
    public  static User fromJSON(JSONObject jsonObject) throws JSONException {
        User user = new User();

        // extract values from json
        user.name = jsonObject.getString("name");
        user.uid = jsonObject.getLong("id");
        user.screenName = jsonObject.getString("screen_name");
        user.profileImageUrl = jsonObject.getString("profile_image_url");
        user.handle = "@" + jsonObject.getString("screen_name");

        return user;
    }
}
