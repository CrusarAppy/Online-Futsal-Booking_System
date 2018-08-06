package com.example.crusarappy.OnlineFutsalBookingSystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class futsal_list extends AppCompatActivity {
    // Button book;
    private List<Futsal> FutsalList = new ArrayList<Futsal>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_list);
////        book=findViewById(R.id.book);
////        book.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent i= new Intent(futsal_list.this,BookingActivity.class);
////                startActivity(i);
////                finish();
////            }
//        });
        populateFutsalList();
        populateListView();
        registerClickCallback();
    }

    private void populateFutsalList() {
        FutsalList.add(new Futsal("B Fit Sports (Futsal)", "Address", R.drawable.about, "Contact"));
        FutsalList.add(new Futsal("Champion Futsal & Paintball Pvt. Ltd.", "Address", R.drawable.about," Contact"));
        FutsalList.add(new Futsal("G & S Futsal Ground", "Address", R.drawable.about, "Contact"));
        FutsalList.add(new Futsal("Ranipauwa Sports Center", "Address", R.drawable.about, "Contact"));
        FutsalList.add(new Futsal("Top Corner Futsal", "Address", R.drawable.about, "Contact"));
        FutsalList.add(new Futsal("Barahi Futsal Arena", "Address", R.drawable.about, "Contact"));
        FutsalList.add(new Futsal("Ammarsingh Sports Center Pvt. Ltd.", "Address", R.drawable.about, "Contact"));
        FutsalList.add(new Futsal("New Airport Futsal", "Address", R.drawable.about," Contact"));
    }

    private void populateListView() {
        ArrayAdapter<Futsal> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.FutsalsListView);
        list.setAdapter(adapter);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.FutsalsListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {

                Futsal clickedFutsal = FutsalList.get(position);
                String message = "You clicked position " + position
                        + " Which is Futsal make " + clickedFutsal.getName();
                Toast.makeText(futsal_list.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<Futsal> {
        public MyListAdapter() {
            super(futsal_list.this, R.layout.activity_list, FutsalList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.activity_list, parent, false);
            }

            // Find the Futsal to work with.
            Futsal currentFutsal = FutsalList.get(position);

            // Fill the view
            ImageView imageView = (ImageView) itemView.findViewById(R.id.item_image);
            imageView.setImageResource(currentFutsal.getImage());

            // Make:
            TextView makeText = (TextView) itemView.findViewById(R.id.item_txtName);
            makeText.setText(currentFutsal.getName());

            // Year:
            TextView yearText = (TextView) itemView.findViewById(R.id.item_txtAddress);
            yearText.setText( currentFutsal.getAddress());

            // Condition:
            TextView condionText = (TextView) itemView.findViewById(R.id.item_txtContact);
            condionText.setText(currentFutsal.getContact());

            return itemView;
        }
    }
}
