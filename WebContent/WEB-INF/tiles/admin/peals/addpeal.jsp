<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="${pageContext.request.contextPath}/static/js/bootstrap-datepicker.js"></script>

<link
	href='${pageContext.request.contextPath}/static/css/datepicker.css'
	rel='stylesheet' type='text/css' />


<div class="container">

	
	<sf:form method="post"
		action="${pageContext.request.contextPath}/admin/peals/doadd"
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
					<button type="submit" class="btn btn-default">Add Peal</button>
				</div>
			</div>
		</div>

		<div class="row">
			<hr />
			<c:if test="${not empty message }">
				<div class="alert alert-danger" role="alert">
					<c:out value="${message}"></c:out>
				</div>
			</c:if>
			<sf:errors path="dateRung" cssClass="alert alert-danger" element="div" />
			<sf:errors path="method" cssClass="alert alert-danger" element="div" />
			<sf:errors path="ringer1" cssClass="alert alert-danger" element="div" />
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-9">
				<div class="form-group">
					<label for="towerId">Tower</label>
					<sf:select class="form-control" path="towerId" id="towerId"
						items="${towers}" />
				</div>
			</div>
			<div class="col-xs-12 col-sm-3">
				<div class="form-group">
					<label for="dateRung">Date</label>
					<sf:input type="text" class="form-control" path="dateRung" id="dp"
						placeholder="Date"/>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6">
				<div class="form-group">
					<label for="dedication">Dedication (or address)</label>
					<sf:input type="text" class="form-control" path="dedication"
						id="dedication" placeholder="Dedication (or address)" />
				</div>
			</div>

			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="time">Duration</label>
					<sf:input type="text" class="form-control" path="time" id="time"
						placeholder="Duration" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="tenor">Tenor</label>
					<sf:input type="text" class="form-control" path="tenor" id="tenor"
						placeholder="Tenor" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-4 col-sm-3">
				<div class="form-group">
					<label for="changes">Changes</label>
					<sf:input type="text" class="form-control" path="changes"
						id="changes" placeholder="Changes" />
				</div>
			</div>
			<div class="col-xs-8 col-sm-9">
				<div class="form-group">
					<label for="method">Method</label>
					<sf:input type="text" class="form-control" path="method"
						id="method" placeholder="Method" />
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-group">
					<label for="methodDetails">Method Details</label>
					<sf:textarea style="resize: none;" rows="2" class="form-control"
						path="methodDetails" id="methodDetails"
						placeholder="Method Details" />
				</div>
			</div>
		</div>
		<h4>Ringers</h4>
		<div class="row">
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer1">1 (1-2)</label>
					<sf:input type="text" class="form-control" path="ringer1"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer2">2 (3-4)</label>
					<sf:input type="text" class="form-control" path="ringer2"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer3">3 (5-6)</label>
					<sf:input type="text" class="form-control" path="ringer3"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer4">4 (7-8)</label>
					<sf:input type="text" class="form-control" path="ringer4"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer5">5 (9-10)</label>
					<sf:input type="text" class="form-control" path="ringer5"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer6">6 (11-12)</label>
					<sf:input type="text" class="form-control" path="ringer6"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer7">7 (13-14)</label>
					<sf:input type="text" class="form-control" path="ringer7"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer8">8 (15-16)</label>
					<sf:input type="text" class="form-control" path="ringer8"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer9">9</label>
					<sf:input type="text" class="form-control" path="ringer9"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer10">10</label>
					<sf:input type="text" class="form-control" path="ringer10"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer11">11</label>
					<sf:input type="text" class="form-control" path="ringer11"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer12">12</label>
					<sf:input type="text" class="form-control" path="ringer12"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer13">13</label>
					<sf:input type="text" class="form-control" path="ringer13"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer14">14</label>
					<sf:input type="text" class="form-control" path="ringer14"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer15">15</label>
					<sf:input type="text" class="form-control" path="ringer15"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="ringer16">16</label>
					<sf:input type="text" class="form-control" path="ringer16"
						id="ringer" />
				</div>
			</div>
			<div class="col-xs-12 col-sm-6">
				<div class="form-group">
					<label for="footnotes">Footnotes</label>
					<sf:textarea style="resize: none;" rows="3" class="form-control"
						path="footnotes" id="footnotes" placeholder="Footnotes" />
				</div>
			</div>
			<div class="col-xs-12 col-sm-6">
				<div class="form-group">
					<label for="composition">Composition</label>
					<sf:textarea style="resize: none;" rows="3" class="form-control"
						path="composition" id="composition" placeholder="Composition" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="leader">Leader</label>
					<sf:input type="text" class="form-control" path="leader"
						id="leader" placeholder="Leader" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="satNavLatitude">Composer</label>
					<sf:input type="text" class="form-control" path="composer"
						id="composer" placeholder="Composer" />
				</div>
			</div>

		</div>
	</sf:form>
<script>
$(document).ready(function(){
    
	var nowTemp = new Date();
    var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
	
	$('#dp').datepicker({
        format: 'yyyy-mm-dd',
        endDate: '0d',
        autoclose: true,
        onRender: function(date) {
            return date.valueOf() > now.valueOf() ? 'disabled' : '';
        }
    });
   
});

</script>
</div>