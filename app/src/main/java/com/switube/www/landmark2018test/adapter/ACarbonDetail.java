package com.switube.www.landmark2018test.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ACarbonDetail extends RecyclerView.Adapter<ACarbonDetail.ViewHolder> {
    private ArrayList<ECarbonDetail> list;
    //private DynamicLineChartManager lineChartManagerA;
    //private DynamicLineChartManager lineChartManagerB;
    private LineChartManager lineChartManagerA;
    private LineChartManager lineChartManagerB;
    private Activity activity;
    private DecimalFormat df = new DecimalFormat("0.0");

    public ACarbonDetail(Activity activity) {
        list = new ArrayList<>();
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_carbon, parent, false));
    }

    private int indexA = 0;
    private int indexB = 0;
    private int indexC = 0;
    //GoogleMap map;

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        switch (position) {
            case 0:
                holder.map.setVisibility(View.VISIBLE);
                holder.lineChart.setVisibility(View.GONE);
                holder.layoutA.setVisibility(View.GONE);
                holder.layoutB.setVisibility(View.GONE);
                holder.layoutC.setVisibility(View.GONE);
                //map = holder.googleMap;
                /*if (map != null) {
                    final PolylineOptions polylineOptions = new PolylineOptions().color(Color.RED);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < list.get(0).getLatLngs().size(); i++) {
                                indexC = i;
                                polylineOptions.add(list.get(0).getLatLngs().get(i));
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    Log.e("A", e.toString());
                                }
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (indexC == 0) {
                                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(list.get(0).getLatLngs().get(indexC), 15));
                                        }
                                        map.addPolyline(polylineOptions);
                                    }
                                });
                            }
                        }
                    }).start();
                }*/
                break;
            case 1:
                holder.map.setVisibility(View.GONE);
                holder.lineChart.setVisibility(View.VISIBLE);
                holder.layoutA.setVisibility(View.GONE);
                holder.layoutB.setVisibility(View.GONE);
                holder.layoutC.setVisibility(View.GONE);
                lineChartManagerA = new LineChartManager(holder.lineChart, "速度");
                lineChartManagerA.setEntryData(list.get(1).getSpdEnList());
                //lineChartManagerA = new DynamicLineChartManager(holder.lineChart, "速度", Color.BLUE, list.get(1).getSpdEnList(), list.get(1).getDisForString());
                //lineChartManagerA.setYAxis(150, 0, list.get(1).getSpeeds().size());
                //int sizeDisA = list.get(1).getDistanceAdd().size() - 1;
                //lineChartManagerA.setxAxis(Float.parseFloat(String.valueOf(list.get(1).getDistanceAdd().get(sizeDisA))), 0, list.get(1).getDistanceAdd().size());
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < list.get(1).getSpeeds().size(); i++) {
                            indexA = i;
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                Log.e("A", e.toString());
                            }
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    double dis = list.get(1).getDistanceAdd().get(indexA) / 1000;
                                    double disF = Double.parseDouble(df.format(dis));
                                    lineChartManagerA.addEntry(list.get(1).getSpeeds().get(indexA), disF);
                                }
                            });
                        }
                    }
                }).start();*/
                break;
            case 2:
                holder.map.setVisibility(View.GONE);
                holder.lineChart.setVisibility(View.VISIBLE);
                holder.layoutA.setVisibility(View.GONE);
                holder.layoutB.setVisibility(View.GONE);
                holder.layoutC.setVisibility(View.GONE);
                lineChartManagerB = new LineChartManager(holder.lineChart, "碳足跡");
                lineChartManagerB.setEntryData(list.get(2).getCbEnList());
                //lineChartManagerB = new DynamicLineChartManager(holder.lineChart, "碳足跡", Color.BLUE, list.get(2).getCbEnList(), list.get(2).getDisForString());
                //lineChartManagerB.setYAxis(20, 0, list.get(2).getCarbons().size());
                //int sizeDisB = list.get(2).getDistanceAdd().size() - 1;
                //lineChartManagerB.setxAxis(Float.parseFloat(String.valueOf(list.get(2).getDistanceAdd().get(sizeDisB))), 0, list.get(2).getDistanceAdd().size());
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < list.get(2).getCarbons().size(); i++) {
                            indexB = i;
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                Log.e("B", e.toString());
                            }
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    double dis = list.get(2).getDistanceAdd().get(indexB) / 1000;
                                    double disF = Double.parseDouble(df.format(dis));
                                    lineChartManagerB.addEntry(list.get(2).getCarbons().get(indexB), disF);
                                }
                            });
                        }
                    }
                }).start();*/
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

    /*@Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        if (holder.googleMap != null) {
            holder.googleMap.clear();
            holder.googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        }
    }*/

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refresh(ArrayList<ECarbonDetail> eCarbonDetails) {
        list.clear();
        list.addAll(eCarbonDetails);
        Log.e("adapter", "list size " + list.size());
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
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(list.get(0).getLatLngs().get(0), 11));
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < list.get(0).getLatLngs().size(); i++) {
                        indexC = i;
                        polylineOptions.add(list.get(0).getLatLngs().get(i));
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            Log.e("C", e.toString());
                        }
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (indexC == 0) {
                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(list.get(0).getLatLngs().get(0), 15));
                                }
                                googleMap.addPolyline(polylineOptions);
                            }
                        });
                    }
                }
            }).start();*/
        }
    }
}
