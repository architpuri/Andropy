Image is reaching server as a multipart post request.


At server, change python script, jython caller code and controller code to use this image accordingly to get desired result in Android Application.


Android Application is configured for 000.000.000.000
You can change it to your server ip by changing ip address at these two locations 

java/data/ApiClient.java  & resources/xml/network_security_config.xml (here add the ip without port (and https/http prefix))

To get ip4 wireless for your system (Windows) Open cmd(admin)  Type ip config.
