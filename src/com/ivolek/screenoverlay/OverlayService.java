package com.ivolek.screenoverlay;

import com.ivolek.screenoverlay.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings.SettingNotFoundException;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

public class OverlayService extends Service {

	LinearLayout oView;
	boolean notificationOn = false;
	boolean viewOn = false;

	@Override
	public IBinder onBind(Intent i) {
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		try {
			int curBrightnessValue = android.provider.Settings.System.getInt(
					getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);

			if(curBrightnessValue == 20){
				setScreenFilter(0);
				createNotification();
			}
		}
		catch (SettingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SettingsContentObserver mSettingsContentObserver = new SettingsContentObserver( new Handler() ); 
		this.getApplicationContext().getContentResolver().registerContentObserver( 
				android.provider.Settings.System.CONTENT_URI, true, 
				mSettingsContentObserver );
		
		return START_STICKY;
	}
	WindowManager wm;
	private void setScreenFilter(int color){
		if(color == 0){
			try{
			WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
			wm.removeView(oView);}
			catch(Exception e){}
			
			viewOn = false;
		}
		else{
			
			WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
				WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
				PixelFormat.TRANSLUCENT);       
		 WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		 
			if(!viewOn){
				oView = new LinearLayout(this);   
				oView.setBackgroundColor(color);
				wm.addView(oView, params);
				viewOn = true;
			}
			else{
				oView.setBackgroundColor(color);
				wm.updateViewLayout(oView, params);
			}


		}
	}

	private void createNotification(){
		String ns = this.NOTIFICATION_SERVICE;
		NotificationManager notificationManager = (NotificationManager) getSystemService(ns);

		Notification notification = new Notification(R.drawable.ic_launcher, null, System.currentTimeMillis());
		RemoteViews notificationView = new RemoteViews(getPackageName(), R.layout.activity_main);
		//the intent that is started when the notification is clicked (works)
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

		notification.contentView = notificationView;
		notification.contentIntent = pendingNotificationIntent;
		notification.flags |= Notification.FLAG_NO_CLEAR;

		//this is the intent that is supposed to be called when the button is clicked
		Intent switchIntent1 = new Intent("button1_pressed");
		PendingIntent pendingSwitchIntent1 = PendingIntent.getBroadcast(this, 0, switchIntent1, 0);
		notificationView.setOnClickPendingIntent(R.id.button1, pendingSwitchIntent1);

		Intent switchIntent2 = new Intent("button2_pressed");
		PendingIntent pendingSwitchIntent2 = PendingIntent.getBroadcast(this, 0, switchIntent2, 0);
		notificationView.setOnClickPendingIntent(R.id.button2, pendingSwitchIntent2);

		Intent switchIntent3 = new Intent("button3_pressed");
		PendingIntent pendingSwitchIntent3 = PendingIntent.getBroadcast(this, 0, switchIntent3, 0);
		notificationView.setOnClickPendingIntent(R.id.button3, pendingSwitchIntent3);

		Intent switchIntent4 = new Intent("button4_pressed");
		PendingIntent pendingSwitchIntent4 = PendingIntent.getBroadcast(this, 0, switchIntent4, 0);
		notificationView.setOnClickPendingIntent(R.id.button4, pendingSwitchIntent4);

		Intent switchIntent5 = new Intent("button5_pressed");
		PendingIntent pendingSwitchIntent5 = PendingIntent.getBroadcast(this, 0, switchIntent5, 0);
		notificationView.setOnClickPendingIntent(R.id.button5, pendingSwitchIntent5);

		Intent switchIntent6 = new Intent("button6_pressed");
		PendingIntent pendingSwitchIntent6 = PendingIntent.getBroadcast(this, 0, switchIntent6, 0);
		notificationView.setOnClickPendingIntent(R.id.button6, pendingSwitchIntent6);

		Intent switchIntent7 = new Intent("button7_pressed");
		PendingIntent pendingSwitchIntent7 = PendingIntent.getBroadcast(this, 0, switchIntent7, 0);
		notificationView.setOnClickPendingIntent(R.id.button7, pendingSwitchIntent7);

		Intent switchIntent8 = new Intent("button8_pressed");
		PendingIntent pendingSwitchIntent8 = PendingIntent.getBroadcast(this, 0, switchIntent8, 0);
		notificationView.setOnClickPendingIntent(R.id.button8, pendingSwitchIntent8);

		Intent switchIntent9 = new Intent("button9_pressed");
		PendingIntent pendingSwitchIntent9 = PendingIntent.getBroadcast(this, 0, switchIntent9, 0);
		notificationView.setOnClickPendingIntent(R.id.button9, pendingSwitchIntent9);

		Intent switchIntent10 = new Intent("button10_pressed");
		PendingIntent pendingSwitchIntent10 = PendingIntent.getBroadcast(this, 0, switchIntent10, 0);
		notificationView.setOnClickPendingIntent(R.id.button10, pendingSwitchIntent10);		

		Intent switchIntent11 = new Intent("button11_pressed");
		PendingIntent pendingSwitchIntent11 = PendingIntent.getBroadcast(this, 0, switchIntent11, 0);
		notificationView.setOnClickPendingIntent(R.id.button11, pendingSwitchIntent11);

		switchButtonListener switchButtonListener = new switchButtonListener();
		registerReceiver(switchButtonListener, new IntentFilter("button1_pressed"));
		registerReceiver(switchButtonListener, new IntentFilter("button2_pressed"));
		registerReceiver(switchButtonListener, new IntentFilter("button3_pressed"));
		registerReceiver(switchButtonListener, new IntentFilter("button4_pressed"));
		registerReceiver(switchButtonListener, new IntentFilter("button5_pressed"));
		registerReceiver(switchButtonListener, new IntentFilter("button6_pressed"));
		registerReceiver(switchButtonListener, new IntentFilter("button7_pressed"));
		registerReceiver(switchButtonListener, new IntentFilter("button8_pressed"));
		registerReceiver(switchButtonListener, new IntentFilter("button9_pressed"));
		registerReceiver(switchButtonListener, new IntentFilter("button10_pressed"));
		registerReceiver(switchButtonListener, new IntentFilter("button11_pressed"));
		notificationManager.notify(1, notification);        
		
		notificationOn = true;
	}
	
	public class switchButtonListener extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			int color = 0;
			if(intent.getAction() == "button1_pressed"){}
			if(intent.getAction() == "button2_pressed")
				color = 0x14000000;
			if(intent.getAction() == "button3_pressed")
				color = 0x29000000;
			if(intent.getAction() == "button4_pressed")
				color = 0x3e000000;
			if(intent.getAction() == "button5_pressed")
				color = 0x53000000;
			if(intent.getAction() == "button6_pressed")
				color = 0x68000000;
			if(intent.getAction() == "button7_pressed")
				color = 0x7d000000;
			if(intent.getAction() == "button8_pressed")
				color = 0x92000000;
			if(intent.getAction() == "button9_pressed")
				color = 0xa7000000;
			if(intent.getAction() == "button10_pressed")
				color = 0xbc000000;
			if(intent.getAction() == "button11_pressed")
				color = 0xd1000000; 
			setScreenFilter(color);		
		}

	}

	@Override
	public void onCreate() {
		super.onCreate();


	}

	@Override
	public void onDestroy() {           
		super.onDestroy();
		if(oView!=null){
			WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
			wm.removeView(oView);
			viewOn = false;
		}
	}

	public class SettingsContentObserver extends ContentObserver {

		public SettingsContentObserver(Handler handler) {
			super(handler);
		} 

		@Override
		public boolean deliverSelfNotifications() {
			return super.deliverSelfNotifications(); 
		}

		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			float curBrightnessValue = 0;
			try {
				curBrightnessValue = android.provider.Settings.System.getInt(
						getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);

				if(curBrightnessValue == 20){
					setScreenFilter(0x00000000);
					createNotification();
				}
				else if (curBrightnessValue != 20 && notificationOn){
					try{
						WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
						wm.removeView(oView);}
						catch(Exception e){}
					notificationOn = false;
					viewOn = false;
					NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
					notificationManager.cancelAll();
				}			
			} 
			catch (SettingNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


}