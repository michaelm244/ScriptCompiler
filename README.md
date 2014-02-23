shit_compiler
=============

name speaks for itself

Steps for running the Java server:
1) Install a Apache Tomcat v7.0 server
    - The code works on windows and mac
    - You can install runtimes in eclipse
    - Download link http://tomcat.apache.org/download-70.cgi
2) Move the Script Compiler.war file to the /var/lib/tomcat7/webapps directory
    - If you are running in eclipse, import project from existing code -> Script Complier -> Run on Server
3) Make sure tomcat is running on port 8080
    - If in eclipse, go to Window -> Show View -> Servers -> Double click your tomcat7 server -> Change configuration to tomcat default
4) Do an ifconfig/ipconfig to determine host address
5) Make sure devices are on the same network as the server
6) get  host:port/LaunchProject/manager/test (returns "Test Successful!")
7) post host:port/LaunchProject/manager/script
    - data = image base64 data
