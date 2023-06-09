package com.example.projeto_integrador_java.gamepanel;

import static android.os.SystemClock.currentThreadTimeMillis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.view.MotionEvent;

import androidx.core.content.ContextCompat;

import com.example.projeto_integrador_java.Game;
import com.example.projeto_integrador_java.MainActivity;
import com.example.projeto_integrador_java.R;

/**
 * GameOver is a panel which draws the text Game Over to the screen.
 */
public class GameOver {
    private Context context;
    private Paint paint;

    private int time = (int) ((currentThreadTimeMillis())*0.001) ;
    public GameOver(Context context) {
        this.context = context;
        paint = new Paint();
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        drawGameOver(canvas);
        drawMainMenu(canvas);

    }

    private void drawGameOver(Canvas canvas) {
        // Code for drawing the "Game Over" text
        String text = "Game Over";

        float x = 600;
        float y = 200;

        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.gameOver);
        paint.setColor(color);
        float textSize = 150;
        paint.setTextSize(textSize);

        canvas.drawText(text, x, y, paint);
    }
    private void drawMainMenu(Canvas canvas) {
        String text = "Main Menu";

        float x = 600;
        float y = 800;

        int color = ContextCompat.getColor(context, R.color.gameOver);
        paint.setColor(color);
        float textSize = 150;
        paint.setTextSize(textSize);

        canvas.drawText(text, x, y, paint);
    }
    public void handleTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isMainMenuButtonTouched(touchX, touchY)) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        }
    }
    private boolean isMainMenuButtonTouched(float touchX, float touchY) {
        // Define os limites do botão do Main Menu
        float buttonLeft = 600;
        float buttonTop = 800 - 150;
        float buttonRight = 600 + paint.measureText("Main Menu");
        float buttonBottom = 800;

        // Verifica se o toque de coordenadas estão dentro dos limites de pixels Main Menu
        return touchX >= buttonLeft && touchX <= buttonRight && touchY >= buttonTop && touchY <= buttonBottom;
    }
}