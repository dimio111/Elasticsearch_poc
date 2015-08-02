# README #


### Run the Customer API ###
java -jar target/elasticsearch-0.0.1-SNAPSHOT.jar server dropwizard.yml

java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar target//elasticsearch-0.0.1-SNAPSHOT.jar server dropwizard.yml
