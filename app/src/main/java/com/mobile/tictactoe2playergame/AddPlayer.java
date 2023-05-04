package com.mobile.tictactoe2playergame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        EditText playerone=findViewById(R.id.playerone);
        EditText playertwo=findViewById(R.id.playertwo);
        Button startgamebtn=findViewById(R.id.startgamebtn);

        Button againComp=findViewById(R.id.compagainst);

        againComp.setOnClickListener(view -> {
            Intent intent =new Intent(AddPlayer.this, singleplayer.class);
            startActivity(intent);
        });



        startgamebtn.setOnClickListener(view -> {

            String Playeronename=playerone.getText().toString();
            String Playertwoname=playertwo.getText().toString();

            if(Playeronename.isEmpty()||Playertwoname.isEmpty())
            {
                Toast.makeText(AddPlayer.this, "Please Enter the player name", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent =new Intent(AddPlayer.this, MainActivity.class);
                intent.putExtra("PlayerOne",Playeronename);
                intent.putExtra("PlayerTwo",Playertwoname);
                startActivity(intent);

            }

        });
    }
}