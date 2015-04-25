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
				}
			},
			"columns" : [ {
				"mData" : "dateRung"
			}, {
				"mData" : "towerId"
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

		$('#dateFrom').datepicker({
			format : 'dd-mm-yyyy'
		});

		$('#dateFrom').on('changeDate', function() {
			table.ajax.reload();
		});

		$('#dateTo').datepicker({
			format : 'dd-mm-yyyy'
		});

		$('#dateTo').on('changeDate', function() {
			table.ajax.reload();
		});
		
		$('#clearbtn').click(function() {
			$('#dateFrom').val("");
			$('#dateTo').val("");
			$('#towerselect').val("0");
			table.ajax.reload();
		});

	});
	
	
	
</script>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-10 col-sm-offset-1">
			<form class="form-inline">
				<div class="form-group" id="towerselectwrap">
					<label for="tower">Tower</label> <select name="tower"
						class="form-control" id="towerselect">
						<c:forEach items="${towers}" var="tower">
							<option value="${tower.key}">${tower.value}</option>
						</c:forEach>
					</select>
				</div>

				<div class="form-group">
					<label for="dp1">From</label> <input type="text"
						class="form-control" id="dateFrom" placeholder="Date From">
				</div>
				<div class="form-group">
					<label for="dp2">to</label> <input type="text" class="form-control"
						id="dateTo" placeholder="Date To">
				</div>
				<a class="btn btn-default" id="clearbtn">Clear
					Filters</a>
			</form>
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
								<th>Method</th>
							</tr>
						</thead>
					</table>
				</sf:form>
			</div>
		</div>
	</div>
	<div class="row">

		<a class="btn btn-primary"
			href="${pageContext.request.contextPath}/peals/add">Add a Peal</a>
	</div>
</div>
<br />