package com.example.suanming.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.suanming.DBManager;
import com.example.suanming.MainActivity;
import com.example.suanming.R;
import com.example.suanming.RateItem;

import java.util.Objects;

import pl.droidsonroids.gif.GifDecoder;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import static java.lang.Thread.sleep;

public class DashboardFragment extends Fragment {

    GifImageView gifImageView;
    GifDrawable gifDrawable;
    TextView cqtip,count;
    int i = 0;
    int j;
    DBManager dbManager;
    RateItem rateItem = new RateItem("","");
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        gifImageView = root.findViewById(R.id.cqgif);
        gifDrawable = (GifDrawable) gifImageView.getDrawable();
        gifDrawable.stop();
        cqtip = root.findViewById(R.id.cqtip);
        count = root.findViewById(R.id.count);


        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button button = getActivity().findViewById(R.id.chouqian);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num;
                dbManager = new DBManager(getActivity());
                if(button.getText().equals("抽签")){
                    gifDrawable.setLoopCount(1);//设置播放次数为1
                    gifDrawable.start();//开始播放
                    cqtip.setText("请注意拾取您抽取的签");
                    button.setText("再抽一次");
                    num = Integer.parseInt((String) count.getText())+1;
                    count.setText(String.valueOf(num));

                }
                else{
                    gifDrawable.reset();
                    gifDrawable.setLoopCount(1);//设置播放次数为1
                    gifDrawable.start();//开始播放
                    num = Integer.parseInt((String) count.getText())+1;
                    count.setText(String.valueOf(num));
                }
                i = 1;//控制Gif播放一遍之后才可以点击

                j = (int) (Math.random()*10+1);//传递随机数
                rateItem.setID(num);
                rateItem.setCurName("第"+String.valueOf(j)+"签");
                //签的内容
                switch (j){
                    case 1: case 4: case 8:case 9:
                        rateItem.setCurRate("上签");
                        break;
                    case 2: case 3: case 7:
                        rateItem.setCurRate("下签");
                        break;
                    case 5: case 6: case 10:
                        rateItem.setCurRate("中签");
                        break;
                }
                Log.i("rateItem",rateItem.getId()+rateItem.getCurName()+rateItem.getCurRate());
                dbManager.add(rateItem);

            }
        });

        gifImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cqresult = new Intent(getActivity(), com.example.suanming.ui.dashboard.cqresult.class);
                cqresult.putExtra("flag",i);
                cqresult.putExtra("random",j);
                i = 0;
                if(!gifDrawable.isRunning()&&!cqtip.getText().equals("")){
                    startActivity(cqresult);
                }
            }
        });


    }

}