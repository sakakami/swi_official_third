package com.switube.www.landmark2018test.youtube;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.webkit.JavascriptInterface;

public class YouTubePlayerBridge {
    @NonNull
    private final YouTubePlayer youTubePlayer;
    @NonNull
    private final Handler mainThreadHandler;

    public YouTubePlayerBridge(@NonNull YouTubePlayer youTubePlayer) {
        this.youTubePlayer = youTubePlayer;
        mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    @JavascriptInterface
    public void currentSeconds(final String seconds) {
        final float fSeconds;
        try {
            fSeconds = Float.parseFloat(seconds);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(YouTubePlayer.YouTubeListener listener : youTubePlayer.getYouTubeListeners())
                    listener.onCurrentSecond(fSeconds);
            }
        });
    }

    @JavascriptInterface
    public void onApiChange() {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(YouTubePlayer.YouTubeListener listener : youTubePlayer.getYouTubeListeners())
                    listener.onApiChange();
            }
        });
    }

    @JavascriptInterface
    public void onError(final String error) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(YouTubePlayer.YouTubeListener listener : youTubePlayer.getYouTubeListeners()) {
                    if ("2".equalsIgnoreCase(error))
                        listener.onError(YouTubePlayer.Error.INVALID_PARAMENTER_IN_REQUEST);
                    else if ("5".equalsIgnoreCase(error))
                        listener.onError(YouTubePlayer.Error.HTML_5_PLAYER);
                    else if ("100".equalsIgnoreCase(error))
                        listener.onError(YouTubePlayer.Error.VIDEO_NOT_FOUND);
                    else if ("101".equalsIgnoreCase(error))
                        listener.onError(YouTubePlayer.Error.VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER);
                    else if ("150".equalsIgnoreCase(error))
                        listener.onError(YouTubePlayer.Error.VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER);
                }
            }
        });
    }

    @JavascriptInterface
    public void onReady() {
        for (YouTubePlayer.YouTubeListener listener : youTubePlayer.getYouTubeListeners()) {
            listener.onReady();
        }
    }

    @JavascriptInterface
    public void onLog(final String message) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(YouTubePlayer.YouTubeListener listener : youTubePlayer.getYouTubeListeners())
                    listener.onLog(message);
            }
        });
    }

    @JavascriptInterface
    public void onPlaybackQualityChange(final String quality) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(YouTubePlayer.YouTubeListener listener : youTubePlayer.getYouTubeListeners())
                    if ("small".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(YouTubePlayer.PlaybackQuality.SMALL);
                    else if ("medium".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(YouTubePlayer.PlaybackQuality.MEDIUM);
                    else if ("large".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(YouTubePlayer.PlaybackQuality.LARGE);
                    else if ("hd720".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(YouTubePlayer.PlaybackQuality.HD720);
                    else if ("hd1080".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(YouTubePlayer.PlaybackQuality.HD1080);
                    else if ("highres".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(YouTubePlayer.PlaybackQuality.HIGH_RES);
                    else if ("default".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(YouTubePlayer.PlaybackQuality.DEFAULT);
            }
        });
    }

    @JavascriptInterface
    public void onPlaybackRateChange(final String rate) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    double dRate = Double.parseDouble(rate);
                    for (YouTubePlayer.YouTubeListener listener : youTubePlayer.getYouTubeListeners())
                        listener.onPlaybackRateChange(dRate);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @JavascriptInterface
    public void onStateChange(final String state) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(YouTubePlayer.YouTubeListener listener : youTubePlayer.getYouTubeListeners())
                    if ("UNSTARTED".equalsIgnoreCase(state))
                        listener.onStateChange(YouTubePlayer.State.UNSTARTED);
                    else if ("ENDED".equalsIgnoreCase(state))
                        listener.onStateChange(YouTubePlayer.State.ENDED);
                    else if ("PLAYING".equalsIgnoreCase(state))
                        listener.onStateChange(YouTubePlayer.State.PLAYING);
                    else if ("PAUSED".equalsIgnoreCase(state))
                        listener.onStateChange(YouTubePlayer.State.PAUSED);
                    else if ("BUFFERING".equalsIgnoreCase(state))
                        listener.onStateChange(YouTubePlayer.State.BUFFERING);
                    else if ("CUED".equalsIgnoreCase(state))
                        listener.onStateChange(YouTubePlayer.State.VIDEO_CUED);
            }
        });
    }

    @JavascriptInterface
    public void onVideoDuration(final String seconds) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    float videoDuration = Float.parseFloat(seconds);
                    for (YouTubePlayer.YouTubeListener listener : youTubePlayer.getYouTubeListeners())
                        listener.onVideoDuration(videoDuration);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @JavascriptInterface
    public void onVideoId(final String videoId) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(YouTubePlayer.YouTubeListener listener : youTubePlayer.getYouTubeListeners())
                    listener.onVideoId(videoId);
            }
        });
    }

    @JavascriptInterface
    public void onVideoTitle(final String videoTitle) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(YouTubePlayer.YouTubeListener listener : youTubePlayer.getYouTubeListeners())
                    listener.onVideoTitle(videoTitle);
            }
        });
    }
}
