## Brief: 
This program is designed to calculate interest on your deposits. 
It collects information from the user, such as deposit amount, investment duration, estimated interest rate, and calculation method. 
The program utilizes multiple functions, classes, and objects. 
Currently, it includes basic functions for calculating interest on deposits, saving data locally, and storing it on a server. 
The user interface is created with HTML and JavaScript, while PHP fetches additional investment-related information for the HTML page. 
This program is intended for personal use and experimentation with different programming techniques and is subject to change

## Status of Project:
#### HTML Frontend: 
The HTML page is complete and successfully displays API data as intended. 
The frontend provides users with a simple, accessible interface for viewing information from the API.

#### Java App Functionality: 
The application includes all planned core functions, and each has been tested to ensure functionality aligns with project requirements. 
The application processes data effectively and is fully integrated with the API for smooth data handling and communication.

#### Java App Interface: 
Development of the JavaFX graphical interface is still ongoing. While some elements are still being refined, the interface is functional and offers basic usability. 
Continued work on JavaFX will enhance user experience and complete the visual components of the application.

## How to Use

compile:
```
javac -cp "lib/javafx-sdk-23/lib/*;lib/gson/*" src/*.java -d out
```
run:
```
java --module-path "lib/javafx-sdk-23/lib/" --add-modules javafx.controls,javafx.fxml -cp "out;lib/gson/*" Main
```

Place HTML, JS, PHP files on a server. Add the URL to source files: Cache.java and IC_JS.js.

```
yourURLhere/IC_Backend.php
```

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
* [ ]  JavaFX -- Ready for use, missing styling, needs refining. But usable.

### Other
* [ ] Documentation / Comment source code
* [ ] Executable file




