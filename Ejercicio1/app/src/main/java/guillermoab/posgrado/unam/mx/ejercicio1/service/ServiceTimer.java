package guillermoab.posgrado.unam.mx.ejercicio1.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by GuillermoAB on 23/06/2016.
 */
public class ServiceTimer extends Service {
    public static final String TAG="service_tag";
    public static final String ACTION_SEND_TIMER="guillermoab.posgrado.unam.mx.SEND_TIMER";
    int counter;
    private Handler handler = new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            counter++;
            handler.postDelayed(runnable,1000);
            Intent i = new Intent(ACTION_SEND_TIMER);
            i.putExtra("timer",counter);
            sendBroadcast(i);
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler.post(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
