package com.example.danielcampbell.group21_hw5;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private String ITUNES_API = "https://itunes.apple.com/us/rss/toppodcasts/limit=30/xml";
    private ArrayList<Podcast> podcasts;
    private ArrayList<Podcast> sortedPodcasts;
    Context context;
    EditText searchBar;
    Button goButton;
    Button clearButton;
    ListView listView;
    PodcastAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isConnected()) {
            setTitle("iTunes Top Podcasts");
            Toast.makeText(MainActivity.this, "Internet Available", Toast.LENGTH_SHORT).show();

            context = MainActivity.this;

            try {
                podcasts = new GetItunesData(context).execute(ITUNES_API).get();
                sortedPodcasts = sortPodcastsByDate(podcasts);
                listView = (ListView) findViewById(R.id.podcastListView);
                searchBar = (EditText) findViewById(R.id.searchBar);
                goButton = (Button) findViewById(R.id.goButton);
                clearButton = (Button) findViewById(R.id.clearButton);

                adapter = new PodcastAdapter(this, R.layout.podcastitem, sortedPodcasts);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Podcast clickedPodcast = MainActivity.this.sortedPodcasts.get(position);
                        Intent i = new Intent(getApplicationContext(), DetailedViewActivity.class);
                        i.putExtra("SELECTED_PODCAST", clickedPodcast);
                        startActivity(i);
                    }
                });

                //CLICK GO BUTTON
                goButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //keyword
                        String s = searchBar.getText().toString();
                        //sort arraylist
                        Collections.sort(podcasts, new KeywordComparator(s));
                        //highlight entries containing keyword


                        //update listview
                        adapter.notifyDataSetChanged();



                    }
                });

                //CLICK CLEAR BUTTON
                clearButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //remove highlights

                        //resort by date

                        //clear searchbar

                        //update listview

                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(MainActivity.this, "Internet Disconnected", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }

    public ArrayList<Podcast> sortPodcastsByDate(ArrayList<Podcast> podcasts) {
        Collections.sort(podcasts, new Comparator<Podcast>() {
            @Override
            public int compare(Podcast o1, Podcast o2) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.US);
                Date date1 = null;
                Date date2 = null;
                try {
                    date1 = format.parse(o1.getPc_updateDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    date2 = format.parse(o2.getPc_updateDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date1.compareTo(date2); }
        });
    return podcasts;
    }
}


