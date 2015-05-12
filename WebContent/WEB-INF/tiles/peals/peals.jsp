<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<script
	src="http://cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js"></script>
<script
	src="http://cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<link rel="stylesheet"
	href="http://cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.css">
<script
	src="http://cdn.datatables.net/responsive/1.0.4/js/dataTables.responsive.js"></script>
<link rel="stylesheet"
	href="http://cdn.datatables.net/responsive/1.0.4/css/dataTables.responsive.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/datepicker.css">
<script
	src="${pageContext.request.contextPath}/static/js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
	//Plug-in to fetch page data 
	jQuery.fn.dataTableExt.oApi.fnPagingInfo = function(oSettings) {
		return {
			"iStart" : oSettings._iDisplayStart,
			"iEnd" : oSettings.fnDisplayEnd(),
			"iLength" : oSettings._iDisplayLength,
			"iTotal" : oSettings.fnRecordsTotal(),
			"iFilteredTotal" : oSettings.fnRecordsDisplay(),
			"iPage" : oSettings._iDisplayLength === -1 ? 0 : Math
					.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
			"iTotalPages" : oSettings._iDisplayLength === -1 ? 0 : Math
					.ceil(oSettings.fnRecordsDisplay()
							/ oSettings._iDisplayLength)
		};
	};

	$(document).ready(function() {

		var table = $("#towertable").DataTable({
			"bProcessing" : true,
			"bServerSide" : true,
			"sort" : false,
			"bFilter" : false,
			"bStateSave" : true,
			"iDisplayLength" : 10,
			"iDisplayStart" : 0,
			"ajax" : {
				"url" : "/towers/json/peals",
				"data" : function(d) {
					d.tower = $('#towerselect').val();
					d.dateTo = $('#dateTo').val();
					d.dateFrom = $('#dateFrom').val();
					d.ringer = $('#ringer').val();
				}
			},
			"columns" : [ {
				"mData" : "dateRung"
			}, {
				"mData" : "tower.de"
			}, {
				"mData" : "changes"
			}, {
				"mData" : "method"
			} ]
		});

		$('#towertable tbody').on('click', 'tr', function() {
			var data = table.row(this).data();
			var newPage = '/towers/';
			newPage += 'peals/view?p=' + data["pealId"];
			window.location.href = newPage;
		});

		$('.dTable tbody').on('mouseover', 'tr', function() {
			$(this).addClass('highlight');
		});

		$('.dTable tbody').on('mouseout', 'tr', function() {
			$(this).removeClass('highlight');
		});

		$('#towerselect').change(function() {
			table.ajax.reload();
		});

		var timer = null;
		$('#ringer').keydown(function() {
			clearTimeout(timer);
			timer = setTimeout(updateSearch, 1000)
		});

		function updateSearch() {
			table.ajax.reload();
		}
		
		var dFrom = $('#dateFrom').datepicker({
			format : 'dd-mm-yyyy',
			endDate : '0d',
			autoclose : true,
			onRender : function(date) {
				return date.valueOf() > now.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function(ev) {
			if ($('#dateFrom').val() > $('#dateTo').val()) {
			    var dateTo = $('#dateTo').val();
			    var dateParts = dateTo.split("-");
			    var date = new Date(dateParts[2], (dateParts[1] - 1), dateParts[0]);
			    var dateMonth;
			    if (date.getMonth() < 10) {
			    	dateMonth = '0'+(date.getMonth()+1);
			    } else {
			    	dateMonth = date.getMonth()+1;
			    }
			    var dateString = date.getDate()+'-'+dateMonth+'-'+date.getFullYear();
			    console.log('New date: ' +dateString);
			    console.log('Current date: ' + $('#dateFrom').val());
			    $('#dateFrom').val(dateString);
			  }
			table.ajax.reload();
		}).data('datepicker');

		var dTo = $('#dateTo').datepicker({
			format : 'dd-mm-yyyy',
			endDate : '0d',
			autoclose : true,
			onRender : function(date) {
				return date.valueOf() > now.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function(ev) {
			if ($('#dateFrom').val() > $('#dateTo').val()) {
			    var dateTo = $('#dateTo').val();
			    var dateParts = dateTo.split("-");
			    var date = new Date(dateParts[2], (dateParts[1] - 1), dateParts[0]);
			    var dateMonth;
			    console.log(dTo.valueOf());
			    if (date.getMonth() < 10) {
			    	dateMonth = '0'+(date.getMonth()+1);
			    } else {
			    	dateMonth = date.getMonth()+1;
			    }
			    var dateString = date.getDate()+'-'+dateMonth+'-'+date.getFullYear();
			    $('#dateFrom').val(dateString);
			  }
			table.ajax.reload();
		}).data('datepicker');
		

		$('#clearbtn').click(function() {
			$('#dateFrom').val("");
			$('#dateTo').val("");
			$('#towerselect').val("0");
			$('#ringer').val("");
			table.ajax.reload();
		});
		
		var tower = getUrlParameter('t');
		
		if (tower != null) {
			$('#towerselect').val(tower);
			table.ajax.reload();
		}

	});
	function getUrlParameter(sParam)
	{
	    var sPageURL = window.location.search.substring(1);
	    var sURLVariables = sPageURL.split('&');
	    for (var i = 0; i < sURLVariables.length; i++) 
	    {
	        var sParameterName = sURLVariables[i].split('=');
	        if (sParameterName[0] == sParam) 
	        {
	            return sParameterName[1];
	        }
	    }
	}          
</script>
<div class="container">
	<div class="row">
		<div class="col-xs-10 col-xs-offset-1">
			<h3>
				Latest Performances <a class="btn btn-primary btn-sm"
					data-toggle="collapse" href="#filter" aria-expanded="false"
					aria-controls="collapseExample"> Filter</a>
			</h3>

			<div class="collapse in" id="filter">
				<form class="form">
					<div class="col-xs-12 col-md-4">
						<div class="form-group" id="towerselectwrap">
							<label for="tower">Tower</label> <select name="tower"
								class="form-control" id="towerselect">
								<c:forEach items="${towers}" var="tower">
									<option value="${tower.key}">${tower.value}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-xs-6 col-md-2">

						<div class="form-group">
							<label for="dp1">Date From</label> <input type="text"
								class="form-control" id="dateFrom" placeholder="Date From">
						</div>
					</div>
					<div class="col-xs-6 col-md-2">
						<div class="form-group">
							<label for="dp2">Date To</label> <input type="text"
								class="form-control" id="dateTo" placeholder="Date To">
						</div>
					</div>
					<div class="col-xs-6 col-md-3">
						<div class="form-group">
							<label for="ringer">Ringer</label> <input type="text"
								class="form-control" id="ringer" placeholder="Ringer">
						</div>
					</div>
					<div class="col-xs-6 col-md-1">
						<div class="form-group">
							<label for="clearbtn"></label>
							<p class="form-control-static">
								<a class="btn btn-sm btn-primary" id="clearbtn">Clear</a>
							</p>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-10 col-sm-offset-1">
			<hr />
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-10 col-sm-offset-1">
			<div class="responsive">
				<sf:form action="" method="GET">
					<table id="towertable" class="display table hover dTable">
						<thead>
							<tr>
								<th>Date</th>
								<th>Tower</th>
								<th></th>
								<th>Method</th>
							</tr>
						</thead>
					</table>
				</sf:form>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-10 col-sm-offset-1">
			<hr />
			<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/peals/add">Add a Performance</a>
		</div>
	</div>
</div>
<br />