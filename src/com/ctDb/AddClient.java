package com.ctDb;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class AddClient extends Activity  {
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu_addclient, menu);
        return true;
    }
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    default:
	        return super.onOptionsItemSelected(item);
	    case R.id.addClient:
	        AddCliente();
	        break;
	    case R.id.BackNewClient:
	    	setResult(RESULT_OK);
        	finish();
	        break;
	    } return true;
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_client);

        RelativeLayout relLayoutNewClientsText = (RelativeLayout) findViewById(R.id.RelLayoutText);
        relLayoutNewClientsText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        		EditText txtBox = (EditText) findViewById(R.id.txtContacto);
        		imm.hideSoftInputFromWindow(txtBox.getWindowToken(), 0);
        		imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        		txtBox = (EditText) findViewById(R.id.txtNome);
        		imm.hideSoftInputFromWindow(txtBox.getWindowToken(), 0);
        		imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        		txtBox = (EditText) findViewById(R.id.txtMorada);
        		imm.hideSoftInputFromWindow(txtBox.getWindowToken(), 0);
            }
        });        
        
        Button btnAddClient = (Button) findViewById(R.id.btnAddClient);
        btnAddClient.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AddCliente();
				
			}
		});
        
    }
    
	protected void AddCliente() {
		ContentValues clt = new ContentValues();
		EditText txtNome = (EditText) findViewById(R.id.txtNome);
		EditText txtContacto = (EditText) findViewById(R.id.txtContacto);
		EditText txtMorada = (EditText) findViewById(R.id.txtMorada);
		clt.put("nome", txtNome.getText().toString());
		clt.put("contacto",txtContacto.getText().toString());
		clt.put("morada",txtMorada.getText().toString());
		ClientsDatabase.ClientsDB.insert("Clientes", null, clt);
		setResult(RESULT_OK);
    	finish();
	}
}