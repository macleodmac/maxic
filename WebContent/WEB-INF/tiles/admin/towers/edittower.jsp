<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sf:form method="post"
	action="${pageContext.request.contextPath}/admin/towers/doedit?t=${tower.towerId}"
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
				<label for="towerId">Tower ID</label>
				<sf:input type="text" class="form-control" path="towerId"
					id="towerId" placeholder="Enter Tower ID" disabled="disabled"></sf:input>
			</div>
		</div>
		<div class="col-xs-6 col-sm-4">
			<div class="form-group">
				<label for="doveId">Dove ID</label>
				<sf:input type="text" class="form-control" path="doveId" id="doveId"
					placeholder="Enter Dove ID" />
			</div>
		</div>
		<div class="col-xs-6 col-sm-4">
			<div class="form-group">
				<label for="towerbaseId">Towerbase ID</label>
				<sf:input type="text" class="form-control" path="towerbaseId"
					id="towerbaseId" placeholder="towerbaseId" />
			</div>
		</div>
		<div class="col-xs-6 col-sm-4">
			<div class="form-group">
				<label for="placeName">placeName</label>
				<sf:input type="text" class="form-control" path="placeName"
					id="placeName" placeholder="placeName" />
			</div>
		</div>
		<div class="col-xs-6 col-sm-4">
			<div class="form-group">
				<label for="placeName2">placeName2</label>
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
				<label for="countryId">countryId</label>
				<sf:input type="text" class="form-control" path="countryId"
					id="countryId" placeholder="countryId" />
			</div>
		</div>
		<div class="col-xs-6 col-sm-3">
			<div class="form-group">
				<label for="countyId">countyId</label>
				<sf:input type="text" class="form-control" path="countyId"
					id="countyId" placeholder="countyId" />
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
				<sf:input type="text" class="form-control" path="toilet" id="toilet"
					placeholder="toilet" />
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
					path="accessDetails" id="accessDetails" placeholder="accessDetails" />
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
<c:if test="${not empty contactMessage }">
	<div class="row">
		<div class="col-xs-6 col-sm-4">
			<div class="form-group">
				<label for="contactNumber">contactNumber</label>
				<sf:input type="text" class="form-control" path="contactNumber"
					id="contactNumber" placeholder="Enter contactNumber"></sf:input>
			</div>
		</div>
		<div class="col-xs-6 col-sm-4">
			<div class="form-group">
				<label for="email">Dove ID</label>
				<sf:input type="text" class="form-control" path="email" id="email"
					placeholder="Enter contactEmail" />
			</div>
		</div>
	</div>

</c:if>


<div class="row">
	<div class="col-xs-6">
		<div class="form-group">
			<a class="btn btn-default"
				href="${pageContext.request.contextPath}/admin/towers/edit?t=${tower.towerId}&c=1">Add
				Contact Details</a>
		</div>
	</div>
	<div class="col-xs-6 text-right">
		<a class="btn btn-danger"
			href="${pageContext.request.contextPath}/admin/towers/dodelete?t=${tower.towerId}">Delete</a>
	</div>
</div>