<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/admin/peals/doedit?p=${peal.pealId}"
		modelAttribute="peal" commandName="peal">

		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}/admin/peals">Back</a>
				</div>
			</div>
			<div class="col-xs-6 text-right">
				<div class="form-group">
					<button type="submit" class="btn btn-default">Save Changes</button>
				</div>
			</div>
		</div>

		<div class="row">
			<hr />
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="pealId">Peal ID</label>
					<p class="form-control-static">${peal.pealId}</p>
					<sf:input type="hidden" path="pealId" id="pealId"></sf:input>
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="towerId">towerId</label>
					<sf:input type="text" class="form-control" path="towerId"
						id="towerId" placeholder="Enter Tower ID" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="dedication">Dedication</label>
					<sf:input type="text" class="form-control" path="dedication"
						id="dedication" placeholder="dedication" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="dateRung">dateRung</label>
					<sf:input type="text" class="form-control" path="dateRung"
						id="dateRung" placeholder="dateRung" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="time">time</label>
					<sf:input type="text" class="form-control" path="time" id="time"
						placeholder="time" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="tenor">tenor</label>
					<sf:input type="text" class="form-control" path="tenor" id="tenor"
						placeholder="tenor" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label for="method">method</label>
					<sf:input type="text" class="form-control" path="method"
						id="method" placeholder="method" />
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label for="methodDetails">methodDetails</label>
					<sf:input type="text" class="form-control" path="methodDetails"
						id="methodDetails" placeholder="methodDetails" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="changes">changes</label>
					<sf:input type="text" class="form-control" path="changes"
						id="changes" placeholder="changes" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="leader">leader</label>
					<sf:input type="text" class="form-control" path="leader"
						id="leader" placeholder="leader" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="satNavLatitude">composer</label>
					<sf:input type="text" class="form-control" path="composer"
						id="composer" placeholder="composer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="footnotes">footnotes</label>
					<sf:input type="text" class="form-control" path="footnotes"
						id="footnotes" placeholder="footnotes" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="composition">composition</label>
					<sf:input type="text" class="form-control" path="composition"
						id="composition" placeholder="composition" />
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-xs-6">
			</div>
			<div class="col-xs-6 text-right">
				<a class="btn btn-danger"
					href="${pageContext.request.contextPath}/admin/peals/dodelete?p=${peal.pealId}">Delete</a>
			</div>
		</div>
	</sf:form>
</div>