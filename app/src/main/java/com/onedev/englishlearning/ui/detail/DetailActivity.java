package com.onedev.englishlearning.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.onedev.englishlearning.BuildConfig;
import com.onedev.englishlearning.R;
import com.onedev.englishlearning.adapter.DetailAdapter;
import com.onedev.englishlearning.data.model.Sentence;
import com.onedev.englishlearning.data.model.SentenceData;
import com.onedev.englishlearning.data.network.model.SentenceResponse;
import com.onedev.englishlearning.ui.base.BaseActivity;
import com.onedev.englishlearning.utils.TimeUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements DetailBaseView, SeekBar.OnSeekBarChangeListener {

    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_CATEGORY_ID = "EXTRA_CATEGORY_ID";

    public static Intent getStartIntent(Context context, String title, int categoryId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Inject
    DetailMvpPresenter<DetailBaseView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.txtCurrentDuration)
    TextView txtCurrentDuration;
    @BindView(R.id.txtTotalDuration)
    TextView txtTotalDuration;
    @BindView(R.id.seekBarEntireSound)
    SeekBar seekBarEntireSound;
    @BindView(R.id.imgBtnPlayPause)
    ImageButton imgBtnPlayPause;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int mCategoryId;
    private ArrayList<Sentence> mListItems = new ArrayList<>();
    private DetailAdapter mDetailAdapter;
    private SentenceData mSentenceData;
    private int mSelectedItem;

    private MediaPlayer mEntireSoundMediaPlayer, mShortSoundMediaPlayer;
    private Timer mTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMedia(mEntireSoundMediaPlayer);
        releaseMedia(mShortSoundMediaPlayer);
        stopUpdateTimer();
    }

    @OnClick(R.id.nav_back_btn)
    public void onBackClick() {
        finish();
    }

    @OnClick(R.id.imgBtnPlayPause)
    public void onPlayPauseClick() {
        if (mEntireSoundMediaPlayer != null) {
            if (mEntireSoundMediaPlayer.isPlaying()) {
                mEntireSoundMediaPlayer.pause();
                setImageButton(R.drawable.ic_play);
                stopUpdateTimer();
            } else {
                releaseMedia(mShortSoundMediaPlayer);
                updateAdapterStopAllSound();
                mEntireSoundMediaPlayer.start();
                setImageButton(R.drawable.ic_pause);
                startUpdateTimer();
            }
        } else {
            showMessage(R.string.cannot_play_the_audio);
        }
    }

    @Override
    protected void setUp() {
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_TITLE)) {
            toolbar.setTitle(intent.getStringExtra(EXTRA_TITLE));
        }
        if (intent.hasExtra(EXTRA_CATEGORY_ID)) {
            mCategoryId = intent.getIntExtra(EXTRA_CATEGORY_ID, 0);
        }

        seekBarEntireSound.setOnSeekBarChangeListener(this);

        mDetailAdapter = new DetailAdapter(mListItems, (position, type) -> {
            mSelectedItem = position;
            updateAdapterStopAllSound();
            mListItems.get(position).setPlaying(true);
            mDetailAdapter.notifyItemChanged(position);
            playShortSound(mListItems.get(position).getSoundUrl());
        });
        recyclerView.setAdapter(mDetailAdapter);

        mPresenter.getSentences(mCategoryId, true);
    }

    @Override
    public void onGetSentencesSuccess(SentenceResponse response) {
        mListItems.clear();
        mSentenceData = response.getData();
        mListItems.addAll(response.getData().getSentences());
        mDetailAdapter.notifyDataSetChanged();
        prepareMediaPlayer();
    }

    private void prepareMediaPlayer() {
        mEntireSoundMediaPlayer = new MediaPlayer();
        mEntireSoundMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mEntireSoundMediaPlayer.setDataSource(BuildConfig.BASE_URL + mSentenceData.getEntireSound());
            mEntireSoundMediaPlayer.prepareAsync();
            mEntireSoundMediaPlayer.setOnPreparedListener(mediaPlayer -> {
                txtTotalDuration.setText(TimeUtils.milliSecondsToTimer(mEntireSoundMediaPlayer.getDuration()));
                seekBarEntireSound.setMax(mEntireSoundMediaPlayer.getDuration());
            });
            mEntireSoundMediaPlayer.setOnCompletionListener(mediaPlayer -> setImageButton(R.drawable.ic_play));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playShortSound(String soundUrlEndPoint) {
        if (mEntireSoundMediaPlayer != null && mEntireSoundMediaPlayer.isPlaying()) {
            mEntireSoundMediaPlayer.pause();
            imgBtnPlayPause.setImageResource(R.drawable.ic_play);
            stopUpdateTimer();
        }
        releaseMedia(mShortSoundMediaPlayer);
        mShortSoundMediaPlayer = new MediaPlayer();
        mShortSoundMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mShortSoundMediaPlayer.setDataSource(BuildConfig.BASE_URL + soundUrlEndPoint);
            mShortSoundMediaPlayer.prepareAsync();
            mShortSoundMediaPlayer.setOnPreparedListener(MediaPlayer::start);
            mShortSoundMediaPlayer.setOnCompletionListener(mediaPlayer -> {
                mListItems.get(mSelectedItem).setPlaying(false);
                mDetailAdapter.notifyItemChanged(mSelectedItem);
                releaseMedia(mediaPlayer);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void releaseMedia(MediaPlayer mediaPlayer) {
        try {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateAdapterStopAllSound() {
        for (int i = 0; i < mListItems.size(); i++) {
            if (mListItems.get(i).isPlaying()) {
                mListItems.get(i).setPlaying(false);
                mDetailAdapter.notifyItemChanged(i);
            }
        }
    }

    private void setImageButton(int resId) {
        imgBtnPlayPause.setImageResource(resId);
    }

    private void startUpdateTimer() {
        stopUpdateTimer();
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (mEntireSoundMediaPlayer != null && mEntireSoundMediaPlayer.isPlaying()) {
                    txtTotalDuration.post(() -> {
                        txtCurrentDuration.setText(TimeUtils.milliSecondsToTimer(mEntireSoundMediaPlayer.getCurrentPosition()));
                        seekBarEntireSound.setProgress(mEntireSoundMediaPlayer.getCurrentPosition());
                    });
                }
            }
        }, 0, 1000);
    }

    private void stopUpdateTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (mEntireSoundMediaPlayer != null) {
            mEntireSoundMediaPlayer.seekTo(progress);
            txtCurrentDuration.setText(TimeUtils.milliSecondsToTimer(progress));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
