function deleteContact() {
    var form = document.getElementById('checkbox');
    form.command.value = 'delete';
    form.mode.value = 'delete';
    if( countCheked()>0 ) form.submit();
}

function getContact() {
    var form = document.getElementById('checkbox');
    form.command.value = 'get';
    form.idContact.value = '';
    if( countCheked()>0 ) form.submit();
}
function getContactByClick(num) {
    var form = document.getElementById('checkbox');
    var table = document.getElementById('table');
    var row = table.rows[num-1];
    var id = row.cells[0].firstElementChild.value;
    form.command.value = 'get';
    form.idContact.value = id;
    form.submit();
}
function next() {
    var form = document.getElementById("paging");
    if( form.pageNumber.value < form.pageNumber.max) {
        form.pageNumber.value ++ ;
        form.submit();
    }
}
function prev() {
    var form = document.getElementById("paging");
    if( form.pageNumber.value > 1) {
        form.pageNumber.value -- ;
        form.submit();
    }
}
function chgRecordsPerPage() {
    var form = document.getElementById("paging");
    form.pageNumber.value = 1;
    form.submit();
}
function checkAll(checktoggle) {
    var checkboxes = document.getElementsByName("marked");
    for (var i=0; i<checkboxes.length; i++)  {
        checkboxes[i].checked = checktoggle;
    }
}
function countCheked() {
    var checkboxes = document.getElementsByName("marked");
    var count = 0;
    for (var i=0; i<checkboxes.length; i++)
        if (checkboxes[i].checked)  count++;
    return count;
}
alert("HI");