package com.mobile.tictactoe2playergame;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class singleDialog extends Dialog {
    private final String  message;
    private  final  singleplayer singleplayer;

    public singleDialog(@NonNull Context context, String message, singleplayer singleplayer) {
        super(context);
        this.message = message;
        this.singleplayer = singleplayer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_dialog);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView messagetxt=findViewById(R.id.messagetxt);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button startagain=findViewById(R.id.startagain);

        messagetxt.setText(message);
        startagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.mobile.tictactoe2playergame.singleplayer.newGame();
                dismiss();
            }
        });
    }
}