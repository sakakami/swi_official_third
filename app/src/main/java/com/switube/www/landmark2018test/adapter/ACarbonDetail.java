package com.switube.www.landmark2018test.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.PolylineOptions;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.entity.ECarbonDetail;
import com.switube.www.landmark2018test.util.LineChartManager;

import java.util.ArrayList;

public class ACarbonDetail extends RecyclerView.Adapter<ACarbonDetail.ViewHolder> {
    private ArrayList<ECarbonDetail> list;
    private boolean isFirstA = true;
    private boolean isFirstB = true;

    public ACarbonDetail() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_carbon, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        switch (position) {
            case 0:
                holder.map.setVisibility(View.VISIBLE);
                holder.lineChart.setVisibility(View.GONE);
                holder.layoutA.setVisibility(View.GONE);
                holder.layoutB.setVisibility(View.GONE);
                holder.layoutC.setVisibility(View.GONE);
                break;
            case 1:
                holder.map.setVisibility(View.GONE);
                holder.lineChart.setVisibility(View.VISIBLE);
                holder.layoutA.setVisibility(View.GONE);
                holder.layoutB.setVisibility(View.GONE);
                holder.layoutC.setVisibility(View.GONE);
                if (isFirstA) {
                    LineChartManager lineChartManagerA = new LineChartManager(holder.lineChart);
                    lineChartManagerA.setEntryData(list.get(1).getSpdEnList(), "速度");
                    isFirstA = false;
                }
                break;
            case 2:
                holder.map.setVisibility(View.GONE);
                holder.lineChart.setVisibility(View.VISIBLE);
                holder.layoutA.setVisibility(View.GONE);
                holder.layoutB.setVisibility(View.GONE);
                holder.layoutC.setVisibility(View.GONE);
                if (isFirstB) {
                    LineChartManager lineChartManagerB = new LineChartManager(holder.lineChart);
                    lineChartManagerB.setEntryData(list.get(2).getCbEnList(), "碳足跡");
                    isFirstB = false;
                }
                break;
            case 3:
                holder.map.setVisibility(View.GONE);
                holder.lineChart.setVisibility(View.GONE);
                holder.layoutA.setVisibility(View.VISIBLE);
                holder.layoutB.setVisibility(View.VISIBLE);
                holder.layoutC.setVisibility(View.GONE);
                holder.textRight.setText(String.format("%s公克", list.get(position).getRight()));
                holder.textCenter.setText(list.get(position).getCenter());
                holder.textLeft.setText(String.format("%sKm", list.get(position).getLift()));
                break;
            default:
                holder.map.setVisibility(View.GONE);
                holder.lineChart.setVisibility(View.GONE);
                holder.layoutA.setVisibility(View.GONE);
                holder.layoutB.setVisibility(View.GONE);
                holder.layoutC.setVisibility(View.VISIBLE);
                holder.textName.setText(list.get(position).getName());
                holder.textDis.setText(list.get(position).getDis());
                holder.textTool.setText(list.get(position).getTool());
                holder.textCarbon.setText(list.get(position).getCarbon());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refresh(ArrayList<ECarbonDetail> eCarbonDetails) {
        list.clear();
        list.addAll(eCarbonDetails);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
        MapView map;
        GoogleMap googleMap;
        LineChart lineChart;
        RelativeLayout layoutC;
        TextView textName;
        TextView textDis;
        TextView textTool;
        TextView textCarbon;
        LinearLayout layoutA;
        LinearLayout layoutB;
        TextView textRight;
        TextView textCenter;
        TextView textLeft;

        public ViewHolder(View itemView) {
            super(itemView);
            map = itemView.findViewById(R.id.mapInItemCarbon);
            lineChart = itemView.findViewById(R.id.lineChartInItemCarbon);
            layoutA = itemView.findViewById(R.id.layoutAInItemCarbon);
            layoutB = itemView.findViewById(R.id.layoutBInItemCarbon);
            layoutC = itemView.findViewById(R.id.layoutCInItemCarbon);
            textLeft = itemView.findViewById(R.id.textLeftUpInLayoutB);
            textCenter = itemView.findViewById(R.id.textCenterUpInLayoutB);
            textRight = itemView.findViewById(R.id.textRightUpInLayoutB);
            textName = itemView.findViewById(R.id.textNameInLayoutC);
            textDis = itemView.findViewById(R.id.textDistanceInLayoutC);
            textTool = itemView.findViewById(R.id.textToolsInLayoutC);
            textCarbon = itemView.findViewById(R.id.textCarbonInLayoutC);
            if (map != null) {
                map.onCreate(null);
                map.getMapAsync(this);
                map.onResume();
            }
        }

        @Override
        public void onMapReady(final GoogleMap googleMap) {
            MapsInitializer.initialize(MyApplication.getInstance());
            this.googleMap = googleMap;
            final PolylineOptions polylineOptions = new PolylineOptions().color(Color.RED);
            for (int i = 0; i < list.get(0).getLatLngs().size(); i++) {
                polylineOptions.add(list.get(0).getLatLngs().get(i));
            }
            googleMap.addPolyline(polylineOptions);
            int index = polylineOptions.getPoints().size() / 3;
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(list.get(0).getLatLngs().get(index), 8));
        }
    }
}
