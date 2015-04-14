<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<h1>Donations</h1>
	<div class="row">
			<div class="col-xs-4">
				<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
					<h5>Please Enter Amount to Donate</h5>
					<hr />
					<input type="hidden" name="cmd" value="_donations"> <input
						type="hidden" name="item_name" value="Donation"> <input
						type="hidden" name="business" value="innfo@towerfinder.com">
					<!-- CHANGE 'info@designbootstrap.com' TO YOUR EMAIL -->
					<input type="hidden" name="currency_code" value="GBP">
					<!-- CHANGE 'USD' TO YOUR CURRENCY CODE IF YOU WANT -->
					<input type="text" class="form-control"
						placeholder="Enter amount in GBP" name="amount" required>
					<hr />
					<input type="submit" class="btn btn-info" value="Donate">
				</form>
			</div>
	</div>
	<div class="row">
		<h1>Latest Updates</h1>
		<c:forEach var="message" items="${messages}">

		</c:forEach>
		<h1>Latest Updates</h1>
	</div>
</div>