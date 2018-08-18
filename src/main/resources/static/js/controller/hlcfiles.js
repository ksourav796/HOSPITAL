app.directive("hlcController", function ($http, $timeout, $window, Notification) {
    return {
        restrict: 'E',
        templateUrl: "partials/hlcfiles.html",
        link: function ($scope) {

            $scope.format = 'dd/MM/yyyy';

            $scope.open1 = function () {
                $scope.popup1.opened = true;
            };
            $scope.popup1 = {
                opened: false
            };


            $scope.addNewState = function () {
                $scope.StatusText = "Active";
                $scope.countryId=null;
                $scope.stateNameText="";
                $("#submit").text("Save");
                $('#state-title').text("Add State");
                $("#add_new_State_modal").modal('show');
                // $scope.getStateList();

            };

            $scope.addNewCity = function () {
                $scope.getCityList();
                $scope.countryName=null;
                $scope.stateId=null;
                $scope.stateList=[];
                $scope.cityNameText="";
                $scope.StatusText = "Active";
                $("#submit").text("Save");
                $('#city-title').text("Add City");
                $("#add_new_City_modal").modal('show');
                // $scope.getStateList();

            };


            $scope.addNewcountryPopulate = function () {
                $('#country-title').text("Add Country");
                $scope.CountryNameText="";
                $scope.StatusText = "Active";
                $("#submit").text("Save");
                $("#add_new_country_modal").modal('show');
            };

            $scope.saveCountry = function () {
                if ($scope.CountryNameText === ''||$scope.CountryNameText==null||angular.isUndefined($scope.CountryNameText)) {
                    Notification.warning({message: 'Country Name can not be empty', positionX: 'center', delay: 2000});
                }
                else {
                    var saveCountryDetails;
                    saveCountryDetails = {
                        countryId: $scope.countryId,
                        countryName: $scope.CountryNameText,
                        status: $scope.StatusText
                    };
                    $http.post($scope.hospitalServerURL + '/saveCountry', angular.toJson(saveCountryDetails, "Create")).then(function (response, status, headers, config) {
                        var data = response.data;
                        if (data == "") {
                            Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                        }
                        else {
                            $scope.getCountryList();
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


            $scope.saveState = function () {
                if (angular.isUndefined($scope.stateNameText) || $scope.stateNameText == ''||$scope.stateNameText==null) {
                    Notification.warning({message: ' stateName can not be Empty', positionX: 'center', delay: 2000});
                }
                else if (angular.isUndefined($scope.countryId) || $scope.countryId == ''||$scope.countryId==null) {
                    Notification.warning({message: ' country can not be Empty', positionX: 'center', delay: 2000});
                }
                else {
                    var saveStateDetails;
                    saveStateDetails = {
                        id: $scope.id,
                        status: $scope.StatusText,
                        stateName: $scope.stateNameText,
                        country: $scope.countryId
                    };
                    $http.post($scope.hospitalServerURL + '/saveState', angular.toJson(saveStateDetails, "Create")).then(function (response, status, headers, config) {
                        var data = response.data;
                        if (data == "") {
                            Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                        }
                        else {
                            $scope.getStateList();
                            $scope.getCountryList();
                            $("#add_new_State_modal").modal('hide');
                            if (!angular.isUndefined(data) && data !== null) {
                                $scope.stateNameText = "";
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
                ;
            };

            $scope.saveCity = function () {
                if (angular.isUndefined($scope.cityNameText) || $scope.cityNameText == ''||$scope.cityNameText==null) {
                    Notification.warning({message: ' city Name can not be Empty', positionX: 'center', delay: 2000});
                }
                else if (angular.isUndefined($scope.stateId) || $scope.stateId == ''||$scope.stateId==null) {
                    Notification.warning({message: ' State can not be Empty', positionX: 'center', delay: 2000});
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

                }
                ;
            };


            $scope.getStateList = function (type, page) {
                if (angular.isUndefined(type)) {
                    type = "";
                }
                switch (page) {
                    case 'firstPage':
                        $scope.firstPage = true;
                        $scope.lastPage = false;
                        $scope.pageNo = 0;
                        break;
                    case 'lastPage':
                        $scope.lastPage = true;
                        $scope.firstPage = false;
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
                if (angular.isUndefined($scope.stateSearchText)) {
                    $scope.stateSearchText = "";
                }
                $http.post($scope.hospitalServerURL + '/getStateList?type=' + type + '&searchText=' + $scope.stateSearchText, angular.toJson(paginationDetails)).then(function (response) {
                    var data = response.data;
                    console.log(data);
                    $scope.stateList = data.list;
                    $scope.first = data.firstPage;
                    $scope.last = data.lastPage;
                    $scope.prev = data.prevPage;
                    $scope.next = data.nextPage;
                    $scope.pageNo = data.pageNo;
                    /**/
                    var i = 0;
                    $scope.listStatus = true;
                    angular.forEach($scope.stateList, function (value, key) {
                        if (value.stateName.toUpperCase() == type.toUpperCase()) {
                            if (value.status == 'InActive') {
                                i++;
                            }
                        }
                    })
                    if (i > 0) {
                        Notification.error({
                            message: 'State is InActive',
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
            // $scope.getStateList();

            $scope.getCityList = function (type, page) {
                if (angular.isUndefined(type)) {
                    type = "";
                }
                switch (page) {
                    case 'firstPage':
                        $scope.firstPage = true;
                        $scope.lastPage = false;
                        $scope.pageNo = 0;
                        break;
                    case 'lastPage':
                        $scope.lastPage = true;
                        $scope.firstPage = false;
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
                $http.post($scope.hospitalServerURL + '/getCityList?type=' + type + '&searchText=' + $scope.citySearchText, angular.toJson(paginationDetails)).then(function (response) {
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
            // $scope.getCityList();

            $scope.countryState = function(country){
                var url = "/hospital/getCountryState?countryName=" + country;
                $http.post(url).then(function (response) {
                    var data = response.data;
                    $scope.stateList = angular.copy(data);
                })

            }

            $scope.stateCity = function(state){
                var url = "/hospital/getStateCity?stateName=" + state;
                $http.post(url).then(function (response) {
                    var data = response.data;
                    $scope.cityList = angular.copy(data);
                })

            }

            $scope.getCountryState =function(countryName){
                $http.post($scope.hospitalServerURL + "/getStateListBasedOnCountry?countryName=" +countryName).then(function (response) {
                    var data = response.data;
                    $scope.stateList=data;
                });
            }
            $scope.getStateCity=function(stateName){
                $http.post($scope.hospitalServerURL + "/getCityListBasedOnState?stateName=" +stateName).then(function (response) {
                    var data = response.data;
                    $scope.cityList=data;
                });
            }


            $scope.getCountryList = function () {
                $http.post($scope.hospitalServerURL + '/getCountryList').then(function (response) {
                    var data = response.data;
                    $scope.countryList = angular.copy(data);
                }, function (error) {
                    Notification.error({
                        message: 'Something went wrong, please try again',
                        positionX: 'center',
                        delay: 2000
                    });
                });
            };


            $scope.addNewSlot = function () {
                $("#add_new_Select_modal").modal('show');
            };

        }
    }
})