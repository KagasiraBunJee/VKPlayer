package com.historic.vkplayer.entity;

/**
 * Created by Historic on 21.07.2015.
 */
public class VKAudio
{
    private int id;
    private String artist;
    private String title;

    public VKAudio(String artist, String title)
    {
        this.artist = artist;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
