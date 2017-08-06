package com.moosa.unlimitedtodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Moosa on 05/08/2017.
 * moosa.bh@gmail.com
 */

public class CustomAdapter extends BaseAdapter{
    private final LayoutInflater inflater;
    private Context context;
    private ArrayList<TodoItem> todoItemArrayList ;
    private onAddClick click;

    public CustomAdapter(Context context, ArrayList<TodoItem> todoItemArrayList , onAddClick listener) {
        this.context = context;
        this.todoItemArrayList = todoItemArrayList;
        this.inflater = LayoutInflater.from(context);
        this.click = listener;
    }

    @Override
    public int getCount() {
        return todoItemArrayList.size();
    }

    @Override
    public TodoItem getItem(int position) {
        return todoItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.todo_item, null);
        TodoItem item = todoItemArrayList.get(position);
        TextView name  = (TextView) view.findViewById(R.id.name_view);
        TextView counter = (TextView) view.findViewById(R.id.counter_view);
        Button addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onAddClicked(position);
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onNextClick(position);
            }
        });
        name.setText(item.getName());
        counter.setText(""+item.getChildItems().size());

        return view;

    }
    interface onAddClick{
        void onAddClicked(int position);
        void onNextClick(int pos);

    }
}
