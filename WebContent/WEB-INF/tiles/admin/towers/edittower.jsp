<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/static/js/edit.js"></script>
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
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</div>
			</div>
		</div>
		<div class="row">
			<hr />
			<div class="col-xs-12">
				<c:if test="${not empty message }">
					<div class="alert alert-success" role="alert">
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
						id="doveId" placeholder="Dove ID" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="towerbaseId">Towerbase ID</label>
					<sf:input type="text" class="form-control" path="tower.towerbaseId"
						id="towerbaseId" placeholder="Towerbase ID" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="placeName">Place Name</label>
					<sf:input type="text" class="form-control" path="tower.placeName"
						id="placeName" placeholder="Place Name" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="placeName2">Place Name 2</label>
					<sf:input type="text" class="form-control" path="tower.placeName2"
						id="placeName2" placeholder="Place Name 2" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="placeNameCL">Place Name (County Lists)</label>
					<sf:input type="text" class="form-control" path="tower.placeNameCL"
						id="placeNameCL" placeholder="Place Name (CL)" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label for="ringable">Ringable?</label>
					<sf:select class="form-control" path="tower.ringable" id="ringable"
						items="${yesno}" />
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label for="dedication">Dedication</label>
					<sf:input type="text" class="form-control" path="tower.dedication"
						id="dedication" placeholder="Dedication" />
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
						placeholder="SatNav Latitude" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="satNavLongitude">Sat Nav Longitude</label>
					<sf:input type="text" class="form-control"
						path="tower.satNavLongitude" id="satNavLongitude"
						placeholder="SatNav Longitude" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="gridReference">Grid Reference</label>
					<sf:input type="text" class="form-control"
						path="tower.gridReference" id="gridReference"
						placeholder="Grid Reference" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="postCode">Postcode</label>
					<sf:input type="text" class="form-control" path="tower.postCode"
						id="postCode" placeholder="Postcode" />
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
					<label for="guildId">Guild</label>
					<sf:input type="text" class="form-control" path="tower.guildId"
						id="guildId" placeholder="Guild" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="affiliation">Affiliation</label>
					<sf:input type="text" class="form-control" path="tower.affiliation"
						id="affiliation" placeholder="Affiliation" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="buildingId">Building ID</label>
					<sf:input type="text" class="form-control" path="tower.buildingId"
						id="buildingId" placeholder="Building ID" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="listedGrade">Listed Grade</label>
					<sf:input type="text" class="form-control" path="tower.listedGrade"
						id="listedGrade" placeholder="Listed Grade" />
				</div>
			</div>
		</div>
		<div class="row">
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
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="towerCaptain">Tower Captain</label>
					<sf:input type="text" class="form-control"
						path="tower.towerCaptain" id="towerCaptain"
						placeholder="Tower Captain" />
				</div>
			</div>
		</div>
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
			Practices <a class="btn btn-primary btn-sm"
				href="${pageContext.request.contextPath}/admin/towers/addpractice?t=${towerWrapper.tower.towerId}">Add</a>
		</h3>
		<c:if test="${empty towerWrapper.practiceList}">
			<div class="alert alert-info" role="alert">
				No practice details held, why not <a class="alert-link"
					href="${pageContext.request.contextPath}/admin/towers/addpractice?t=${towerWrapper.tower.towerId}">add
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
								id="practiceName${i.index + 1}" type="text" />

						</div>
					</div>
					<div class="col-xs-6 col-sm-2">
						<div class="form-group">
							<label for="practiceDay${i.index}">Day</label>
							<sf:select class="form-control"
								path="practiceList[${i.index}].day"
								id="practiceDay${i.index + 1}" items="${days}" />

						</div>
					</div>
					<div class="col-xs-6 col-sm-2">
						<div class="form-group">
							<label for="practiceTime${i.index}">Time</label>
							<sf:input class="form-control" id="practiceTime${i.index + 1}"
								path="practiceList[${i.index}].time" type="text"
								placeholder="Time" />
						</div>
					</div>
					<div class="col-xs-6 col-sm-2">
						<div class="form-group">
							<label for="practiceRegularity${i.index}">Regularity</label>
							<sf:input class="form-control"
								id="practiceRegularity${i.index + 1}"
								path="practiceList[${i.index}].regularity" type="text"
								placeholder="Regularity" />
						</div>
					</div>
					<div class="col-xs-6 col-sm-2">
						<div class="form-group">
							<label for="practiceVisitorsWelcome${i.index}">Visitors
								Welcome?</label>
							<sf:select class="form-control"
								path="practiceList[${i.index}].visitorsWelcome"
								id="practiceVisitorsWelcome${i.index + 1}" items="${yesno}" />

						</div>
					</div>
					<div class="col-xs-1">
						<div class="form-group">
							<label for="towerId"></label>
							<p class="form-control-static">
								<a class="btn btn-danger btn-sm"
									href="${pageContext.request.contextPath}/admin/towers/dodeletepractice?pr=${practiceDetail.practiceId}&t=${towerWrapper.tower.towerId}">X</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
		<h3>
			Contact Details <a class="btn btn-primary btn-sm"
				href="${pageContext.request.contextPath}/admin/towers/addcontact?t=${towerWrapper.tower.towerId}">Add</a>
		</h3>
		<c:if test="${empty towerWrapper.contactDetailsList}">
			<div class="alert alert-info" role="alert">
				No contact details held, why not <a class="alert-link"
					href="${pageContext.request.contextPath}/admin/towers/addcontact?t=${towerWrapper.tower.towerId}">add
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
							<sf:input class="form-control"
								path="contactDetailsList[${i.index}].type"
								id="contactType${i.index}" type="text" />
						</div>
					</div>
					<div class="col-xs-5 col-sm-3">
						<div class="form-group">
							<label for="contactDetail${i.index}">Detail</label>
							<sf:input class="form-control"
								path="contactDetailsList[${i.index}].detail"
								id="contactDetail${i.index}" type="text" rel="txtTooltip" title="Must be " data-toggle="tooltip" data-placement="top"/>
						</div>
					</div>
					<div class="col-xs-1 col-sm-1">
						<div class="form-group">
							<label for="towerId"></label>
							<p class="form-control-static">
								<a class="btn btn-danger btn-sm"
									href="${pageContext.request.contextPath}/admin/towers/dodeletecontact?c=${contactDetail.contactId}&t=${towerWrapper.tower.towerId}">X</a>
							</p>
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
		<div class="col-xs-12 text-right">
			<a class="btn btn-danger"
				href="${pageContext.request.contextPath}/admin/towers/dodelete?t=${towerWrapper.tower.towerId}">Delete
				Tower</a>
		</div>

	</div>
	<div class="row">
		<hr />
	</div>
</div>