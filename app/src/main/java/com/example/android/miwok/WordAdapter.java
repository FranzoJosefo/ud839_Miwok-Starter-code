package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by franciscoolivero on 4/19/18.
 */


public class WordAdapter extends ArrayAdapter<Word> {
    //Stores the colorResourceId that will be applied to the background
    private int mBackgroundColorResourceId;


    public WordAdapter(Activity context, ArrayList<Word> words, int BackgroundColorResourceId) {
        super(context, 0, words);
        this.mBackgroundColorResourceId = BackgroundColorResourceId;

    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        /** Get the {@link Word} object located at this position in the list*/
        Word currentWord = getItem(position);

        TextView defaultWordTextView = (TextView) listItemView.findViewById(R.id.text_default);
        defaultWordTextView.setText(currentWord.getmDefaultTranslation());

        TextView miwokWordTextView = (TextView) listItemView.findViewById(R.id.text_miwok);
        miwokWordTextView.setText(currentWord.getmMiwokTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        if (currentWord.hasImage()) {
            //Set the ImageView to the image resource specified in the current word.
            imageView.setImageResource(currentWord.getmImageResourceId());
            //Make sure the ImageView is visible in case it's reused.
            imageView.setVisibility(View.VISIBLE);
        } else {
            //Hides the ImageView
            imageView.setVisibility(View.GONE);
        }

        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.text_container);
        linearLayout.setBackgroundResource(mBackgroundColorResourceId);

//        final MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), currentWord.getmAudioResourceId());
//
//        listItemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!mediaPlayer.isPlaying()){
//                    mediaPlayer.start();
//                }
//            }
//        });


        return listItemView;
    }
}
