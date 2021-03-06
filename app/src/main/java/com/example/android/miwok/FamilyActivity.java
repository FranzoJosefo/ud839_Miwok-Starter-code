package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    /**
     * Handles playback of all media files.
     */
    MediaPlayer mMediaPlayer;

    /**
     * Handles Audio Focus for audio files.
     */
    AudioManager mAudioManager;
    /**
     * This listener gets triggered when audio focus Changes.
     * (i.e., we gain or lose audio focus because of another app or device)
     */
    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            }
        }
    };
    /**
     * This Listener gets triggered when {@link MediaPlayer} has completed
     * playing the audio file
     */
    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("father", "әpә",
                R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "әṭa",
                R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "angsi",
                R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "tune",
                R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi",
                R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti",
                R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe",
                R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("young sister", "kolliti",
                R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother", "ama",
                R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa",
                R.drawable.family_grandfather, R.raw.family_grandfather));

        //Log to check that the list was created correctly.
        for (int i = 0; i < words.size(); i++) {
            Word currentWord = words.get(i);
            Log.v("FamilyActivity", "Current Word Object: " + currentWord);

        }

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Release the mediaPlayer if it currently exists, to play another sound. This way only one file can be player at a time.
                //And!, if one file is playing, it gets interrupted and the new one will start playing.
                releaseMediaPlayer();
                int afRequest = mAudioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (afRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //Create and setup the {@link MediaPlayer} for the audio resource associated with the current word.
                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, words.get(i).getmAudioResourceId());
                    mMediaPlayer.start();
                    //Setup a listener to the mediaPlayer so that when the audio finish playing I can release the resources.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
                Log.v("FamilyActivity", "Current Word Object: " + words.get(i));
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing it's resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(afChangeListener);
        }
    }
}
