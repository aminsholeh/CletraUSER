package com.example.aminsholeh.cletrauser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormActivity extends AppCompatActivity {

    TextView edtTitle, edtAuthor, edtNum;
    Book bookEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        edtTitle = (TextView) findViewById(R.id.title);
        edtAuthor = (TextView) findViewById(R.id.author);
        edtNum = (TextView) findViewById(R.id.num_of_copy);
        Intent it = getIntent();
        bookEdit = (Book) it.getSerializableExtra("bookedit");
        if (bookEdit != null){
            edtTitle.setText(bookEdit.getTitle());
            edtAuthor.setText(bookEdit.getAuthor());
            edtNum.setText(bookEdit.getNum());
        }
    }

    public void saveData(View view){
        DatabaseReference mDb = FirebaseDatabase.getInstance().getReference();
        String bookId = null;

        Book newBook = new Book(
                edtTitle.getText().toString(),
                edtAuthor.getText().toString(),
                edtNum.getText().toString()
        );
        if (bookEdit == null){
            bookId = mDb.child("books").push().getKey();
        }else{
            bookId = bookEdit.getId();
        }

        mDb.child("books").child(bookId).setValue(newBook);
        Toast.makeText(this, "Silahkan refresh untuk memantau kembali berkas Anda", Toast.LENGTH_SHORT).show();
        finish();
    }
}
