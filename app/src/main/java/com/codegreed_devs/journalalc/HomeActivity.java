package com.codegreed_devs.journalalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import android.view.Menu;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DetailsAdapter adapter;
    ProgressBar progressBar;

    private FirebaseFirestore db;
    FirebaseAuth firebaseAuth;

    List<Details> detailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();
        String Journal = firebaseAuth.getUid();

        detailsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        //Create adapter objects
        adapter = new DetailsAdapter(this, detailsList);
        recyclerView.setAdapter(adapter);

        //Query the dp to get documents stored
        db.collection(Journal).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        progressBar.setVisibility(View.GONE);

                        if (!documentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = documentSnapshots.getDocuments();
                            //Convert to details format
                            for (DocumentSnapshot d: list){

                                Details p = d.toObject(Details.class);
                                p.setId(d.getId());
                                detailsList.add(p);
                            }
                            //Notify adapter on the change
                            adapter.notifyDataSetChanged();

                        }

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main_menu, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.thought){
            Intent intent = new Intent(this, JournalActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.logOut){
            firebaseAuth.signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
