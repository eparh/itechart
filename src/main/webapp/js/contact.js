function openbox(id) {
    var div = document.getElementById(id);

    if(div.style.display == 'block') {
        div.style.display = 'none';
    }
    else {
        div.style.display = 'block';
    }
}

function selectAvatar() {
    document.getElementById("avatar").onchange = function () {
        var reader = new FileReader();

        reader.onload = function (e) {
            // get loaded data and render thumbnail.
            document.getElementById("image").src = e.target.result;
        };

        // read the image file as a data URL.
        reader.readAsDataURL(this.files[0]);
    };
}

function getDate() {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();

    if(dd<10) {
        dd='0'+dd
    }

    if(mm<10) {
        mm='0'+mm
    }
    today = yyyy +'-'+mm+'-'+dd;

    return today;
}

var phoneService = {
    pos : 0,
    popUp: 'phonePopUp',
    mode: 0,

    savePhone: function () {
        var form= document.getElementById("telephone");
        if (form.operatorCode.value == "" || form.phone.value == "") {
            alert("Please, fill required fields");
            return false;
        }
        if (form.countryCode.value > 1000 ||  form.phone.value > 99999999 || form.operatorCode.value > 1000) {
            alert("Please, check input fields");
            return false;
        }
        openbox(this.popUp);

        var table = document.getElementById("phoneTable");

        if (this.mode == 0) {
            var i = table.rows.length;
            var row = table.insertRow(i);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);
            var cell6 = row.insertCell(5);
            var cell7 = row.insertCell(6);
        }  else {
            var i = this.pos;
            var row = table.rows[i];
            var cell1 = row.cells[0];
            var cell2 = row.cells[1];
            var cell3 = row.cells[2];
            var cell4 = row.cells[3];
            var cell5 = row.cells[4];
            var cell6 = row.cells[5];
            var cell7 = row.cells[6];
        }

        cell1.innerHTML = "<input type='checkbox'  name='phones'/>";

        var fullPhone = form.countryCode.value +" " +form.operatorCode.value +" "+ form.phone.value;

        cell2.innerHTML ="<input type='text' form='form' value='"+ fullPhone +"' readonly/>";

        cell3.innerHTML ="<input type='text' form='form' name='kind"+i+"' value='"+form.kind.value+"' readonly/>";
        cell4.innerHTML ="<input type='text'form='form' name='comment"+i+"' value='"+form.comment.value+"' readonly/>";
        cell5.innerHTML ="<input type='hidden'form='form' name='countryCode"+i+"' value='"+form.countryCode.value+"' />";
        cell6.innerHTML ="<input type='hidden'form='form' name='operatorCode"+i+"' value='"+form.operatorCode.value+"' />";
        cell7.innerHTML ="<input type='hidden'form='form' name='phone"+i+"' value='"+form.phone.value+"' />";
        form.reset();
    },

    deletePhone: function () {
        var table = document.getElementById("phoneTable");
        var checkboxes = document.getElementsByName('phones');

        for (var i=checkboxes.length - 1; i>=0; i--) {

            if (checkboxes[i].checked) {
                table.deleteRow(i);
            }
        }
    },

    editPhone: function () {
        var form= document.getElementById("telephone");
        var table = document.getElementById("phoneTable");
        var checkboxes = document.getElementsByName('phones');

        for (var i=0; i<checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                var row = table.rows[i];
                form.countryCode.value = row.cells[4].childNodes[0].value;
                form.operatorCode.value = row.cells[5].childNodes[0].value;
                form.phone.value = row.cells[6].childNodes[0].value;
                form.kind.value = row.cells[2].childNodes[0].value;
                form.comment.value = row.cells[3].childNodes[0].value;
                this.pos = i;
                this.mode = 1;
                openbox(this.popUp);
                break;
            }
        }

    },

    addPhone: function () {
        this.mode = 0;
        openbox(this.popUp);
    },

    cancelPhone: function () {
        document.getElementById("telephone").reset();
        openbox(this.popUp);
    }
}

var attachService = {
    pos : 0,
    popUp: 'attachPopUp',
    mode: 0,

    saveAttach: function () {
        var form= document.getElementById("attachment");

        openbox(this.popUp);

        var table = document.getElementById("attachTable");

        if (this.mode == 0) {
            var i = table.rows.length;
            var row = table.insertRow(i);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);
        }  else {
            var i = this.pos;
            var row = table.rows[i];
            var cell1 = row.cells[0];
            var cell2 = row.cells[1];
            var cell3 = row.cells[2];
            var cell4 = row.cells[3];
            var cell5 = row.cells[4];
        }

        cell1.innerHTML = "<input type='checkbox'  name='attaches'/>";

        var fileName = form.attach.value.split(/(\\|\/)/g).pop();

        cell2.innerHTML ="<input type='text' form='form' value='"+ fileName +"' readonly/>";

        cell3.innerHTML ="<input type='date' form='form' name='attachDate"+i+"' value='"+ getDate() +"' readonly/>";
        cell4.innerHTML ="<input type='text'form='form' name='comment"+i+"' value='"+form.comment.value+"' readonly/>";

        var file = form.attach.files[0];
        alert(file.size);

        cell5.innerHTML ="<input type='file'form='form' name='attach"+i+"' value='"+file+"'  style='display: none'/>";
        form.reset();
    },

    deleteAttach: function () {
        var table = document.getElementById("attachTable");
        var checkboxes = document.getElementsByName('attaches');

        for (var i=checkboxes.length - 1; i>=0; i--) {

            if (checkboxes[i].checked) {
                table.deleteRow(i);
            }
        }
    },

    editAttach: function () {
        var form= document.getElementById("attachment");
        var table = document.getElementById("attachTable");
        var checkboxes = document.getElementsByName('attaches');

        for (var i=0; i<checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                var row = table.rows[i];
                var file = row.cells[4].firstElementChild.files[0];
                alert(file.size);
                form.attach.files[0] = row.cells[4].firstElementChild.files[0];
                form.comment.value = row.cells[3].firstElementChild.value;
                this.pos = i;
                this.mode = 1;
                openbox(this.popUp);
                break;
            }
        }

    },

    addAttach: function () {
        this.mode = 0;
        openbox(this.popUp);
    },

    cancelAttach: function () {
        document.getElementById("attachment").reset();
        openbox(this.popUp);
    }
}

