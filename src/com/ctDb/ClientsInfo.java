package com.ctDb;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ClientsInfo extends Activity {
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu_info, menu);
        return true;
    }
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.BackInfos:
	    	setResult(RESULT_OK);
        	finish();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_info);
        
        LoadClientInfo();
        
    }

    private Clientes ct = new Clientes(ClientsDatabase.client);
    
	private void LoadClientInfo() {
		TextView txtNome = (TextView) findViewById(R.id.userName);
		txtNome.setText(Html.fromHtml(ct.getNome()));
		TextView txtContacto = (TextView) findViewById(R.id.userContacto);
		txtContacto.setText(Html.fromHtml(ct.getContacto()));
		TextView txtMorada = (TextView) findViewById(R.id.userMorada);
		txtMorada.setText(Html.fromHtml(ct.getMorada()));
		
	}
}