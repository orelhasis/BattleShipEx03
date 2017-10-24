$(function()
{
    $('input[name=SubmitGame]').click(function () {
        var errString = checkFormData();
        var fdata = new FormData($('form[name=UploadGame]')[0]);
        $.ajax({
            url:  'UploadGame',
            type: 'POST',
            data: fdata,
            enctype: 'multipart/form-data',
            processData: false,  // Important!
            contentType: false,
            success: function(responseText){
                setMessageToUser(responseText);
            },
            error: function(xhr, ajaxOptions, thrownError){
                setMessageToUser('<div class="fail-div">Could not Upload</div>');
            }
        });
    });
});

function checkFormData(){
    var errString = '';
    if($('input[name=gameFile]').val().length == 0){
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
}