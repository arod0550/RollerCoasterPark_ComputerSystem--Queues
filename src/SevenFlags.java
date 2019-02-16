import java.util.*;

/**
 * Main class of the project. This project is a RollerCoaster Park Management system that Uses Queues as the data structure to support the lines of the park.
 * queue is open at both its ends. One end is always used to insert data (enqueue), go on the back of the line and the other is used to remove data (dequeue) let the first person in line in.
 * There are more features taken into account like, whether a client is regular, silver or gold and their waiting times are adjusted as follow.
 */
public class SevenFlags {
    public static void main(String[] args) {
        //Variables
        int currentTime = 0;

        //create a scanner
        Scanner input = new Scanner(System.in);

        //Welcoming message
        System.out.println("Welcome to Seven Flags!");
        System.out.println();
        boolean isValid = false;
        while (!isValid) {
            //Customer's input
            try {
                System.out.println("Please enter the number of regular customers:");
                int numOfReg = input.nextInt();
                System.out.println("Please enter the number of silver customers:");
                int numOfSilver = input.nextInt();
                System.out.println("Please enter the number of gold customers:");
                int numOfGold = input.nextInt();
                System.out.println("Please enter simulation length: ");
                int endTime = input.nextInt();
                System.out.println();

                //Rides
                System.out.println("Please enter the duration of Blue Scream of Death (minutes):");
                int durationBSOD = input.nextInt();
                System.out.println("Please enter the capacity of Blue Scream of Death: ");
                int capacityBSOD = input.nextInt();
                System.out.println("Please enter the holding queue size for Blue Scream of Death:");
                int holdSizeBSOD = input.nextInt();
                System.out.println();

                System.out.println("Please enter the duration of Kingda Knuth (minutes):");
                int durationKK = input.nextInt();
                System.out.println("Please enter the capacity of Kingda Knuth: ");
                int capacityKK = input.nextInt();
                System.out.println("Please enter the holding queue size for Kingda Knuth:");
                int holdSizeKK = input.nextInt();
                System.out.println();

                System.out.println("Please enter the duration of i386 Tower of Terror (minutes):");
                int durationToT = input.nextInt();
                System.out.println("Please enter the capacity of i386 Tower of Terror: ");
                int capacityToT = input.nextInt();
                System.out.println("Please enter the holding queue size for Blue Scream of Death:");
                int holdSizeToT = input.nextInt();
                System.out.println();

                System.out.println("Please enter the duration of GeForce (minutes):");
                int durationGF = input.nextInt();
                System.out.println("Please enter the capacity of GeForce: ");
                int capacityGF = input.nextInt();
                System.out.println("Please enter the holding queue size for GeForce:");
                int holdSizeGF = input.nextInt();
                System.out.println();


                //Create arrayList for each type of person
                List<Person> regularPeople = new ArrayList<>();
                List<Person> goldPeople = new ArrayList<>();
                List<Person> silverPeople = new ArrayList<>();


                //Add content to the list
                //Golds
                while (numOfGold != 0) {
                    for (int i = 1; i <= numOfGold; i++) {
                        Person goldTemp = new Person();
                        goldTemp.setNumber(i);
                        goldTemp.setCategory("Gold");
                        goldPeople.add(goldTemp);
                    }
                    break;
                }
                //Silver
                while (numOfSilver != 0) {
                    for (int i = 1; i <= numOfSilver; i++) {
                        Person silverTemp = new Person();
                        silverTemp.setNumber(i);
                        silverTemp.setCategory("Silver");
                        silverPeople.add(silverTemp);
                    }
                    break;
                }
                //regulars
                while (numOfReg != 0) {
                    for (int i = 1; i <= numOfReg; i++) {
                        Person regTemp = new Person();
                        regTemp.setNumber(i);
                        regTemp.setCategory("Regular");
                        regularPeople.add(regTemp);
                    }
                    break;
                }


                //Create Rides
                Ride GF = new Ride(durationGF, durationGF, holdSizeGF, capacityGF, "GF");
                Ride BSOD = new Ride(durationBSOD, durationBSOD, holdSizeBSOD, capacityBSOD, "BSOD");
                Ride KK = new Ride(durationKK, durationKK, holdSizeKK, capacityKK, "KK");
                Ride ToT = new Ride(durationToT, durationToT, holdSizeToT, capacityToT, "ToT");

                //Array of rides
                Ride[] rides = {GF, BSOD, KK, ToT};

                //Random generator
                RandomGenerator randomGen = new RandomGenerator();

                //Simulation
                for (currentTime = 0; currentTime <= endTime; currentTime++) {
                    //time left for each ride
                    //Time being less than zero
                    BSOD.setTimeLeft(durationBSOD - currentTime);
                    if (durationBSOD - currentTime < 0) {
                        int result = durationBSOD - currentTime;
                        BSOD.setTimeLeft(result % currentTime);
                    }
                    if (BSOD.getTimeLeft() == 0)
                        BSOD.setTimeLeft(BSOD.getDuration());

                    GF.setTimeLeft(durationGF - currentTime);
                    if (durationGF - currentTime < 0) {
                        int result = durationGF - currentTime;
                        GF.setTimeLeft(result % currentTime);
                    }
                    if (GF.getTimeLeft() == 0)
                        GF.setTimeLeft(GF.getDuration());

                    ToT.setTimeLeft(durationToT - currentTime);
                    if (durationToT - currentTime < 0) {
                        int result = durationToT - currentTime;
                        ToT.setTimeLeft(result % currentTime);
                    }
                    if (ToT.getTimeLeft() == 0)
                        ToT.setTimeLeft(ToT.getDuration());

                    KK.setTimeLeft(durationKK - currentTime);
                    if (durationKK - currentTime < 0) {
                        int result = durationKK - currentTime;
                        KK.setTimeLeft(result % currentTime);
                    }
                    if (KK.getTimeLeft() == 0)
                        KK.setTimeLeft(KK.getDuration());

                    //adding the respective object the times its supposed to be added to a ride.
                    if (currentTime == 0) {
                        for (int i = 0; i < goldPeople.size(); i++) {
                            //gold into ride (3 times)
                            Ride randomRide = randomGen.selectRide(rides);
                            if (randomRide.getPeopleOnRide().size() != randomRide.getMaxPeopleOnRide()) {
                                randomRide.getPeopleOnRide().add(goldPeople.get(i));
                                goldPeople.get(i).setStatus(Status.OnRide);
                                goldPeople.get(i).rideCounterPlus();
                                randomRide.AddtoNumRiders(1);
                            } else if (randomRide.getHoldingQueue().size() != randomRide.getMaxSizeHold()) {
                                randomRide.getHoldingQueue().enqueue(goldPeople.get(i));
                                goldPeople.get(i).setStatus(Status.Holding);
                            } else {
                                randomRide.getVirtualLine().enqueue(goldPeople.get(i));
                                goldPeople.get(i).setStatus(Status.Available);
                            }
                            List<Ride> templines = new ArrayList<>();
                            templines.add(randomRide);
                            goldPeople.get(i).setLines(templines);

                            randomRide = randomGen.selectRide(rides);
                            if (goldPeople.get(i).getStatus() == Status.OnRide || goldPeople.get(i).getStatus() == Status.Holding) {
                                randomRide.getVirtualLine().enqueue(goldPeople.get(i));
                            } else {
                                if (randomRide.getPeopleOnRide().size() != randomRide.getMaxPeopleOnRide()) {
                                    randomRide.getPeopleOnRide().add(goldPeople.get(i));
                                    goldPeople.get(i).setStatus(Status.OnRide);
                                    goldPeople.get(i).rideCounterPlus();
                                    randomRide.AddtoNumRiders(1);
                                } else if (randomRide.getHoldingQueue().size() != randomRide.getMaxSizeHold()) {
                                    randomRide.getHoldingQueue().enqueue(goldPeople.get(i));
                                    goldPeople.get(i).setStatus(Status.Holding);
                                } else {
                                    randomRide.getVirtualLine().enqueue(goldPeople.get(i));
                                    goldPeople.get(i).setStatus(Status.Available);
                                }
                            }

                            templines.add(randomRide);
                            goldPeople.get(i).setLines(templines);

                            randomRide = randomGen.selectRide(rides);
                            if (goldPeople.get(i).getStatus() == Status.OnRide || goldPeople.get(i).getStatus() == Status.Holding) {
                                randomRide.getVirtualLine().enqueue(goldPeople.get(i));
                            } else {
                                if (randomRide.getPeopleOnRide().size() != randomRide.getMaxPeopleOnRide()) {
                                    randomRide.getPeopleOnRide().add(goldPeople.get(i));
                                    goldPeople.get(i).setStatus(Status.OnRide);
                                    goldPeople.get(i).rideCounterPlus();
                                    randomRide.AddtoNumRiders(1);
                                } else if (randomRide.getHoldingQueue().size() != randomRide.getMaxSizeHold()) {
                                    randomRide.getHoldingQueue().enqueue(goldPeople.get(i));
                                    goldPeople.get(i).setStatus(Status.Holding);
                                } else {
                                    randomRide.getVirtualLine().enqueue(goldPeople.get(i));
                                    goldPeople.get(i).setStatus(Status.Available);
                                }
                            }

                            templines.add(randomRide);
                            goldPeople.get(i).setLines(templines);
                        }

                        for (int i = 0; i < silverPeople.size(); i++) {
                            //silver into a ride (2 times)
                            Ride randomRide = randomGen.selectRide(rides);
                            if (randomRide.getPeopleOnRide().size() != randomRide.getMaxPeopleOnRide()) {
                                randomRide.getPeopleOnRide().add(silverPeople.get(i));
                                silverPeople.get(i).setStatus(Status.OnRide);
                                silverPeople.get(i).rideCounterPlus();
                                randomRide.AddtoNumRiders(1);
                            } else if (randomRide.getHoldingQueue().size() != randomRide.getMaxSizeHold()) {
                                randomRide.getHoldingQueue().enqueue(silverPeople.get(i));
                                silverPeople.get(i).setStatus(Status.Holding);
                            } else {
                                randomRide.getVirtualLine().enqueue(silverPeople.get(i));
                                silverPeople.get(i).setStatus(Status.Available);
                            }

                            List<Ride> templines2 = new ArrayList<>();
                            templines2.add(randomRide);
                            silverPeople.get(i).setLines(templines2);

                            randomRide = randomGen.selectRide(rides);
                            if (silverPeople.get(i).getStatus() == Status.OnRide || silverPeople.get(i).getStatus() == Status.Holding) {
                                randomRide.getVirtualLine().enqueue(silverPeople.get(i));
                            } else {
                                if (randomRide.getPeopleOnRide().size() != randomRide.getMaxPeopleOnRide()) {
                                    randomRide.getPeopleOnRide().add(silverPeople.get(i));
                                    silverPeople.get(i).setStatus(Status.OnRide);
                                    silverPeople.get(i).rideCounterPlus();
                                    randomRide.AddtoNumRiders(1);
                                } else if (randomRide.getHoldingQueue().size() != randomRide.getMaxSizeHold()) {
                                    randomRide.getHoldingQueue().enqueue(silverPeople.get(i));
                                    silverPeople.get(i).setStatus(Status.Holding);
                                } else {
                                    randomRide.getVirtualLine().enqueue(silverPeople.get(i));
                                    silverPeople.get(i).setStatus(Status.Available);
                                }
                            }
                            templines2.add(randomRide);
                            silverPeople.get(i).setLines(templines2);
                        }
                        //regular into a ride (1 time)
                        for (int i = 0; i < regularPeople.size(); i++) {
                            Ride randomRide = randomGen.selectRide(rides);
                            if (randomRide.getPeopleOnRide().size() != randomRide.getMaxPeopleOnRide()) {
                                randomRide.getPeopleOnRide().add(regularPeople.get(i));
                                regularPeople.get(i).setStatus(Status.OnRide);
                                regularPeople.get(i).rideCounterPlus();
                                randomRide.AddtoNumRiders(1);
                            } else if (randomRide.getHoldingQueue().size() != randomRide.getMaxSizeHold()) {
                                randomRide.getHoldingQueue().enqueue(regularPeople.get(i));
                                regularPeople.get(i).setStatus(Status.Holding);
                            } else {
                                randomRide.getVirtualLine().enqueue(regularPeople.get(i));
                                regularPeople.get(i).setStatus(Status.Available);
                            }
                            List<Ride> templines3 = new ArrayList<>();
                            templines3.add(randomRide);
                            regularPeople.get(i).setLines(templines3);
                        }

                    } else {
                        //Ride GF--------------------------------------------------------------------------------------------------------------------------------------
                        if (currentTime % durationGF == 0) {
                            VirtualLine tempLine = new VirtualLine();
                            Person tempPerson;

                            //from Onride to temp
                            while (!GF.getPeopleOnRide().isEmpty()) {
                                for (int i = 0; i < GF.getPeopleOnRide().size(); i++) {
                                    GF.getPeopleOnRide().get(i).setStatus(Status.Available);
                                    tempLine.enqueue(GF.getPeopleOnRide().remove(i));
                                }
                            }
                            //handle people that got off and assign them a new ride
                            while (!tempLine.isEmpty()) {
                                for (int i = 0; i < tempLine.size(); i++) {
                                    Person p = (Person) tempLine.get(i);
                                    Ride randomRide = randomGen.selectRide(rides);
                                    if (randomRide.getPeopleOnRide().size() != randomRide.getMaxPeopleOnRide()) {
                                        randomRide.getPeopleOnRide().add(p);
                                        p.setStatus(Status.OnRide);
                                        p.rideCounterPlus();
                                        randomRide.AddtoNumRiders(1);
                                        List<Ride> templines = new ArrayList<>();
                                        templines.add(randomRide);
                                        p.setLines(templines);
                                    } else if (randomRide.getHoldingQueue().size() != randomRide.getMaxSizeHold()) {
                                        randomRide.getHoldingQueue().enqueue(p);
                                        p.setStatus(Status.Holding);
                                    } else {
                                        randomRide.getVirtualLine().enqueue(p);
                                        p.setStatus(Status.Available);
                                    }
                                }
                                tempLine.clear();
                            }
                            //from hold to ride
                            while (GF.getPeopleOnRide().size() < GF.getMaxPeopleOnRide() && !GF.noneAvailableInHold() && !GF.getHoldingQueue().isEmpty()) {
                                for (int i = 0; i < GF.getHoldingQueue().size(); i++) {
                                    tempPerson = (Person) GF.getHoldingQueue().get(i);
                                    while (GF.getPeopleOnRide().size() < GF.getMaxPeopleOnRide()) {
                                        if (tempPerson.getStatus() == Status.Available) {
                                            tempPerson.setStatus(Status.OnRide);
                                            tempPerson.rideCounterPlus();
                                            GF.peopleOnRide.add(tempPerson);
                                            GF.getHoldingQueue().dequeue();
                                        } else {
                                            GF.getHoldingQueue().enqueue(tempPerson);
                                            GF.getHoldingQueue().dequeue();
                                        }
                                    }
                                }
                                GF.AddtoNumRiders(GF.peopleOnRide.size());
                            }
                            //from virtual to ride if none available in the middle
                            while (GF.noneAvailableInHold() && !GF.getVirtualLine().isEmpty() && GF.getPeopleOnRide().size() < GF.getMaxPeopleOnRide()) {
                                while (!GF.noneAvailableInVirtual() || GF.getPeopleOnRide().size() != GF.getMaxPeopleOnRide()) {
                                    tempPerson = (Person) GF.getVirtualLine().get(0);
                                    if (tempPerson.getStatus() == Status.Available) {
                                        tempPerson.setStatus(Status.OnRide);
                                        tempPerson.rideCounterPlus();
                                        GF.peopleOnRide.add(tempPerson);
                                        GF.getVirtualLine().dequeue();
                                    } else {
                                        GF.getVirtualLine().enqueue(tempPerson);
                                        GF.getVirtualLine().dequeue();
                                    }
                                }
                                GF.AddtoNumRiders(GF.peopleOnRide.size());
                            }
                            //from virtual to holding
                            while (!GF.noneAvailableInVirtual() && GF.getHoldingQueue().size() != GF.getMaxSizeHold()) {
                                for (int i = 0; i < GF.getVirtualLine().size(); i++) {
                                    tempPerson = (Person) GF.getVirtualLine().get(i);
                                    int availableHoldSpace = GF.getMaxSizeHold() - GF.getHoldingQueue().size();
                                    while (availableHoldSpace != 0) {
                                        if (tempPerson.getStatus() == Status.Available) {
                                            tempPerson.setStatus(Status.Holding);
                                            GF.getHoldingQueue().enqueue(tempPerson);
                                            GF.getVirtualLine().dequeue();
                                        } else {
                                            GF.getVirtualLine().enqueue(tempPerson);
                                            GF.getVirtualLine().dequeue();
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        //Ride BSOD------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                        if (currentTime % durationBSOD == 0) {
                            VirtualLine tempLine = new VirtualLine();
                            Person tempPerson;

                            //from Onride to temp
                            while (!BSOD.getPeopleOnRide().isEmpty()) {
                                for (int i = 0; i < BSOD.getPeopleOnRide().size(); i++) {
                                    BSOD.getPeopleOnRide().get(i).setStatus(Status.Available);
                                    tempLine.enqueue(BSOD.getPeopleOnRide().remove(i));
                                }
                            }
                            //handle people that got off and assign them a new ride
                            while (!tempLine.isEmpty()) {
                                for (int i = 0; i < tempLine.size(); i++) {
                                    Person p = (Person) tempLine.get(i);
                                    Ride randomRide = randomGen.selectRide(rides);
                                    if (randomRide.getPeopleOnRide().size() != randomRide.getMaxPeopleOnRide()) {
                                        randomRide.getPeopleOnRide().add(p);
                                        p.setStatus(Status.OnRide);
                                        p.rideCounterPlus();
                                        randomRide.AddtoNumRiders(1);
                                    } else if (randomRide.getHoldingQueue().size() != randomRide.getMaxSizeHold()) {
                                        randomRide.getHoldingQueue().enqueue(p);
                                        p.setStatus(Status.Holding);
                                    } else {
                                        randomRide.getVirtualLine().enqueue(p);
                                        p.setStatus(Status.Available);
                                    }

                                }
                                tempLine.clear();
                            }
                            //from hold to ride
                            while (BSOD.getPeopleOnRide().size() < BSOD.getMaxPeopleOnRide() && !BSOD.noneAvailableInHold() && !BSOD.getHoldingQueue().isEmpty()) {
                                for (int i = 0; i < BSOD.getHoldingQueue().size(); i++) {
                                    tempPerson = (Person) BSOD.getHoldingQueue().get(i);
                                    while (BSOD.getPeopleOnRide().size() < BSOD.getMaxPeopleOnRide()) {
                                        if (tempPerson.getStatus() == Status.Available) {
                                            tempPerson.setStatus(Status.OnRide);
                                            tempPerson.rideCounterPlus();
                                            BSOD.peopleOnRide.add(tempPerson);
                                            BSOD.getHoldingQueue().dequeue();
                                        } else {
                                            BSOD.getHoldingQueue().enqueue(tempPerson);
                                            BSOD.getHoldingQueue().dequeue();
                                        }
                                    }
                                }
                                BSOD.AddtoNumRiders(BSOD.peopleOnRide.size());
                            }
                            //from virtual to ride if none available in the middle
                            while (BSOD.noneAvailableInHold() && !BSOD.getVirtualLine().isEmpty() && BSOD.getPeopleOnRide().size() < BSOD.getMaxPeopleOnRide()) {
                                while (!BSOD.noneAvailableInVirtual() || BSOD.getPeopleOnRide().size() != BSOD.getMaxPeopleOnRide()) {
                                    tempPerson = (Person) BSOD.getVirtualLine().get(0);
                                    if (tempPerson.getStatus() == Status.Available) {
                                        tempPerson.setStatus(Status.OnRide);
                                        tempPerson.rideCounterPlus();
                                        BSOD.peopleOnRide.add(tempPerson);
                                        BSOD.getVirtualLine().dequeue();
                                    } else {
                                        BSOD.getVirtualLine().enqueue(tempPerson);
                                        BSOD.getVirtualLine().dequeue();
                                    }
                                }
                                BSOD.AddtoNumRiders(BSOD.peopleOnRide.size());
                            }
                            //from virtual to holding
                            while (!BSOD.noneAvailableInVirtual() && BSOD.getHoldingQueue().size() != BSOD.getMaxSizeHold()) {
                                for (int i = 0; i < BSOD.getVirtualLine().size(); i++) {
                                    tempPerson = (Person) BSOD.getVirtualLine().get(i);
                                    int availableHoldSpace = BSOD.getMaxSizeHold() - BSOD.getHoldingQueue().size();
                                    while (availableHoldSpace != 0) {
                                        if (tempPerson.getStatus() == Status.Available) {
                                            tempPerson.setStatus(Status.Holding);
                                            BSOD.getHoldingQueue().enqueue(tempPerson);
                                            BSOD.getVirtualLine().dequeue();
                                        } else {
                                            BSOD.getVirtualLine().enqueue(tempPerson);
                                            BSOD.getVirtualLine().dequeue();
                                        }
                                        break;
                                    }
                                }
                            }
                        }

                        //Ride KK-------------------------------------------------------------------------------------------------------------------------------------------------
                        if (currentTime % durationKK == 0) {
                            VirtualLine tempLine = new VirtualLine();
                            Person tempPerson;
                            //from Onride to temp
                            while (!KK.getPeopleOnRide().isEmpty()) {
                                for (int i = 0; i < KK.getPeopleOnRide().size(); i++) {
                                    KK.getPeopleOnRide().get(i).setStatus(Status.Available);
                                    tempLine.enqueue(KK.getPeopleOnRide().remove(i));
                                }
                            }
                            //handle people that got off and assign them a new ride
                            while (!tempLine.isEmpty()) {
                                for (int i = 0; i < tempLine.size(); i++) {
                                    Person p = (Person) tempLine.get(i);
                                    Ride randomRide = randomGen.selectRide(rides);
                                    if (randomRide.getPeopleOnRide().size() != randomRide.getMaxPeopleOnRide()) {
                                        randomRide.getPeopleOnRide().add(p);
                                        p.setStatus(Status.OnRide);
                                        p.rideCounterPlus();
                                        randomRide.AddtoNumRiders(1);
                                    } else if (randomRide.getHoldingQueue().size() != randomRide.getMaxSizeHold()) {
                                        randomRide.getHoldingQueue().enqueue(p);
                                        p.setStatus(Status.Holding);
                                    } else {
                                        randomRide.getVirtualLine().enqueue(p);
                                        p.setStatus(Status.Available);
                                    }
                                }
                                tempLine.clear();
                            }
                            //from hold to ride
                            while (KK.getPeopleOnRide().size() < KK.getMaxPeopleOnRide() && !KK.noneAvailableInHold() && !KK.getHoldingQueue().isEmpty()) {
                                for (int i = 0; i < KK.getHoldingQueue().size(); i++) {
                                    tempPerson = (Person) KK.getHoldingQueue().get(i);
                                    while (KK.getPeopleOnRide().size() < KK.getMaxPeopleOnRide()) {
                                        if (tempPerson.getStatus() == Status.Available) {
                                            tempPerson.setStatus(Status.OnRide);
                                            tempPerson.rideCounterPlus();
                                            KK.peopleOnRide.add(tempPerson);
                                            KK.getHoldingQueue().dequeue();
                                        } else {
                                            KK.getHoldingQueue().enqueue(tempPerson);
                                            KK.getHoldingQueue().dequeue();
                                        }
                                    }
                                }
                                KK.AddtoNumRiders(KK.peopleOnRide.size());
                            }
                            //from virtual to ride if none available in the middle
                            while (KK.noneAvailableInHold() && !KK.getVirtualLine().isEmpty() && KK.getPeopleOnRide().size() < KK.getMaxPeopleOnRide()) {
                                while (!KK.noneAvailableInVirtual() || KK.getPeopleOnRide().size() != KK.getMaxPeopleOnRide()) {
                                    tempPerson = (Person) KK.getVirtualLine().get(0);
                                    if (tempPerson.getStatus() == Status.Available) {
                                        tempPerson.setStatus(Status.OnRide);
                                        tempPerson.rideCounterPlus();
                                        KK.peopleOnRide.add(tempPerson);
                                        KK.getVirtualLine().dequeue();
                                    } else {
                                        KK.getVirtualLine().enqueue(tempPerson);
                                        KK.getVirtualLine().dequeue();
                                    }
                                }
                                KK.AddtoNumRiders(KK.peopleOnRide.size());
                            }
                            //from virtual to holding
                            while (!KK.noneAvailableInVirtual() && KK.getHoldingQueue().size() != KK.getMaxSizeHold()) {
                                for (int i = 0; i < KK.getVirtualLine().size(); i++) {
                                    tempPerson = (Person) KK.getVirtualLine().get(i);
                                    int availableHoldSpace = KK.getMaxSizeHold() - KK.getHoldingQueue().size();
                                    while (availableHoldSpace != 0) {
                                        if (tempPerson.getStatus() == Status.Available) {
                                            tempPerson.setStatus(Status.Holding);
                                            KK.getHoldingQueue().enqueue(tempPerson);
                                            KK.getVirtualLine().dequeue();
                                        } else {
                                            KK.getVirtualLine().enqueue(tempPerson);
                                            KK.getVirtualLine().dequeue();
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        //Ride ToT--------------------------------------------------------------------------------------------------------------------------------------------------------
                        if (currentTime % durationToT == 0) {
                            VirtualLine tempLine = new VirtualLine();
                            Person tempPerson;
                            //from Onride to temp
                            while (!ToT.getPeopleOnRide().isEmpty()) {
                                for (int i = 0; i < ToT.getPeopleOnRide().size(); i++) {
                                    ToT.getPeopleOnRide().get(i).setStatus(Status.Available);
                                    tempLine.enqueue(ToT.getPeopleOnRide().remove(i));
                                }
                            }
                            //handle people that got off and assign them a new ride
                            while (!tempLine.isEmpty()) {
                                for (int i = 0; i < tempLine.size(); i++) {
                                    Person p = (Person) tempLine.get(i);
                                    Ride randomRide = randomGen.selectRide(rides);
                                    if (randomRide.getPeopleOnRide().size() != randomRide.getMaxPeopleOnRide()) {
                                        randomRide.getPeopleOnRide().add(p);
                                        p.setStatus(Status.OnRide);
                                        p.rideCounterPlus();
                                        randomRide.AddtoNumRiders(1);
                                    } else if (randomRide.getHoldingQueue().size() != randomRide.getMaxSizeHold()) {
                                        randomRide.getHoldingQueue().enqueue(p);
                                        p.setStatus(Status.Holding);
                                    } else {
                                        randomRide.getVirtualLine().enqueue(p);
                                        p.setStatus(Status.Available);
                                    }
                                }
                                tempLine.clear();
                            }
                            //from hold to ride
                            while (ToT.getPeopleOnRide().size() < ToT.getMaxPeopleOnRide() && !ToT.noneAvailableInHold() && !ToT.getHoldingQueue().isEmpty()) {
                                for (int i = 0; i < ToT.getHoldingQueue().size(); i++) {
                                    tempPerson = (Person) ToT.getHoldingQueue().get(i);
                                    while (ToT.getPeopleOnRide().size() < ToT.getMaxPeopleOnRide()) {
                                        if (tempPerson.getStatus() == Status.Available) {
                                            tempPerson.setStatus(Status.OnRide);
                                            tempPerson.rideCounterPlus();
                                            ToT.peopleOnRide.add(tempPerson);
                                            ToT.getHoldingQueue().dequeue();
                                        } else {
                                            ToT.getHoldingQueue().enqueue(tempPerson);
                                            ToT.getHoldingQueue().dequeue();
                                        }
                                    }
                                }
                                ToT.AddtoNumRiders(ToT.peopleOnRide.size());
                            }
                            //from virtual to ride if none available in the middle
                            while (ToT.noneAvailableInHold() && !ToT.getVirtualLine().isEmpty() && ToT.getPeopleOnRide().size() < ToT.getMaxPeopleOnRide()) {
                                while (!ToT.noneAvailableInVirtual() || ToT.getPeopleOnRide().size() != ToT.getMaxPeopleOnRide()) {
                                    tempPerson = (Person) ToT.getVirtualLine().get(0);
                                    if (tempPerson.getStatus() == Status.Available) {
                                        tempPerson.setStatus(Status.OnRide);
                                        tempPerson.rideCounterPlus();
                                        ToT.peopleOnRide.add(tempPerson);
                                        ToT.getVirtualLine().dequeue();
                                    } else {
                                        ToT.getVirtualLine().enqueue(tempPerson);
                                        ToT.getVirtualLine().dequeue();
                                    }
                                }
                                ToT.AddtoNumRiders(ToT.peopleOnRide.size());
                            }
                            //from virtual to holding
                            while (!ToT.noneAvailableInVirtual() && ToT.getHoldingQueue().size() != ToT.getMaxSizeHold()) {
                                for (int i = 0; i < ToT.getVirtualLine().size(); i++) {
                                    tempPerson = (Person) ToT.getVirtualLine().get(i);
                                    int availableHoldSpace = ToT.getMaxSizeHold() - ToT.getHoldingQueue().size();
                                    while (availableHoldSpace != 0) {
                                        if (tempPerson.getStatus() == Status.Available) {
                                            tempPerson.setStatus(Status.Holding);
                                            ToT.getHoldingQueue().enqueue(tempPerson);
                                            ToT.getVirtualLine().dequeue();
                                        } else {
                                            ToT.getVirtualLine().enqueue(tempPerson);
                                            ToT.getVirtualLine().dequeue();
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    //printing
                    System.out.println("------------------------------------------------------------------------------------------");
                    System.out.println("At Time " + currentTime + ":");
                    System.out.println();
                    System.out.println("Blue Scream of Death - Time remaining: " + BSOD.getTimeLeft());
                    System.out.println("On Ride: " + BSOD.toStringList());
                    System.out.println("Holding Queue: " + BSOD.toStringHold());
                    System.out.println("Virtual Queue: " + BSOD.toStringVirtual());
                    System.out.println("----------------");
                    System.out.println("Kingda Knuth - Time remaining: " + KK.getTimeLeft());
                    System.out.println("On Ride: " + KK.toStringList());
                    System.out.println("Holding Queue: " + KK.toStringHold());
                    System.out.println("Virtual Queue: " + KK.toStringVirtual());
                    System.out.println("----------------");
                    System.out.println("i386 Tower of Terror - Time remaining: " + ToT.getTimeLeft());
                    System.out.println("On Ride: " + ToT.toStringList());
                    System.out.println("Holding Queue: " + ToT.toStringHold());
                    System.out.println("Virtual Queue: " + ToT.toStringVirtual());
                    System.out.println("----------------");
                    System.out.println("GeForce - Time remaining: " + GF.getTimeLeft());
                    System.out.println("On Ride: " + GF.toStringList());
                    System.out.println("Holding Queue: " + GF.toStringHold());
                    System.out.println("Virtual Queue: " + GF.toStringVirtual());
                    System.out.println("----------------");

                    System.out.println("| Regular Customers: |");
                    System.out.println();
                    System.out.println("Num |Line| Status");
                    System.out.println("-----------------");
                    for (int i = 0; i < numOfReg; i++) {
                        System.out.println(i + 1 + ".   " + regularPeople.get(i).toStringRides() + "" + regularPeople.get(i).getStatus());
                    }

                    System.out.println();
                    System.out.println();

                    System.out.println("| Silver Customers: |");
                    System.out.println();
                    System.out.println("Num |Line 1| |Line 2| Status");
                    System.out.println("----------------------------");
                    for (int i = 0; i < numOfSilver; i++) {
                        System.out.println(i + 1 + ".   " + silverPeople.get(i).toStringRides() + "" + silverPeople.get(i).getStatus());
                    }
                    System.out.println();
                    System.out.println();

                    System.out.println("| Gold Customers: |");
                    System.out.println();
                    System.out.println("Num |Line 1| |Line 2| |Line 3|  Status");
                    System.out.println("----------------------------------------");
                    for (int i = 0; i < numOfGold; i++) {
                        System.out.println(i + 1 + ".   " + goldPeople.get(i).toStringRides() + "" + goldPeople.get(i).getStatus());
                    }
                    System.out.println();


                    //Calculations for the stats
                    double silverAvg = 0;
                    double regAvg = 0;
                    double goldAvg = 0;

                    int sum = 0;
                    for (int i = 0; i < goldPeople.size(); i++) {
                        sum += goldPeople.get(i).getRideCounter();
                        goldAvg = sum / goldPeople.size();
                    }

                    int sum2 = 0;
                    for (int i = 0; i < silverPeople.size(); i++) {
                        sum2 += silverPeople.get(i).getRideCounter();
                        silverAvg = sum / silverPeople.size();
                    }
                    int sum3 = 0;
                    for (int i = 0; i < regularPeople.size(); i++) {
                        sum3 += regularPeople.get(i).getRideCounter();
                        regAvg = sum / regularPeople.size();
                    }
                    //End of simulation... print stats
                    if (currentTime == endTime) {
                        //Stats---------------------------------------------------------------------------------------------------------------------------------------------------------
                        System.out.println(".................end of the simulation.................");
                        System.out.println("Thank you for using SevenFlags simulation!!!");
                        System.out.println("On average, Gold customers have taken " + goldAvg + " rides");
                        System.out.println("On average, Silver customers have taken " + silverAvg + " rides");
                        System.out.println("On average, regular customers have taken " + regAvg + " rides");
                        System.out.println();
                        System.out.println("BSOD has completed rides for " + BSOD.getNumberOfRiders() + " people.");
                        System.out.println("KK has completed rides for " + KK.getNumberOfRiders() + " people.");
                        System.out.println("ToT has completed rides for " + ToT.getNumberOfRiders() + " people.");
                        System.out.println("GF has completed rides for " + GF.getNumberOfRiders() + " people.");

                        System.out.println();
                    }
                }
                isValid = true;
            } catch (InputMismatchException ex) {
                System.out.println("illegal argument. Try again From the beginning!!");
                //Flush the input object of the error it caught
                input.next();

            } catch (IllegalArgumentException ex) {
                System.out.println("illegal argument. Try again From the beginning!!!!");
                input.next();

            } catch (FullQueueException ex) {
                System.out.println("Full exception");
                input.next();

            } catch (EmptyQueueException ex) {
                System.out.println("Empty exception");
                input.next();
            }
        }
    }
}
