package de.uniba.dsg.models;

import com.wrapper.spotify.model_objects.specification.Artist;
import java.util.List;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id", "name", "genres", "popularity"})
public class Interpret {
    private String id;
    private String name;
    private List<String> genres;
    private int popularity;

    public Interpret(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
        this.genres = Arrays.asList(artist.getGenres());
        this.popularity = artist.getPopularity();
    }

    public Interpret() {

    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
