// Lấy ra checkbox và thực hiện theo dõi sự kiện khi thay đổi
    const rememberMeCheckbox = document.getElementById('rememberMeCheckbox');
    rememberMeCheckbox.addEventListener('change', function() {
        // Kiểm tra nếu checkbox được chọn, lưu một cookie
        if (this.checked) {
            // Thời gian sống của cookie (ở đây là 30 ngày)
            const expiryDate = new Date();
            expiryDate.setDate(expiryDate.getDate() + 24);

            // Tạo cookie với tên là 'rememberMe' và giá trị là 'true'
            document.cookie = `rememberMe=true; expires=${expiryDate.toUTCString()}; path=/`;
        } else {
            // Nếu checkbox không được chọn, xóa cookie
            document.cookie = 'rememberMe=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
        }
    });
// const rememberMeCheckbox = document.getElementById('rememberMeCheckbox');
//
// rememberMeCheckbox.addEventListener('change', function() {
//     if (this.checked) {
//         // Nếu checkbox được chọn, lưu một cookie để ghi nhớ
//         const expiryDate = new Date();
//         expiryDate.setDate(expiryDate.getDate() + 1); // Thay vì 30 ngày, đặt thời gian sống là 1 ngày
//
//         // Tạo cookie với tên là 'rememberMe' và giá trị là 'true'
//         document.cookie = `rememberMe=true; expires=${expiryDate.toUTCString()}; path=/`;
//     } else {
//         // Nếu checkbox không được chọn, xóa cookie
//         document.cookie = 'rememberMe=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
//     }
// });


// // Hàm lưu cookie
// function setCookie(name, value, days) {
//     const date = new Date();
//     date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000)); // Tính thời gian hết hạn của cookie
//     const expires = "expires=" + date.toUTCString();
//     document.cookie = name + "=" + value + ";" + expires + ";path=/";
// }
//
// // Hàm kiểm tra checkbox và lưu cookie khi được chọn
// document.addEventListener('DOMContentLoaded', function() {
//     const rememberCheckbox = document.querySelector('input[type="checkbox"]');
//     rememberCheckbox.addEventListener('change', function() {
//         if (this.checked) {
//             setCookie('rememberMe', 'true', 1); // Lưu cookie với thời gian sống 1 ngày
//         } else {
//             // Nếu checkbox không được chọn, xóa cookie
//             document.cookie = 'rememberMe=;expires=Thu, 01 Jan 1970 00:00:00 UTC;path=/;';
//         }
//     });
// });
