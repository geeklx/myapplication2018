package com.example.p016_glide47_quan.liebiao.demo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p016_glide47_quan.R;
import com.example.p016_glide47_quan.liebiao.Biaoge_listBean;

import java.util.ArrayList;
import java.util.List;


public class MainActivityList23 extends AppCompatActivity {

    private TextView tv11;
    //数据解析bufen
    private RecyclerView recyclerView;
    private RecycleAdapter23 mAdapter;
    private List<Biaoge_listBean> mratings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_glide_list23);
        tv11 = findViewById(R.id.tv11);
        donetwork();

    }

    private void donetwork() {
        recyclerView = findViewById(R.id.recycler_view1);
        mAdapter = new RecycleAdapter23(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        mratings = getList(1);
        mAdapter.setContacts(mratings);
        mAdapter.notifyDataSetChanged();

        // 全选bufen
        mAdapter.setOnItemViewClick(new RecycleAdapter23.OnItemViewClick() {
            @Override
            public void onclick(View view, int pos, String id) {
                Biaoge_listBean bean = (Biaoge_listBean) mAdapter.getItem(pos);
                if (mAdapter.isEnallchoose()){
                    tv11.setText("非全选");
                    mAdapter.setEnallchoose(false);
                }else {
                    tv11.setText("全选");
                    mAdapter.setEnallchoose(true);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        //  选择删除bufen
        mAdapter.setOnItemViewClick2(new RecycleAdapter23.OnItemViewClick2() {
            @Override
            public void onclick2(View view, int pos) {
                Biaoge_listBean bean = (Biaoge_listBean) mAdapter.getItem(pos);
                mratings.remove(bean);
                if (mratings.size() == 0) {
                    Toast.makeText(MainActivityList23.this, "这是最后一个item", Toast.LENGTH_LONG).show();
                }
                mAdapter.setContacts(mratings);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    private String currentPos = "-1";

    private List<Biaoge_listBean> getList(int pos) {
        List<Biaoge_listBean> mratings = new ArrayList<>();
        for (int i = 0; i < pos; i++) {
            mratings.add(new Biaoge_listBean("11", "https://s3.51cto.com/wyfs02/M01/89/BA/wKioL1ga-u7QnnVnAAAfrCiGnBQ946_middle.jpg", false));
            mratings.add(new Biaoge_listBean("22", "", false));
            mratings.add(new Biaoge_listBean("33", "https://img.zcool.cn/community/013bce592505d3b5b3086ed49f70e6.gif", false));
            mratings.add(new Biaoge_listBean("44", "https://s3.51cto.com/wyfs02/M01/89/BA/wKioL1ga-u7QnnVnAAAfrCiGnBQ946_middle.jpg", false));
            mratings.add(new Biaoge_listBean("55", "https://img.zcool.cn/community/013bce592505d3b5b3086ed49f70e6.gif", false));
            mratings.add(new Biaoge_listBean("66", "https://s3.51cto.com/wyfs02/M01/89/BA/wKioL1ga-u7QnnVnAAAfrCiGnBQ946_middle.jpg", false));
        }
        return mratings;
    }

}

