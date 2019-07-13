package de.uniba.dsg.interfaces;

import de.uniba.dsg.models.Song;

import java.util.List;

public interface ChartApi {
    List<Song> getTopSongs(String artistID);
}
