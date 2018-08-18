/**
 * Created by azgar.h on 7/1/2017.
 */
app.controller('currencyCtrl',
    function ($scope, $http, $location, $filter, Notification, ngTableParams, $timeout) {

        // body...\
        $scope.hospitalServerURL = "/hospital";
        $scope.userRights = [];
        $scope.operation = 'Create';
        $scope.firstPage = true;
        $scope.lastPage = false;
        $scope.pageNo = 0;
        $scope.prevPage = false;
        $scope.isPrev = false;
        $scope.isNext = false;
        $scope.today = new Date();

        $scope.inactiveStatus = "Active";
        $scope.removeCurrencyDetails = function () {
            $scope.currencyId="";
            $scope.CurrencyWordText = "";
            $scope.CurrencyCodeText = "";
            $scope.CurrencysymbolText = "";
            $scope.CurrencyDescriptionText = "";
            $scope.CurrencysymbolplaceText = "";
            $scope.AccountCodeText = "";
            $scope.operation = 'Create';
        };

        $scope.clicked = false;
        $scope.ButtonStatus = "InActive";
        $scope.inactiveCurrency = function (val) {
            if ($scope.clicked == false) {
                $scope.inactiveStatus = "InActive";
                $scope.ButtonStatus = "Active";
                var page = "Page";
            }
            else {
                $scope.inactiveStatus = "Active";
                $scope.ButtonStatus = "InActive";
                var page = "";
            }
            $scope.clicked = !$scope.clicked;

            if (angular.isUndefined(val)) {
                val = "";
            }
            $http.post( $scope.hospitalServerURL  + "/getCurrencyList?&type=" + $scope.inactiveStatus).then(function (response) {
                var data = response.data;
                $scope.currencyList = data;
                $scope.listStatus = false;
            })
        };

        $scope.backServices=function(){
            window.location.href='#!/services_masters';
        }


        $scope.getCurrencyList = function (type,currencySearchText) {
            if (angular.isUndefined(type,currencySearchText)) {
                type = "";
                $scope.currencySearchText="";

            }
            $http.post( $scope.hospitalServerURL  + "/getCurrencyList?&type=" + type).then(function (response) {
                var data = response.data;
                console.log(data);
                var i = 0;
                $scope.currencyList = data;
                $scope.listStatus = true;
                angular.forEach($scope.currencyList, function (value, key) {
                    if (value.currencyCode.toUpperCase() == type.toUpperCase()) {
                        if (value.status == 'InActive') {
                            i++;
                        }
                    }
                });
                if (i > 0) {
                    Notification.warning({
                        message: 'currency Became InActive',
                        positionX: 'center',
                        delay: 2000
                    });
                }
                $scope.removeCurrencyDetails();

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getPaginatedCurrencyList = function (page) {
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
            if (angular.isUndefined($scope.currencySearchText)) {
                $scope.currencySearchText = "";
            }
            $http.post($scope.hospitalServerURL + '/getPaginatedCurrencyList?&searchText=' + $scope.currencySearchText, angular.toJson(paginationDetails)).then(function (response) {
                var data = response.data;
                $scope.currencyList = data.list;
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
        $scope.getPaginatedCurrencyList();
        $scope.addNewCurrency = function () {
            $scope.currencyId = "";
            $scope.CurrencyWordText = "";
            $scope.CurrencyCodeText = "";
            $scope.CurrencysymbolText = "";
            $scope.CurrencyDescriptionText = "";
            $scope.CurrencysymbolplaceText = "";
            $scope.AccountCodeText = "";
            $scope.currencyStatusText = "Active";
            $scope.removeCurrencyDetails();
            $("#title").text("Add Currency");
            $("#submit").text("Save");
            $("#add_new_Currency_modal").modal('show');
        };
        $scope.populateCurrencyData = function () {
            var data = {
                CurrencyCode: $scope.CurrencyCode,
                CurrencyWord: $scope.CurrencyWord,
                AccountCode: $scope.AccountCode,
                CurrencyDescription: $scope.CurrencyDescription,
                Currencysymbol: $scope.Currencysymbol
            };

            console.log(data);
            return data;
        };


        $scope.importPopup = function(){
            $("#import_Currency").modal('show');
        }


        $scope.saveCurrencyImport = function(){
            $scope.isDisabled= true;
            var formElement = document.getElementById("currencyDetails");
            var currencyDetails = new FormData(formElement);
            $http.post($scope.hospitalServerURL + '/currencyImportSave',currencyDetails,
                { headers: {'Content-Type': undefined},
                    transformRequest: angular.identity,
                }).then(function (response) {
                    $("#import_Currency").modal('hide');
                    $scope.getPaginatedCurrencyList();
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

        $scope.saveNewCurrency = function () {
            if (angular.isUndefined($scope.CurrencyCodeText) || $scope.CurrencyCodeText == ''||$scope.CurrencyCodeText==null) {
                Notification.warning({message: 'Please Enter the Currency Code', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.CurrencyWordText) || $scope.CurrencyWordText == ''||$scope.CurrencyWordText==null) {
                Notification.warning({message: 'Please Enter the Currency Word  ', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.CurrencysymbolText) || $scope.CurrencysymbolText == ''||$scope.CurrencysymbolText==null) {
                Notification.warning({message: 'Currency symbol can not be Empty', positionX: 'center', delay: 2000});
            }
            else {
                var saveCurrencyDetails;
                saveCurrencyDetails = {
                    currencyId: $scope.currencyId,
                    currencyName: $scope.CurrencyCodeText,
                    currencyCode: $scope.CurrencyCodeText,
                    currencySymbol: $scope.CurrencysymbolText,
                    currencyDescription: $scope.CurrencyDescriptionText,
                    currencySymbolPlace: $scope.CurrencysymbolplaceText,
                    AccountCode: $scope.AccountCodeText,
                    status: $scope.currencyStatusText
                };
            }
            $http.post( $scope.hospitalServerURL  + '/saveCurrency', angular.toJson(saveCurrencyDetails, "Create")).then(function (response) {
                var data = response.data;
                if (data == "") {
                    Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                }
                else {
                    $("#add_new_Currency_modal").modal('hide');
                    Notification.success({message: 'Currency Created  successfully', positionX: 'center', delay: 2000});
                    $scope.removeCurrencyDetails();
                    $scope.getPaginatedCurrencyList();
                }
            }, function (error) {
            });
        };
        $scope.deleteCurrency = function (data) {
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
                        $http.post( $scope.hospitalServerURL  + '/deleteCurrency?currencyName=', data.currencyName).then(function (response, status, headers, config) {
                            var data = response.data;
                            if ($scope.currencyStatusText == "InActive") {
                                $scope.getCurrencyList("", "InActive");
                                Notification.success({
                                    message: 'Status has been changed to Active',
                                    positionX: 'center',
                                    delay: 2000
                                });
                            }
                            else {
                                $scope.getCurrencyList("", "");
                                Notification.success({
                                    message: 'Status has been changed to InActive',
                                    positionX: 'center',
                                    delay: 2000
                                });
                            }

                        }, function (error) {
                            Notification.error({
                                message: 'Something went wrong, please try again',
                                positionX: 'center',
                                delay: 2000
                            });
                        });
                    }
                }
            });
        };

        $scope.editCurrencyPopulate = function (name) {
            $http.post( $scope.hospitalServerURL  + '/editCurrency?currencyName=' + name).then(function (response) {
                var data = response.data;
                $scope.operation = 'Edit';
                $scope.currencyeditobj = data;
                $scope.currencyId = data.currencyId;
                $scope.CurrencyWordText = data.currencyName;
                $scope.CurrencyCodeText = data.currencyCode;
                $scope.CurrencysymbolText = data.currencySymbol;
                $scope.CurrencyDescriptionText = data.currencyDescription;
                $scope.CurrencysymbolplaceText = data.currencySymbolPlace;
                $scope.currencyStatusText = data.status;
                $("#add_new_Currency_modal").modal('show');
                $("#title").text("Edit Currency");
                $("#submit").text("Update");
            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            });
        };
    })
