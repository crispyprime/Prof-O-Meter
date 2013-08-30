package com.duckysoftware.profometer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Search extends Activity implements View.OnClickListener
{	
	EditText prof, coursenum;
	Spinner mingrade, dept;
	Button search, reset;
	
	 public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.searchlayout);
	        
	        prof = (EditText)findViewById(R.id.EditProf);
	        coursenum = (EditText)findViewById(R.id.EditCourseNum);
	        mingrade = (Spinner)findViewById(R.id.SpinGrade);
	        dept = (Spinner)findViewById(R.id.SpinDept);
	        search = (Button)findViewById(R.id.BtnSearch);
	        reset = (Button)findViewById(R.id.BtnReset);
	        
	        ArrayAdapter <CharSequence> adapter =
					  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			ArrayAdapter <CharSequence> adapter2 =
					  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			adapter.add("COP");
			adapter.add("CNT");
			adapter.add("CGS");
			adapter.add("EEL");
			adapter.add("MAS");
			adapter.add("PHY");
			
			adapter2.add("A+");
			adapter2.add("A");
			adapter2.add("A-");
			
			adapter2.add("B+");
			adapter2.add("B");
			adapter2.add("B-");
			
			adapter2.add("C+");
			adapter2.add("C");
			adapter2.add("C-");
			
			adapter2.add("D+");
			adapter2.add("D");
			adapter2.add("D-");
			
			adapter2.add("F");
			
			dept.setAdapter(adapter);
			mingrade.setAdapter(adapter2);
			
	    }

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			case R.id.BtnSearch:
				
				break;
			case R.id.BtnReset:
				prof.setText("");
				coursenum.setText("");
				dept.setSelection(0);
				mingrade.setSelection(0);
				break;
			default:
				break;
		}
	}
	 
	 
}
