@ECHO OFF


java -jar "%~dp0../lib/cucumber-sandwich.jar" -r "FirefoxReport" -f "%~dp0../target/firefoxOutput" -o "%~dp0../target/firefoxOutput" -n

end