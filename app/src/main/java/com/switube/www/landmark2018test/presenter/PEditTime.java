package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.entity.ECreateAttraction;
import com.switube.www.landmark2018test.entity.EEditTime;
import com.switube.www.landmark2018test.view.callback.IVEditTime;

import java.util.ArrayList;
import java.util.List;

public class PEditTime {
    private IVEditTime ivEditTime;
    public PEditTime(IVEditTime ivEditTime) {
        this.ivEditTime = ivEditTime;
    }

    public void handleTimeForCreateMode(ECreateAttraction eCreateAttraction, List<EEditTime> eEditTimeList) {
        int size = eCreateAttraction.getTime().getMon().size();
        StringBuilder week = new StringBuilder();
        List<String> weeks = new ArrayList<>();
        //List<String> open = new ArrayList<>();
        //List<String> close = new ArrayList<>();
        StringBuilder time = new StringBuilder();
        //String startTime = "";
        //String endTime = "";
        int index;
        for (int i = 0; i < size; i++) {
            if (week.length() > 0) {
                week.delete(0, week.length());
            }
            if (eCreateAttraction.getTime().getMon().get(i).length() > 0) {
                eEditTimeList.get(i).setMon(true);
                eEditTimeList.get(i).setEdit(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_mon));
                week.append(",");
                if (eCreateAttraction.getTime().getMon().get(i).equals("24")) {
                    eEditTimeList.get(i).setAllDay(true);
                    eEditTimeList.get(i).setStart("00:00");
                    eEditTimeList.get(i).setEnd("23:59");
                } else {
                    time.append(eCreateAttraction.getTime().getMon().get(i));
                    index = time.indexOf("-");
                    time.delete(index, time.length());
                    eEditTimeList.get(i).setStart(time.toString());
                    time.delete(0, time.length());
                    time.append(eCreateAttraction.getTime().getMon().get(i));
                    time.delete(0, index + 1);
                    eEditTimeList.get(i).setEnd(time.toString());
                }
            }
            if (eCreateAttraction.getTime().getTue().get(i).length() > 0) {
                eEditTimeList.get(i).setTue(true);
                eEditTimeList.get(i).setEdit(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_tue));
                week.append(",");
                if (time.length() > 0) {
                    time.delete(0, time.length());
                }
                if (eCreateAttraction.getTime().getTue().get(i).equals("24")) {
                    eEditTimeList.get(i).setAllDay(true);
                    eEditTimeList.get(i).setStart("00:00");
                    eEditTimeList.get(i).setEnd("23:59");
                } else {
                    time.append(eCreateAttraction.getTime().getTue().get(i));
                    index = time.indexOf("-");
                    time.delete(index, time.length());
                    eEditTimeList.get(i).setStart(time.toString());
                    time.delete(0, time.length());
                    time.append(eCreateAttraction.getTime().getTue().get(i));
                    time.delete(0, index + 1);
                    eEditTimeList.get(i).setEnd(time.toString());
                }
            }
            if (eCreateAttraction.getTime().getWed().get(i).length() > 0) {
                eEditTimeList.get(i).setWed(true);
                eEditTimeList.get(i).setEdit(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_wed));
                week.append(",");
                if (time.length() > 0) {
                    time.delete(0, time.length());
                }
                if (eCreateAttraction.getTime().getWed().get(i).equals("24")) {
                    eEditTimeList.get(i).setAllDay(true);
                    eEditTimeList.get(i).setStart("00:00");
                    eEditTimeList.get(i).setEnd("23:59");
                } else {
                    time.append(eCreateAttraction.getTime().getWed().get(i));
                    index = time.indexOf("-");
                    time.delete(index, time.length());
                    eEditTimeList.get(i).setStart(time.toString());
                    time.delete(0, time.length());
                    time.append(eCreateAttraction.getTime().getWed().get(i));
                    time.delete(0, index + 1);
                    eEditTimeList.get(i).setEnd(time.toString());
                }
            }
            if (eCreateAttraction.getTime().getThu().get(i).length() > 0) {
                eEditTimeList.get(i).setThu(true);
                eEditTimeList.get(i).setEdit(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_thu));
                week.append(",");
                if (time.length() > 0) {
                    time.delete(0, time.length());
                }
                if (eCreateAttraction.getTime().getThu().get(i).equals("24")) {
                    eEditTimeList.get(i).setAllDay(true);
                    eEditTimeList.get(i).setStart("00:00");
                    eEditTimeList.get(i).setEnd("23:59");
                } else {
                    time.append(eCreateAttraction.getTime().getThu().get(i));
                    index = time.indexOf("-");
                    time.delete(index, time.length());
                    eEditTimeList.get(i).setStart(time.toString());
                    time.delete(0, time.length());
                    time.append(eCreateAttraction.getTime().getThu().get(i));
                    time.delete(0, index + 1);
                    eEditTimeList.get(i).setEnd(time.toString());
                }
            }
            if (eCreateAttraction.getTime().getFri().get(i).length() > 0) {
                eEditTimeList.get(i).setFri(true);
                eEditTimeList.get(i).setEdit(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_fri));
                week.append(",");
                if (time.length() > 0) {
                    time.delete(0, time.length());
                }
                if (eCreateAttraction.getTime().getFri().get(i).equals("24")) {
                    eEditTimeList.get(i).setAllDay(true);
                    eEditTimeList.get(i).setStart("00:00");
                    eEditTimeList.get(i).setEnd("23:59");
                } else {
                    time.append(eCreateAttraction.getTime().getFri().get(i));
                    index = time.indexOf("-");
                    time.delete(index, time.length());
                    eEditTimeList.get(i).setStart(time.toString());
                    time.delete(0, time.length());
                    time.append(eCreateAttraction.getTime().getFri().get(i));
                    time.delete(0, index + 1);
                    eEditTimeList.get(i).setEnd(time.toString());
                }
            }
            if (eCreateAttraction.getTime().getSat().get(i).length() > 0) {
                eEditTimeList.get(i).setSat(true);
                eEditTimeList.get(i).setEdit(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_sat));
                week.append(",");
                if (time.length() > 0) {
                    time.delete(0, time.length());
                }
                if (eCreateAttraction.getTime().getSat().get(i).equals("24")) {
                    eEditTimeList.get(i).setAllDay(true);
                    eEditTimeList.get(i).setStart("00:00");
                    eEditTimeList.get(i).setEnd("23:59");
                } else {
                    time.append(eCreateAttraction.getTime().getSat().get(i));
                    index = time.indexOf("-");
                    time.delete(index, time.length());
                    eEditTimeList.get(i).setStart(time.toString());
                    time.delete(0, time.length());
                    time.append(eCreateAttraction.getTime().getSat().get(i));
                    time.delete(0, index + 1);
                    eEditTimeList.get(i).setEnd(time.toString());
                }
            }
            if (eCreateAttraction.getTime().getSun().get(i).length() > 0) {
                eEditTimeList.get(i).setSun(true);
                eEditTimeList.get(i).setEdit(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_sun));
                if (time.length() > 0) {
                    time.delete(0, time.length());
                }
                if (eCreateAttraction.getTime().getSun().get(i).equals("24")) {
                    eEditTimeList.get(i).setAllDay(true);
                    eEditTimeList.get(i).setStart("00:00");
                    eEditTimeList.get(i).setEnd("23:59");
                } else {
                    time.append(eCreateAttraction.getTime().getSun().get(i));
                    index = time.indexOf("-");
                    time.delete(index, time.length());
                    eEditTimeList.get(i).setStart(time.toString());
                    time.delete(0, time.length());
                    time.append(eCreateAttraction.getTime().getSun().get(i));
                    time.delete(0, index + 1);
                    eEditTimeList.get(i).setEnd(time.toString());
                }
            }
            if (week.length() > 0) {
                weeks.add(week.toString());
                //open.add(startTime);
                //startTime = "";
                //close.add(endTime);
                //endTime = "";
            }
        }
        ivEditTime.showDefaultTime(weeks, eEditTimeList);
    }

    public void handleTimeForEditMode(List<EEditTime> eEditTimeList) {
        int size = MyApplication.getAppData().getgInfoData().getData().get(0).getMon().size();
        StringBuilder week = new StringBuilder();
        List<String> weeks = new ArrayList<>();
        StringBuilder time = new StringBuilder();
        int index;
        for (int i = 0; i < size; i++) {
            if (week.length() > 0) {
                week.delete(0, week.length());
            }
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getMon().get(i).length() > 0) {
                eEditTimeList.get(i).setEdit(true);
                eEditTimeList.get(i).setMon(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_mon));
                week.append(",");
                if (MyApplication.getAppData().getgInfoData().getData().get(0).getMon().get(i).equals("24")) {
                    eEditTimeList.get(i).setAllDay(true);
                    eEditTimeList.get(i).setStart("00:00");
                    eEditTimeList.get(i).setEnd("23:59");
                } else {
                    if (time.length() > 0) {
                        time.delete(0, time.length());
                    }
                    time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getMon().get(i));
                    index = time.indexOf("-");
                    time.delete(index, time.length());
                    eEditTimeList.get(i).setStart(time.toString());
                    time.delete(0, time.length());
                    time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getMon().get(i));
                    time.delete(0, index + 1);
                    eEditTimeList.get(i).setEnd(time.toString());
                }
            }
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getTue().get(i).length() > 0) {
                eEditTimeList.get(i).setEdit(true);
                eEditTimeList.get(i).setTue(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_tue));
                week.append(",");
                if (eEditTimeList.get(i).getStart().length() == 0) {
                    if (time.length() > 0) {
                        time.delete(0, time.length());
                    }
                    if (MyApplication.getAppData().getgInfoData().getData().get(0).getTue().get(i).equals("24")) {
                        eEditTimeList.get(i).setAllDay(true);
                        eEditTimeList.get(i).setStart("00:00");
                        eEditTimeList.get(i).setEnd("23:59");
                    } else {
                        time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getTue().get(i));
                        index = time.indexOf("-");
                        time.delete(index, time.length());
                        eEditTimeList.get(i).setStart(time.toString());
                        time.delete(0, time.length());
                        time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getTue().get(i));
                        time.delete(0, index + 1);
                        eEditTimeList.get(i).setEnd(time.toString());
                    }
                }
            }
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getWed().get(i).length() > 0) {
                eEditTimeList.get(i).setEdit(true);
                eEditTimeList.get(i).setWed(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_wed));
                week.append(",");
                if (eEditTimeList.get(i).getStart().length() == 0) {
                    if (time.length() > 0) {
                        time.delete(0, time.length());
                    }
                    if (MyApplication.getAppData().getgInfoData().getData().get(0).getWed().get(i).equals("24")) {
                        eEditTimeList.get(i).setAllDay(true);
                        eEditTimeList.get(i).setStart("00:00");
                        eEditTimeList.get(i).setEnd("23:59");
                    } else {
                        time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getWed().get(i));
                        index = time.indexOf("-");
                        time.delete(index, time.length());
                        eEditTimeList.get(i).setStart(time.toString());
                        time.delete(0, time.length());
                        time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getWed().get(i));
                        time.delete(0, index + 1);
                        eEditTimeList.get(i).setEnd(time.toString());
                    }
                }
            }
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getThu().get(i).length() > 0) {
                eEditTimeList.get(i).setEdit(true);
                eEditTimeList.get(i).setThu(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_thu));
                week.append(",");
                if (eEditTimeList.get(i).getStart().length() == 0) {
                    if (time.length() > 0) {
                        time.delete(0, time.length());
                    }
                    if (MyApplication.getAppData().getgInfoData().getData().get(0).getThu().get(i).equals("24")) {
                        eEditTimeList.get(i).setAllDay(true);
                        eEditTimeList.get(i).setStart("00:00");
                        eEditTimeList.get(i).setEnd("23:59");
                    } else {
                        time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getThu().get(i));
                        index = time.indexOf("-");
                        time.delete(index, time.length());
                        eEditTimeList.get(i).setStart(time.toString());
                        time.delete(0, time.length());
                        time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getThu().get(i));
                        time.delete(0, index + 1);
                        eEditTimeList.get(i).setEnd(time.toString());
                    }
                }
            }
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getFri().get(i).length() > 0) {
                eEditTimeList.get(i).setEdit(true);
                eEditTimeList.get(i).setFri(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_fri));
                week.append(",");
                if (eEditTimeList.get(i).getStart().length() == 0) {
                    if (time.length() > 0) {
                        time.delete(0, time.length());
                    }
                    if (MyApplication.getAppData().getgInfoData().getData().get(0).getFri().get(i).equals("24")) {
                        eEditTimeList.get(i).setAllDay(true);
                        eEditTimeList.get(i).setStart("00:00");
                        eEditTimeList.get(i).setEnd("23:59");
                    } else {
                        time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getFri().get(i));
                        index = time.indexOf("-");
                        time.delete(index, time.length());
                        eEditTimeList.get(i).setStart(time.toString());
                        time.delete(0, time.length());
                        time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getFri().get(i));
                        time.delete(0, index + 1);
                        eEditTimeList.get(i).setEnd(time.toString());
                    }
                }
            }
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getSat().get(i).length() > 0) {
                eEditTimeList.get(i).setEdit(true);
                eEditTimeList.get(i).setSat(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_sat));
                week.append(",");
                if (eEditTimeList.get(i).getStart().length() == 0) {
                    if (time.length() > 0) {
                        time.delete(0, time.length());
                    }
                    if (MyApplication.getAppData().getgInfoData().getData().get(0).getSat().get(i).equals("24")) {
                        eEditTimeList.get(i).setAllDay(true);
                        eEditTimeList.get(i).setStart("00:00");
                        eEditTimeList.get(i).setEnd("23:59");
                    } else {
                        time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getSat().get(i));
                        index = time.indexOf("-");
                        time.delete(index, time.length());
                        eEditTimeList.get(i).setStart(time.toString());
                        time.delete(0, time.length());
                        time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getSat().get(i));
                        time.delete(0, index + 1);
                        eEditTimeList.get(i).setEnd(time.toString());
                    }
                }
            }
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getSun().get(i).length() > 0) {
                eEditTimeList.get(i).setEdit(true);
                eEditTimeList.get(i).setSun(true);
                week.append(MyApplication.getInstance().getString(R.string.weekly_sun));
                if (eEditTimeList.get(i).getStart().length() == 0) {
                    if (time.length() > 0) {
                        time.delete(0, time.length());
                    }
                    if (MyApplication.getAppData().getgInfoData().getData().get(0).getSun().get(i).equals("24")) {
                        eEditTimeList.get(i).setAllDay(true);
                        eEditTimeList.get(i).setStart("00:00");
                        eEditTimeList.get(i).setEnd("23:59");
                    } else {
                        time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getSun().get(i));
                        index = time.indexOf("-");
                        time.delete(index, time.length());
                        eEditTimeList.get(i).setStart(time.toString());
                        time.delete(0, time.length());
                        time.append(MyApplication.getAppData().getgInfoData().getData().get(0).getSun().get(i));
                        time.delete(0, index + 1);
                        eEditTimeList.get(i).setEnd(time.toString());
                    }
                }
            }
            if (week.length() > 0) {
                weeks.add(week.toString());
            }
        }
        ivEditTime.showDefaultTime(weeks, eEditTimeList);
    }

    public void handleRefreshTimeData(List<EEditTime> eEditTimeList) {
        List<String> week = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (eEditTimeList.get(i).isEdit()) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.delete(0, stringBuilder.length());
                }
                boolean isFirst = true;
                if (eEditTimeList.get(i).isMon()) {
                    isFirst = false;
                    stringBuilder.append(MyApplication.getInstance().getString(R.string.weekly_mon));
                }
                if (eEditTimeList.get(i).isTue()) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(MyApplication.getInstance().getString(R.string.weekly_tue));
                }
                if (eEditTimeList.get(i).isWed()) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(MyApplication.getInstance().getString(R.string.weekly_wed));
                }
                if (eEditTimeList.get(i).isThu()) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(MyApplication.getInstance().getString(R.string.weekly_thu));
                }
                if (eEditTimeList.get(i).isFri()) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(MyApplication.getInstance().getString(R.string.weekly_fri));
                }
                if (eEditTimeList.get(i).isSat()) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(MyApplication.getInstance().getString(R.string.weekly_sat));
                }
                if (eEditTimeList.get(i).isSun()) {
                    if (!isFirst) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(MyApplication.getInstance().getString(R.string.weekly_sun));
                }
                week.add(stringBuilder.toString());
            }
        }
        ivEditTime.showDefaultTime(week, eEditTimeList);
    }
}
