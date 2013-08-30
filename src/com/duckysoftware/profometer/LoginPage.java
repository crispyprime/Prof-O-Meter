package com.duckysoftware.profometer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginPage extends Activity implements View.OnClickListener
{
	EditText Email, pass;
	Button Login, Register;
	
	Student User;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        
        Email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        Login = (Button)findViewById(R.id.btn_login);
        Register = (Button)findViewById(R.id.btn_register);
        Login.setOnClickListener(this);
        Register.setOnClickListener(this);
    }

    public void onClick (View view)
    {
    	final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
    	          "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
    	          "\\@" +
    	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
    	          "(" +
    	          "\\." +
    	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
    	          ")+"
    	      );
    	
    	Toast NotFilledOut;
    	switch(view.getId())
    	{
    	case R.id.btn_login:
    		if (EMAIL_ADDRESS_PATTERN.matcher(Email.getText().toString()).matches())
    		{
    			//NotFilledOut = Toast.makeText(this, "Your email address is " + Email.getText().toString(), Toast.LENGTH_LONG);
    			//NotFilledOut.show();
    			
    			/*
    			 * if (DB returns user true)
    			 * 		store credentials;
    			 * else
    			 * {
    			 * 	NotFilledOut = Toast.makeText(this, "No User Found", Toast.LENGTH_LONG);
    			 *  Email.setText("");
    			 *  }
    			 */
    			boolean logged = loginAccount();
    			if(logged)
    			{
    				Bundle b = new Bundle();
    				b.putParcelable("LoggedIn", User);
    				Intent Logs = new Intent(this, Profile2.class);
    				Logs.putExtras(b);
    				startActivity(Logs);
    			}
    			
    		}
    		else
    		{
    			NotFilledOut = Toast.makeText(this, "Email Address is invalid", Toast.LENGTH_LONG);
    			NotFilledOut.show();
    		}
    		
    		break;
    		
    	case R.id.btn_register:
    		
    		Bundle b = new Bundle();
    		b.putString("EmailPassed", Email.getText().toString().trim());
    		Intent i = new Intent(this, Register.class);
    		i.putExtras(b);
    		startActivity(i);
    		break;
    	}
    }
    private boolean loginAccount()
	{
    	boolean loggedIn = true;
    	String result = null;
		InputStream is = null;
		try{
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://icanhasandroidz.com/prof-o-meter/login.php");
	        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
	        param.add(new BasicNameValuePair("Email", Email.getText().toString()));
	        param.add(new BasicNameValuePair("Pass", pass.getText().toString()));
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
	        
    		if(result.equals("Incorrect Password"))
    		{
    			Toast dispmsg = Toast.makeText(this, result, Toast.LENGTH_LONG);
        		dispmsg.show();	
    			loggedIn = false;
    		}
    		if(result.equals("Email Not Registered!"))
    		{
    			Toast dispmsg = Toast.makeText(this, result, Toast.LENGTH_LONG);
        		dispmsg.show();	
    			loggedIn = false;
    		}
    		
		}catch(Exception e){
		        Log.e("log_tag", "Error converting result "+e.toString());
		}
		if(loggedIn)
		{
			
			try{
		        JSONArray jArray = new JSONArray(result);
		        for(int i=0;i<jArray.length();i++){
		                JSONObject json_data = jArray.getJSONObject(i);
		                
		                User = new Student(json_data.getString("Name"), Email.getText().toString());
		                User.setSchool(json_data.getString("University"));
		                User.setMajor(json_data.getString("Major"));
		                User.setClarity(json_data.getInt("Clarity"));
		                User.setFairness(json_data.getInt("Fairness"));
		                User.setEasiness(json_data.getInt("Easiness"));
		                User.setHomework(json_data.getInt("Homework"));
		                User.setQuizzes(json_data.getInt("Quizzes"));
		                User.setEnglish(json_data.getInt("English"));          
		                
		                /*Log.i("log_tag","id: "+json_data.getInt("id")+
		                        ", Name: "+json_data.getString("Name")+
		                        ", Email: "+json_data.getString("Email")+
		                        ", Password: "+json_data.getString("Password")+
		                        ", Major: "+json_data.getString("Major")+
		                        ", University: "+json_data.getString("University")+
		                        ", MemberSince: "+json_data.getString("MemberSince")+
		                        ", Quizweight: "+json_data.getInt("Quizweight")+
		                        ", Projectweight: "+json_data.getInt("Projectweight")+
		                        ", Homeworkweight: "+json_data.getInt("Homeworkweight")+
		                        ", Englishweight: "+json_data.getInt("Englishweight")
		                );*/
		        }
			}
			catch(JSONException e){
			        Log.e("log_tag", "Error parsing data "+e.toString());
			}
		}
		return loggedIn;
	}
}