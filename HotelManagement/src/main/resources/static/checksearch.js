/**
 * 
 */
 function getbooking() {
		var code = $('#code').val();
		$.post("checkCodeBooking", {
			id : code
		}, function(booking, status) {
			if (status == "success") {
				alert('Code exist')
				$("#id").val(booking.id);
				$("#visitbooking").submit();						
			} else {
				alert('Something is wroong!');
			}
		})
	}