package com.smart.videored.ui.activities;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.ads.control.AdmobHelp;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.material.tabs.TabLayout;
import com.smart.videored.middleware.hw.photomovie.PhotoMovie;
import com.smart.videored.middleware.hw.photomovie.PhotoMovieFactory;
import com.smart.videored.middleware.hw.photomovie.PhotoMoviePlayer;
import com.smart.videored.middleware.hw.photomovie.model.PhotoSource;
import com.smart.videored.middleware.hw.photomovie.model.SimplePhotoData;
import com.smart.videored.middleware.hw.photomovie.music.MusicPlayer;
import com.smart.videored.middleware.hw.photomovie.record.GLMovieRecorder;
import com.smart.videored.middleware.hw.photomovie.render.GLSurfaceMovieRenderer;
import com.smart.videored.middleware.hw.photomovie.render.GLTextureMovieRender;
import com.smart.videored.middleware.hw.photomovie.render.GLTextureView;
import com.smart.videored.middleware.hw.photomovie.timer.IMovieTimer;
import com.smart.videored.middleware.hw.photomovie.util.MLog;
import com.smart.videored.middleware.matisse.ui.LoadingDialog;
import com.smart.videored.core.screen.activity.ViewPagerCustomAdapter;
import com.smart.videored.storage.Storage;
import com.smart.videored.R;
import com.smart.videored.UriUtil;
import com.smart.videored.model.FilterItem;
import com.smart.videored.model.TransferItem;
import com.smart.videored.ui.dialog.PhotoDiscardDialog;
import com.smart.videored.ui.fragment.movie.DurationFragment;
import com.smart.videored.ui.fragment.movie.FilterFragment;
import com.smart.videored.ui.fragment.movie.MusicFragment;
import com.smart.videored.ui.fragment.movie.RatioFragment;
import com.smart.videored.ui.fragment.movie.TransferFragment;
import com.smart.videored.utils.DeviceUtils;
import com.smart.videored.view.PlayPauseView;
import com.smart.videored.view.radioview.RatioDatumMode;
import com.smart.videored.view.radioview.RatioFrameLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class MovieActivity extends BaseSplitActivity implements View.OnClickListener, IMovieTimer.MovieListener, FilterFragment.FilterFragmentListener, TransferFragment.TransferFragmentListener, MusicFragment.MusicFragmentListener, DurationFragment.DurationFragmentListener, RatioFragment.RatioFragmentListener {
    private static final int REQUEST_MUSIC = 234;

    public static final String TAG = MovieActivity.class.getSimpleName();
    private Runnable alphaRunnable = new Runnable() {
        public void run() {
            MovieActivity.this.controlContainer.animate().alpha(0.0f).setListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    MovieActivity.this.controlContainer.setVisibility(View.GONE);
                }
            }).start();
        }
    };
    String audioPath = null;
    ImageView btnBack;
    ImageView btnFinish;
    PlayPauseView btnPlayPause;

    public ViewGroup controlContainer;
    int duration = 2000;
    private Handler handler = new Handler();
    private boolean isSeekBarTracking = false;
    private GLTextureView mGLTextureView;
    private GLSurfaceMovieRenderer mMovieRenderer;
    private PhotoMovieFactory.PhotoMovieType mMovieType = PhotoMovieFactory.PhotoMovieType.RANDOM;
    private Uri mMusicUri;
    private PhotoMovie mPhotoMovie;

    public PhotoMoviePlayer mPhotoMoviePlayer;
    String musicPath = null;
    String path = null;

    public LoadingDialog progressDialog;
    RatioFrameLayout ratioFrameLayout;

    public ViewGroup savingLayout;
    private SeekBar sbControl;
    private TabLayout tabLayoutMovie;
    private TextView tvControlCurrentTime;
    private TextView tvControlTotalTime;

    public TextView tvSaving;
    private ViewPager viewPagerMovie;

    private void startToFinalActivity() {
    }

    public void onMovieEnd() {
    }

    public void onMoviePaused() {
    }

    public void onMovieResumed() {
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.act_movie);
        LoadingDialog progressDialog2 = new LoadingDialog(this);
        this.progressDialog = progressDialog2;
        showAd();
        initView();
        this.btnPlayPause.setPlayPauseListener(new PlayPauseView.PlayPauseListener() {
            public void play() {
                MovieActivity.this.onResumeVideo();
            }

            public void pause() {
                MovieActivity.this.onPauseVideo();
            }
        });
        ArrayList<String> stringArrayList = getIntent().getExtras().getStringArrayList("PHOTO");
        int[] iArr = {
                R.raw.sad, R.raw.romantic, R.raw.funny, R.raw.summer, R.raw.movie,
                R.raw.happy, R.raw.christmas, R.raw.travel, R.raw.beach, R.raw.friend, R.raw.love
        };
        for (int i = 0; i < iArr.length; i++) {
            String str = i + ".m4r";
            Storage.CopyRAWToSDCard(this, iArr[i], str);
        }
        path = Storage.getDirectoryRawPath(this);
        getMusicData(0);
        initMoviePlayer();
        onPhotoPick(stringArrayList);
    }

    private void showAd() {
        AdmobHelp.getInstance().loadBanner(this);
    }

    private void initView() {
        this.ratioFrameLayout = (RatioFrameLayout) findViewById(R.id.videoContainer);
        this.mGLTextureView = (GLTextureView) findViewById(R.id.gl_texture);
        this.btnPlayPause = (PlayPauseView) findViewById(R.id.btn_play_pause);
        ImageView imageView = (ImageView) findViewById(R.id.btnBack);
        this.btnBack = imageView;
        imageView.setOnClickListener(this);
        ImageView imageView2 = (ImageView) findViewById(R.id.btnFinish);
        this.btnFinish = imageView2;
        imageView2.setOnClickListener(this);
        this.mGLTextureView.setOnClickListener(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpagerMovie);
        this.viewPagerMovie = viewPager;
        //setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayoutMovie);
        this.tabLayoutMovie = tabLayout;
        tabLayout.setupWithViewPager(this.viewPagerMovie);
        // setupTabIconMovie();
        this.tabLayoutMovie.setTabTextColors(getResources().getColor(R.color.un_selected_white), getResources().getColor(R.color.rainbow_yellow));
        this.btnPlayPause.setPlaying(true);
        this.controlContainer = (ViewGroup) findViewById(R.id.control_container);
        this.sbControl = (SeekBar) findViewById(R.id.sb_control);
        this.tvControlCurrentTime = (TextView) findViewById(R.id.tv_control_current_time);
        this.tvControlTotalTime = (TextView) findViewById(R.id.tv_control_total_time);
        this.sbControl.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.savingLayout = (ViewGroup) findViewById(R.id.saving_layout);
        this.tvSaving = (TextView) findViewById(R.id.tv_saving);
        hideControl();
        initTabBar();
    }

    int[] tabsIcons = {
            R.drawable.ic_movie_filter,
            R.drawable.ic_movie_transfer,
            R.drawable.ic_movie_music,
            R.drawable.ic_duration_movie,
            R.drawable.ic_aspect_ratio};
    private ViewPagerCustomAdapter mViewPagerAdapter = null;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private String[] tabs = {"Filter", "Transition", "Music", "Time", "Canvas"};

    private void initTabBar() {
        mViewPagerAdapter = new ViewPagerCustomAdapter(fragmentManager);

        FilterFragment filterFragment = new FilterFragment();
        mViewPagerAdapter.addFragment(tabs[0], filterFragment);
        filterFragment.setFilterFragmentListener(this);

        TransferFragment transferFragment = new TransferFragment();
        mViewPagerAdapter.addFragment(tabs[1], transferFragment);
        transferFragment.setTransferFragmentListener(this);

        MusicFragment musicFragment = new MusicFragment();
        mViewPagerAdapter.addFragment(tabs[2], musicFragment);
        musicFragment.setMusicFragmentListener(this);

        DurationFragment durationFragment = new DurationFragment();
        mViewPagerAdapter.addFragment(tabs[3], durationFragment);
        durationFragment.setDurationFragmentListener(this);

        RatioFragment ratioFragment = new RatioFragment();
        mViewPagerAdapter.addFragment(tabs[4], ratioFragment);
        ratioFragment.RatioFragmentListener(this);


        viewPagerMovie.setAdapter(mViewPagerAdapter);
        viewPagerMovie.setOffscreenPageLimit(5);
        tabLayoutMovie.setupWithViewPager(viewPagerMovie);
        viewPagerMovie.setCurrentItem(0);

        //Init
        for (int i = 0; i < tabLayoutMovie.getTabCount(); i++) {
            Drawable drawable = ContextCompat.getDrawable(this, tabsIcons[i]);
            drawable.clearColorFilter();
            tabLayoutMovie.getTabAt(i).setIcon(tabsIcons[i]);
        }

        tabLayoutMovie.setTabTextColors(getResources().getColor(R.color.black), getResources().getColor(R.color.blue));

        tabLayoutMovie.getTabAt(0).getIcon().setTint(getResources().getColor(R.color.blue));

        tabLayoutMovie.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setTint(getResources().getColor(R.color.blue));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setTint(getResources().getColor(R.color.black));

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

   /* private void setupTabIconMovie() {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView.setText(getString(R.string.str_filter));
        textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_movie_filter, 0, 0);
        this.tabLayoutMovie.getTabAt(0).setCustomView((View) textView);
        TextView textView2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView2.setText(getString(R.string.str_transfer));
        textView2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_movie_transfer, 0, 0);
        this.tabLayoutMovie.getTabAt(1).setCustomView((View) textView2);
        TextView textView3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView3.setText(getString(R.string.str_music));
        textView3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_movie_music, 0, 0);
        this.tabLayoutMovie.getTabAt(2).setCustomView((View) textView3);
        TextView textView4 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView4.setText(getString(R.string.str_duration));
        textView4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_duration_movie, 0, 0);
        this.tabLayoutMovie.getTabAt(3).setCustomView((View) textView4);
        TextView textView5 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        textView5.setText(getString(R.string.str_ratio));
        textView5.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_aspect_ratio, 0, 0);
        this.tabLayoutMovie.getTabAt(4).setCustomView((View) textView5);
        if ((getResources().getConfiguration().screenLayout & 15) == 1) {
            this.tabLayoutMovie.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }*/

/*    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        FilterFragment filterFragment = new FilterFragment();
        viewPagerAdapter.addFrag(filterFragment, getString(R.string.str_filter));
        filterFragment.setFilterFragmentListener(this);
        TransferFragment transferFragment = new TransferFragment();
        viewPagerAdapter.addFrag(transferFragment, getString(R.string.str_transfer));
        transferFragment.setTransferFragmentListener(this);
        MusicFragment musicFragment = new MusicFragment();
        viewPagerAdapter.addFrag(musicFragment, getString(R.string.str_music));
        musicFragment.setMusicFragmentListener(this);
        DurationFragment durationFragment = new DurationFragment();
        viewPagerAdapter.addFrag(durationFragment, getString(R.string.str_duration));
        durationFragment.setDurationFragmentListener(this);
        RatioFragment ratioFragment = new RatioFragment();
        viewPagerAdapter.addFrag(ratioFragment, getString(R.string.str_ratio));
        ratioFragment.RatioFragmentListener(this);
        viewPager.setAdapter(viewPagerAdapter);
    }*/

    private void initMoviePlayer() {
        this.mMovieRenderer = new GLTextureMovieRender(this.mGLTextureView);
        PhotoMoviePlayer photoMoviePlayer = new PhotoMoviePlayer(getApplicationContext());
        this.mPhotoMoviePlayer = photoMoviePlayer;
        photoMoviePlayer.setMovieRenderer(this.mMovieRenderer);
        this.mPhotoMoviePlayer.setMovieListener(this);
        this.mPhotoMoviePlayer.setLoop(true);
        this.mPhotoMoviePlayer.setOnPreparedListener(new PhotoMoviePlayer.OnPreparedListener() {
            public void onPreparing(PhotoMoviePlayer photoMoviePlayer, float f) {
            }

            public void onPrepared(PhotoMoviePlayer photoMoviePlayer, int i, int i2) {
                MovieActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        MovieActivity.this.mPhotoMoviePlayer.start();
                    }
                });
            }

            public void onError(PhotoMoviePlayer photoMoviePlayer) {
                MLog.i("onPrepare", "onPrepare error");
            }
        });
    }

    public void onPhotoPick(ArrayList<String> arrayList) {
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new SimplePhotoData(this, it.next(), 2));
        }
        PhotoSource photoSource = new PhotoSource(arrayList2);
        if (this.mPhotoMoviePlayer == null) {
            startPlay(photoSource);
        } else {
            createVideo(photoSource, PhotoMovieFactory.PhotoMovieType.GRADIENT);
        }
    }


    public void startVideo() {
        this.mPhotoMoviePlayer.start();
        int duration2 = this.mPhotoMovie.getDuration() / 1000;
        this.tvControlTotalTime.setText(DeviceUtils.getTimeString(duration2));
        this.sbControl.setMax(duration2);
    }

    public void onPauseVideo() {
        this.mPhotoMoviePlayer.pause();
    }

    public void onResumeVideo() {
        this.mPhotoMoviePlayer.start();
    }

    private void startPlay(PhotoSource photoSource) {
        PhotoMovie generatePhotoMovie = PhotoMovieFactory.generatePhotoMovie(photoSource, this.mMovieType, this.duration);
        this.mPhotoMovie = generatePhotoMovie;
        this.mPhotoMoviePlayer.setDataSource(generatePhotoMovie);
        this.mPhotoMoviePlayer.prepare();
    }

    public void saveVideo() {
        this.mPhotoMoviePlayer.pause();
        AdmobHelp.getInstance().loadNative(this);
        this.savingLayout.setVisibility(View.VISIBLE);
        TextView textView = this.tvSaving;
        textView.setText(getString(R.string.str_saving_video) + "...");
        GLMovieRecorder gLMovieRecorder = new GLMovieRecorder(this);
        Storage.VideoValue videoValue = Storage.saveVideo(this);
        gLMovieRecorder.configOutput(this.mGLTextureView.getWidth(), this.mGLTextureView.getHeight(), this.mGLTextureView.getWidth() * this.mGLTextureView.getHeight() > 1500000 ? GmsVersion.VERSION_SAGA : 4000000, 30, 1, videoValue.getPath());
        PhotoMovie generatePhotoMovie = PhotoMovieFactory.generatePhotoMovie(this.mPhotoMovie.getPhotoSource(), this.mMovieType, this.duration);
        GLSurfaceMovieRenderer gLSurfaceMovieRenderer = new GLSurfaceMovieRenderer(this.mMovieRenderer);
        gLSurfaceMovieRenderer.setPhotoMovie(generatePhotoMovie);
        if (this.mMusicUri != null) {
            this.audioPath = this.musicPath;
        }
        if (!TextUtils.isEmpty(this.audioPath)) {
            if (Build.VERSION.SDK_INT < 18) {
                Toast.makeText(getApplicationContext(), "Mix audio needs api18!", Toast.LENGTH_LONG).show();
            } else {
                gLMovieRecorder.setMusic(this.audioPath);
            }
        }
        gLMovieRecorder.setDataSource(gLSurfaceMovieRenderer);
        MLog.i(TAG, "saveVideo setDataSource");
        final GLMovieRecorder gLMovieRecorder2 = gLMovieRecorder;
        gLMovieRecorder.startRecord(new GLMovieRecorder.OnRecordListener() {
            public void onRecordFinish(boolean z) {
                long currentTimeMillis = System.currentTimeMillis();
                String access$100 = MovieActivity.TAG;
                MLog.i(access$100, "onRecordFinish:" + (currentTimeMillis - currentTimeMillis));
                if (z){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        Storage.updateGalleryAbove10(MovieActivity.this, videoValue.getContentValues(), videoValue.getUri());
                    } else {
                        Storage.updateGalleryBelow10(MovieActivity.this, videoValue.getPath());
                    }
                    Intent intent = new Intent(MovieActivity.this, FinalActivity.class);
                    intent.putExtra("VIDEO", Uri.fromFile(new File(videoValue.getPath())));
                    MovieActivity.this.startActivity(intent);
                }else {
                    Toast.makeText(MovieActivity.this.getApplicationContext(), "Record error!", Toast.LENGTH_LONG).show();
                }
                if (gLMovieRecorder2.getAudioRecordException() != null) {
                    Log.e("XXXXXXXX", gLMovieRecorder2.getAudioRecordException().getMessage());
                    Context applicationContext = MovieActivity.this.getApplicationContext();
                    Toast.makeText(applicationContext, "record audio failed:" + gLMovieRecorder2.getAudioRecordException().toString(), Toast.LENGTH_LONG).show();
                }
                MovieActivity.this.savingLayout.setVisibility(View.GONE);
            }

            public void onRecordProgress(final int i, final int i2) {
                MovieActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        TextView access$300 = MovieActivity.this.tvSaving;
                        int per = ((int) ((((float) i) / ((float) i2)) * 100.0f));
                        if (per > 100) {
                            per = 100;
                        }
                        access$300.setText(MovieActivity.this.getString(R.string.str_saving_video) + " " + per + "%");
                    }
                });
            }
        });
    }

    public void setMusic(Uri uri) {
        this.mMusicUri = uri;
        createVideo(this.mPhotoMovie.getPhotoSource(), this.mMovieType);
    }

    public void createVideo(PhotoSource photoSource, PhotoMovieFactory.PhotoMovieType photoMovieType) {
        this.progressDialog.show();
        this.mPhotoMoviePlayer.stop();
        PhotoMovie generatePhotoMovie = PhotoMovieFactory.generatePhotoMovie(photoSource, photoMovieType, this.duration);
        this.mPhotoMovie = generatePhotoMovie;
        this.mPhotoMoviePlayer.setDataSource(generatePhotoMovie);
        this.mPhotoMoviePlayer.setOnPreparedListener(new PhotoMoviePlayer.OnPreparedListener() {
            public void onPreparing(PhotoMoviePlayer photoMoviePlayer, float f) {
                Log.d("onPrepare", "onPreparing " + f);
            }

            public void onPrepared(PhotoMoviePlayer photoMoviePlayer, int i, int i2) {
                Log.d("onPrepare", "onPrepared " + i2);
                MovieActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        MovieActivity.this.startVideo();
                        if (MovieActivity.this.progressDialog != null) {
                            MovieActivity.this.progressDialog.dismiss();
                        }
                    }
                });
            }

            public void onError(PhotoMoviePlayer photoMoviePlayer) {
                if (MovieActivity.this.progressDialog != null) {
                    MovieActivity.this.progressDialog.dismiss();
                }
                Log.d("onPrepare", "onPrepare onError");
            }
        });
        if (this.mMusicUri != null) {
            LoadingDialog progressDialog2 = this.progressDialog;
            if (progressDialog2 != null) {
                progressDialog2.show();
            }
            this.mPhotoMoviePlayer.setMusic(this, this.mMusicUri, new MusicPlayer.OnMusicOKListener() {
                public void onMusicOK() {
                    MovieActivity.this.mPhotoMoviePlayer.prepare();
                    if (progressDialog2 != null) {
                        progressDialog2.dismiss();
                    }
                }
            });
        }
    }


    private void CopyRAWtoSDCard(int id, String path) throws IOException {
        InputStream in = getResources().openRawResource(id);
        FileOutputStream out = new FileOutputStream(path);
        byte[] buff = new byte[1024];
        int read = 0;
        try {
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
        } finally {
            in.close();
            out.close();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnBack) {
            PhotoDiscardDialog photoDiscardDialog = new PhotoDiscardDialog(this);
            photoDiscardDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            photoDiscardDialog.show();
        } else if (id == R.id.btnFinish) {
            saveVideo();
//            AdmobHelp.getInstance().showInterstitialAd(this,() -> {
//
//            });

        } else if (id == R.id.gl_texture) {
            this.handler.removeCallbacks(this.alphaRunnable);
            this.controlContainer.setAlpha(1.0f);
            this.controlContainer.setVisibility(View.VISIBLE);
            hideControl();
        }
    }

    private void hideControl() {
        this.handler.removeCallbacks(this.alphaRunnable);
        this.handler.postDelayed(this.alphaRunnable, 3500);
    }

    public void onFilter(FilterItem filterItem) {
        this.mMovieRenderer.setMovieFilter(filterItem.initFilter());
    }

    public void onMyMusic() {
        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(intent, REQUEST_MUSIC);
    }

    public void onTypeMusic(int i) {
        getMusicData(i);
        setMusic(this.mMusicUri);
    }

    private void getMusicData(int i) {
        this.musicPath = this.path + File.separator + i + ".m4r";
        this.mMusicUri = Uri.fromFile(new File(musicPath));
    }

    public void onTransfer(TransferItem transferItem) {
        this.mMovieType = transferItem.type;
        createVideo(this.mPhotoMovie.getPhotoSource(), this.mMovieType);
    }

    public void onDurationSelect(int i) {
        this.duration = i;
        createVideo(this.mPhotoMovie.getPhotoSource(), this.mMovieType);
    }

    public void onMovieUpdate(int i) {
        if (!this.isSeekBarTracking) {
            int round = Math.round(((float) i) / 1000.0f);
            this.sbControl.setProgress(round);
            this.tvControlCurrentTime.setText(DeviceUtils.getTimeString(round));
        }
    }

    public void onMovieStarted() {
        MLog.d(TAG, "onMovieStarted");
    }


    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == REQUEST_MUSIC) {
            setMusic(intent.getData());
            this.musicPath = UriUtil.getPath(this, this.mMusicUri);
        }
    }


    public void onPostResume() {
        super.onPostResume();
    }


    public void onPause() {
        super.onPause();
    }


    public void onStop() {
        super.onStop();
        PhotoMoviePlayer photoMoviePlayer = this.mPhotoMoviePlayer;
        if (photoMoviePlayer != null) {
            photoMoviePlayer.pause();
        }
    }


    public void onStart() {
        super.onStart();
        PhotoMoviePlayer photoMoviePlayer = this.mPhotoMoviePlayer;
        if (photoMoviePlayer != null) {
            photoMoviePlayer.start();
        }
    }

    public void onBackPressed() {
        PhotoDiscardDialog photoDiscardDialog = new PhotoDiscardDialog(this);
        photoDiscardDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        photoDiscardDialog.show();
    }

    public void destroyMovie() {
        PhotoMoviePlayer photoMoviePlayer = this.mPhotoMoviePlayer;
        if (photoMoviePlayer != null) {
            photoMoviePlayer.pause();
            this.mPhotoMoviePlayer.destroy();
        }
    }

    public void onRatio(int i) {
        if (i == 11) {
            this.ratioFrameLayout.setRatio(RatioDatumMode.valueOf(1), 1.0f, 1.0f);
            createVideo(this.mPhotoMovie.getPhotoSource(), this.mMovieType);
        } else if (i == 34) {
            this.ratioFrameLayout.setRatio(RatioDatumMode.valueOf(1), 3.0f, 4.0f);
            createVideo(this.mPhotoMovie.getPhotoSource(), this.mMovieType);
        } else if (i == 43) {
            this.ratioFrameLayout.setRatio(RatioDatumMode.valueOf(1), 4.0f, 3.0f);
            createVideo(this.mPhotoMovie.getPhotoSource(), this.mMovieType);
        } else if (i == 169) {
            this.ratioFrameLayout.setRatio(RatioDatumMode.valueOf(1), 16.0f, 9.0f);
            createVideo(this.mPhotoMovie.getPhotoSource(), this.mMovieType);
        } else if (i == 916) {
            this.ratioFrameLayout.setRatio(RatioDatumMode.valueOf(1), 9.0f, 16.0f);
            createVideo(this.mPhotoMovie.getPhotoSource(), this.mMovieType);
        } else if (i == 45) {
            this.ratioFrameLayout.setRatio(RatioDatumMode.valueOf(1), 4.0f, 5.0f);
            createVideo(this.mPhotoMovie.getPhotoSource(), this.mMovieType);
        }
    }


    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
