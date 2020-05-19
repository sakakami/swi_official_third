package com.switube.www.landmark2018test;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.switube.www.landmark2018test.gson.CodeContent;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;

public class MyCodeActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_code);
        imageView = findViewById(R.id.imgCodeInMyCodeA);
        BarcodeEncoder encoder = new BarcodeEncoder();
        try {
            String maid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
            String name = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userName", "null");
            StringBuilder builder1 = new StringBuilder();
            for (int i = 0; i < 13; i++) { builder1.append((int)(Math.random() * 10)); }
            CodeContent codeContent = new CodeContent();
            codeContent.setId(builder1.toString());
            codeContent.setMaid(maid);
            codeContent.setName(name);
            codeContent.setMode("0");
            Gson gson = new Gson();
            String qrcode = gson.toJson(codeContent);
            Bitmap bitmap = encoder.encodeBitmap(qrcode, BarcodeFormat.QR_CODE, 250, 250);
            Glide.with(this)
                    .load(bitmap)
                    .into(imageView);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

}
