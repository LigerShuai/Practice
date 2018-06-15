// IAddInterface.aidl
package com.liger.aidllib;
import com.liger.aidllib.IServerListener;

interface IAddInterface {

    int add(int a,int b);

    //向服务端注册，用于服务端回调数据
    void registerListener(IServerListener listener);

    //反注册
    void unRegisterListener(IServerListener listener);
}
