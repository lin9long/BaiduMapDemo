package com.linsaya.baidumapdemo;

import android.view.View;

import com.baidu.mapapi.map.BaiduMap;


/**
 * Created by Administrator on 2017/2/17.
 */

public class MapLayerActivity extends BaseActivity {
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                //设置卫星图层
                baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                baiduMap.setTrafficEnabled(false);
                break;
            case R.id.btn2:
                //设置正常图层
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                baiduMap.setTrafficEnabled(false);
                break;
            case R.id.btn3:
                //显示交通状况
                baiduMap.setTrafficEnabled(true);
                break;
            case R.id.btn4:

                break;
            case R.id.btn5:

                return;
        }

    }

    @Override
    public void initData() {

    }
}
