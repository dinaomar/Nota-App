package dina.nota.main;

import java.util.ArrayList;

import com.example.nota.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

	public class CustomListAdapter  extends ArrayAdapter<String> {
		
	  private final Context context;
	  private final ArrayList<String> title_values;

	  public CustomListAdapter(Context context, ArrayList<String> title_values) {
	    super(context, -1, title_values);
	    this.context = context;
	    this.title_values = title_values;
	  }

	  @SuppressLint("ViewHolder")
	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.list_item, parent, false);
	    TextView textView = (TextView) rowView.findViewById(R.id.texttitle);
	    textView.setText(title_values.get(position));
	    return rowView;
	  }
	} 




