/**
 * Created by azgar.h on 7/1/2017.
 */


app.controller('employeeCtrl',
    function ($scope, $http, $location, $filter, Notification, ngTableParams, $timeout) {
        $scope.hospitalServerURL = "/hospital";
        $scope.operation = 'Create';
        $scope.inactiveStatus="Active";
        $scope.number =/^[0-9]/;
        //added for pagination purpose @rahul
        $scope.firstPage=true;
        $scope.lastPage=false;
        $scope.pageNo=0;
        $scope.prevPage=false;
        $scope.isPrev=false;
        $scope.isNext=false;
        $scope.inactiveStatus = "Active";

        (function(){
            var setDateRestriction = function(){
                $scope.options = {
                    maxDate : new Date()
                };
            };
            $timeout(setDateRestriction, 0);
        })();

        $scope.BackServices=function(){
            window.location.href='#!/services_masters';
        };

        /*$scope.getEmployeeList = function (val) {
            if (angular.isUndefined(val)) {
                val = "";
            }
            $http.get($scope.hiposServerURL + "/" + $scope.customer + '/getEmployeeList?employeeSearchText=' + val).then(function (response) {
                var data = response.data;
                $scope.employeeList = angular.copy(data);
                $scope.employeeSearchText = val;
                $scope.searchText = val;
            },function (error) {
                Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
            })
            //     // .error(function (data, status, header, config) {
            //     // Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
            // });
        };*/
        $scope.clicked = false;
        $scope.ButtonStatus = "InActive";
        $scope.inactiveEmployee = function(val) {

            if($scope.clicked == false){
                $scope.inactiveStatus = "InActive";
                $scope.ButtonStatus = "Active";
                var page="Page";
            }
            else{
                $scope.inactiveStatus = "Active";
                $scope.ButtonStatus = "InActive";
                var page="";
            }
            $scope.clicked = !$scope.clicked;
            $scope.getEmployeeList();
        };

        $scope.importPopup = function(){
            $("#import_Employee").modal('show');
        }

        $scope.saveEmployeeImport = function(){
            $scope.isDisabled= true;
            var formElement = document.getElementById("employeeDetails");
            var employeeDetails = new FormData(formElement);
            $http.post($scope.hospitalServerURL + '/employeeImportSave',employeeDetails,
                { headers: {'Content-Type': undefined},
                    transformRequest: angular.identity,
                }).then(function (response) {
                    $("#import_Employee").modal('hide');
                    $scope.getEmployeeList();
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
        $scope.getEmployeeList = function (type,page) {
            if (angular.isUndefined(type)) {
                type = "Active";
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
            if (angular.isUndefined($scope.employeeSearchText)) {
                $scope.employeeSearchText = "";
            }
            $http.post($scope.hospitalServerURL  + '/getPaginatedEmployeeList?type='+ $scope.inactiveStatus + '&searchText=' + $scope.employeeSearchText, angular.toJson(paginationDetails)).then(function (response) {
                var data = response.data;
                $scope.employeeList = data.list;
                $scope.first = data.firstPage;
                $scope.last = data.lastPage;
                $scope.prev = data.prevPage;
                $scope.next = data.nextPage;
                $scope.pageNo = data.pageNo;
            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getEmployeeList();


        $scope.saveNewEmployee = function () {
            if (angular.isUndefined($scope.EmployeeNameText) || $scope.EmployeeNameText == '') {
                Notification.warning({message: 'Please Enter Employee Name', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.dt) || $scope.dt == '' ) {
                Notification.warning({message: 'Please Enter DOB', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.dt1) || $scope.dt1 == '' ) {
                Notification.warning({message: 'Please Enter DOJ', positionX: 'center', delay: 2000});
            }
            else if($scope.dt > $scope.dt1){
                Notification.warning({message: 'DOJ can not be less than DOB', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.EmployeeCodeText) || $scope.EmployeeCodeText == '' ) {
                Notification.warning({message: 'Please Enter Employee Code', positionX: 'center', delay: 2000});
            }
            else {
                $scope.isDisabled= true;
                $timeout(function(){
                    $scope.isDisabled= false;
                }, 3000)
                var saveEmployeeDetails;
                saveEmployeeDetails = {
                    employeeId: $scope.employeeId,
                    employeeCode: $scope.EmployeeCodeText,
                    employeeName: $scope.EmployeeNameText,
                    employeeAddr: $scope.EmployeeAddressText,
                    employeePhone: $scope.EmployeePhoneText,
                    gender: $scope.GenderText,
                    waiterFlag: $scope.waiterFlag,
                    deliveryFlag: $scope.deliveryFlag,
                    image: $scope.image,
                    employeeDOJ: $scope.dt1,
                    employeeDOB: $scope.dt,
                    status:$scope.empStatusText
                };
                $http.post($scope.hospitalServerURL  +  '/saveEmployee', angular.toJson(saveEmployeeDetails, "Create")).then(function (response, status, headers, config) {
                    var data = response.data;
                    if(data==""){
                        Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $scope.getEmployeeList();
                       $("#add_new_Employee_modal").modal('hide');
                        Notification.success({message: 'Employee Created  successfully', positionX: 'center', delay: 2000});
                       $scope.removeEmployeeDetails();
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
        $scope.getEmployeeList();
        $scope.importEmployee = function(){
            $("#import_Employee").modal('show');
        }

        $scope.addNewEmployee = function () {
            $scope.empStatusText="Active";
            $("#submit").text("Save");
            $('#employee-title').text("Add Employee");
            $("#add_new_Employee_modal").modal('show');
        };

        $scope.today = function() {
            $scope.dt = new Date();
            $scope.dt1 = new Date();
        };
         $scope.today();
         $scope.format = 'dd/MM/yyyy';

        $scope.open1 = function() {
            $scope.popup1.opened = true;
        };

        $scope.popup1 = {
            opened: false
        };

        $scope.open2 = function() {
            $scope.popup2.opened = true;
        };

        $scope.popup2 = {
            opened: false
        };


        $scope.removeEmployeeDetails = function (){
            $scope.employeeId ="";
            $scope.EmployeeCodeText="";
            $scope.EmployeeNameText="";
            $scope.EmployeeAddressText="";
            $scope.EmployeePhoneText="";
            $scope.image="";
            $scope.GenderText="";
            $scope.deliveryFlag="";
            $scope.waiterFlag="";
            $scope.empStatusText='Active';
            $scope.operation='Create';
        }


        $scope.editEmployeePopulate = function (data) {
            $scope.empObj = data;
            $scope.employeeId = data.employeeId;
            $scope.EmployeeCodeText=data.employeeCode;
            $scope.EmployeeNameText=data.employeeName;
            $scope.EmployeeAddressText=data.employeeAddr;
            $scope.EmployeePhoneText=data.employeePhone;
            $scope.GenderText=data.gender;
            $scope.image=data.image;
            $scope.deliveryFlag=data.deliveryFlag;
            $scope.waiterFlag=data.waiterFlag;
            $scope.dt1=data.employeeDOJ;
            $scope.dt=data.employeeDOB;
            $scope.empStatusText=data.status;
            $scope.operation='Edit';
            $("#submit").text("Update");
            $('#employee-title').text("Edit Employee");
            $scope.getEmployeeList();
            $("#add_new_Employee_modal").modal('show');
        },function (error) {
            Notification.error({message: 'Something went wrong, please try again',positionX: 'center',delay: 2000});

        };

        $scope.deleteEmployee = function (data) {
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
                    if(result == true){
                        $scope.employeeId=data.employeeId;
                        $scope.EmployeeCodeText=data.employeeCode;
                        $scope.EmployeeNameText=data.employeeName;
                        $scope.EmployeeAddressText=data.employeeAddr;
                        $scope.EmployeePhoneText=data.employeePhone;
                        $scope.GenderText=data.gender;
                        $scope.dt1=data.employeeDOJ;
                        $scope.dt=data.employeeDOB;
                        $scope.empStatusText=data.status1;
                        var deleteDetails = {
                            employeeId: $scope.employeeId,
                            employeeCode: $scope.employeeCode,
                            employeeName: $scope.employeeName,
                            employeeAddr: $scope.employeeAddr,
                            employeePhone: $scope.employeePhone,
                            gender: $scope.gender,
                            employeeDOJ: $scope.employeeDOJ,
                            employeeDOB: $scope.employeeDOB

                        };
                        $http.post($scope.hospitalServerURL +  '/deleteEmployee', angular.toJson(deleteDetails, "Create")).then(function (response, status, headers, config) {
                            var data = response.data;
                            $scope.status=data.status;
                               if($scope.status=="InActive") {
                                $scope.getEmployeeList();
                                Notification.success({
                                    message: 'It is Already in use So Status Changes to Inactive',
                                    positionX: 'center',
                                    delay: 2000
                                });
                            }else {
                                   if($scope.empStatusText=="InActive") {
                                       $scope.getEmployeeList("","InActive");
                                       Notification.success({
                                           message: 'Status has been changed to Active',
                                           positionX: 'center',
                                           delay: 2000
                                       });
                                   }
                                   else{
                                       $scope.getEmployeeList("","");
                                       Notification.success({
                                           message: 'Status has been changed to InActive',
                                           positionX: 'center',
                                           delay: 2000
                                       });
                                   }
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
    });
