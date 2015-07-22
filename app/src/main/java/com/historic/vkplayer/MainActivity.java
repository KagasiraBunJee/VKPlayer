package com.historic.vkplayer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.historic.vkplayer.adapters.MusicListAdapter;
import com.historic.vkplayer.entity.VKAudio;
import com.historic.vkplayer.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list_layout);

        listView = (ListView)findViewById(R.id.musicList);

        showListofMusic();
    }

    private void showListofMusic()
    {
        File f = new File(getString(R.string.vk_audio_path));
        File[] files = f.listFiles();
        if(f.exists())
        {
            if(files.length > 0)
            {
                List<File> fileList = new ArrayList<File>();
                int interator = 0;
                for(File file : files)
                {
                    if(!file.getName().contains(".covers"))
                    {
                        fileList.add(file);
                    }
                }
                new MusicListAsync(getApplicationContext(),getLayoutInflater(), fileList).execute();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class MusicListAsync extends AsyncTask<Void, Integer, Void> {

        private Context context;
        private Integer filesCount;
        private LayoutInflater inflater;
        private List<VKAudio> list = new ArrayList<VKAudio>();
        List<File> files = new ArrayList<File>();
        ProgressDialog pd;

        Integer loaded = 0;

        public MusicListAsync(Context context, LayoutInflater inflater, List<File> files)
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
                        VKAudio audio = Utils.getAudio(inFile, context);
                        list.add(audio);
                    }
                }
                publishProgress(1);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            pd.dismiss();
            MusicListAdapter adapter = new MusicListAdapter(list, inflater, context);
            listView.setAdapter(adapter);

        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(MainActivity.this);
            pd.setIndeterminate(false);
            pd.setTitle("VKPlayer");
            pd.setMessage("Loading music list");
            pd.setMax(files.size());
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            loaded += values[0];
            pd.setProgress(loaded);
        }
    }
}
