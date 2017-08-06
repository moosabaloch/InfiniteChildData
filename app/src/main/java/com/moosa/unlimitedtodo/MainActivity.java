package com.moosa.unlimitedtodo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private CustomAdapter adapter;
    private ArrayList<TodoItem> todoItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Singleton Array List
        todoItemArrayList = getCurrentList(SingletonTodo.getInstance().getTodoItemArrayList(), 0);
            StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < SingletonTodo.getInstance().getIds().size();i++){
            builder.append(SingletonTodo.getInstance().getIds().get(i));
        }
        ((TextView)findViewById(R.id.textId)).setText(builder.toString());

        listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomAdapter(this, todoItemArrayList, new CustomAdapter.onAddClick() {
            @Override
            public void onNextClick(int position) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                SingletonTodo.getInstance().getIndexes().add(position);
                SingletonTodo.getInstance().getIds().add(todoItemArrayList.get(position).getName()+"/");
                startActivity(intent);
            }

            @Override
            public void onAddClicked(int position) {
                addChild(position);
            }
        });
        listView.setAdapter(adapter);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSibling();
            }
        });
    }

    private ArrayList<TodoItem> getCurrentList(ArrayList<TodoItem> totoList, int counter) {
        if (counter < SingletonTodo.getInstance().getIndexes().size()) {
            return getCurrentList(
                    totoList.get(
                            SingletonTodo.getInstance().getIndexes().get(counter)
                    ).getChildItems()
                    ,(counter + 1)
            );
        } else {
            return totoList;
        }
    }


    private void addChild(int position) {
        addDialog(true, position);
    }

    private void addSibling() {
        addDialog(false, 0);
    }

    private void addDialog(final boolean isChild, final int pos) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        final EditText text = new EditText(this);
        text.setHint("Insert Task Name");

        dialog.setView(text);
        dialog.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isChild) {
                    todoItemArrayList.get(pos).getChildItems().add(new TodoItem(text.getText().toString() + "", "-"));
                } else {
                    todoItemArrayList.add(new TodoItem(text.getText().toString() + "", "-"));
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    @Override
    public void onBackPressed() {
        try {
            SingletonTodo.getInstance().getIndexes().remove((SingletonTodo.getInstance().getIndexes().size() - 1));
            SingletonTodo.getInstance().getIds().remove((SingletonTodo.getInstance().getIds().size()-1));
        }catch (Exception ex){
            Log.e("Moosa","Array index out of bound");
        }
        super.onBackPressed();
    }
}
