package com.praveen.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView t=(TextView)findViewById(R.id.message);
        FirebaseDatabase s=FirebaseDatabase.getInstance().getReference("text").getDatabase();
        t.setText(s.toString());

        FirebaseDatabase.getInstance().getReference("text").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                EditText txt = (EditText) findViewById(R.id.text2);
                String s = txt.getEditableText().toString();
                t.setText(s);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "nothing happened", Toast.LENGTH_SHORT);
            }
        });
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 EditText txt = (EditText) findViewById(R.id.text2);
                 String s = txt.getEditableText().toString();

                FirebaseDatabase.getInstance().getReference("text").setValue(s);
                String tst = "the text updated is: " + s + " ";
                Toast.makeText(getApplicationContext(), tst, Toast.LENGTH_SHORT).show();
            }


        });


    }
}
