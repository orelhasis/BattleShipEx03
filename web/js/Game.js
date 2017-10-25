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
function doPlayerAction(action,x,y){
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
    var player = $(replacementHtml).closest('#myDetails').html();
    var opponent = $(replacementHtml).closest('#opponentDetails').html();
    var boards = $(replacementHtml).closest('#boards').html();
    $('#myDetails').html(player);
    $('#opponentDetails').html(opponent);
    $('#boards').html(boards);
}