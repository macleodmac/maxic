<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<div class="row">
		<h1>To Do</h1>
		<div class="col-xs-12">
			<ul>
				<li>Main page map (js)
					<ul>
						<li>Sort main page map modal not reloading</li>
						<li>Add location search and filtering by ringable/gfr/bell
							number/visited</li>
						<li>Add geolocation</li>
					</ul>
				</li>
				<li>Crawling bellboard (java)
					<ul>
						<li>Build crawler managed by Spring Scheduler</li>
						<li>Match churches to current database (using Levenshtein distance?) </li>
					</ul>
				</li>
				<li>Build system for users to message administrator</li>
				<li>Bugs
					<ul>
						<li>Form validation - user creation</li>
						<li>Sort double encoding of passwords after admin edit</li>
						<li>Fix datepicker/reloading js on peal page</li>
					</ul>
				</li>
				<li>Write user FAQ / Admin/Captain manual</li>
				<li>Software Documentation</li>
				<li>Finish this page</li>
				<li>Negate unachievable requirements</li>
				<li>Fine tune mobile responsiveness</li>
			</ul>
		</div>
	</div>
	<div class="row">
		<h1>Donations</h1>
		<div class="col-xs-4">
			<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
				<h5>Please Enter Amount to Donate</h5>
				<hr />
				<input type="hidden" name="cmd" value="_donations"> <input
					type="hidden" name="item_name" value="Donation"> <input
					type="hidden" name="business" value="innfo@towerfinder.com">
				<input type="hidden" name="currency_code" value="GBP"> <input
					type="text" class="form-control" placeholder="Enter amount in GBP"
					name="amount" required>
				<hr />
				<input type="submit" class="btn btn-info" value="Donate">
			</form>
		</div>
	</div>
</div>