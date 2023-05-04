package com.example.tictactoe2playergame;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class singleplayer extends AppCompatActivity {

    TextView playeronename,playertwoname;
    Button reset;

    @SuppressLint("StaticFieldLeak")
    static Button button1;
    @SuppressLint("StaticFieldLeak")
    static Button button2;
    @SuppressLint("StaticFieldLeak")
    static Button button3;
    @SuppressLint("StaticFieldLeak")
    static Button button4;
    @SuppressLint("StaticFieldLeak")
    static Button button5;
    @SuppressLint("StaticFieldLeak")
    static Button button6;
    @SuppressLint("StaticFieldLeak")
    static Button button7;
    @SuppressLint("StaticFieldLeak")
    static Button button8;
    @SuppressLint("StaticFieldLeak")
    static Button button9;
    private static boolean gameover = false;

    private final Button[][] buttons = new Button[3][3];
    private static boolean player1Turn = true;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplayer);

        button1=findViewById(R.id.button_00);
        button2=findViewById(R.id.button_01);
        button3=findViewById(R.id.button_02);
        button4=findViewById(R.id.button_10);
        button5=findViewById(R.id.button_11);
        button6=findViewById(R.id.button_12);
        button7=findViewById(R.id.button_20);
        button8=findViewById(R.id.button_21);
        button9=findViewById(R.id.button_22);
        reset=findViewById(R.id.Reset);

        playeronename=findViewById(R.id.playeronename);
        playertwoname=findViewById(R.id.playertwoname);


        playeronename.setText("You");
        playertwoname.setText("Robot");


        reset.setOnClickListener(view -> {
            final MediaPlayer m=MediaPlayer.create(view.getContext(),R.raw.over);
            m.start();
            newGame();
        });



        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String buttonID = "button_" + i + j;
                @SuppressLint("DiscouragedApi") int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
            }
        }


    }

    public  void click(View view)
    {
        final MediaPlayer mp= MediaPlayer.create(this, R.raw.tap);
        mp.start();
        if(gameover)
        {
            return;
        }
        Button button =(Button) view;
        if(button.getText().toString().equals(""))
        {
            if(player1Turn)
            {
                button.setText("X");
            }
            else{
                button.setText("O");
            }

            if(checkforWin())
            {
                gameover=true;
                if(player1Turn)
                {
                    singleDialog singleDialog =new singleDialog(singleplayer.this,"You Won", singleplayer.this);
                    singleDialog.setCancelable(false);
                    singleDialog.show();

                }
                else{
                    singleDialog singleDialog =new singleDialog(singleplayer.this,"Computer Won", singleplayer.this);
                    singleDialog.setCancelable(false);
                    singleDialog.show();
                }
            }
            else if(checkforDraw())
            {
                gameover=true;
                singleDialog singleDialog =new singleDialog(singleplayer.this,"Draw", singleplayer.this);
                singleDialog.setCancelable(false);
                singleDialog.show();
            }
            else{
                player1Turn=!player1Turn;
                if(!player1Turn)
                {
                    computerMove();
                }
            }
        }
    }

    private boolean checkforWin()
    {
        String[][] field=new String[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                field[i][j]=buttons[i][j].getText().toString();
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[i][0].equals(field[i][1]) &&field[i][0].equals(field[i][2]) &&!field[i][0].equals(""))
            {
                return true;
            }
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }
        return field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("");
    }

    public  boolean  checkforDraw()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(buttons[i][j].getText().toString().equals(""))
                {
                    return false;

                }
            }
        }

        return  true;
    }

    public void computerMove() {
        boolean moveMade = false;
        while(!moveMade) {
            int[] bestMove = new int[2];
            int bestScore = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j].getText().toString().equals("")) {
                        buttons[i][j].setText("O");
                        int currentScore = minimax(2, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                        buttons[i][j].setText("");

                        if (currentScore > bestScore) {
                            bestScore = currentScore;
                            bestMove[0] = i;
                            bestMove[1] = j;
                        }
                    }
                }
            }

            buttons[bestMove[0]][bestMove[1]].setText("O");
            moveMade = true;

            if(checkforWin()) {
                gameover = true;
                singleDialog singleDialog = new singleDialog(singleplayer.this, "Computer Won", singleplayer.this);
                singleDialog.setCancelable(false);
                singleDialog.show();
            } else if(checkforDraw()) {
                gameover = true;
                singleDialog singleDialog = new singleDialog(singleplayer.this, "Draw", singleplayer.this);
                singleDialog.setCancelable(false);
                singleDialog.show();
            } else {
                player1Turn = true;
            }
        }
    }

    private int minimax(int depth, int alpha, int beta, boolean maximizingPlayer) {
        if (checkforWin() && maximizingPlayer) {
            return -1;
        } else if (checkforWin() && !maximizingPlayer) {
            return 1;
        } else if (checkforDraw()) {
            return 0;
        }

        if (depth == 0) {
            return 0;
        }

        if (maximizingPlayer) {
            int bestScore = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j].getText().toString().equals("")) {
                        buttons[i][j].setText("O");
                        int score = minimax(depth - 1, alpha, beta, false);
                        buttons[i][j].setText("");

                        bestScore = Math.max(bestScore, score);
                        alpha = Math.max(alpha, score);

                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }

            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j].getText().toString().equals("")) {
                        buttons[i][j].setText("X");
                        int score = minimax(depth - 1, alpha, beta, true);
                        buttons[i][j].setText("");

                        bestScore = Math.min(bestScore, score);
                        beta = Math.min(beta, score);

                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }

            return bestScore;
        }
    }

    public static void newGame()
    {

        player1Turn = true;
        gameover = false;
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");

    }



}


