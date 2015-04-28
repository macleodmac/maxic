<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/admin/towers/doadd"
		modelAttribute="tower" commandName="tower">

		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}/admin/towers">Back</a>
				</div>
			</div>
			<div class="col-xs-6 text-right">
				<div class="form-group">
					<button type="submit" class="btn btn-default">Add Tower</button>
				</div>
			</div>
		</div>

		<div class="row">
			<hr />
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="doveId">Dove ID</label>
					<sf:input type="text" class="form-control" path="doveId"
						id="doveId" placeholder="Enter Dove ID" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="towerbaseId">Towerbase ID</label>
					<sf:input type="text" class="form-control" path="towerbaseId"
						id="towerbaseId" placeholder="Enter TowerBase ID" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="placeName">Place Name</label>
					<sf:input type="text" class="form-control" path="placeName"
						id="placeName" placeholder="e.g. Sheffield" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="placeName2">Place Name 2</label>
					<sf:input type="text" class="form-control" path="placeName2"
						id="placeName2" placeholder="Place Name" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="placeNameCL">Place Name (CL)</label>
					<sf:input type="text" class="form-control" path="placeNameCL"
						id="placeNameCL" placeholder="Place Name (County Lists)" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="dedication">Dedication</label>
					<sf:input type="text" class="form-control" path="dedication"
						id="dedication" placeholder="e.g. S Mary" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="latitude">Latitude</label>
					<sf:input type="text" class="form-control" path="latitude"
						id="latitude" placeholder="Latitude" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="longitude">Longitude</label>
					<sf:input type="text" class="form-control" path="longitude"
						id="longitude" placeholder="Longitude" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="satNavLatitude">Sat Nav Latitude</label>
					<sf:input type="text" class="form-control" path="satNavLatitude"
						id="satNavLatitude" placeholder="Sat Nav Latitude" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="satNavLongitude">Sat Nav Longitude</label>
					<sf:input type="text" class="form-control" path="satNavLongitude"
						id="satNavLongitude" placeholder="satNavLongitude" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="gridReference">Grid Reference</label>
					<sf:input type="text" class="form-control" path="gridReference"
						id="gridReference" placeholder="Grid Reference" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="postCode">Postcode</label>
					<sf:input type="text" class="form-control" path="postCode"
						id="postCode" placeholder="Postcode" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="country.isoCode">Country</label>
					<sf:select class="form-control" path="country.isoCode"
						id="country.isoCode" items="${countries}" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="diocese.dioceseId">Diocese</label>
					<sf:select class="form-control" path="diocese.dioceseId"
						id="diocese.dioceseId" items="${dioceses}" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="affiliation">Affiliation</label>
					<sf:input type="text" class="form-control" path="affiliation"
						id="affiliation" placeholder="Affiliation" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="listedGrade">Listed Grade</label>
					<sf:input type="text" class="form-control" path="listedGrade"
						id="listedGrade" placeholder="Listed Grade" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringable">Ringable?</label>
					<sf:select class="form-control" path="ringable"
						id="ringable" items="${yesno}" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="groundFloorRing">Ground Floor Ring?</label>
					<sf:select class="form-control" path="groundFloorRing"
						id="groundFloorRing" items="${yesno}" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="simulator">Simulator?</label>
					<sf:select class="form-control" path="simulator"
						id="simulator" items="${yesno}" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="toilet">Toilet?</label>
					<sf:select class="form-control" path="toilet"
						id="toilet" items="${yesno}" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label for="accessDetails">Access Details</label>
					<sf:textarea style="resize: none;" rows="3" class="form-control"
						path="accessDetails" id="accessDetails"
						placeholder="Access Details" />
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label for="extraInfo">Extra Information</label>
					<sf:textarea style="resize: none;" rows="3" class="form-control"
						path="extraInfo" id="extraInfo" placeholder="Extra Information" />
				</div>
			</div>
		</div>
	</sf:form>
</div>