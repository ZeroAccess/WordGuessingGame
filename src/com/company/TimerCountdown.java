package com.company;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerCountdown {
    Toolkit toolkit;
    Timer timer;
    static int countdown = 15;

    public TimerCountdown() {
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new CrunchifyReminder(), 0, // initial delay
                1 * 1000); // subsequent rate
    }

    class CrunchifyReminder extends TimerTask {
        int loop = countdown;
        int lives = 0;

        public void run() {
            if(lives > 0 && loop > 0) {
                System.out.println("You have " + loop + " remaining");
                loop--;
            }else{
                System.out.println("You're out of time!");
                timer.cancel();
            }
        }
    }
    public static void main(String args[]) {
        new TimerCountdown();
        System.out.println("Game has started you have " + countdown + " seconds remaining");
    }
}