package com.codegreed_devs.journalalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DetailsAdapter adapter;

    List<Details> detailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        detailsList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Add all items here
        detailsList.add(
                new Details(
                        "Trip to maasai mara",
                        "The trip was awesome with amaizing views of the nature and animals around in kenya. "
                )
        );
        //Create adapter objects
        adapter = new DetailsAdapter(this, detailsList);
        recyclerView.setAdapter(adapter);
    }
}
