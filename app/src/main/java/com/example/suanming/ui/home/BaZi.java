package com.example.suanming.ui.home;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 根据西元的生日时辰获取某个人的生辰八字，以及农历生日
 **/
public class BaZi {
    private int year;	//年
    private int month;	//月
    private int day;	//日
    private boolean leap;	//是否有闰月
    Date baseDate = null;	//日期基准值
    final static String chineseNumber[] = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "腊"};	//农历月数组
    public final static String[] Gan = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};	//天干数组
    public final static String[] Zhi = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};	//地支数组
    //规定日期格式
    static SimpleDateFormat chineseDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**农历数组
     *0x表示十六进制，后面的是十六进制数
     *最后四位代表该年闰月的月份，为0则没有闰月。
     *前四位，在该年有闰月才有意义，为0表示闰月29天，为1表示闰月30天。
     *中间12位，即4bd代表该年12个月每个月的天数，0表示29天，1表示30天。
     *然后根据阳历的1900年1月31日是阴历的1900年的初一，然后查表得出阴历和阳历的关系。
     *下面是经过整理的150年内的阴历数据。
     **/
    static long[] lunarInfo = new long[]{
            0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,
            0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977,
            0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,
            0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950,
            0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557,
            0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,
            0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0,
            0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,
            0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570,
            0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,
            0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5,
            0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930,
            0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
            0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,
            0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0
    };
    /**
     * 六十甲子
     **/
    public static String[] jiazi = {
            "甲子", "乙丑", "丙寅", "丁卯", "戊辰", "己巳", "庚午", "辛未", "壬申", "癸酉",
            "甲戌", "乙亥", "丙子", "丁丑", "戊寅", "己卯", "庚辰", "辛巳", "壬午", "癸未",
            "甲申", "乙酉", "丙戌", "丁亥", "戊子", "己丑", "庚寅", "辛卯", "壬辰", "癸巳",
            "甲午", "乙未", "丙申", "丁酉", "戊戌", "己亥", "庚子", "辛丑", "壬寅", "癸卯",
            "甲辰", "乙巳", "丙午", "丁未", "戊申", "己酉", "庚戌", "辛亥", "壬子", "癸丑",
            "甲寅", "乙卯", "丙辰", "丁巳", "戊午", "己未", "庚申", "辛酉", "壬戌", "癸亥"
    };
    private Calendar cal;

    /**
     * @param hour 这里的时间范围是1-12
     * 具体的选择如下 "子：1", "丑：2", "寅：3", "卯：4", "辰：5", "巳：6", "午：7", "未：8", "申：9", "酉：10", "戌：11", "亥：12"
     **/
    public String getYearGanZhi(int hour) {
        //1864年是甲子年，每隔六十年一个甲子
        int jzy = (year - 1864) % 60;
        //没有过春节的话那么年还算上一年的，此处求的年份的干支
        String y = jiazi[jzy];  //年

        String m="";  //月
        String d="";  //日
        String h="";  //时辰
        jzy = jzy % 5;
        int gzm=0;
        /**
         * 年上起月
         * 算法口诀：
         * 甲己之年丙作首，乙庚之岁戊为头，
         * 丙辛必定寻庚起，丁壬壬位顺行流，
         * 更有戊癸何方觅，甲寅之上好追求。
         **/
        gzm=(jzy+1)*2;
        if(gzm==10) gzm=0;
        //求的月份的干支
        m=Gan[(gzm+month-1)%10]+Zhi[(month+2-1)%12];


        /*求出和1900年1月31日甲辰日相差的天数
         * 甲辰日是第四十天
         **/
        int day1 = (int) ((cal.getTime().getTime() - baseDate.getTime()) / 86400000L);
        day1=(day1+40)%60;
        //求的日的干支
        d=jiazi[day1];

        /**
         * 日上起时
         * 算法口诀：
         * 甲己还生甲，乙庚丙作初，
         * 丙辛从戊起，丁壬庚子居，
         * 戊癸何方发，壬子是真途。
         **/

        day1=(day1 % 5 )*2;
        //求得时辰的干支
        h=Gan[(day1+hour)%10]+Zhi[hour-1];
        //在此处输出我们的年月日时的天干地支
        return y+","+m+","+d+","+h;
    }

    //传回农历 y年的总天数
    private static int yearDays(int y) {
        int i,  sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1) {
            if ((lunarInfo[y - 1900] & i) != 0) {
                sum += 1;
            }
        }
        return (sum + leapDays(y));
    }

    //传回农历 y年闰月的天数
    private static int leapDays(int y) {
        if (leapMonth(y) != 0) {
            if ((lunarInfo[y - 1900] & 0x10000) != 0) {
                return 30;
            } else {
                return 29;
            }
        } else {
            return 0;
        }
    }

    //传回农历 y年闰哪个月 1-12 , 没闰传回 0
    private static int leapMonth(int y) {
        return (int) (lunarInfo[y - 1900] & 0xf);
    }

    //传回农历 y年m月的总天数
    private static int monthDays(int y, int m) {
        if ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0) {
            return 29;
        } else {
            return 30;
        }
    }

    /**
     * @return 传回农历 y年的生肖
     **/
    public String animalsYear() {
        final String[] Animals = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
        return Animals[(year - 4) % 12];
    }
    //传入 月日的offset 传回干支, 0=甲子
    private static String cyclicalm(int num) {
        return (Gan[num % 10] + Zhi[num % 12]);
    }
    //传入 offset 传回干支, 0=甲子
    public String cyclical() {
        int num = year - 1900 + 36;
        return (cyclicalm(num));
    }
    /**
     * 传出y年m月d日对应的农历.
     * yearC:农历年与1864的相差数（1864年为甲子年）
     * dayC:与1900年1月31日相差的天数,再加40(因为1月31日为甲辰日，是第四十天)
     **/
    public BaZi(Calendar cal) {
        this.cal=cal;
        int yearC,dayC;
        int leapMonth = 0;

        try {
            baseDate = chineseDateFormat.parse("1900-1-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int day2 = (int) ((cal.getTime().getTime() - baseDate.getTime()) / 86400000L);	//求出和1900年1月31日相差的天数
        dayC = day2 + 40;
        //用day2减去每农历年的天数
        //计算当天是农历第几天
        //iYear最终结果是农历的年份
        //day2是当年的第几天
        int iYear,  daysOfYear = 0;
        for (iYear = 1900; iYear < 2050 && day2 > 0; iYear++) {
            daysOfYear = yearDays(iYear);
            day2 -= daysOfYear;
        }
        if (day2 < 0) {
            day2 += daysOfYear;
            iYear--;
        }

        //农历年份
        year = iYear;
        yearC = iYear - 1864;
        leapMonth = leapMonth(iYear); //闰哪个月,1-12，没有闰月得0
        leap = false;  //设初值为没有闰月
        //用当年的天数day2,逐个减去每月（农历）的天数（daysOfMonth），求出当天是本月的第几天（iMonth）。
        int iMonth,  daysOfMonth = 0;
        for (iMonth = 1; iMonth < 13 && day2 > 0; iMonth++) {
            //闰月
            if (leapMonth > 0 && iMonth == (leapMonth + 1) && !leap)//如果有闰月
            {
                --iMonth;	//减去闰月
                leap = true;	//有闰月
                daysOfMonth = leapDays(year);	//月天数为闰月天数
            }
            else	//若没有闰月
            {
                daysOfMonth = monthDays(year, iMonth);  //月天数为该农历月天数
            }
            day2 -= daysOfMonth;  //减去该农历月的天数
            //如果当前计算月份为闰月得下一个月份则解除闰月标记
            if (leap && iMonth == (leapMonth + 1)) {
                leap = false;
            }
        }
        //day2为0时，并且刚才计算的月份是闰月，要校正
        if (day2 == 0 && leapMonth > 0 && iMonth == leapMonth + 1) {
            if (leap) {
                leap = false;
            } else {
                leap = true;
                --iMonth;
            }
        }
        //day2小于0时，也要校正
        if (day2 < 0) {
            day2 += daysOfMonth;
            --iMonth;
        }
        month = iMonth;
        day = day2 + 1;
    }

    /**
     *
     * @param day为计算出的农历日期的日
     * @return 农历中文的日
     **/
    public static String getChinaDayString(int day) {
        String chineseTen[] = {"初", "十", "廿", "卅"};
        int n = day % 10 == 0 ? 9 : day % 10 - 1;
        if (day > 30) {
            return "";
        }
        if (day == 10) {
            return "初十";
        } else {
            return chineseTen[day / 10] + chineseNumber[n];
        }
    }

    /**
     * @return 返回农历中文年月日
     **/
    public String toString() {
        if(month==1) {	//正月
            return getYearStr(year) + "年," + (leap ? "闰" : "") + "正月," + getChinaDayString(day);
        }
        else {
            return getYearStr(year) + "年," + (leap ? "闰" : "") + chineseNumber[month - 1] + "月," + getChinaDayString(day);
        }
    }
    /**
     *
     * @param year为计算出的农历年份
     * @return	返回中文的农历年份
     */
    public String getYearStr(int year) {
        String[] chineseword = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String ys = "";
        int index = year / 1000;
        ys += chineseword[index];
        year = year % 1000;
        index = year / 100;
        ys += chineseword[index];
        year = year % 100;
        index = year / 10;
        ys += chineseword[index];
        year = year % 10;
        index = year;
        ys += chineseword[index];
        return ys;
    }
    /**
     *
     * @return 六十干支
     */
    public static String getSixtyDay() {
        String temp = "";
        for (int i = 0; i < 60; i++) {
            temp += ",/" + cyclicalm(i) + "/";
        }
        return temp;
    }
}