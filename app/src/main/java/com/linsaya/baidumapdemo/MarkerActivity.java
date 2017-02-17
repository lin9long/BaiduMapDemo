package com.linsaya.baidumapdemo;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by Administrator on 2017/2/17.
 */

public class MarkerActivity extends BaseActivity {

    private View pop;
    private LatLng position;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initData() {
        initMarker();
        baiduMap.setOnMarkerClickListener(mOnMarkerClickListener);
        baiduMap.setOnMarkerDragListener(mOnMarkerDragListener);
    }

    BaiduMap.OnMarkerDragListener mOnMarkerDragListener = new BaiduMap.OnMarkerDragListener() {
        @Override
        public void onMarkerDrag(Marker marker) {
            //刷新布局
            mMapView.updateViewLayout(pop, getLayoutParams(marker.getPosition()));
        }

        @Override
        public void onMarkerDragEnd(Marker marker) {
            //刷新布局
            mMapView.updateViewLayout(pop, getLayoutParams(marker.getPosition()));
        }

        @Override
        public void onMarkerDragStart(Marker marker) {
            //刷新布局
            mMapView.updateViewLayout(pop, getLayoutParams(marker.getPosition()));
        }
    };

    //设置兴趣点的点击事件，弹出一个对话框
    BaiduMap.OnMarkerClickListener mOnMarkerClickListener = new BaiduMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            //复用pop这个view，不再重新生成
            if (pop == null) {
                pop = View.inflate(getApplicationContext(), R.layout.pop, null);
                //获取当前位置并设置标题
                TextView tv_title = (TextView) pop.findViewById(R.id.tv_title);
                tv_title.setText(marker.getTitle());
                position = marker.getPosition();
                //放入需要显示的view和布局参数
                mMapView.addView(pop, getLayoutParams(position));
            } else {
                //刷新布局
                mMapView.updateViewLayout(pop, getLayoutParams(marker.getPosition()));
            }
            return true;
        }
    };

    //获取地图标识点对话框的参数配置
    @NonNull
    private MapViewLayoutParams getLayoutParams(LatLng position) {
        MapViewLayoutParams.Builder builder = new MapViewLayoutParams.Builder();
        builder.layoutMode(MapViewLayoutParams.ELayoutMode.mapMode);        //配置地图模式为经纬度
        builder.yOffset(-100);                                              //设置标示偏移坐标y轴上方
        builder.position(position);                                         //设置对话框的显示位置
        MapViewLayoutParams params = builder.build();                       //创建参数
        return params;
    }

    private void initMarker() {
        MarkerOptions options = new MarkerOptions();
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.icon_eat);
        options.icon(icon).position(hmPos).draggable(true).title("黑马程序员培训基地");
        baiduMap.addOverlay(options);

        options = new MarkerOptions().icon(icon)
                .title("向北")
                .position(new LatLng(hmPos.latitude + 0.001, hmPos.longitude))
                .draggable(true);
        baiduMap.addOverlay(options);

        // 添加一个向东的标志
        options = new MarkerOptions().icon(icon)
                .title("向东")
                .position(new LatLng(hmPos.latitude, hmPos.longitude + 0.001))
                .draggable(true);
        baiduMap.addOverlay(options);

        // 添加一个向西南的标志
        options = new MarkerOptions().icon(icon)
                .title("向西南")
                .position(new LatLng(hmPos.latitude - 0.001, hmPos.longitude - 0.001))
                .draggable(true);
        baiduMap.addOverlay(options);
    }
}
