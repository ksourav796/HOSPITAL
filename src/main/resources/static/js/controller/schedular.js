/**
 * Created by azgar.h on 7/1/2017.
 */


app.controller('schedulerCtrl',
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
        $scope.today = new Date();

        (function(){
            var setDateRestriction = function(){
                $scope.options = {
                    maxDate : new Date()
                };
            };
            $timeout(setDateRestriction, 0);
        })();

        $scope.BackServices=function(){
            window.location.href='#!/users';
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
        $scope.inactiveScheduler = function(val) {

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
            $scope.getSchedulerList();
        };

        $scope.importPopup = function(){
            $("#import_Scheduler").modal('show');
        }

        $scope.saveSchedulerImport = function(){
            $scope.isDisabled= true;
            var formElement = document.getElementById("schedulerDetails");
            var schedulerDetails = new FormData(formElement);
            $http.post($scope.hospitalServerURL + '/schedulerImportSave',schedulerDetails,
                { headers: {'Content-Type': undefined},
                    transformRequest: angular.identity,
                }).then(function (response) {
                $scope.getSchedulerList();
                $("#import_Scheduler").modal('hide');
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



        $scope.getSchedulerList = function (type,page) {
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
            if (angular.isUndefined($scope.searchText)) {
                $scope.searchText = "";
            }
            $http.post($scope.hospitalServerURL  + '/getPaginatedSchedulerList?type='+ $scope.inactiveStatus + '&searchText=' + $scope.searchText, angular.toJson(paginationDetails)).then(function (response) {
                var data = response.data;
                console.log(data);
                $scope.schedulerList = data.list;
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
        $scope.getSchedulerList();

        $scope.saveScheduler = function () {
            if (angular.isUndefined($scope.firstName) || $scope.firstName == '') {
                Notification.warning({message: 'Please Enter First Name', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.mobileNumber) || $scope.mobileNumber == '' ) {
                Notification.warning({message: 'Please Enter Phone Number', positionX: 'center', delay: 2000});
            }
            else {
                $scope.isDisabled= true;
                $timeout(function(){
                    $scope.isDisabled= false;
                }, 3000)
                var saveSchedulerDetails;
                saveSchedulerDetails = {
                    id: $scope.id,
                    firstName: $scope.firstName,
                    lastName: $scope.lastName,
                    mobileNumber: $scope.mobileNumber,
                    email: $scope.email,
                    alternativeNumber: $scope.alternativeNumber,
                    address: $scope.address,
                    gender: $scope.gender,
                    occupation: $scope.occupation,
                    maritalStatus: $scope.maritalStatus,
                    age: $scope.age,
                    education: $scope.education,
                    city:$scope.city,
                    state:$scope.stateName,
                    country:$scope.countryName,
                    status:$scope.schStatusText
                };
                $http.post($scope.hospitalServerURL  +  '/saveSchedulerDetails', angular.toJson(saveSchedulerDetails, "Create")).then(function (response, status, headers, config) {
                    var data = response.data;
                    if(data==""){
                        Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $scope.getSchedulerList();
                        $("#add_new_Scheduler_modal").modal('hide');
                        Notification.success({message: 'Scheduler Created  successfully', positionX: 'center', delay: 2000});
                        $scope.removeSchedulerDetails();
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
        $scope.getSchedulerList();


        $scope.addNewScheduler = function () {
            $scope.operation = 'create';
            $scope.schStatusText="Active";
            $scope.countryName = "India";
            $scope.countryState($scope.countryName);
            $scope.stateName="Karnataka";
            $scope.stateCity($scope.stateName);
            $scope.city="Bengaluru";
            $('#scheduler-title').text("Add Scheduler");
            $("#add_new_Scheduler_modal").modal('show');
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


        $scope.removeSchedulerDetails = function (){
            $scope.id ="";
            $scope.firstName="";
            $scope.lastName="";
            $scope.mobileNumber="";
            $scope.email="";
            $scope.alternativeNumber="";
            $scope.address="";
            $scope.gender="";
            $scope.occupation="";
            $scope.maritalStatus="";
            $scope.education="";
            $scope.age="";
            $scope.country="";
            $scope.state="";
            $scope.city="";
            $scope.zipCode="";
            $scope.notes="";
            $scope.schStatusText='Active';
            $scope.operation='Create';
        };


        $scope.editSchedulerPopulate = function (data) {
            $scope.schObj = data;
            $scope.id = data.id;
            $scope.firstName=data.firstName;
            $scope.lastName=data.lastName;
            $scope.mobileNumber=data.mobileNumber;
            $scope.email=data.email;
            $scope.gender=data.gender;
            $scope.appntTime=data.startTime;
            $scope.occupation=data.occupation;
            $scope.alternativeNumber=data.alternativeNumber;
            $scope.address=data.address;
            $scope.date=new Date(data.book_datetime);
            $scope.maritalStatus=data.maritalStatus;
            $scope.education=data.education;
            $scope.zipCode=data.zipCode;
            $scope.countryName=data.country;
            $scope.countryState(data.country);
            $scope.stateName=data.state;
            $scope.stateCity(data.state);
            $scope.city=data.city;
            $scope.age=data.age;
            $scope.notes=data.notes;
            $scope.operation='Edit';
            $('#scheduler-title').text("Edit Scheduler");
            $scope.getSchedulerList();
            $("#add_new_Scheduler_modal").modal('show');
        },function (error) {
            Notification.error({message: 'Something went wrong, please try again',positionX: 'center',delay: 2000});

        };

        $scope.setDateWithTime=function(){
            if(!angular.isUndefined($scope.appntTime)){
                $scope.date=new Date($scope.date);
                var appntTime = $filter('date')(new Date($scope.appntTime), 'HH:mm:ss');
                var date=appntTime.split(":");
                $scope.date.setHours(date[0]);
                $scope.date.setMinutes(date[1]);
                $scope.appntTime=appntTime;
            }
        }


        $scope.DeleteScheduler = function (data) {
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
                            firstName: data.firstName,
                            lastName: data.lastName,
                            mobileNumber: data.mobileNumber,
                            email: data.email,
                            alternativeNumber: data.alternativeNumber,
                            address: data.address,
                            maritalStatus: data.maritalStatus,
                            education: data.education,
                            zipCode: data.zipCode,
                            country: data.country,
                            state: data.state,
                            city: data.city,
                            age: data.age,
                            description: data.description
                        };
                        $http.post($scope.hospitalServerURL + "/deleteScheduler", angular.toJson(deleteDetails, "Create")).then(function (response, status, headers, config) {
                            var data = response.data;
                            $scope.getSchedulerList();
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
    });
