package de.uniba.dsg.interfaces;

import de.uniba.dsg.models.Picture;

public interface ImageApi {
    public Picture getUrlFromTrackId(String trackId);
}
