package com.historic.vkplayer.adapters;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.historic.vkplayer.entity.VKAudio;
import com.historic.vkplayer.utils.Utils;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.historic.vkplayer.R;

import java.util.ArrayList;

/**
 * Created by Historic on 17.07.2015.
 */

public class MusicListAdapter extends BaseAdapter
{

    private List<VKAudio> musicList = new ArrayList<VKAudio>();
    private LayoutInflater inflater;
    private Context context;

    public MusicListAdapter(List<VKAudio> musicList, LayoutInflater inflater, Context context) {
        this.musicList = musicList;
        this.inflater = inflater;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return musicList.size();
    }

    @Override
    public VKAudio getItem(int position)
    {
        return musicList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View item = convertView;
        if (item == null)
        {
            item = inflater.inflate(R.layout.music_list_item, parent, false);
        }
        TextView musicName = (TextView)item.findViewById(R.id.sampleName);
        VKAudio audio = getItem(position);
        Log.d("file_to_open", context.getString(R.string.vk_audio_path) + "/" + getItem(position));


        try{
            musicName.setText(audio.getArtist()+"-"+audio.getTitle());
        }
        catch (Exception ex){
            Log.d("error", ex.getMessage());
            musicName.setText("undefied");
        }

        return item;
    }

}
