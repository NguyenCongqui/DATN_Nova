    let url = "/api/sanpham/phantrang";
    let url1 = "/api/sanpham";
    let urldanhmuc = "/api/danhmuc";
    let urlthuonghieu = "/api/thuonghieu";
    let urlphanloai = "/api/phanloai";
    let urlxuatxu = "/api/xuatxu";
    let urlphongcach = "/api/phongcach";
    let urlchatlieu = "/api/chatlieu";
    let urlmausac = "/api/mausac";
    let urlkichthuoc = "/api/kichthuoc";
    let app = angular.module("myApp", []);
    app.controller("ctrl", function ($scope, $http, $location){
        $scope.list = [];

        $http.get(url).then(function (response) {
            $scope.list = response.data.content;
        });

        $scope.listDM = [];
        $http.get(urldanhmuc).then(function (response) {
            $scope.listDM = response.data;
        });
        $scope.listTH = [];
        $http.get(urlthuonghieu).then(function (response) {
            $scope.listTH = response.data;
        });
        $scope.listPL = [];
        $http.get(urlphanloai).then(function (response) {
            $scope.listPL = response.data;
        });
        $scope.listXX = [];
        $http.get(urlxuatxu).then(function (response) {
            $scope.listXX = response.data;
        });
        $scope.listPC = [];
        $http.get(urlphongcach).then(function (response) {
            $scope.listPC = response.data;
        });
        $scope.listCL = [];
        $http.get(urlchatlieu).then(function (response) {
            $scope.listCL = response.data;
        });
        $scope.listMS = [];
        $http.get(urlmausac).then(function (response) {
            $scope.listMS = response.data;
        });
        $scope.listKT = [];
        $http.get(urlkichthuoc).then(function (response) {
            $scope.listKT = response.data;
        });


        // mausac, kichthuoc
        $scope.checkbox = function(mausac) {
            let listMS1 =  $scope.listMS;
            let listKT1 =  $scope.listKT;
            // Get the checkbox
            var checkBox = document.getElementById(mausac);
            // Get the output text
            var text = document.getElementById("text" + mausac);
            // If the checkbox is checked, display the output text
            for(let i = 0 ; i < listMS1.length; i++){
                if (listMS1[i].idMauSac === mausac){
                    if (checkBox.checked == true){
                        text.innerHTML += '<div ><label>Số lượng '+ listMS1[i].tenMauSac +'</label>';
                        for(let j = 0 ; j < listKT1.length; j++){
                            if (listKT1[j].trangThai == 0){
                                text.innerHTML += '<span style="padding-left: 10px; padding-bottom: 10px">Size</span> '+listKT1[j].tenKichThuoc+' <input id="MS'+listMS1[i].idMauSac+'KT'+listKT1[j].idKichThuoc+'" type="number" value="0" style="width: 50px">';
                            }

                        }
                        text.innerHTML += '</div>';
                    } else {
                        text.innerHTML = '';
                    }
                }
            }

        };

        //add san pham
        $scope.add = function(){
            var fileanh = document.getElementById("fileUpload").files;
            var reader = new FileReader();
            $http.post("/api/sanpham",{
                maSanPham : $scope.masanpham,
                tenSanPham : $scope.tensanpham,
                trangThai : 0,
                ngayTao : new Date()
            }).then(function(sanpham){
                if(sanpham.status === 200){
                    //add ảnh
                    var img = new FormData();
                    var fileanh = document.getElementById("fileUpload").files;
                    for (let i = 0; i < fileanh.length; i++) {
                        img.append('files',fileanh[i]);
                        $http.post("/api/upload",img, {
                            transformRequest: angular.identity,
                            headers: {
                                'Content-Type': undefined
                            }
                        }).then(function (image) {
                            $http.post("/api/anh",{
                                link : image.data[i],
                                tenAnh : sanpham.data.tenSanPham,
                                idSanPham : sanpham.data.idSanPham
                            });

                        });

                    }


                    //add ctsp
                    $http.post("/api/ctsp",{
                        giaBan : $scope.giaban,
                        giaNhap : $scope.gianhap,
                        giamGia : $scope.giamgia,
                        moTa : $scope.mota,
                        idDanhMuc : $scope.danhmuc,
                        idPhanLoai : $scope.phanloai,
                        idThuongHieu : $scope.thuonghieu,
                        idXuatXu : $scope.xuatxu,
                        idSanPham : sanpham.data.idSanPham,
                        ngayTao : new Date()
                    }).then(function(ctsp){
                        if(ctsp.status === 200){
                            //add phong cach
                            let listPC1 = $scope.listPC;
                           for(let i = 0 ; i < listPC1.length; i++){
                                var check = document.getElementById('PC'+listPC1[i].idPhongCach);
                                if(check.checked == true){
                                    $http.post("/api/phongcach-ctsp",{
                                        idPhongCach : listPC1[i].idPhongCach,
                                        idCTSP : ctsp.data.idCTSP
                                    });
                                }
                            }
                           //add chat lieu
                            let listCL1 = $scope.listCL;
                            for(let i = 0 ; i < listCL1.length; i++){
                                var checkcl = document.getElementById('CL'+listCL1[i].idChatLieu);
                                if(checkcl.checked == true){
                                    $http.post("/api/chatlieu-ctsp",{
                                        idChatLieu : listCL1[i].idChatLieu,
                                        idCTSP : ctsp.data.idCTSP
                                    });
                                }
                            }
                            //add mau sac va kich thuoc
                            let listMS2 = $scope.listMS;
                            let listKT = $scope.listKT;
                            for (let i = 0; i < listMS2.length; i++) {
                                let mausacc = document.getElementById(listMS2[i].idMauSac);
                                if (mausacc.checked === true){
                                    for (let j = 0; j < listKT.length; j++) {
                                        var soLuong = document.getElementById('MS' +listMS2[i].idMauSac + 'KT'+listKT[j].idKichThuoc).value;
                                           if (soLuong > 0){
                                               $http.post("/api/kichthuoc-mausac-ctsp",{
                                                   idMauSac : listMS2[i].idMauSac,
                                                   idKichThuoc : listKT[j].idKichThuoc,
                                                   idCTSP : ctsp.data.idCTSP,
                                                   soLuong : soLuong
                                               });
                                           }

                                    }
                                }

                            }
                        }
                    })
                    Swal.fire(
                        'Thông báo',
                        'Thêm sản phẩm thành công !',
                        'success'
                    );

                }
            })
        }

        //delete san pham
        $scope.delete = function(idCTSP){
            var check = window.confirm("Bạn chắc chắn muốn xóa sản phẩm ?");
            if(check === true){
                $http.put("/api/ctsp/" +idCTSP).then(function(response){
                    if(response.status === 200){
                        alert("Xóa thành công !");
                        window.location.reload();

                    }
                })
            }

        }

          //get form update
          //     var idctsp = document.getElementById("idctsp").value;
          //     alert(idctsp)
          //    $http.get("/api/ctsp/" + idctsp).then(function(detail){
          //        $scope.masanpham = detail.data.sanPham.maSanPham,
          //        $scope.tensanpham = detail.data.sanPham.tenSanPham,
          //            $scope.gianhap = detail.data.giaNhap,
          //            $scope.giaban = detail.data.giaBan,
          //            $scope.giamgia = detail.data.giamGia,
          //            $scope.mota = detail.data.moTa
          //
          //
          //    })

        $scope.update = function(){
            var idDanhMuc = document.getElementById("danhMuc").value;
            var idThuongHieu = document.getElementById("thuongHieu").value;
            var idPhanLoai = document.getElementById("phanLoai").value;
            var idXuatXu = document.getElementById("xuatXu").value;
            var idctsp = document.getElementById("idctsp").value;
            $http.delete("/api/phongcach-ctsp/"+ idctsp);
            $http.delete("/api/chatlieu-ctsp/"+ idctsp);
            $http.delete("/api/kichthuoc-mausac-ctsp/"+ idctsp);
            var fileanh = document.getElementById("fileUpload").files;
            //update ctsp
            $http.put("/api/ctsp/update/"+idctsp,{
                giaBan : $scope.detail.giaBan,
                giaNhap : $scope.detail.giaNhap,
                giamGia : $scope.detail.giamGia,
                moTa : $scope.detail.moTa,
                idDanhMuc : idDanhMuc,
                idPhanLoai : idPhanLoai,
                idThuongHieu : idThuongHieu,
                idXuatXu : idXuatXu,
                ngaySua : new Date()
            }).then(function (ctsp){
                //update sanpham
                    $http.put("/api/sanpham/update/"+ ctsp.data.sanPham.idSanPham,{
                        maSanPham : $scope.detail.sanPham.maSanPham ,
                        tenSanPham : $scope.detail.sanPham.tenSanPham,
                        ngaySua : new Date()
                    }).then(function (sanpham){
                        if (sanpham.status === 200){
                            //update ảnh
                            var img = new FormData();
                            var fileanh = document.getElementById("fileUpload").files;

                            if (fileanh.length > 0){
                                $http.delete("/api/anh/"+sanpham.data.idSanPham);
                                for (let i = 0; i < fileanh.length; i++) {
                                    img.append('files',fileanh[i]);
                                    $http.post("/api/upload",img, {
                                        transformRequest: angular.identity,
                                        headers: {
                                            'Content-Type': undefined
                                        }
                                    }).then(function (image) {
                                        $http.post("/api/anh",{
                                            link : image.data[i],
                                            tenAnh : sanpham.data.tenSanPham,
                                            idSanPham : sanpham.data.idSanPham
                                        });

                                    });

                                }
                            }

                        }
                    })
                //update phong cach
                let listPC2 = $scope.listPC;
                for(let i = 0 ; i < listPC2.length; i++){
                    var checkPC = document.getElementById('PC'+listPC2[i].idPhongCach);
                    if(checkPC.checked == true){
                        $http.post("/api/phongcach-ctsp",{
                            idPhongCach : listPC2[i].idPhongCach,
                            idCTSP : ctsp.data.idCTSP
                        });
                    }
                }

                //update chat lieu
                let listCL2 = $scope.listCL;
                for(let i = 0 ; i < listCL2.length; i++){
                    var checkcl = document.getElementById('CL'+listCL2[i].idChatLieu);
                    if(checkcl.checked == true){
                        $http.post("/api/chatlieu-ctsp",{
                            idChatLieu : listCL2[i].idChatLieu,
                            idCTSP : ctsp.data.idCTSP
                        });
                    }
                }

                //update mau sac va kich thuoc
                let listMS3 = $scope.listMS;
                let listKT1 = $scope.listKT;
                for (let i = 0; i < listMS3.length; i++) {
                    let mausacc = document.getElementById(listMS3[i].idMauSac);
                    if (mausacc.checked === true){
                        for (let j = 0; j < listKT1.length; j++) {
                            var soLuong = document.getElementById('MS' +listMS3[i].idMauSac + 'KT'+listKT1[j].idKichThuoc).value;
                            if (soLuong > 0){
                                $http.post("/api/kichthuoc-mausac-ctsp",{
                                    idMauSac : listMS3[i].idMauSac,
                                    idKichThuoc : listKT1[j].idKichThuoc,
                                    idCTSP : ctsp.data.idCTSP,
                                    soLuong : soLuong
                                });
                            }

                        }
                    }

                }
                Swal.fire("Sửa sản phẩm thành công","success");

            })

        }

        //detail
        $scope.detail = function (){
            var idctsp1 = document.getElementById("idctsp").value;
            $http.get("/api/ctsp/"+idctsp1).then(function (detail){
                $scope.detail = detail.data;
                for (let i = 0; i < detail.data.phongCach_ctspList.length; i++) {
                    document.getElementById('PC'+detail.data.phongCach_ctspList[i].phongCach.idPhongCach).checked = true;
                }
                for (let i = 0; i < detail.data.chatLieu_ctspList.length; i++) {
                    document.getElementById('CL'+detail.data.chatLieu_ctspList[i].chatLieu.idChatLieu).checked = true;
                }
                var check;
                for (let i = 0; i < detail.data.mauSac_kichThuoc_ctspList.length; i++) {
                    document.getElementById(detail.data.mauSac_kichThuoc_ctspList[i].mauSac.idMauSac).checked = true;

                        // document.getElementById('MS'+detail.data.mauSac_kichThuoc_ctspList[i].mauSac.idMauSac + 'KT'+detail.data.mauSac_kichThuoc_ctspList[i].kichThuoc.idKichThuoc).value = detail.data.mauSac_kichThuoc_ctspList[i].soLuong;

                }
                let listmau = $scope.listMS;
                for (let i = 0; i < listmau.length; i++) {
                    var checkBox = document.getElementById(listmau[i].idMauSac);
                    if (checkBox.checked == true){
                        $scope.checkbox(listmau[i].idMauSac);
                    }
                }
                for (let i = 0; i < detail.data.mauSac_kichThuoc_ctspList.length; i++) {
                    document.getElementById('MS'+detail.data.mauSac_kichThuoc_ctspList[i].mauSac.idMauSac + 'KT'+detail.data.mauSac_kichThuoc_ctspList[i].kichThuoc.idKichThuoc).value = detail.data.mauSac_kichThuoc_ctspList[i].soLuong;

                }




            })

        }










    });


