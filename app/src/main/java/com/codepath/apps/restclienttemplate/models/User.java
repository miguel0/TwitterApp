package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    // attributes of an user
    public String name;
    public  long uid;
    public String screenName;
    public String profileImageUrl;

    // deserealize json
    public  static User fromJSON(JSONObject jsonObject) throws JSONException {
        User user = new User();

        // extract values from json
        user.name = jsonObject.getString("name");
        user.uid = jsonObject.getLong("id");
        user.screenName = jsonObject.getString("screen_name");
        user.profileImageUrl = jsonObject.getString("profile_image_url");

        return user;
    }
}
