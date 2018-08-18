app.controller('currentUserController',
    function($scope, $http, $location, $filter, Notification, ngTableParams,  $timeout, $window, $rootScope) {

        $scope.hospitalServerURL = "/hospital";
        $scope.word = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
        $scope.operation = 'Create';
        $scope.stateName="Karnataka";
        $scope.city="Bengaluru";
        $scope.country = "India";

        $scope.saveCurrentUserDetails = function () {
            if (angular.isUndefined($scope.firstname) || $scope.firstname == '') {
                Notification.warning({message: 'firstname cannot be Empty', positionX: 'center', delay: 2000});
            }

            else if(angular.isUndefined($scope.lastname) || $scope.lastname == ''){
                Notification.warning({message: 'lastname can not be Empty', positionX: 'center', delay: 2000});
            }
            else if(angular.isUndefined($scope.username) || $scope.username == ''){
                Notification.warning({message: 'user name can not be Empty', positionX: 'center', delay: 2000});
            }
            else if(angular.isUndefined($scope.email) || $scope.email == ''){
                Notification.warning({message: 'enter valid emailId', positionX: 'center', delay: 2000});
            }
            // else if(angular.isUndefined() || $scope.retypepassword == ''){
            //     Notification.warning({message: 'retypepassword can not be Empty', positionX: 'center', delay: 2000});
            // }
            else if(angular.isUndefined($scope.retypepassword) || $scope.retypepassword != $scope.password){
                Notification.warning({message: 'password and retype password should be same', positionX: 'center', delay: 2000});
            }
            else {
                var saveCurrentUserDetails;
                saveCurrentUserDetails = {
                    id: $scope.id,
                    firstName: $scope.firstname,
                    lastName: $scope.lastname,
                    email: $scope.email,
                    phoneNumber: $scope.phoneNumber,
                    mobileNumber: $scope.mobileNumber,
                    city: $scope.city,
                    state: $scope.stateName,
                    country: $scope.country,
                    address: $scope.address,
                    zipCode: $scope.zipCode,
                    notes: $scope.notes,
                    username: $scope.username,
                    password: $scope.password,
                    retypepassword: $scope.retypepassword,
                    calenderType: $scope.calenderType,
                };
                $http.post($scope.hospitalServerURL + "/saveCurrentUserDetails", angular.toJson(saveCurrentUserDetails)).then(function (response) {
                    var data = response.data;
                    console.log(data);
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        Notification.success({
                            message: 'Current User Details is Created  successfully',
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

        $scope.countryState = function(country){
            var url = "/hospital/getCountryState?countryName=" + country;
            $http.post(url).then(function (response) {
                var data = response.data;
                $scope.stateList = angular.copy(data);
                $scope.stateCity($scope.stateName);
            })

        }
        $scope.stateCity = function(state){
            var url = '/hospital/getStateCity?stateName=' + state;
            $http.post(url).then(function (response) {
                var data = response.data;
                $scope.cityList = angular.copy(data);
            })
        }
        $scope.getCountryList = function () {
            $http.post($scope.hospitalServerURL + '/getCountryList').then(function (response) {
                var data = response.data;
                $scope.countryList = angular.copy(data);
                $scope.countryState($scope.country);
                }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            });
        }
        //
        $scope.getCurrentUserList = function () {
            $http.post($scope.hospitalServerURL + '/getCurrentUser').then(function (response) {
                var data = response.data.object;
                // $scope.currentUserList = data;
                console.log(data);
                // for(var i=0; i<data.length;i++) {
                    $scope.id = data.id;
                    $scope.firstname = data.firstName;
                    $scope.lastname = data.lastName;
                    $scope.email = data.email;
                    $scope.phoneNumber = data.phoneNumber;
                    $scope.mobileNumber = data.mobileNumber;
                    // $scope.city = data.city;
                    // $scope.stateName = data.state;
                    $scope.address = data.address;
                    $scope.zipCode = data.zipCode;
                    $scope.notes = data.notes;
                    $scope.username = data.username;
                    $scope.password = data.password;
                    $scope.retypepassword = data.retypepassword;
                    $scope.calenderType = data.calenderType;
                // }

                // $scope.searchText = val;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getCurrentUserList();
        $scope.getCountryList();

    });
