package de.uniba.dsg.jaxrs.resources;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;


import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;
import de.uniba.dsg.interfaces.ArtistsApi;
import de.uniba.dsg.jaxrs.exceptions.*;
import de.uniba.dsg.models.ArtistSearchResult;
import de.uniba.dsg.models.ErrorMessage;
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

@Path("api/artists")
public class ArtistsResource implements ArtistsApi {

    private static final Logger LOG = Logger.getLogger(ArtistsResource.class.getName());

    @Override
    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchArtists(@QueryParam(value = "artist") String artist) {

        try {
            if (artist == null) {
                throw new ClientRequestException(new ErrorMessage("Client error. artist not defined"));
            }

            SearchArtistsRequest artistRequest = CustomSpotifyApi.getInstance().searchArtists(artist).limit(1).build();

            Paging<Artist> artistSearchResult = artistRequest.execute();
            Artist[] artists = artistSearchResult.getItems();

            if (artists == null) {
                throw new BadGateWayException(new ErrorMessage("Spotify error"));
            }
            
            if (artists.length == 0) {
                throw new NoContentException(new ErrorMessage("No artists found"));
            }

            ArtistSearchResult result = new ArtistSearchResult(artists[0]);

            return Response.status(200).entity(result).build();

        }  catch (IOException | SpotifyWebApiException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new RemoteApiException(new ErrorMessage(ex.getMessage()));
        }
    }
}
