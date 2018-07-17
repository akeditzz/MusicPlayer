package com.akeditzz.musicplayer.musiclist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.akeditzz.musicplayer.R;
import com.akeditzz.musicplayer.musiclist.adapter.SongListAdapter;
import com.akeditzz.musicplayer.musiclist.interfaces.ISongList;
import com.akeditzz.musicplayer.musiclist.model.MusicModel;

import java.util.ArrayList;

public class MusicListActivity extends AppCompatActivity implements ISongList {

    //Declaration
    private RecyclerView rvSongList;
    private ArrayList<MusicModel> songList;
    private SearchView searchView;
    private SongListAdapter songListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initView();
    }

    /**
     * @author Akshay
     * view initialization
     */
    private void initView() {
        rvSongList = findViewById(R.id.rv_song_list);
        rvSongList.setLayoutManager(new LinearLayoutManager(this));
        setSongList();
    }

    /**
     * @author Akshay
     * method to set songs data in list
     * in future we can fetch data either froms server or form local storage
     */
    private void setSongList() {
        songList = new ArrayList<>();
        songList.add(new MusicModel(getString(R.string.song_paniyon_sa), getString(R.string.artist_tanishk), R.drawable.paniyon_sa, getString(R.string.temp_elapsed_time), getString(R.string.temp_song_duration)));
        songList.add(new MusicModel(getString(R.string.song_tera), getString(R.string.artist_tanishk), R.drawable.tera_ghata, getString(R.string.temp_elapsed_time), getString(R.string.temp_song_duration)));
        songList.add(new MusicModel(getString(R.string.song_high), getString(R.string.artist_guru), R.drawable.high_rated_gabru, getString(R.string.temp_elapsed_time), getString(R.string.temp_song_duration)));
        songList.add(new MusicModel(getString(R.string.song_zingaat), getString(R.string.artist_ajay), R.drawable.zingaat, getString(R.string.temp_elapsed_time), getString(R.string.temp_song_duration)));
        songList.add(new MusicModel(getString(R.string.song_bom_diggy), getString(R.string.artist_zack), R.drawable.bom_diggy, getString(R.string.temp_elapsed_time), getString(R.string.temp_song_duration)));
        songList.add(new MusicModel(getString(R.string.song_tareefan), getString(R.string.artist_badshah), R.drawable.tareefan, getString(R.string.temp_elapsed_time), getString(R.string.temp_song_duration)));
        songList.add(new MusicModel(getString(R.string.song_saathi), getString(R.string.artist_atif), R.drawable.o_saathi, getString(R.string.temp_elapsed_time), getString(R.string.temp_song_duration)));
        songList.add(new MusicModel(getString(R.string.song_pehli), getString(R.string.artist_ajay_gogavale), R.drawable.pehli_baar, getString(R.string.temp_elapsed_time), getString(R.string.temp_song_duration)));
        songList.add(new MusicModel(getString(R.string.song_buzz), getString(R.string.artist_aastha), R.drawable.buzz, getString(R.string.temp_elapsed_time), getString(R.string.temp_song_duration)));
        songList.add(new MusicModel(getString(R.string.song_dil_diyan), getString(R.string.artist_atif), R.drawable.dil_diyan, getString(R.string.temp_elapsed_time), getString(R.string.temp_song_duration)));
        songList.add(new MusicModel(getString(R.string.song_sanu_ek), getString(R.string.artist_rahat), R.drawable.sanu_ek, getString(R.string.temp_elapsed_time), getString(R.string.temp_song_duration)));
        setSongsAdapter();
    }

    /**
     * @author Akshay
     * setting songs list adapter
     */
    private void setSongsAdapter() {
        songListAdapter = new SongListAdapter(this, songList, this);
        rvSongList.setAdapter(songListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_songs, menu);

        // Associate searchable configuration with the SearchView
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setQueryHint(getString(R.string.hint_search_here));
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                songListAdapter.getFilter().filter(query.toLowerCase().trim());
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void musicModel(MusicModel musicModel) {
        Intent intent = new Intent();
        intent.putExtra(getString(R.string.intentkey_song), musicModel);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
