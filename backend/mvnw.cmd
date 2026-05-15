@echo off
@setlocal

set ERROR_CODE=0

if not "%JAVA_HOME%" == "" goto OkJHome
for %%i in (java.exe) do @set JAVA_EXE=%%~$PATH:i
if not "%JAVA_EXE%" == "" goto OkJHomeByPath
echo Error: JAVA_HOME not found. Please set JAVA_HOME. >&2
goto error

:OkJHomeByPath
set JAVA_CMD=java
goto DetectBase

:OkJHome
set JAVA_CMD="%JAVA_HOME%\bin\java.exe"

:DetectBase
set MAVEN_PROJECTBASEDIR=%~dp0
if "%MAVEN_PROJECTBASEDIR:~-1%"=="\" set MAVEN_PROJECTBASEDIR=%MAVEN_PROJECTBASEDIR:~0,-1%

set WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar

if exist "%WRAPPER_JAR%" goto runWrapper

echo Downloading Maven Wrapper...
powershell -Command "$dest = '%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar'; [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; (New-Object System.Net.WebClient).DownloadFile('https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar', $dest)"
if ERRORLEVEL 1 goto error
echo Maven Wrapper downloaded.

:runWrapper
%JAVA_CMD% -classpath "%WRAPPER_JAR%" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" org.apache.maven.wrapper.MavenWrapperMain %*

if ERRORLEVEL 1 goto error
goto end

:error
set ERROR_CODE=1
:end
@endlocal & exit /B %ERROR_CODE%
