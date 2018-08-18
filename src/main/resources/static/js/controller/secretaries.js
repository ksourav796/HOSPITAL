app.controller('secretariesController',
    function($scope, $http, $location, $filter, Notification, ngTableParams,  $timeout, $window, $rootScope) {
        // console.log("aaaaaaaaaaaaa");
        $scope.hospitalServerURL = "/hospital";
        $scope.word = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
        $scope.customerId = 1;
        $scope.userRights = [];
        $scope.operation = 'Create';
        $scope.customer = 1;
        $scope.today = new Date();
        $scope.removeSecretaries = function () {
            // $scope.gradeId = "";
            // $scope.gradename = "";
            // $scope.gradedesc = "";
            // $scope.StatusText = "";
            // document.getElementById("checkbox").checked = false;
        };

        $scope.EditSecretaries = function (data) {
            // $scope.gradename = data.gradeName;
            // $scope.gradedesc = data.gradeDescription;
            // $scope.statusText = data.gradeStatus;
            // $scope.gradeId = data.gradeId;
            $scope.operation = 'Edit';
            $('#users-secretaries-title').text("Edit Secretaries");
            $("#add_users_secretaries").modal('show');

        }, function (error) {
            Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
        };

        // $scope.getProvidersList = function (val,checkboxForInActive) {
        //     if (angular.isUndefined(val)) {
        //         val = "";
        //     }
        //     if (angular.isUndefined(checkboxForInActive)) {
        //         checkboxForInActive = "false";
        //     }
        //     $(".loader").css("display", "block");
        //     $http.post($scope.hospitalServerURL + '/getGradeList2?searchText=' + val+'&checkboxForInActive='+checkboxForInActive).then(function (response) {
        //         var data = response.data.object;
        //         $scope.gradeList = data;
        //         $scope.searchText = val;
        //
        //     }, function (error) {
        //         Notification.error({
        //             message: 'Something went wrong, please try again',
        //             positionX: 'center',
        //             delay: 2000
        //         });
        //     })
        // };
        // $scope.getProvidersList();

        $scope.getProvidersList = function () {

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
        $scope.getProvidersList();

        $scope.selectedsecretarians = [];
        $scope.pushSelectedSectarians = function (id) {
            var index = $scope.selectedsecretarians.indexOf(id);
            if (parseInt(index) >= 0) {
                $scope.selectedsecretarians.splice(index, 1);
            }else {
                $scope.selectedsecretarians.push(id);
            }
            $scope.secretarianslist=$scope.selectedsecretarians;
        };



        $scope.getGeneralSettingsDetailsList = function () {
            $http.post($scope.hospitalServerURL + '/getGeneralSettingsDetailsList').then(function (response) {
                var data = response.data;
                $scope.generalSettingsList = data;

                console.log("general settings");
                console.log($scope.generalSettingsList);
                $scope.CompanyName=data.companyName;
                $scope.sectretariansId =data.id;
                // $scope.CompanyEmail=data.companyEmail;
                // $scope.CompanyLink=data.companyLink;
                // $scope.GoogleAnalyticsID=data.googleAnalyticsID;
                // $scope.DateFormat=data.dateFormat;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })

        };
        $scope.getGeneralSettingsDetailsList();



        $scope.feeconfigurationList=function () {
            $window.location.href = '/home#!/Users';
        };

        $scope.addSecretaries = function () {
            // $scope.statusText = "Active";
            $scope.operation='create';
            $scope.removeSecretaries();
            $('#users-secretaries-title').text("Add Secretaries");
            $("#add_users_secretaries").modal('show');

        };
        // $scope.saveSecretaries = function () {
        //     if (angular.isUndefined($scope.gradename) || $scope.gradename == '') {
        //         Notification.warning({message: 'Grade Name can not be Empty', positionX: 'center', delay: 2000});
        //
        //     } else {
        //         var saveGradeDetails;
        //         saveGradeDetails = {
        //             gradeId: $scope.gradeId,
        //             gradeName: $scope.gradename,
        //             gradeDescription: $scope.gradedesc,
        //             gradeStatus: $scope.statusText
        //         };
        //         $http.post($scope.hospitalServerURL + "/saveNewGradeMaster", angular.toJson(saveGradeDetails)).then(function (response) {
        //             var data = response.data;
        //             if (data == "") {
        //                 Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
        //             }
        //             else {
        //                 $("#add_grade_master").modal('hide');
        //                 if($scope.operation=='Edit'){
        //                     Notification.success({
        //                         message: 'GradeMaster is Updated successfully',
        //                         positionX: 'center',
        //                         delay: 2000
        //                     });
        //                 }else {
        //                     Notification.success({
        //                         message: 'GradeMaster is Created  successfully',
        //                         positionX: 'center',
        //                         delay: 2000
        //                     });
        //                 }
        //                 $scope.removeGradeMaster();
        //                 $scope.getGradeList();
        //             }
        //         }, function (error) {
        //             Notification.error({
        //                 message: 'Something went wrong, please try again',
        //                 positionX: 'center',
        //                 delay: 2000
        //             });
        //         });
        //     }
        // };
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
