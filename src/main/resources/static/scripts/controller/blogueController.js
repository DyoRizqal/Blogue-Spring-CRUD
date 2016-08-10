/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module('aplikasiBlogue')
        .controller('blogueController',function ($scope,blogueService){
            $scope.hallo = "Hello";
            $scope.nama = "Blogue";
            $scope.model = {};
            $scope.isEdit = false;
            
            $scope.tampilkanData = function() {
                blogueService.getListBlogue().success(function (data){
                    $scope.dataServer = data;
                })
            };
            
            $scope.data = [
                {"nama": "Blogue","alamat": "Jakarta"},{"nama": "Go Blog","alamat": "Jakarta"}
            ];
            
            $scope.hapus = function (index){
                $scope.data.splice(index,1);
            };
            
            $scope.simpan = function(data){
                if ($scope.isEdit==true){
                    $scope.data[$scope.indexEdit] = data;
                }
                else{
                    $scope.data.push(data);
                }
                $scope.clear();
            }
            
            $scope.clear = function () {
                $scope.model = {};
                $scope.isEdit = false;
            }
            
            $scope.edit = function (data,index) {
                $scope.model.nama = data.nama;
                $scope.model.alamat = data.alamat;
                $scope.indexEdit = index;
                $scope.isEdit = true;
            }
            
            $scope.tampilkanData = function () {
                blogueService.getBlogueFromDb().success(function (data){
                   $scope.dataServer = data.content; 
                });
            };
            
            $scope.isDataEdit = false;
            $scope.currentBlogue = {};
            $scope.simpanData = function (data){
                if ($scope.isDataEdit==true){
                    blogueService.update(data.id,data).success(function (data){
                       alert("data berhasil dirubah")
                       $scope.clearData();
                    }).error(function (){
                        alert("terjadi kesalahan dalam penyimpanan data")
                    })
                }
                else{
                    blogueService.save(data).success(function (data){
                        alert("data berhasil disimpan")
                        $scope.clearData();
                    }).error(function (){
                        alert("Terjadi kesalahan dalam menyimpan data")
                    })
                }
            }
            
            $scope.hapusData = function(id) {
                blogueService.delete(id).success(function (data){
                    alert("data berhasil dihapus");
                    $scope.clearData();
                }).error(function (){
                    alert("terjadi kesalahan dalam penghapusan data");
                })
            }
            
            $scope.editData = function (data){
                $scope.isDataEdit = true;
                $scope.currentBlogue = {};
                $scope.currentBlogue.id = data.id;
                $scope.currentBlogue.tanggal = data.tanggal;
                $scope.currentBlogue.judul = data.judul;
                $scope.currentBlogue.isi = data.isi;
                $scope.currentBlogue.author = data.author;
                
            };
            
            $scope.clearData = function (){
                $scope.isDataEdit = false;
                $scope.currentBlogue = {};
                $scope.reloadData();
            }
            
            $scope.reloadData = function (){
                blogueService.getBlogueFromDb().success(function (data){
                    $scope.dataServer = data.content;
                })
            }
});