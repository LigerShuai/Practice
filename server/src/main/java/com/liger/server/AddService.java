package com.liger.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.liger.aidllib.IAddInterface;
import com.liger.aidllib.IServerListener;

/**
 * 在服务中实现AIDL中定义的方法
 *
 * @author zs
 * @date 2018/5/28 0028.
 */
public class AddService extends Service {

    private RemoteCallbackList<IServerListener> mCallbackList = new RemoteCallbackList<>();

    /**
     * AIDL 接口实现
     */
    private IBinder mBinder = new IAddInterface.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            int result = a + b;
            //已注册 IServerListener 的回调个数
            int count = mCallbackList.beginBroadcast();
            try {
                for (int i = 0; i < count; i++) {
                    mCallbackList.getBroadcastItem(i).onServerListener("成功");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mCallbackList.finishBroadcast();
            return result;
        }

        @Override
        public void registerListener(IServerListener listener) throws RemoteException {
            if (listener != null) {
                mCallbackList.register(listener);
            }
        }

        @Override
        public void unRegisterListener(IServerListener listener) throws RemoteException {
            if (listener != null) {
                mCallbackList.unregister(listener);
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
