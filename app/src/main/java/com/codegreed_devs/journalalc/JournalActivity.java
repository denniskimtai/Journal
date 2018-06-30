package com.codegreed_devs.journalalc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class JournalActivity extends AppCompatActivity {

    private EditText editTextHeading, editTextThought;
    private Button btnSave;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        db = FirebaseFirestore.getInstance();

        editTextHeading = (EditText) findViewById(R.id.editTextHeading);
        editTextThought = (EditText) findViewById(R.id.editTextThought);
        btnSave = (Button) findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Heading = editTextHeading.getText().toString().trim();
                String Thought = editTextThought.getText().toString().trim();

                if (!validateInputs(Heading,Thought)){

                    CollectionReference dbDetails = db.collection("Details");

                    Details details = new Details(
                            Heading,
                            Thought
                    );
                    //Save to firestone and listen for success or failure
                    dbDetails.add(details)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {

                                    Toast.makeText(JournalActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(JournalActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }

                Intent intent = new Intent(JournalActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean validateInputs(String Heading, String Thought){
        if(Heading.isEmpty()){
            editTextHeading.setError("Heading required");
            editTextHeading.requestFocus();
            return true;
        }

        if(Thought.isEmpty()){
            editTextThought.setError("Please enter your thoughts today to save");
            editTextThought.requestFocus();
            return true;
        }
        return false;
    }
}
