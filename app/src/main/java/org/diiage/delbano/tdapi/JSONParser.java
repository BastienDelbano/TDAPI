package org.diiage.delbano.tdapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Bastien on 03/05/2018.
 */

public class JSONParser {
    public Track jsonToTrackByArtist(JSONObject json) throws JSONException{


        String id = json.getString("id");
        String name = json.getString("name");
        String url = json.getJSONObject("external_urls").getString("spotify");

        Track track = new Track(id, name, url);

        return track;
    }
}
