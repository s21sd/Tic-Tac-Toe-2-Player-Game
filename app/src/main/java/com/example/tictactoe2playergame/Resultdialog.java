package com.example.tictactoe2playergame;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class Resultdialog extends Dialog {

    private final String  message;
    private  final  MainActivity mainActivity;



    public Resultdialog(@NonNull Context context, String message, MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultdialog);

        TextView messagetxt=findViewById(R.id.messagetxt);
        Button startagain=findViewById(R.id.startagain);

        messagetxt.setText(message);
        startagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.newGame();
                dismiss();
            }
        });
    }
}