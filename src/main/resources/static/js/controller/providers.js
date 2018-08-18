app.controller('providersController',
    function($scope, $http, $location, $filter, Notification, ngTableParams,  $timeout, $window, $rootScope) {
        $scope.hospitalServerURL = "/hospital";
        $scope.operation = 'Create';
        $scope.firstPage = true;
        $scope.lastPage = false;
        $scope.pageNo = 0;
        $scope.prevPage = false;
        $scope.isPrev = false;
        $scope.isNext = false;
        $scope.today = new Date();
        $scope.removeProviders = function () {
            $scope.id= "";
            $scope.firstName= "";
            $scope.lastName= "";
            $scope.email= "";
            $scope.phoneNumber= "";
            $scope.mobileNumber= "";
            $scope.zipCode= "";
            $scope.Calender= "";
            $scope.username= "";
            $scope.password= "";
            $scope.retypePassword= "";
            $scope.address= "";
            $scope.notes= "";
        };
        $scope.countryState = function(country){
            var url = "/hospital/getCountryState?countryName=" + country;
            $http.post(url).then(function (response) {
                var data = response.data;
                $scope.stateList = angular.copy(data);
                $scope.stateName="Karnataka";
                $scope.stateCity($scope.stateName);
            })

        }

        $scope.stateCity = function(state){
            var url = "/hospital/getStateCity?stateName=" + state;
            $http.post(url).then(function (response) {
                var data = response.data;
                $scope.cityList = angular.copy(data);
                $scope.city="Bengaluru";
            })

        }
        $scope.backServices=function(){
            window.location.href='#!/users';
        }



        $scope.importPopup = function(){
            $("#import_Providers").modal('show');
        }


        $scope.saveProvidersImport = function(){
            $scope.isDisabled= true;
            var formElement = document.getElementById("providersDetails");
            var providersDetails = new FormData(formElement);
            $http.post($scope.hospitalServerURL + '/providersImportSave',providersDetails,
                { headers: {'Content-Type': undefined},
                    transformRequest: angular.identity,
                }).then(function (response) {
                    $("#import_Providers").modal('hide');
                $scope.getProvidersList();
                $scope.getPaginatedProvidersList();
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


        $scope.addNewcountryPopulate = function () {
            $('#country-title').text("Add Country");
            $scope.StatusText = "Active";
            $("#submit").text("Save");
            $("#add_new_country_modal").modal('show');
        };

        $scope.addNewState = function () {
            $scope.StatusText = "Active";
            $("#submit").text("Save");
            $('#state-title').text("Add State");
            $("#add_new_State_modal").modal('show');
            // $scope.getStateList();

        };

        $scope.addNewCity = function () {
            $scope.StatusText = "Active";
            $("#submit").text("Save");
            $('#city-title').text("Add City");
            $("#add_new_City_modal").modal('show');
            $scope.getCityList();
            // $scope.getStateList();

        };

        $scope.saveCity = function () {
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


        $scope.saveCountry = function () {
            if (angular.isUndefined($scope.countryName) || $scope.countryName == ''||$scope.countryName==null) {
                Notification.warning({message: ' Enter country Name', positionX: 'center', delay: 2000});
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
                        $scope.removeCountryDetails();
                        $scope.getPaginatedCountryList();
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


        $scope.EditProviders = function (data) {
            $scope.id= data.id;
            $scope.providerId= data.providerId;
            $scope.firstName= data.id_users.firstName;
            $scope.lastName= data.id_users.lastName;
            $scope.email= data.id_users.email;
            $scope.phoneNumber= data.id_users.phoneNumber;
            $scope.mobileNumber= data.id_users.mobileNumber;
            $scope.city= data.id_users.city;
            $scope.stateName= data.id_users.state;
            $scope.countryName= data.id_users.country;
            $scope.zipCode= data.id_users.zipCode;
            $scope.Calender= data.id_users.calenderType;
            $scope.username= data.id_users.username;
            $scope.password= data.id_users.password;
            $scope.retypePassword= data.id_users.retypepassword;
            $scope.address= data.id_users.address;
            $scope.notes= data.id_users.notes;
                $scope.operation = 'Edit';
            $("#submit").text("Update");
            // $scope.getServicesList();
            $('#users-providers-title').text("Edit Providers");
            $("#add_users_providers").modal('show');

        }, function (error) {
            Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
        };
        $scope.getProvidersList = function (searchText) {
            if(angular.isUndefined(searchText)){
                $scope.searchText="";
            }
            $(".loader").css("display", "block");
            $http.post($scope.hospitalServerURL + '/providersList').then(function (response) {
                var data = response.data.object;
                $scope.providersList = data;
                console.log("providers list:")
                console.log($scope.providersList);
                // $scope.searchText = val;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getPaginatedProvidersList = function (page) {
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
            if (angular.isUndefined($scope.searchText)) {
                $scope.searchText = "";
            }
            $http.post($scope.hospitalServerURL + '/getPaginatedProvidersList?&searchText=' + $scope.searchText, angular.toJson(paginationDetails)).then(function (response) {
                var data = response.data;
                $scope.providersList = data.list;
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
        $scope.getPaginatedProvidersList();
        $scope.getServicesList = function () {
            $(".loader").css("display", "block");
            $http.post($scope.hospitalServerURL + '/getServices').then(function (response) {
                var data = response.data.object;
                $scope.servicesList = data;
                console.log( $scope.servicesList);
                // $scope.searchText = val;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getServicesList();

        $scope.selectedServices = [];
        $scope.pushSelectedServices = function (id,name) {
            var index = $scope.selectedServices.indexOf(name);
            if (parseInt(index) >= 0) {
                $scope.selectedServices.splice(index, 1);
            }else {
                $scope.selectedServices.push(name);
            }
            $scope.serviceslist=$scope.selectedServices;
        };


        $scope.feeconfigurationList=function () {
            $window.location.href = '/home#!/Users';
        };

        $scope.addProviders = function () {
            $scope.operation='create';
            $scope.countryName = "India";
            $scope.countryState($scope.countryName);
            $scope.stateName = "Karnataka";
            $scope.stateCity($scope.stateName);
            $scope.city = "Bengaluru";
            $scope.removeProviders();
            $("#submit").text("save");
            $('#users-providers-title').text("Add Providers");
            $("#add_users_providers").modal('show');

        };
        $scope.getCountryList = function () {
            $http.post($scope.hospitalServerURL + '/getCountryList').then(function (response) {
                var data = response.data;
                $scope.countryList = angular.copy(data);
                $scope.countryName = "India";
                $scope.countryState($scope.countryName);
                }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            });
        };

        $scope.SaveProviders = function (flagName) {
            if (angular.isUndefined($scope.firstName) || $scope.firstName == ''|| $scope.firstName ==null) {
                Notification.warning({message: 'First Name can not be Empty', positionX: 'center', delay: 2000});
                }
                else if (angular.isUndefined($scope.phoneNumber) || $scope.phoneNumber == ''|| $scope.phoneNumber==null) {
                Notification.warning({message: 'Please Enter Valid PhoneNumber', positionX: 'center', delay: 2000});
                }
                // else if (angular.isUndefined($scope.email) || $scope.email == ''|| $scope.email==null) {
                // Notification.warning({message: 'Enter Valid mailId', positionX: 'center', delay: 2000});
                // }
                // else if (angular.isUndefined($scope.username) || $scope.username == '') {
                // Notification.warning({message: 'username cannot be empty', positionX: 'center', delay: 2000});
                // }
                // else if (angular.isUndefined($scope.password) || $scope.password == '') {
                // Notification.warning({message: 'password cannot be empty', positionX: 'center', delay: 2000});
                // }
                // else if (angular.isUndefined($scope.retypePassword) || $scope.retypePassword != $scope.password) {
                // Notification.warning({message: 'Password and retypePassword should match', positionX: 'center', delay: 2000});
                // }
            else {
                var saveProviderDetails;
                saveProviderDetails = {
                    id: $scope.id,
                    firstName: $scope.firstName,
                    lastName: $scope.lastName,
                    email: $scope.email,
                    phoneNumber: $scope.phoneNumber,
                    mobileNumber: $scope.mobileNumber,
                    city: $scope.city,
                    state: $scope.stateName,
                    countryName: $scope.countryName,
                    zipCode: $scope.zipCode,
                    calenderType: $scope.Calender,
                    username: $scope.username,
                    password: $scope.password,
                    retypepassword: $scope.retypePassword,
                    address:$scope.address,
                    notes:$scope.notes,
                    serviceslist:$scope.serviceslist,
                    flagType:flagName

                };
                $http.post($scope.hospitalServerURL + "/saveProviderDetails", angular.toJson(saveProviderDetails)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#add_users_providers").modal('hide');
                        if($scope.operation=='Edit'){
                            Notification.success({
                                message: 'Providers is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }else {
                            Notification.success({
                                message: 'Providers is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        $scope.removeProviders();
                        $scope.getPaginatedProvidersList();
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


        $scope.DeleteProviders = function (data) {
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
                        $http.post($scope.hospitalServerURL + "/deleteProviders?id="+data.id).then(function (response, status, headers, config) {
                            var data = response.data;
                            $scope.getPaginatedProvidersList();
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
        $scope.getCountryList();

    });
