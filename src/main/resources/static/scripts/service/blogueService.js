/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module('aplikasiBlogue')
        .factory('blogueService',function ($http) {
            return {
                getBlogue: function() {
                    return $http.get("/blogue");
                },
                getListBlogue: function() {
                    return $http.get("/listblogue");
                },
                save: function (data) {
                    return $http.post("/api/blogue",data);
                },
                getBlogueFromDb: function () {
                    return $http.get("/api/blogue");
                },
                delete: function(id) {
                    return $http.delete("/api/blogue/"+id);
                },
                update: function(id,data) {
                    return $http.put("/api/blogue/"+id,data);
                }
            };
});