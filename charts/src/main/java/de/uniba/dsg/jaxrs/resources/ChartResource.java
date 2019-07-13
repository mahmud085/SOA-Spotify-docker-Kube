package de.uniba.dsg.jaxrs.resources;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;

import de.uniba.dsg.CustomSpotifyApi;
import de.uniba.dsg.interfaces.ChartApi;
import de.uniba.dsg.jaxrs.exceptions.ClientRequestException;
import de.uniba.dsg.jaxrs.exceptions.RemoteApiException;
import de.uniba.dsg.jaxrs.exceptions.ResourceNotFoundException;
import de.uniba.dsg.models.ErrorMessage;
import de.uniba.dsg.models.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value = "charts")
public class ChartResource implements ChartApi {

    private int CHART_LENGTH = 10;

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path(value = "{artistID}")
    @Override
    public List<Song> getTopSongs(@PathParam("artistID") String artistID) {
        // check if artistID is unavailable
        if (artistID == null) {
            throw new ClientRequestException(new ErrorMessage("Error: Missing artistID"));
        }

        GetArtistsTopTracksRequest artistTopTracksRequest = CustomSpotifyApi.getInstance().getArtistsTopTracks(artistID,CountryCode.DE).build();
        try {
            Track[] topTracksList = artistTopTracksRequest.execute();

            // check if any song found for the provided artistID
            if (topTracksList == null) {
                throw new ResourceNotFoundException(new ErrorMessage(String.format("Error: No song was found for the artistID: %s", artistID)));
            }

            List<Song> songs = new ArrayList<Song>();
            for(int i=0; i<CHART_LENGTH ; i++) {
                if(topTracksList[i] != null) {
                    Song song = new Song();

                    song.setId(topTracksList[i].getId());
                    song.setTitle(topTracksList[i].getName());
                    song.setArtist(topTracksList[i].getArtists()[0].getName());
                    song.setDuration(topTracksList[i].getDurationMs());

                    songs.add(song);
                }
            }
            return songs;
        }
        catch (SpotifyWebApiException | IOException e) {
            if(e instanceof SpotifyWebApiException)
                throw new ResourceNotFoundException(new ErrorMessage(String.format("Error: No artist found for query param: %s", artistID)));
            else
                throw new RemoteApiException(new ErrorMessage("Error: Cannot find remote server"));
        }
    }

}
