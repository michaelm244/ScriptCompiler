ScriptComiler
=============

Compile and run scripts on a web server.

JSONObject returns...\n
    - result: if the function returns some value, this is where that value will be\n
    - success: boolean true or false if the method call succeeds\n
    - console: any data printed to console\n
    - time: the time the method call took to run\n
    - error: if there was an error, this is where it will appear\n
    
Steps for running the Java server:\n
1) Install a Apache Tomcat v7.0 server\n
    - The code works on windows and mac\n
    - You can install runtimes in eclipse\n
    - Download link http://tomcat.apache.org/download-70.cgi\n
2) Move the Script Compiler.war file to the /var/lib/tomcat7/webapps directory\n
    - If you are running in eclipse, import project from existing code -> Script Complier -> Run on Server\n
3) Make sure tomcat is running on port 8080\n
    - If in eclipse, go to Window -> Show View -> Servers -> Double click your tomcat7 server -> Change configuration to tomcat default\n
4) Do an ifconfig/ipconfig to determine host address\n
5) Make sure devices are on the same network as the server\n
6) get  host:port/LaunchProject/manager/test (returns "Test Successful!")\n
7) post host:port/LaunchProject/manager/script\n
    - data = image base64 data\n
    
Tips for running JavaScript on ScriptEngine\n
1) Functions must be in this form to be run\n
    -  (function xyz() {...}());\n
