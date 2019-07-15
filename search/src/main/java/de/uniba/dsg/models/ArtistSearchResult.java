
package de.uniba.dsg.models;

import com.wrapper.spotify.model_objects.specification.Artist;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ArtistSearchResult {

    @XmlAttribute(name = "id")
    private String artistId;
    @XmlAttribute(name = "name")
    private String artistName;

    public ArtistSearchResult() {
    }

    public ArtistSearchResult(String artistId, String artistName) {
        this.artistId = artistId;
        this.artistName = artistName;
    }

    public ArtistSearchResult(Artist artist) {
        this.artistId = artist.getId();
        this.artistName = artist.getName();
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    @Override
    public String toString() {
        return "ArtistSearchResult{" + "artistId=" + artistId + ", artistName=" + artistName + '}';
    }
    
}
