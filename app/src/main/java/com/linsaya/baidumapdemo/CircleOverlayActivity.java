package com.linsaya.baidumapdemo;

import android.view.View;

import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Stroke;

/**
 * Created by Administrator on 2017/2/17.
 */

public class CircleOverlayActivity extends BaseActivity {
    @Override
    public void onClick(View v) {

    }

    @Override
    public void initData() {
        CircleOptions options = new CircleOptions();
        options.center(czPos).//设置中心点
                radius(500).//设置半径
                stroke(new Stroke(50,0x55ff0000)).//设置圆的边线宽度和颜色
                fillColor(0x5500ff00);//设置填充颜色
        baiduMap.addOverlay(options);
    }
}
