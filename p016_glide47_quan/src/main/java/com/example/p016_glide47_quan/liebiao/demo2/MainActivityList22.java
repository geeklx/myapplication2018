package com.example.p016_glide47_quan.liebiao.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.p016_glide47_quan.R;
import com.example.p016_glide47_quan.liebiao.Biaoge_listBean;

import java.util.ArrayList;
import java.util.List;


public class MainActivityList22 extends AppCompatActivity {

    //数据解析bufen
    private RecyclerView recyclerView;
    private RecycleAdapter22 mAdapter;
    private List<Biaoge_listBean> mratings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_glide_list);
        donetwork();

    }

    private void donetwork() {
        recyclerView = findViewById(R.id.recycler_view1);
        mAdapter = new RecycleAdapter22(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        mratings = getList(1);
        mAdapter.setContacts(mratings);
        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemViewClick(new RecycleAdapter22.OnItemViewClick() {
            @Override
            public void onclick(View view, int pos, String id) {
                Biaoge_listBean bean = (Biaoge_listBean) mAdapter.getItem(pos);
                if (id.equals(currentPos)) {
                    currentPos = "-1";
                    bean.setEnchoose(false);
                } else {
                    currentPos = id;
                    bean.setEnchoose(true);
                    //其他为false
                    for (Biaoge_listBean bean2 : mAdapter.getMratings()) {
                        if (!bean2.getText_content1().equals(id)) {
                            bean2.setEnchoose(false);
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        mAdapter.setOnItemViewClick2(new RecycleAdapter22.OnItemViewClick2() {
            @Override
            public void onclick2(View view, int pos) {
                Biaoge_listBean bean = (Biaoge_listBean) mAdapter.getItem(pos);
                mratings.remove(bean);
                if (mratings.size() == 0) {
                    Toast.makeText(MainActivityList22.this, "这是最后一个item", Toast.LENGTH_LONG).show();
                }
                mAdapter.setSelectedPosition(-1);
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

