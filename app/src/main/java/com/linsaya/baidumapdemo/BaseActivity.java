package com.linsaya.baidumapdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/17.
 */

public abstract class BaseActivity extends Activity implements View.OnClickListener {
    /**
     * 黑马坐标（北京市海淀区东北旺南路45号）
     */
    protected LatLng hmPos = new LatLng(40.050513, 116.30361);
    /**
     * 传智坐标
     */
    protected LatLng czPos = new LatLng(40.065817, 116.349902);
    /**
     * 天安门坐标
     */
    protected LatLng tamPos = new LatLng(39.915112, 116.403963);

    MapView mMapView = null;
    protected BaiduMap baiduMap;
    protected MapStatusUpdate mapStatusUpdate;
    @BindViews({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    List<Button> buttons;


    //将此方法定义为final，让子类不能复写方法，防止出现代码调用异常
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView = (MapView) findViewById(R.id.bmapView);
        baiduMap = mMapView.getMap();
        //设置放大及比例尺控件不显示
//        mMapView.showScaleControl(false);
//        mMapView.showZoomControls(false);
        //打印地图允许最大的缩放值
        Log.e("tag", String.valueOf(baiduMap.getMaxZoomLevel() + baiduMap.getMinZoomLevel()));
        //将地图中心点设置为黑马
        mapStatusUpdate = MapStatusUpdateFactory.newLatLng(hmPos);
        baiduMap.setMapStatus(mapStatusUpdate);
        //将地图默认的放大距离为50
        mapStatusUpdate = MapStatusUpdateFactory.zoomBy(5);
        baiduMap.setMapStatus(mapStatusUpdate);
        //隐藏指南针
        UiSettings uiSettings = baiduMap.getUiSettings();
        uiSettings.setCompassEnabled(false);
        //注入button点击事件
        ButterKnife.bind(this);
        buttons.get(0).setOnClickListener(this);
        buttons.get(1).setOnClickListener(this);
        buttons.get(2).setOnClickListener(this);
        buttons.get(3).setOnClickListener(this);
        buttons.get(4).setOnClickListener(this);
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    protected void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public abstract void onClick(View v);

    //子类初始化方法必须要写到此方法内
    public abstract void initData();
}
