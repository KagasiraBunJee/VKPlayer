package com.historic.vkplayer.utils;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;

import com.historic.vkplayer.R;
import com.historic.vkplayer.entity.VKAudio;

import java.io.File;
import java.lang.String;

/**
 * Created by Historic on 17.07.2015.
 */
public class Utils
{
    public static VKAudio getAudio(File file, Context context)
    {
        Uri fileUri = Uri.fromFile(file);

        MediaMetadataRetriever mediaRetriever = (MediaMetadataRetriever)new MediaMetadataRetriever();
        mediaRetriever.setDataSource(context,fileUri);

        String soundArtist = (String)mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        String soundTitle = (String)mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);

        if (soundArtist == "null" || soundArtist == null)
        {
            soundArtist = (String)mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR);
        }

        mediaRetriever.release();

        VKAudio audio = new VKAudio(soundArtist,soundTitle);

        return audio;
    }
}
