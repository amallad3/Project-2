/**
 * wrapper class for line
 * 
 * @author Yeongbae Jeon
 * @version 1.0
 */
public class Line {
    private int endPoint1;
    private int endPoint2;

    public Line(int endPoint1, int endPoint2) {
        this.endPoint1 = endPoint1;
        this.endPoint2 = endPoint2;
    }

    public int getEndPoint1() {
        return this.endPoint1;
    }

    public int getEndPoint2() {
        return this.endPoint2;
    }
}
