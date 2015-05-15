<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="container">
	<div class="row">

		<div class="col-xs-12">
			<h2>Users</h2>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" aria-controls="collapseOne"> How do I
								search for towers? </a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							There are two possible ways to search for towers:
							<ol>
								<li>On the <a href="${pageContext.request.contextPath}/">homepage,</a>
									use the search box to navigate around the map.
								</li>
								<li>On the <a
									href="${pageContext.request.contextPath}/towers">towers</a>
									page, search using the search box.
								</li>
							</ol>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingTwo">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseTwo"
								aria-expanded="false" aria-controls="collapseTwo"> How do I
								view a tower? </a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingTwo">
						<div class="panel-body">
							Either click on the tower on the map, or click on the name of the
							tower on the <a href="${pageContext.request.contextPath}/towers">towers</a>
							page.
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingThree">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseThree"
								aria-expanded="false" aria-controls="collapseThree"> How do
								I search for performances? </a>
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingThree">
						<div class="panel-body">
							Go to the <a href="${pageContext.request.contextPath}/towers">performances</a>
							page, click on 'Filter' and then narrow your search either by
							tower, date or ringer. The table will be automatically updated to
							show the results of your search.
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingFour">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseFour"
								aria-expanded="false" aria-controls="collapseFour"> How do I
								view a performance? </a>
						</h4>
					</div>
					<div id="collapseFour" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingFour">
						<div class="panel-body">Click on the perfomance and you will
							be shown the performance details.</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingFive">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseFive"
								aria-expanded="false" aria-controls="collapseFive"> How do I
								make a donation? </a>
						</h4>
					</div>
					<div id="collapseFive" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingFive">
						<div class="panel-body">
							Go to the <a href="${pageContext.request.contextPath}/about">about</a>
							page, and follow the instructions to make a donation.
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingSix">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseSix"
								aria-expanded="false" aria-controls="collapseSix"> How do I
								create an account? </a>
						</h4>
					</div>
					<div id="collapseSix" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingSix">
						<div class="panel-body">
							Go to the <a href="${pageContext.request.contextPath}/newaccount">signup</a>
							page, enter your display name, email address (which will be your
							login), your password and click 'Submit'. You will then receive
							an email, in which there will be a link for you to click to
							confirm your account.
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingSeven">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseSeven"
								aria-expanded="false" aria-controls="collapseSeven"> How do
								I recover my password? </a>
						</h4>
					</div>
					<div id="collapseSeven" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingSeven">
						<div class="panel-body">
							Click on the <a
								href="${pageContext.request.contextPath}/forgotpassword">forgotten
								password</a> link, and enter your email address. You will then
							receive an email with a link to reset your password.
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingEight">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseEight"
								aria-expanded="false" aria-controls="collapseEight"> How do
								I change my email address and name? </a>
						</h4>
					</div>
					<div id="collapseEight" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingEight">
						<div class="panel-body">
							<a href="${pageContext.request.contextPath}/login">Login</a> to
							your account and go to <a
								href="${pageContext.request.contextPath}/account/edit">account
								> edit details</a>. Enter your new email address and/or name, your
							current password and click 'Submit'. You will receive an email to
							both your old email address and new email address confirming the
							change.
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingNine">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseNine"
								aria-expanded="false" aria-controls="collapseNine"> How do I
								change my password? </a>
						</h4>
					</div>
					<div id="collapseNine" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingNine">
						<div class="panel-body">
							<a href="${pageContext.request.contextPath}/login">Login</a> to
							your account and go to <a
								href="${pageContext.request.contextPath}/account/edit">account
								> edit details</a>. Enter your new password, your current password
							and click 'Submit'. You will then be logged out and you can log
							in with your new password.
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingTen">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseTen"
								aria-expanded="false" aria-controls="collapseTen"> How do I
								record a tower visit? </a>
						</h4>
					</div>
					<div id="collapseTen" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingTen">
						<div class="panel-body">
							<a href="${pageContext.request.contextPath}/login">Login</a> to
							your account and go to <a
								href="${pageContext.request.contextPath}/account/visits">account
								> my visits</a>. Click 'Add a Visit' and enter the visit details,
							then click 'Add Visit'.
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingEleven">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseEleven"
								aria-expanded="false" aria-controls="collapseEleven"> How do
								I download my tower visits? </a>
						</h4>
					</div>
					<div id="collapseEleven" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingEleven">
						<div class="panel-body">
							<a href="${pageContext.request.contextPath}/login">Login</a> to
							your account and go to <a
								href="${pageContext.request.contextPath}/account/visits">account
								> my visits</a>. Click on 'Download CSV' and you will download a
							record of your tower visits as a .csv file which you can then use
							as you wish.
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingTwelve">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseTwelve"
								aria-expanded="false" aria-controls="collapseTwelve"> How do
								I add a performance? </a>
						</h4>
					</div>
					<div id="collapseTwelve" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingTwelve">
						<div class="panel-body">
							<a href="${pageContext.request.contextPath}/login">Login</a> to
							your account and go to <a
								href="${pageContext.request.contextPath}/performances">performances</a>
							page. Click on 'Add A Performance' and enter the performance
							details, then click 'Add Performance'.
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingThirteen">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseThirteen"
								aria-expanded="false" aria-controls="collapseThirteen"> How
								do I get tower captain rights for my tower? </a>
						</h4>
					</div>
					<div id="collapseThirteen" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingThirteen">
						<div class="panel-body">
							<a href="${pageContext.request.contextPath}/about">Send a
								message</a> to the administrators with your account email address
							and proof that you are the tower captain.
						</div>
					</div>
				</div>
			</div>
		</div>
		<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_CAPTAIN')">
			<div class="col-xs-12">
				<h2>Tower Captains</h2>
				<div class="panel-group" id="accordion2" role="tablist"
					aria-multiselectable="true">
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingFourteen">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseFourteen"
									aria-expanded="false" aria-controls="collapseFourteen">How
									do I add practice details? </a>
							</h4>
						</div>
						<div id="collapseFourteen" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingFourteen">
							<div class="panel-body">
								<a href="${pageContext.request.contextPath}/login">Login</a> to
								your account and go to <a
									href="${pageContext.request.contextPath}/captain/edit">account
									> edit tower</a>. Next to 'Practices', click 'Add' and enter your
								practice details, then click 'Save Changes'.
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingFifteen">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseFifteen"
									aria-expanded="false" aria-controls="collapseFifteen">How
									do I add contact details? </a>
							</h4>
						</div>
						<div id="collapseFifteen" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingFifteen">
							<div class="panel-body">
								<a href="${pageContext.request.contextPath}/login">Login</a> to
								your account and go to <a
									href="${pageContext.request.contextPath}/captain/edit">account
									> edit tower</a>. Next to 'Contact Details', click 'Add' and enter
								your contact details, then click 'Save Changes'.
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingSixteen">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion2" href="#collapseSixteen"
									aria-expanded="false" aria-controls="collapseSixteen">How
									do I edit tower details? </a>
							</h4>
						</div>
						<div id="collapseSixteen" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingSixteen">
							<div class="panel-body">
								<a href="${pageContext.request.contextPath}/login">Login</a> to
								your account and go to <a
									href="${pageContext.request.contextPath}/captain/edit">account
									> edit tower</a>. From here you can edit tower information, edit
								current contact and practice details, and add new contact and
								practice details. When you are done making changes click 'Save
								Changes'.
							</div>
						</div>
					</div>
				</div>
			</div>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div class="col-xs-12">
				<h2>Administrators</h2>
				<div class="panel-group" id="accordion3" role="tablist"
					aria-multiselectable="true">
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingSeventeen">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion3" href="#collapseSeventeen"
									aria-controls="collapseSeventeen">How do I add a tower? </a>
							</h4>
						</div>
						<div id="collapseSeventeen" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingSeventeen">
							<div class="panel-body">
								Go to <a href="${pageContext.request.contextPath}/admin/towers">admin
									> towers,</a>. Next to 'Contact Details', click 'Add' and enter
								click 'Add a Tower', enter the information required and click
								'Add Tower'.
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingEighteen">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion3" href="#collapseEighteen"
									aria-controls="collapseEighteen">How do I edit a tower? </a>
							</h4>
						</div>
						<div id="collapseEighteen" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingEighteen">
							<div class="panel-body">
								Go to <a href="${pageContext.request.contextPath}/admin/towers">admin
									> towers,</a> search for the tower, and click 'Edit', you can then
								edit the information you need to and click 'Save Changes'.
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingNineteen">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion3" href="#collapseNineteen"
									aria-controls="collapseNineteen">How do I delete a tower? </a>
							</h4>
						</div>
						<div id="collapseNineteen" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingNineteen">
							<div class="panel-body">
								Go to <a href="${pageContext.request.contextPath}/admin/towers">admin
									> towers,</a> search for the tower, and click 'Edit'. At the bottom
								you can click 'Delete Tower' and the tower will be deleted.
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingTwenty">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion3" href="#collapseTwenty"
									aria-controls="collapseTwenty">How do I edit a performance?
								</a>
							</h4>
						</div>
						<div id="collapseTwenty" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTwenty">
							<div class="panel-body">
								Go to <a href="${pageContext.request.contextPath}/admin/peals">admin
									> performances,</a> search for the performance, and click 'Edit'.
								Edit the details you need to and then click 'Save Changes'.
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingTwentyOne">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion3" href="#collapseTwentyOne"
									aria-controls="collapseTwentyOne">How do I delete a
									performance? </a>
							</h4>
						</div>
						<div id="collapseTwentyOne" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTwentyOne">
							<div class="panel-body">
								Go to <a href="${pageContext.request.contextPath}/admin/peals">admin
									> performances,</a> search for the performance, and click the 'X'
								next to the performance. The performance will be deleted.
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingTwentyTwo">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion3" href="#collapseTwentyTwo"
									aria-controls="collapseTwentyTwo">How do I add/edit/delete
									a country? </a>
							</h4>
						</div>
						<div id="collapseTwentyTwo" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTwentyTwo">
							<div class="panel-body">
								Go to <a
									href="${pageContext.request.contextPath}/admin/countries">admin
									> countries,</a> click 'Add a Country' if you want to add a new
								country, enter the information and click 'Add Country'. To Edit
								a current country, click 'Edit' next to the country, make your
								changes and click 'Save Changes'. You can also delete the
								country on this page if necessary.
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingTwentyThree">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion3" href="#collapseTwentyThree"
									aria-controls="collapseTwentyThree">How do I
									add/edit/delete a diocese? </a>
							</h4>
						</div>
						<div id="collapseTwentyThree" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTwentyThree">
							<div class="panel-body">
								Go to <a
									href="${pageContext.request.contextPath}/admin/dioceses">admin
									> dioceses,</a> click 'Add a Diocese' if you want to add a new
								diocese, enter the information and click 'Add Diocese'. To Edit
								a current diocese, click 'Edit' next to the diocese, make your
								changes and click 'Save Changes'. You can also delete the
								diocese on this page if necessary.
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingTwentyFour">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion3" href="#collapseTwentyFour"
									aria-controls="collapseTwentyFour">How do I add a user? </a>
							</h4>
						</div>
						<div id="collapseTwentyFour" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTwentyFour">
							<div class="panel-body">
								Go to <a href="${pageContext.request.contextPath}/admin/users">admin
									> users,</a> click 'Add a User'. Enter their details and click 'Add
								User'. They will then receive an email with a link where they
								can set their password.
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingTwentyFive">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion3" href="#collapseTwentyFive"
									aria-controls="collapseTwentyFive">How do I make a user a
									tower captain or administrator? </a>
							</h4>
						</div>
						<div id="collapseTwentyFive" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTwentyFive">
							<div class="panel-body">
								Go to <a href="${pageContext.request.contextPath}/admin/users">admin
									> users</a>. Find the user by searching, click 'Edit' and set their
								role to either ROLE_ADMIN or ROLE_CAPTAIN. If you set it to the
								latter, choose the tower which they have permission to edit.
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingTwentySix">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion3" href="#collapseTwentySix"
									aria-controls="collapseTwentySix">How do I reset a user's
									password? </a>
							</h4>
						</div>
						<div id="collapseTwentySix" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTwentySix">
							<div class="panel-body">
								Go to <a href="${pageContext.request.contextPath}/admin/users">admin
									> users</a>. Find the user by searching, then click 'Reset'. The
								user will receive an email with a link where they can reset
								their password.
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingTwentySeven">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion3" href="#collapseTwentySeven"
									aria-controls="collapseTwentySeven">How do I update the
									database from a dove.txt file? </a>
							</h4>
						</div>
						<div id="collapseTwentySeven" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTwentySeven">
							<div class="panel-body">
								Go to <a href="${pageContext.request.contextPath}/admin/dove">admin
									> database</a>. Upload the newpks.txt file and wait for the page to
								finish processing, then add the new dove.txt file and wait for
								it to finish processing. Note, this will replace all towers with
								the updated ones from dove. It will not affect performances,
								contact details or practice details.
							</div>
						</div>
					</div>
				</div>
			</div>
		</sec:authorize>
	</div>
</div>