package com.duckysoftware.profometer;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable
{

	private final String Name;
	private final String Email;
	private String Password;


	private String Major;
	private String School;

	private int Clarity;
	private int Fairness;
	private int Easiness;
	private int Homework;
	private int Quizzes;
	private int English;

	Student(String inputName, String inputEmail)
	{
		Name = inputName;
		Email = inputEmail;
	}

	public Student(Parcel in) 
	{
		Name = in.readString();
		Email = in.readString();
		Password = in.readString();
		
		Major = in.readString();
		School = in.readString();
		
		Clarity = in.readInt();
		Fairness = in.readInt();
		Easiness = in.readInt();
		Homework = in.readInt();
		Quizzes = in.readInt();
		English = in.readInt();
		// TODO Auto-generated constructor stub
	}

	public String getName()
	{
		return Name;
	}
	public String getEmail()
	{
		return Email;
	}
	public void setSchool(String inSchool)
	{
		School = inSchool;
	}
	public void setMajor(String inMajor)
	{
		Major = inMajor;
	}
	public String getSchool()
	{
		return School;
	}
	public String getMajor()
	{
		return Major;
	}
	
	public void setClarity(int input)
	{
		Clarity = input;
	}
	public void setFairness(int input)
	{
		Fairness = input;
	}
	public void setHomework(int input)
	{
		Homework = input;
	}
	public void setQuizzes(int input)
	{
		Quizzes = input;
	}
	public void setEnglish(int input)
	{
		English = input;
	}
	public void setEasiness(int input)
	{
		Easiness = input;
	}
	public int getClarity()
	{
		return Clarity;
	}
	public int getFairness()
	{
		return Fairness;
	}
	public int getHomework()
	{
		return Homework;
	}
	public int getQuizzes()
	{
		return Quizzes;
	}
	public int getEnglish()
	{
		return English;
	}
	public int getEasiness()
	{
		return Easiness;
	}

	public int describeContents() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) 
	{
		dest.writeString(Name);
		dest.writeString(Email);
		dest.writeString(Password);
		
		dest.writeString(Major);
		dest.writeString(School);
		
		dest.writeInt(Clarity);
		dest.writeInt(Fairness);
		dest.writeInt(Easiness);
		dest.writeInt(Homework);
		dest.writeInt(Quizzes);
		dest.writeInt(English);
	}
	public static final Parcelable.Creator<Student> CREATOR = 
			new Parcelable.Creator<Student>() 
			{
				public Student createFromParcel(Parcel in) 
				{
					return new Student(in);
				}
		
				public Student[] newArray(int size) 
				{
					return new Student[size];
				}
			};

}