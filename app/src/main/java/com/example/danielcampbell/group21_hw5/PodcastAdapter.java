package com.example.danielcampbell.group21_hw5;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.Comparator;
import java.util.List;
import static java.lang.System.load;

/**
 * Created by danielcampbell on 10/28/17.
 */

public class PodcastAdapter extends ArrayAdapter<Podcast> {

    Context context;

    public PodcastAdapter (@NonNull Context context, @LayoutRes int resource, @NonNull List<Podcast> objects){
        super(context, resource, objects);
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Podcast podcast = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.podcastitem, parent, false);
        }

        TextView textViewPodcastTitle = (TextView) convertView.findViewById(R.id.podcastTitle);
        ImageView imageViewPodcastImage = (ImageView) convertView.findViewById(R.id.podcastImage);

        textViewPodcastTitle.setText(podcast.pc_title);
        String urlToImage = podcast.pc_img;

        //PICASSO IMAGE HANDLING
        Picasso
                .with(context)
                .load(urlToImage)
                .into(imageViewPodcastImage);

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public void sort(@NonNull Comparator<? super Podcast> comparator) {
        super.sort(comparator);
    }

    public static CharSequence highlight(String search, String ogText){
        String normalizeText = Normalizer.normalize(ogText, Normalizer.Form.NFD).toLowerCase();
        int start = normalizeText.indexOf(search);
        Spannable highlighted = new SpannableString(ogText);

        if (start < 0 ){
            return ogText;
        } else {
            while (start >= 0){

                int spanStart = Math.min(start, ogText.length());
                int spanEnd = Math.min(start + search.length(), ogText.length());
                highlighted.setSpan(new BackgroundColorSpan(Color.GREEN), spanStart, spanEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                start = normalizeText.indexOf(search, spanEnd);
            }
        }

    return highlighted;}


}

