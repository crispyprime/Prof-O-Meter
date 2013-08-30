package com.duckysoftware.profometer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Profile2 extends Activity {
	
	TextView Name, Email, School, Major;
	TextView Clarity, Fairness, Easiness, Homework, Quizzes, English;
	Student User;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilelayout);
        Bundle bundle = getIntent().getExtras();
        
        User = bundle.getParcelable("StudentPassed");
        
        Name = (TextView)findViewById(R.id.textView5);
        Name.setText(User.getName());
        
        Email = (TextView)findViewById(R.id.textView7);
        Email.setText(User.getEmail());
        
        School = (TextView)findViewById(R.id.textView9);
        School.setText(User.getSchool());
        
        Major = (TextView)findViewById(R.id.textView11);
        Major.setText(User.getMajor());
        
        Clarity = (TextView)findViewById(R.id.textProfileClarity);
        Clarity.setText(convertInteger(User.getClarity()));
        
        Fairness = (TextView)findViewById(R.id.textView20);
        Fairness.setText(convertInteger(User.getFairness()));
        
        Easiness = (TextView)findViewById(R.id.textView21);
        Easiness.setText(convertInteger(User.getEasiness()));
        
        Homework = (TextView)findViewById(R.id.textView22);
        Homework.setText(convertInteger(User.getHomework()));
        
        Quizzes = (TextView)findViewById(R.id.textView23);
        Quizzes.setText(convertInteger(User.getQuizzes()));
        
        English = (TextView)findViewById(R.id.textView24);
        English.setText(convertInteger(User.getEnglish()));
    }
	
	@Override
	public void onResume()
	{
		super.onResume();

		String result = null;
		InputStream is = null;
		try{
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://icanhasandroidz.com/prof-o-meter/profile.php");
	        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
	        param.add(new BasicNameValuePair("Email", Email.getText().toString()));
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
    		
		}catch(Exception e){
		        Log.e("log_tag", "Error converting result "+e.toString());
		}
		try{
	        JSONArray jArray = new JSONArray(result);
	        for(int i=0;i<jArray.length();i++){
	                JSONObject json_data = jArray.getJSONObject(i);
	                
	                User.setSchool(json_data.getString("University"));
	                User.setMajor(json_data.getString("Major"));
	                User.setClarity(json_data.getInt("Clarity"));
	                User.setFairness(json_data.getInt("Fairness"));
	                User.setEasiness(json_data.getInt("Easiness"));
	                User.setHomework(json_data.getInt("Homework"));
	                User.setQuizzes(json_data.getInt("Quizzes"));
	                User.setEnglish(json_data.getInt("English"));          
	        }
		}
		catch(JSONException e){
		        Log.e("log_tag", "Error parsing data "+e.toString());
		}
		School.setText(User.getSchool());
		Major.setText(User.getMajor());
		Clarity.setText(convertInteger(User.getClarity()));
		Fairness.setText(convertInteger(User.getFairness()));
		Easiness.setText(convertInteger(User.getEasiness()));
		Homework.setText(convertInteger(User.getHomework()));
		Quizzes.setText(convertInteger(User.getQuizzes()));
		English.setText(convertInteger(User.getEnglish()));
		
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) 
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profilemenu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) 
    {
    	switch(item.getItemId())
    	{
    	case R.id.item1:
    		Bundle b = new Bundle();
			b.putParcelable("Profile", User);
    		Intent i = new Intent(this, EditProfile.class);
    		i.putExtras(b);
    		startActivity(i);
    		return true;
    	}
		return false;
    }
    
	public static String convertInteger(int i) {
	    return Integer.toString(i);
	}
}
