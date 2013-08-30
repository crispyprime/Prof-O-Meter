package com.duckysoftware.profometer;

import android.app.Activity;
import android.os.Bundle;

public class results extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerlayout);
		Bundle bundle = getIntent().getExtras();
		
		String query = bundle.getString("Completed Query");
	}
}
