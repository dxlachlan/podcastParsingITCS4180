package com.example.danielcampbell.group21_hw5;

import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by danielcampbell on 10/27/17.
 */

public class PodcastParser {

    public static class PodcastPullParser {
        static public ArrayList<Podcast> parsePodcasts(InputStream inputStream) throws XmlPullParserException, IOException {
            //System.out.println("entered PodcastParser");
            ArrayList<Podcast> podcasts =  new ArrayList<>();
            Podcast podcast = null;
            Boolean flag = false;
            Boolean imgFlag = false;
            Boolean releaseFlag = false;
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, "UTF-8");
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT){

                switch (event) {
                    case XmlPullParser.START_TAG:
                        //do parsing
                        if (parser.getName().equals("entry")) {
                            podcast = new Podcast();
                            flag = true;

                        } else if (parser.getName().equals("releaseDate") && flag){
                            releaseFlag = true;
                            if (releaseFlag == true){
                                podcast.pc_updateDate = String.valueOf(parser.nextText().trim());
                                //System.out.println("Released: " + podcast.pc_updateDate);

                            } else {
                                //nothing
                            }

                        } else if (parser.getName().equals("title") && flag) {
                            podcast.pc_title = String.valueOf(parser.nextText().trim());
                            //System.out.println(podcast.pc_title.toString());

                        } else if (parser.getName().equals("summary") && flag) {
                            podcast.pc_summary = String.valueOf(parser.nextText().trim());
                            //System.out.println(podcast.pc_summary);

                        } else if (parser.getName().equals("image") && flag) {
                            imgFlag = true;

                            if(imgFlag == true && parser.getAttributeValue(null, "height").equals("55")){
                                podcast.pc_img = String.valueOf(parser.nextText().trim());
                                //System.out.println("true");
                            } else if (imgFlag == true & parser.getAttributeValue(null, "height").equals("77")){
                                podcast.pc_Limg = String.valueOf(parser.nextText().trim());
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("entry")){
                            podcasts.add(podcast);
                            flag = false;
                            imgFlag = false;
                            releaseFlag = false;
                            //System.out.println("podcast added!");
                        }
                        break;

                    //default case statement
                    default:
                        break;
                }
                event = parser.next();
            }
       return podcasts; }
    }
}
