package com.mindhack;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class Sudoku extends Activity implements OnClickListener{
	//Tag for log
	private static final String TAG = "Sudoku";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	
		//set up click listeners for all the buttons
		View continueButton = findViewById(R.id.continue_button);
		continueButton.setOnClickListener(this);
		View newButton = findViewById(R.id.new_button);
		newButton.setOnClickListener(this);
		View aboutButton = findViewById(R.id.about_button);
		aboutButton.setOnClickListener(this);
		View exitButton = findViewById(R.id.exit_button);
		exitButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.about_button:
			Intent i = new Intent(this,About.class);
			startActivity(i);
			break;
		case R.id.new_button:
			openNewGameDialog();
			break;
		case R.id.continue_button:
			startGame(Game.DIFFICULTY_CONTINUE);
			break;
		case R.id.exit_button:
			finish();
			break;
		}
	}

	//open select dialog for select difficulty
	private void openNewGameDialog() {
		new AlertDialog.Builder(this).setTitle(R.string.new_game_title).setItems(R.array.difficulty,
				new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				startGame(which);
			}
		}).show();
	}

	//start game
	private void startGame(int which) {
		Log.d(TAG, "clicked on "+ which);
		Intent intent = new Intent(Sudoku.this,Game.class);
		intent.putExtra(Game.KEY_DIFFICULTY, which);
		startActivity(intent);		
	}

	//create menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.settings:
			startActivity(new Intent(this, Prefs.class));
			return true;
		}
		return false;
	}

	/** play background music **/
	@Override
	protected void onResume() {
		super.onResume();
		Music.play(this,R.raw.main);
	}

	/** stop background music **/
	@Override
	protected void onPause() {
		super.onPause();
		Music.stop(this);
	}
	
	
	
}