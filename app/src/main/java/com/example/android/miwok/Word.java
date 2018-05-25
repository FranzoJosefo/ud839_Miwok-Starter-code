package com.example.android.miwok;

/**
 * Created by franciscoolivero on 4/19/18.
 * {@link Word} Represents a vocabulary world that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */

public class Word {

    //I USE -1 BECAUSE THERE IS NOT RESOURCE ID WHICH IS NEGATIVE.
    private static final int NO_IMAGE_PROVIDED = -1;
    /**
     * Default translation for the word
     */
    private String mDefaultTranslation;
    //mAlgo es por memberAlgo
    /**
     * Miwok translation for the word
     */
    private String mMiwokTranslation;
    /**
     * Image Resource Id representing the word
     */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private int mAudioResourceId;


    /**
     * @param defaultTranslation is the word in the language the user is familiar with.
     * @param miwokTranslation   is the word in the Miwok language.
     * @param audioResourceId    is the Raw Resource Id for audio the associated with the word.
     */
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;
    }

    /**
     * @param defaultTranslation is the word in the language the user is familiar with.
     * @param miwokTranslation   is the word in the Miwok language.
     * @param imageResourceId    is the Drawable Resource Id for the image associated with the word.
     * @param audioResourceId    is the Raw Resource Id for audio the associated with the word.
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }

    /**
     * @return Returns whether there is or not an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
