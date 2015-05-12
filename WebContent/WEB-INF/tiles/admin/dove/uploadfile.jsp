<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="container">
	<div class="row">
		<div class="col-xs-12">
		<h2>Update Tower Database</h2>
			<c:if test="${not empty dangerMessage }">
				<div class="alert alert-danger" role="alert">
					<c:out value="${dangerMessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${not empty successMessage }">
				<div class="alert alert-success" role="alert">
					<c:out value="${successMessage}"></c:out>
				</div>
			</c:if>
		</div>
		<div class="col-xs-12">
			<div class="alert alert-danger" role="alert">
				Always update PKs <strong>BEFORE</strong> uploading the latest dove.txt file by
				uploading the latest version of newpks.txt from <a
					class="alert-link" href="http://dove.cccbr.org.uk/downloads.php">here.</a>
			</div>
			<div class="alert alert-info" role="alert">When you click
				upload the page will not respond for some time. This is absolutely
				normal, and when the file has been processed you will be notified.
				Please be patient and do not navigate away from this page.</div>

			<div class="col-xs-12 col-md-6">
				<h3>Upload newpks.txt</h3>
				<sf:form method="POST" commandName="file"
					enctype="multipart/form-data">

					<div class="form-group">
						<label for="file">Select your file:</label> <input type="file"
							name="file" class="form-control" id="file" />
					</div>
					<div class="alert alert-warning" role="alert">The filename must be newpks.txt</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Upload</button>
					</div>
					<sf:errors path="file" cssClass="alert alert-danger" element="div" />
				</sf:form>
			</div>
			<div class="col-xs-12 col-md-6">
				<h3>Upload dove.txt</h3>
				<sf:form method="POST" commandName="file"
					enctype="multipart/form-data">

					<div class="form-group">
						<label for="file">Select your file:</label> <input type="file"
							name="file" class="form-control" id="file" />
					</div>
					<div class="alert alert-warning" role="alert">The filename must be dove.txt</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Upload</button>
					</div>
					<sf:errors path="file" cssClass="alert alert-danger" element="div" />
				</sf:form>
			</div>
		</div>
	</div>

</div>