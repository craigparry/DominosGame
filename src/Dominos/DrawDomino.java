package Dominos;

import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
public class DrawDomino extends Canvas{
    private Canvas canvas;
    private GraphicsContext gc;

    public DrawDomino(int num){
        canvas = new Canvas(40,40);
        gc = canvas.getGraphicsContext2D();
        gc.setFill ( Color . WHITE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(4);
        gc.strokeRect(0,0,canvas.getWidth(),canvas.getHeight());

        switch(num){

            case 1: gc.setStroke(Color.BLACK);
                    gc.strokeOval(19,19,2,2);
            break;
            case 2: gc.setStroke(Color.BLACK);
                    gc.strokeOval(10,10,2,2);
                    gc.strokeOval(28,28,2,2);
            break;
            case 3: gc.setStroke(Color.BLACK);
                    gc.strokeOval(19,19,2,2);
                    gc.strokeOval(10,10,2,2);
                    gc.strokeOval(28,28,2,2);
            break;
            case 4: gc.setStroke(Color.BLACK);
                    gc.strokeOval(10,10,2,2);
                    gc.strokeOval(28,28,2,2);
                    gc.strokeOval(28,10,2,2);
                    gc.strokeOval(10,28,2,2);
            break;
            case 5: gc.setStroke(Color.BLACK);
                    gc.strokeOval(19,19,2,2);
                    gc.strokeOval(10,10,2,2);
                    gc.strokeOval(28,28,2,2);
                    gc.strokeOval(28,10,2,2);
                    gc.strokeOval(10,28,2,2);
            break;
            case 6: gc.setStroke(Color.BLACK);
                    gc.strokeOval(10,19,2,2);
                    gc.strokeOval(28,19,2,2);
                    gc.strokeOval(10,10,2,2);
                    gc.strokeOval(28,28,2,2);
                    gc.strokeOval(28,10,2,2);
                    gc.strokeOval(10,28,2,2);
            break;
            default:
            break;
        }

    }


//    @Override
//    public void start ( Stage stage ) {
//        stage . setTitle (" Canvas Drawing Demo ");
//        Canvas domino = drawDomino(6);
//
//        stage . setScene ( new Scene ( new StackPane ( domino )));
//        stage . show ();
//    }
//
//    public static void main ( String [] args ) { launch ( args ); }

}
