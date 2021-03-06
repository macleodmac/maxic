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
jQuery.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
{
	return {
		"iStart":         oSettings._iDisplayStart,
		"iEnd":           oSettings.fnDisplayEnd(),
		"iLength":        oSettings._iDisplayLength,
		"iTotal":         oSettings.fnRecordsTotal(),
		"iFilteredTotal": oSettings.fnRecordsDisplay(),
		"iPage":          oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
		"iTotalPages":    oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
	};
};

$(document).ready(function() {

$("#towertable").dataTable( {
    "bProcessing": true,
    "bServerSide": true,
    "sort": false,
    //bStateSave variable you can use to save state on client cookies: set value "true" 
    "bStateSave": true,
    //Default: Page display length
    "iDisplayLength": 10,
    //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
    "iDisplayStart": 0,
    "fnDrawCallback": function () {
        //Get page numer on client. Please note: number start from 0 So
        //for the first page you will see 0 second page 1 third page 2...
        //Un-comment below alert to see page number
    	//alert("Current page number: "+this.fnPagingInfo().iPage);    
    },         
    "sAjaxSource": "/towers/admin/json/users",
    "aoColumns": [
		{ "mData": "id" },
        { "mData": "name" },
        { "mData": "email" },
        { "mData": "role" },
        { "mData": "enabled" },
        
    ],
    "columnDefs": [
    	{
    		"render": function (data, type, row) {
    			return '<a class="btn btn-xs btn-primary" href="${pageContext.request.contextPath}/admin/users/reset?u='+data+'">Reset</a> <a class="btn btn-xs btn-warning" href="${pageContext.request.contextPath}/admin/users/edit?u='+data+'">Edit</a> <div class="pull-right">'+data+'</div>';
    		},
    		"targets": 0
    	},
    	{ "visible": true, "targets": [ 0 ]}
    
    
    
    ]
    
} );

} );
</script>
<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<c:if test="${not empty message }">
				<div class="alert alert-info" role="alert">
					<c:out value="${message}"></c:out>
				</div>
			</c:if>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/admin/users/add">Add a
				User</a>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<hr />
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
		<div class="responsive">
<sf:form action="" method="GET">
			<table id="towertable"
				class="display table table-striped table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Role</th>
						<th>Enabled</th>
					</tr>
				</thead>				
			</table>
			</sf:form>
			</div>
		</div>
	</div>
</div>
<br />