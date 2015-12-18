function deleteContact(){
    var form = document.getElementById('checkbox');
    form.command.value = 'delete';
    form.submit();
}
function getContact(){
    var form = document.getElementById('checkbox');
    form.command.value = 'get';
    form.idContact.value = '';
    form.submit();
}
function getContactByClick(num){
    var form = document.getElementById('checkbox');
    var table = document.getElementById('table');
    var row = table.rows[num-1];
    var id = row.cells[0].firstElementChild.value;
    form.command.value = 'get';
    form.idContact.value = id;
    form.submit();
}
