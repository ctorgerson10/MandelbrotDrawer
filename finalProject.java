//central location for the entirety of the project
//basically just a cleaner implementation where necessary methods are called
    //easier testing without breaking someone else's code haha

public class finalProject {    

    //IMPLEMENTATION IDEAS
        //V1:
            //mandelbrot will essentially run how it currently is, thread will be inserted
                //directly into the mandelbrot class to send outputs to bridge as soon as they occur
                //will be simplest to implement based on current mandelbrot class?? i think

        //V2:
            //mandelbrot will be passed values that will be created in the main method within finalProject
                //that output can then be passed to a thread using bridge
                //work very similar to the goblin carver assignment done in 3600
                //will require mdoficiation to mandelbrot class
                    //unsure if this will work as intended? need to ask ethan

        //V3??? none i can think of
    public static void main(String[] args) {
        Mandelbrot m1 = new Mandelbrot();
        //unsure if multiple instances of mandelbrot need to be created 
            //i dont think so but here as a reminder to test
        // Mandelbrot m2 = new Mandelbrot();

        
       // Bridge bridgeForm1 = new Bridge(){

        }

}
