$.fn.exists = function () {
    return this.length !== 0;
}

$(function() {
	
	if ($("#datetimepicker1").exists()) {
		$('#datetimepicker1').datetimepicker({
			format: 'DD/MM/YYYY HH:mm'          
		});
	}
	
	if ($(".delitem").exists()) {
		$(".delitem").click(function() {
			var taskId = $(this).parent().next("input").val();
			
			$.ajax({
				type: "GET",
				url: "../delete-task",
				data:{ 'taskId': taskId}
			}).done(function () {
				$('#task-table input[value="' + taskId + '"]').parent().remove();
				$(".alert").addClass("in").fadeIn(2000);
				$(".alert").delay(200).addClass("in").fadeOut(4000);
			});
		});
	}
});


