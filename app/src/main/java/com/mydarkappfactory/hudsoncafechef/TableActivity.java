package com.mydarkappfactory.hudsoncafechef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    DatabaseReference firebaseDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        firebaseDb = FirebaseDatabase.getInstance().getReference();

        TextView tableNumberText = findViewById(R.id.tableNumberHead);

        Intent intent = getIntent();
        int tableNumber = intent.getIntExtra("TABLE_NUMBER", -1);

        tableNumberText.setText(Integer.toString(tableNumber + 1));

        firebaseListenerSetup(tableNumber);

    }

    public void firebaseListenerSetup(int tableNum) {
        final int tableNumber = tableNum + 1;
        firebaseDb.child("tables").child(Integer.toString(tableNumber)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Table x = new Table(dataSnapshot, tableNumber);

                StringBuilder dishNames = new StringBuilder("");
                StringBuilder dishQuantities = new StringBuilder("");

                for (FirebaseDish y: x.getDishes()) {
                    dishNames.append(y.getName() + "\n");
                    dishQuantities.append(y.getQuantity() + "\n");
                }

                TextView dishNamesText = findViewById(R.id.dishListNames);
                TextView dishQuantitiesText = findViewById(R.id.dishListQuantities);

                dishNamesText.setText(dishNames.toString());
                dishQuantitiesText.setText(dishQuantities.toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
