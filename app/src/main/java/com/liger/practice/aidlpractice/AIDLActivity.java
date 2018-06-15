package com.liger.practice.aidlpractice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.liger.aidllib.IAddInterface;
import com.liger.aidllib.IServerListener;
import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;

public class AIDLActivity extends BaseActivity implements View.OnClickListener {

    private TextView mResultTv;

    private IAddInterface mIAddInterface;

    /**
     * 服务和aidl接口绑定
     */
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
        mResultTv = findViewById(R.id.result_tv);
        findViewById(R.id.bind_btn).setOnClickListener(this);
        findViewById(R.id.bind_callback_btn).setOnClickListener(this);
        findViewById(R.id.unbind_btn).setOnClickListener(this);
        bindService();
    }

    private void connectRemoteService(String pkg, String cls) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(pkg, cls));
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.liger.server", "com.liger.server.AddService"));
        bindService(intent, mConnection, BIND_AUTO_CREATE);
//        intent.setAction("");
//        intent.setPackage("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind_btn:
                try {
                    int result = mIAddInterface.add(10, 22);
                    mResultTv.setText("结果: " + result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bind_callback_btn:
                try {
                    mIAddInterface.registerListener(new IServerListener.Stub() {
                        @Override
                        public void onServerListener(String result) throws RemoteException {
                            mResultTv.setText("结果: " + result);
                        }
                    });
                    mIAddInterface.add(33, 33);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.unbind_btn:
//                unbindService(mConnection);
                break;
            default:
                break;
        }
    }
}
