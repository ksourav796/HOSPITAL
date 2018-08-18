app.controller('breaksController',
    function($scope, $http, $location, $filter, Notification, ngTableParams,  $timeout, $window, $rootScope) {
        $scope.hospitalServerURL = "/hospital";
        $scope.operation = 'Create';
        $scope.today = new Date();
        $scope.removeBreaks = function () {
            $scope.id = "";
            $scope.name = "";
            $scope.description = "";
        };

        $scope.BackServices=function(){
            window.location.href='#!/businessLogic';
        }

        $scope.addBreaks = function () {
            $scope.id=0;
            $scope.day = "";
            $scope.startTime = "";
            $scope.endTime = "";

            $scope.operation = 'create';
            $("#submit").text("Save");
            $('#breaks-title').text("Add Breaks");
            $("#add_breaks").modal('show');
        };
        $scope.removeBreaks();

        $scope.editBreaks = function (data) {
            $scope.id = data.id;
            $scope.name = data.name;
            $scope.description = data.description;
            $scope.day = data.day;
            $scope.startTime = data.startTime;
            $scope.endTime = data.endTime;
            $scope.operation = 'Edit';
            $("#submit").text("Update");
            $('#breaks-title').text("Edit Breaks");
            $("#add_breaks").modal('show');

        }, function (error) {
            Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
        };

        $scope.removeBreaks();

        $scope.getBreaksList = function (searchText) {
            if (angular.isUndefined(searchText)) {
                $scope.searchText = "";
            }
            $(".loader").css("display", "block");
            $http.post($scope.hospitalServerURL + '/getBreaksList').then(function (response) {
                var data = response.data.object;
                $scope.breaksList = data;
                // $scope.searchText = val;
            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getPaginatedBreaksList = function (page) {
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
            $http.post($scope.hospitalServerURL + '/getPaginatedBreaksList?&searchText=' + $scope.searchText, angular.toJson(paginationDetails)).then(function (response) {
                var data = response.data;
                $scope.breaksList = data.list;
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

        $scope.getPaginatedBreaksList();
        $scope.deleteBreaks = function (data) {
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
                            name: data.name,
                            description: data.description
                        };
                        $http.post($scope.hospitalServerURL + "/deleteBreaks", angular.toJson(deleteDetails, "Create")).then(function (response, status, headers, config) {
                            var data = response.data;
                            $scope.getPaginatedBreaksList();
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

        $scope.saveBreaks = function () {
            if (angular.isUndefined($scope.day) || $scope.day == '') {
                Notification.warning({message: 'Day can not be Empty', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.startTime) || $scope.startTime == '') {
                Notification.warning({message: 'Enter start time', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.endTime) || $scope.endTime == '') {
                Notification.warning({message: 'Enter End time', positionX: 'center', delay: 2000});
            }
            else {
                var saveBreaks;
                saveBreaks = {
                    id: $scope.id,
                    day: $scope.day,
                    startTime: $scope.startTime,
                    endTime:$scope.endTime
                };
                $http.post($scope.hospitalServerURL + "/saveNewBreaks", angular.toJson(saveBreaks)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#add_breaks").modal('hide');
                        if ($scope.operation == 'Edit') {
                            Notification.success({
                                message: 'Breaks  Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        } else {
                            Notification.success({
                                message: 'Breaks is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        // $scope.removeWorkingPlan();
                        $scope.getPaginatedBreaksList();
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