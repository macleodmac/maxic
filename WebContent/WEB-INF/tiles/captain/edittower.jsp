<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/static/js/edit.js"></script>

<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/captain/doedit"
		modelAttribute="towerWrapper" commandName="towerWrapper">
		<div class="row">
			<div class="col-xs-12 col-sm-3">
				<div class="form-group">
					<a class="btn btn-info"
						href="${pageContext.request.contextPath}/captain/view">View
						Tower</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6">
				<c:if test="${not empty message }">
					<div class="alert alert-success" role="alert" style="margin-bottom: 0px;">
						<c:out value="${message}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty dangerMessage }">
					<div class="alert alert-danger" role="alert" style="margin-bottom: 0px;">
						<c:out value="${dangerMessage}"></c:out>
					</div>
				</c:if>
			</div>
			<div class="col-xs-12 col-sm-3 text-right">
				<div class="form-group">

					<button type="submit" class="btn btn-primary">Save Changes</button>
				</div>
			</div>
		</div>
		<div class="row">
			<hr />
		</div>

		<div class="row">
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="towerId">Tower ID</label>
					<p class="form-control-static">${towerWrapper.tower.towerId}</p>
					<sf:input type="hidden" path="tower.towerId" id="towerId" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="doveId">Dove ID</label>
					<p class="form-control-static">${towerWrapper.tower.doveId}</p>
					<sf:input type="hidden" path="tower.doveId" id="doveId" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="towerbaseId">Towerbase ID</label>
					<p class="form-control-static">${towerWrapper.tower.towerbaseId}</p>
					<sf:input type="hidden" path="tower.towerbaseId" id="towerbaseId" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="placeName">Place Name</label>
					<p class="form-control-static">${towerWrapper.tower.placeName}</p>
					<sf:input type="hidden" path="tower.placeName" id="placeName" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="placeName2">Place Name 2</label>
					<p class="form-control-static">${towerWrapper.tower.placeName2}</p>
					<sf:input type="hidden" path="tower.placeName2" id="placeName2" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="placeNameCL">Place Name (CL)</label>
					<p class="form-control-static">${towerWrapper.tower.placeNameCL}</p>
					<sf:input type="hidden" path="tower.placeNameCL" id="placeNameCL" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="latitude">Latitude</label>
					<p class="form-control-static">${towerWrapper.tower.latitude}</p>
					<sf:input type="hidden" path="tower.latitude" id="latitude" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="longitude">Longitude</label>
					<p class="form-control-static">${towerWrapper.tower.longitude}</p>
					<sf:input type="hidden" path="tower.longitude" id="longitude" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="satNavLatitude">SatNav Latitude</label>
					<p class="form-control-static">${towerWrapper.tower.satNavLatitude}</p>
					<sf:input type="hidden" path="tower.satNavLatitude"
						id="satNavLatitude" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="satNavLongitude">SatNav Longitude</label>
					<p class="form-control-static">${towerWrapper.tower.satNavLongitude}</p>
					<sf:input type="hidden" path="tower.satNavLongitude"
						id="satNavLongitude" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="gridReference">Grid Reference</label>
					<p class="form-control-static">${towerWrapper.tower.gridReference}</p>
					<sf:input type="hidden" path="tower.gridReference"
						id="gridReference" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="postCode">Postcode</label>
					<p class="form-control-static">${towerWrapper.tower.postCode}</p>
					<sf:input type="hidden" path="tower.postCode" id="postCode" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="dedication">Dedication</label>
					<p class="form-control-static">${towerWrapper.tower.dedication}</p>
					<sf:input type="hidden" path="tower.dedication" id="dedication" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="affiliation">Affiliation</label>
					<p class="form-control-static">${towerWrapper.tower.affiliation}</p>
					<sf:input type="hidden" path="tower.affiliation" id="affiliation" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="isoCode">Country</label>
					<p class="form-control-static">${towerWrapper.tower.country.name}</p>
					<sf:input type="hidden" path="tower.country.isoCode" id="isoCode" />
					<sf:input type="hidden" path="tower.country.name" id="name" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="dioceseId">Diocese</label>
					<p class="form-control-static">${towerWrapper.tower.diocese.name}</p>
					<sf:input type="hidden" path="tower.diocese.dioceseId"
						id="dioceseId" />
					<sf:input type="hidden" path="tower.diocese.name" id="dioceseId" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="buildingId">Building ID</label>
					<p class="form-control-static">${towerWrapper.tower.buildingId}</p>
					<sf:input type="hidden" path="tower.buildingId" id="buildingId" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-2">
				<div class="form-group">
					<label for="listedGrade">Listed Grade</label>
					<p class="form-control-static">${towerWrapper.tower.listedGrade}</p>
					<sf:input type="hidden" path="tower.listedGrade" id="listedGrade" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringable">Ringable?</label>
					<sf:select class="form-control" path="tower.ringable" id="ringable"
						items="${yesno}" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="groundFloorRing">Ground Floor Ring?</label>
					<sf:select class="form-control" path="tower.groundFloorRing"
						id="groundFloorRing" items="${yesno}" />

				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="simulator">Simulator?</label>
					<sf:select class="form-control" path="tower.simulator"
						id="simulator" items="${yesno}" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="toilet">Toilet</label>
					<sf:select class="form-control" path="tower.toilet" id="toilet"
						items="${yesno}" />
				</div>
			</div>

		</div>
		<div class="row"></div>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label for="accessDetails">Access Details</label>
					<sf:textarea style="resize: none;" rows="3" class="form-control"
						path="tower.accessDetails" id="accessDetails"
						placeholder="Access Details" />
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label for="extraInfo">Additional Information</label>
					<sf:textarea style="resize: none;" rows="3" class="form-control"
						path="tower.extraInfo" id="extraInfo"
						placeholder="Additional Information" />
				</div>
			</div>
		</div>
		<h3>
			Practices <a class="btn btn-default btn-sm"
				href="${pageContext.request.contextPath}/captain/addpractice">Add</a>
		</h3>
		<c:if test="${empty towerWrapper.practiceList}">
			<div class="alert alert-info" role="alert">
				No practice details held, why not <a class="alert-link"
					href="${pageContext.request.contextPath}/captain/addpractice">add
					a practice?</a>
			</div>
		</c:if>
		<c:if test="${not empty towerWrapper.practiceList}">
			<c:forEach var="practiceDetail" items="${towerWrapper.practiceList}"
				varStatus="i">
				<sf:input type="hidden" path="practiceList[${i.index}].practiceId"
					id="practiceId" />
				<sf:input type="hidden" path="practiceList[${i.index}].towerId"
					id="towerId" />
				<div class="row">
					<div class="col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="practiceName${i.index}">Practice Name</label>
							<sf:input class="form-control"
								path="practiceList[${i.index}].practiceName"
								id="practiceName${i.index + 1}" type="text" required="true" />
						</div>
					</div>
					<div class="col-xs-6 col-sm-2">
						<div class="form-group">
							<label for="practiceDay${i.index}">Day</label>
							<sf:select class="form-control"
								path="practiceList[${i.index}].day"
								id="practiceDay${i.index + 1}" items="${days}" required="true" />
						</div>
					</div>
					<div class="col-xs-6 col-sm-2">
						<div class="form-group">
							<label for="practiceTime${i.index}">Time</label>
							<sf:input class="form-control" id="practiceTime${i.index + 1}"
								path="practiceList[${i.index}].time" type="text"
								placeholder="Time" required="true" />
						</div>
					</div>
					<div class="col-xs-6 col-sm-2">
						<div class="form-group">
							<label for="practiceRegularity${i.index}">Regularity</label>
							<sf:input class="form-control"
								id="practiceRegularity${i.index + 1}"
								path="practiceList[${i.index}].regularity" type="text"
								placeholder="Regularity" required="true" />
						</div>
					</div>
					<div class="col-xs-6 col-sm-2">
						<div class="form-group">
							<label for="practiceVisitorsWelcome${i.index}">Visitors
								Welcome?</label>
							<sf:select class="form-control"
								path="practiceList[${i.index}].visitorsWelcome"
								id="practiceVisitorsWelcome${i.index + 1}" items="${yesno}" required="true" />

						</div>
					</div>
					<div class="col-xs-1">
						<div class="form-group">
							<label for="towerId"></label>
							<p class="form-control-static">
								<a class="btn btn-danger btn-sm" onClick="return checkPractice()"
									href="${pageContext.request.contextPath}/captain/deletepractice?pr=${practiceDetail.practiceId}">X</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
		<h3>
			Contact Details <a class="btn btn-default btn-sm"
				href="${pageContext.request.contextPath}/captain/addcontact">Add</a>
		</h3>
		<c:if test="${empty towerWrapper.contactDetailsList}">
			<div class="alert alert-info" role="alert">
				No contact details held, why not <a class="alert-link"
					href="${pageContext.request.contextPath}/captain/addcontact">add
					some contact details?</a>
			</div>
		</c:if>
		<c:if test="${not empty towerWrapper.contactDetailsList}">
			<c:forEach var="contactDetail"
				items="${towerWrapper.contactDetailsList}" varStatus="i">
				<sf:input type="hidden"
					path="contactDetailsList[${i.index}].contactId" id="contactId" />
				<sf:input type="hidden"
					path="contactDetailsList[${i.index}].towerId" id="towerId" />
				<div class="row">
					<div class="col-xs-5 col-sm-3">
						<div class="form-group">
							<label for="contactType${i.index}">Type</label>
							<sf:select class="form-control"
								path="contactDetailsList[${i.index}].type"
								id="contactType${i.index}" items="${contactTypes}" required="true" />
						</div>
					</div>
					<div class="col-xs-5 col-sm-3">
						<div class="form-group">
							<label for="contactDetail${i.index}">Detail</label>
							<sf:input class="form-control"
								path="contactDetailsList[${i.index}].detail"
								id="contactDetail${i.index}" type="text" required="true" />
						</div>
					</div>
					<div class="col-xs-1 col-sm-1">
						<div class="form-group">
							<label for="towerId"></label>
							<p class="form-control-static">
								<a class="btn btn-danger btn-sm" onClick="return checkContact()"
									href="${pageContext.request.contextPath}/captain/deletecontact?c=${contactDetail.contactId}">X</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</sf:form>
</div>