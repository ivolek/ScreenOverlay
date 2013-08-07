package com.ivolek.screenoverlay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MainActivity extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("TAG", "hij komt hier main");
//		Intent service = new Intent(context, OrientationService.class);
//		context.startService(service); 
		
		Intent service= new Intent(context, OverlayService.class);
        // intent.putExtra("color", "0");
        // Try to stop the service if it is already running
        // Otherwise start the service
        if(!context.stopService(service)){
        	context.startService(service);
        }
	}

//    private void toggleService(){
//        Intent intent= new Intent(this, OverlayService.class);
//        // intent.putExtra("color", "0");
//        // Try to stop the service if it is already running
//        // Otherwise start the service
//        if(!stopService(intent)){
//            startService(intent);
//        }
//    }
}
