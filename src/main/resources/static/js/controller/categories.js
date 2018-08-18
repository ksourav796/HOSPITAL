app.controller('categoriesController',
    function($scope, $http, $location, $filter, Notification, ngTableParams,  $timeout, $window, $rootScope) {
        $scope.hospitalServerURL = "/hospital";
        $scope.word = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
        $scope.customerId = 1;
        $scope.userRights = [];
        $scope.operation = 'Create';
        $scope.customer = 1;
        $scope.firstPage = true;
        $scope.lastPage = false;
        $scope.pageNo = 0;
        $scope.prevPage = false;
        $scope.isPrev = false;
        $scope.isNext = false;
        $scope.today = new Date();
        // $route.reload();
        $scope.removeCategories = function () {
            $scope.id = "";
            $scope.name = "";
            $scope.description = "";
        };
        $scope.BackServices=function(){
            window.location.href='#!/services_masters';
        };
        $scope.EditCategories = function (data) {
            $scope.id = data.id;
            $scope.name = data.name;
            $scope.description = data.description;
            $scope.operation = 'Edit';
            $("#submit").text("Update");
            $('#student-title').text("Edit Categories");
            $("#add_categories").modal('show');

        }, function (error) {
            Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
        };
        $scope.removeCategories();


        $scope.getCategoriesList = function (searchText) {
            if (angular.isUndefined(searchText)) {
                $scope.searchText = "";
            }
            $(".loader").css("display", "block");
            $http.post($scope.hospitalServerURL + '/getCategories').then(function (response) {
                var data = response.data.object;
                $scope.categoriesList = data;
                // $scope.searchText = val;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.importPopup = function(){
            $("#import_categories").modal('show');
        }

        $scope.saveCategoriesImport = function(){
            $scope.isDisabled= true;
            var formElement = document.getElementById("categoriesDetails");
            var categoriesDetails = new FormData(formElement);
            $http.post($scope.hospitalServerURL + '/categoriesImportSave',categoriesDetails,
                { headers: {'Content-Type': undefined},
                    transformRequest: angular.identity,
                }).then(function (response) {
                    $("#import_categories").modal('hide');
                    $scope.getPaginatedCategoryList();
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
        $scope.getPaginatedCategoryList = function (page) {
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
            $http.post($scope.hospitalServerURL + '/getPaginatedCategoryList?&searchText=' + $scope.searchText, angular.toJson(paginationDetails)).then(function (response) {
                var data = response.data;
                $scope.categoriesList = data.list;
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
        $scope.getPaginatedCategoryList();

        $scope.feeconfigurationList=function () {
            $window.location.href = '/home#!/services_master';
        };

        $scope.addCategories = function () {
            $scope.operation='create';
            // $scope.removeCategories();
            $("#submit").text("Save");
            $('#student-title').text("Add Categories");
            $("#add_categories").modal('show');
        };
        $scope.removeCategories();

        $scope.saveCategories = function () {
            if (angular.isUndefined($scope.name) || $scope.name == ''||$scope.name == null) {
                Notification.warning({message: 'Please Enter the Name', positionX: 'center', delay: 2000});
                
            } else {
                var saveCategoriesDetails;
                saveCategoriesDetails = {
                    id: $scope.id,
                    name: $scope.name,
                    description: $scope.description
                };
                $http.post($scope.hospitalServerURL + "/saveNewCategory", angular.toJson(saveCategoriesDetails)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#add_categories").modal('hide');
                        if($scope.operation=='Edit'){
                            Notification.success({
                                message: 'Category is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }else {
                            Notification.success({
                                message: 'Category is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        $scope.removeCategories();
                        $scope.getPaginatedCategoryList();
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
        $scope.DeleteCategories = function (data) {
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
                        $http.post($scope.hospitalServerURL + "/deleteCategories", angular.toJson(deleteDetails, "Create")).then(function (response, status, headers, config) {
                            var data = response.data;
                            $scope.getPaginatedCategoryList();
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
