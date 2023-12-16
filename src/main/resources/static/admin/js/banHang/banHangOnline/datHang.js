
// File JavaScript của bạn
function kiemTraSoLuongVaTaoHoaDon() {
    // Lấy danh sách các sản phẩm trong giỏ hàng để kiểm tra
    const gioHangChiTietIds = [];
    const gioHangChiTiets = document.querySelectorAll('.form-checkbox:checked');
    gioHangChiTiets.forEach(item => {
        gioHangChiTietIds.push(item.value);
    });

    // Gửi yêu cầu kiểm tra số lượng sản phẩm trước khi tạo hóa đơn
    fetch('/khachhang/gio-hang-chi-tiet/kiem-tra-so-luong', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(gioHangChiTietIds),
    })
        .then(response => response.json())
        .then(result => {
            if (result.canProceed) {
                // Số lượng hợp lệ, tiến hành tạo hóa đơn
                taoHoaDon();
            } else {
                // Hiển thị thông báo lỗi và danh sách sản phẩm không hợp lệ
                const errorMessage = result.message || 'Số lượng sản phẩm không đủ';
                const invalidProducts = result.invalidProducts || [];
                // Hiển thị thông báo lỗi
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi',
                    html: `${errorMessage}<br><br>Sản phẩm :<br>${hienThiDanhSachSanPhamKhongHopLe(invalidProducts)}`,
                    showConfirmButton: false,
                    timer: 5000
                });
            }
        })
        .catch(error => {
            console.error('Lỗi kiểm tra số lượng sản phẩm:', error);
        });
}


function hienThiDanhSachSanPhamKhongHopLe(invalidProducts) {
    let message = '';
    invalidProducts.forEach(product => {
        message += ` ${product.gioHangChiTietDTO1.sanPhamChiTietDTO.sanPhamDTO.tenSanPham}(${product.gioHangChiTietDTO1.sanPhamChiTietDTO.tenMauSac}, 
${product.gioHangChiTietDTO1.sanPhamChiTietDTO.tenKichCo}): Số lượng khách hàng (${product.soLuongCuaKhachHang}) vượt quá số lượng sản phẩm (${product.gioHangChiTietDTO1.sanPhamChiTietDTO.soLuong})<br>`;
    });
    return message;
}


function taoHoaDon() {
    // Lấy tất cả các ô checkbox có class "form-checkbox" (lớp dùng cho các ô checkbox giỏ hàng)
    const checkboxes = document.querySelectorAll('.form-checkbox');

    // Kiểm tra xem có checkbox nào được chọn hay không
    let hasCheckedItems = false;
    checkboxes.forEach(checkbox => {
        if (checkbox.checked) {
            hasCheckedItems = true;
            return;
        }
    });

    if (!hasCheckedItems) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi',
            text: 'Vui lòng chọn sản phẩm',
            showConfirmButton: false,
            timer: 2000
        });
        return;
    }

    const selectedCartItemIds = [];
    checkboxes.forEach(checkbox => {
        if (checkbox.checked) {
            selectedCartItemIds.push(checkbox.value);
        }
    });

    fetch('/khachhang/gio-hang-chi-tiet/tao-hoa-don', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(selectedCartItemIds),
    })
        .then(response => {
            window.location.href = response.url;
        })
        .catch(error => {
            console.error('Lỗi lưu chi tiết hóa đơn:', error);
        });
}
