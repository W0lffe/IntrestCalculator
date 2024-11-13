## Brief: 
This program is designed to calculate interest on your deposits. 
It collects information from the user, such as deposit amount, investment duration, estimated interest rate, and calculation method. 
The program utilizes multiple functions, classes, and objects. 
Currently, it includes basic functions for calculating interest on deposits, saving data locally, and storing it on a server. 
The user interface is created with HTML and JavaScript, while PHP fetches additional investment-related information for the HTML page. 
This program is intended for personal use and experimentation with different programming techniques and is subject to change

## To run:

compile:
´´´
javac -cp "lib/javafx-sdk-23/lib/*;lib/gson/*" src/*.java -d out
´´´
run:
´´´
java --module-path "lib/javafx-sdk-23/lib/" --add-modules javafx.controls,javafx.fxml -cp "out;lib/gson/*" Main
´´´

## Roadmap: 
### Calculations 
* [x] Linear calculations 
* [X] Advanced calculations
    * [X] Stocks calculation
    * [X] Funds calculation

### Saving data 
* [x] Saving to textfile 
* [x] Reading textfiles with the software

### Backend 
* [x] Very basic POST, GET, DELETE 
    * [ ]  Add user authenticating

### Debug 
* [x] Testcases for debugging

### GUI 
* [x] HTML User Inteface -- Displays data from this app and other API
* [ ]  JavaFX -- experimenting started, very primitive so far. Will keep working on it

### Other
* [ ] Documentation / Comment source code

#### To Fix
* VS Code Task: Run



