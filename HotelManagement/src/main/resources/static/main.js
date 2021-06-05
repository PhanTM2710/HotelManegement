/**
 * 
 */
 
 $(document).ready(function(){
	$('.div .eBtn').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
			if(text=='Show more')
		$.get(href, function(service){
			$.get('.myForm #name').val(service.name);
			$.get('.myForm #price').val(service.price);
			$.get('.myForm #desc').val(service.desciption);
			$.get('.myForm #unit').val(service.unit);
		});
		$('.myForm #showmoreModal').modal();
	});
});