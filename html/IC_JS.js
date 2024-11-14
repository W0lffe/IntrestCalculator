/*File: IC_JS.js
Author: Henry Karppinen
Date: 31.10.2024
Version: 1.0

Brief: This program is designed to be an simplified
user interface/display for my Java app "Intrest Calculator".
This program utilizes jQuery and vanilla Javascript.

This software is under GPL License.
*/


let ClientData = []; //Initializing array for data that is made with Java
let StockData = []; //Initializing array for data that comes from API request

$(document).ready(function() {
    createClientDataList(); //when opening html, creates table with client data
    getStockData();  // creates div elements with stock data

});

$("#panel").click(function(){
        $("#table").slideToggle("slow");
})
$("#table").hide();

$("#dividerParagraph").click(function(){
    $("#left_container").slideToggle("slow");
})
$("#left_container").hide();

$("#refresh").click(function(){
    updateStockData();
})


/*function: createClientDataList
description: This function fetches investment data that is made with Java from PHP, function will
loop through object properties and append to table*/
function createClientDataList(){
    const method = 1; //method for PHP, gets client data from JSON file
    $.get(`urlHere?method=${method}`, function(data){
        console.log(data);
        ClientData = data;

        if(data != null){
            $("#panel").html("Client data loaded - Press here");
            $("#table").html("");
            const row = $("<tr></tr>").html(`
                <td>#</td>
                <td>Deposit</td>
                <td>Percentage</td>
                <td>Time</td>
                <td>After Intrest</td>
                <td>Earnings</td>
                <td>Type</td>
                <td>Stocks #</td>`);
                $("#table").append(row);
    
                ClientData.Data.forEach(item => {
                    let stock;
                  
                    if(item.quantity != null){
                        stock = item.quantity;
                    }else{
                        stock = "";
                    }
            
                    let id = item.id;
                    const row = $("<tr></tr>").html(`
                    <td>${item.id}</td>
                    <td>${item.deposit.toFixed(2)}€</td>
                    <td>${item.percentage.toFixed(2)}%</td>
                    <td>${item.time} ${item.period}</td>
                    <td>${item.afterIntrest.toFixed(2)}€</td>
                    <td>${item.earnings.toFixed(2)}€</td>
                    <td>${item.type}</td>
                    <td>${stock}</td>
                    <td> <button id="deleteButton" onclick="deleteData(${id})">Delete Entry</button>
                    `);
                    $("#table").append(row);
                });
        };
    });
};

/*function: deleteData 
parameter: id number of selected row
description: this function deletes data from json file, it will send ID as parameter to PHP
wich will handle delete*/
function deleteData(id) {
    const choice = window.confirm("Are you sure you want to delete this data?");
    
    if (choice) {
        console.log("Deleting data for id:", id);

        const dataToDelete = ClientData.Data.findIndex(item => item.id === id);
        console.log("Deleting index: " + dataToDelete);
        
        if (dataToDelete !== -1) {

            $.ajax({
                url: `urlHere?id=${id}`,
                method: "DELETE",
                success: function() {
                    console.log("Data deleted successfully");
                    setTimeout(() => { createClientDataList(); }, 1000); //Update client data list
                },
                error: function(xhr, error) {
                    console.error("Failed to delete data on the server:", xhr, error);
                }
            });
        } else {
            console.error("Data not found in the array");
        }
    }
}

/*function: getStockData
description: This function will send a method to PHP wich will fetch latest data from api
and return back here. This function will call createStockDataTable to handle the new data*/
function getStockData(){
    const method = 2; //method for PHP, gets API data from JSON file
    $.get(`urlHere?method=${method}`, function(data){
        console.log(data);
        StockData = data;
        createStockDataTables();
    }) 
}

/*function: getStockData
description: This function will send a method to PHP wich will fetch latest data from api
and return back here. This function will call getStockData after fetching new data*/
function updateStockData(){
    const apikey = prompt("Please give your API key");
    if(apikey == null || apikey == ""){
        return;
    }
    $.get(`urlHere?apikey=${apikey}`, function(data){
        console.log(data);
        getStockData();
    })
}


/*function: createStockDataTables
description: This function handles the API data by looping through
array, creating div elements and appending data paragraphs to div elements */
function createStockDataTables(){
    $("#stocks").html("");
    $("#left_container").html("");
    $("#dividerParagraph").html("Market Status");
    $("#refresh").html("Update Stock Data");


    //StockData.Data(ARRAY) => StockData.Data.Symbol => StockData.Data.Symbol["Global Quote"]=> Access to object properties
    //Object keys takes Array(StockData.Data) wich has symbols, and converts symbols to enum
    Object.keys(StockData.Data).forEach(symbol => {
        const stock = StockData.Data[symbol]; //StockData.Data.Symbol
        const globalQuote = stock["Global Quote"]; //StockData.Data.Symbol["Global Quote"]
        const stockMarkets = stock["markets"]; //StockData.Data.Symbol["markets"]
        let invest = "Stock";

        if(symbol != "Status"){
            const stockSymbol = globalQuote["01. symbol"];
            const open = parseFloat(globalQuote["02. open"]);
            const high = parseFloat(globalQuote["03. high"]);
            const low = parseFloat(globalQuote["04. low"]);
            const price = parseFloat(globalQuote["05. price"]);
            const day = globalQuote["07. latest trading day"];
            const close = parseFloat(globalQuote["08. previous close"]);
            const change = parseFloat(globalQuote["10. change percent"]);
            if(symbol == "QDVE.DEX"){
                invest = "ETF";
            }
            //Create div with stock data
            const div = $(`<div class="stock"></div>`).html(`
                <h4>${invest}: ${stockSymbol}</h4>
                <p>Open: ${open.toFixed(2)}</p>
                <p>High: ${high.toFixed(2)}</p>
                <p>Low: ${low.toFixed(2)}</p>
                <p>Price: ${price.toFixed(2)}</p>
                <p>Latest trading day: ${day}</p>
                <p>Close: ${close.toFixed(2)}</p>
                <p id="changeParagraph">Change: ${change.toFixed(2)}%</p>`
            );
            if(change > 0){
                div.find("#changeParagraph").css("color", "green");
            }
            else{
                div.find("#changeParagraph").css("color", "red");
            }
            
            $("#stocks").append(div);
        }
        else if(symbol == "Status"){
            const markets = ["United States", "Germany", "United Kingdom", "Canada", "Japan"];
            for(let i = 0; i < markets.length; i++){
                const foundMarket = stockMarkets.find(market => market.region === markets[i]);

                //create div with market status data
                const div = $(`<div class="status"></div>`).html(`
                    <h4>Country: ${foundMarket.region}</h4>
                    <p>Exchanges: ${foundMarket.primary_exchanges}</p>
                    <p>Open (local): ${foundMarket.local_open}</p>
                    <p>Close (local): ${foundMarket.local_close}</p>
                    <p>Current: ${foundMarket.current_status}</p>
                    `);
                $("#left_container").append(div);
            } 
        }
    })
}
