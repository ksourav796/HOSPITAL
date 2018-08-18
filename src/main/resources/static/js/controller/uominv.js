app.controller('uomController',
    function($scope, $http, $location, $filter, Notification, ngTableParams,  $timeout, $window, $rootScope) {
        $scope.hospitalServerURL = "/hospital";
        $scope.word = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
        $scope.customerId = 1;
        $scope.userRights = [];
        $scope.operation = 'Create';
        $scope.customer = 1;
        $scope.firstPage = true;
        $scope.lastPage = false;
        $scope.pageNo = 0;
        $scope.prevPage = false;
        $scope.isPrev = false;
        $scope.isNext = false;
        $scope.today = new Date();
        // $route.reload();
        // $scope.removeCategories = function () {
        //     $scope.id = "";
        //     $scope.name = "";
        //     $scope.description = "";
        // };
        // $scope.BackServices=function(){
        //     window.location.href='#!/services_masters';
        // }



        $scope.removeUom = function () {
            $scope.unitofmeasurementid = "";
            $scope.UOMName = "";
            $scope.UOMDescription = "";
        };


        $scope.BackServices=function(){
            window.location.href='#!/services_masters';
        }
        $scope.editNewUOM = function (data) {
            $scope.uominvObj = data;
            $scope.unitofmeasurementid = data.unitOfMeasurementId;
            $scope.UOMName=data.unitOfMeasurementName;
            $scope.UOMDescription=data.unitOfMeasurementDescription;
            $scope.uomStatusText=data.unitOfMeasurementStatus;
            $scope.StatusText=data.status;
            $("#submit").text("Update");
            $('#uominv-title').text("Edit UnitOfMeasurement");
            $("#add_new_UOM_modal").modal('show');

        }, function (error) {
            Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
        };
        // $scope.removeCategories();

        //
        $scope.getUOMList = function (searchText) {
            if (angular.isUndefined(searchText)) {
                $scope.searchText = "";
            }
            $(".loader").css("display", "block");
            $http.post($scope.hospitalServerURL + '/getUOMList').then(function (response) {
                var data = response.data.object;
                $scope.UOMList = data;
                // $scope.searchText = val;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };

        $scope.feeconfigurationList=function () {
            $window.location.href = '/home#!/services_master';
        };

        $scope.getPaginatedUomList = function (page) {
            switch (page) {
                case 'firstPage':
                    $scope.firstPage = true;
                    $scope.lastPage = false;
                    $scope.isNext = false;
                    $scope.isPrev = false;
                    $scope.pageNo = 0;
                    break;
                case 'lastPage':
                    $scope.lastPage = true;
                    $scope.firstPage = false;
                    $scope.isNext = false;
                    $scope.isPrev = false;
                    $scope.pageNo = 0;
                    break;
                case 'nextPage':
                    $scope.isNext = true;
                    $scope.isPrev = false;
                    $scope.lastPage = false;
                    $scope.firstPage = false;
                    $scope.pageNo = $scope.pageNo + 1;
                    break;
                case 'prevPage':
                    $scope.isPrev = true;
                    $scope.lastPage = false;
                    $scope.firstPage = false;
                    $scope.isNext = false;
                    $scope.pageNo = $scope.pageNo - 1;
                    break;
                default:
                    $scope.firstPage = true;
                    $scope.lastPage = false;
                    $scope.isNext = false;
                    $scope.isPrev = false;
                    $scope.pageNo = 0;
            }
            var paginationDetails;
            paginationDetails = {
                firstPage: $scope.firstPage,
                lastPage: $scope.lastPage,
                pageNo: $scope.pageNo,
                prevPage: $scope.prevPage,
                prevPage: $scope.isPrev,
                nextPage: $scope.isNext
            }
            if (angular.isUndefined($scope.UOMSearchText)) {
                $scope.UOMSearchText = "";
            }
            $http.post($scope.hospitalServerURL + '/getPaginatedUomList?&searchText=' + $scope.UOMSearchText, angular.toJson(paginationDetails)).then(function (response) {
                var data = response.data;
                $scope.UOMList = data.list;
                $scope.first = data.firstPage;
                $scope.last = data.lastPage;
                $scope.prev = data.prevPage;
                $scope.next = data.nextPage;
                $scope.pageNo = data.pageNo;
                $scope.listStatus = true;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getPaginatedUomList();

        $scope.addNewUOMPopulate = function () {
            $scope.unitofmeasurementid = "";
            $scope.UOMName = "";
            $scope.UOMDescription = "";
            $("#submit").text("save");
            $('#uominv-title').text("Add UnitOfMeasurement");
            $("#add_new_UOM_modal").modal('show');
        };
        // $scope.removeCategories();

        $scope.importPopup = function(){
            $("#import_Uom").modal('show');
        }

        $scope.saveUomImport = function(){
            $scope.isDisabled= true;
            var formElement = document.getElementById("uomDetails");
            var uomDetails = new FormData(formElement);
            $http.post($scope.hospitalServerURL + '/uomImportSave',uomDetails,
                { headers: {'Content-Type': undefined},
                    transformRequest: angular.identity,
                }).then(function (response) {
                    $("#import_Uom").modal('hide');
                    $scope.getPaginatedUomList();
                    $scope.isDisabled= false;
                }, function (error) {
                    Notification.error({
                        message: 'Something went wrong, please try again',
                        positionX: 'center',
                        delay: 2000
                    });
                    $scope.isDisabled= false;
                }
            )
        }

        $scope.saveNewUOM = function () {
            if (angular.isUndefined($scope.UOMName) || $scope.UOMName == '') {
                Notification.warning({message: 'Please Enter unitOfMeasurementName ', positionX: 'center', delay: 2000});

            } else {
            var saveUomDetails;
            saveUomDetails = {
                unitOfMeasurementId: $scope.unitofmeasurementid,
                unitOfMeasurementName: $scope.UOMName,
                unitOfMeasurementDescription: $scope.UOMDescription
                // unitOfMeasurementStatus: $scope.uomStatusText
            };
                $http.post($scope.hospitalServerURL + "/saveNewUOM", angular.toJson(saveUomDetails)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#add_new_UOM_modal").modal('hide');
                        if($scope.operation=='Edit'){
                            Notification.success({
                                message: 'uom is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }else {
                            Notification.success({
                                message: 'uom is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        $scope.getPaginatedUomList();
                        // $scope.removeCategories();
                        // $scope.getCategoriesList();
                    }
                }, function (error) {
                    Notification.error({
                        message: 'Something went wrong, please try again',
                        positionX: 'center',
                        delay: 2000
                    });
                });
            }
        };
        // $scope.deleteUOMItem = function (data) {
        //     // if (data.gradeStatus == "InActive" && data.gradeStatus != "Active") {
        //     //     Notification.warning({
        //     //         message: 'Cannot Delete Aleady in use',
        //     //         positionX: 'center',
        //     //         delay: 2000
        //     //     });
        //     // }
        //     // else {
        //     bootbox.confirm({
        //         title: "Alert",
        //         message: "Do you want to Continue ?",
        //         buttons: {
        //             confirm: {
        //                 label: 'OK'
        //             },
        //             cancel: {
        //                 label: 'Cancel'
        //             }
        //         },
        //         callback: function (result) {
        //             if (result == true) {
        //                 var deleteDetails = {
        //                     id: data.id,
        //                     name: data.name,
        //                     description: data.description
        //                 };
        //                 $http.post($scope.hospitalServerURL + "/deleteUOMItem", angular.toJson(deleteDetails, "Create")).then(function (response, status, headers, config) {
        //                     var data = response.data;
        //                     $scope.getCategoriesList();
        //                     if(data==true){
        //                         Notification.success({
        //                             message: 'Successfully Deleted',
        //                             positionX: 'center',
        //                             delay: 2000
        //                         });
        //                     }else {
        //                         Notification.warning({
        //                             message: 'Cannot delete Already in Use',
        //                             positionX: 'center',
        //                             delay: 2000
        //                         });
        //                     }
        //                 }, function (error) {
        //                     Notification.warning({
        //                         message: 'Cannot delete Already in Use',
        //                         positionX: 'center',
        //                         delay: 2000
        //                     });
        //                 });
        //             }
        //         }
        //     });
        // };
    });
