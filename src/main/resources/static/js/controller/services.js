app.controller('servicesController',
    function($scope, $http, $location, $filter, Notification, ngTableParams,  $timeout, $window, $rootScope) {
        console.log("aaaaaaaaaaaaa");
        $scope.hospitalServerURL = "/hospital";
        $scope.word = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
        $scope.customerId = 1;
        $scope.userRights = [];
        $scope.operation = 'Create';
        $scope.customer = 1;
        $scope.today = new Date();
        // $route.reload();

        $scope.removeServices = function () {
            $scope.id = "";
            $scope.name = "";
            $scope.duration="";
            $scope.price="";
            $scope.currency="";
            $scope.categoryId="";
            $scope.availableTypes="";
            $scope.flag="";
            $scope.attendantsNo="";
            $scope.description = "";

        };

        $scope.dateOptions = {
            minDate : new Date()
        };

        $scope.open8 = function () {
            $scope.popup1.opened = true;
        };
        $scope.popup1 = {
            opened: false
        };
        $scope.open1 = function () {
            $scope.popup1.opened = true;
        };
        $scope.popup1 = {
            opened: false
        };

        $scope.backServices=function(){
            window.location.href='#!/services_masters';
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


        $scope.EditServices = function (data) {
            $scope.id = data.id;
            $scope.name = data.name;
            $scope.duration=data.duration;
            $scope.price=data.price;
            $scope.currency=data.currency;
            $scope.categoryId=data.categoryId;
            $scope.availableTypes=data.availabilities_type;
            $scope.flag=data.flag;
            $scope.attendantsNo=data.attendants_number;
            $scope.description = data.description;
            $scope.operation = 'Edit';
            $("#submit").text("Update");
            $('#services-title').text("Edit Services");
            $("#add_services").modal('show');

        }, function (error) {
            Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
        };
        $scope.removeServices();

        $scope.getServicesList = function (type,page) {
            if (angular.isUndefined(type,page)) {
                type="";
            }
            $(".loader").css("display", "block");
            $http.post($scope.hospitalServerURL + '/getServices'+ type).then(function (response) {
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

        $scope.getPaginatedServicesList = function (page) {
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
            $http.post($scope.hospitalServerURL + '/getPaginatedServicesList?&searchText=' + $scope.searchText, angular.toJson(paginationDetails)).then(function (response) {
                var data = response.data;
                $scope.servicesList = data.list;
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
        $scope.getPaginatedServicesList();

        $scope.getCategoriesList = function () {
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
        $scope.getCategoriesList();

        $scope.feeconfigurationList=function () {
            $window.location.href = '/home#!/services_master';
        };

        $scope.addServices = function () {
            $scope.operation='create';
            $scope.categoryId=null;
            $scope.currency="INR";
            // $scope.removeCategories();
            $("#submit").text("save");
            $('#services-title').text("Add Services");
            $("#add_services").modal('show');
        };
        $scope.removeServices();

        $scope.addAppointment = function () {
            $http.post($scope.hospitalServerURL + '/getFormSetupValue').then(function (response) {
                var data = response.data.message;
                $scope.uhid=data;
            });
            $scope.operation = 'create';
            $scope.countryName = "India";
            $scope.countryState($scope.countryName);
            $scope.stateName="Karnataka";
            $scope.stateCity($scope.stateName);
            // $scope.getStateCity($scope.city);
            $scope.serviceName = null;
            $scope.providerName=null;
            $('#appointment-title').text("Add Appointment");
            $("#add_appointments").modal('show');
        };

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
                $scope.city="Bengaluru";
            })
        }

        $scope.addCategorie = function () {
            $scope.operation='create';
            // $scope.removeCategories();
            $('#student-title').text("Add Categories");
            $("#add_Categorie").modal('show');
        };

        $scope.addConsultation = function () {
            $scope.operation='create';
            // $scope.removeCategories();
            $('#student-title').text("Consultation");
            $("#add_Consultation").modal('show');
        };

        $scope.others = function () {
            $scope.operation='create';
            $scope.notes="";
            // $scope.removeCategories();
            // $('#student-title').text("Add Categories");
            $("#add_others").modal('show');
        };

        $scope.addMedicine = function () {
            $scope.operation='create';
            // $scope.removeCategories();
            // $('#student-title').text("Add Categories");
            $("#add_Medicine").modal('show');
        };

        $scope.addNewCurrency1 = function () {
            $scope.currencyId = "";
            $scope.CurrencyWordText = "";
            $scope.CurrencyCodeText = "";
            $scope.CurrencysymbolText = "";
            $scope.CurrencyDescriptionText = "";
            $scope.CurrencysymbolplaceText = "";
            $scope.AccountCodeText = "";
            $scope.currencyStatusText = "Active";
            $("#title").text("Add Currency");
            $("#submit").text("Save");
            $("#add_Currency_modal").modal('show');
        };


        $scope.importPopup = function(){
            $("#import_services").modal('show');
        }

        $scope.saveServicesImport = function(){
            $scope.isDisabled= true;
            var formElement = document.getElementById("serviceDetails");
            var serviceDetails = new FormData(formElement);
            $http.post($scope.hospitalServerURL + '/categoriesImportSave',serviceDetails,
                { headers: {'Content-Type': undefined},
                    transformRequest: angular.identity,
                }).then(function (response) {
                    $("#import_services").modal('hide');
                    $scope.getPaginatedServicesList();
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

        $scope.saveNewCurrency = function () {
            if (angular.isUndefined($scope.CurrencyCodeText) || $scope.CurrencyCodeText == '') {
                Notification.warning({message: 'Please Enter Currency Code', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.CurrencyWordText) || $scope.CurrencyWordText == '') {
                Notification.warning({message: 'Please Enter the Currency Word', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.CurrencysymbolText) || $scope.CurrencysymbolText == '') {
                Notification.warning({
                    message: 'Currency symbol Text can not be Empty',
                    positionX: 'center',
                    delay: 2000
                });
            }
            else {
                $scope.isDisabled = true;
                $timeout(function () {
                    $scope.isDisabled = false;
                }, 3000)
                var saveCurrencyDetails;
                saveCurrencyDetails = {
                    currencyId: $scope.currencyId,
                    currencyName: $scope.CurrencyWordText,
                    currencyCode: $scope.CurrencyCodeText,
                    currencySymbol: $scope.CurrencysymbolText,
                    currencyDescription: $scope.CurrencyDescriptionText,
                    currencySymbolPlace: $scope.CurrencysymbolplaceText,
                    AccountCode: $scope.AccountCodeText,
                    status: $scope.currencyStatusText
                };
            }
            $http.post( $scope.hospitalServerURL  + '/saveCurrency', angular.toJson(saveCurrencyDetails, "Create")).then(function (response) {
                var data = response.data;
                if (data == "") {
                    Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                }
                else {
                    $scope.getCurrencyList();
                    $("#add_Currency_modal").modal('hide');
                    Notification.success({message: 'Currency Created  successfully', positionX: 'center', delay: 2000});
                }
            }, function (error) {
            });
        };

        $scope.saveCategories = function () {
            if (angular.isUndefined($scope.categoryname) || $scope.categoryname == '') {
                Notification.warning({message: 'Category Name can not be Empty', positionX: 'center', delay: 2000});

            } else {
                var saveCategoriesDetails;
                saveCategoriesDetails = {
                    id: $scope.id,
                    name: $scope.categoryname,
                    description: $scope.Description
                };
                $http.post($scope.hospitalServerURL + "/saveNewCategory", angular.toJson(saveCategoriesDetails)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $scope.getCategoriesList();
                        $("#add_Categorie").modal('hide');
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
                        $scope.getCategoriesList();
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

        $scope.saveNotes = function () {
            if (angular.isUndefined($scope.notes) || $scope.notes == ''||$scope.notes==null) {
                Notification.warning({message: 'Please Fill the Field', positionX: 'center', delay: 2000});
            }
            else {
                var saveNotes;
                saveNotes = {
                    id: $scope.id,
                    notes: $scope.notes,
                };
                $http.post($scope.hospitalServerURL + "/saveNotes", angular.toJson(saveNotes)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        // $scope.getCategoriesList();
                        $("#add_others").modal('hide');
                        if ($scope.operation == 'Edit') {
                            Notification.success({
                                message: 'notes is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        } else {
                            Notification.success({
                                message: 'notes is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        // $scope.removeCategories();
                        // $scope.getCategoriesList();
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


        $scope.getMedicineList = function (searchText) {
            if (angular.isUndefined(searchText)) {
                $scope.searchText = "";
            }
            $(".loader").css("display", "block");
            $http.post($scope.hospitalServerURL + '/getMedicine').then(function (response) {
                var data = response.data.object;
                $scope.medicineList = data;
                // $scope.searchText = val;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getPaginatedMedicineList = function (page) {
            switch (page) {
                case 'firstPage':
                    $scope.firstPage = true;
                    $scope.lastPage = false;
                    $scope.pageNo = 0;
                    break;
                case 'lastPage':
                    $scope.lastPage = true;
                    $scope.firstPage = false;
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
            $http.post($scope.hospitalServerURL + '/getPaginatedMedicineList?&searchText=' + $scope.searchText, angular.toJson(paginationDetails)).then(function (response) {
                var data = response.data;
                $scope.medicineList = data.list;
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
        $scope.getPaginatedMedicineList();



        $scope.saveMedplus = function () {
            if (angular.isUndefined($scope.medicine) || $scope.medicine == '') {
                Notification.warning({message: 'Medicine Name can not be Empty', positionX: 'center', delay: 2000});

            } else {
                var saveMedplus;
                saveMedplus = {
                    id: $scope.id,
                    medicine: $scope.medicine,
                    quantity: $scope.quantity
                };
                $http.post($scope.hospitalServerURL + "/saveMedplus", angular.toJson(saveMedplus)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        // $scope.getCategoriesList();
                        $("#add_Medicine").modal('hide');
                        if($scope.operation=='Edit'){
                            Notification.success({
                                message: 'Medicine is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }else {
                            Notification.success({
                                message: 'Medicine is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        // $scope.removeCategories();
                        // $scope.getCategoriesList();
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



        $scope.saveConsultation = function () {
            if (angular.isUndefined($scope.patientName) || $scope.patientName == '') {
                Notification.warning({message: 'patientName Name can not be Empty', positionX: 'center', delay: 2000});

            } else {
                var saveConsultationDetails;
                saveConsultationDetails = {
                    id: $scope.id,
                    patientName: $scope.patientName,
                    mobileNumber: $scope.mobileNumber,
                    email: $scope.email,
                    alternativeNumber: $scope.alternativeNumber,
                    address: $scope.address,
                    gender: $scope.gender,
                    occupation: $scope.occupation,
                    maritalStatus: $scope.maritalStatus,
                    age: $scope.age,
                    education: $scope.education
                };
                $http.post($scope.hospitalServerURL + "/saveConsultationDetails", angular.toJson(saveConsultationDetails)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        // $scope.getCategoriesList();
                        $("#add_Consultation").modal('hide');
                        if($scope.operation=='Edit'){
                            Notification.success({
                                message: 'Consultation is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }else {
                            Notification.success({
                                message: 'Consultation is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        // $scope.removeCategories();
                        // $scope.getCategoriesList();
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



        $scope.getCurrencyList = function (type) {
            if (angular.isUndefined(type)) {
                type = "";
            }
            $http.post( $scope.hospitalServerURL  + "/getCurrencyList?&type=" + type).then(function (response) {
                var data = response.data;
                console.log(data);
                var i = 0;
                $scope.currencyList = data;
                $scope.listStatus = true;
                angular.forEach($scope.currencyList, function (value, key) {
                    if (value.currencyCode.toUpperCase() == type.toUpperCase()) {
                        if (value.status == 'InActive') {
                            i++;
                        }
                    }
                })
                if (i > 0) {
                    Notification.warning({
                        message: 'currency Became InActive',
                        positionX: 'center',
                        delay: 2000
                    });
                }
                // $scope.removeCurrencyDetails();

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getCurrencyList();



        $scope.saveAppointment = function () {
            // if (angular.isUndefined($scope.serviceName) || $scope.serviceName == '' ) {
            //     Notification.warning({message: 'fill all the fields', positionX: 'center', delay: 2000});
            // }
            if (angular.isUndefined($scope.firstname) || $scope.firstname == '') {
                Notification.warning({message: 'Patient Name cannot be empty', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.phoneNumber) || $scope.phoneNumber == '') {
                Notification.warning({message: 'Enter PhoneNumber', positionX: 'center', delay: 2000});
            }
            // else if (angular.isUndefined($scope.email) || $scope.email == '' ||  $scope.email ==) {
            //     Notification.warning({message: 'Enter valid emailId', positionX: 'center', delay: 2000});
            // }
            else {
                var saveAppointmentDetails;
                saveAppointmentDetails = {
                    id: $scope.id,
                    serviceName: $scope.serviceName,
                    providerName: $scope.providerName,
                    book_datetime: $scope.date,
                    firstName: $scope.firstname,
                    lastName: $scope.lastname,
                    email: $scope.email,
                    mobileNumber: $scope.mobileNumber,
                    phoneNumber: $scope.phoneNumber,
                    address: $scope.address,
                    city: $scope.city,
                    countryName: $scope.countryName,
                    stateName: $scope.stateName,
                    notes: $scope.notes,
                    zipCode: $scope.zipCode,
                    uhid:$scope.uhid,
                    gender: $scope.gender,
                    occupation: $scope.occupation,
                    age:$scope.age,
                    maritalStatus: $scope.maritalStatus,
                    cheifComplaints:$scope.cheif,
                    history:$scope.historyofpresenting,

                    // appointmentNo: $scope.appointmentNo
                };

                $http.post($scope.hospitalServerURL + "/saveNewAppointment", angular.toJson(saveAppointmentDetails)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#addon_providers").modal('hide');
                        $("#add_appointments").modal('hide');
                        $scope.getProvidersList();
                        $scope.getPaginatedAppointmentList();
                        $scope.sendSMS(data,"AppointmentNumber");
                        if ($scope.operation == 'Edit') {
                            Notification.success({
                                message: 'appointment is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        } else {
                            Notification.success({
                                message: 'appointment is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }

                        $scope.removeAppointment();

                        // $scope.removeCategories();
                        $scope.getPaginatedAppointmentList();
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

        // $scope.getCategoriesList();
        $scope.saveServices = function () {
         if (angular.isUndefined($scope.categoryId) || $scope.categoryId == ''|| $scope.categoryId==null) {
                Notification.warning({message: 'Please select Category', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.name) || $scope.name == ''||$scope.name == null) {
             Notification.warning({message: 'Services Name can not be Empty', positionX: 'center', delay: 2000});
           }
           else if (angular.isUndefined($scope.currency) || $scope.currency == '' || $scope.currency==null) {
                Notification.warning({message: 'Please select currency', positionX: 'center', delay: 2000});
            }
            else  if (angular.isUndefined($scope.availableTypes) || $scope.availableTypes == ''||$scope.availableTypes == null) {
                Notification.warning({message: 'Please select AvailableType', positionX: 'center', delay: 2000});
            }
            else  if (angular.isUndefined($scope.flag) || $scope.flag == '' || $scope.flag == null) {
                Notification.warning({message: 'Please select  Flag', positionX: 'center', delay: 2000});
            }
            else {
                var saveServicesDetails;
                saveServicesDetails = {
                    id: $scope.id,
                    name: $scope.name,
                    duration:$scope.duration,
                    price:$scope.price,
                    currency:$scope.currency,
                    categoryId:$scope.categoryId,
                    availabilities_type:$scope.availableTypes,
                    flag:$scope.flag,
                    attendants_number:$scope.attendantsNo,
                    description: $scope.description
                };
                $http.post($scope.hospitalServerURL + "/saveNewServices", angular.toJson(saveServicesDetails)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $scope.getCategoriesList();
                        $("#add_services").modal('hide');
                        if($scope.operation=='Edit'){
                            Notification.success({
                                message: 'Services is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }else {
                            Notification.success({
                                message: 'Services is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        $scope.removeServices();
                        $scope.getPaginatedServicesList();
                    }
                }, function (error) {
                    Notification.error({
                        message: 'Add mandatory fields, please try again',
                        positionX: 'center',
                        delay: 2000
                    });
                });
            }
        };


        $scope.DeleteServices = function (data) {
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
                            duration:data.duration,
                            price:data.price,
                            currency:data.currency,
                            categoryId:data.categoryId,
                            availableTypes:data.availableTypes,
                            flag:data.flag,
                            attendantsNo:data.attendantsNo,
                            description: data.description

                        };
                        $http.post($scope.hospitalServerURL + "/deleteServices", angular.toJson(deleteDetails, "Create")).then(function (response, status, headers, config) {
                            var data = response.data;
                            $scope.getPaginatedServicesList();
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
