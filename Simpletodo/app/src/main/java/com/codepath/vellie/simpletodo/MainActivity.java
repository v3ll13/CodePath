package com.codepath.vellie.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items;
    ArrayAdapter<String>itemsAdapter;
    ListView lvItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItem =(ListView) findViewById(R.id.lvitem);
        readItem();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvItem.setAdapter(itemsAdapter);
    }
     private void setupListViewlistenner(){
                lvItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View item, int position, long id) {

                        items.remove(position);
                        itemsAdapter.notifyDataSetChanged();
                        writeItem();
                        return true;
                    }
                });


     }


     public void readItem(){
         File filesDir = getFilesDir();
         File todoFile = new File(filesDir, "todo.txt");

         try {
             items = new ArrayList<String>(FileUtils.readLines(todoFile));
         } catch (IOException e){
             items = new ArrayList<String>();
         }
     }

    public void writeItem(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");

        try {
            FileUtils.writeLines(todoFile,items);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void onAddItem(View v){
        EditText editText =(EditText) findViewById(R.id.editText);
        String itemText = editText.getText().toString();
        itemsAdapter.add(itemText);
        editText.setText("");
        writeItem();
    }
}
