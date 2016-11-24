package com.example.renpeng.messengerdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by renpeng on 16/11/24.
 */
public class MessengerService extends Service{

    private static final String TAG = "renpengxxxx";

    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Log.i(TAG,"receive msg from client"+msg.getData().getString("msg"));
                    Messenger cleint = msg.replyTo;
                    Message message = Message.obtain(null,2);
                    Bundle data = new Bundle();
                    data.putString("reply","发二娃萨芬");
                    message.setData(data);
                    try {
                        cleint.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private Messenger mMessenger = new Messenger(new MessengerHandler());
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
