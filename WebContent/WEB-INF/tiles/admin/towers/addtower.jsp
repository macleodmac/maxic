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
						id="placeName2" placeholder="placeName2" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="placeNameCL">placeNameCL</label>
					<sf:input type="text" class="form-control" path="placeNameCL"
						id="placeNameCL" placeholder="placeNameCL" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label for="ringable">ringable</label>
					<sf:input type="text" class="form-control" path="ringable"
						id="ringable" placeholder="ringable" />
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label for="dedication">dedication</label>
					<sf:input type="text" class="form-control" path="dedication"
						id="dedication" placeholder="dedication" />
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
					<label for="gridReference">gridReference</label>
					<sf:input type="text" class="form-control" path="gridReference"
						id="gridReference" placeholder="gridReference" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="postCode">postCode</label>
					<sf:input type="text" class="form-control" path="postCode"
						id="postCode" placeholder="postCode" />
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
					<label for="guildId">guildId</label>
					<sf:input type="text" class="form-control" path="guildId"
						id="guildId" placeholder="guildId" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="affiliation">affiliation</label>
					<sf:input type="text" class="form-control" path="affiliation"
						id="affiliation" placeholder="affiliation" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="buildingId">buildingId</label>
					<sf:input type="text" class="form-control" path="buildingId"
						id="buildingId" placeholder="buildingId" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="listedGrade">listedGrade</label>
					<sf:input type="text" class="form-control" path="listedGrade"
						id="listedGrade" placeholder="listedGrade" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="groundFloorRing">groundFloorRing</label>
					<sf:input type="text" class="form-control" path="groundFloorRing"
						id="groundFloorRing" placeholder="groundFloorRing" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="simulator">simulator</label>
					<sf:input type="text" class="form-control" path="simulator"
						id="simulator" placeholder="simulator" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="toilet">toilet</label>
					<sf:input type="text" class="form-control" path="toilet"
						id="toilet" placeholder="toilet" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="towerCaptain">towerCaptain</label>
					<sf:input type="text" class="form-control" path="towerCaptain"
						id="towerCaptain" placeholder="towerCaptain" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label for="accessDetails">accessDetails</label>
					<sf:textarea style="resize: none;" rows="3" class="form-control"
						path="accessDetails" id="accessDetails"
						placeholder="accessDetails" />
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label for="extraInfo">extraInfo</label>
					<sf:textarea style="resize: none;" rows="3" class="form-control"
						path="extraInfo" id="extraInfo" placeholder="extraInfo" />
				</div>
			</div>
		</div>
	</sf:form>
</div>