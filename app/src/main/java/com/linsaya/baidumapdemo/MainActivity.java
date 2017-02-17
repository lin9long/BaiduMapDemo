package com.linsaya.baidumapdemo;

import android.view.View;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        MapStatusUpdate mapStatusUpdate = null;
        switch (v.getId()) {
            case R.id.btn1:
                mapStatusUpdate = MapStatusUpdateFactory.zoomOut();
                break;
            case R.id.btn2:
                mapStatusUpdate = MapStatusUpdateFactory.zoomIn();
                break;
            case R.id.btn3:
                MapStatus mapStatus = baiduMap.getMapStatus();
                float currentRotate = mapStatus.rotate;
                MapStatus status = new MapStatus.Builder().rotate(currentRotate + 30).build();
                mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(status);
                break;
            case R.id.btn4:
                MapStatus mapStatus1 = baiduMap.getMapStatus();
                float currentOverlook = mapStatus1.overlook;
                MapStatus status1 = new MapStatus.Builder().overlook(currentOverlook - 5).build();
                mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(status1);
                break;
            case R.id.btn5:
                mapStatusUpdate = MapStatusUpdateFactory.newLatLng(hmPos);
                baiduMap.animateMapStatus(mapStatusUpdate, 2000);
                return;
        }
        baiduMap.setMapStatus(mapStatusUpdate);
    }


}
