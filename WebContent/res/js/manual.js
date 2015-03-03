$('#sidebar').affix({
  offset: {
    top: $('.navbar-collapse.collapse').height()
  }
});

$.getJSON('localhost/towers/gettowers', function(data) {
	  document.write(data);
});