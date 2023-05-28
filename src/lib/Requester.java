package lib;

import java.util.Queue;
import java.util.Random;
public class Requester implements Runnable {
    private final Queue<Request> requests;
    private final int numberOfFloors;

    private final long period;

    private boolean isBreak;

    public void breakMove(){isBreak = true;}

    public Requester(Queue<Request> requests, int numberOfFloors, long period){
        this.requests = requests;
        this.numberOfFloors = numberOfFloors;
        this.period = period;
        this.isBreak = false;
    }

    @Override
    public void run(){
        Random rand = new Random();

        while(!isBreak){
            int floor = rand.nextInt(numberOfFloors);
            Direction direction = rand.nextBoolean()? Direction.DOWN : Direction.UP;
            Request request = new Request(direction, floor);
            requests.add(request);
            System.out.printf("New request to move %s. Floor: %d\n", direction, floor);
            // System.out.println(requests.size());

            try{
                Thread.sleep(period);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }
}