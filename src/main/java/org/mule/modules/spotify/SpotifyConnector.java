package org.mule.modules.spotify;

import java.io.IOException;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.modules.spotify.config.ConnectorConfig;

import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleAlbum;
import com.wrapper.spotify.models.Track;

@Connector(name="spotify", friendlyName="Spotify Connector")
public class SpotifyConnector {

    @Config
    ConnectorConfig config;

    /**
     * {@sample.xml ../../../doc/spotify-connector.xml.sample spotify:search-artist}
     *
     * @param query the name of the artist
     * @return A greeting message
     * @throws WebApiException 
     * @throws IOException 
     */
    @Processor
    public Page<Artist> searchArtist(String query) throws IOException, WebApiException {
    	return this.config.getApi().searchArtists(query).build().get();
    }

    /**
     * {@sample.xml ../../../doc/spotify-connector.xml.sample spotify:search-album}
     *
     * @param query the name of the album
     * @return A greeting message
     * @throws WebApiException 
     * @throws IOException 
     */
    @Processor
    public Page<SimpleAlbum> searchAlbum(String query) throws IOException, WebApiException {
    	return this.config.getApi().searchAlbums(query).build().get();
    }

    /**
     * {@sample.xml ../../../doc/spotify-connector.xml.sample spotify:search-trakc}
     *
     * @param query the name of the track
     * @return a page with the tracks
     * @throws WebApiException 
     * @throws IOException 
     */
    @Processor
    public Page<Track> searchSong(String query) throws IOException, WebApiException {
    	return this.config.getApi().searchTracks(query).build().get();
    }
    
    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}