package com.akeditzz.musicplayer.musiclist.model;

import java.io.Serializable;

public class MusicModel implements Serializable {

    private String songName;
    private String artistName;
    private int thumbNail;
    private String elapsedTime;
    private String endTime;

    public MusicModel(String songName, String artistName, int thumbNail, String elapsedTime, String endTime) {
        this.songName = songName;
        this.artistName = artistName;
        this.thumbNail = thumbNail;
        this.elapsedTime = elapsedTime;
        this.endTime = endTime;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(int thumbNail) {
        this.thumbNail = thumbNail;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
