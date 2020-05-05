package com.example.a300cem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.content.Intent;
import android.graphics.PostProcessor;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.Button;

import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collection;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    FirebaseAuth auth;
    private Button logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView userN = findViewById(R.id.usernamedisplay);
        final String user = auth.getInstance().getCurrentUser().getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.d(TAG, String.valueOf(db));
       CollectionReference data = db.collection("UID");
       DocumentReference documentReference = data.document(user);
       documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
           @Override
           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
               if (task.isSuccessful()) {
                   DocumentSnapshot document = task.getResult();
                   if (document.exists()) {
                       Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                       Log.d(TAG, "DocumentSnapshot data: " + document.getString("name"));
                       String name = document.getString("name");
                       userN.setText(name);
                   } else {
                       Log.d(TAG, "No such document");
                   }
               } else {
                   Log.d(TAG, "get failed with ", task.getException());
               }
           }
       });


       /*Log.d(TAG,"username = " + query.toString());
     /   userN.setText(query.toString());
       collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if(task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        Log.d(TAG, String.valueOf(documentSnapshot.getData()));
                    }
               }
           }
       });

        */


        /*

        final DatabaseReference database = FirebaseDatabase.getInstance().getReference("UID");
        DatabaseReference userdata = database.child(user).child("username");
        Query test1 = userdata.equalTo(user);
        Log.d(TAG, "Query = " + test1);
        Query query = userdata
                .orderByChild("studentID")
                .equalTo(user);
        query.addListenerForSingleValueEvent(valueEventListener);

        ValueEventListener valueEventListener= new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                Log.d(TAG, "username = " + name);
                userN.setText(name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "ERROR ");
            }

        };



        Log.d(TAG, "DatabaseReference = "+query.toString());
 */
        logout = (Button) findViewById(R.id.logoutbutton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    /*
    ValueEventListener valueEventListener= new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Name name = dataSnapshot.getValue(Name.class);
            Log.d(TAG, "username = " + name);
            //userN.setText(name);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.d(TAG, "ERROR ");
        }

    };

     */

}
