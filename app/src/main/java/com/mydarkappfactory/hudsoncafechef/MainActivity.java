package com.mydarkappfactory.hudsoncafechef;

import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button[] tables;
    ArrayList<Integer> tableNumbers;
    FirebaseAuth mAuth;
    DatabaseReference firebaseDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        firebaseDb = FirebaseDatabase.getInstance().getReference();

        mAuth.signInWithEmailAndPassword("chef@hudsoncafe.com", "hudsoncafeadmin")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Hudson", "Sign in status: " + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.d("Hudson", task.getException().toString());
                        } else {
//
                        }
                    }
                });

        tables = new Button[32];

        tableNumbers = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            tables[i] = findViewById(getResources().getIdentifier("butt" + i, "id", getPackageName()));
            tables[i].setText((i + 1) + "");
            firebaseListenerSetup(i);
            Log.d("Hudson", i + "");
        }

    }

    public void tableInformation(View view) {
        int tag = Integer.parseInt(view.getTag().toString());
        Intent intent = new Intent(MainActivity.this, TableActivity.class);
        intent.putExtra("TABLE_NUMBER", tag);
        startActivity(intent);
//        System.out.println(tag);
    }

    public void firebaseListenerSetup(int tableNum) {
        final int tableNumber = tableNum + 1;
        firebaseDb.child("tables").child(Integer.toString(tableNumber)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Table x = new Table(dataSnapshot, tableNumber);

                if (x.isVacant()) {
                    tables[tableNumber - 1].setBackgroundResource(R.color.tableGreen);
                } else {
                    tables[tableNumber - 1].setBackgroundResource(R.color.tableRed);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
