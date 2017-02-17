package com.linsaya.baidumapdemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/2/17.
 */

public class DemoListActivity extends ListActivity {

    ClassAndName[] datas = {new ClassAndName(MainActivity.class, "MainActivity"), new ClassAndName(MapLayerActivity.class, "图层切换"),
            new ClassAndName(CircleOverlayActivity.class, "圆形覆盖物"), new ClassAndName(TextOverlayActivity.class, "文字覆盖物"),
            new ClassAndName(MarkerActivity.class, "标志物覆盖物"),new ClassAndName(SearchInBoundActivity.class, "兴趣点搜索"),
            new ClassAndName(SearchInCityActivity.class, "城市搜索")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ClassAndName classAndName = (ClassAndName) l.getItemAtPosition(position);
        Class<?> clazz = classAndName.clazz;
        startActivity(new Intent(DemoListActivity.this, clazz));
    }

    class ClassAndName {
        public ClassAndName(Class<?> clazz, String name) {
            this.clazz = clazz;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        private Class<?> clazz;
        private String name;
    }
}
