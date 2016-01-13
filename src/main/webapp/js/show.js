function openbox(id) {
    var div = document.getElementById(id);

    if(div.style.display == 'block') {
        div.style.display = 'none';
    }
    else {
        div.style.display = 'block';
    }
}

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
function email() {
    var checkboxes = document.getElementsByName('marked');
    var table = document.getElementById("table");
    var address= document.getElementById("address");
    var countAddress = 0;
    var addressesOnRow = 3;
    address.value = "";
    for (var i=0; i<checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            var row = table.rows[i];
            var email = row.cells[5].childNodes[0].value;
            if (email != null && email != "") {
                var separator = ",";
                if(countAddress % addressesOnRow == 0) separator  += "\n";
                if(countAddress != 0) address.value += separator;
                address.value += email;
                countAddress++;
            }
        }
    }
    if(countAddress !=0) {
        var addressRows = countAddress / addressesOnRow| 0;
        if (addressRows == 0 || countAddress % addressesOnRow != 0) addressRows++;
        if(addressRows > 4) addressRows = 4;
        address.rows = addressRows;
        openbox('emailPopUp');
    } else {
        alert("Choose contacts with email, please");
        return false;
    }

}