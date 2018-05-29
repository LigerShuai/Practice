package com.liger.practice.aidlpractice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.liger.practice.R;
import com.liger.server.IAddInterface;

public class AIDLActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mBindTv, mUnbindTv, mResultTv;

    private IAddInterface mIAddInterface;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIAddInterface = IAddInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIAddInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        mBindTv = findViewById(R.id.bind_tv);
        mUnbindTv = findViewById(R.id.unbind_tv);
        mResultTv = findViewById(R.id.result_tv);
        mBindTv.setOnClickListener(this);
        mUnbindTv.setOnClickListener(this);
        bindService();
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.liger.server", "com.liger.server.AddService"));
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind_tv:
                try {
                    int result = mIAddInterface.add(10, 22);
                    mResultTv.setText("结果: " + result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.unbind_tv:
//                unbindService(mConnection);
                break;
            default:
                break;
        }
    }
}
