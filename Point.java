/**
 * wrapper class for point
 * 
 * @author Yeongbae Jeon
 * @version 1.0
 */
public class Point {
    private int x;
    private int y;
    private PointColor color;

    public Point(int x, int y, PointColor color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public PointColor getColor() {
        return this.color;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setColor(PointColor color) {
        this.color = color;
    }
}


