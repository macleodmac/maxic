$(document).ready(function() {
	$('input[rel="txtTooltip"]').tooltip();
});

function checkPractice() {
	if (confirm("Those practice details will be deleted. Are you sure?")) {
		return true;
	} else {
		return false;
	}
}

function checkContact() {
	if (confirm("Those contact details will be deleted. Are you sure?")) {
		return true;
	} else {
		return false;
	}
}

function checkTower() {
	if (confirm("This tower will be deleted. Are you sure?")) {
		if (confirm("This deletion is irreversable. Do you want to continue?")) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}
}