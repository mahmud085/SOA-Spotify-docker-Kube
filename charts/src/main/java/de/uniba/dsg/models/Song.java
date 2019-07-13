package de.uniba.dsg.models;

public class Song {
    private String id;
    private String title;
    private String artist;
    private double duration;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public double getDuration() {
        return duration;
    }
    public void setDuration(double duration) {
        this.duration = duration;
    }
}
