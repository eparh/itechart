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

function Pager(tableName, itemsPerPage) {
    this.currentPage = 1;
    this.pages = 0;

    this.showRecords = function(from, to) {
        var rows = document.getElementById(tableName).rows;
        // i starts from 1 to skip table header row
        for (var i = 1; i < rows.length; i++) {
            if (i < from || i > to)
                rows[i].style.display = 'none';
            else
                rows[i].style.display = '';
        }
    }

    this.showPage = function(pageNumber) {
        this.currentPage = pageNumber;

        var from = (pageNumber - 1) * itemsPerPage + 1;
        var to = from + itemsPerPage - 1;
        this.showRecords(from, to);
    }

    this.prev = function() {
        if (this.currentPage > 1) {
            this.showPage(this.currentPage - 1);
        }
    }

    this.next = function() {
        if (this.currentPage < this.pages) {
            this.showPage(this.currentPage + 1);
        }
    }

    this.init = function() {
        var rows = document.getElementById(tableName).rows;
        var records = (rows.length - 1);
        this.pages = Math.ceil(records / itemsPerPage);
    }

    this.showPageNav = function(pagerName, positionId) {
        var element = document.getElementById(positionId);
        var pagerHtml = '<li onclick="pager.prev()"><span class="glyphicon glyphicon-menu-left"></span></li>';
        for (var page = 1; page <= this.pages; page++) {
            pagerHtml += '<li onclick="pager.showPage(' + page + ')"><span>' + page + '</span></li>';
        }
        pagerHtml += '<li onclick="pager.next()"><span class="glyphicon glyphicon-menu-right"></span></li>';
        element.innerHTML = pagerHtml;
    }
}
var pager = new Pager('results', 1);
pager.init();
pager.showPageNav('pager', 'pageNavPosition');
pager.showPage(1);
