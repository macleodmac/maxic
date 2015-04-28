<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="${pageContext.request.contextPath}/static/js/bootstrap-datepicker.js"></script>

<link
	href='${pageContext.request.contextPath}/static/css/datepicker.css'
	rel='stylesheet' type='text/css' />
<script>
	function onLoad() {
		$('#quarterPealRung').change(changeRung);
		$('#pealRung').change(changeRung);

		var nowTemp = new Date();
		var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp
				.getDate(), 0, 0, 0, 0);

		$('#dp').datepicker({
			format : 'yyyy-mm-dd',
			endDate : '0d',
			autoclose : true,
			onRender : function(date) {
				return date.valueOf() > now.valueOf() ? 'disabled' : '';
			}
		}).datepicker('top');
	}

	function changeRung() {
		var pealRung = $('#pealRung').val();
		var quarterPealRung = $('#quarterPealRung').val();
		if (pealRung === 'true' || quarterPealRung === 'true') {
			$('#rung').val('true');
			$('#rung').prop('disabled', true);
		} else {
			$('#rung').prop('disabled', false);
		}
	}

	$(document).ready(onLoad);
</script>

<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/account/visits/doadd"
		modelAttribute="visit" commandName="visit">

		<div class="row">
			<div class="col-xs-12 col-sm-3">
				<div class="form-group">
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}/account/visits">Back</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6">
				<c:if test="${not empty message }">
					<div class="alert alert-success" role="alert"
						style="margin-bottom: 0px;">
						<c:out value="${message}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty dangerMessage }">
					<div class="alert alert-danger" role="alert"
						style="margin-bottom: 0px;">
						<c:out value="${dangerMessage}"></c:out>
					</div>
				</c:if>
			</div>
			<div class="col-xs-12 col-sm-3 text-right">
				<div class="form-group">

					<button type="submit" class="btn btn-primary">Add Visit</button>
				</div>
			</div>
		</div>
		<div class="row">
			<hr />
		</div>
		<div class="row">
			<div
				class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
				<div class="col-xs-12">
					<div class="form-group">
						<label for="towerId">Tower</label>
						<sf:select class="form-control" path="tower.id" id="towerId"
							items="${towers}" />
						<sf:input type="hidden" path="visitId" />
						<sf:input type="hidden" path="userId" />
					</div>
				</div>
				<div class="col-xs-4">
					<div class="form-group">
						<label for="date">Rung?</label>
						<sf:select class="form-control" path="rung" id="rung"
							items="${yesno}" required="true" />
					</div>
				</div>
				<div class="col-xs-4">
					<div class="form-group">
						<label for="quarterPealRung">Quarter Peal Rung?</label>
						<sf:select class="form-control" path="quarterPealRung"
							id="quarterPealRung" items="${yesno}" required="true" />
					</div>
				</div>
				<div class="col-xs-4">
					<div class="form-group">
						<label for="pealRung">Peal Rung?</label>
						<sf:select class="form-control" path="pealRung" id="pealRung"
							items="${yesno}" required="true" />
					</div>
				</div>
				<div class="col-xs-12 col-sm-4">
					<div class="form-group">
						<label for="date">Date</label>
						<sf:input type="text" class="form-control" path="date" id="dp"
							placeholder="Date" />
					</div>
				</div>
				<div class="col-xs-12 col-sm-8">
					<div class="form-group">
						<label for="notes">Notes</label>
						<sf:textarea rows="1" class="form-control" path="notes" id="notes"
							placeholder="Notes" />
					</div>
				</div>
			</div>
		</div>
	</sf:form>
</div>