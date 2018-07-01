package com.codegreed_devs.journalalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextHeading, editTextThought;
    private Button btnUpdate;

    private FirebaseFirestore db;
    private Details details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = FirebaseFirestore.getInstance();

        editTextHeading = findViewById(R.id.editTextHeading);
        editTextThought = findViewById(R.id.editTextThought);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

        details = (Details) getIntent().getSerializableExtra("detail");

        //use details to update the edit Text
        editTextHeading.setText(details.getHeading());
        editTextThought.setText(details.getThought());
    }

    private boolean fieldIsEmpty(String Heading, String Thought){
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

    private void updateDetails(){
        String Heading = editTextHeading.getText().toString().trim();
        String Thought = editTextThought.getText().toString().trim();

        if (!fieldIsEmpty(Heading, Thought)){
            Details d = new Details(
                    Heading,
                    Thought
            );

            db.collection("details").document(details.getId())
                    .set(d)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(UpdateActivity.this, "Update successfull", Toast.LENGTH_SHORT).show();

                        }
                    });

        }
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnUpdate:{
                updateDetails();
                break;
            }
        }
    }

}
