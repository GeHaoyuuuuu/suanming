package com.example.suanming.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
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

public class NotificationsFragment extends Fragment {


    private NotificationsViewModel notificationsViewModel;

    ListView listView;
    String data[] = new String[50];
    DBManager dbManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        listView = root.findViewById(R.id.recordlist);

        for(int j =0;j<50;j++ ){
        data[j] = " ";}

        dbManager = new DBManager(getActivity());
        RateItem r1;
        int i = 1;
        try {
          while (i<100){
                r1 = dbManager.findById(i);
                if(r1==null)break;
                data[i-1] = i +"  "+r1.getCurName()+"  "+r1.getCurRate();
                i++;
                Log.i("rate","run...."+r1.getCurName()+"  "+r1.getCurRate());
            }
            Log.i("rate","run...."+i);
            ListAdapter adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }



        /* final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
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
    }
}