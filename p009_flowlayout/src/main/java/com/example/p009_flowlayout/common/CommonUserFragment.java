package com.example.p009_flowlayout.common;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.p009_flowlayout.R;
import com.example.p009_flowlayout.common.domain.LabOne;
import com.example.p009_flowlayout.common.domain.PackageOneKeyBuyBeanNew;

import java.util.ArrayList;
import java.util.List;

public class CommonUserFragment extends Fragment {

    //数据解析bufen
    private RecyclerView recyclerView;
    private RecycleAdapter1 mAdapter;
    private List<PackageOneKeyBuyBeanNew> mratings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_recyclelistview, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        findView(view);
        addlisteners();
        DataFirst();
    }

    private void DataFirst() {
        mratings = new ArrayList<PackageOneKeyBuyBeanNew>();
        //假数据bufen
        LabOne lo = new LabOne();
        mratings = lo.getmParent_model1();
        mAdapter.setContacts(mratings);
        mAdapter.notifyDataSetChanged();
    }

    private void createAdapter() {
        mAdapter = new RecycleAdapter1(getActivity());
        LinearLayoutManager mLinearLayoutManager1 = new LinearLayoutManager(getActivity());
        mLinearLayoutManager1.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager1);
        recyclerView.setAdapter(mAdapter);
    }

    private void findView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view1);
        createAdapter();
    }


    private void addlisteners() {
        //按分类
        mAdapter.setOnItemClickLitener(new RecycleAdapter1.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                PackageOneKeyBuyBeanNew listItem = (PackageOneKeyBuyBeanNew)
                        mAdapter.getItem(position);
                //请求服务器部分
                Toast.makeText(getActivity(), listItem.getGoods_id(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
