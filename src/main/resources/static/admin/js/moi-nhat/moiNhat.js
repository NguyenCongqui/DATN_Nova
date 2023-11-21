document.addEventListener('DOMContentLoaded', function () {
    var ngayTao = new Date(/* Set giá trị của ngayTao tại đây */);

    // Kiểm tra nếu ngày tạo là mới nhất
    if (isNgayTaoMoiNhat(ngayTao)) {
        var newestLabel = document.getElementById('newestLabel');
        newestLabel.classList.add('newest-label');
    }
});

function isNgayTaoMoiNhat(ngayTao) {
    var now = new Date();
    return ngayTao.getTime() === now.getTime();
}
