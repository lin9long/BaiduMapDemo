package com.linsaya.baidumapdemo;

import android.view.View;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.linsaya.baidumapdemo.overlayutil.PoiOverlay;

/**
 * Created by Administrator on 2017/2/18.
 */

public abstract class SearchPoiBaseActivity extends BaseActivity implements OnGetPoiSearchResultListener {

    protected PoiSearch poiSearch;
    protected PoiOverlay poiOverlay;

    @Override
    public void onClick(View v) {

    }

    @Override
    public final void initData() {
        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(this);
        initPoiSearch();
        //讲此方法抽取出来,防止每次获取一个兴趣点结果时重复生成PoiOverlay对象
        //此处要复写onPoiClick的方法，并且弹出toast显示相关的信息
        poiOverlay = new PoiOverlay(baiduMap) {
            @Override
            public boolean onPoiClick(int i) {
                //此处对onPoiClick进行抽取，使子类能复写该方法，获取详情界面
                return SearchPoiBaseActivity.this.onPoiClick(i);
            }
        };
        //设置兴趣点的单击事件，因为兴趣点可能存在多个，所以需要重写poi的点击事件
        baiduMap.setOnMarkerClickListener(poiOverlay);
    }

    //
    public boolean onPoiClick(int i) {
        PoiInfo poiInfo = poiOverlay.getPoiResult().getAllPoi().get(i);
        showToast(poiInfo.address + "," + poiInfo.name);
        return true;
    }

    protected abstract void initPoiSearch();

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        //如果当前兴趣点结果返回为空，或者错误码为错误时，返回方法不再执行
        if (poiResult == null || poiResult.error != SearchResult.ERRORNO.NO_ERROR) {
            showToast("没有找到兴趣点哦！");
            return;
        }
        poiOverlay.setData(poiResult);
        poiOverlay.zoomToSpan();
        poiOverlay.addToMap();
        //baiduMap.addOverlay()
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }
}
