<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/admin/towers/doedit?t=${towerWrapper.tower.towerId}"
		modelAttribute="towerWrapper" commandName="towerWrapper">

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
			<div class="col-xs-12">
				<c:if test="${not empty message }">
					<div class="alert alert-info" role="alert">
						<c:out value="${message}"></c:out>
					</div>
				</c:if>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="towerId">Tower ID</label>
					<p class="form-control-static">${towerWrapper.tower.towerId}</p>
					<sf:input type="hidden" path="tower.towerId" id="towerId" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="doveId">Dove ID</label>
					<sf:input type="text" class="form-control" path="tower.doveId"
						id="doveId" placeholder="Enter Dove ID" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="towerbaseId">Towerbase ID</label>
					<sf:input type="text" class="form-control" path="tower.towerbaseId"
						id="towerbaseId" placeholder="towerbaseId" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="placeName">placeName</label>
					<sf:input type="text" class="form-control" path="tower.placeName"
						id="placeName" placeholder="placeName" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="placeName2">placeName2</label>
					<sf:input type="text" class="form-control" path="tower.placeName2"
						id="placeName2" placeholder="placeName2" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="placeNameCL">placeNameCL</label>
					<sf:input type="text" class="form-control" path="tower.placeNameCL"
						id="placeNameCL" placeholder="placeNameCL" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label for="ringable">ringable</label>
					<sf:input type="text" class="form-control" path="tower.ringable"
						id="ringable" placeholder="ringable" />
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label for="dedication">dedication</label>
					<sf:input type="text" class="form-control" path="tower.dedication"
						id="dedication" placeholder="dedication" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="latitude">Latitude</label>
					<sf:input type="text" class="form-control" path="tower.latitude"
						id="latitude" placeholder="Latitude" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="longitude">Longitude</label>
					<sf:input type="text" class="form-control" path="tower.longitude"
						id="longitude" placeholder="Longitude" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="satNavLatitude">Sat Nav Latitude</label>
					<sf:input type="text" class="form-control"
						path="tower.satNavLatitude" id="satNavLatitude"
						placeholder="Sat Nav Latitude" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="satNavLongitude">Sat Nav Longitude</label>
					<sf:input type="text" class="form-control"
						path="tower.satNavLongitude" id="satNavLongitude"
						placeholder="satNavLongitude" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="gridReference">gridReference</label>
					<sf:input type="text" class="form-control"
						path="tower.gridReference" id="gridReference"
						placeholder="gridReference" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="postCode">postCode</label>
					<sf:input type="text" class="form-control" path="tower.postCode"
						id="postCode" placeholder="postCode" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="isoCode">Country</label>
					<sf:select class="form-control" path="tower.country.isoCode"
						id="isoCode" items="${countries}" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="dioceseId">Diocese</label>
					<sf:select class="form-control" path="tower.diocese.dioceseId"
						id="dioceseId" items="${dioceses}" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="guildId">guildId</label>
					<sf:input type="text" class="form-control" path="tower.guildId"
						id="guildId" placeholder="guildId" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="affiliation">affiliation</label>
					<sf:input type="text" class="form-control" path="tower.affiliation"
						id="affiliation" placeholder="affiliation" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="buildingId">buildingId</label>
					<sf:input type="text" class="form-control" path="tower.buildingId"
						id="buildingId" placeholder="buildingId" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="listedGrade">listedGrade</label>
					<sf:input type="text" class="form-control" path="tower.listedGrade"
						id="listedGrade" placeholder="listedGrade" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="groundFloorRing">groundFloorRing</label>
					<sf:input type="text" class="form-control"
						path="tower.groundFloorRing" id="groundFloorRing"
						placeholder="groundFloorRing" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="simulator">simulator</label>
					<sf:input type="text" class="form-control" path="tower.simulator"
						id="simulator" placeholder="simulator" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="toilet">toilet</label>
					<sf:input type="text" class="form-control" path="tower.toilet"
						id="toilet" placeholder="toilet" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="towerCaptain">towerCaptain</label>
					<sf:input type="text" class="form-control"
						path="tower.towerCaptain" id="towerCaptain"
						placeholder="towerCaptain" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label for="accessDetails">accessDetails</label>
					<sf:textarea style="resize: none;" rows="3" class="form-control"
						path="tower.accessDetails" id="accessDetails"
						placeholder="accessDetails" />
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label for="extraInfo">extraInfo</label>
					<sf:textarea style="resize: none;" rows="3" class="form-control"
						path="tower.extraInfo" id="extraInfo" placeholder="extraInfo" />
				</div>
			</div>
		</div>
		<c:if test="${not empty towerWrapper.practiceList}">
			<h3>Practices</h3>
			<c:forEach items="${towerWrapper.practiceList}" varStatus="i">
				<sf:input type="hidden" path="practiceList[${i.index}].practiceId"
					id="practiceId" />
				<sf:input type="hidden" path="practiceList[${i.index}].towerId"
					id="towerId" />
				<div class="row">
					<div class="col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="practiceDay${i.index}">Day</label>
							<sf:input class="form-control"
								path="practiceList[${i.index}].day"
								id="practiceDay${i.index + 1}" type="text" />

						</div>
					</div>
					<div class="col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="practiceTime${i.index}">Time</label>
							<sf:input class="form-control" id="practiceTime${i.index + 1}"
								path="practiceList[${i.index}].time" type="text"
								placeholder="Time" />
						</div>
					</div>
					<div class="col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="practiceRegularity${i.index}">Regularity</label>
							<sf:input class="form-control"
								id="practiceRegularity${i.index + 1}"
								path="practiceList[${i.index}].regularity" type="text"
								placeholder="Regularity" />
						</div>
					</div>
					<div class="col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="practiceVisitorsWelcome${i.index}">Visitors
								Welcome?</label>
							<sf:input class="form-control"
								path="practiceList[${i.index}].visitorsWelcome"
								id="practiceVisitorsWelcome${i.index + 1}" type="text" />

						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>




	</sf:form>

	<div class="row">
		<hr />
	</div>

	<div class="row">
		<div class="col-xs-3">
			<div class="form-group">
				<a class="btn btn-default disabled"
					href="${pageContext.request.contextPath}/admin/towers/addcontact?t=${towerWrapper.tower.towerId}&c=1">Add
					Contact Details</a>
			</div>
		</div>
		<div class="col-xs-3">
			<div class="form-group">
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}/admin/towers/addpractice?t=${towerWrapper.tower.towerId}">Add
					Practice</a>
			</div>
		</div>
		<div class="col-xs-6 text-right">
			<a class="btn btn-danger"
				href="${pageContext.request.contextPath}/admin/towers/dodelete?t=${towerWrapper.tower.towerId}">Delete
				Tower</a>
		</div>
	</div>
</div>