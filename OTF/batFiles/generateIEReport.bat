@ECHO OFF


java -jar "%~dp0../lib/cucumber-sandwich.jar" -r "IEReport" -f "%~dp0../target/IEOutput" -o "%~dp0../target/IEOutput" -n

end