package com.noclue;

import com.googlecode.lanterna.TerminalPosition;

import java.util.Objects;

public class Position implements Cloneable{
    private int x,y;
    private int x_max,y_max;
    static int x_maxG,y_maxG;

    static void setMaxPosition(int x_max,int y_max){
        x_maxG=x_max;
        y_maxG=y_max;
    }

    Position(int x_max,int y_max,int x,int y){
        this.x_max=x_max;
        this.y_max=y_max;
        this.y=y;
        this.x=x;
    }

    @Override
    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }



    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y){
        if(!(y>y_max))
            this.y = y;
    }

    public void setX(int x)  {
        if(!(x>x_max))
            this.x = x;
    }

    public Position getRealPosition(){
        return new Position(x_max*6+20,y_max*3+20,x*6,y*3);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return this.x == position.getX() && this.y == position.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public TerminalPosition getTerminalPosition(){
        return new TerminalPosition(this.x,this.y);
    }
}
