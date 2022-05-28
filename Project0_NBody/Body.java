public class Body {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV,
                double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body b) {
        this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);

    }

    public double calcDistance(Body other) {
        double dx = this.xxPos - other.xxPos;
        double dy = this.yyPos - other.yyPos;

        double r2 = Math.pow(dx, 2) + Math.pow(dy, 2);
        double distance = Math.sqrt(r2);

        return distance;
    }

    public double calcForceExertedBy(Body other) {
        final double gravConst = 6.67e-11;
        double dist = this.calcDistance(other);

        return (gravConst * this.mass * other.mass) / Math.pow(dist, 2);
    }

    public double calcForceExertedByX(Body other) {
        double dx = this.xxPos - other.xxPos;
        double force = this.calcForceExertedBy(other);
        double dist = this.calcDistance(other);

        if (dx >= 0) {
            return (force * dx) / dist;
        } else {
            return -(force * dx) / dist;
        }


    }

    public double calcForceExertedByY(Body other) {
        double dy = this.yyPos - other.yyPos;
        double force = this.calcForceExertedBy(other);
        double dist = this.calcDistance(other);

        if (dy >= 0) {
            return (force * dy) / dist;
        } else {
            return -(force * dy) / dist;
        }
    }

    public double calcNetForceExertedByX(Body[] others) {
        int bodyCount = others.length;
        double result = 0.0;

        for (int i = 0; i < bodyCount; i++) {
            if (others[i].equals(this)) {
                result += 0;
            } else {
                result += this.calcForceExertedByX(others[i]);
            }
        }

        return result;

    }

    public double calcNetForceExertedByY(Body[] others) {
        int bodyCount = others.length;
        double result = 0.0;

        for (int i = 0; i < bodyCount; i++) {
            if (others[i].equals(this)) {
                result += 0;
            } else {
                result += this.calcForceExertedByY(others[i]);
            }
        }

        return result;

    }

    public void update(double dt, double xForce, double yForce) {
        double xAcc = xForce / this.mass;
        double yAcc = yForce / this.mass;

        this.xxVel = this.xxVel + dt * xAcc;
        this.yyVel = this.yyVel + dt * yAcc;

        //double newPosX = this.xxPos + dt * newXVel;
        //double newPosY = this.yyPos + dt * newYVel;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;

    }


    //public double update(double dt, int xForce, int yForce){

    //}

}
