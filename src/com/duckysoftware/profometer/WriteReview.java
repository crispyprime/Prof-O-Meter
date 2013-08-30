package com.duckysoftware.profometer;

import com.duckysoftware.profometer.Register.MyOnItemSelectedListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;


public class WriteReview extends Activity implements OnCheckedChangeListener, OnSeekBarChangeListener
{	
	CheckBox homework, project, quiz;
	SeekBar homeworkseek, projectseek, quizseek;
	LinearLayout hwholder1, hwholder2, projectholder1, projectholder2, quizholder1, quizholder2;
	TextView hwGrade, projectGrade, quizGrade;
	Spinner Course;
	String CourseString;
	
	@Override
	 public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.writereviewlayout);
	        
	        homework = (CheckBox) findViewById(R.id.cbhw);
	        homework.setOnCheckedChangeListener(this);
	        
	        project = (CheckBox) findViewById(R.id.cbproject);
	        project.setOnCheckedChangeListener(this);
	        
	        quiz = (CheckBox) findViewById(R.id.cbquiz);
	        quiz.setOnCheckedChangeListener(this);
	        
	        homeworkseek = (SeekBar) findViewById(R.id.hwSeek);
	        homeworkseek.setOnSeekBarChangeListener(this);
	        
	        projectseek = (SeekBar) findViewById(R.id.projectSeek);
	        projectseek.setOnSeekBarChangeListener(this);
	        
	        quizseek = (SeekBar) findViewById(R.id.quizSeek);
	        quizseek.setOnSeekBarChangeListener(this);
	        
	        hwholder1 = (LinearLayout) findViewById(R.id.HWLin1);
	        hwholder2 = (LinearLayout) findViewById(R.id.HWLin2);
	        hwholder1.setVisibility(8);
	        hwholder2.setVisibility(8);
	        
	        projectholder1 = (LinearLayout) findViewById(R.id.ProjectLin1);
	        projectholder2 = (LinearLayout) findViewById(R.id.ProjectLin2);
	        projectholder1.setVisibility(8);
	        projectholder2.setVisibility(8);
	        
	        quizholder1 = (LinearLayout) findViewById(R.id.QuizLin1);
	        quizholder2 = (LinearLayout) findViewById(R.id.QuizLin2);
	        quizholder1.setVisibility(8);
	        quizholder2.setVisibility(8);
	        
	        hwGrade = (TextView) findViewById(R.id.hwgrade);
	        projectGrade = (TextView) findViewById(R.id.projectgrade);
	        quizGrade = (TextView) findViewById(R.id.quizgrade);
	        
	        ArrayAdapter <CharSequence> adapter =
					  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			addCourse(adapter);
			
			Course = (Spinner)findViewById(R.id.courseSpinner);
			Course.setAdapter(adapter);
			Course.setOnItemSelectedListener(new MyOnItemSelectedListener());
			
			
	    }
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) 
	{
		LinearLayout changevis1 = null;
		LinearLayout changevis2 = null;
		
		switch(arg0.getId())
		{
		case R.id.cbhw:	
			changevis1 = hwholder1;
			changevis2 = hwholder2;
			break;
		case R.id.cbproject:
			changevis1 = projectholder1;
			changevis2 = projectholder2;
			break;
		case R.id.cbquiz:
			changevis1 = quizholder1;
			changevis2 = quizholder2;
			break;
		}
		
		if(arg1)
		{
			changevis1.setVisibility(0);
			changevis2.setVisibility(0);
		}
		else
		{
			changevis1.setVisibility(8);
			changevis2.setVisibility(8);
		}
	}
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) 
	{
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.hwSeek:
			hwGrade.setText(FindGrade(homeworkseek.getProgress()));
		case R.id.projectSeek:
			projectGrade.setText(FindGrade(projectseek.getProgress()));
		case R.id.quizSeek:
			quizGrade.setText(FindGrade(quizseek.getProgress()));
			break;
		}
	}
	private CharSequence FindGrade(int progress) 
	{
		// TODO Auto-generated method stub
		
		String grade = null;
		switch (progress)
		{
		case 100: case 99: case 98: case 97:
			grade = "A+";
			break;
		case 96: case 95: case 94: case 93:
			grade = "A";
			break;
		case 92: case 91: case 90:
			grade = "A-";
			break;
		case 89: case 88: case 87:
			grade = "B+";
			break;
		case 86: case 85: case 84: case 83:
			grade = "B";
			break;
		case 82: case 81: case 80:
			grade = "B-";
			break;
		case 79: case 78: case 77:
			grade = "C+";
			break;
		case 76: case 75: case 74: case 73:
			grade = "C";
			break;
		case 72: case 71: case 70:
			grade = "C-";
			break;
		case 69: case 68: case 67:
			grade = "D+";
			break;
		case 66: case 65: case 64: case 63:
			grade = "D";
			break;
		case 62: case 61: case 60:
			grade = "D-";
			break;
		default:
			grade = "F";
			break;
		}
		return grade;
	}
	public void onStartTrackingTouch(SeekBar seekBar) 
	{
		// TODO Auto-generated method stub
		
	}
	public void onStopTrackingTouch(SeekBar seekBar) 
	{
		// TODO Auto-generated method stub
		
	}
	
	private void addCourse(ArrayAdapter <CharSequence> a)
	{
		a.add("COP");
		a.add("COT");
	}
	
	class MyOnItemSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	        CourseString = parent.getItemAtPosition(pos).toString();
	    }

	    public void onNothingSelected(AdapterView<?> parent) {
	      // Do nothing.
	    }
	}
	 
}
