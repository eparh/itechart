function openbox(id) {
    var div = document.getElementById(id);

    if(div.style.display == 'block') {
        div.style.display = 'none';
    }
    else {
        div.style.display = 'block';
    }
}


function saveContact(){
    var form= document.getElementById("form");
    var name = form['name'].value;
    var surname = form['surname'].value;
    console.log('hi');
    if (name == null || name == ""|| surname == null || surname == "") {
        return false;
    }
    form.command.value = 'save';

    form.submit();
}


var phoneService = {

    count: 0,

    addPhone: function () {
        var form= document.getElementById("telephone");

        var phone = {};

        phone.countryCode = form.countryCode;
        phone.operatorCode = form.operatorCode;
        phone.phone = form.phone;
        phone.kind = form.kind;
        phone.comment = form.comment;

        this.count++;
        var i = this.count;
        openbox('Wrapp');
        alert(i);


        var table = document.getElementById("phoneTable");
        var row = table.insertRow(this.count);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var cell6 = row.insertCell(5);
        var cell7 = row.insertCell(6);
        cell1.innerHTML = "<input type='checkbox' value='"+i+"' readonly/>";

        var fullPhone = phone.countryCode.value +  phone.operatorCode.value +  phone.phone.value;
        cell2.innerHTML ="<input type='text' form='form' value='"+ fullPhone +"'name='hi' readonly/>";

        cell3.innerHTML ="<input type='text' form='form' name='kind"+i+"' value='"+phone.kind.value+"' readonly/>";
        cell4.innerHTML ="<input type='text'form='form' name='comment"+i+"' value='"+phone.comment.value+"' readonly/>";
        cell5.innerHTML ="<input type='hidden'form='form' name='countryCode"+i+"' value='"+phone.countryCode.value+"' />";
        cell6.innerHTML ="<input type='hidden'form='form' name='operatorCode"+i+"' value='"+phone.operatorCode.value+"' />";
        cell7.innerHTML ="<input type='hidden'form='form' name='phone"+i+"' value='"+phone.phone.value+"' />";
        form.reset();
    }


}