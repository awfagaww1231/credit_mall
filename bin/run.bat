@echo off
echo.
echo [��Ϣ] ʹ��Jar��������Web���̡�
echo.

cd %~dp0
cd ../ruoyi-admin/target

set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -jar -Xms3072m -Xmx3072m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m admin.jar

cd bin
pause