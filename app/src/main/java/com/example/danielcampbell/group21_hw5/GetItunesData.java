package com.example.danielcampbell.group21_hw5;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by danielcampbell on 10/27/17.
 */

public class GetItunesData extends AsyncTask<String, Void, ArrayList<Podcast>> {

    private ProgressDialog pdia;
    private Context context;

    GetItunesData(Context context){
        this.context = context;
    }

    @Override
    protected ArrayList<Podcast> doInBackground(String... params) {

        //worker thread (Parse XML, Create Podcasts, Add to Linked List & Return
        HttpURLConnection connection;
        ArrayList<Podcast> podcastLinkedList = null;

        try{

            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){

                //System.out.println("entered GetiTunesData Class");
                //System.out.println(params[0].toString());

                podcastLinkedList = PodcastParser.PodcastPullParser.parsePodcasts(connection.getInputStream());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return podcastLinkedList;

    }
    //PRE / POST / PROGRESS

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //progress dialog pre
        pdia = new ProgressDialog(context);
        pdia.setMessage("Loading...");
        pdia.show();


    }

    @Override
    protected void onPostExecute(ArrayList<Podcast> podcasts) {
        super.onPostExecute(podcasts);

        if(pdia != null){
            pdia.dismiss();
        }
        //progress dialog post



    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }
}
