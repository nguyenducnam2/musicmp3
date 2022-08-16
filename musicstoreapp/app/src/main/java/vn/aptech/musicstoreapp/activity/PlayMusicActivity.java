package vn.aptech.musicstoreapp.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.adapter.ViewPagerSongCd;
import vn.aptech.musicstoreapp.entity.Song;
import vn.aptech.musicstoreapp.fragment.Fragment_SongCd;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;
import vn.aptech.musicstoreapp.service_api.service.ArtistService;
import vn.aptech.musicstoreapp.service_api.service.SongService;
import vn.aptech.musicstoreapp.service_local.ForegroundServiceControl;

public class PlayMusicActivity extends AppCompatActivity {
    private androidx.appcompat.widget.Toolbar toolbarplaynhac;
    private SeekBar seekBarnhac;
    private ImageView imageViewtim;
    private TextView textViewtennhac, textViewcasi, textViewrunrime, textViewtatoltime;
    private ImageButton imageButtontronnhac, imageButtonpreviewnhac, imageButtonplaypausenhac, imageButtonnexnhac,
            imageButtonlapnhac;
    private int dem = 0, position = 0, duration = 0, timeValue = 0, durationToService = 0;
    private boolean repeat = false, checkrandom = false, isplaying;
    private static ArrayList<Song> mangbaihat = new ArrayList<>();
    private Fragment_SongCd fragment_dia_nhac;
    public static ViewPagerSongCd adapternhac;
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                isplaying = intent.getBooleanExtra("status_player", false);
                int action = intent.getIntExtra("action_music", 0);
                duration = intent.getIntExtra("duration_music", 0);
                timeValue = intent.getIntExtra("seektomusic", 0);
                position = intent.getIntExtra("position_music", 0);
                seekBarnhac.setProgress(timeValue);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                textViewrunrime.setText(simpleDateFormat.format(timeValue));
                handleMusic(action);
                TimeSong();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playmusic);

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter("send_data_to_activity"));
        GetDataFromIntent();

        AnhXa();
        enventClick();

        StartService();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
    }

    private void StartService() {
        Intent intent = new Intent(this, ForegroundServiceControl.class);
        if (mangbaihat.size() > 0) {
            intent.putExtra("obj_song_baihat", mangbaihat);
        }
        startService(intent);
    }

    private void handleMusic(int action) {
        switch (action) {
            case ForegroundServiceControl.ACTION_PAUSE:
                imageButtonplaypausenhac.setImageResource(R.drawable.nutpause);
                break;
            case ForegroundServiceControl.ACTION_RESUME:
                imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                break;
            case ForegroundServiceControl.ACTION_NEXT:
                completeNextMusic();
                break;
            case ForegroundServiceControl.ACTION_PREVIOUS:
                completePreviousMusic();
                break;
        }
    }

    private void enventClick() {
        imageButtonplaypausenhac.setOnClickListener(view -> {
            if (isplaying) {
                sendActionToService(ForegroundServiceControl.ACTION_PAUSE);
                imageButtonplaypausenhac.setImageResource(R.drawable.nutpause);
            } else {
                sendActionToService(ForegroundServiceControl.ACTION_RESUME);
                imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
            }
        });
        imageButtonlapnhac.setOnClickListener(this::onClick);
        imageButtontronnhac.setOnClickListener(view -> {
            if (!checkrandom) {
                if (repeat) {
                    repeat = false;
                    imageButtontronnhac.setImageResource(R.drawable.iconshuffled);
                    imageButtonlapnhac.setImageResource(R.drawable.iconrepeat);
                } else {
                    imageButtontronnhac.setImageResource(R.drawable.iconshuffled);
                }
                checkrandom = true;
            } else {
                imageButtontronnhac.setImageResource(R.drawable.iconsuffle);
                checkrandom = false;
            }
            sendActionToService(ForegroundServiceControl.ACTION_RANDOM);
        });
        seekBarnhac.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                durationToService = seekBar.getProgress();
                sendActionToService(ForegroundServiceControl.ACTION_DURATION);
            }
        });
        imageButtonnexnhac.setOnClickListener(view -> sendActionToService(ForegroundServiceControl.ACTION_NEXT));
        imageButtonpreviewnhac.setOnClickListener(view -> sendActionToService(ForegroundServiceControl.ACTION_PREVIOUS));
        toolbarplaynhac.setNavigationOnClickListener(view -> {
            mangbaihat.clear();
            finish();
        });
    }

    private void completePreviousMusic() {
        if (mangbaihat.size() > 0) {
            PreviousMusic();
        }
    }

    private void completeNextMusic() {
        if (mangbaihat.size() > 0) {
            NextMusic();
        }
    }

    private void NextMusic() {
        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
        timeValue = 0;
    }

    private void PreviousMusic() {
        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
        timeValue = 0;
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                ArtistService dataservice = ApiUtil.getArtistService();
                retrofit2.Call<List<Song>> callback = dataservice.getSongById(5);
                callback.enqueue(new Callback<List<Song>>() {
                    @Override
                    public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                        mangbaihat = (ArrayList<Song>) response.body();
                    }

                    @Override
                    public void onFailure(Call<List<Song>> call, Throwable t) {

                    }
                });
            }
        }
    }

    private void sendActionToService(int action) {
        Intent intent = new Intent(this, ForegroundServiceControl.class);
        intent.putExtra("action_music_service", action);
        intent.putExtra("duration", durationToService);
        intent.putExtra("repeat_music", repeat);
        intent.putExtra("random_music", checkrandom);
        startService(intent);
    }


    private void TimeSong() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewtatoltime.setText(simpleDateFormat.format(duration));
        seekBarnhac.setMax(duration);
    }


    private void AnhXa() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        seekBarnhac = findViewById(R.id.seekBartime);
        ViewPager viewPagerplaynhac = findViewById(R.id.viewPagerdianhac);
        imageViewtim = findViewById(R.id.imageViewtimplaynhac);
        imageButtontronnhac = findViewById(R.id.imageButtontron);
        imageButtonpreviewnhac = findViewById(R.id.imageButtonpreview);
        imageButtonplaypausenhac = findViewById(R.id.imageButtonplaypause);
        imageButtonnexnhac = findViewById(R.id.imageButtonnext);
        imageButtonlapnhac = findViewById(R.id.imageButtonlap);
        textViewtatoltime = findViewById(R.id.textViewtimetotal);
        textViewcasi = findViewById(R.id.textViewtencasiplaynhac);
        textViewtennhac = findViewById(R.id.textViewtenbaihatplaynhac);
        textViewrunrime = findViewById(R.id.textViewruntime);
        fragment_dia_nhac = new Fragment_SongCd();
        adapternhac = new ViewPagerSongCd(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_dia_nhac);
        viewPagerplaynhac.setAdapter(adapternhac);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setTitleTextColor(Color.BLACK);
        fragment_dia_nhac = (Fragment_SongCd) adapternhac.getItem(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    private void onClick(View view) {
        if (!repeat) {
            if (checkrandom) {
                checkrandom = false;
                imageButtonlapnhac.setImageResource(R.drawable.iconsyned);
                imageButtontronnhac.setImageResource(R.drawable.iconsuffle);
            } else {
                imageButtonlapnhac.setImageResource(R.drawable.iconsyned);
            }
            repeat = true;
        } else {
            imageButtonlapnhac.setImageResource(R.drawable.iconrepeat);
            repeat = false;
        }
        sendActionToService(ForegroundServiceControl.ACTION_REPEAT);
    }
}
