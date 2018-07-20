package com.example.p016_glide47_quan.liebiao.demo4;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p016_glide47_quan.R;
import com.example.p016_glide47_quan.liebiao.Biaoge_listBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MainActivityList24 extends AppCompatActivity {

    private TextView tv11;
    private Button btn11;
    //数据解析bufen
    private RecyclerView recyclerView;
    private RecycleAdapter24 mAdapter;
    private List<Biaoge_listBean> mratings;
    private List<Biaoge_listBean> send_list = new ArrayList<>();// 提交服务器的list
    private String send_ids = "";// 提交服务器的腰删除的ids

    /**
     * 设置全选的状态tv_drawable_leftbufen
     */
    private void shezhi_tv_drawable(int drawable_r, boolean is_cho) {
        Drawable drawable = getResources().getDrawable(drawable_r);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv11.setCompoundDrawables(drawable, null, null, null);
        //收集ID的选择状态设置bufen
        for (int i = 0; i < mAdapter.getMratings().size(); i++) {
            Biaoge_listBean ratings = mAdapter.getMratings().get(i);
            if (is_cho) {
                if (!ratings.isEnchoose()) {
                    ratings.setEnchoose(is_cho);
                    mAdapter.list_addone(ratings);
                }
            } else {
                ratings.setEnchoose(is_cho);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_glide_list24);
        tv11 = findViewById(R.id.tv11);
        tv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 设置全选bufen
                mAdapter.setEnallchoose(true);// 显示勾勾布局bufen
                if (mAdapter.list_getall() == null || mAdapter.list_getall().size() == 0) {
                    //设置全选 收集ID的选择状态设置 true bufen
                    shezhi_tv_drawable(R.drawable.iv_sku_checked, true);
                } else {
                    //设置非全选 非全选两种情况 1.全选 2.部分全选
                    if (mAdapter.list_getall().size() == mAdapter.getMratings().size()) {
                        //删除IDbufen
                        mAdapter.list_clearall();
                        //1 设置为非全选 清除收集ID的选择状态 false bufen
                        shezhi_tv_drawable(R.drawable.iv_sku_unchecked, false);
                    } else {
                        //2 设置为全选 收集ID的选择状态设置 true bufen
                        shezhi_tv_drawable(R.drawable.iv_sku_checked, true);
                    }
                }
                mAdapter.notifyDataSetChanged();
                send_list = mAdapter.list_getall();
                send_ids = mAdapter.list_getallstring(send_list);
                Toast.makeText(MainActivityList24.this, send_list.size() + "", Toast.LENGTH_LONG).show();
            }
        });
        btn11 = findViewById(R.id.btn11);
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 批量删除bufen
                if (mAdapter.list_getall() == null || mAdapter.list_getall().size() == 0) {
                    Toast.makeText(MainActivityList24.this, "请至少选择一个商品", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(MainActivityList24.this, "向服务器提交数据:"+mAdapter.list_getallstring(mAdapter.list_getall()), Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //activity
                        shezhi_quanxuan_gougou(tv11, 0, R.drawable.iv_sku_unchecked, R.drawable.iv_sku_checked);
                        tv11.setVisibility(View.GONE);
                        btn11.setVisibility(View.GONE);

                        // 此处应该请求接口刷新 这里用假数据
                        Collection exists = new ArrayList<Biaoge_listBean>(mratings);
                        exists.removeAll(mAdapter.list_getall());
                        List<Biaoge_listBean> lists = (List<Biaoge_listBean>) exists;
                        mAdapter.setContacts(lists);

                        // adapter
                        mAdapter.list_clearall();
                        for (Biaoge_listBean bean : mAdapter.getMratings()) {
                            bean.setEnchoose(false);
                        }
                        mAdapter.setEnallchoose(false);

                        mAdapter.notifyDataSetChanged();
                    }
                }, 3000);
            }
        });
        donetwork();

    }

    private void donetwork() {
        recyclerView = findViewById(R.id.recycler_view1);
        mAdapter = new RecycleAdapter24(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        mratings = getList(1);
        mAdapter.setContacts(mratings);
        mAdapter.notifyDataSetChanged();
        // 长按设置全选bufen
        mAdapter.setOnItemViewClick(new RecycleAdapter24.OnItemViewClick() {
            @Override
            public void onclick(View view, int pos, String id) {
                Biaoge_listBean bean = (Biaoge_listBean) mAdapter.getItem(pos);
                if (!mAdapter.isEnallchoose()) {
                    tv11.setVisibility(View.VISIBLE);
                    mAdapter.setEnallchoose(true);
                } else {
                    tv11.setVisibility(View.GONE);
                    mAdapter.setEnallchoose(false);
                }
                shezhi_quanxuan_gougou(tv11, 0, R.drawable.iv_sku_unchecked, R.drawable.iv_sku_checked);
                for (Biaoge_listBean bean1 : mAdapter.getMratings()) {
                    bean1.setEnchoose(false);
                }
                mAdapter.list_clearall();
                mAdapter.notifyDataSetChanged();
            }
        });
        // 批量选中bufen
        mAdapter.setOnItemViewClick2(new RecycleAdapter24.OnItemViewClick2() {
            @Override
            public void onclick2(View view, int pos, boolean enchoose) {
                Biaoge_listBean bean = (Biaoge_listBean) mAdapter.getItem(pos);
                if (!enchoose) {
                    //设置已选状态bufen
                    ((ImageView) view).setBackgroundResource(R.drawable.iv_sku_checked);
                    bean.setEnchoose(true);
                    mAdapter.list_addone(bean);
                } else {
                    //设置未选状态bufen
                    ((ImageView) view).setBackgroundResource(R.drawable.iv_sku_unchecked);
                    bean.setEnchoose(false);
                    mAdapter.list_removeone(bean);
                }
                // 设置全选bufen
                if (mAdapter.list_getall().size() == mAdapter.getMratings().size()) {
                    shezhi_quanxuan_gougou(tv11, 1, R.drawable.iv_sku_unchecked, R.drawable.iv_sku_checked);
                } else {
                    shezhi_quanxuan_gougou(tv11, 0, R.drawable.iv_sku_unchecked, R.drawable.iv_sku_checked);
                }
                mAdapter.notifyDataSetChanged();
                send_list = mAdapter.list_getall();
                send_ids = mAdapter.list_getallstring(send_list);
                Toast.makeText(MainActivityList24.this, send_list.size() + "", Toast.LENGTH_LONG).show();
            }
        });
        // 全选按钮回调状态bufen
        mAdapter.setOnItemViewClick3(new RecycleAdapter24.OnItemViewClick3() {
            @Override
            public void onclick3(boolean engougou) {
                //是否显示清除按钮
                if (engougou) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btn11.setVisibility(View.VISIBLE);
                        }
                    },100);
                } else {
                    btn11.setVisibility(View.GONE);
                }
            }
        });

    }

    /**
     * 设置全选布局bufen 0不勾 1 勾勾    shezhi_quanxuan_gougou(tv1, 0, R.drawable.iv_sku_unchecked,R.drawable.iv_sku_checked);
     *
     * @param is_gou
     */
    public void shezhi_quanxuan_gougou(TextView tv1, int is_gou, int... dra) {
        if (is_gou == 0) {
            Drawable drawable = getResources().getDrawable(dra[0]);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv1.setCompoundDrawables(drawable, null, null, null);
        } else if (is_gou == 1) {
            Drawable drawable = getResources().getDrawable(dra[1]);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv1.setCompoundDrawables(drawable, null, null, null);
        }
    }

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

