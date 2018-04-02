package com.sanilk.harmonia.entities;

import android.graphics.drawable.Drawable;

/**
 * Created by sanil on 16/2/18.
 */

public class Song {
    private String songName;
    private String songLink;
    private int upVotes;
    private int downVotes;
    private Drawable songThumb;
    
    public Song(String songName, String songLink, int upVotes, int downVotes, Drawable songThumb) {
        this.songName = songName;
        this.songLink = songLink;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.songThumb = songThumb;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public Drawable getSongThumb() {
        return songThumb;
    }

    public void setSongThumb(Drawable songThumb) {
        this.songThumb = songThumb;
    }
}
