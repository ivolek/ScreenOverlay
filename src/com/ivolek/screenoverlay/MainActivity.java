package com.ivolek.screenoverlay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class MainActivity extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		Intent service= new Intent(context, OverlayService.class);
        // Try to stop the service if it is already running
        // Otherwise start the service
        	context.startService(service);
	}

}