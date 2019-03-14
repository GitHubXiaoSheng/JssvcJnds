package com.app.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.app.trafficclient.R;
import com.app.trafficclient.adapter.Weizhang;
import com.app.trafficclient.adapter.YwsActivityAdapter;

import java.util.ArrayList;
import java.util.List;

public class Yws_Fragment_activity4_video extends Fragment {
    private GridView gridView;

    private YwsActivityAdapter gridadapter;
    private List<Weizhang> videolist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yws__fragment_activity4_video, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        gridView=getActivity().findViewById(R.id.gridview_item);
        videolist=new ArrayList<>();
        init();
        gridadapter=new YwsActivityAdapter(getContext(),R.layout.yws_activity4_item,videolist);
        gridView.setAdapter(gridadapter);

    }
    void init(){
        Weizhang video1=new Weizhang("视频1",R.drawable.video_icon);
        videolist.add(video1);
        Weizhang video2=new Weizhang("视频2",R.drawable.video_icon);
        videolist.add(video2);
        Weizhang video3=new Weizhang("视频3",R.drawable.video_icon);
        videolist.add(video3);
        Weizhang video4=new Weizhang("视频3",R.drawable.video_icon);
        videolist.add(video4);
        Weizhang video5=new Weizhang("视频3",R.drawable.video_icon);
        videolist.add(video5);
    }
}
