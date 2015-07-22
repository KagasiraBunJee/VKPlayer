package com.historic.vkplayer.async;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.historic.vkplayer.R;
import com.historic.vkplayer.adapters.MusicListAdapter;
import com.historic.vkplayer.entity.VKAudio;
import com.historic.vkplayer.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Historic on 21.07.2015.
 */
public class MusicListAsync extends AsyncTask<Void, Integer, Void> {

    private Context context;
    private Integer filesCount;
    private LayoutInflater inflater;
    private List<VKAudio> list = new ArrayList<VKAudio>();
    private File[] files;

    public MusicListAsync(Context context, LayoutInflater inflater, File[] files)
    {
        this.context = context;
        this.inflater = inflater;
        this.files = files;
    }

    @Override
    protected Void doInBackground(Void... params) {

        for (File inFile : files)
        {
            if (inFile.exists())
            {
                if (inFile.isFile()) {
                    if(!inFile.getName().contains(".covers"))
                    {
                        VKAudio audio = Utils.getAudio(inFile, context);
                        list.add(audio);
                        Log.d("debug_file", inFile.getName());
                    }
                }
            }
            publishProgress(1);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        MusicListAdapter adapter = new MusicListAdapter(list, inflater, context);
        ListView listView = (ListView)((Activity)context).findViewById(R.id.musicList);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        Integer currentFile = values[0];
        Log.d("items_22", currentFile+"");
        Log.d("items_overall",""+this.files.length);
    }
}
