package com.ctDb;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class ListViewAdapter extends BaseAdapter {

	private Context context;

	private List<String> Clientes;
	
    public ListViewAdapter(Context context, List<String> tweets) {
        this.context = context;
        this.Clientes = tweets;
    }

    public int getCount() {
        return Clientes.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	String entry = Clientes.get(position);
    	
    	if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }
    	
    	TextView clientsInfo = (TextView) convertView.findViewById(R.id.Cell);
    	clientsInfo.setText(entry);
    	
		return convertView;
        
    }
    
    

    
}