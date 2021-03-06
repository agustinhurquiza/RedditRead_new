package ar.edu.unc.famaf.redditreader.backend;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ar.edu.unc.famaf.redditreader.model.Listing;

public class GetTopPostsTask extends AsyncTask<URL, Integer, Listing>{
    private  String mtype;

    public void setType(String type) {
        mtype = type;
    }
    @Override
    protected Listing doInBackground(URL... params) {
        InputStream jsonStream = null;
        URL url = params[0];
        Listing list = null;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            jsonStream = connection.getInputStream();
        } catch (IOException e) {
            return null;
        }
        if (jsonStream != null)
            try {
                list = new Parser().readJsonStream(jsonStream, mtype);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return list;
    }
}
