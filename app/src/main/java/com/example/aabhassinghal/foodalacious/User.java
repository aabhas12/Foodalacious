package com.example.aabhassinghal.foodalacious;

import com.google.gson.annotations.SerializedName;

import java.net.URL;

/**
 * Created by aabhassinghal on 1/15/18.
 */

public class User {
    @SerializedName("firstname")
    String firstname;
    @SerializedName("email")
    String email;
    @SerializedName("username")
    String username;
    @SerializedName("social_media_id")
    String social_media_id;
    @SerializedName("avatar")
    URL avatar;

    public String getFirstname() {
        return firstname;
    }

    public User(String firstname, String email, String username, String social_media_id, URL avatar) {
        this.firstname = firstname;
        this.email = email;
        this.username = username;
        this.social_media_id = social_media_id;
        this.avatar = avatar;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSocial_media_id() {
        return social_media_id;
    }

    public void setSocial_media_id(String social_media_id) {
        this.social_media_id = social_media_id;
    }

    public URL getAvatar() {
        return avatar;
    }

    public void setAvatar(URL avatar) {
        this.avatar = avatar;
    }
}
