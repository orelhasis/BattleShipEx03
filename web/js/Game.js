var justRefreshed = false;
$(function(){
    setInterval(function () {
        if(!justRefreshed){
            $('input[name=Action]').val('Refresh');
            doPlayerAction();
        }
    },5000);
});

function playerMove(x,y){
    $('input[name=x]').val(x);
    $('input[name=y]').val(y);
    $('input[name=Action]').val('Attack');
    doPlayerAction();
}
function doPlayerAction(){
    var formData  = new FormData($('form[name=actionForm]')[0]);
    justRefreshed = true;
    $.ajax({
        url:  'Action',
        type: 'POST',
        data: formData,
        enctype: 'multipart/form-data',
        processData: false,  // Important!
        contentType: false,
        success: function(responseText){
            updateDivs(responseText);
            justRefreshed = false;
        },
        error: function(xhr, ajaxOptions, thrownError){
            $('.error-div').html('Action Failed');
            justRefreshed = false;
        }
    });
}

function DragMine(event){
    //event.dataTransfer = event.originalEvent.dataTransfer;
    event.dataTransfer.setData("isMine","Yes")
}

function allowDrop(event){
    event.preventDefault();
}

function DroppedMine(event,x,y){
    event.preventDefault();
    if(event.dataTransfer.getData("isMine") == "Yes"){
        $('input[name=x]').val(x);
        $('input[name=y]').val(y);
        $('input[name=Action]').val('Mine');
        doPlayerAction();
    }
}

function updateDivs(replacementHtml){
    if(replacementHtml == "GoBack"){
        window.location = "BattleShips"
    }
    else if(replacementHtml == "GameOver"){
        window.location = "GameOver"
    }
    var player = $(replacementHtml).closest('#myDetails').html();
    var Statistics = $(replacementHtml).closest('#Statistics').html();
    var opponent = $(replacementHtml).closest('#opponentDetails').html();
    var boards = $(replacementHtml).closest('#boards').html();
    var msgToPlayer = $(replacementHtml).find('#msgPlayer').html();
    var actionResults = $(replacementHtml).find('#actionResults').html();
    createMsgsDialogs(msgToPlayer, actionResults);
    $('#myDetails').html(player);
    $('#opponentDetails').html(opponent);
    $('#boards').html(boards);
    $('#Statistics').html(Statistics);
}

function createMsgsDialogs(msgToPlayer, actionResults){
    if(msgToPlayer.length > 0) {
        startDialogWithText(msgToPlayer,"Game Update");
    }
    if(actionResults.length > 0) {
        startDialogWithText(actionResults,"Your Move results");
    }
}

function startDialogWithText(text, title){
    if(text.length > 0){
        $("<div>" + text + "</div>").dialog({
            title:title,
            modal: true,
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
}

function showStatistics(){
    $("#Statistics").dialog({
        title:"Statistics",
        modal: true,
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

function startSurrender(surrenderType){
    $("#surrenderDiv").html("Are you sure you want to " + surrenderType + "?")
    $("#surrenderDiv").dialog({
        title:surrenderType,
        buttons: [
            {
                text: "Yes, " + surrenderType,
                click: function() {
                    $(this).dialog( "close" );
                    surrender(surrenderType);
                }
            },
            {
                text: "Hell No!",
                click: function() {
                    $(this).dialog( "close" );
                }
            }
        ]
    });
}

function surrender(surrenderType){
    $('input[name=Action]').val(surrenderType);
    doPlayerAction(surrenderType,0,0)
}