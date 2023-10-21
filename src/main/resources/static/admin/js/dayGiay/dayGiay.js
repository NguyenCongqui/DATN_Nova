
function flexUrlSubmit(url, method, formName) {
    $("#flexUrlTableForm" + formName).attr("action", "/admin/day-giay" + url);
    $("#flexUrlTableForm" + formName).attr("method", method);
    document.getElementById("flexUrlTableForm" + formName).submit();
}

function openPopupCreateOrUpdate(createOrUpdateType, chatLieuId, tenChatLieu) {
    if (createOrUpdateType === "create") {
        $('#CreateOrUpdateTitle').text("Thêm mới dây giày");
        $('#dayGiayIdCreateOrUpdate').val(-1);
        $('#tenDayGiayCreateOrUpdate').val("");
        $('#yesOptionCreateOrUpdateModalId').text("Thêm mới");
    } else if (createOrUpdateType === "update") {
        $('#CreateOrUpdateTitle').text("Cập nhật dây giày");
        $('#dayGiayIdCreateOrUpdate').val(chatLieuId);
        $('#tenDayGiayCreateOrUpdate').val(tenChatLieu);
        $('#yesOptionCreateOrUpdateModalId').text("Cập nhật");
    } else {
        return;
    }
    $('#createOrUpdateModalId').modal("show");
}

function showConfirmModalDeleteDialog(id, name) {
    $("#productName").text(name);
    $("#yesOptionDeleteModalId").attr("cl-id", id);
    $("#deleteModalId").modal("show");
}

function submitDeleteProduct() {
    var kcId = $("#yesOptionDeleteModalId").attr("cl-id");
    flexUrlSubmit("/delete/" + kcId, "get", "DayGiay");
}

function submitCreateOrUpdate() {
    $('#formCreateOrUpdate').submit();
}
