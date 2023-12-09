let mauSacDaChon;
let kichCoDaChon;

$(document).ready(function () {
    let tenKichCo = "";
    let mauSacId = "";
    let soLuongInput = $(".daucatmoi").val();

    $(".chonKichCoa").click(function () {
        tenKichCo = $(this).attr("data-id");
        console.log(tenKichCo)
        kichCoDaChon = tenKichCo;
        const sanPhamId = $("#id_san_pham").data("id");
        console.log(sanPhamId)

        if (mauSacId != "") {
            getSoLuongSanPhamChiTiet(tenKichCo, mauSacId, sanPhamId);
            getGiaBanSanPhamChiTiet(tenKichCo, mauSacId, sanPhamId);
        }

        if (soLuongInput > soLuongHienCoCus) {
            soLuongInput = soLuongHienCoCus;
        }
    });

    $(".chonMauSaca").click(function () {
        mauSacId = $(this).attr("data-id")
        mauSacDaChon = mauSacId;
        console.log(mauSacId)
        const sanPhamId = $("#id_san_pham").data("id");

        if (tenKichCo != "") {
            getSoLuongSanPhamChiTiet(tenKichCo, mauSacId, sanPhamId);
            getGiaBanSanPhamChiTiet(tenKichCo, mauSacId, sanPhamId);
        }

        const soLuongHienCoCus = $(".soLuongHienCoCus").val();
        const giaBans = $(".giaBans").val();
        if (soLuongInput > soLuongHienCoCus) {
            soLuongInput = soLuongHienCoCus;
        }
    });

    $(".daucatmoi").on("input", function () {
        let soLuongInput = $(".daucatmoi").val();
        let sanPhamId = $("#id_san_pham").data("id");
        let soLuongHienCo = parseInt($("#soLuongHienCoCus" + sanPhamId).text());

        if (soLuongInput > soLuongHienCo) {
            Swal.fire({
                icon: "error",
                title: "Số lượng nhập vào vượt quá số lượng hiện có của sản phẩm.",
                showConfirmButton: false,
                timer: 2000,
            });
            $(this).val(soLuongHienCo);
            soLuongInput = soLuongHienCo;
        }
        else if (soLuongInput < 1) {
            Swal.fire({
                icon: "error",
                title: "Số lượng nhập vào không được nhỏ hơn 0",
                showConfirmButton: false,
                timer: 2000,
            });
            $(this).val(1);
        }
    });
});

function getSoLuongSanPhamChiTiet(tenKichCo, mauSacId, sanPhamId) {
    $.ajax({
        type: "GET",
        url: "/khachhang/SoLuongSanPhamChiTiet",
        data: {
            tenKichCo: tenKichCo,
            mauSacId: mauSacId,
            sanPhamId: sanPhamId,
        },
        success: function (response) {
            let soLuongSanPhamChiTiet = response.soLuongSanPhamChiTiet;

            if (soLuongSanPhamChiTiet == null || isNaN(soLuongSanPhamChiTiet)) {
                soLuongSanPhamChiTiet = 0;
            }

            $("#soLuongHienCoCus" + sanPhamId).text(soLuongSanPhamChiTiet);
        },
        error: function () {
            alert("Đã xảy ra lỗi khi gửi yêu cầu đến server.");
        },
    });
}

function getGiaBanSanPhamChiTiet(tenKichCo, mauSacId, sanPhamId) {
    $.ajax({
        type: "GET",
        url: "/GiaBan",
        data: {
            tenKichCo: tenKichCo,
            mauSacId: mauSacId,
            sanPhamId: sanPhamId,
        },
            success: function (response) {
                let giaBanSanPhamChiTiet = response.giaBanSanPhamChiTiet;

                if (giaBanSanPhamChiTiet == null || isNaN(giaBanSanPhamChiTiet)) {
                    giaBanSanPhamChiTiet = 0;
                }

                let formattedPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(giaBanSanPhamChiTiet);
                console.log(formattedPrice)
                $("#giaBanHienCoCus" + sanPhamId).text(formattedPrice);
            },
        error: function () {
            alert("Đã xảy ra lỗi khi gửi yêu cầu đến server.");
        },
    });
}

$(document).ready(function () {
    $("#themVaoGioHang").click(function () {
        const auth = $('#auth').val();
        console.log(mauSacDaChon)
        const sanPhamID = $("#id_san_pham").data("id");
        // const mauSacId = $("button[name='mauSacId']:checked").val();
        // const kichCoId = $("input[name='kichCoId']:checked").val();
        const soLuong = $(".daucatmoi").val();


        if (soLuong <= 0) {
            Swal.fired({
                icon: "error",
                title: "Lỗi",
                text: "Vui lòng nhập lại số lượng",
            });
            return;
        }

        if (mauSacDaChon == null) {
            Swal.fire({
                icon: "error",
                title: "Lỗi",
                text: "Bạn chưa chọn màu sắc",
            });
            return;
        }
        if (kichCoDaChon == null) {
            Swal.fire({
                icon: "error",
                title: "Lỗi",
                text: "Bạn chưa chọn kích cỡ",
            });
            return;
        }
        if (soLuong == null) {
            Swal.fire({
                icon: "error",
                title: "Lỗi",
                text: "Bạn chưa chọn số lượng",
            });
            return;
        }

        if (auth != 'anonymousUser') {
            themVaoGioHang(sanPhamID, soLuong);
        } else {
            Swal.fire({
                icon: "danger",
                title: "Cảnh báo",
                text: "Vui lòng đăng nhập !",
            }).then(function () {

                //Tải lại trang
                window.location.href = "/security/login/form";
            });
        }
    });
});

function themVaoGioHang(sanPhamID, soLuong) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/khachhang/addToCart",
        data: {
            sanPhamId: sanPhamID,
            mauSacId: mauSacDaChon,
            kichCoId: kichCoDaChon,
            soLuong: soLuong
        },
        success: function (response) {
            Swal.fire({
                icon: "success",
                title: "Thành công",
                text: "Đã thêm sản phẩm vào giỏ hàng",
            }).then(function () {
                // Lưu trạng thái đã xác nhận vào sessionStorage
                sessionStorage.setItem('isConfirmed', true);

                // Tải lại trang
                window.location.href = "/khachhang/shop-details/" + sanPhamID;
            });
        },
        error: function (error) {
            Swal.fire({
                icon: "error",
                title: "Lỗi",
                text: "Thêm sản phẩm không thành công",
            });
            console.log(error.responseText);
        },
    });
}

