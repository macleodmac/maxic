<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<div class="row">
		<h1>To Do</h1>
		<div class="col-xs-12">
			<ul>
				<li>Build system for users to message administrator</li>
				<li>Bugs
					<ul>
						<li>Form validation - user creation</li>
						<li>Sort double encoding of passwords after admin edit</li>
						<li>Fix datepicker/reloading js on peal page</li>
					</ul>
				</li>
				<li>Finish this page</li>
				<li>Negate unachievable requirements</li>
			</ul>
		</div>
	</div>
	<div class="row">
		<h1>Donations</h1>
		<div class="col-xs-4">
			<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
				<h5>towermap is a community run site, and would gratefully
					receive any donations towards running costs.</h5>
				<hr />
				<input type="hidden" name="cmd" value="_donations"> <input
					type="hidden" name="item_name" value="Donation"> <input
					type="hidden" name="business" value="donate@towermap.co.uk">
				<input type="hidden" name="currency_code" value="GBP"> <input
					type="text" class="form-control" placeholder="Enter amount in GBP"
					name="amount" required>
				<hr />
				<input type="submit" class="btn btn-primary" value="Donate">
			</form>
		</div>
	</div>
</div>