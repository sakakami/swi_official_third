package com.switube.www.landmark2018test.youtube;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.switube.www.landmark2018test.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;
import java.util.Set;

public class YouTubePlayer extends WebView {
    @NonNull
    private Set<YouTubeListener> youTubeListeners;
    @NonNull
    private final Handler mainThreadHandler;

    public YouTubePlayer(Context context) {
        this(context, null);
    }

    public YouTubePlayer(Context context, AttributeSet attrs) {
        super(context, attrs);

        mainThreadHandler = new Handler(Looper.getMainLooper());
        youTubeListeners = new HashSet<>();
    }

    protected boolean addListener(YouTubeListener listener) {
        return youTubeListeners.add(listener);
    }

    protected void seekTo(final int time) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                loadUrl("javascript:seekTo(" + time + ")");
            }
        });
    }

    @NonNull
    protected Set<YouTubeListener> getYouTubeListeners() {
        return youTubeListeners;
    }

    protected void initialize(@NonNull YouTubeListener youTubeListener) {
        this.youTubeListeners.add(youTubeListener);

        WebSettings settings = this.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setMediaPlaybackRequiresUserGesture(false);
        this.addJavascriptInterface(new YouTubePlayerBridge(this), "YouTubePlayerBridge");
        this.loadDataWithBaseURL("https://www.youtube.com", getVideoPlayerHTML(), "text/html", "utf-8", null);
        this.setWebChromeClient(new WebChromeClient() {
            @Override
            public Bitmap getDefaultVideoPoster() {
                Bitmap result = null;
                try {
                    result = super.getDefaultVideoPoster();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (result == null) {
                    return Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
                } else {
                    return result;
                }
            }
        });
    }

    protected void loadVideo(final String videoId, final float startSeconds) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                loadUrl("javascript:loadVideo('" + videoId + "'," + startSeconds + ")");
            }
        });
    }

    protected void pause() {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                loadUrl("javascript:pauseVideo()");
            }
        });
    }

    protected void play() {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                loadUrl("javascript:playVideo()");
            }
        });
    }

    @NonNull
    private String getVideoPlayerHTML() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.player);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String read;
            StringBuilder stringBuilder = new StringBuilder("");

            while ((read = bufferedReader.readLine()) != null) {
                stringBuilder.append(read).append("\n");
            }

            inputStream.close();

            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    interface YouTubeListener {
        void onReady();
        void onStateChange(@State.YouTubePlayerState int state);
        void onPlaybackQualityChange(@PlaybackQuality.Quality int playbackQuality);
        void onPlaybackRateChange(double rate);
        void onError(@Error.PlayerError int error);
        void onApiChange();
        void onCurrentSecond(float second);
        void onVideoDuration(float duration);
        void onLog(String log);
        void onVideoTitle(String videoTitle);
        void onVideoId(String videoId);

    }

    static class Error {
        final static int INVALID_PARAMENTER_IN_REQUEST = 0;
        final static int HTML_5_PLAYER = 1;
        final static int VIDEO_NOT_FOUND = 2;
        final static int VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER = 3;

        @IntDef({INVALID_PARAMENTER_IN_REQUEST, HTML_5_PLAYER, VIDEO_NOT_FOUND, VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER})
        @Retention(RetentionPolicy.SOURCE)
        @interface PlayerError {}
    }

    static class State {
        final static int UNSTARTED = -1;
        final static int ENDED = 0;
        final static int PLAYING = 1;
        final static int PAUSED = 2;
        final static int BUFFERING = 3;
        final static int VIDEO_CUED = 5;

        @IntDef({UNSTARTED, ENDED, PLAYING, PAUSED, BUFFERING, VIDEO_CUED})
        @Retention(RetentionPolicy.SOURCE)
        @interface YouTubePlayerState {}
    }

    static class PlaybackQuality {
        final static int SMALL = 0;
        final static int MEDIUM = 1;
        final static int LARGE = 2;
        final static int HD720 = 3;
        final static int HD1080 = 4;
        final static int HIGH_RES = 5;
        final static int DEFAULT = -1;

        @IntDef({SMALL, MEDIUM, LARGE, HD720, HD1080, HIGH_RES, DEFAULT})
        @Retention(RetentionPolicy.SOURCE)
        @interface Quality {}
    }
}
