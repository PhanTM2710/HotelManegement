/**
 * 
 */

        
    	$(Document).ready(function() {
			if($('.total').val() ==NaN){
				$('.total').val()=0;
			}
		});
    		  
        function getServiceDetail(target) {
        	var seviceId = $(target).val();
        	$.post("addservice",
   		    {
   		      id: seviceId
   		    },
   		    function(service, status){
   		    	if (status == "success") {
   		    		if (service.id != undefined) {
   	   		    		alert('Đã thêm phòng '+service.name+' vào danh sách!');
   	   		    		location.reload();
   		    		} else {
   		    			alert('Phòng đã được chọn. Vui lòng chọn phòng khác!');
   		    		}
   		    	} else {
   		    		alert('Something is wroong!');
   		    	}
   		    });

        }
        
        function removeService(target) {
        	var seviceId = $(target).val();
        	var result = confirm('Want to delete?')
        	
        	$.post("deleteservice",
   		    {
   		      id: seviceId
   		    },
   		    function(service, status){
   		    	if(result){
   		    		if (status == "success") {
   	   		    		if (service.id != undefined) {
   	   	   	   		    		alert('Đã xóa phòng '+service.name+' ra khỏi danh sách!');
   	   	   	   		    		location.reload();

   	   		    		} else {
   	   		    			alert('Phòng đã được chọn. Vui lòng chọn phòng khác!');
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
        
