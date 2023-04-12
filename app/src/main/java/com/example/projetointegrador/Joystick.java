package com.example.projetointegrador;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Joystick {

    private int outerCircleCenterPositionX;
    private int outerCircleCenterPositionY;
    private int innerCircleCenterPositionX;
    private int innerCircleCenterPositionY;
    private final int outerCircleRadius;
    private final int innerCircleRadius;
    private final Paint outerCirclePaint;
    private final Paint innerCirclePaint;
    private double joystickCenterToTouchDistance;
    private boolean isPressed;

    private double actuatorX;
    private double actuatorY;

    public Joystick(int centerPositionX, int centerPositionY, int outerCircleRadius, int innerCircleRadius){

        outerCircleCenterPositionX = centerPositionX;
        outerCircleCenterPositionY = centerPositionY;
        innerCircleCenterPositionX = centerPositionX;
        innerCircleCenterPositionY = centerPositionY;

        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;

        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.GRAY);
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.GREEN);
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

    }



    public void draw(Canvas canvas) {

        canvas.drawCircle(outerCircleCenterPositionX, outerCircleCenterPositionY, outerCircleRadius, outerCirclePaint);
        canvas.drawCircle(innerCircleCenterPositionX, innerCircleCenterPositionY, innerCircleRadius, innerCirclePaint);

    }

    public void update() {

        updateInnerCirclePosition();
    }

    private void updateInnerCirclePosition() {

        innerCircleCenterPositionX = (int)(outerCircleCenterPositionX + actuatorX * outerCircleRadius);
        innerCircleCenterPositionY = (int)(outerCircleCenterPositionY + actuatorY * outerCircleRadius);

    }

    public boolean isPressed(double touchPositionX, double touchPositionY) {

        joystickCenterToTouchDistance = Math.sqrt(Math.pow(outerCircleCenterPositionX - touchPositionX, 2) + Math.pow(outerCircleCenterPositionY -touchPositionY, 2));

        return joystickCenterToTouchDistance < outerCircleRadius;

    }

    public void setIsPressed(boolean isPressed) {

        this.isPressed = isPressed;

    }

    public boolean getIsPressed() {

        return isPressed;

    }

    public void setActuator(double touchPositionX, double touchPositionY) {

        double deltaX = touchPositionX - outerCircleCenterPositionX;
        double deltaY = touchPositionY - outerCircleCenterPositionY;
        double deltaDistance = Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2));

        if (deltaDistance < outerCircleRadius){
            actuatorX = deltaX/outerCircleRadius;
            actuatorY = deltaY/outerCircleRadius;

        }
        else{

            actuatorX = deltaX/deltaDistance;
            actuatorY = deltaY/deltaDistance;

        }

    }

    public void resetActuator() {

        actuatorX = 0;
        actuatorY = 0;

    }

    public double getActuatorX() {
        return actuatorX;
    }

    public double getActuatorY() {
        return actuatorY;
    }

    public static class howplay extends AppCompatActivity {

        private Button voltarmenu;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.howplay);

            voltarmenu = findViewById(R.id.voltarmenu);

            //AO CLICAR NO BOTAO JOGAR
            voltarmenu.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(howplay.this, InicioGame.class);
                    startActivity(intent);

                }
            });
        }
    }
}
