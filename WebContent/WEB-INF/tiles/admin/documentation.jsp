<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<div id="content"></div>
 
<script>
var json = (function () {
    var json = null;
    $.ajax({
        'async': false,
        'global': false,
        'url': '../gettowers',
        'dataType': "json",
        'success': function (data) {
            json = data;
        }
    });
    return json;
})(); 
<!-- json.towers.length -->
for ( var i = 0; i < 100; i++) {
	var obj = json.towers[i];
	document.getElementById('content').innerHTML += json.towers[i]["t"] + ' ' + json.towers[i]["la"] + ' ' + json.towers[i]["lo"]+ '<br/>';
	}
</script>