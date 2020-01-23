@ECHO OFF


java -jar "%~dp0../lib/cucumber-sandwich.jar" -r "ChromeReport" -f "%~dp0../target/chromeOutput" -o "%~dp0../target/chromeOutput/" -n

pause