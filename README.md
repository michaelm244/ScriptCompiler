ScriptComiler
=============

Compile and run scripts on a web server.

JSONObject returns...

    result: if the function returns some value, this is where that value will be
    
    success: boolean true or false if the method call succeeds
    
    console: any data printed to console
    
    time: the time the method call took to run
    
    error: if there was an error, this is where it will appear
    

NOTE: JavaScript functions must be in the following form to get run by the ScriptEngine:

    (function xyz() {...}());
