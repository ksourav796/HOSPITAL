/**
 * Created by sahera on 3/1/2018.
 */
app.controller('cityCtrl',
    function ($scope, $http, $location, $filter, $timeout, Notification) {// body...
        $scope.hospitalServerURL = "/hospital";
        $scope.firstPage = true;
        $scope.lastPage = false;
        $scope.pageNo = 0;
        $scope.prevPage = false;
        $scope.isPrev = false;
        $scope.isNext = false;
        $scope.inactiveStatus = "Active";
        /***Method which sets all values to null**/
        $scope.removeCityDetails = function () {
            $scope.cityNameText = "";
            $scope.cityId = "";
            $scope.StatusText = "";
        };
        $scope.clicked = false;
        $scope.ButtonStatus = "InActive";
        $scope.inactiveCity = function () {

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

            $scope.getCityList();
        };

        $scope.backServices=function(){
            window.location.href='#!/services_masters';
        }

        $scope.getCountryList = function () {
            $http.post($scope.hospitalServerURL + '/getCountryList?type='+"Active").then(function (response) {
                var data = response.data;
                $scope.country = angular.copy(data);
            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            });
        };
        $scope.getCountryList();

        $scope.importPopup = function(){
            $("#import_city").modal('show');
        }

        $scope.saveCityImport = function(){
            $scope.isDisabled= true;
            var formElement = document.getElementById("cityDetails");
            var cityDetails = new FormData(formElement);
            $http.post($scope.hospitalServerURL + '/cityImportSave',cityDetails,
                { headers: {'Content-Type': undefined},
                    transformRequest: angular.identity,
                }).then(function (response) {
                    $("#import_city").modal('hide');
                    $scope.getCityList();
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

        /****Method for getting the State List*******/
        $scope.getCityList = function (type, page,citySearchText) {
            if (angular.isUndefined(type,citySearchText)) {
                type = "";
                $scope.citySearchText="";

            }
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
            if (angular.isUndefined($scope.citySearchText)) {
                $scope.citySearchText = "";
            }
            $http.post($scope.hospitalServerURL + '/getCityList?type=' + $scope.inactiveStatus + '&searchText=' + $scope.citySearchText, angular.toJson(paginationDetails)).then(function (response) {
                var data = response.data;
                console.log(data);
                $scope.cityList = data.list;
                $scope.first = data.firstPage;
                $scope.last = data.lastPage;
                $scope.prev = data.prevPage;
                $scope.next = data.nextPage;
                $scope.pageNo = data.pageNo;
                /**/
                var i = 0;
                $scope.listStatus = true;
                angular.forEach($scope.cityList, function (value, key) {
                    if (value.cityName.toUpperCase() == type.toUpperCase()) {
                        if (value.status == 'InActive') {
                            i++;
                        }
                    }
                })
                if (i > 0) {
                    Notification.error({
                        message: 'City is InActive',
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
            })
        };

        /*****Method for getting the country List*****/

        $scope.countryState = function(country){
            var url = "/hospital/getCountryState?countryName=" + country;
            $http.post(url).then(function (response) {
                var data = response.data;
                $scope.stateList = angular.copy(data);
            })

        }

        $scope.getCountryState =function(countryName){
            $http.post($scope.hospitalServerURL + "/getStateListBasedOnCountry?countryName=" +countryName).then(function (response) {
                var data = response.data;
                $scope.stateList=data;

            });
        }


        /***Method for add POPUP*******/
        $scope.addNewCity = function () {
            $scope.cityNameText = "";
            $scope.cityId = "";
            $scope.StatusText = "";
            $scope.stateId = null;
            $scope.countryName = null;
            $scope.StatusText = "Active";
            $("#submit").text("Save");
            $('#city-title').text("Add City");
            $("#add_new_City_modal").modal('show');
            // $scope.getStateList();

        };


        $scope.addNewcountryPopulate = function () {
            $scope.countryName=null;
            $('#country-title').text("Add Country");
            $scope.StatusText = "Active";
            $("#submit").text("Save");
            $("#add_new_country_modal").modal('show');
        };


        $scope.addNewState = function () {
            $scope.countryName=null;
            $scope.state=null;
            $scope.StatusText = "Active";
            $("#submit").text("Save");
            $('#state-title').text("Add State");
            $("#add_new_State_modal").modal('show');
            // $scope.getStateList();

        };


        $scope.addpluscity = function () {

            $scope.stateId = null;
            $scope.countryName = null;
            $scope.StatusText = "Active";
            $("#submit").text("Save");
            $('#city-title').text("Add City");
            $("#add_new_pluscity_modal").modal('show');
            // $scope.getStateList();

        };

        /******edit Popup with values for state*******/
        $scope.editCity = function (name) {
            $http.post($scope.hospitalServerURL + '/editCity?cityName=' + name).then(function (response) {
                var data = response.data;
                $scope.stateId = data.state;
                $scope.cityObj = data;
                $scope.id = data.id;
                $scope.cityNameText = data.cityName;
                $scope.StatusText = data.status;
                $scope.countryName = data.countryName;
                $('#city-title').text("Edit City");
                $("#submit").text("Update");
                $("#add_new_City_modal").modal('show');
            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            });
        }, function (error) {
            Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
        };
        /*****Method for saving the state Details*******/
        $scope.saveCity = function () {
            if (angular.isUndefined($scope.countryName) || $scope.countryName == '' || $scope.countryName == null) {
                Notification.warning({message: ' Please Select the Country', positionX: 'center', delay: 2000});
            }
            else  if (angular.isUndefined($scope.stateId) || $scope.stateId == ''||$scope.stateId == null) {
                Notification.warning({message: 'Select state', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.cityNameText) || $scope.cityNameText == ''|| $scope.cityNameText == null) {
                Notification.warning({message: ' Please Enter the City', positionX: 'center', delay: 2000});
            }
            else {
                var saveCityDetails;
                saveCityDetails = {
                    id: $scope.id,
                    status: $scope.StatusText,
                    cityName: $scope.cityNameText,
                    state: $scope.stateId,
                    countryName: $scope.countryName
                };
                $http.post($scope.hospitalServerURL + '/saveCity', angular.toJson(saveCityDetails, "Create")).then(function (response, status, headers, config) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $scope.removeCityDetails();
                        $scope.getCityList();
                        $("#add_new_City_modal").modal('hide');
                        if (!angular.isUndefined(data) && data !== null) {
                            $scope.cityNameText = "";
                        }
                        Notification.success({
                            message: 'City  Created  successfully',
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

            };
        };


        /*****Method for saving the Country Details*******/

        $scope.saveCountry = function () {
            if (angular.isUndefined($scope.CountryNameText) || $scope.CountryNameText == ''||$scope.CountryNameText==null) {
                Notification.warning({message: ' Enter country Name', positionX: 'center', delay: 2000});
            }
            else {
                var saveCountryDetails;
                saveCountryDetails = {
                    country: $scope.countryId,
                    countryName: $scope.CountryNameText,
                    status: $scope.StatusText
                };
                $http.post($scope.hospitalServerURL + '/saveCountry', angular.toJson(saveCountryDetails, "Create")).then(function (response, status, headers, config) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#add_new_country_modal").modal('hide');
                        if (!angular.isUndefined(data) && data !== null) {
                            $scope.countrySearchText = "";
                        }
                        Notification.success({
                            message: 'Country Created  successfully',
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
            ;
        };


        /*****Method for saving the state Details*******/
        $scope.saveState = function () {
            if (angular.isUndefined($scope.countryId) || $scope.countryId == ''||$scope.countryId==null) {
                Notification.warning({message: ' Please select Country', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.stateNameText) || $scope.stateNameText == ''||$scope.stateNameText==null) {
                Notification.warning({message: ' Please Enter the StateName', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.stateNameText1) || $scope.stateNameText1 == ''||$scope.stateNameText1==null) {
                Notification.warning({message: ' Please Enter the StateName', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.stateNameText2) || $scope.stateNameText2 == ''||$scope.stateNameText2==null) {
                Notification.warning({message: ' Please Enter the StateName', positionX: 'center', delay: 2000});
            }

            else {
                var saveStateDetails;
                saveStateDetails = {
                    id: $scope.id,
                    status: $scope.StatusText,
                    stateName: $scope.stateNameText,
                    stateName: $scope.stateNameText1,
                    stateName: $scope.stateNameText2,
                    country: $scope.countryId
                };
                $http.post($scope.hospitalServerURL + '/saveState', angular.toJson(saveStateDetails, "Create")).then(function (response, status, headers, config) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        // $scope.getStateList();
                        $("#add_new_State_modal").modal('hide');
                        if (!angular.isUndefined(data) && data !== null) {
                            $scope.stateNameText = "";
                            $scope.stateNameText1 = "";
                            $scope.stateNameText2 = "";
                        }
                        Notification.success({
                            message: 'State  Created  successfully',
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
        };



        $scope.saveplusCity = function () {
            if (angular.isUndefined($scope.countryName) || $scope.countryName == '' || $scope.countryName == null) {
                Notification.warning({message: ' Please Enter the Country', positionX: 'center', delay: 2000});
            }
            if (angular.isUndefined($scope.cityNameText) || $scope.cityNameText == ''||$scope.cityNameText==null) {
                Notification.warning({message: ' Please Enter the City', positionX: 'center', delay: 2000});
            }
            if (angular.isUndefined($scope.stateId) || $scope.stateId == ''||$scope.stateId==null) {
                Notification.warning({message: 'Select state', positionX: 'center', delay: 2000});
            }
            else {
                var saveCityDetails;
                saveCityDetails = {
                    id: $scope.id,
                    status: $scope.StatusText,
                    cityName: $scope.cityNameText,
                    state: $scope.stateId,
                    countryName: $scope.countryName
                };
                $http.post($scope.hospitalServerURL + '/saveCity', angular.toJson(saveCityDetails, "Create")).then(function (response, status, headers, config) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $scope.removeCityDetails();
                        $scope.getCityList();
                        $("#add_new_pluscity_modal").modal('hide');
                        if (!angular.isUndefined(data) && data !== null) {
                            $scope.cityNameText = "";
                        }
                        Notification.success({
                            message: 'City  Created  successfully',
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

            };
        };




        $scope.deleteCity = function (data) {
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
                        $scope.id = data.id;
                        $http.post($scope.hospitalServerURL + '/deleteCity?id=' + $scope.id).then(function (response, status, headers, config) {
                            var data = response.data;

                            if ($scope.StatusText == "InActive") {
                                $scope.getCityList("", "InActive");
                                Notification.success({
                                    message: 'City has been changed to Active',
                                    positionX: 'center',
                                    delay: 2000
                                });
                            }

                            else {
                                $scope.getCityList("", "");
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
        $scope.getCityList();
        // $scope.getStateList();
    });
