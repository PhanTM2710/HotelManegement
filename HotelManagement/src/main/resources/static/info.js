/**
 * 
 */
	$.date = function(dateObject) {
	    var d = new Date(dateObject);
	    var day = d.getDate();
	    var month = d.getMonth() + 1;
	    var year = d.getFullYear();
	    if (day < 10) {
	        day = "0" + day;
	    }
	    if (month < 10) {
	        month = "0" + month;
	    }
	    var date =month + "/" +  day + "/" + year;
	
	    return date;
	};
	
	function check(name,phone,email,cardnumber) {
		var validFlg = true;
		
		// Check name
		if (name == "" ) {
			$("#errMsgname").text("Name is required!");
			$('#errname').css({"display": "block", "position": "relative", "width": "181px"});
			validFlg = false;
		} else {
			$("#errMsgname").text("");
			$('#errname').css({"display": "none"});
		}
		
		// check phone
		if (phone == "" ) {
			$("#errMsgphone").text("Phone is required!");
			$('#errphone').css({"display": "block", "position": "relative", "width": "181px"});
			validFlg = false;
		} else {
			$("#errMsgphone").text("");
			$('#errphone').css({"display": "none"});
		}
		
		// check email
		var regexMailFormat = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (email == "" ) {
			$("#errMsgemail").text("Email is required!");
			$('#erremail').css({"display": "block", "position": "relative", "width": "181px"});
			validFlg = false;
		} else if (!email.match(regexMailFormat)) {
			// format email invalid
			$("#errMsgemail").text("Format email invalid!");
			$('#erremail').css({"display": "block", "position": "relative", "width": "181px"});
			validFlg = false;
		} else {
			$("#errMsgemail").text("");
			$('#erremail').css({"display": "none"});
		}	

		
		// check CardNumber
		if(cardnumber == "") {
			$("#Msgsucces").text("Payment is required!");
			$('#success').css({"display": "table-caption", "position": "relative", "width": "181px"});
			validFlg = false;
		} else if(id==null ) {
			$("#Msgsucces").text("Please choose a card to pay");
			$('#success').css({"display": "table-caption", "position": "relative", "width": "181px"});
			validFlg = false;
		}else{			
			$("#Msgsucces").text("");
			$('#success').css({"display": "none"});
		}
			
		return validFlg;
	}    
	
	function addpayment() {
		var validFlg = true;
		var cardnumber = $('#cardNumber').val();
		var name = $("#name").val();
		var phone = $("#phone").val();
		var address = $("#address").val();
		var email = $("#email").val();		
		var date = $("#birthday").val();
		var birthday = $.date(date);
		var descg = $('#description').val();
		var id = $('#id').val();
		var idPromo=$('#idpro').val();		
		
		validFlg = check(name,phone,email,cardnumber,id);
		
		var result = confirm('YOU ARE SURE?');
		
		if (validFlg && result) {
			$.post("addpayment", {
			name : name,
			phone : phone,
			address :address,
			email :email,
			birthday : birthday,
			id : id,
			descr : descg,
			idpro : idPromo
			}, function(payment, status) {
				if (status == "success") {
					if (payment.id != undefined) {
						alert('Booking code will be sent to your email!');
						$("#visitSumary").submit();
			    		} else {
			    			alert('Booking unsuccessful!');
			    		}	
				} else {
					alert('Something is wrong!');
				}
			});
		}

	}
 		
	function getpromotion(target) {
		var promotionCode = $('#code').val();
		var moneybefore = $("#hiden").val();
		$.post("checkPromo", {
			code : promotionCode
		}, function(promotion, status) {
			if (status == "success") {
				if (promotion.code != undefined) {
					alert(' Discount code' + promotion.code +' applied successfully!');
					$("#discount").text(promotion.discount);					
					$("#afterDiscount").text(moneybefore - ((moneybefore * promotion.discount)/100));
					$("#hidenafter").val(moneybefore - ((moneybefore * promotion.discount)/100));
					$(".afterprice").change();
					$('#code').val(promotion.code);
					$('#idpro').val(promotion.id);
 		    	} else {
 		    		alert('Discount code not found!');
 		    	}						
			} else {
				alert('Something is wrong!');
			}
		});
	}
			
	function getcard(target) {
		var cardnumber = $('#cardNumber').val();
		var money = $('#hidenafter').val();
		$.post("checkcard", {
			money : money,
			cardNumber : cardnumber
		}, function(creditcard, status) {
			if (status == "success") {
				if (creditcard.cardNumber != undefined) {
					$("#Msgsucces").text(creditcard.cardNumber +  "      SUCCESS!");
					$('#success').css({"display": "table-caption", "position": "relative", "width": "181px","background-color":"green","font-size":"16px"});	
					$("#id").val(creditcard.id);
					$("#moneycard").text(creditcard.amount);
					$(".moneycard").change();
				} else {
 		    		$("#Msgsucces").text( "Not found");
					$('#success').css({"display": "table-caption", "position": "relative", "width": "181px","background-color":"red","font-size":"16px"});
 		   		}						
			} else {
				alert('Something is wrong!');
			}
		})
	}
			
	function formatMoney(target) {
       	var money = parseInt($(target).text());
       	$(target).text(money.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	}
	function afterformatMoney(target) {
       	var money = parseInt($(target).text());
       	$(target).text(money.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	}
	   
	function formatMoneycard(target) {
       	var money = parseInt($(target).text());
       	$(target).text(money.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	} 
	//Format money khi load trang
	$(".moneycard").change();
	$(".afterprice").change();
	$(".price").change();
	     
	$("#code").keyup(function() {
		var nameInput = $("#code").val();
		if (nameInput != "") {
			$("#check").removeAttr("disabled");
		} else {
			$("#check").attr("disabled", "disabled");
		}
	});
		
	$("#cardNumber").keyup(function() {
		var nameInput = $("#cardNumber").val();
		if (nameInput != "") {
			$("#checkcard").removeAttr("disabled");
		} else {
			$("#checkcard").attr("disabled", "disabled");
		}
     });
	

