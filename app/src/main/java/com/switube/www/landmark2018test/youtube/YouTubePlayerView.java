package com.switube.www.landmark2018test.youtube;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.switube.www.landmark2018test.MainActivity;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;

public class YouTubePlayerView extends RelativeLayout {
    private final YouTubePlayer youTubePlayer;
    private final YouTubePlayerController youTubePlayerController;

    public static float ENDSEC = 0;
    public YouTubePlayerView(Context context) {
        super(context);
        youTubePlayer = new YouTubePlayer(getContext());
        addView(youTubePlayer,
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        youTubePlayerController = new YouTubePlayerController(getContext(),
                this,
                inflate(getContext(), R.layout.layout_youtube, this));
        youTubePlayer.addListener(youTubePlayerController);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            int sixteenNineHeight = View
                    .MeasureSpec
                    .makeMeasureSpec(View.MeasureSpec.getSize(widthMeasureSpec) * 9 / 16, MeasureSpec.EXACTLY
                    );
            super.onMeasure(widthMeasureSpec, sixteenNineHeight);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void initialized(final String videoId, final float startSecond) {
        youTubePlayer.initialize(new YouTubePlayer.YouTubeListener() {
            @Override
            public void onReady() {
                loadVideo(videoId, startSecond);
            }

            @Override
            public void onStateChange(@YouTubePlayer.State.YouTubePlayerState final int state) {
                if (state == YouTubePlayer.State.ENDED) {
                    nextVideo();
                }
                if (state == YouTubePlayer.State.PLAYING) {
                    if (!MyApplication.getAppData().getiFloatPlayerService().getCheckFloatCanSee()) {
                        MyApplication.getAppData().getiFloatPlayerService().switchPlayMode(false);
                        MyApplication.getAppData().setPlaying(false);
                    } else {
                        MyApplication.getAppData().setPlaying(true);
                    }
                }
            }

            @Override
            public void onPlaybackQualityChange(@YouTubePlayer.PlaybackQuality.Quality int playbackQuality) {}

            @Override
            public void onPlaybackRateChange(double rate) {}

            @Override
            public void onError(@YouTubePlayer.Error.PlayerError int error) {
                if (error == YouTubePlayer.Error.VIDEO_NOT_FOUND || error == YouTubePlayer.Error.VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER) {
                    nextVideo();
                }
            }

            @Override
            public void onApiChange() {}

            @Override
            public void onCurrentSecond(float second) {}

            @Override
            public void onVideoDuration(float duration) {
                ENDSEC = duration;
            }

            @Override
            public void onLog(String log) {}

            @Override
            public void onVideoTitle(String videoTitle) {}

            @Override
            public void onVideoId(String videoId) {}
        });
    }

    public void loadVideo(String videoId, float startSecond) {
        youTubePlayer.loadVideo(videoId, startSecond);
    }

    public void playVideo() {
        youTubePlayer.play();
        MyApplication.getAppData().setPlaying(true);
    }

    public void pauseVideo() {
        youTubePlayer.pause();
        MyApplication.getAppData().setPlaying(false);
    }

    public void nextVideo() {
        int index = MyApplication.getAppData().getPlayingIndex();
        int size = MyApplication.getAppData().getMusicEntities().size();
        if (index + 1 < size) {
            MyApplication.getAppData().setPlayingIndex(index + 1);
        } else {
            MyApplication.getAppData().setPlayingIndex(0);
        }
        index = MyApplication.getAppData().getPlayingIndex();
        String videoId = MyApplication.getAppData().getMusicEntities().get(index).getStid();
        loadVideo(videoId, 0);
        if (MyApplication.getAppData().isCanRefreshList()) {
            ((MainActivity)getContext()).handleRefreshList();
        }
    }

    public void previousVideo() {
        int index = MyApplication.getAppData().getPlayingIndex();
        int size = MyApplication.getAppData().getMusicEntities().size();
        if (index - 1 >= 0) {
            MyApplication.getAppData().setPlayingIndex(index - 1);
        } else {
            MyApplication.getAppData().setPlayingIndex(size - 1);
        }
        index = MyApplication.getAppData().getPlayingIndex();
        String videoId = MyApplication.getAppData().getMusicEntities().get(index).getStid();
        loadVideo(videoId, 0);
        if (MyApplication.getAppData().isCanRefreshList()) {
            ((MainActivity)getContext()).handleRefreshList();
        }
    }

    public void releasePlayer() {
        youTubePlayer.destroy();
    }

    public void seekTo(int time) {
        youTubePlayer.seekTo(time);
    }

    public void fullScreen() {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        setLayoutParams(params);
    }

    public void handleCloseTopLayout() {
        youTubePlayerController.handleCloseTopLayout();
    }
}
