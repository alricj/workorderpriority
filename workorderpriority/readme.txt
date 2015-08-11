
Project technical specification
the project name is workorderpriority.it is a
maven web application project developed using
 
java 1.8.0_51
jersey and jackson api for the resful service
tomcat 7.0.63

build instruction

pull the project from the repo to a local folder and import it into eclipse, I have provided a word document
with detailed instructions for doing this. 
To summarize here:
open eclipse in a workspace of your choosing
right click in project explorer
open the Maven folder
select existing maven project
click next
on the next dialog screen get browser to the folder where the project was downloaded
select the workorderpriority folder
click finish
this should import the project into eclipse.

server setup:
add your server, in my case it was tomcat 7.0.63, I have also run it on jboss
select add/remove project
you will see one project 'workorderpriority', add it and start the server

the project can also be build from the command line using maven
locate the pom.xml file and do the build (mvn install or mvn clean install)
I would suggest that if you do this build and exclued the test.

Testing instuctions:
the end points are located in the properties file endpoint.properties
there are six Junit test classes provided as follows:
1. AddIDToQueueTest.java
2. GetListOfIdsTest.java
3. GetMeanWaitTimeTest.java
4. GetAndRemoveTopIdTest.java

5. GetIDPositionInQueueTest.java
6. RemoveAnIdTest.java

To test effectively start with test class 1, to see how the data was added, then you can do 2,3,4 in any order. 
an ID would have to be provided to test 5 and 6. below are some test results


Test results: 10 entered IDs
{"id":273831162584939871,"date":"08/10/2015 19:47:21","timeMilisec":1439250439632,"woclass":"PRIORITY","woclassInt":3,"rank":0}
{"id":6361356437831732063,"date":"08/10/2015 19:47:22","timeMilisec":1439250442063,"woclass":"NORMAL","woclassInt":4,"rank":0}
{"id":5930245730442104443,"date":"08/10/2015 19:47:22","timeMilisec":1439250442252,"woclass":"NORMAL","woclassInt":4,"rank":0}
{"id":5930421593928788593,"date":"08/10/2015 19:47:22","timeMilisec":1439250442432,"woclass":"NORMAL","woclassInt":4,"rank":0}
{"id":3340636749056724661,"date":"08/10/2015 19:47:22","timeMilisec":1439250442610,"woclass":"NORMAL","woclassInt":4,"rank":0}
{"id":4921895403773878955,"date":"08/10/2015 19:47:22","timeMilisec":1439250442774,"woclass":"VIP","woclassInt":2,"rank":0}
{"id":4493664301894573064,"date":"08/10/2015 19:47:23","timeMilisec":1439250442934,"woclass":"NORMAL","woclassInt":4,"rank":0}
{"id":5894534122184962489,"date":"08/10/2015 19:47:23","timeMilisec":1439250443087,"woclass":"NORMAL","woclassInt":4,"rank":0}
{"id":8817539611304147773,"date":"08/10/2015 19:47:23","timeMilisec":1439250443246,"woclass":"NORMAL","woclassInt":4,"rank":0}
{"id":7628976155930189447,"date":"08/10/2015 19:47:23","timeMilisec":1439250443404,"woclass":"NORMAL","woclassInt":4,"rank":0}

The average wait time for given time : 1439250975948 is 533

-----------Start SORTED List--------------
ID: 4921895403773878955
Date: 08/10/2015 19:47:22
Time: 1439250442774
Rank: 12870
Class rank id: 2
Class: VIP
________________________________________

ID: 273831162584939871
Date: 08/10/2015 19:47:21
Time: 1439250439632
Rank: 33939
Class rank id: 3
Class: PRIORITY
________________________________________

ID: 6361356437831732063
Date: 08/10/2015 19:47:22
Time: 1439250442063
Rank: 1651
Class rank id: 4
Class: NORMAL
________________________________________

ID: 5930245730442104443
Date: 08/10/2015 19:47:22
Time: 1439250442252
Rank: 1462
Class rank id: 4
Class: NORMAL
________________________________________

ID: 5930421593928788593
Date: 08/10/2015 19:47:22
Time: 1439250442432
Rank: 1282
Class rank id: 4
Class: NORMAL
________________________________________

ID: 3340636749056724661
Date: 08/10/2015 19:47:22
Time: 1439250442610
Rank: 1104
Class rank id: 4
Class: NORMAL
________________________________________

ID: 4493664301894573064
Date: 08/10/2015 19:47:23
Time: 1439250442934
Rank: 780
Class rank id: 4
Class: NORMAL
________________________________________

ID: 5894534122184962489
Date: 08/10/2015 19:47:23
Time: 1439250443087
Rank: 627
Class rank id: 4
Class: NORMAL
________________________________________

ID: 8817539611304147773
Date: 08/10/2015 19:47:23
Time: 1439250443246
Rank: 468
Class rank id: 4
Class: NORMAL
________________________________________

ID: 7628976155930189447
Date: 08/10/2015 19:47:23
Time: 1439250443404
Rank: 310
Class rank id: 4
Class: NORMAL
________________________________________

-----------End SORTED List--------------




