package com.example.suanming.ui.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.suanming.R;

import com.example.suanming.ui.home.BaZi;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    EditText year,month,day,nlyear,nlmonth,nlday,animal,glc;
    ImageView animalpicture;
    TextView tip;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        year = root.findViewById(R.id.gl_year);
        month = root.findViewById(R.id.gl_month);
        day = root.findViewById(R.id.gl_day);
        glc = root.findViewById(R.id.gl_clock);
        nlyear = root.findViewById(R.id.nl_year);
        nlmonth = root.findViewById(R.id.nl_month);
        nlday = root.findViewById(R.id.nl_day);
        animal = root.findViewById(R.id.shengxiao);
        animalpicture = root.findViewById(R.id.animal);
        tip = root.findViewById(R.id.trans_tip);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //生辰计算
        Button but = getActivity().findViewById(R.id.trans);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yea = year.getText().toString();
                String mon = month.getText().toString();
                String day1 = day.getText().toString();
                Log.i("cal", " run :....data is:" + yea + mon + day1);
                if(yea.equals("")||mon.equals("")||day1.equals("")){
                    Toast.makeText(getActivity(), "请输入完整的出生日期", Toast.LENGTH_SHORT
                    ).show();
                }
                else {
                    if (Integer.parseInt(yea) < 1901 || Integer.parseInt(yea) > 2049) {
                        Toast.makeText(getActivity(), "年份只能为1901-2049", Toast.LENGTH_SHORT
                        ).show();
                    } else if (Integer.parseInt(mon) < 1 || Integer.parseInt(mon) > 12) {
                        Toast.makeText(getActivity(), "月份只能为1-12", Toast.LENGTH_SHORT
                        ).show();
                    } else if (Integer.parseInt(day1) > 31 || Integer.parseInt(day1) < 1) {
                        Toast.makeText(getActivity(), "日只能为1-31", Toast.LENGTH_SHORT
                        ).show();
                    } else {

                        Calendar cal = null;
                        try {
                            cal = getCalendarfromString(yea + "-" + mon + "-" + day1, "yyyy-MM-dd");

                            BaZi lun = new BaZi(cal);
                            String Sc = lun.toString();
                            Log.i("cal", "data is:" + Sc);
                            String[] nlbir = new String[3];
                            nlbir = Sc.split(",");

                            nlyear.setText(nlbir[0]);
                            nlmonth.setText(nlbir[1]);
                            nlday.setText(nlbir[2]);

                            String an = lun.animalsYear();
                            animal.setText(an);

                            animalpicture.setBackgroundResource(R.drawable.bagua2);
                            tip.setText("点击上方图片查看结果");
                            //根据生肖显示相应的图片
                            switch (an) {
                                case "鼠":
                                    animalpicture.setImageResource(R.drawable.shu);
                                    break;
                                case "牛":
                                    animalpicture.setImageResource(R.drawable.niu);
                                    break;
                                case "虎":
                                    animalpicture.setImageResource(R.drawable.hu);
                                    break;
                                case "兔":
                                    animalpicture.setImageResource(R.drawable.tu);
                                    break;
                                case "龙":
                                    animalpicture.setImageResource(R.drawable.long1);
                                    break;
                                case "蛇":
                                    animalpicture.setImageResource(R.drawable.she);
                                    break;
                                case "马":
                                    animalpicture.setImageResource(R.drawable.ma);
                                    break;
                                case "羊":
                                    animalpicture.setImageResource(R.drawable.yang);
                                    break;
                                case "猴":
                                    animalpicture.setImageResource(R.drawable.hou);
                                    break;
                                case "鸡":
                                    animalpicture.setImageResource(R.drawable.ji);
                                    break;
                                case "狗":
                                    animalpicture.setImageResource(R.drawable.gou);
                                    break;
                                case "猪":
                                    animalpicture.setImageResource(R.drawable.zhu);
                                    break;

                            }

                        } catch (ParseException ex) {
                            Toast.makeText(getActivity(), "请输入正确的日期！！！", Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                }
            }
        });

        animalpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result = new Intent(getActivity(),Sc_Result.class);

                String shour = glc.getText().toString();

                if (animalpicture.getDrawable()==null){
                }else if(shour.equals("") ||!isNumericZidai(shour)||Integer.parseInt(shour)>23||Integer.parseInt(shour)<0){
                    Toast.makeText(getActivity(),"请输入您正确的出生时刻！！！",Toast.LENGTH_SHORT
                    ).show();
                }else{
                    String yea = year.getText().toString();
                    String mon = month.getText().toString();
                    String day1 = day.getText().toString();
                    Log.i("cal"," run :....data is:"+yea+mon+day1);

                    Calendar cal = null;
                    try {
                        cal = getCalendarfromString(yea+"-"+mon+"-"+day1, "yyyy-MM-dd");

                        baziqianzao qianzao = new baziqianzao();
                        String qz = qianzao.paipan(cal, Integer.parseInt(shour));

                        result.putExtra("Tresult",qz);


                    } catch (ParseException ex) {
                    }

                    startActivity(result);
                }
            }
        });

    }
    public static boolean isNumericZidai(String str) {
        //判断是否为数字
        if (str == null){
            return false;
        }
        else {
            for (int i = 0; i < str.length(); i++) {
                System.out.println(str.charAt(i));
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    private Calendar getCalendarfromString(String year, String DateFormat) throws ParseException {
        //将字符串日期根据特定格式转化为Calendar类
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(year));
        return cal;
    }
}