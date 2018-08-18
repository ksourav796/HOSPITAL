app.controller('generalController',
    function($scope, $http, $location, $filter, Notification, ngTableParams,  $timeout, $window, $rootScope) {

        $scope.hospitalServerURL = "/hospital";
        $scope.word = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
        $scope.operation = 'Create';



        $scope.saveGeneralSettingDetails = function () {
            if (angular.isUndefined($scope.CompanyName) || $scope.CompanyName == '') {
                Notification.warning({message: 'Company Name can not be Empty', positionX: 'center', delay: 2000});

            }
            else if(angular.isUndefined($scope.CompanyEmail) || $scope.CompanyEmail == ''){
                Notification.warning({message: 'Enter valid EmailId', positionX: 'center', delay: 2000});
            }
            else if(angular.isUndefined($scope.CompanyLink) || $scope.CompanyLink == ''){
                Notification.warning({message: 'Company link can not be Empty', positionX: 'center', delay: 2000});
            }
            else if(angular.isUndefined($scope.GoogleAnalyticsID) || $scope.GoogleAnalyticsID == ''){
                Notification.warning({message: 'GoogleAnalyticsID can not be Empty', positionX: 'center', delay: 2000});
            }
            else if(angular.isUndefined($scope.DateFormat) || $scope.DateFormat == ''){
                Notification.warning({message: ' DateFormat can not be Empty', positionX: 'center', delay: 2000});
            }

            else {
                var saveGeneralDetails;
                saveGeneralDetails = {
                    id: $scope.id,
                    companyName: $scope.CompanyName,
                    companyEmail: $scope.CompanyEmail,
                    companyLink: $scope.CompanyLink,
                    googleAnalyticsID: $scope.GoogleAnalyticsID,
                    dateFormat: $scope.DateFormat,
                };


                $http.post($scope.hospitalServerURL + "/saveGeneral", angular.toJson(saveGeneralDetails)).then(function (response) {
                    var data = response.data;


                    console.log(data);
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        Notification.success({
                            message: 'General Settings Details is Created  successfully',
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


        $scope.getGeneralSettingsDetailsList = function () {
            $http.post($scope.hospitalServerURL + '/getGeneralSettingsDetailsList').then(function (response) {
                var data = response.data.object;
                console.log(data);
                // $scope.getGeneralSettingsDetailsList = data;
                $scope.CompanyName=data.companyName;
                $scope.CompanyEmail=data.companyEmail;
                $scope.CompanyLink=data.companyLink;
                $scope.GoogleAnalyticsID=data.googleAnalyticsID;
                $scope.DateFormat=data.dateFormat;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })

        };
        $scope.getGeneralSettingsDetailsList();

    });
