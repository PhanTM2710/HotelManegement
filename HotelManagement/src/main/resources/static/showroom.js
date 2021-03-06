/**
 * 
 */
 
	    var today = new Date();
	 	var today_format =  (today.getMonth() + 1) + "/"
	    						+ today.getDate() + "/"
    							+ today.getFullYear();

		var minDateVal = new Date();
		minDateVal.setDate(minDateVal.getDate() - 1);
        $('#startDate').datepicker({
            uiLibrary: 'bootstrap4',
            iconsLibrary: 'fontawesome',
            minDate: minDateVal,
            maxDate: function () {
                return $('#endDate').val();
            },

        });        
        
        $('#endDate').datepicker({
            uiLibrary: 'bootstrap4',
            iconsLibrary: 'fontawesome',
            minDate: function () {
                return $('#startDate').val();
            },
        });
        
        $('#searchForm').submit(function () {
			var invalidRequiredFlg = false;

			if ($('#startDate').val() == "") {
				$("#errMsgStartDate").text("Start Date is required!");
				$('#errStartDate').css({"display": "block", "position": "relative", "width": "181px"});
				invalidRequiredFlg = true;
        	}
			
			if ($('#endDate').val() == "") {
				$("#errMsgEndDate").text("End Date is required!");
				$('#errEndDate').css({"display": "block", "position": "relative", "width": "181px"});
				invalidRequiredFlg = true;
        	}
			
			
			
			if (invalidRequiredFlg) {
				return false;
			} else {
				var startDate = new Date($('#startDate').val());
				var currentDate = new Date(today_format);
				if (startDate < currentDate) {
					$("#errMsgStartDate").text("Start Date cannot be past date!");
					$('#errStartDate').css({"display": "block", "position": "relative", "width": "181px"});
					return false;
				} else {
					if ($('#startDate').val() > $('#endDate').val()) {
						$("#errMsgEndDate").text("Start Date is less than or equal to End Date!");
						$('#errEndDate').css({"display": "block", "position": "relative", "width": "181px"});
						return false;
					} else {
						if ($('#endDate').val() - $('#startDate').val() > 90) {
							$("#errMsgEndDate").text("Booking time is too long!");
							$('#errEndDate').css({"display": "block", "position": "relative", "width": "181px"});
							return false;
						} else {
							return true;
						}
					}
				}
			}
        });
        
        function getBookingDetail(target) {
        	var roomId = $(target).val();
        	$.post("/addroom",
   		    {
   		      id: roomId
   		    },
   		    function(room, status){
   		    	if (status == "success") {
   		    		if (room.id != undefined) {
   	   		    		alert('S??? th??m ph??ng '+room.roomNumber+' v??o danh s??ch!');   	   		    		
   	   		    		location.reload();
   		    		} else {
   		    			alert('Ph??ng ???? ???????c ch???n. Vui l??ng ch???n ph??ng kh??c!');
   		    		}
   		    	} else {
   		    		alert('Something is wroong!');
   		    	}
   		    });

        }
        
        function removebooking(target) {
        	var roomId = $(target).val();
        	var result = confirm('Want to delete?')
        	
        	$.post("/delete",
   		    {
   		      id: roomId
   		    },
   		    function(room, status){
   		    	if(result){
   		    		if (status == "success") {
   	   		    		if (room.id != undefined) {
   	   	   	   		    		alert('???? x??a ph??ng '+room.roomNumber+' ra kh???i danh s??ch!');
   	   	   	   		    		location.reload();
   	   		    		} else {
   	   		    			alert('Ph??ng ???? ???????c ch???n. Vui l??ng ch???n ph??ng kh??c!');
   	   		    		}
   	   		    	} else {
   	   		    		alert('Something is wroong!');
   	   		    	}
   	        		return true
   	        	}else{
   	        		return false;
   	        	}
   		    	
   		    });

        }
        function formatMoney(target) {
        	var money = parseInt($(target).text());
        	$(target).text(money.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        }
        
        //Format money khi load trang
        $(".price").change();
        
        $(document).ready(function() {
			if($('.total').val() == NaN){
				$('.total').val(0);
			}
		});
		
		
		function goBack() {
			window.history.back();
		}
    		
