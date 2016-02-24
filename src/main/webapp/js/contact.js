function openbox(id) {
    "use strict";
    var div = document.getElementById(id);

    if(div.style.display == 'block') {
        div.style.display = 'none';
    }
    else {
        div.style.display = 'block';
    }
}

function selectAvatar() {
    "use strict";
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

var phoneService = {
    pos : 0,
    popUp: 'phonePopUp',
    mode: 0,
    MINVALUE : 1000,
    MAXVALUE : 99999999,

    savePhone: function () {
        "use strict";
        var form= document.getElementById("telephone");
        if (!(form.operatorCode.value && form.phone.value )) {
            alert("Please, fill required fields");
            return false;
        }
        if (form.countryCode.value > this.MINVALUE  ||  form.phone.value > this.MAXVALUE ||
            form.operatorCode.value > this.MINVALUE ){
            alert("Please, check input fields");
            return false;
        }
        openbox(this.popUp);

        var table = document.getElementById("phoneTable");

        var i, row,cell1, cell2, cell3,cell4,cell5,cell6,cell7;

        if (this.mode == 0) {
            i = table.rows.length;
            row = table.insertRow(i);
            cell1 = row.insertCell(0);
            cell2 = row.insertCell(1);
            cell3 = row.insertCell(2);
            cell4 = row.insertCell(3);
            cell5 = row.insertCell(4);
            cell6 = row.insertCell(5);
            cell7 = row.insertCell(6);
        }  else {
            i = this.pos;
            row = table.rows[i];
            cell1 = row.cells[0];
            cell2 = row.cells[1];
            cell3 = row.cells[2];
            cell4 = row.cells[3];
            cell5 = row.cells[4];
            cell6 = row.cells[5];
            cell7 = row.cells[6];
        }

        cell1.innerHTML = "<input type='checkbox'  name='phones'/>";

        var fullPhone = form.countryCode.value +" " +form.operatorCode.value +" "+ form.phone.value;

        cell2.innerHTML ="<input type='text' form='form' value='"+ fullPhone +"' readonly/>";

        cell3.innerHTML ="<input type='text' form='form' name='kind"+i+"' value='"+form.kind.value+"' readonly/>";
        cell4.innerHTML ="<input type='text' form='form' name='comment"+i+"' value='"+form.comment.value+"' readonly/>";
        cell5.innerHTML ="<input type='hidden' form='form' name='countryCode"+i+"' value='"+form.countryCode.value+"' />";
        cell6.innerHTML ="<input type='hidden' form='form' name='operatorCode"+i+"' value='"+form.operatorCode.value+"' />";
        cell7.innerHTML ="<input type='hidden' form='form' name='phone"+i+"' value='"+form.phone.value+"' />";
        form.reset();
    },

    deletePhone: function () {
        "use strict";
        var table = document.getElementById("phoneTable");
        var checkboxes = document.getElementsByName('phones'), length = checkboxes.length;

        for (var i=length - 1; i>=0; i--) {
            if (checkboxes[i].checked) {
                table.deleteRow(i);
            }
        }
    },

    editPhone: function () {
        "use strict";
        var form= document.getElementById("telephone");
        var table = document.getElementById("phoneTable");
        var checkboxes = document.getElementsByName('phones'), length = checkboxes.length;

        for (var i=0; i<length; i++) {
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
        "use strict";
        this.mode = 0;
        openbox(this.popUp);
    },

    cancelPhone: function () {
        "use strict";
        document.getElementById("telephone").reset();
        openbox(this.popUp);
    }
};

var attachService = {
    popUp: 'attachPopUp',

    saveAttach: function () {
        "use strict";
        var input_file = document.getElementById("b_attach");
        var form= document.getElementById("form");


        var file = form.attach.files[0];
        if(file != undefined && file.size > 1024* 1024 * 10) {
            alert("Too much size of file!Maximum size of file is 10 MB");
            return false;
        }
        if(input_file.style.display == "initial" && form.attach.value == "") {
            alert("Choose file, please");
            return false;
        }
        openbox(this.popUp);
        form.command.value = 'setattach';
        form.submit();
    },

    deleteAttach: function () {
        "use strict";
        var form= document.getElementById("form");
        var checkboxes = document.getElementsByName('attaches');
        var length = document.getElementsByName('attaches').length;
        var flag = false;
        for (var i=length - 1; i>=0; i--) {
            if (checkboxes[i].checked) {
                flag = true;
                break;
            }
        }
        if (flag === false) {
            return false;
        }
        form.command.value = 'setattach';
        form.attachMode.value = 'delete';
        form.submit();
    },

    editAttach: function () {
        "use strict";
        var comment= document.getElementById("attachComment");
        var table = document.getElementById("attachTable");
        var checkboxes = document.getElementsByName('attaches'), length = checkboxes.length;

        document.getElementById("form").attachMode.value = "edit";

        var input_file = document.getElementById("b_attach");
        var file_name = document.getElementById("b_file_name");
        input_file.style.display = "none";
        file_name.style.display = "initial";

        for (var i=0; i<length; i++) {
            if (checkboxes[i].checked) {
                var row = table.rows[i];
                document.getElementById("file_name").value = row.cells[4].firstElementChild.value;
                comment.value = row.cells[3].firstElementChild.value;
                openbox(this.popUp);
                break;
            }
        }

    },

    addAttach: function () {
        "use strict";
        var input_file = document.getElementById("b_attach");
        var file_name = document.getElementById("b_file_name");
        input_file.style.display = "initial";
        file_name.style.display = "none";
        document.getElementById("form").attachMode.value = "add";
        openbox(this.popUp);
    },

    cancelAttach: function () {
        "use strict";
        var comment= document.getElementById("attachComment");
        comment.value = '';
        openbox(this.popUp);
    }
};
