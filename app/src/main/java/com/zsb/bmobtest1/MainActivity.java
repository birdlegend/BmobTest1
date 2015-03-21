package com.zsb.bmobtest1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


public class MainActivity extends Activity {
    Button updateButton, addButton, deleteButton, searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(this, "09b4b7b8c5b9a70d301f175f0e5aac1d");

        addButton = (Button) findViewById(R.id.addButton);
        updateButton = (Button) findViewById(R.id.updateButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        searchButton = (Button) findViewById(R.id.searchButton);

        initAddButton();
        initDeleteButton();
        initSearchButon();
        initUpdateButton();
    }

    void initUpdateButton() {
        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Person person = new Person();
                person.setAddress("Beijing");
                person.update(MainActivity.this, "b0f5d3a89f", new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this, "update at：" + person.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(MainActivity.this, "update failure", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    void initAddButton() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Person person = new Person();
                person.setName("Damin");
                person.setAddress("Guangdong");

                person.save(MainActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this, "创建数据成功，id：" + person.getObjectId(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        Toast.makeText(MainActivity.this, "创建数据失败：" + msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    void initDeleteButton() {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Person person = new Person();
                person.setObjectId("b0f5d3a89f");
                person.delete(MainActivity.this, new DeleteListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this, "delete数据成功", Toast.LENGTH_SHORT).show();
                        
                        //for test of renew github
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(MainActivity.this, "delete数据failue", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    void initSearchButon() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final BmobQuery<Person> personBmobQuery = new BmobQuery<Person>();
                personBmobQuery.getObject(MainActivity.this, "b0f5d3a89f", new GetListener<Person>() {
                    @Override
                    public void onSuccess(Person person) {
                        Toast.makeText(MainActivity.this, "name：" + person.getName() + " table name: " + person.getTableName(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        Toast.makeText(MainActivity.this, "search failure：" + msg, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

}
