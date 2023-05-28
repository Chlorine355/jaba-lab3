import lib.ElevatorSystem;
import lib.Requester;
import lib.Request;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the number of floors");
        int numberOfFloors = new Scanner(System.in).nextInt();

        Queue<Request> requests = new LinkedList<>();

        ElevatorSystem build = new ElevatorSystem(numberOfFloors, requests);
        Thread buildThread = new Thread(build);
        buildThread.start();

        System.out.println("Enter anything to stop");
        Requester requester = new Requester(requests, numberOfFloors, 10000);
        Thread reqThread = new Thread(requester);
        reqThread.start();

        new Scanner(System.in).next();

        System.out.println("Довезем последних и закончим");
        requester.breakMove();
        build.breakMove();

        try {
            buildThread.join();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}