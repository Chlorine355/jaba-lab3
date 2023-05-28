package lib;

import java.util.Queue;

public class ElevatorSystem implements Runnable{
    private Elevator firstElevator;
    private Elevator secondElevator;
    private Queue<Request> requests;
    private boolean isBreak;

    public ElevatorSystem(){}

    public ElevatorSystem(int numberOfFloors, Queue<Request> requests){
        firstElevator = new Elevator("FIRST", Direction.STOP, 0, -1, numberOfFloors, requests);
        secondElevator = new Elevator("SECOND", Direction.STOP, 0, -1, numberOfFloors, requests);
        this.requests = requests;
        this.isBreak = false;
    }

    @Override
    public void run(){
        this.isBreak = false;
        Thread firstElevatorThread = new Thread(firstElevator);
        Thread secondElevatorThread = new Thread(secondElevator);

        try{
            while(!isBreak){
                if(requests.isEmpty()){
                    Thread.sleep(500);
                    // System.out.println("requests empty");
                }else if(firstElevator.getStatus() == Direction.STOP || secondElevator.getStatus() == Direction.STOP){
                    Thread.sleep(1500);
                    Request request = requests.poll();
                    if(request != null){
                        if(firstElevator.getStatus() == Direction.STOP && Math.abs(firstElevator.getCurFloor() - request.getEndFloor()) <=
                                Math.abs(secondElevator.getCurFloor() - request.getEndFloor()) || secondElevator.getStatus() != Direction.STOP){
                            firstElevator.setEndFloor(request.getEndFloor());
                            firstElevator.setStatus(request.getDirection());

                            firstElevatorThread = new Thread(firstElevator);
                            firstElevatorThread.start();
                        }else{
                            secondElevator.setEndFloor(request.getEndFloor());
                            secondElevator.setStatus(request.getDirection());
                            secondElevatorThread = new Thread(secondElevator);
                            secondElevatorThread.start();
                        }
                    }
                } else {
                    Thread.sleep(500);
                }
            }

            firstElevatorThread.join();
            secondElevatorThread.join();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public void breakMove(){
        isBreak = true;
    }
}