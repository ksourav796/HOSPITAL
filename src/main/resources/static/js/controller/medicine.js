app.controller('MedicineController',
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
        $scope.removeMedicine = function () {
            $scope.id = "";
            $scope.medicineName = "";
            $scope.code = "";
            $scope.uom = "";
        };
        $scope.BackServices=function(){
            window.location.href='#!/services_masters';
        }
        $scope.EditMedicine = function (data) {
            $scope.id = data.id;
            $scope.medicineName = data.medicineName;
            $scope.code = data.code;
            $scope.uom = data.unitOfMeasurement;
            $scope.operation = 'Edit';
            $("#submit").text("Update");
            $('#medicine-title').text("Edit Medicine");
            $("#add_medicine").modal('show');

        }, function (error) {
            Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
        };
        $scope.removeMedicine();

        $scope.getMedicineList = function (searchText) {
            if (angular.isUndefined(searchText)) {
                $scope.searchText = "";
            }
            $(".loader").css("display", "block");
            $http.post($scope.hospitalServerURL + '/getMedicine').then(function (response) {
                var data = response.data.object;
                $scope.medicineList = data;
                // $scope.searchText = val;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getPaginatedMedicineList = function (page) {
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
                    $scope.isPrev = false;
                    $scope. isNext = false;
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
                    $scope.isPrev = false;
                    $scope.isNext = false;
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
            if (angular.isUndefined($scope.searchText)) {
                $scope.searchText = "";
            }
            $http.post($scope.hospitalServerURL + '/getPaginatedMedicineList?&searchText=' + $scope.searchText, angular.toJson(paginationDetails)).then(function (response) {
                var data = response.data;
                $scope.medicineList = data.list;
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
        $scope.getPaginatedMedicineList();

        $scope.feeconfigurationList=function () {
            $window.location.href = '/home#!/services_master';
        };

        $scope.popUpDivHide = function (val) {
            $scope.popUPValue = val;
            $timeout(function () {
                $("#" + $scope.popUPValue).hide();
            }, 500);

        }

        $scope.popUpDivShow = function (val) {
            $timeout(function () {

                if (val === 'medicinePopup') {
                    if ($scope.medicineList.length <= 0) {
                        Notification.error({
                            message: 'Atleast One Item has to be selected ',
                            positionX: 'center',
                            delay: 2000
                        });
                        return false;
                    } else {
                        $("#" + val).show();
                        if ($scope.tenderedAmt == 0) {
                            $scope.paymentTotalBalance = $scope.paymentTotalDue;
                        } else {
                            $scope.paymentDue('cash');
                        }
                        $scope.cashShow = true;
                        var color = $scope.cashShow = true ? '#FFA500' : 'orange';
                        $("#cashButton").css('background-color', color);
                        $scope.cardShow = false;
                        var color = $scope.cardShow = false ? '#376092' : '';
                        $("#cardButton").css('background-color', color);
                        $scope.creditNoteShow = false;
                        var color = $scope.creditNoteShow = false ? '#376092' : '';
                        $("#creditButton").css('background-color', color);
                        $scope.CouponShow = false;
                        var color = $scope.CouponShow = false ? '#376092' : '';
                        $("#couponButton").css('background-color', color);
                        $scope.eWalletShow = false;
                        var color = $scope.eWalletShow = false ? '#376092' : '';
                        $("#eWalletButton").css('background-color', color);
                        $scope.giftShow = false;
                        var color = $scope.giftShow = false ? '#376092' : '';
                        $("#giftButton").css('background-color', color);
                        $scope.chequeShow = false;
                        var color = $scope.chequeShow = false ? '#376092' : '';
                        $("#chequeButton").css('background-color', color);
                    }
                }
                else {
                    $("#" + val).show();
                }
            }, 500);
        }

        $scope.isVisible = false;
        $scope.isItemVisible = false;
        $scope.isBatchItem = false;
        $scope.isAgent = false;
        $scope.isUser = false;
        $scope.displayPopUp = function (popUpValue, itemCode) {
            $timeout(function () {
                $("#" + popUpValue).show();
            }, 500);
            if(angular.isUndefined(itemCode)){
                itemCode="";
            }
            $http.post( $scope.hospitalServerURL  + '/getMedicine?type=' + "Active"+'&searchText='+medicineName).then(function (response) {
                var data = response.data;
                $scope.itemList = angular.copy(data);
                // $scope.serializableItemsList = data[0].serializableItemsDTOList;
            })
            if (popUpValue === 'itemPopup') {
                $scope.isItemVisible = true;
            } else if (popUpValue === 'batchItemPopup') {
                $scope.isBatchItem = true;
            } else if (popUpValue === 'DoctorPopUp') {
                $scope.isAgent = true;
            }
            else if (popUpValue === 'UserPopup') {
                $scope.isUser = true;
            }
            else {
                $scope.isVisible = true;
            }
        }

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
        $scope.getUOMList();

        $scope.addMedicine = function () {
            $scope.operation='create';
            // $scope.removeCategories();
            $("#submit").text("save");
            $('#medicine-title').text("Add Medicine");
            $("#add_medicine").modal('show');
        };
        $scope.removeMedicine();

        $scope.importPopup = function(){
            $("#import_Medicine").modal('show');
        }

        $scope.saveMedicineImport = function(){
            $scope.isDisabled= true;
            var formElement = document.getElementById("medicineDetails");
            var medicineDetails = new FormData(formElement);
            $http.post($scope.hospitalServerURL + '/medicineImportSave',medicineDetails,
                { headers: {'Content-Type': undefined},
                    transformRequest: angular.identity,
                }).then(function (response) {
                    $("#import_Medicine").modal('hide');
                    $scope.getPaginatedMedicineList();
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

        $scope.saveMedicine = function () {
            if (angular.isUndefined($scope.medicineName) || $scope.medicineName == '') {
                Notification.warning({message: 'Please Enter MedicineName', positionX: 'center', delay: 2000});

            } else {
                var saveMedicineDetails;
                saveMedicineDetails = {
                    id: $scope.id,
                    medicineName: $scope.medicineName,
                    code: $scope.code,
                    unitOfMeasurement: $scope.uom
                };
                $http.post($scope.hospitalServerURL + "/saveMedicineDetails", angular.toJson(saveMedicineDetails)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#add_medicine").modal('hide');
                        if($scope.operation=='Edit'){
                            Notification.success({
                                message: 'Medicines is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }else {
                            Notification.success({
                                message: 'Medicines is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        $scope.removeMedicine();
                        $scope.getPaginatedMedicineList();
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
        $scope.DeleteMedicine = function (data) {
            // if (data.gradeStatus == "InActive" && data.gradeStatus != "Active") {
            //     Notification.warning({
            //         message: 'Cannot Delete Aleady in use',
            //         positionX: 'center',
            //         delay: 2000
            //     });
            // }
            // else {
            bootbox.confirm({
                title: "Alert",
                message: "Do you want to Continue ?",
                buttons: {
                    confirm: {
                        label: 'OK'
                    },
                    cancel: {
                        label: 'Cancel'
                    }
                },
                callback: function (result) {
                    if (result == true) {
                        var deleteDetails = {
                            id: data.id,
                            medicineName: data.medicineName,
                            code: data.code,
                            unitOfMeasurement: data.unitOfMeasurement
                        };
                        $http.post($scope.hospitalServerURL + "/deleteDetails", angular.toJson(deleteDetails, "Create")).then(function (response, status, headers, config) {
                            var data = response.data;
                            $scope.getPaginatedMedicineList();
                            if(data==true){
                                Notification.success({
                                    message: 'Successfully Deleted',
                                    positionX: 'center',
                                    delay: 2000
                                });
                            }else {
                                Notification.warning({
                                    message: 'Cannot delete Already in Use',
                                    positionX: 'center',
                                    delay: 2000
                                });
                            }
                        }, function (error) {
                            Notification.warning({
                                message: 'Cannot delete Already in Use',
                                positionX: 'center',
                                delay: 2000
                            });
                        });
                    }
                }
            });
        };
    });
