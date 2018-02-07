package info.androidhive.floatingview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.security.acl.LastOwnerException;
import java.util.Date;

public class CallReceiver extends BroadcastReceiver {
private static boolean isRinging=false;
        Context context;

        @Override
        public void onReceive(Context context, Intent intent){
            Log.e("CHECK","WORKING");
            try{
                String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

                if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                    Toast.makeText(context, "Incoming", Toast.LENGTH_LONG).show();
                    context.startService(new Intent(context, FloatingViewService.class));
                    isRinging=true;

                }

                if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){

                    if(!isRinging)
                    {
                        Toast.makeText(context, "outgoing", Toast.LENGTH_LONG).show();
                        context.startService(new Intent(context, FloatingViewService.class));
                    }
                    else
                    {
                        Toast.makeText(context, "Incoming Answered", Toast.LENGTH_LONG).show();
                    }
                    //at time of call answer it goes at outgoing

                }

                if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                    Toast.makeText(context, "Phone Is Idle", Toast.LENGTH_LONG).show();
                    context.stopService(new Intent(context, FloatingViewService.class));
                    isRinging=false;
                }
            }
            catch(Exception e){
                Toast.makeText(context, (CharSequence) e, Toast.LENGTH_LONG).show();
                e.printStackTrace();}
        }

    }