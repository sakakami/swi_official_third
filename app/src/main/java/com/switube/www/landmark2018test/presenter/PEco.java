package com.switube.www.landmark2018test.presenter;

import android.location.Location;
import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.google.android.gms.maps.model.LatLng;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.entity.ECarbonDetail;
import com.switube.www.landmark2018test.entity.EEco;
import com.switube.www.landmark2018test.entity.StrokeData;
import com.switube.www.landmark2018test.view.callback.IVEco;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PEco {
    private IVEco ivEco;
    private DecimalFormat df = new DecimalFormat("0.00");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/M/d HH:mm:ss:SSS", Locale.getDefault());
    public PEco(IVEco ivEco) { this.ivEco = ivEco; }

    public void init() {
        ArrayList<EEco> eEcos = MyApplication.getAppData().geteEcoList().getList();
        int size = eEcos.size();
        ArrayList<LatLng> latLngs = new ArrayList<>();
        ArrayList<Double> speeds = new ArrayList<>();
        ArrayList<Double> distances = new ArrayList<>();
        ArrayList<Double> carbons = new ArrayList<>();
        ArrayList<Double> spdOutZero = new ArrayList<>();
        ArrayList<Double> disOutZero = new ArrayList<>();
        ArrayList<Double> distancesAdd = new ArrayList<>();
        //計算速度與距離
        for (int i = 0; i < size; i++) {
            double lat = eEcos.get(i).getLatitude();
            double lng = eEcos.get(i).getLongitude();
            LatLng latLng = new LatLng(lat, lng);
            latLngs.add(latLng);
            if (i == 0) {
                distances.add(0.0);
                speeds.add(0.0);
                distancesAdd.add(0.0);
            } else {
                float[] d = new float[1];
                Location.distanceBetween(eEcos.get(i - 1).getLatitude(), eEcos.get(i - 1).getLongitude(), lat, lng, d);
                double dis = Double.parseDouble(df.format(d[0]));
                distances.add(dis);
                double total = dis + distancesAdd.get(i - 1);
                distancesAdd.add(total);
                try {
                    long old = dateFormat.parse(eEcos.get(i - 1).getDateFull()).getTime();
                    long now = dateFormat.parse(eEcos.get(i).getDateFull()).getTime();
                    long sec = now - old;
                    double speedForMPerS = 0;
                    if (dis > 0) {
                        speedForMPerS = dis / sec;
                    }
                    double speedForKPerH = speedForMPerS * 3600;
                    speeds.add(Double.parseDouble(df.format(speedForKPerH)));
                    if (dis > 4) {
                        spdOutZero.add(Double.parseDouble(df.format(speedForKPerH)));
                        disOutZero.add(dis);
                    }
                } catch (ParseException e) {
                    Log.e("PEco", e.toString());
                }
            }
        }
        ArrayList<StrokeData> strokeList = new ArrayList<>();
        ArrayList<Integer> carbonType = new ArrayList<>();
        double speedPerMin = 0;
        double disPerMin = 0;
        //行程分類
        for (int i = 0; i < spdOutZero.size(); i++) {
            int x = i + 1;
            if (x % 60 == 0 || i == spdOutZero.size() - 1) {
                speedPerMin += spdOutZero.get(i);
                double speedForKPH = speedPerMin / 60;
                disPerMin += disOutZero.get(i);
                StrokeData data = new StrokeData();
                data.setDis(disPerMin);
                data.setSpeed(speedForKPH);
                if (speedForKPH <= 6) {
                    data.setMode(1);
                    if (x % 60 == 0) {
                        for (int j = 0; j < 60; j++) {
                            carbonType.add(1);
                        }
                    } else {
                        int y = spdOutZero.size() % 60;
                        for (int j = 0; j < y; j++) {
                            carbonType.add(1);
                        }
                    }
                } else if (speedForKPH > 6 && speedForKPH <= 20) {
                    data.setMode(2);
                    if (x % 60 == 0) {
                        for (int j = 0; j < 60; j++) {
                            carbonType.add(2);
                        }
                    } else {
                        int y = spdOutZero.size() % 60;
                        for (int j = 0; j < y; j++) {
                            carbonType.add(2);
                        }
                    }
                } else if (speedForKPH > 20 && speedForKPH <= 50) {
                    data.setMode(3);
                    if (x % 60 == 0) {
                        for (int j = 0; j < 60; j++) {
                            carbonType.add(3);
                        }
                    } else {
                        int y = spdOutZero.size() % 60;
                        for (int j = 0; j < y; j++) {
                            carbonType.add(3);
                        }
                    }
                } else {
                    data.setMode(4);
                    if (x % 60 == 0) {
                        for (int j = 0; j < 60; j++) {
                            carbonType.add(4);
                        }
                    } else {
                        int y = spdOutZero.size() % 60;
                        for (int j = 0; j < y; j++) {
                            carbonType.add(4);
                        }
                    }
                }
                speedPerMin = 0;
                disPerMin = 0;
                strokeList.add(data);
            } else {
                speedPerMin += spdOutZero.get(i);
                disPerMin += disOutZero.get(i);
            }
        }
        double totalDis = 0;
        //計算碳足跡
        for (int i = 0; i < disOutZero.size(); i++) {
            double disToKm = disOutZero.get(i) / 1000;
            totalDis += disToKm;
            switch (carbonType.get(i)) {
                case 1:
                case 2:
                    carbons.add(disToKm * 0);
                    break;
                case 3:
                    carbons.add(disToKm * 86);
                    break;
                default:
                    carbons.add(disToKm * 300);
                    break;
            }
        }
        double totalCarbon = 0;
        //計算總碳足跡
        for (int i = 0; i < carbons.size(); i++) {
            totalCarbon += carbons.get(i);
        }
        ArrayList<StrokeData> finalStroke = new ArrayList<>();
        //行程同類型合併
        for (int i = 0; i < strokeList.size(); i++) {
            if (i == 0) {
                finalStroke.add(strokeList.get(0));
            } else {
                int index = finalStroke.size() - 1;
                if (finalStroke.get(index).getMode() == strokeList.get(i).getMode()) {
                    double dOld = strokeList.get(i).getDis();
                    double dNew = finalStroke.get(index).getDis();
                    finalStroke.get(index).setDis(dOld + dNew);
                    double sOld = strokeList.get(i).getSpeed();
                    double sNew = finalStroke.get(index).getSpeed();
                    double sFinal = (sOld + sNew) / 2;
                    finalStroke.get(index).setSpeed(sFinal);
                } else {
                    finalStroke.add(strokeList.get(i));
                }
            }
        }
        for (int i = 0; i < finalStroke.size(); i++) {
            double dis = finalStroke.get(i).getDis();
            switch (finalStroke.get(i).getMode()) {
                case 1:
                case 2:
                    finalStroke.get(i).setCarbon(dis / 1000 * 0);
                    break;
                case 3:
                    finalStroke.get(i).setCarbon(dis / 1000 * 86);
                    break;
                default:
                    finalStroke.get(i).setCarbon(dis / 1000 * 300);
                    break;
            }
        }
        ArrayList<Entry> spdEnList = new ArrayList<>();
        for (int i = 0; i < spdOutZero.size(); i++) {
            spdEnList.add(new Entry(Float.parseFloat(String.valueOf(distancesAdd.get(i))), Float.parseFloat(String.valueOf(spdOutZero.get(i)))));
        }
        ArrayList<Entry> cbEnList = new ArrayList<>();
        for (int i = 0; i < carbons.size(); i++) {
            cbEnList.add(new Entry(Float.parseFloat(String.valueOf(distancesAdd.get(i))), Float.parseFloat(String.valueOf(carbons.get(i)))));
        }
        ArrayList<String> disForString = new ArrayList<>();
        for (int i = 0; i < distancesAdd.size(); i++) {
            disForString.add(String.valueOf(distancesAdd.get(i)));
        }
        //init
        ArrayList<ECarbonDetail> details = new ArrayList<>();
        ECarbonDetail detailA = new ECarbonDetail();
        detailA.setLatLngs(latLngs);
        details.add(detailA);
        ECarbonDetail detailB = new ECarbonDetail();
        detailB.setDistances(distances);
        detailB.setDistanceAdd(distancesAdd);
        detailB.setSpeeds(speeds);
        detailB.setSpdEnList(spdEnList);
        detailB.setDisForString(disForString);
        details.add(detailB);
        ECarbonDetail detailC = new ECarbonDetail();
        detailC.setDistances(distances);
        detailC.setCarbons(carbons);
        detailC.setDistanceAdd(distancesAdd);
        detailC.setCbEnList(cbEnList);
        detailC.setDisForString(disForString);
        details.add(detailC);
        long time = 0;
        StringBuilder timeString = new StringBuilder();
        try {
            Date dateS = dateFormat.parse(MyApplication.getAppData().geteEcoList().getList().get(0).getDateFull());
            Date dateE = dateFormat.parse(MyApplication.getAppData().geteEcoList().getList().get(size - 1).getDateFull());
            time = dateE.getTime() - dateS.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        time /= 1000;
        int hour;
        int min;
        int sec;
        if (time >= 3600) {
            hour = (int)(time / 3600);
            time %= 3600;
            min = (int)(time / 60);
            sec = (int)(time % 60);
            if (hour >= 10) {
                timeString.append(hour);
            } else {
                timeString.append("0");
                timeString.append(hour);
            }
            timeString.append(":");
            if (min >= 10) {
                timeString.append(min);
            } else {
                timeString.append("0");
                timeString.append(min);
            }
            timeString.append(":");
            if (sec >= 10) {
                timeString.append(sec);
            } else {
                timeString.append("0");
                timeString.append(sec);
            }
        } else if (time >= 60) {
            min = (int)(time / 60);
            sec = (int)(time % 60);
            if (min >= 10) {
                timeString.append(min);
            } else {
                timeString.append("0");
                timeString.append(min);
            }
            timeString.append(":");
            if (sec >= 10) {
                timeString.append(sec);
            } else {
                timeString.append("0");
                timeString.append(sec);
            }
        } else {
            sec = (int)time;
            if (sec >= 10) {
                timeString.append(sec);
            } else {
                timeString.append("0");
                timeString.append(sec);
            }
        }
        ECarbonDetail detailD = new ECarbonDetail();
        detailD.setLift(df.format(totalDis));
        detailD.setCenter(timeString.toString());
        detailD.setRight(df.format(totalCarbon));
        details.add(detailD);
        for (int i = 0; i < finalStroke.size(); i++) {
            ECarbonDetail detail = new ECarbonDetail();
            detail.setName("行程" + (i + 1));
            detail.setDis("里程" + df.format(finalStroke.get(i).getDis() / 1000) + "公里");
            switch (finalStroke.get(i).getMode()) {
                case 1:
                    detail.setTool("交通工具： 步行");
                    break;
                case 2:
                    detail.setTool("交通工具： 自行車");
                    break;
                case 3:
                    detail.setTool("交通工具： 摩托車");
                    break;
                default:
                    detail.setTool("交通工具： 開車");
                    break;
            }
            detail.setCarbon("碳足跡：" + df.format(finalStroke.get(i).getCarbon()) + "公克二氧化碳排放量");
            details.add(detail);
        }
        ivEco.init(details);
    }
}
