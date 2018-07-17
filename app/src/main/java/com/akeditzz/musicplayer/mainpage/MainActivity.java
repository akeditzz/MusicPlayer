package com.akeditzz.musicplayer.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.akeditzz.musicplayer.R;
import com.akeditzz.musicplayer.musiclist.MusicListActivity;
import com.akeditzz.musicplayer.musiclist.model.MusicModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaration
    private static boolean isShuffle;
    private static boolean isLoop;
    private ImageView ivShuffle;
    private ImageView ivLoop;
    private int MUSIC_REQUEST_CODE = 999;
    private static MusicModel musicModel;
    private TextView tvSongName;
    private TextView tvElapsedTime;
    private TextView tvEndTime;
    private ImageView ivThumbNail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_exit);
        }
        initView();
    }

    /**
     * @author Akshay
     * Views initialization
     */
    private void initView() {
        ivShuffle = findViewById(R.id.iv_shuffle);
        ivLoop = findViewById(R.id.iv_loop);
        tvSongName = findViewById(R.id.tv_song_name);
        tvElapsedTime = findViewById(R.id.tv_elapsed_time);
        tvEndTime = findViewById(R.id.tv_end_time);
        ivThumbNail = findViewById(R.id.iv_thumb);
        ImageView ivPlay = findViewById(R.id.iv_play);
        ImageView ivNext = findViewById(R.id.iv_next_song);
        ImageView ivPrevious = findViewById(R.id.iv_previous_song);
        ivShuffle.setOnClickListener(this);
        ivLoop.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivPrevious.setOnClickListener(this);

        if (musicModel == null) {
            startActivityForResult(new Intent(this, MusicListActivity.class), MUSIC_REQUEST_CODE);
        }else {
            playMusic();
        }
    }

    /**
     * @author Akshay
     * common method to check and update ui if shuffle or loop should be active
     */
    private boolean checkShuffleLoops(boolean b, ImageView imageView) {
        boolean temp;
        if (b) {
            temp = false;
            imageView.setColorFilter(ContextCompat.getColor(this, R.color.primaryDark));
        } else {
            temp = true;
            imageView.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        return temp;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_play:
                break;
            case R.id.iv_next_song:
                break;
            case R.id.iv_previous_song:
                break;
            case R.id.iv_shuffle:
                isShuffle = checkShuffleLoops(isShuffle, ivShuffle);
                break;
            case R.id.iv_loop:
                isLoop = checkShuffleLoops(isLoop, ivLoop);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_music:
                startActivityForResult(new Intent(this, MusicListActivity.class), MUSIC_REQUEST_CODE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == MUSIC_REQUEST_CODE && data.getExtras() != null) {
            musicModel = (MusicModel) data.getSerializableExtra(getString(R.string.intentkey_song));
            playMusic();
        }
    }

    /**
     * @author Akshay
     * method to set ui for song selected and play music
     */
    private void playMusic() {
        if (musicModel != null) {
            tvSongName.setText(String.format("%s - %s", musicModel.getArtistName(), musicModel.getSongName()));
            tvElapsedTime.setText(musicModel.getElapsedTime());
            tvEndTime.setText(musicModel.getEndTime());
            ivThumbNail.setImageResource(musicModel.getThumbNail());
        }
    }
}

