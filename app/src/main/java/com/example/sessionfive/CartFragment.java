package com.example.sessionfive;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Visibility;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartFragment extends Fragment implements ItemClickInterface{
    ArrayList<SQLiteService> arrayList = new ArrayList<>();
    CartAdapter cartAdapter;
    RecyclerView recyclerView;
    EditText edt_coupon;
    TextView txt_coupon_successful,txt_total_item_price;
    Button btn_submit_coupon,btn_proceed_checkout;
    DBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartAdapter = new CartAdapter(this,getContext(),arrayList);
        dbHelper = new DBHelper(getContext());
        btn_proceed_checkout = view.findViewById(R.id.btn_proceed_checkout);
        recyclerView = view.findViewById(R.id.recyclerViewCart);
        edt_coupon = view.findViewById(R.id.edt_coupon_code);
        txt_coupon_successful = view.findViewById(R.id.txt_coupon_succesful);
        txt_coupon_successful.setText("");
        txt_total_item_price = view.findViewById(R.id.txt_total_item_price);
        btn_submit_coupon = view.findViewById(R.id.btn_submit_coupon);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cartAdapter);
        retrieveData();
        final int[] totalMoney = {calculateTotalPeople(arrayList)};
        txt_total_item_price.setText("Total amount payable ("+arrayList.size() + " items): " + totalMoney[0]);
        int percentage = Integer.parseInt(arrayList.get(0).getCoupon_percentage());
        int percentageNumber = (totalMoney[0] * percentage )/100;
        btn_submit_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_coupon.getText().toString().equals("abcd")){
                    totalMoney[0]=totalMoney[0]-percentageNumber;
                    txt_total_item_price.setText("Total amount payable ("+arrayList.size() + " items): " + totalMoney[0]);
                    txt_coupon_successful.setText("Coupon Successfully Applied!");
                }else Toast.makeText(getContext(),"Coupon isnot valuable",Toast.LENGTH_LONG).show();
            }
        });
        btn_proceed_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Saved in Database",Toast.LENGTH_LONG).show();
                arrayList.clear();
                cartAdapter.notifyDataSetChanged();
                txt_total_item_price.setText("");
                txt_coupon_successful.setText("");
            }
        });
    }

    @Override
    public void itemClick(int position) {

    }

    @Override
    public void itemClickDelete(int position) {
        arrayList.remove(arrayList.get(position));
        final int[] totalMoney = {calculateTotalPeople(arrayList)};
        txt_total_item_price.setText("Total amount payable ("+arrayList.size() + " items): " + totalMoney[0]);
        cartAdapter.notifyDataSetChanged();
    }

    private void retrieveData()  {
        // Get a readable database instance
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Perform a query to retrieve all rows from the table
        String selectQuery = "SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.COLUMN_ADD_TO_CART + " = 1";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Check if there are any rows in the cursor
        if (cursor.getCount() > 0) {
            // Iterate over the cursor and add items to the list
            while (cursor.moveToNext())
            {
                @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(DBHelper.COLUMN_IMAGE));
                @SuppressLint("Range") String simple_description = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_SIMPLE_DESCRIPTION));
                @SuppressLint("Range")  String service_name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_SERVICE_NAME));
                @SuppressLint("Range")  String title = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TITLE));
                @SuppressLint("Range")  String description = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DESCRIPTION));
                @SuppressLint("Range")  String date_from = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DATE_FROM));
                @SuppressLint("Range")  String date_to = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DATE_TO));
                @SuppressLint("Range")  String spots = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_SPOTS));
                @SuppressLint("Range")  String number_of_people = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NUMBER_OF_PEOPLE));
                @SuppressLint("Range")  String coupon_percentage = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_COUPON_PERCENTAGE));
                @SuppressLint("Range")  int add_to_cart = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ADD_TO_CART));
                @SuppressLint("Range")  int money = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_MONEY));

                // Convert the byte array back to a Bitmap
                Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

//                 Create an Item object and add it to the list
                SQLiteService item = new SQLiteService(service_name,simple_description,imageBitmap,title,description,date_from,date_to,spots,number_of_people,coupon_percentage,add_to_cart,money);
                arrayList.add(item);
            }

            // Notify the adapter that the data has changed
            cartAdapter.notifyDataSetChanged();
        }

        // Close the cursor and database connection
        cursor.close();
        db.close();
    }
    private int calculateTotalPeople(ArrayList<SQLiteService> serviceList) {
        int totalPeople = 0;

        for (SQLiteService service : serviceList) {
            totalPeople+=service.getMoney();
        }

        return totalPeople;
    }
}