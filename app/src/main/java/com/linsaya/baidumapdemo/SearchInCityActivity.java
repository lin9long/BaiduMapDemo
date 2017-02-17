package com.linsaya.baidumapdemo;

import android.view.View;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;

/**
 * Created by Administrator on 2017/2/18.
 */

public class SearchInCityActivity extends SearchPoiBaseActivity {
    int pageNum;
    private PoiDetailSearchOption searchDetail;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                pageNum++;
                poiSearch.searchInCity(getSearchBound());
                break;
        }
    }

    @Override
    protected void initPoiSearch() {
        poiSearch.searchInCity(getSearchBound());
    }

    @Override
    public boolean onPoiClick(int i) {
        PoiInfo poiInfo = poiOverlay.getPoiResult().getAllPoi().get(i);
        poiSearch.searchPoiDetail(getSearchDetail(poiInfo.uid));
        return true;
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        if (poiDetailResult==null&&poiDetailResult.error!= SearchResult.ERRORNO.NO_ERROR){
            showToast("没有找到兴趣点详情哦！");
            return;
        }
        showToast(poiDetailResult.getTelephone()+","+poiDetailResult.getShopHours());
    }

    public PoiCitySearchOption getSearchBound() {
        PoiCitySearchOption option = new PoiCitySearchOption();
        option.city("北京");
        //添加搜索关键词
        option.keyword("餐厅");
        //设置每一页显示的个数
        option.pageCapacity(10);
        option.pageNum(pageNum);
        return option;
    }

    public PoiDetailSearchOption getSearchDetail(String uid) {
        PoiDetailSearchOption option = new PoiDetailSearchOption();
        option.poiUid(uid);
        return option;
    }
}
