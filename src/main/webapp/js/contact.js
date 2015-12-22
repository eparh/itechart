function openbox(id) {
    var div = document.getElementById(id);

    if(div.style.display == 'block') {
        div.style.display = 'none';
    }
    else {
        div.style.display = 'block';
    }
}

var phoneService = {
    pos : 0,
    popUp: 'phonePopUp',
    mode: 0,

    savePhone: function () {
        var form= document.getElementById("telephone");

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

        var fullPhone = form.countryCode.value +  form.operatorCode.value +  form.phone.value;
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

