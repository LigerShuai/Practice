package com.liger.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.liger.aidllib.IAddInterface;

/**
 * @author zs
 * @date 2018/5/28 0028.
 */
public class AddService extends Service {

    private IBinder mBinder = new IAddInterface.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
