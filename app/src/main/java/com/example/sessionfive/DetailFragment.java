package com.example.sessionfive;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sessionfive.R;

import java.util.ArrayList;

public class DetailFragment extends Fragment implements ItemClickInterface {
    ArrayList<SQLiteService> arrayList = new ArrayList<>();
    TextView btn_up,
            btn_down, txt_detail_description, txt_bookings, txt_spots, txt_number_of_people, txt_counter,txt_final_price,txt_title_desc,txt_main_title;
    Button add_cart;
    EditText edt_date;
    DBHelper dbHelper;
    ServiceAdapter serviceAdapter;
    SQLiteService selectedService;

    public DetailFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String title = getArguments().getString("title");
        String title_desc = getArguments().getString("title_desc");
        dbHelper = new DBHelper(getContext());
        txt_bookings = view.findViewById(R.id.txt_detail_bookings);
        add_cart = view.findViewById(R.id.btn_add_cart);
        txt_main_title = view.findViewById(R.id.txt_main_title);
        txt_title_desc = view.findViewById(R.id.txt_title_description);
        txt_main_title.setText(title);
        txt_title_desc.setText(title_desc);
        txt_final_price = view.findViewById(R.id.txt_final_price);
        txt_spots = view.findViewById(R.id.txt_detail_spots);
        txt_counter = view.findViewById(R.id.txt_counter);
        btn_up = view.findViewById(R.id.btn_up);
        btn_down = view.findViewById(R.id.btn_down);
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                up(txt_counter);
            }
        });
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                down(txt_counter);
            }
        });

        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(txt_counter.getText().toString()) <= 0) {
                    Toast.makeText(getContext(), "Number of people should be more than 0", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Added to Cart", Toast.LENGTH_LONG).show();
                    if (selectedService != null) {
                        dbHelper.updateProperty(selectedService);
                    }
                }

            }
        });

        txt_number_of_people = view.findViewById(R.id.txt_counter);
        txt_detail_description = view.findViewById(R.id.txt_description);
        edt_date = view.findViewById(R.id.edt_detail_date);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        serviceAdapter = new ServiceAdapter(this, getContext(), arrayList);
        recyclerView.setAdapter(serviceAdapter);

//        dbHelper.insertData(R.drawable.castle, "SQLite First Service", "SQLite simple Description", "SQLite Example Title", "SQLite simple decription was made by someone who has more money than every static people in planet saturn", "10/10/2020", "10/10/2022", "15 spots", "5", "10", 0,100);
        retrieveData();
        int total_money = calculateMoney(arrayList);
        txt_final_price.setText("Total amount of payable" + total_money);
    }

    private int calculateMoney(ArrayList<SQLiteService> arrayList) {
        int total = 0;
        for(SQLiteService sqLiteService : arrayList){
            total+=sqLiteService.getMoney();
        }
        return total;
    }

    private void down(TextView txt_counter) {
        int num = Integer.parseInt(txt_counter.getText().toString());
        num -= 1;
        txt_counter.setText(String.valueOf(num));
    }

    private void up(TextView txt_counter) {
        int num = Integer.parseInt(txt_counter.getText().toString());
        num += 1;
        txt_counter.setText(String.valueOf(num));
    }

    private void retrieveData() {
        // Get a readable database instance
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Perform a query to retrieve all rows from the table
        String selectQuery = "SELECT * FROM " + DBHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Check if there are any rows in the cursor
        if (cursor.getCount() > 0) {
            // Iterate over the cursor and add items to the list
            while (cursor.moveToNext()) {
                @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(DBHelper.COLUMN_IMAGE));
                @SuppressLint("Range") String simple_description = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_SIMPLE_DESCRIPTION));
                @SuppressLint("Range") String service_name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_SERVICE_NAME));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TITLE));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DESCRIPTION));
                @SuppressLint("Range") String date_from = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DATE_FROM));
                @SuppressLint("Range") String date_to = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DATE_TO));
                @SuppressLint("Range") String spots = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_SPOTS));
                @SuppressLint("Range") String number_of_people = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NUMBER_OF_PEOPLE));
                @SuppressLint("Range") String coupon_percentage = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_COUPON_PERCENTAGE));
                @SuppressLint("Range") int add_to_cart = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ADD_TO_CART));
                @SuppressLint("Range") int money = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_MONEY));

                // Convert the byte array back to a Bitmap
                Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

//                 Create an Item object and add it to the list
                SQLiteService item = new SQLiteService(service_name, simple_description, imageBitmap, title, description, date_from, date_to, spots, number_of_people, coupon_percentage, add_to_cart,money);
                arrayList.add(item);
            }

            // Notify the adapter that the data has changed
            serviceAdapter.notifyDataSetChanged();
        }

        // Close the cursor and database connection
        cursor.close();
        db.close();
    }

    @Override
    public void itemClick(int position) {
        selectedService = arrayList.get(position);
        txt_bookings.setText("in " + arrayList.get(position).getNumber_of_people() + " bookings");
        edt_date.setText((CharSequence) arrayList.get(position).getDate_from());
        txt_number_of_people.setText(arrayList.get(position).getNumber_of_people());
        txt_detail_description.setText("Description of " + arrayList.get(position).getTitle() + ":  " + arrayList.get(position).getDescription());
        txt_spots.setText("Remaining: " + arrayList.get(position).getSpots());
    }

    @Override
    public void itemClickDelete(int position) {

    }
}