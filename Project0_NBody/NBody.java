public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int firstInput = in.readInt();
        double secondInput = in.readDouble();

        return secondInput;
    }


    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int firstInput = in.readInt();
        double secondInput = in.readDouble();

        Body[] result = new Body[firstInput];

        for (int i = 0; i < firstInput; i++) {
            result[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readString());

        }
        return result;
    }

    public static void drawBackground(double radius) {

        String imageToDraw = "images/starfield.jpg";


        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
        StdDraw.show();
        //StdDraw.pause(2000);


    }

    public static void main(String[] args) {

        double time = 0.0;
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];


        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        //drawBackground(radius);


        //for (int i = 0; i < bodies.length; i++) {
        //    bodies[i].draw();
        //}

        //StdDraw.enableDoubleBuffering();


        while (time < T) {
            double[] xForcesArray = new double[bodies.length];
            double[] yForcesArray = new double[bodies.length];

            for (int i = 0; i < bodies.length; i++) {
                xForcesArray[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForcesArray[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForcesArray[i], yForcesArray[i]);
            }

            drawBackground(radius);

            for (int i = 0; i < bodies.length; i++) {
                bodies[i].draw();
            }

            StdDraw.show();


            StdDraw.pause(10);


            time += dt;
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }

    }
}
