package com.switube.www.landmark2018test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;

import pl.droidsonroids.gif.GifTextView;

public class VSlide extends RelativeLayout implements ScaleGestureDetector.OnScaleGestureListener {
    private static int baseSize = 0;
    private ScaleGestureDetector scaleGestureDetector;
    private ImageView imageBase;
    private ImageView imageHint;
    private GifTextView textSlide;
    private TextView textTop;
    private TextView textBottom;
    private TextView textRight;
    private TextView textLeft;
    private int pointX = 0;
    private int pointY = 0;
    private float lengthOld = 0f;
    private float lengthNew = 0f;
    private boolean canScale = true;

    public VSlide(Context context, AttributeSet attrs) {
        super(context, attrs);
        scaleGestureDetector = new ScaleGestureDetector(context, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imageBase = findViewById(R.id.imageBaseInPlayer);
        imageHint = findViewById(R.id.imageHintInPlayer);
        textSlide = findViewById(R.id.imageSlideInPlayer);
        textTop = findViewById(R.id.textUpInPlayer);
        textBottom = findViewById(R.id.textDownInPlayer);
        textRight = findViewById(R.id.textRightInPlayer);
        textLeft = findViewById(R.id.textLeftInPlayer);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        baseSize = getHeight() / 5;
        switch (MyApplication.getAppData().getSlideMode()) {
            case 0:
                MyApplication.getAppData().setSlideSize(baseSize);
                break;
            case 1:
                MyApplication.getAppData().setSlideSize((int) (baseSize * 1.5));
                break;
            case 2:
                MyApplication.getAppData().setSlideSize(baseSize * 2);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        textSlide.layout(pointX, pointY, pointX + MyApplication.getAppData().getSlideSize(), pointY + MyApplication.getAppData().getSlideSize());
        textSlide.setMaxWidth(MyApplication.getAppData().getSlideSize());
        textSlide.setMaxHeight(MyApplication.getAppData().getSlideSize());
    }

    @Override
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        lengthOld = scaleGestureDetector.getPreviousSpan();
        lengthNew = scaleGestureDetector.getCurrentSpan();
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        if (canScale) {
            if (lengthNew > lengthOld) {
                handleZoom("zoomOut");
            } else if (lengthOld > lengthNew) {
                handleZoom("zoomIn");
            }
        }
    }

    public void handleMoveSlide(int x, int y) {
        int finalX;
        int finalY;
        int rangeWidth = MyApplication.getAppData().getRangeWith();
        int rangeHeight = MyApplication.getAppData().getRangeHeight();
        if (x <= MyApplication.getAppData().getSlideSize() / 2) {
            pointX = 0;
        } else if (x >= rangeWidth - MyApplication.getAppData().getSlideSize()) {
            pointX = rangeWidth - MyApplication.getAppData().getSlideSize();
        } else {
            pointX = x - MyApplication.getAppData().getSlideSize() / 2;
        }
        if (y <= MyApplication.getAppData().getSlideSize() / 2) {
            pointY = 0;
        } else if (y >= rangeHeight - MyApplication.getAppData().getSlideSize()) {
            pointY = rangeHeight - MyApplication.getAppData().getSlideSize();
        } else {
            pointY = y - MyApplication.getAppData().getSlideSize() / 2;
        }
        finalX = pointX + MyApplication.getAppData().getSlideSize();
        finalY = pointY + MyApplication.getAppData().getSlideSize();
        textSlide.setWidth(MyApplication.getAppData().getSlideSize());
        textSlide.setHeight(MyApplication.getAppData().getSlideSize());
        textSlide.layout(pointX, pointY, finalX, finalY);
    }

    public void handleHintVisibility() {
        if (MyApplication.getAppData().getgPlayer().getSmartData().getWtype().equals("1")) {
            textTop.setVisibility(GONE);
            textBottom.setVisibility(GONE);
            textLeft.setVisibility(GONE);
            textRight.setVisibility(GONE);
            imageHint.setVisibility(VISIBLE);
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    imageHint.setImageResource(R.drawable.bg_square_index_tw_v2_2x);
                    break;
                case 2:
                    imageHint.setImageResource(R.drawable.bg_square_index_ch_v2_2x);
                    break;
                case 3:
                    imageHint.setImageResource(R.drawable.bg_square_index_jp_v2_2x);
                    break;
                default:
                    imageHint.setImageResource(R.drawable.bg_square_index_en_v2_2x);
                    break;
            }
        } else {
            imageHint.setVisibility(GONE);
            textTop.setVisibility(VISIBLE);
            textBottom.setVisibility(VISIBLE);
            textRight.setVisibility(VISIBLE);
            textLeft.setVisibility(VISIBLE);
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    textTop.setText(MyApplication.getAppData().getgPlayer().getSmartData().getU_tw());
                    textBottom.setText(MyApplication.getAppData().getgPlayer().getSmartData().getD_tw());
                    textLeft.setText(MyApplication.getAppData().getgPlayer().getSmartData().getL_tw());
                    textRight.setText(MyApplication.getAppData().getgPlayer().getSmartData().getR_tw());
                    break;
                case 2:
                    textTop.setText(MyApplication.getAppData().getgPlayer().getSmartData().getU_ch());
                    textBottom.setText(MyApplication.getAppData().getgPlayer().getSmartData().getD_ch());
                    textLeft.setText(MyApplication.getAppData().getgPlayer().getSmartData().getL_ch());
                    textRight.setText(MyApplication.getAppData().getgPlayer().getSmartData().getR_ch());
                    break;
                case 3:
                    textTop.setText(MyApplication.getAppData().getgPlayer().getSmartData().getU_jp());
                    textBottom.setText(MyApplication.getAppData().getgPlayer().getSmartData().getD_jp());
                    textLeft.setText(MyApplication.getAppData().getgPlayer().getSmartData().getL_jp());
                    textRight.setText(MyApplication.getAppData().getgPlayer().getSmartData().getR_jp());
                    break;
                default:
                    textTop.setText(MyApplication.getAppData().getgPlayer().getSmartData().getU_en());
                    textBottom.setText(MyApplication.getAppData().getgPlayer().getSmartData().getD_en());
                    textLeft.setText(MyApplication.getAppData().getgPlayer().getSmartData().getL_en());
                    textRight.setText(MyApplication.getAppData().getgPlayer().getSmartData().getR_en());
                    break;
            }
        }
    }

    public void handleChangeBaseResource(int resource) {
        Glide.with(MyApplication.getInstance())
                .asGif()
                .load(resource)
                .into(imageBase);
    }

    public void handleZoom(String mode) {
        if (mode.equals("zoomIn")) {
            if (MyApplication.getAppData().getSlideMode() == 2) {
                MyApplication.getAppData().setSlideSize((int) (baseSize * 1.5));
                MyApplication.getAppData().setSlideMode(1);
            } else {
                MyApplication.getAppData().setSlideSize(baseSize);
                MyApplication.getAppData().setSlideMode(0);
            }
        } else {
            if (MyApplication.getAppData().getSlideMode() == 0) {
                MyApplication.getAppData().setSlideSize((int) (baseSize * 1.5));
                MyApplication.getAppData().setSlideMode(1);
            } else {
                MyApplication.getAppData().setSlideSize(baseSize * 2);
                MyApplication.getAppData().setSlideMode(2);
            }
        }
        int finalX = pointX + MyApplication.getAppData().getSlideSize();
        int finalY = pointY + MyApplication.getAppData().getSlideSize();
        int rangeWidth = MyApplication.getAppData().getRangeWith();
        int rangeHeight = MyApplication.getAppData().getRangeHeight();
        if (finalX > rangeWidth) {
            pointX = rangeWidth - MyApplication.getAppData().getSlideSize();
        }
        if (finalY > rangeHeight) {
            pointY = rangeHeight - MyApplication.getAppData().getSlideSize();
        }
        textSlide.layout(pointX, pointY, pointX + MyApplication.getAppData().getSlideSize(), pointY + MyApplication.getAppData().getSlideSize());
        textSlide.setWidth(MyApplication.getAppData().getSlideSize());
        textSlide.setHeight(MyApplication.getAppData().getSlideSize());
    }

    public void handleEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
    }

    public void handleChangeSlidePhoto(boolean b) {
        if (b) {
            canScale = true;
            textSlide.setBackgroundResource(R.drawable.btn_bean_3x);
            textSlide.setText("");
        } else {
            canScale = false;
            textSlide.setBackgroundResource(R.drawable.btn_play_all_v1);
            String temp = "All";
            textSlide.setText(temp);
            textSlide.setPadding(10, 50, 0, 0);
            MyApplication.getAppData().setSlideMode(0);
            handleZoom("zoomOut");
        }
    }
}
