package com.tune.tuneme.data;

import android.net.Uri;

/**
 * Basic followers information
 */
@SuppressWarnings("unused")
public class Followers extends DataModel{
    private String userName;
    private String profileName;
    private Uri profilePicture;

    public Followers(String userName, String profileName, Uri profilePicture) {
        this.userName = userName;
        this.profileName = profileName;
        this.profilePicture = profilePicture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Uri getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Uri profilePicture) {
        this.profilePicture = profilePicture;
    }
}
