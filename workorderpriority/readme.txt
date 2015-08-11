Project technical specification
maven project so locate the pom.xml file and do the build (mvn install or mvn clean install)
The project can also be imported in eclipse
java 1.8.0_45
jersey and jackson api for the resful service
tomcat 7.0.3

---------------project synopsis------------------------------------------------------
This application process randomly entered orders so that orders can be organized and process based on 
the time entered and priority based on class of ids.
the four classes, the classes are VIP, management override, normal and priority, 
management override is given the higest priority, followed
VIP, priority and normal. The classed assigne to each id is determined by formulae as follows:

(1) IDs that are evenly divisible by 3 are priority IDs.
(2) IDs that are evenly divisible by 5 are VIP IDs.
(3) IDs that are evenly divisible by both 3 and 5 are management overrideIDs.
(4) IDs that are not evenly divisible by 3 or 5 are normal IDs.
 
 (1) Normal IDs are given a rank equal to the number of seconds they have been in the queue.
 
 (2) Priority IDs are given a rank equal to the result of applying the following 
 formula to the number of seconds they’ve been in the queue:max(3, n log n)
 
 (3) VIP IDs are given a rank equal to the result of applying the followingformula to the 
 number of seconds they’ve been in the queue:max(4, 2n log n)
 
 (4) Management Override IDs are always ranked ahead of all other IDsand are ranked among 
 themselves according to the number of secondsthey’ve been in the queue.• The queue should 
 be sorted from highest ranked to lowest ranke


Each order is uniquely identified by an id in the range 1 to 9223372036854775807. 
which I an generating randomly using a random number
generator and a formula.

The aim of the application is to ensure that IDs rank higest are given process priority; 
so that a listing of the order ids
meet the following criterion:

IDs entered in the queue are assign the correct class
a list of IDs sorted from highest ranked to lowest.
IDs at the top of the list can be determined and processed

The object wasachive using the programming language java to develop a Restful service that if passed 
an appropriate end point will return the appropriate result.

==========================================================================================


