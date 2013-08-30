package com.duckysoftware.profometer;

import android.app.Activity;
import android.os.Bundle;

public class Browse extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browselayout);
		
		Bundle bundle = getIntent().getExtras();
		
		//need to pull the query out of the bundle.
		//Then run the query and dump it into the list view.
	}
}
