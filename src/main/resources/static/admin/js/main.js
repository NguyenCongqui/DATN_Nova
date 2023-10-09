(function($) {

	"use strict";

	var fullHeight = function() {

		$('.js-fullheight').css('height', $(window).height());
		$(window).resize(function(){
			$('.js-fullheight').css('height', $(window).height());
		});

	};
	fullHeight();

	$('#sidebarCollapse').on('click', function () {
      $('#sidebar').toggleClass('active');
  });

})(jQuery);
$(document).ready(function () {
	//jquery for toggle sub menus
	$('.sub-btn').click(function () {
		$(this).next('.sub-menu').slideToggle();
		$(this).find('.dropdown').toggleClass('rotate');
	});
	// // Xử lý sự kiện nhấp chuột vào liên kết "View Update"
	// $('a[data-toggle="modal"]').on('click', function() {
	// 	var id = $(this).data('id');
	// 	var modal = $('#updateEmployeeModal');
	//
	// 	// Gửi yêu cầu AJAX để lấy thông tin cần thiết
	// 	$.ajax({
	// 		url: '/mau-sac/view-update/' + id,
	// 		type: 'GET',
	// 		success: function(response) {
	// 			// Xử lý phản hồi thành công và cập nhật dữ liệu vào modal
	// 			modal.find('.modal-content').html(response);
	// 			modal.modal('show');
	// 		},
	// 		error: function(xhr, status, error) {
	// 			// Xử lý lỗi
	// 			console.log(error);
	// 		}
	// 	});
	// });
});
$(".menu > ul > li").click(function (e) {
	// remove active from already active
	$(this).siblings().removeClass("active");
	// add active to clicked
	$(this).toggleClass("active");
	// if has sub menu open it
	$(this).find("ul").slideToggle();
	// close other sub menu if any open
	$(this).siblings().find("ul").slideUp();
	// remove active class of sub menu items
	$(this).siblings().find("ul").find("li").removeClass("active");
});

$(".menu-btn").click(function () {
	$(".sidebar").toggleClass("active");
});
jQuery(document).ready(function(){
	// This button will increment the value
	$('[data-quantity="plus"]').click(function(e){
		// Stop acting like a button
		e.preventDefault();
		// Get the field name
		fieldName = $(this).attr('data-field');
		// Get its current value
		var currentVal = parseInt($('input[name='+fieldName+']').val());
		// If is not undefined
		if (!isNaN(currentVal)) {
			// Increment
			$('input[name='+fieldName+']').val(currentVal + 1);
		} else {
			// Otherwise put a 0 there
			$('input[name='+fieldName+']').val(0);
		}
	});
	// This button will decrement the value till 0
	$('[data-quantity="minus"]').click(function(e) {
		// Stop acting like a button
		e.preventDefault();
		// Get the field name
		fieldName = $(this).attr('data-field');
		// Get its current value
		var currentVal = parseInt($('input[name='+fieldName+']').val());
		// If it isn't undefined or its greater than 0
		if (!isNaN(currentVal) && currentVal > 0) {
			// Decrement one
			$('input[name='+fieldName+']').val(currentVal - 1);
		} else {
			// Otherwise put a 0 there
			$('input[name='+fieldName+']').val(0);
		}
	});
});
function openPanel() {
	document.getElementById("newPanel").style.display = "block";
}
function toggleSelect(button) {
	if (button.classList.contains('selected')) {
		button.classList.remove('selected');
		button.classList.add('deselected');
	} else {
		button.classList.remove('deselected');
		button.classList.add('selected');
	}
}
$(document).ready(function () {
	// Xử lý sự kiện khi người dùng nhập vào ô input search
	$("#searchInput").on("input", function () {
		// Lấy giá trị từ ô input search
		var searchValue = $(this).val().trim();

		// Gọi hàm search với giá trị searchValue
		searchTableData(searchValue);
	});

	function searchTableData(searchValue) {
		// Gửi request Ajax để search dữ liệu từ server với giá trị searchValue
		$.get('/chi-tiet-san-pham/search', { ma: searchValue }, function (data) {
			// Xóa nội dung hiện tại của bảng
			$("#datatable-buttons tbody").empty();

			// Loop qua kết quả từ server và cập nhật dữ liệu mới vào bảng
			data.forEach(function (item) {
				var row = "<tr>" +
					"<td>" + item.ten + "</td>" +
					"<td>" + item.soLuong + "</td>" +
					"<td>" + (item.trangThai === 1 ? "Hoạt động" : "Không hoạt động") + "</td>" +
					"<td><a href='/chi-tiet-san-pham/delete1/" + item.id + "' class='delete'>" +
					"<i class='material-icons' data-toggle='tooltip' title='Delete'>&#xE872;</i>" +
					"</a></td>" +
					"</tr>";

				$("#datatable-buttons tbody").append(row);
			});
		});
	}
});

