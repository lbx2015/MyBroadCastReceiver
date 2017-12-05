package scrollview.custom.com.mybroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by zw.zhang on 2017/12/5.
 */

public abstract class ZClickListener implements View.OnClickListener {
    static ArrayList<WeakReference<ZClickListener>> list = new ArrayList<>();
    View v;

    ZClickListener() {
        list.add(new WeakReference<ZClickListener>(this));
    }

    @Override
    public void onClick(View v) {
        this.v = v;
        click(v);
    }

    public abstract void click(View v);


    public static class MyReceiver extends BroadcastReceiver {

        private static final String TAG = "MyReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("msg");
            Log.i(TAG, msg);
            for (WeakReference<ZClickListener> weakReference : list) {
                ZClickListener z = weakReference.get();
                if (z != null) {
                    z.click(z.v);
                }
            }
        }

    }
}
