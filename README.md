###Steps to run parking application.

Step 1 : Unzip attached parking.zip file
step 2 : Import unzip file in IDE like eclipse, IntelliJ
step 3 : Include JDK 8 version
step 4 : Goto ParkingClient.java file and Run as a java project

####Implementation approach 

Code has been written by following solid principle
Code can be easily extendable in case new vehicle type will be introduced for parking
Used Completable Future interface to submit task asynchronously
Added custom Exception to handle parking full and empty scenario
Used BlockingQueue data structure to store Vehicle in multithreaded environment
