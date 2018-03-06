package com.example.danielcampbell.group21_hw5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class DetailedViewActivity extends AppCompatActivity {

    TextView podcastTitle;
    TextView podcastDate;
    TextView podcastSummary;
    ImageView podcastImage;

    SimpleDateFormat dateFormat;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        Intent i = getIntent();
        final Podcast podcast = (Podcast)getIntent().getExtras().getSerializable("SELECTED_PODCAST");

        setTitle(podcast.pc_title);

        //Podcast Title
        podcastTitle = (TextView)findViewById(R.id.podcastTitleText);
        podcastTitle.setText(podcast.pc_title);

        //Date
        podcastDate = (TextView)findViewById(R.id.podcastUpdateDate);
        String s = podcast.pc_updateDate;
        dateFormat = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss.SSSXXX");

        System.out.println("Date" + s);

        podcastDate.setText(s);


        podcastImage = (ImageView)findViewById(R.id.podcast_imageView);
        String urlToImage = podcast.pc_img;
        System.out.println(urlToImage);
        //new DownloadImage().execute(urlToImage);
        Picasso
                .with(DetailedViewActivity.this)
                .load(urlToImage)
                .into(podcastImage);


        podcastSummary = (TextView)findViewById(R.id.podcastSummaryText);
        podcastSummary.setText(podcast.pc_summary);
    }


    private class DownloadImage extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... params) {
            String imageURL = params[0];
            Bitmap bitmap = null;

            try {
                InputStream input = new java.net.URL(imageURL).openStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
       return bitmap;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            podcastImage.setImageBitmap(bitmap);

        }
    }



}
