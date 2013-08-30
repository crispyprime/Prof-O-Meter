package com.duckysoftware.profometer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends Activity implements View.OnClickListener, OnCheckedChangeListener
{
	EditText Email, Name, Password;
	Spinner College, Major;
	CheckBox Agree;
	Button register;
	String CollegeString, MajorString;
	
	class MyOnItemSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	        CollegeString = parent.getItemAtPosition(pos).toString();
	    }

	    public void onNothingSelected(AdapterView<?> parent) {
	      // Do nothing.
	    }
	}
	class MyOnItemSelectedListener2 implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	    	MajorString = parent.getItemAtPosition(pos).toString();
	    }

	    public void onNothingSelected(AdapterView<?> parent) {
	      // Do nothing.
	    }
	}
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerlayout);
		Bundle bundle = getIntent().getExtras();
		
		Name = (EditText)findViewById(R.id.ETextName);
		Password = (EditText)findViewById(R.id.ETextPass);
		
		Email = (EditText)findViewById(R.id.ETextEmail);
		Email.setText(bundle.getString("EmailPassed"));
		
		register = (Button)findViewById(R.id.btn_Register);
		register.setClickable(false);
		register.setOnClickListener(this);
		
		Agree = (CheckBox)findViewById(R.id.chkAgree);
		Agree.setOnCheckedChangeListener(this);
		
		ArrayAdapter <CharSequence> adapter =
				  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		addCollege(adapter);
		ArrayAdapter <CharSequence> adapter2 =
				  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		addMajor(adapter2);
		
		College = (Spinner)findViewById(R.id.PDCollege);
		College.setAdapter(adapter);
		College.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		Major = (Spinner)findViewById(R.id.PDMajor);
		Major.setAdapter(adapter2);
		Major.setOnItemSelectedListener(new MyOnItemSelectedListener2());
		
	}
	public void onClick(View view) 
	{
		switch(view.getId())
		{
		case R.id.btn_Register:
			boolean filled = checkfields();
			if(filled)
			{
				boolean inserted = insertAccount();
				if(inserted)
				{
					Toast dispmsg = Toast.makeText(this, "Account Created!", Toast.LENGTH_LONG);
		    		dispmsg.show();	
		    		finish();
				}
				else
				{
					Toast dispmsg = Toast.makeText(this, "Email already in use!", Toast.LENGTH_LONG);
		    		dispmsg.show();	
		    		inserted = true;
				}
			}
			break;
		}
		
	}
	
	private void addCollege(ArrayAdapter <CharSequence> a)
	{
		a.add("University of Central Florida");
		a.add("University of Florida");
		a.add("Florida Institute of Technology");
		a.add("Georgia Tech");
		a.add("Florida State University");
		a.add("South Harmon Institute of Technology");
	}
	private void addMajor(ArrayAdapter <CharSequence> a)
	{
		a.add("Computer Science");
		a.add("Underwater Basketweaving");
		a.add("Computer Engineering");
		a.add("Liberal Arts");
		a.add("Mathematics");
		a.add("Creative Writing");
	}
	
	private boolean insertAccount()
	{
		boolean inserted = true;
		String result = null;
		InputStream is = null;
		try{
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://icanhasandroidz.com/prof-o-meter/test.php");
	        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
	        param.add(new BasicNameValuePair("Email", Email.getText().toString()));
	        param.add(new BasicNameValuePair("Name", Name.getText().toString()));
	        param.add(new BasicNameValuePair("Password", Password.getText().toString()));
	        param.add(new BasicNameValuePair("College", CollegeString));
	        param.add(new BasicNameValuePair("Major", MajorString));
	        httppost.setEntity(new UrlEncodedFormEntity(param));
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
	        
		}catch(Exception e){
		        Log.e("log_tag", "Error in http connection "+e.toString());
		}
		try{
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	                sb.append(line);
	        }
	        is.close();
	 
	        result=sb.toString();
	        
	        if(!result.equalsIgnoreCase("null"))
	        {
        		inserted = false;
	        }

	        
		}catch(Exception e){
		        Log.e("log_tag", "Error converting result "+e.toString());
		}
		return inserted;
	}
	
	private boolean checkfields() 
	{
		boolean filled = true;
		// TODO Auto-generated method stub
    	final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
  	          "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
  	          "\\@" +
  	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
  	          "(" +
  	          "\\." +
  	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
  	          ")+"
  	      );
    	
    	//final Pattern NAME_PATTERN = Pattern.compile("/^[a-z ,.'-]+$/i");
    	final Pattern NAME_PATTERN = Pattern.compile("[A-Z][a-z]+\\s[A-Z][a-z]+");
    	String NotFilledOut = "Field(s) cannot be left blank or incomplete:\n";
    	if(!NAME_PATTERN.matcher(Name.getText().toString()).matches())
    	{
    		NotFilledOut = NotFilledOut + "First and Last name\n";
    		filled = false;
    	}
    	if(!EMAIL_ADDRESS_PATTERN.matcher(Email.getText().toString()).matches())
    	{
    		NotFilledOut = NotFilledOut + "Email Address";
    		filled = false;
    	}
    	
    	if(!filled)
    	{
    		Toast dispmsg = Toast.makeText(this, NotFilledOut, Toast.LENGTH_LONG);
    		dispmsg.show();	
    	}
    	return filled;
	}
	
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		if(buttonView.getId() == R.id.chkAgree)
			if(isChecked)
				register.setClickable(true);
			else
				register.setClickable(false);
	}
}
