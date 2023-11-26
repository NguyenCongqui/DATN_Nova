document.addEventListener('DOMContentLoaded', function () {
    const ngayTao = new Date(/* Set giá trị của ngayTao tại đây */);

    // Kiểm tra nếu ngày tạo là mới nhất
    if (isNgayTaoMoiNhat(ngayTao)) {
        const newestLabel = document.getElementById('newestLabel');
        newestLabel.classList.add('newest-label');
    }
});

function isNgayTaoMoiNhat(ngayTao) {
    const now = new Date();
    return ngayTao.getTime() === now.getTime();
}
