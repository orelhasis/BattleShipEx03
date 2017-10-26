$(function()
{
    $('input[name=SubmitGame]').click(submitForm);
    setInterval(function () {
        refreshGamesList();
        getPlayersList();
    },5000);

});

function refreshGamesList(){
    $.ajax({
        url:  'RefreshList',
        type: 'POST',
        enctype: 'multipart/form-data',
        processData: false,  // Important!
        contentType: false,
        success: function(responseText){
            updateList(responseText);
        },
        error: function(xhr, ajaxOptions, thrownError){
        }
    });
}

function getPlayersList(){
    $.ajax({
        url:  'GetPlayers',
        type: 'POST',
        dataType: 'json',
        enctype: 'multipart/form-data',
        processData: false,  // Important!
        contentType: false,
        success: function(responseJson){
            updatePlayerList(responseJson);
        },
    });
}

function updateList(newList){
    $('#availableGames').html(newList);
}

function updatePlayerList(PlayersJson){
    $('#OnlinePlayers ul').html("");
    for(var i = 0 ; i < PlayersJson.players.length ; i++){
        $('#OnlinePlayers ul').append("<li>" + PlayersJson.players[i].name + "</li>");
    }
}

function submitForm() {
    var errString = checkFormData();
    if(errString.length > 0){
        $('#Uploadoutput').html(errString);
    }
    else {
        var fdata = new FormData($('form[name=UploadGame]')[0]);
        $.ajax({
            url: 'UploadGame',
            type: 'POST',
            data: fdata,
            enctype: 'multipart/form-data',
            processData: false,  // Important!
            contentType: false,
            success: function (responseText) {
                setMessageToUser(responseText);
                refreshGamesList();
            },
            error: function (xhr, ajaxOptions, thrownError) {
                setMessageToUser('<div class="fail-div">Could not Upload</div>');
            }
        });
    }
    return false;
}

function delGame(id){
    $.ajax({
        url: 'Delete?gameID=' + id,
        type: 'GET',
        dataType: 'json',
        enctype: 'multipart/form-data',
        success: function (responseJson) {
            console.log(responseJson);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log("Failed Deletion call")
        }
    });
}

function checkFormData(){
    var errString = '';
    if($('input[name=gameName]').val().length == 0){
        errString ='<div class="fail-div">Please Select Game Name';
    }
    if($('input[name=gameFile]').val().length == 0){
        errString+=errString.length > 0 ? '<br>' : '<div class="fail-div">';
        errString+='Please Select a File';
    }
    errString+=errString.length > 0 ? '</div>' : '';
    return errString;
}

function setMessageToUser(msg){
    $('#Uploadoutput').html(msg);
    $('#Uploadoutput').dialog({
        title: "Game Upload status",
        buttons: [
            {
                text: "OK",
                click: function() {
                    $( this ).dialog( "close" );
                }
            }
        ]
    });
}