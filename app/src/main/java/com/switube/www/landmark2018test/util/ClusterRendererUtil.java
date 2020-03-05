package com.switube.www.landmark2018test.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.google.maps.android.ui.SquareTextView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.entity.EClusterItem;

public class ClusterRendererUtil extends DefaultClusterRenderer<EClusterItem> {
    private IconGenerator iconGenerator;
    private SparseArray<BitmapDescriptor> sparseArray = new SparseArray<>();
    private ShapeDrawable coloredCircleBackground;
    private float density;
    public ClusterRendererUtil(Context context, GoogleMap map, ClusterManager<EClusterItem> clusterManager) {
        super(context, map, clusterManager);
        density = context.getResources().getDisplayMetrics().density;
        iconGenerator = new IconGenerator(context);
        iconGenerator.setContentView(makeSquareTextVie(context));
        iconGenerator.setTextAppearance(R.style.amu_ClusterIcon_TextAppearance);
        iconGenerator.setBackground(makeClusterBackground());
    }

    @Override
    protected void onBeforeClusterItemRendered(EClusterItem item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
        if (!MyApplication.getAppData().isDefaultHotKye() && MyApplication.getAppData().getHotKeyMode() == 1 && MyApplication.getAppData().isMobileMusicMode()) {
            if (item.getLike().equals("1")) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_r_r_1_1));
            } else {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_r_b_1_1));
            }
            if (MyApplication.getAppData().getWebId().equals(item.getSpid())) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_r_o_1_1));
            }
        } else {
            if (item.getMsid().equals("ja")) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.bike_pin));
            } else {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_v1_3));
            }
        }
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<EClusterItem> cluster, MarkerOptions markerOptions) {
        super.onBeforeClusterRendered(cluster, markerOptions);
        int bucket = getBucket(cluster);
        BitmapDescriptor bitmapDescriptor = sparseArray.get(bucket);
        if (bitmapDescriptor == null) {
            coloredCircleBackground.getPaint().setColor(Color.parseColor("#ff8000"));
            bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon(getClusterText(bucket)));
            sparseArray.put(bucket, bitmapDescriptor);
        }
        markerOptions.icon(bitmapDescriptor);
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster<EClusterItem> cluster) {
        return cluster.getSize() > 2;
    }

    @Override
    protected int getBucket(Cluster<EClusterItem> cluster) {
        int size = cluster.getSize();
        if (size < 1000) {
            return size;
        } else {
            return 999;
        }
    }

    @Override
    protected String getClusterText(int bucket) {
        if (bucket < 1000) {
            return String.valueOf(bucket);
        } else {
            return "999+";
        }
    }

    private SquareTextView makeSquareTextVie(Context context) {
        SquareTextView squareTextView = new SquareTextView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        squareTextView.setLayoutParams(layoutParams);
        squareTextView.setId(R.id.amu_text);
        int twelveDpi = (int)(12 * density);
        squareTextView.setPadding(twelveDpi, twelveDpi, twelveDpi, twelveDpi);
        return squareTextView;
    }

    private LayerDrawable makeClusterBackground() {
        coloredCircleBackground = new ShapeDrawable(new OvalShape());
        ShapeDrawable outline = new ShapeDrawable(new OvalShape());
        outline.getPaint().setColor(0x80ffffff);
        LayerDrawable background = new LayerDrawable(new Drawable[]{outline, coloredCircleBackground});
        int strokeWidth = (int)(3 * density);
        background.setLayerInset(1, strokeWidth, strokeWidth, strokeWidth, strokeWidth);
        return background;
    }
}
