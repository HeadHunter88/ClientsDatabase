package com.ctDb;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ClientsDatabase extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ListView lv = (ListView) findViewById(R.id.lstViewCli);
        lv.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		client = new Clientes(listClients.get(position));
        		Intent it = new Intent(ClientsDatabase.this, ClientsInfo.class);
        		startActivityForResult(it,RESULT_OK);
        	}
        });
        lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				ThrowDeleteEditAlert(arg2);
				return true;
			}
		});
        
        OpenCreateDataBase();
        CreateTable();
  
		FillListView();
    }
    
    public static SQLiteDatabase ClientsDB;
	public List<Clientes> listClients = new ArrayList<Clientes>();
	public static Clientes client;
	
	public void FillListView()
	{
		LoadClientes();
		List<String> nomeClientes = new ArrayList<String>();
		if(listClients.size() > 0)
		{
			for(Clientes cl : listClients)
			{
				nomeClientes.add(cl.getNome());
			}
		}
		else
		{
			ThrowAlert();
		}
		ListViewAdapter adapter = new ListViewAdapter(this, nomeClientes);
		ListView list = (ListView) findViewById(R.id.lstViewCli);
		list.setAdapter(adapter);
		
	}
	void ThrowDeleteEditAlert(final int position) {
		
		final String [] items=new String []{"Edit","Delete","Cancel"};
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Options");

		builder.setItems(items, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					client = new Clientes(listClients.get(position));
					Intent i = new Intent(ClientsDatabase.this, EditItem.class);
			        startActivityForResult(i,0);
					break;
				case 1:
					DeleteClient(position);
					break;
				case 2:
					break;
				default:
					break;
				}
			}
		});

		builder.show();
		
	}
	void ThrowAlert()
	{
		final AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("No existing clients!");
		builder.setMessage("Do you want to add a new client?");
		builder.setIcon(android.R.drawable.ic_dialog_alert);

		builder.setPositiveButton("Yes", new OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
				newClient();
			}
		});

		builder.setNegativeButton("No", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				AlertDialog ad=builder.create();
				ad.cancel();
			}
		});
		builder.show();
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu_main, menu);
        return true;
    }	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.addClientForm:
	        newClient();
	        return true;
	    case R.id.actualizar:
	        FillListView();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}	
	public void newClient()
	{
		Intent i = new Intent(ClientsDatabase.this, AddClient.class);
        startActivityForResult(i,0);
	}
	public void DeleteClient(final int position)
	{
		final AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Warning");
		builder.setMessage("Do you really want to eliminate the client " + listClients.get(position).getNome() + "?");
		builder.setIcon(android.R.drawable.ic_dialog_alert);
	
		builder.setPositiveButton("Yes", new OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
				ClientsDB.delete("Clientes", "id="+listClients.get(position).getId(), null);
				FillListView();
			}
		});
	
		builder.setNegativeButton("No", new OnClickListener() {
	
			public void onClick(DialogInterface dialog, int which) {
				AlertDialog ad=builder.create();
				ad.cancel();
			}
		});
		builder.show();
		
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		FillListView();
	}
	public void LoadClientes()
	{
		listClients.clear();
		Cursor tableQuery = ClientsDB.query("Clientes", null, null, null, null, null, null);
		 tableQuery.moveToFirst();
		 while(tableQuery.isAfterLast() == false) {
			 String id = tableQuery.getString(0);
			 String nome = tableQuery.getString(1);
			 String contacto = tableQuery.getString(2);
			 String morada = tableQuery.getString(3);
			 Clientes clt = new Clientes(id,nome,contacto,morada);
			 listClients.add(clt);
			 tableQuery.moveToNext();
		 }
		 tableQuery.close();
    	
		
	}
	public void OpenCreateDataBase() {
    	SQLiteDatabase db = null;
    	try
        {
        	db = SQLiteDatabase.openDatabase( "/data/data/com.ctDb/databases/ClientsDatabase.db", null,  SQLiteDatabase.OPEN_READWRITE);
        }
        catch (Exception e) {

        	db = openOrCreateDatabase( "ClientsDatabase.db", SQLiteDatabase.CREATE_IF_NECESSARY, null );
        	
        	db.setVersion(1);
        	db.setLocale(Locale.getDefault());
        	db.setLockingEnabled(true);
		}
        finally
        {
        	ClientsDB = db;
        }
		
	}
	public void CreateTable() {
		try{
	    	String crtTblClientes = "CREATE TABLE Clientes ( id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, contacto TEXT, morada TEXT);";
	    	ClientsDB.execSQL(crtTblClientes);
		}
		catch (Exception e) {
		}
	}
    
}