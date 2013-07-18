package com.fullsail.dmlib;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


public class FormItems {
	
	public static LinearLayout editTextWithButton(Context currentContext, String theHint, String buttonText){
		
		LinearLayout linLay = new LinearLayout(currentContext);
		LayoutParams layPar = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		linLay.setLayoutParams(layPar);
		
		EditText textEdit = new EditText(currentContext);
		layPar = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
		textEdit.setHint(theHint);
		textEdit.setLayoutParams(layPar);
		
		Button submitButton = new Button(currentContext);
		submitButton.setText(buttonText);
		submitButton.setId(2);
		
		linLay.addView(textEdit);
		linLay.addView(submitButton);
		
		return linLay;
	}
	
	public static Button singleButton(Context currentContext, String buttonText)
	{
		
		
		Button submitButton = new Button(currentContext);
		submitButton.setText(buttonText);
		
		
		return submitButton;
	}

	
	public static TextView singleTextView(Context currentContext, String textString)
	{
		TextView textView = new TextView(currentContext);
		textView.setText(textString);
		
		return textView;
		
	}
	
	public static LinearLayout saveButtonAndTextFieldRtoL(Context currentContext, String theHint, String buttonText)
	{
		
		LinearLayout linLay = new LinearLayout(currentContext);
		linLay.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layPar = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		linLay.setLayoutParams(layPar);
		
		
		
		TextView textView = new TextView(currentContext);
		textView.setText(theHint);
		textView.setId(4);
		
		Button saveButton = new Button(currentContext);
		saveButton.setText(buttonText);
		saveButton.setId(3);
		saveButton.setTag(textView);
		
		linLay.addView(saveButton);
		linLay.addView(textView);
		
		return linLay;
		
		
	}
	
	public static LinearLayout twoButtonsAndTextField(Context currentContext, String button1Text, 
			String button2Text)
	{
		LinearLayout linLay = new LinearLayout(currentContext);
		LayoutParams layPar = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		linLay.setLayoutParams(layPar);
		
		TextView textView = new TextView(currentContext);
		layPar = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.4f);
		textView.setLayoutParams(layPar);
		textView.setId(6);
		
		Button button1 = new Button(currentContext);
		layPar = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.3f);
		button1.setLayoutParams(layPar);
		button1.setText(button1Text);
		button1.setId(5);
		button1.setTag(textView);
		
		Button button2 = new Button(currentContext);
		layPar = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.3f);
		button2.setLayoutParams(layPar);
		button2.setText(button2Text);
		button2.setId(7);
		button2.setTag(textView);
		
		
		linLay.addView(button1);
		linLay.addView(textView);
		linLay.addView(button2);
		
		return linLay;
		
	}
}
