package com.example.p002_screen_light.utils;

/**
 * <p>
 *     设置页亮度模块工具类
 * </p>
 * Created by A on 2018/1/5.
 */

public class LiangduUtil {

    /**
     * <p>
     *     根据当前的位置判断减少后在哪个刻度
     * </p>
     * @param progress
     * @return
     */
    public static int getDecTime(int progress){
        int time = 0;
        if (progress >= 0 && progress < 12.5) {//0
            time = 0;
        } else if (progress >= 12.5 && progress < 37.5) {//25
            time = 0;
        } else if (progress >= 37.5 && progress < 62.5) {//50
            time = 25;
        } else if (progress >= 62.5 && progress < 87.5) {//75
            time = 50;
        } else {
            time = 75;
        }
        return  time;
    }

    /**
     * <p>
     *     根据当前的位置判断停在哪个刻度
     * </p>
     * @param progress
     * @return
     */
    public static int getTime(int progress){
        int time = 0;
        if (progress >= 0 && progress < 12.5) {
            time = 0;
        } else if (progress >= 12.5 && progress < 37.5) {
            time = 25;
        } else if (progress >= 37.5 && progress < 62.5) {
            time = 50;
        } else if (progress >= 62.5 && progress < 87.5) {
            time = 75;
        } else {
            time = 100;
        }
        return time;
    }

    /**
     * <p>
     *   根据滚动的位置判断停在哪个刻度
     * </p>
     * @param progress
     * @return
     */
    public static int getScrollTime(int progress){
        int time = 100;
        if (progress >= 0 && progress < 12.5) {
            time = 25;
        } else if (progress >= 12.5 && progress < 37.5) {
            time = 50;
        } else if (progress >= 37.5 && progress < 62.5) {
            time = 75;
        } else if (progress >= 62.5 && progress < 87.5) {
            time = 100;
        } else {
            time = 100;
        }
        return time;
    }

    /**
     * <p>
     *     根据当前的停的那个刻度，判断时间段在哪个阶段
     * </p>
     * @param time
     * @return
     */
    public static String getMinuteStr(int time){
        String str = "";
        if (time == 0) {
            str = "1分钟";
        } else if (time == 25) {
            str = "2分钟";
        } else if (time == 50) {
            str = "5分钟";
        } else if (time == 75) {
            str = "10分钟";
        } else if (time == 100) {
            str = "30分钟";
        } else {
            str = "30分钟";
        }
        return str;
    }

    /**
     * <p>
     *     根据当前停留的刻度设置系统休眠时间(按照正常理解应该是毫秒，但是由于contentResovler中是Int，所以此处为了保持一致用Int)
     * </p>
     * @param time
     * @return
     */
    public static int getMillsSecond(int time){
        int mill = 300000;
        if (time == 0) {
            mill = 60000;
        } else if (time == 25) {
            mill = 120000 ;
        } else if (time == 50) {
            mill = 300000;
        } else if (time == 75) {
            mill = 600000;
        } else if (time == 100) {
            mill = 1800000;
        } else {
            mill = 300000;
        }
        return mill;
    }
}
