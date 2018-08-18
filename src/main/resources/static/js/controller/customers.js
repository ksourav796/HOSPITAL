app.controller('customersController',
    function($scope, $http, $location, $filter, Notification, ngTableParams,  $timeout, $window, $rootScope) {
        console.log("aaaaaaaaaaaaa");
        $scope.hospitalServerURL = "/hospital";
        $scope.word = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
        $scope.customerId = 1;
        $scope.userRights = [];
        $scope.operation = 'Create';
        $scope.customer = 1;
        $scope.today = new Date();
        $scope.removeCustomers = function () {
            $scope.id = "";
            $scope.firstname = "";
            $scope.lastname = "";
            $scope.email = "";
            $scope.phoneNumber = "";
            $scope.address = "";
            $scope.ZipCode = "";
            $scope.notes = "";
        };

        $scope.open1 = function () {
            $scope.popup1.opened = true;
        };
        $scope.popup1 = {
            opened: false
        };

        $scope.EditCustomers = function (data) {
            $scope.id = data.id;
            $scope.firstname = data.firstName;
            $scope.lastname = data.lastName;
            $scope.email = data.email;
            $scope.phoneNumber = parseInt(data.phoneNumber);
            $scope.Address = data.address;
            $scope.ZipCode = parseInt(data.zipCode);
            $scope.Notes = data.notes;
            $scope.uhid =data.uhid;
            $scope.date=new Date(data.date);
            $scope.time = data.starttime;
            $scope.occupation = data.occupation;
            $scope.gender = data.gender;
            $scope.maritalStatus = data.maritalStatus;
            $scope.age = data.age;
            $scope.countryName=data.countryName;
            $scope.countryState(data.countryName);
            $scope.stateName=data.stateName;
            $scope.stateCity(data.stateName);
            $scope.city=data.city;
            $scope.cheif=data.cheifComplaints;
            $scope.historyofpresenting=data.history;

            $scope.operation = 'Edit';
            $("#submit").text("Update");
            $('#customers-title').text("Edit Patients");
            $("#add_customers").modal('show');

        }, function (error) {
            Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
        };



        $scope.getCustomersList = function (searchText) {
            if (angular.isUndefined(searchText)) {
                $scope.searchText = "";
            }
            $(".loader").css("display", "block");
            $http.post($scope.hospitalServerURL + '/getCustomers').then(function (response) {
                var data = response.data.object;
                $scope.customersList = data;
                // $scope.searchText = val;
            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getCustomersList();


        $scope.addCustomers = function () {
            $scope.operation='create';
            $scope.countryName = null;
            $scope.countryState($scope.countryName);
            $scope.stateName=null;
            $scope.stateCity($scope.stateName);
            $scope.city=null;
            $('#customers-title').text("Add Customers");
            $("#add_customers").modal('show');

        };
        $scope.saveCustomers = function () {
            if (angular.isUndefined($scope.firstname) || $scope.firstname == '') {
                Notification.warning({message: 'Customer Name can not be Empty', positionX: 'center', delay: 2000});
                }
                // else if (angular.isUndefined($scope.email) || $scope.email == '') {
                // Notification.warning({message: 'Enter valid EmailId', positionX: 'center', delay: 2000});
                // }
                else {
                var saveCustomerDetails;
                saveCustomerDetails = {
                    id: $scope.id,
                    firstName: $scope.firstname,
                    lastName: $scope.lastname,
                    email: $scope.email,
                    uhid:$scope.uhid,
                    phoneNumber: $scope.phoneNumber,
                    address: $scope.Address,
                    zipCode: $scope.ZipCode,
                    date:$scope.date,
                    notes: $scope.Notes,
                    starttime: $scope.time,
                    occupation: $scope.occupation,
                    gender: $scope.gender,
                    maritalStatus: $scope.maritalStatus,
                    age: $scope.age,
                    countryName:$scope.countryName,
                    stateName:$scope.stateName,
                    city:$scope.city,
                    cheifComplaints:$scope.cheif,
                    history:$scope.historyofpresenting
                };
                $http.post($scope.hospitalServerURL + "/saveNewCustomer", angular.toJson(saveCustomerDetails)).then(function (response) {
                    var data = response.data;
                    console.log("data");
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#add_customers").modal('hide');
                        if($scope.operation=='Edit'){
                            Notification.success({
                                message: 'Customer is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }else {
                            Notification.success({
                                message: 'Customer is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        $scope.removeCustomers();

                        // $scope.removeCategories();
                        $scope.getCustomersList();
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
        $scope.DeleteCustomers = function (data) {
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
                            firstName: data.firstname,
                            lastName: data.lastname,
                            email: data.email,
                            phoneNumber: data.phoneNumber,
                            address: data.address,
                            zipCode: data.zipCode,
                            notes: data.notes,

                        };
                        $http.post($scope.hospitalServerURL + "/deleteCustomers", angular.toJson(deleteDetails, "Create")).then(function (response, status, headers, config) {
                            var data = response.data;
                            $scope.getCustomersList();
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
        $scope.getCountryList();
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


    });
