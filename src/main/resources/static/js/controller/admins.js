app.controller('adminsController',
    function($scope, $http, $location, $filter, Notification, ngTableParams,  $timeout, $window, $rootScope) {
        // console.log("aaaaaaaaaaaaa");
        $scope.hospitalServerURL = "/hospital";
        $scope.word = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
        $scope.customerId = 1;
        $scope.userRights = [];
        $scope.operation = 'Create';
        $scope.customer = 1;
        $scope.today = new Date();
        $scope.removeAdmins = function () {
            // $scope.gradeId = "";
            // $scope.gradename = "";
            // $scope.gradedesc = "";
            // $scope.StatusText = "";
            // document.getElementById("checkbox").checked = false;
        };

        $scope.EditAdmins = function (data) {
            // $scope.gradename = data.gradeName;
            // $scope.gradedesc = data.gradeDescription;
            // $scope.statusText = data.gradeStatus;
            // $scope.gradeId = data.gradeId;
            $scope.operation = 'Edit';
            $('#users-admins-title').text("Edit Admin");
            $("#add_users_admins").modal('show');

        }, function (error) {
            Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
        };

        $scope.getAdminsList = function () {
            $(".loader").css("display", "block");
            $http.post($scope.hospitalServerURL + '/getAdminsList').then(function (response) {
                var data = response.data.object;
                $scope.gradeList = data;
                $scope.searchText = val;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getAdminsList();

        // $scope.getGradeListBasedOnInActive = function () {
        //     $(".loader").css("display", "block");
        //     $http.post($scope.hospitalServerURL + '/getGradeListBasedOnInactive').then(function (response) {
        //         var data = response.data.object;
        //         $scope.gradeList = data;
        //         if(data==""){
        //             Notification.warning({
        //                 message: 'No Records are Available with InActive Status.',
        //                 positionX: 'center',
        //                 delay: 2000
        //             });
        //             $scope.getGradeList();
        //         }
        //
        //     }, function (error) {
        //         Notification.error({
        //             message: 'Something went wrong, please try again',
        //             positionX: 'center',
        //             delay: 2000
        //         });
        //     })
        //
        // };


        $scope.feeconfigurationList=function () {
            $window.location.href = '/home#!/Users';
        };

        $scope.addAdmins = function () {
            // $scope.statusText = "Active";
            $scope.operation='create';
            $scope.removeAdmins();
            $('#users-admins-title').text("Add Admin");
            $("#add_users_admins").modal('show');

        };
        $scope.saveAdmins = function () {
            if (angular.isUndefined($scope.gradename) || $scope.gradename == '') {
                Notification.warning({message: 'Grade Name can not be Empty', positionX: 'center', delay: 2000});

            } else {
                var saveAdminDetails;
                saveAdminDetails = {
                    id: $scope.id,
                    firstName: $scope.firstName,
                    gradeDescription: $scope.lastName,
                    gradeStatus: $scope.email,
                    firstName: $scope.phoneNumber,
                    gradeDescription: $scope.mobileNumber,
                    gradeStatus: $scope.city,
                    firstName: $scope.state,
                    gradeDescription: $scope.pinCode,
                    gradeStatus: $scope.userName,
                    firstName: $scope.password,
                    gradeDescription: $scope.retypePassword,
                    gradeStatus: $scope.calender,
                    firstName: $scope.address,
                    gradeDescription: $scope.notes

                };
                $http.post($scope.hospitalServerURL + "/saveNewAdmin", angular.toJson(saveAdminDetails)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#add_grade_master").modal('hide');
                        if($scope.operation=='Edit'){
                            Notification.success({
                                message: 'Admin Details is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }else {
                            Notification.success({
                                message: 'Admin Details is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        $scope.removeAdmins();
                        $scope.getAdminsList();
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
        // $scope.DeleteGrade = function (data) {
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
        //                     gradeName: data.gradeName,
        //                     gradeDescription: data.gradeDescription,
        //                     gradeStatus: data.gradeStatus,
        //                     gradeId: data.gradeId
        //                 };
        //                 $http.post($scope.hospitalServerURL + "/deleteGradeMaster", angular.toJson(deleteDetails, "Create")).then(function (response, status, headers, config) {
        //                     var data = response.data;
        //                     $scope.getGradeList();
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
