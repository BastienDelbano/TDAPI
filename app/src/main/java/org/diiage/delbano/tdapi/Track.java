package org.diiage.delbano.tdapi;

/**
 * Created by Bastien on 03/05/2018.
 */

public class Track {
    private String id;
    private String name;
    private String urlSpotify;

    public Track(String id, String name, String urlSpotify) {
        this.id = id;
        this.name = name;
        this.urlSpotify = urlSpotify;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrlSpotify() {
        return urlSpotify;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrlSpotify(String urlSpotify) {
        this.urlSpotify = urlSpotify;
    }

}
