package com.switube.www.landmark2018test.youtube;

import android.animation.Animator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.switube.www.landmark2018test.MainActivity;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.view.VPlayer;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;

public class YouTubePlayerController implements YouTubePlayer.YouTubeListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable fadeOutRunnable = new Runnable() {
        @Override
        public void run() {
            fadeControls(0f);
        }
    };

    private ImageView mImageRightTop;
    //private ImageView mImageLeftTop;
    private ImageView mImageRightBottom;
    private ImageView mImageCenter;
    private ImageView mImageRightCenter;
    private ImageView mImageLeftCenter;
    private TextView mTextCenterTop;
    private TextView mTextRightBottom;
    private TextView mTextLeftBottom;
    private ProgressBar mProgressBar;
    private SeekBar mSeekBar;
    private RelativeLayout mLayout;
    private View mViewBar;
    private Context mContext;
    private YouTubePlayerView mYouTubePlayerView;

    private boolean mCanFadeControl = false;
    private boolean mIsVisible = true;
    private boolean mTouchSeekBar = false;

    private int mSeekBarProgress = -1;
    private int mStartX = 0;
    private int mStartY = 0;
    private int mMode = 0;

    YouTubePlayerController(Context context, @NonNull YouTubePlayerView youTubePlayerView,
                            @NotNull View view) {
        ButterKnife.bind(this, view);
        mImageRightTop = view.findViewById(R.id.imageRightTopInYouTubePlayer);
        //mImageLeftTop = view.findViewById(R.id.imageLeftTopInYouTubePlayer);
        mImageRightBottom = view.findViewById(R.id.imageRightDownInYouTubePlayer);
        mImageCenter = view.findViewById(R.id.imagePlayInYouTubePlayer);
        mImageRightCenter = view.findViewById(R.id.imageRightCenterInYouTubePlayer);
        mImageLeftCenter = view.findViewById(R.id.imageLeftCenterInYouTubePlayer);
        mTextCenterTop = view.findViewById(R.id.textTitleInYouTubePlayer);
        mTextRightBottom = view.findViewById(R.id.textRightDownInYouTubePlayer);
        mTextLeftBottom = view.findViewById(R.id.textLeftDownInYouTubePlayer);
        mProgressBar = view.findViewById(R.id.progressBarInYouTubePlayer);
        mSeekBar = view.findViewById(R.id.seekBarInYouTubePlayer);
        mLayout = view.findViewById(R.id.layoutInYouTubePlayer);
        mViewBar = view.findViewById(R.id.viewBarInYouTubePlayer);
        mContext = context;
        mYouTubePlayerView = youTubePlayerView;

        //mImageLeftTop.setOnTouchListener(this);
        mImageRightTop.setOnTouchListener(this);
        mImageRightBottom.setOnTouchListener(this);
        mImageCenter.setOnTouchListener(this);
        mImageRightCenter.setOnTouchListener(this);
        mImageLeftCenter.setOnTouchListener(this);
        mViewBar.setOnTouchListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    public static boolean isFirst = true;
    public static boolean canClick = true;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!MyApplication.getAppData().getiFloatPlayerService().getIsFullScreenMode() && !MyApplication.getAppData().isPlayerPage()) {
                    MyApplication.getAppData().getiFloatPlayerService().showFloatPlayerKiller(true);
                    mStartX = (int)motionEvent.getRawX();
                    mStartY = (int)motionEvent.getRawY();
                }
                mMode = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!MyApplication.getAppData().getiFloatPlayerService().checkIsBigMode()) {
                    if (mMode == 0) {
                        if (motionEvent.getRawX() - mStartX > 5 || motionEvent.getRawY() - mStartY > 5) {
                            mMode = 1;
                        }
                    }
                    if (mMode == 1) {
                        MyApplication.getAppData().getiFloatPlayerService().handleFloatPlayerMoving((int)motionEvent.getRawX(), (int)motionEvent.getRawY());
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                switch (view.getId()) {
                    case R.id.viewBarInYouTubePlayer:
                        if (mMode == 0) {
                            if (MyApplication.getAppData().getiFloatPlayerService().checkDeskTop() && !MyApplication.getAppData().getiFloatPlayerService().checkIsBigMode()) {
                                if (isFirst) {
                                    MyApplication.getAppData().getiFloatPlayerService().handleBackFromDeskTop(mContext, true);
                                    isFirst = false;
                                } else {
                                    MyApplication.getAppData().getiFloatPlayerService().handleBackFromDeskTop(mContext, false);
                                    isFirst = true;
                                }

                            } else {
                                if (MyApplication.getAppData().getiFloatPlayerService().checkIsBigMode()) {
                                    toggleControlsVisibility();
                                    startFadeOutViewTimer();
                                } else if (MyApplication.getAppData().isPlaylist()) {
                                    MyApplication.getAppData().setPlaylist(false);
                                    ((MainActivity)mContext).getSupportFragmentManager().popBackStack();
                                } else if (!MyApplication.getAppData().getiFloatPlayerService().checkIsBigMode() &&
                                        !MyApplication.getAppData().isPlayerPage() && mMode != 1 && canClick) {
                                    canClick = false;
                                    MyApplication.getAppData().setBackWithFloat(true);
                                    ((MainActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPlayer(), "Player").commit();
                                }
                            }
                        }
                        break;
                    case R.id.imageRightTopInYouTubePlayer:
                        Toast.makeText(mContext, R.string.float_message_coming_soon, Toast.LENGTH_SHORT).show();
                        break;
                    /*case R.id.imageLeftTopInYouTubePlayer:
                        MyApplication.getAppData().getiFloatPlayerService().handleBeatClicked();
                        break;*/
                    case R.id.imageLeftCenterInYouTubePlayer:
                        mYouTubePlayerView.previousVideo();
                        break;
                    case R.id.imageRightCenterInYouTubePlayer:
                        mYouTubePlayerView.nextVideo();
                        break;
                    case R.id.imagePlayInYouTubePlayer:
                        upDateViewPlaybackState(MyApplication.getAppData().isPlaying());
                        if (MyApplication.getAppData().isPlaying()) {
                            MyApplication.getAppData().setPlaying(false);
                            mYouTubePlayerView.pauseVideo();
                        } else {
                            MyApplication.getAppData().setPlaying(true);
                            mYouTubePlayerView.playVideo();
                        }
                        ((MainActivity)mContext).handleRefreshList();
                        break;
                    case R.id.imageRightDownInYouTubePlayer:
                        if (MyApplication.getAppData().getiFloatPlayerService().getIsFullScreenMode()) {
                            mImageRightBottom.setImageResource(R.drawable.btn_fullscreen_v1_1);
                        } else {
                            mImageRightBottom.setImageResource(R.drawable.btn_smallscreen_1_1);
                        }
                        ((MainActivity) mContext).switchScreenOrientation();
                        break;
                    default:
                        break;
                }
                startFadeOutViewTimer();
                MyApplication.getAppData().getiFloatPlayerService().handleKillFloatPlayer();
                MyApplication.getAppData().getiFloatPlayerService().showFloatPlayerKiller(false);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        mTextLeftBottom.setText(MyApplication.getAppData().getiFloatPlayerService().handleYouTubeTime(i));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mTouchSeekBar = true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mTouchSeekBar = false;
        mYouTubePlayerView.seekTo(seekBar.getProgress());
    }

    @Override
    public void onReady() {}

    @Override
    public void onStateChange(@YouTubePlayer.State.YouTubePlayerState int state) {
        mSeekBarProgress = -1;

        if (state == YouTubePlayer.State.PLAYING || state == YouTubePlayer.State.PAUSED
                || state == YouTubePlayer.State.VIDEO_CUED) {
            mViewBar.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.transparent));
            mImageCenter.setVisibility(View.VISIBLE);
            mImageLeftCenter.setVisibility(View.VISIBLE);
            mImageRightCenter.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
            if (MyApplication.getAppData().getiFloatPlayerService().checkIsBigMode() || MyApplication.getAppData().getiFloatPlayerService().getIsFullScreenMode()) {
                mCanFadeControl = true;
                upDateViewPlaybackState(state == YouTubePlayer.State.PLAYING);
                if (MyApplication.getAppData().isPlaying()) {
                    startFadeOutViewTimer();
                } else {
                    handler.removeCallbacks(fadeOutRunnable);
                }
            } else {
                mCanFadeControl = false;
            }
        } else {
            if (state == YouTubePlayer.State.BUFFERING) {
                mProgressBar.setVisibility(View.VISIBLE);
                mCanFadeControl = false;
            }
            if (state == YouTubePlayer.State.UNSTARTED) {
                mViewBar.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.black));
                mCanFadeControl = false;
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onPlaybackQualityChange(@YouTubePlayer.PlaybackQuality.Quality int playbackQuality) {}

    @Override
    public void onPlaybackRateChange(double rate) {}

    @Override
    public void onError(@YouTubePlayer.Error.PlayerError int error) {}

    @Override
    public void onApiChange() {}

    @Override
    public void onCurrentSecond(float second) {
        if (mTouchSeekBar) {
            return;
        }
        if (mSeekBarProgress > 0 &&
                !MyApplication.getAppData().getiFloatPlayerService().handleYouTubeTime((int)second).equals(MyApplication.getAppData().getiFloatPlayerService().handleYouTubeTime(mSeekBarProgress))) {
            return;
        }
        mSeekBarProgress = -1;
        mSeekBar.setProgress((int)second);
    }

    @Override
    public void onVideoDuration(float duration) {
        mTextRightBottom.setText(MyApplication.getAppData().getiFloatPlayerService().handleYouTubeTime((int)duration));
        mSeekBar.setMax((int)duration);
    }

    @Override
    public void onLog(String log) {}

    @Override
    public void onVideoTitle(String videoTitle) {
        mTextCenterTop.setText(videoTitle);
    }

    @Override
    public void onVideoId(String videoId) {}

    private void upDateViewPlaybackState(boolean playing) {
        mImageCenter.setImageResource(playing ? R.drawable.ic_pause_36dp : R.drawable.ic_play_36dp);
    }

    private void fadeControls(final float alpha) {
        if (!mCanFadeControl) {
            return;
        }

        mIsVisible = alpha != 0f;

        if (alpha == 1f && MyApplication.getAppData().isPlaying()) {
            startFadeOutViewTimer();
        } else {
            handler.removeCallbacks(fadeOutRunnable);
        }

        if (MyApplication.getAppData().getiFloatPlayerService().getIsFullScreenMode()) {
            mImageRightBottom.setImageResource(R.drawable.btn_smallscreen_1_1);
        } else {
            mImageRightBottom.setImageResource(R.drawable.btn_fullscreen_v1_1);
        }

        mLayout.animate()
                .alpha(alpha)
                .setDuration(500)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        if (alpha == 1f) {
                            mLayout.setVisibility(View.VISIBLE);
                            if (MyApplication.getAppData().getiFloatPlayerService().getIsFullScreenMode()) {
                                //mImageLeftTop.setVisibility(View.INVISIBLE);
                                mImageRightTop.setVisibility(View.INVISIBLE);
                            } else {
                                //mImageLeftTop.setVisibility(View.VISIBLE);
                                mImageRightTop.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (alpha == 0f) {
                            mLayout.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {}

                    @Override
                    public void onAnimationRepeat(Animator animation) {}
                })
                .start();
    }

    private void startFadeOutViewTimer() {
        handler.postDelayed(fadeOutRunnable, 2000);
    }

    private void toggleControlsVisibility() {
        float alpha = mIsVisible ? 0f : 1f;
        fadeControls(alpha);
    }

    void handleCloseTopLayout() {
        mIsVisible = false;
        mLayout.setVisibility(View.GONE);
    }
}
