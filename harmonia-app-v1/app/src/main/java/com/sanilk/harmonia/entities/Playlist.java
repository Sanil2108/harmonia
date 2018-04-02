package com.sanilk.harmonia.entities;

import java.util.ArrayList;

/**
 * Created by sanil on 18/3/18.
 */

public class Playlist {
    private ArrayList<Song> songs;
    private User user;
    private String name;
    private ArrayList<String> tags;

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
