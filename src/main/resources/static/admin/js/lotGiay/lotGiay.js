
function flexUrlSubmit(url, method, formName) {
    $("#flexUrlTableForm" + formName).attr("action", "/admin/lot-giay" + url);
    $("#flexUrlTableForm" + formName).attr("method", method);
    document.getElementById("flexUrlTableForm" + formName).submit();
}

function openPopupCreateOrUpdate(createOrUpdateType, chatLieuId, tenChatLieu) {
    if (createOrUpdateType === "create") {
        $('#CreateOrUpdateTitle').text("Thêm mới lót giày");
        $('#lotGiayIdCreateOrUpdate').val(-1);
        $('#tenLotGiayCreateOrUpdate').val("");
        $('#yesOptionCreateOrUpdateModalId').text("Thêm mới");
    } else if (createOrUpdateType === "update") {
        $('#CreateOrUpdateTitle').text("Cập nhật lót giày");
        $('#lotGiayIdCreateOrUpdate').val(chatLieuId);
        $('#tenLotGiayCreateOrUpdate').val(tenChatLieu);
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
    flexUrlSubmit("/delete/" + kcId, "get", "LotGiay");
}

function submitCreateOrUpdate() {
    $('#formCreateOrUpdate').submit();
}
