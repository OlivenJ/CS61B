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
}
