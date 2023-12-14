<!-- JS ĐANG GIAO -> ĐÃ GIAO -->
$(document).ready(function () {
    $('.DaGiao').click(function () {
        const hoaDonId = $(this).data('id');

        // Lưu trạng thái tab hiện tại vào sessionStorage
        const activeTab = $('.nav-link.active').attr('href');
        sessionStorage.setItem('activeTab', activeTab);

        // Hiển thị modal xác nhận
        $('.giaoHangTCModal').modal('show');

        // Xử lý sự kiện khi bấm nút Đồng ý
        $('.giaoHangTCModal .btn-dong-y').click(function () {
            // Gửi yêu cầu xác nhận đơn hàng bằng Ajax
            $.get('/updateGiaoHangThanhCong/' + hoaDonId, function (response) {
                // Lưu trạng thái đã xác nhận vào sessionStorage
                Swal.fire({
                    icon: 'success',
                    title: 'Xác nhận đơn hàng đã giao thành công !',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function () {
                    // Lưu trạng thái đã xác nhận vào sessionStorage
                    sessionStorage.setItem('isConfirmed', true);

                    window.location.href = "/admin/DonHang/DangGiaoHang/danhSach";
                });
            });

            // Đóng modal
            $('.giaoHangTCModal').modal('hide');
        });

        // Xử lý sự kiện khi bấm nút Không
        $('.giaoHangTCModal .btn-khong').click(function () {
            // Đóng modal
            $('.giaoHangTCModal').modal('hide');
        });
    });
});

function dangGiaoHang() {
    let hoaDonId = $("#dangGiaoId").val();
    // Lưu trạng thái tab hiện tại vào sessionStorage
    const activeTab = $('.nav-link.active').attr('href');
    sessionStorage.setItem('activeTab', activeTab);
    Swal.fire({
        icon: 'question',
        title: 'Xác nhận đơn hàng HD' + hoaDonId + ' đã hoàn thành',
        showCancelButton: true,
        confirmButtonText: "Xác nhận",
    }).then((result) => {
        if (result.isConfirmed) {
            // Gửi yêu cầu hủy đơn hàng bằng Ajax
            $.get('/updateGiaoHangThanhCong/' + hoaDonId, function (response) {
                // Hiển thị thông báo hủy thành công với SweetAlert2
            }).then(function (resp) {
                // Lưu trạng thái đã xác nhận vào sessionStorage
                Swal.fire({
                    icon: 'success',
                    title: 'Xác nhận đơn hàng đã giao thành công !',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function (resp) {
                    // Lưu trạng thái đã xác nhận vào sessionStorage
                    sessionStorage.setItem('isConfirmed', true);

                    // Tải lại trang
                    // location.reload();
                    window.location.href = "/admin/DonHang/DangGiaoHang/danhSach";
                });
            });
        }
    });
}
<!-- JS CHỜ CHỜ XÁC NHẬN -> ĐÃ HỦY -->
// $(document).ready(function () {
//     $('.HuyDon').click(function () {
//         let hoaDonId = $(this).data('id');
//         let modalId = $(this).data('target');
//         let ghiChu = $(this).val();
//
//         // Hiển thị modal xác nhận
//         $(modalId).modal('show');
//
//         // Xử lý sự kiện khi bấm nút Đồng ý
//         $(modalId + ' .btn-dong-y').click(function () {
//             Swal.fire({
//                 // title: "Xác nhận hủy đơn hàng " +hoaDonId +" ?",
//                 title: 'Xác nhận hủy đơn hàng #HD' + hoaDonId,
//                 icon: 'question',
//                 inputLabel: 'Ghi chú',
//                 input: 'textarea',
//                 showCancelButton: true,
//                 confirmButtonText: "Xác nhận",
//             }).then((result) => {
//                 if (result.isConfirmed){
//                     // Gửi yêu cầu hủy đơn hàng bằng Ajax
//                     $.get('/updateHuyDon/' + hoaDonId, function (response) {
//                         // Hiển thị thông báo hủy thành công với SweetAlert2
//                     }).then(function (resp) {
//                         // Lưu trạng thái đã xác nhận vào sessionStorage
//                         Swal.fire({
//                             icon: 'success',
//                             title: 'Hủy đơn hàng thành công !',
//                             showConfirmButton: false,
//                             timer: 2000
//                         }).then(function () {
//                             // Lưu trạng thái đã xác nhận vào sessionStorage
//                             sessionStorage.setItem('isConfirmed', true);
//
//                             // Tải lại trang
//                             // location.reload();
//                             window.location.href = "/admin/DonHang/ChoXacNhanDonHang/danhSach";
//                         });
//                     });
//                 }
//             });
//
//
//             // Đóng modal
//             $(modalId).modal('hide');
//         });
//
//         // Xử lý sự kiện khi bấm nút Không
//         $(modalId + ' .btn-khong').click(function () {
//             // Đóng modal
//             $(modalId).modal('hide');
//         });
//     });
// });
function huyDon() {
    let hoaDonId = $("#hoaDonId").val();
    Swal.fire({
        icon: 'question',
        title: 'Bạn có muốn hủy đơn hàng HD' + hoaDonId,
        showCancelButton: true,
        confirmButtonText: "Xác nhận",
    }).then((result) => {
        if (result.isConfirmed) {
            // Gửi yêu cầu hủy đơn hàng bằng Ajax
            $.get('/updateHuyDon/' + hoaDonId, function (response) {
                // Hiển thị thông báo hủy thành công với SweetAlert2
            }).then(function (resp) {
                // Lưu trạng thái đã xác nhận vào sessionStorage
                Swal.fire({
                    icon: 'success',
                    title: 'Hủy đơn hàng thành công !',
                    text: 'Bạn đã hủy đơn hàng HD' + hoaDonId,
                    showConfirmButton: false,
                    timer: 2000
                }).then(function (resp) {
                    // Lưu trạng thái đã xác nhận vào sessionStorage
                    sessionStorage.setItem('isConfirmed', true);

                    // Tải lại trang
                    // location.reload();
                    window.location.href = "/admin/DonHang/ChoXacNhanDonHang/danhSach";
                });
            });
        }
    });
}

function quayLai() {
    window.location.href = "/admin/DonHang/DangGiaoHang/danhSach";
}

function inHoaDonChiTiet() {
    let hoaDonId = $("#inHoaDon").val();
    Swal.fire({
        icon: 'question',
        title: 'Bạn có muốn in hóa đơn cho đơn hàng HD' + hoaDonId,
        showCancelButton: true,
        confirmButtonText: "In",
    }).then((result) => {
        if (result.isConfirmed) {
            printHoaDon(hoaDonId);

        }
    });
}

// $(document).ready(function () {
//     $(".inHoaDonChiTiet").click(function () {
//         $('.inHoaDonModal').modal('show');
//         $('.inHoaDonModal .btn-dong-y').click(function () {
//             let hoaDonID = $("#idChiTietHoaDon").val();
//             printHoaDon(hoaDonID);
//             $('.inHoaDonModal').modal('hide');
//         });
//
//         $('.inHoaDonModal .btn-khong').click(function () {
//             $('.inHoaDonModal').modal('hide');
//         });
//     });
// });

function printHoaDon(hoaDonId) {
    // Tạo tên file PDF mới bằng UUID
    let pdfFileName = generateUuid() + '.pdf';

    // Gọi API để in hóa đơn và lưu file PDF vào thư mục dự án
    fetch('/in-hoa-don-don-dat-hang/' + hoaDonId + '?pdfFileName=' + pdfFileName)
        .then(response => response.blob())
        .then(pdfBlob => {
            let pdfUrl = URL.createObjectURL(pdfBlob);
            let newWindow = window.open(pdfUrl, '_blank'); // Mở trang mới chứa file PDF
            if (newWindow) {
                newWindow.document.title = 'Hóa đơn của bạn';
            } else {
                alert('Vui lòng cho phép trình duyệt mở popup để xem và lưu hóa đơn.');
            }
        })
        .catch(error => console.error('Lỗi khi tạo file PDF:', error));
}

function generateUuid() {
    let uuid = '', i, random;
    for (i = 0; i < 32; i++) {
        random = Math.random() * 36 | 0; // Thay đổi thành toString(36)
        if (i === 8 || i === 12 || i === 16 || i === 20) {
            uuid += '-';
        }
        uuid += (i === 12 ? 4 : (i === 16 ? (random & 3 | 8) : random))
            .toString(36);
    }
    return uuid;
}
