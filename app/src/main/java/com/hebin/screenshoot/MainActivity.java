package com.hebin.screenshoot;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.btn_scrollview)
    Button btnScrollview;
    @InjectView(R.id.btn_recyclerview)
    Button btnRecyclerview;
    @InjectView(R.id.rv_list)
    RecyclerView rvList;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;
    @InjectView(R.id.sv_main)
    ScrollView svMain;


    List<DataEntity> list = new ArrayList<>();
    Context context;
    String path = Environment.getExternalStorageDirectory().getPath();
    @InjectView(R.id.iv_screen)
    ImageView ivScreen;
    @InjectView(R.id.tv_tip)
    TextView tvTip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        this.context = this;

        for (int i = 0; i < 20; i++) {
            DataEntity dataEntity = new DataEntity();
            dataEntity.setTitle("第" + i + "项");
            list.add(dataEntity);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(manager);
        RecyclerAdapter adapter = new RecyclerAdapter(this, list);
        rvList.setAdapter(adapter);
    }

    @OnClick({R.id.btn_scrollview, R.id.btn_recyclerview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_scrollview:
                tvTip.setText("我是scrollview的截图");
                ivScreen.setImageBitmap(ScreenShoot.getScrollViewBitmap(svMain, path + "/scrollview.jpg"));
                break;
            case R.id.btn_recyclerview:
                tvTip.setText("我是recyclerview的截图");
                ivScreen.setImageBitmap(ScreenShoot.getRecyclerviewBitmap(rvList, path + "/recyclerview.jpg"));
                break;
        }
    }

}
