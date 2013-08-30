package com.duckysoftware.profometer;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.duckysoftware.profometer.Register.MyOnItemSelectedListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class EditProfile extends Activity implements OnSeekBarChangeListener
{
	TextView Clarity, Easiness, Fairness, Homework, Quizzes, English;
	
	SeekBar ClaritySeek, EasinessSeek, FairnessSeek, HomeworkSeek, QuizzesSeek, EnglishSeek;
	
	Spinner UniSpinner, MajorSpinner;
	
	Button Save;

	
	private Student User;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editprofile);
		Bundle bundle = getIntent().getExtras();
		

		User = bundle.getParcelable("Profile");

		
		Clarity = (TextView)findViewById(R.id.EditClarityText);
		ClaritySeek = (SeekBar)findViewById(R.id.EditClaritySeek);
		
		Easiness = (TextView)findViewById(R.id.EditEasinessText);
		EasinessSeek = (SeekBar)findViewById(R.id.EditEasinessSeek);
		
		Fairness = (TextView)findViewById(R.id.EditFairnessText);
		FairnessSeek = (SeekBar)findViewById(R.id.EditFairnessSeek);
		
		Homework = (TextView)findViewById(R.id.EditHomeworkText);
		HomeworkSeek = (SeekBar)findViewById(R.id.EditHomeworkSeek);
		
		Quizzes = (TextView)findViewById(R.id.EditQuizzesText);
		QuizzesSeek = (SeekBar)findViewById(R.id.EditQuizzesSeek);
		
		English = (TextView)findViewById(R.id.EditEnglishText);
		EnglishSeek = (SeekBar)findViewById(R.id.EditEnglishSeek);
		
		ArrayAdapter <CharSequence> adapter =
				  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		ArrayAdapter <CharSequence> adapter2 =
				  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		UniSpinner = (Spinner)findViewById(R.id.EditUniSpinner);
		addCollege(adapter);
		UniSpinner.setAdapter(adapter);
		UniSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		MajorSpinner = (Spinner)findViewById(R.id.EditMajorSpinner);
		addMajor(adapter2);
		MajorSpinner.setAdapter(adapter2);
		MajorSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener2());
		
		ClaritySeek.setProgress(User.getClarity());
		ClaritySeek.setOnSeekBarChangeListener(this);
		EasinessSeek.setProgress(User.getEasiness());
		EasinessSeek.setOnSeekBarChangeListener(this);
		FairnessSeek.setProgress(User.getFairness());
		FairnessSeek.setOnSeekBarChangeListener(this);
		HomeworkSeek.setProgress(User.getHomework());
		HomeworkSeek.setOnSeekBarChangeListener(this);
		QuizzesSeek.setProgress(User.getQuizzes());
		QuizzesSeek.setOnSeekBarChangeListener(this);
		EnglishSeek.setProgress(User.getEnglish());
		EnglishSeek.setOnSeekBarChangeListener(this);
		
		UniSpinner.set
		
		/*
		ClaritySeek.setMax(41);
		EasinessSeek.setMax(41);
		FairnessSeek.setMax(41);
		HomeworkSeek.setMax(41);
		QuizzesSeek.setMax(41);
		EnglishSeek.setMax(41);
		*/
		
		Clarity.setText("Clarity: " + FindGrade(User.getClarity()));
		Easiness.setText("Easiness: " + FindGrade(User.getEasiness()));
		Fairness.setText("Fairness: " + FindGrade(User.getFairness()));
		Homework.setText("Homework: " + FindGrade(User.getHomework()));
		Quizzes.setText("Quizzes: " + FindGrade(User.getQuizzes()));
		English.setText("English: " + FindGrade(User.getEnglish()));
		
		Save = (Button)findViewById(R.id.SaveProfileButton);
		Save.setOnClickListener( new OnClickListener()
		{

			public void onClick(View v) {
				// TODO Auto-generated method stub

				try{
			        HttpClient httpclient = new DefaultHttpClient();
			        HttpPost httppost = new HttpPost("http://icanhasandroidz.com/prof-o-meter/updateprofile.php");
			        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
			        param.add(new BasicNameValuePair("Email", User.getEmail()));
			        param.add(new BasicNameValuePair("School", User.getSchool()));
			        param.add(new BasicNameValuePair("Clarity", convertInteger(User.getClarity())));
			        param.add(new BasicNameValuePair("Fairness", convertInteger(User.getFairness())));
			        param.add(new BasicNameValuePair("Easiness", convertInteger(User.getEasiness())));
			        param.add(new BasicNameValuePair("Homework", convertInteger(User.getHomework())));
			        param.add(new BasicNameValuePair("Quizzes", convertInteger(User.getQuizzes())));
			        param.add(new BasicNameValuePair("English", convertInteger(User.getEnglish())));
			        param.add(new BasicNameValuePair("Major", User.getMajor()));
			        
			        httppost.setEntity(new UrlEncodedFormEntity(param));
			        httpclient.execute(httppost);
			        
			        finish();
			        
				}catch(Exception e){
				        Log.e("log_tag", "Error in http connection "+e.toString());
				}
			}
			
		});
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

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) 
	{
		// TODO Auto-generated method stub
		switch(seekBar.getId())
		{
		case R.id.EditClaritySeek:
			User.setClarity(progress);
			Clarity.setText("Clarity: " + FindGrade(progress));
			break;
		case R.id.EditEasinessSeek:
			User.setEasiness(progress);
			Easiness.setText("Easiness: " + FindGrade(progress));
			break;
		case R.id.EditFairnessSeek:
			User.setFairness(progress);
			Fairness.setText("Fainess: " + FindGrade(progress));
			break;
		case R.id.EditHomeworkSeek:
			User.setHomework(progress);
			Homework.setText("Homework: " + FindGrade(progress));
			break;
		case R.id.EditQuizzesSeek:
			User.setQuizzes(progress);
			Quizzes.setText("Quizzes: " + FindGrade(progress));
			break;
		case R.id.EditEnglishSeek:
			User.setEnglish(progress);
			English.setText("English: " + FindGrade(progress));
			break;
			
		}
	}

	private CharSequence FindGrade(int progress) 
	{
		// TODO Auto-generated method stub
		
		String grade = null;
		switch (progress)
		{
		case 41: case 40: case 39: case 38:
			grade = "A+";
			break;
		case 37: case 36: case 35: case 34:
			grade = "A";
			break;
		case 33: case 32: case 31:
			grade = "A-";
			break;
		case 30: case 29: case 28:
			grade = "B+";
			break;
		case 27: case 26: case 25: case 24:
			grade = "B";
			break;
		case 23: case 22: case 21:
			grade = "B-";
			break;
		case 20: case 19: case 18:
			grade = "C+";
			break;
		case 17: case 16: case 15: case 14:
			grade = "C";
			break;
		case 13: case 12: case 11:
			grade = "C-";
			break;
		case 10: case 9: case 8:
			grade = "D+";
			break;
		case 7: case 6: case 5: case 4:
			grade = "D";
			break;
		case 3: case 2: case 1:
			grade = "D-";
			break;
		default:
			grade = "F";
			break;
		}
		return grade;
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	public static String convertInteger(int i) {
	    return Integer.toString(i);
	}
	
	class MyOnItemSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	        User.setSchool(parent.getItemAtPosition(pos).toString());
	    }

	    public void onNothingSelected(AdapterView<?> parent) {
	      // Do nothing.
	    }
	}
	class MyOnItemSelectedListener2 implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	    	User.setMajor(parent.getItemAtPosition(pos).toString());
	    }

	    public void onNothingSelected(AdapterView<?> parent) {
	      // Do nothing.
	    }
	}
}
