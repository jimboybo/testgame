package com.itaobox.game.testgame;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Context context= this;
		LinearLayout line = (LinearLayout)findViewById(R.id.line1);
		
		Button btn = new Button(context);
		btn.setText("ItaoBox Sprite");
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent = new Intent();  
	             intent.setClass(MainActivity.this, SampleItaoboxSprite.class);  
	             startActivity(intent);
			}
		});
		line.addView(btn);
		
		Button btn1 = new Button(context);
		btn1.setText("Game1");
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent = new Intent();  
	             intent.setClass(MainActivity.this, Game1.class);  
	             startActivity(intent);
			}
		});
		line.addView(btn1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
