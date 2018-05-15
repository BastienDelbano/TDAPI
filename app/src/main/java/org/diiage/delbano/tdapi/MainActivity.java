package org.diiage.delbano.tdapi;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String baseUrlApi = getResources().getString(R.string.spotify_marilyn_tops_tracks_url_api);
        URL baseUrl = null;
        try {
            baseUrl = new URL(baseUrlApi);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        @SuppressLint("StaticFieldLeak") AsyncTask<URL,Integer,ArrayList<Track>> task = new AsyncTask<URL, Integer, ArrayList<Track>>() {
            @Override
            protected ArrayList<Track> doInBackground(URL... urls) {

                try {
                    HttpURLConnection connection = (HttpURLConnection)urls[0].openConnection();
                    connection.setRequestProperty("Authorization", "Bearer BQC2GvBfXv4iwBguKUS459hDyrHOktfq7cVyVE4DVVrciFWZbooq30WC7m__YJLjT2z0Om8F0ZcohPsDhypcEhgIHXeSqj-0kDbaNEB89pWEXyIuL4CCWoM2j1avNeNMKa-ZNNx6zBwruao6");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Accept","application/json");
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    //Initialisation d'un StringBuilder pour stocker le contenu distant
                    StringBuilder stringBuilder = new StringBuilder();
                    String lineBuffer = null;
                    while ((lineBuffer = bufferedReader.readLine()) != null){
                        stringBuilder.append(lineBuffer);
                    }
                    String data = stringBuilder.toString();
                    JSONObject jsonArray = new JSONObject(data);
                    JSONArray ts = jsonArray.getJSONArray("tracks");

                    ArrayList<Track> tracks = new ArrayList<>();
                    for (int i = 0; i < ts.length(); i++){
                        JSONObject jsonObject = ts.getJSONObject(i);
                        JSONParser jsonParser = new JSONParser();
                        tracks.add(jsonParser.jsonToTrackByArtist(jsonObject));
                    }

                    int test = 0;
                    test++;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e("EXCEPTION", e.getLocalizedMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("EXCEPTION", e.getLocalizedMessage());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("EXCEPTION", e.getLocalizedMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(ArrayList<Track> tracks){
                super.onPostExecute(tracks);
            }
        }.execute(baseUrl);
    }
}
