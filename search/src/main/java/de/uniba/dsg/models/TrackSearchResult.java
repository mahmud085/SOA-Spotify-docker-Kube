package de.uniba.dsg.models;

import com.wrapper.spotify.model_objects.specification.Track;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TrackSearchResult {
    
    @XmlAttribute(name = "id")
    private String trackId;
    
    @XmlAttribute(name = "artistName")
    private String artistName;
    @XmlAttribute(name = "songTitle")
    private String songTitle;

    public TrackSearchResult() {
    }

    public TrackSearchResult(String trackId, String artistName, String songTitle) {
        this.trackId = trackId;
        this.artistName = artistName;
        this.songTitle = songTitle;
    }

    public TrackSearchResult(Track track) {
        this.trackId = track.getId();
        if(track.getArtists().length == 0){
            this.artistName = "Unkown";
        } else {
            this.artistName = track.getArtists()[0].getName();
        }
        this.songTitle = track.getName();
        
    }

    public String getTrackId() {
        return trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getSongTitle() {
        return songTitle;
    }

    @Override
    public String toString() {
        return "TrackSearchResult{" + "trackId=" + trackId + ", artistName=" + artistName + ", songTitle=" + songTitle + '}';
    }
    
}
