package com.liger.practice.adview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liger
 * @date 2018/6/20 00:11
 */
public class AdViewActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private AdViewAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adview);
        mRecyclerView = findViewById(R.id.activity_adview_rv);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        for (int i = 0; i < 100; i++) {
            mList.add(i + " 当悦听进行后台播放返回桌面时，右上方显示缩略播放器模块模块显示应用名称专辑图片节目名称进度条。当悦聊—直播进行后台播放返回桌面时，右上方显示缩略播放器模块模块显示应用名称专辑图片节目名称进度条。" +
                    "当悦听进行后台播放返回桌面时，右上方显示缩略播放器模块模块显示应用名称专辑图片节目名称进度条。当悦聊—直播进行后台播放返回桌面时，右上方显示缩略播放器模块模块显示应用名称专辑图片节目名称进度条。");
        }
        mAdapter = new AdViewAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int fPos = mLinearLayoutManager.findFirstVisibleItemPosition();
                int lPos = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                for (int i = fPos; i <= lPos; i++) {
                    View view = mLinearLayoutManager.findViewByPosition(i);
                    AdImageView adImageView = view.findViewById(R.id.item_adview_adimg);
                    if (adImageView.getVisibility() == View.VISIBLE) {
                        adImageView.setDy(mLinearLayoutManager.getHeight() - view.getTop());
                    }
                }
            }
        });
    }

}
