package com.linsaya.baidumapdemo;

import android.view.View;

import com.baidu.mapapi.map.TextOptions;

/**
 * Created by Administrator on 2017/2/17.
 */

public class TextOverlayActivity extends BaseActivity {
    @Override
    public void onClick(View v) {

    }

    @Override
    public void initData() {
        TextOptions options = new TextOptions();
        options.position(czPos).text("传智播客").fontSize(50).fontColor(0x5500ff00).bgColor(0x55ff00ff);
        baiduMap.addOverlay(options);
    }
}
