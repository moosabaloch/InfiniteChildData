package com.moosa.unlimitedtodo;

import java.util.ArrayList;

/**
 * Created by Moosa on 06/08/2017.
 * moosa.bh@gmail.com
 */

public class SingletonTodo {

    private static SingletonTodo instance;
    private ArrayList<TodoItem> todoItemArrayList;
    private ArrayList<Integer> indexes;
    private ArrayList<String> ids;


    public SingletonTodo() {
        todoItemArrayList = new ArrayList<>();
        indexes = new ArrayList<>();
        ids = new ArrayList<>();
        ids.add("/");
    }

    public static SingletonTodo getInstance() {
        if (instance == null) {
            instance = new SingletonTodo();
        }
        return instance;
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    public ArrayList<TodoItem> getTodoItemArrayList() {
        return todoItemArrayList;
    }

    public ArrayList<Integer> getIndexes() {
        return indexes;
    }


}
