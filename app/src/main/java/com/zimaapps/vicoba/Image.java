package com.zimaapps.vicoba;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Image {
    public String key;
    public String userId;
    public String downloadUrl;
    public String namex;
    public String time;
    public String ujumbe;

    // these properties will not be saved to the database
    @Exclude
    public User user;

    @Exclude
    public int likes = 0;

    @Exclude
    public boolean hasLiked = false;

    @Exclude
    public String userLike;

    public Image() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Image(String key, String userId, String downloadUrl, String namex, String time, String ujumbe) {
        this.key = key;
        this.userId = userId;
        this.downloadUrl = downloadUrl;
        this.namex = namex;
        this.time = time;
        this.ujumbe = ujumbe;
    }

    public void addLike() {
        this.likes++;
    }

    public void removeLike() {
        this.likes--;
    }
}
