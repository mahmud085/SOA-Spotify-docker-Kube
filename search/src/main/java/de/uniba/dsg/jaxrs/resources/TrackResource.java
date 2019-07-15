package de.uniba.dsg.jaxrs.resources;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.Paging;

import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import de.uniba.dsg.interfaces.TracksApi;
import de.uniba.dsg.jaxrs.exceptions.*;
import de.uniba.dsg.models.ErrorMessage;
import de.uniba.dsg.models.TrackSearchResult;
import de.uniba.dsg.CustomSpotifyApi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api/tracks")
public class TrackResource implements TracksApi {

    private static final Logger LOG = Logger.getLogger(TrackResource.class.getName());

    @Override
    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchTrack(@QueryParam(value = "title") String title,
            @QueryParam(value = "artist") String artist) {

        try {
            if (title == null && artist == null) {
                throw new ClientRequestException(new ErrorMessage("Client error. title or artist not defined"));
            }
            SearchTracksRequest searchTracksRequest = CustomSpotifyApi.getInstance().searchTracks(String.join("+", title, artist))
                    .build();
            Paging<Track>  trackPaging = searchTracksRequest.execute();
            Track[] tracks = trackPaging.getItems();

            if (tracks == null) {
                throw new BadGateWayException(new ErrorMessage("Spotify error"));
            }

            if (tracks.length == 0) {
                throw new NoContentException(new ErrorMessage("No tracks found"));
            }

            TrackSearchResult result = new TrackSearchResult(tracks[0]);

            return Response.status(200).entity(result).build();

        }  catch (IOException | SpotifyWebApiException ex) {
            throw new RemoteApiException(new ErrorMessage(ex.getMessage()));
        }
    }
}
