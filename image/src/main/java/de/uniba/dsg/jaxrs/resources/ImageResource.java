package de.uniba.dsg.jaxrs.resources;

import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.Image;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import de.uniba.dsg.CustomSpotifyApi;
import de.uniba.dsg.jaxrs.exceptions.BadGateWayException;
import de.uniba.dsg.jaxrs.exceptions.ClientRequestException;
import de.uniba.dsg.jaxrs.exceptions.NoContentException;
import de.uniba.dsg.jaxrs.exceptions.RemoteApiException;
import de.uniba.dsg.models.ErrorMessage;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import de.uniba.dsg.models.Picture;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import de.uniba.dsg.interfaces.ImageApi;
@Path(value = "api/covers")
public class ImageResource implements ImageApi {
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path(value = "{track-id}")
    @Override
    public Picture getUrlFromTrackId(@PathParam(value = "track-id") String trackId) {
        try {

            if(trackId == null || trackId.isEmpty()){
                throw new ClientRequestException(new ErrorMessage("Client error. Trackid is missing"));
            }
            GetTrackRequest trackRequest = CustomSpotifyApi.getInstance().getTrack(trackId).build();
            Track track = trackRequest.execute();

            if (track == null || track.getAlbum() == null || track.getAlbum().getImages() == null) {
                throw new BadGateWayException(new ErrorMessage("Spotify error"));
            }

           Image[] images =  track.getAlbum().getImages(); //track.getAlbum().getImages();

            if (images.length <= 0) {
                throw new NoContentException(new ErrorMessage("No Imageart available"));
            }

            return new Picture(images[0].getUrl());

        }  catch (IOException | SpotifyWebApiException ex) {
            throw new RemoteApiException(new ErrorMessage(ex.getMessage()));
        }
    }

}
