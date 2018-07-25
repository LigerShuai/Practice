package com.liger.practice.greendao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;
import com.liger.practice.constant.RouterConstant;

import java.util.List;

/**
 * @author zs
 * @date 2018/7/24 0024.
 */

@Route(path = RouterConstant.DB_ACTIVITY)
public class DbActivity extends BaseActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        mTextView = findViewById(R.id.activity_db_tv);

        getData();
    }

    private void getData() {
        //数据库操作需在子线程,此处在省略
        List<User> list = GreenDaoHelper.getInstance().queryAll();
        if (list != null && !list.isEmpty()) {
            mTextView.setText(list.get(0).getName() + "\n" + list.get(1).getName() + "\n" + list.get(2).getName());
            /*for (User user : list) {
                mTextView.setText(user.getName());
            }*/
        }
    }
}
