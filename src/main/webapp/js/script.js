function openbox(id) {
    var div = document.getElementById(id);

    if(div.style.display == 'block') {
        div.style.display = 'none';
    }
    else {
        div.style.display = 'block';
    }
}

function addPhone(){
    var form= document.getElementById("form");
    form.command.value ='phone';
    form.mode.value= 'add';
    form.submit();
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

function addPhone2(){
    var form= document.getElementById("form");
    var phone = {}
    phone.countryCode.value = form.countryCode.value;
    phone.operatorCode.value = form.operatorCode.value;
    phone.phone.value = form.phone.value;
    phone.kind.value = form.kind.value;
    phone.comment.value = form.comment.value;
    console.log(phone.countryCode.value);
    alert(phone.countryCode.value);
    arr = ( typeof arr != 'undefined' && arr instanceof Array ) ? arr : [];
}