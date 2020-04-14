import java.util.Objects;

public class Position {
    private int x,y;
    private int x_max,y_max;

    Position(int x,int y){
        this.x = x;
        this.y = y;
    }

    Position(int x,int y,int x_max,int y_max) throws PositionError{
        this.x_max=x_max;
        this.y_max=y_max;
        if(x>x_max) {
            throw new PositionError("x is bigger than x_max");
        }
        else if(y>y_max) {
            throw new PositionError("y is bigger than y_max");
        }
        this.x = x;
        this.y = y;
    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) throws PositionError{
        if(y>y_max)
            throw new PositionError("y is bigger than y_max");
        else
            this.y = y;
    }

    public void setX(int x) throws PositionError {
        if(x>x_max)
            throw new PositionError("x is bigger than x_max");
        else
            this.x = x;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

class PositionError extends Exception{
    private String error;
    PositionError(String error) {
        this.error=error;
    }
    public String toString(){
        return ("Position Error Occurred: "+error) ;
    }
}
