package com.linsaya.baidumapdemo;

import android.view.View;

import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.linsaya.baidumapdemo.overlayutil.PoiOverlay;

/**
 * Created by Administrator on 2017/2/17.
 */

public class SearchInBoundActivity extends SearchPoiBaseActivity implements OnGetPoiSearchResultListener {
    private PoiBoundSearchOption searchBound;

    //初始化搜索参数
    @Override
    protected void initPoiSearch() {
        poiSearch.searchInBound(getSearchBound());
    }

    public PoiBoundSearchOption getSearchBound() {
        PoiBoundSearchOption prame = new PoiBoundSearchOption();
//        西南（左下）：40.048459,116.302072
//        东北（右上）：40.050675,116.304317
        //设置检索的范围，西南角和东北角的坐标
        LatLngBounds bounds = new LatLngBounds.Builder().include(new LatLng(40.048459, 116.302072)).include(new LatLng(40.050675, 116.304317)).build();
        prame.bound(bounds);
        //添加搜索关键词
        prame.keyword("餐厅");
        return prame;
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }
}
