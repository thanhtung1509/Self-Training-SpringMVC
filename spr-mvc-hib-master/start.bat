cls

set CATALINA_HOME=D:\tools\tomcat\apache-tomcat-7.0.53
set MAVEN_OPTS=-Xmx1024M -Xss128k -XX:MaxPermSize=512M -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000
set JPDA_OPTS=-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n
set JAVA_OPTS=-Xms512m -Xmx1024m -XX:MaxPermSize=256m -Dfile.encoding=UTF8 -Duser.timezone=GMT

:check_for_rebuild
if ""%1"" == ""/r"" goto rebuild
if ""%2"" == ""/r"" goto debuild
goto :start

:rebuild
call mvn clean
call mvn install -Dmaven.test.skip=true 

:start
call %CATALINA_HOME%\bin\catalina.bat stop
call mvn cargo:undeploy cargo:deploy

call %CATALINA_HOME%\bin\catalina.bat jpda start

:end
