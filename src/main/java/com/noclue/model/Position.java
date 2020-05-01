package com.noclue.model;

public class Position implements Cloneable{
    private int x,y;
    private int x_max=1,y_max=1;


    public Position(int x_max, int y_max, int x, int y){
        if(x_max>0)
            this.x_max=x_max;
        else
            x_max=1;
        if(y_max>0)
            this.y_max=y_max;
        else
            y_max=1;

        //System.out.println(x_max+" "+y_max);

        if(y<=y_max && y>=0)
            this.y=y;
        else {
            this.y = y_max;
            System.out.println(y+" "+this.y);
        }

        if(x<=x_max && x>=0)
            this.x=x;
        else {
            this.x = x_max;
            System.out.println(x+" "+this.x);
        }
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
        if(!(y>y_max) && y>0)
            this.y = y;
    }

    public void setX(int x)  {
        if(!(x>x_max) && x>0)
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


}
