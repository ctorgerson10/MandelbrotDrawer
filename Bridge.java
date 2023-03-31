/*
 * Created by Andrew
 */


public class Bridge implements Runnable{
    
    //locArr and iterationCount come directly from Ethan mandelbrot code
    private double[] locArr = new double[2];
    private int iterationCount;

    //constructor to pass the values from ethans mandelbrot code when it works
    public Bridge(double[] locs, int iterCount){
        this.locArr = locs;
        this.iterationCount = iterCount;
    }

    //constructor to pass values from ethans mandelbrot code when spirals to infinity
        //iteration count value will probably need to change to negative or something for rendering
    public Bridge(double[] locs){
        this.locArr = locs;
        this.iterationCount = 0;
    }

    //each thread will be passed the values and run cj's code to render
        //joins likely needed so each thread is NOT rendering individual frames, but rather cohesive product
            //need to play around with rendering to fully understand here
    public void run(){
        //insert cj code here
    }


    //i dont think i need a main method, just here to play with if necessary
    public static void main(String[] args){

    }
}
