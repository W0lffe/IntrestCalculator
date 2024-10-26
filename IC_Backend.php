<?php

header('Content-Type: application/json');

//echo "My first PHP script!";

//Checks if there is directory called Data existing, if not it will be created with permissions
if(!is_dir('Data')){
    mkdir('Data', 0765, true);
}

//Path for saving location
$FILE_PATH = 'Data/data.json';

//Handle POST method from App
if($_SERVER['REQUEST_METHOD'] === 'POST'){
    $DATA_FROM_CLIENT = file_get_contents("php://input");
    
//Checks if file exists already
if(file_exists($FILE_PATH)){

    //Get existing data from File
    $EXISTING_DATA = file_get_contents($FILE_PATH);
    //Place decoded data to this variable
    $DATA_ARRAY = json_decode($EXISTING_DATA, true);
   }

//Create array and add data from client
$DATA_ARRAY[] = json_decode($DATA_FROM_CLIENT, true);
//Encode data
$NEW_DATA = json_encode($DATA_ARRAY, JSON_PRETTY_PRINT);

//Save new data to file
if(file_put_contents($FILE_PATH, $NEW_DATA)){
    echo json_encode(["Saved data:" => $NEW_DATA]);
   }
   else{
    echo json_encode(["Message:" => "Failed to save data"]);
   }

}



 //Handles GET method from App
if($_SERVER['REQUEST_METHOD'] === 'GET'){
     
    //Checks if file exists
    if(file_exists($FILE_PATH) ){
        $DATA = file_get_contents($FILE_PATH); //Get contents of file if it exists

        //Checks if file is empty, if not it will send data
        if(!empty($DATA)){
            //$DECODED_DATA = json_decode($DATA, true);
            echo $DATA;
            //echo json_encode(["DATA: " => $DECODED_DATA]);
        }
        else{
            echo json_encode(["status" => "error", "message" => "File is empty!"]);  
        }
    }
    else {
        //echo "Data not found!";
        echo json_encode(["status" => "error", "message" => "File does not exist!"]);
    } 
}
 else{
    echo json_encode(["Error: " => "Failed to fetch data!"]);
}  







?> 