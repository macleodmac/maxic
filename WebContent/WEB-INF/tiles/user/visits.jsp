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

		var table = $("#towertable").dataTable({
			"bProcessing" : true,
			"bServerSide" : true,
			"sort" : false,
			"bFilter" : false,
			"bStateSave" : true,
			"iDisplayLength" : 10,
			"iDisplayStart" : 0,
			"sAjaxSource" : "/towers/json/visits",
			"columns" : [ {
				"mData" : "date"
			}, {
				"mData" : "tower.de"
			}, {
				"mData" : "rung", width: "10%"
			}, {
				"mData" : "quarterPealRung", width: "10%"
			}, {
				"mData" : "pealRung", width: "10%"
			}, {
				"mData" : "visitId", width: "10%"
			} ],
			"columnDefs": [ {
			      "targets": [2,3,4],
			      "render": function ( data, type, full, meta ) {
			          if (data === true) {
			        	  return "Yes";
			          } else {
			        	  return "No";
			          }
			        }
			    }, {
			    	"targets": [2,3,5],
				    "render": function ( data, type, full, meta ) {
				          return '<a class="btn btn-xs btn-primary" href="${pageContext.request.contextPath}/account/visits/view?v='+data +'">View</a> <a class="btn btn-xs btn-danger" href="${pageContext.request.contextPath}/account/visits/delete?v='+data +'">X</a>';
				    }
			    } ]
		});

		$('#towertable tbody').on('click', 'tr', function() {
			var data = table.fnGetData(this);
			var newPage = '/towers/';
			newPage += 'towers/view?t=' + data["id"];
			window.location.href = newPage;
		});

		$('.dTable tbody').on('mouseover', 'tr', function() {
			$(this).addClass('highlight');
		});

		$('.dTable tbody').on('mouseout', 'tr', function() {
			$(this).removeClass('highlight');
		});

	});
</script>
<div class="container">

	<div class="row">

		<div class="col-xs-12 col-sm-10 col-sm-offset-1">
			<h3>My Visits <div class="pull-right"><a class="btn btn-default"
				href="${pageContext.request.contextPath}/account/visits/download">Download CSV</a></div></h3>
			
			<hr />
			<div class="responsive">
				<sf:form action="" method="GET">
					<table id="towertable" class="display table hover dTable">
						<thead>
							<tr>
								<th>Date</th>
								<th>Tower</th>
								<th>Rung</th>
								<th>Quarter</th>
								<th>Peal</th>
								<th></th>
							</tr>
						</thead>
					</table>
				</sf:form>
			</div>
		</div>
	</div>
	<br />
	<div class="row">
		<div class="col-xs-12 col-sm-10 col-sm-offset-1">
			<a class="btn btn-default"
				href="${pageContext.request.contextPath}/account/visits/add?t=0">Add
				a Visit</a>
		</div>
	</div>
</div>