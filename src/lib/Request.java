package lib;

import java.util.Objects;

public class Request {
    private Direction direction;

    private int endFloor;

    public Request(Direction direction, int endFloor){
        this.direction = direction;
        this.endFloor = endFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getEndFloor() {
        return endFloor;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setEndFloor(int endFloor) {
        this.endFloor = endFloor;
    }

    @Override
    public int hashCode(){
        return Objects.hash(endFloor, direction);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }

        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        Request r = (Request) obj;
        return endFloor == r.endFloor && direction == r.direction;
    }
}