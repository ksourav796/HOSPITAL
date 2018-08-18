app.controller('appointmentController',
    function($scope, $http, $location, $filter, Notification, ngTableParams,  $timeout, $window, $rootScope) {
        console.log("aaaaaaaaaaaaa");
        $scope.hospitalServerURL = "/hospital";
        $scope.word = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
        $scope.customerId = 1;
        $scope.userRights = [];
        $scope.operation = 'Create';
        $scope.customer = 1;
        $scope.today = new Date();


        $scope.dateOptions = {
            minDate : new Date()
        };

        $scope.open8 = function () {
            $scope.popup1.opened = true;
        };
        $scope.popup1 = {
            opened: false
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

        $scope.getMedicineUom =function(medicineName){
            $http.post($scope.hospitalServerURL + "/getUOMListBasedOnMedicine?medicineName=" +medicineName).then(function (response) {
                var data = response.data;
                $scope.UOMList=data;
            });
        }

        $scope.MedicineUom = function(medicine,index){
            var url = "/hospital/getMedicineUom?medicineName=" + medicine.medicine;
            $http.post(url).then(function (response) {
                var data = response.data;
                medicine.uom=data[0].unitOfMeasurement;

            })
        }

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


        $scope.serviceDetailsList = [];
        $scope.addNew = function(){
            if($scope.serviceDetailsList==""){
                $scope.serviceDetailsList = [];
            }
            $scope.serviceDetailsList.push({
                serviceName:'',
                servicePrice:'',
                unitPrice:'',
                qty:1,
                discountConfigAmt:0,
                amount:'',
                discountAmt:'',
                totalamt:''

            });
            $scope.numberList=[];
            $scope.numberList.push(0);
            for(i=5;i<=100;i+=4){
                $scope.numberList.push(i++);
            }
            $scope.PD = {};
        };

        $scope.removefunc = function(){
            var newDataList=[];
            $scope.selectedAll = false;
            angular.forEach($scope.serviceDetailsList, function(selected){
                if(!selected.selected){
                    newDataList.push(selected);
                }
            });
            $scope.serviceDetailsList = newDataList;
        };

        $scope.checkAll = function () {
            if (!$scope.selectedAll) {
                $scope.selectedAll = true;
            } else {
                $scope.selectedAll = false;
            }
            angular.forEach( $scope.serviceDetailsList, function (service) {
                service.selected = $scope.selectedAll;
            });
        };

        $scope.medicineDetailsList = [];
        $scope.addNew1= function(){
            if($scope.medicineDetailsList==""){
                $scope.medicineDetailsList = [];
            }
            $scope.medicineDetailsList.push({
                patient:'',
                medicine:'',
                duration:'',
                morning:'',
                afternoon:'',
                night:'',
                qty:'',
                mrngTablets:'',
                aftnTablets:'',
                nightTablets:'',
                unitOfMeasurement:''

            });
            $scope.numberList=[];
            $scope.numberList.push(0);
            for(i=1;i<=100;){
                $scope.numberList.push(i++);
            }
            $scope.PD = {};
        };

        $scope.removefunction = function(){
            var newDataList=[];
            $scope.selectedAll = false;
            angular.forEach($scope.medicineDetailsList, function(selected){
                if(!selected.selected){
                    newDataList.push(selected);
                }
            });
            $scope.medicineDetailsList = newDataList;
        };

        $scope.checkAll = function () {
            if (!$scope.selectedAll) {
                $scope.selectedAll = true;
            } else {
                $scope.selectedAll = false;
            }
            angular.forEach( $scope.medicineDetailsList, function (medicine) {
                medicine.selected = $scope.selectedAll;
            });
        };




        $scope.invoice = function (data) {
            if (angular.isUndefined(data.services)) {
                $scope.price = data.services.price;
                $scope.name = data.services.name;
               $scope.qty="1";
            }
            $scope.appointmentId =data.id;
            $http.post($scope.hospitalServerURL + '/getInvoiceList?id=' + $scope.appointmentId).then(function (response) {
                var data = response.data;
                $scope.serviceDetailsList=data;
                $scope.numberList=[];
                for(i=5;i<=100;i+=4){
                    $scope.numberList.push(i++);
                }
                $("#add_invoice").modal('show');
            });
        };

        $scope.prescription = function (data) {
            var firstName = data.customers.firstName;
            var secondName=data.customers.lastName;
            $scope.appointmentId =data.id;
            $scope.prescPatient =firstName.concat(secondName);
            // $http.post($scope.hospitalServerURL + "/getPrescription?id="+data.id).then(function (response) {
            $http.post($scope.hospitalServerURL + "/getPrescription?id="+ $scope.appointmentId).then(function (response) {
                var data = response.data;
                if(data==""){
                    $scope.medicineDetailsList=[];
                }
                $scope.medicineDetailsList=data[0].medicineList;
                $scope.numberList=[];
                for(i=1;i<=100;){
                    $scope.numberList.push(i++);
                }
            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            });

            $("#add_prescription").modal('show');
        };


        $scope.addCategorie = function () {
            $scope.operation='create';
            // $scope.removeCategories();
            $('#student-title').text("Add Categories");
            $("#add_Categorie").modal('show');
        };



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


        $scope.savePrescription=function () {
            $http.post($scope.hospitalServerURL + "/savePrescription?appointmentId="+$scope.appointmentId,angular.toJson($scope.medicineDetailsList)).then(function (response) {
                var data = response.data;
                if (data == "") {
                    Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                }
                else {
                    $("#add_prescription").modal('hide');
                    $scope.getMedicineList();

                    Notification.success({
                        message: 'Prescription Created  successfully',
                        positionX: 'center',
                        delay: 2000
                    });
                    $("#add_prescription").modal('hide');
                }
            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            });

        }

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


        $scope.saveservices = function () {
            if (angular.isUndefined($scope.categoryId) || $scope.categoryId == ''|| $scope.categoryId==null) {
                Notification.warning({message: 'Please select Category', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.name) || $scope.name == '') {
                Notification.warning({message: 'Services Name can not be Empty', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.currency) || $scope.currency == '' || $scope.currency==null) {
                Notification.warning({message: 'Please select currency', positionX: 'center', delay: 2000});
            }

            else  if (angular.isUndefined($scope.availableTypes) || $scope.availableTypes == '') {
                Notification.warning({message: 'Please select AvailableType', positionX: 'center', delay: 2000});
            }
            else  if (angular.isUndefined($scope.flag) || $scope.flag == '') {
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
                        $scope.getServicesList();
                        // $scope.getCategoriesList();
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


        $scope.saveServices=function () {
            $http.post($scope.hospitalServerURL + "/saveServiceInv?appointmentId="+$scope.appointmentId, angular.toJson($scope.serviceDetailsList)).then(function (response) {
                var data = response.data;
                if (data == "") {
                    Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                }
                else {
                    $("#add_invoice").modal('hide');
                    $scope.getServicesList();

                    Notification.success({
                        message: 'Invoice is Created  successfully',
                        positionX: 'center',
                        delay: 2000
                    });
                    $("#add_invoice").modal('hide');
                }
            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            });

        }


        $scope.remove = function () {
            $scope.id = null;
            $scope.serviceName = null;
            $scope.providerName = null;
            $scope.firstname = "";
            $scope.lastname = "";
            $scope.email = "";
            $scope.mobileNumber = "";
            $scope.phoneNumber = "";
            $scope.address = "";
            $scope.notes = "";
            $scope.zipCode ="";
            $scope.gender = "";
            $scope.occupation = "";
            $scope.maritalStatus = "";
            $scope.appntTime="";
        };
        $scope.today = function () {
            $scope.fromDate = new Date();
            $scope.todate = new Date();
        };
        $scope.today();
        $scope.format = 'dd/MM/yyyy';

        $scope.open1 = function () {
            $scope.popup1.opened = true;
        };
        $scope.popup1 = {
            opened: false
        };
   $scope.open2 = function () {
            $scope.popup2.opened = true;
        };
        $scope.popup2 = {
            opened: false
        };
   $scope.open3 = function () {
            $scope.popup3.opened = true;
        };
        $scope.popup3 = {
            opened: false
        };

        $scope.backServices=function(){
            window.location.href='#!/users';
        }


        $scope.importPopup = function(){
            $("#import_Appointment").modal('show');
        }


        $scope.saveAppointmentImport = function(){
            $scope.isDisabled= true;
            var formElement = document.getElementById("appointmentDetails");
            var appointmentDetails = new FormData(formElement);
            $http.post($scope.hospitalServerURL + '/appointmentImportSave',appointmentDetails,
                { headers: {'Content-Type': undefined},
                    transformRequest: angular.identity,
                }).then(function (response) {
                    $("#import_Appointment").modal('hide');
                    $scope.getPaginatedAppointmentList();
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

        $scope.getServicesList = function () {
            $(".loader").css("display", "block");
            $http.post($scope.hospitalServerURL + '/getServices').then(function (response) {
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
        $scope.getServicesList();

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
        // $scope.getProvidersList();
        $scope.getProvidersList();

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
        $scope.getMedicineList();

        $scope.getAppointmentList = function (searchText) {
            if (angular.isUndefined(searchText)) {
                $scope.searchText = "";
            }
            $(".loader").css("display", "block");
            $http.post($scope.hospitalServerURL + '/getAppointment').then(function (response) {
                var data = response.data.object;
                $scope.appointmentList = data;
                console.log($scope.appointmentList);
                // $scope.searchText = val;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };


        $scope.getPaginatedAppointmentList = function (page) {
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
            $http.post($scope.hospitalServerURL + '/getPaginatedAppointmentList?&searchText=' + $scope.searchText, angular.toJson(paginationDetails)).then(function (response) {
                var data = response.data;
                console.log(data);
                $scope.appointmentList = data.list;
                $scope.first = data.firstPage;
                $scope.last = data.lastPage;
                $scope.prev = data.prevPage;
                $scope.next = data.nextPage;
                $scope.pageNo = data.pageNo;
                // $scope.listStatus = true;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getPaginatedAppointmentList();

        $scope.getPatientByName=function() {
            $http.post($scope.hospitalServerURL + "/getPatientByName?name=" + $scope.firstname).then(function (response) {
                var data = response.data;
                $scope.phoneNumber=data.phoneNumber;

                $scope.lastName=data.lastName;
                $scope.email=data.email;
                $scope.address=data.address;
                $scope.city=data.city;
                $scope.uhid1=data.uhid;
                $scope.countryName=data.countryName;
                $scope.stateName=data.stateName;
                $scope.appntTime=data.starttime;
                $scope.notes=data.notes;
                $scope.zipCode=data.zipCode;
                $scope.gender=data.gender;
                $scope.occupation=data.occupation;
                $scope.age=data.age;
                $scope.maritalStatus=data.status;
                // appointmentNo: $scope.appointmentNo
                $scope.cheif=data.cheifComplaints;
                $scope.historyofpresenting=data. history;
            });
        };
        $scope.saveMedicine = function () {
            if (angular.isUndefined($scope.medicineName) || $scope.medicineName == ''||$scope.medicineName ==null) {
                Notification.warning({message: 'medicineName Name can not be Empty', positionX: 'center', delay: 2000});

            } else {
                var saveMedicineDetails;
                saveMedicineDetails = {
                    id: $scope.id,
                    medicineName: $scope.medicineName,
                    code: $scope.code
                };
                $http.post($scope.hospitalServerURL + "/saveMedicineDetails", angular.toJson(saveMedicineDetails)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#add_medicine").modal('hide');
                        if($scope.operation=='Edit'){
                            Notification.success({
                                message: 'Medicines is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }else {
                            Notification.success({
                                message: 'Medicines is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        $scope.removeMedicine();
                        // $scope.getMedicineList();
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

        $scope.getUOMList = function (searchText) {
            if (angular.isUndefined(searchText)) {
                $scope.searchText = "";
            }
            $(".loader").css("display", "block");
            $http.post($scope.hospitalServerURL + '/getUOMList').then(function (response) {
                var data = response.data.object;
                $scope.UOMList = data;
                // $scope.searchText = val;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })
        };
        $scope.getUOMList();


        $scope.uomvalue=function (data) {
            angular.forEach($scope.medicineList,function (val,key) {
                if(val.medicineName==data.medicine){
                    data.uom=val.unitOfMeasurement;
                }
            })
        }


        // function appendRow() {
        //     var tbl = document.getElementById('my-table'), // table reference
        //         row = tbl.insertRow(tbl.rows.length),      // append table row
        //         i;
        //     // insert table cells to the new row
        //     for (i = 0; i < tbl.rows[0].cells.length; i++) {
        //         createCell(row.insertCell(i), i, 'row');
        //     }
        // }
        //
        // function createCell(cell, text, style) {
        //     var div = document.createElement('div'), // create DIV element
        //         txt = document.createTextNode(text); // create text node
        //     div.appendChild(txt);                    // append text node to the DIV
        //     div.setAttribute('class', style);        // set DIV class attribute
        //     div.setAttribute('className', style);    // set DIV class attribute for IE (?!)
        //     cell.appendChild(div);                   // append DIV to the table cell
        // }
        //
        // function deleteRows() {
        //     var tfocument.getElementById('my-table'), // table reference
        //         lastRow = tbl.rows.length - 1,             // set the last row index
        //         i;
        //     // delete rows with index greater then 0
        //     for (i = lastRow; i > 0; i--) {
        //         tbl.deleteRow(i);
        //     }
        // }


        $scope.discountConfigAmt=0;
        $scope.serviceUnitPrice=function (data) {
            angular.forEach($scope.servicesList,function (val,key) {
                if(val.name==data.serviceName){
                    data.unitPrice=val.price;
                }
            })
        }

        $scope.editSelectedItemListPrice = function (data,index) {
            var unitPrice = data.unitPrice;
            var qty=data.qty;
            var amtexclusivetax = (parseFloat(unitPrice) * parseFloat(qty));
            $scope.serviceDetailsList[index].amount=parseFloat(amtexclusivetax.toFixed(2));
            var discountamt1=data.discountConfigAmt;
            var discountAmt=((amtexclusivetax*discountamt1)/100);
            $scope.serviceDetailsList[index].discountAmt=parseFloat(discountAmt.toFixed(2));
            var totalamt=amtexclusivetax-discountAmt;
            $scope.serviceDetailsList[index].totalamt=parseFloat(totalamt.toFixed(2));

        };

        $scope.patientVisit = function (data) {
            $scope.customerObj = data;
            $scope.patientName = data.customers.firstName+""+data.customers.lastName;
            $scope.age = data.customers.age;
            $scope.gender = data.customers.gender;
            $("#add_visit").modal('show');
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


        $scope.saveOldAppointments = function() {
            if ($scope.firstname == "" || $scope.firstname == null || angular.isUndefined($scope.firstname)) {
                Notification.error({message: 'Please Enter FirstName', positionX: 'center', delay: 2000})
            } else {
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
                    uhid1: $scope.uhid1,
                    countryName: $scope.countryName,
                    stateName: $scope.stateName,
                    starttime: $scope.appntTime,
                    notes: $scope.notes,
                    zipCode: $scope.zipCode,
                    gender: $scope.gender,
                    occupation: $scope.occupation,
                    age: $scope.age,
                    status: $scope.maritalStatus,
                    // appointmentNo: $scope.appointmentNo
                    cheifComplaints: $scope.cheif,
                    history: $scope.historyofpresenting,
                };

                $http.post($scope.hospitalServerURL + "/saveOldAppointments", angular.toJson(saveAppointmentDetails)).then(function (response) {
                    var data = response.data;
                    $("#add_new_old").modal('hide');
                    $("#add_appointment").modal('hide');
                });
            }
            ;
        }

        $scope.saveAppointment = function () {
            if (angular.isUndefined($scope.firstname) || $scope.firstname == ''||$scope.firstname==null) {
                Notification.warning({message: 'Please Enter the FirstName', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.phoneNumber) || $scope.phoneNumber == ''||$scope.phoneNumber==null) {
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
                    uhid: $scope.uhid,
                    countryName: $scope.countryName,
                    stateName: $scope.stateName,
                    starttime: $scope.appntTime,
                    notes: $scope.notes,
                    zipCode: $scope.zipCode,
                    gender: $scope.gender,
                    occupation: $scope.occupation,
                    age:$scope.age,
                    status: $scope.maritalStatus,
                    // appointmentNo: $scope.appointmentNo
                    cheifComplaints:$scope.cheif,
                    history:$scope.historyofpresenting,
                };

                $http.post($scope.hospitalServerURL + "/saveNewAppointment", angular.toJson(saveAppointmentDetails)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#addon_providers").modal('hide');
                        $("#add_new_patient").modal('hide');
                        $("#add_appointment").modal('hide');
                        $scope.getProvidersList();
                        $scope.getServicesList();
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

                        // $scope.removeAppointment();

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




        $scope.remarks = function (data) {
            $scope.notes=data.customers.notes;
            $scope.id=data.id;
            $http.post($scope.hospitalServerURL + '/getRemarksList?id=' + $scope.id).then(function (response) {
                var data = response.data;
                $("#add_remarks").modal('show');
            });
        };



        // $scope.invoice = function (data) {
        //     $scope.price = data.services.price;
        //         $scope.name = data.services.name;
        //         $scope.appointmentId =data.id;
        //     $http.post($scope.hospitalServerURL + "/getInvoice?id="+data.id).then(function (response) {
        //         var data = response.data;
        //         $scope.serviceDetailsList=data.serviceList;
        //     }, function (error) {
        //         Notification.error({
        //             message: 'Something went wrong, please try again',
        //             positionX: 'center',
        //             delay: 2000
        //         });
        //     });
        //
        //     $("#add_invoice").modal('show');
        // };


        $scope.addinvoice = function (data) {
            // $scope.price=$scope.serviceId.price;

            $("#addon_invoice").modal('show');
        };


        $scope.addmedical = function (data) {
            // $scope.price=$scope.serviceId.price;

            $("#addon_medical").modal('show');
        };


        $scope.addproviders = function (data) {
            $scope.id=null;
            $scope.firstName="";
            $scope.lastName="";
            $scope.email="";
            $scope.phoneNumber="";
            $scope.mobileNumber="";
            $scope.zipCode="";
            $scope.Calender="";
            $scope.username="";
            $scope.password="";
            $scope.retypePassword="";
            $scope.address="";
            $scope.notes="";
            // $scope.price=$scope.serviceId.price;

            $("#addon_providers").modal('show');
        };


        $(function() {
            $('input[name=post-format]').on('click init-post-format', function() {
                $('#gallery-box').toggle($('#post-format-gallery').prop('checked'));
            }).trigger('init-post-format');
        });


        $scope.serviceList=[];
        $scope.pushItemList = function() {
            $scope.serviceList.push({
                serviceName: $scope.name,
                servicePrice: $scope.servicePrice,
            })
        }

        $scope.medicineList=[];
        $scope.pushItemList = function() {
            $scope.medicineList.push({
                patient:$scope.patient,
                medicine: $scope.medicineName,
                duration: $scope.duration,
                morning: $scope.morning,
                afternoon: $scope.afternoon,
                afterFood: $scope.afterFood,
                beforeFood: $scope.beforeFood,
                afterFood1: $scope.afterFood1,
                beforeFood1: $scope.beforeFood1,
                afterFood2: $scope.afterFood2,
                beforeFood2: $scope.beforeFood2,
                unitOfMeasurement: $scope.unitOfMeasurement
            })
        }
        // $scope.saveInvoice=function (data) {
        //     var saveInvoice;
        //     saveInvoice = {
        //         name: $scope.name,
        //         serviceId : $scope.serviceId,
        //         price : $scope.price,
        //         invoiceList: $scope.serviceList
        //     };
        //     $http.post($scope.hospitalServerURL + "/saveInvoice", angular.toJson(saveInvoice)).then(function (response) {
        //         var data = response.data;
        //         if (data == "") {
        //             Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
        //         }
        //         else {
        //             $("#add_invoice").modal('hide');
        //             $scope.getServicesList();
        //             $scope.serviceList=[];
        //             // if ($scope.operation == 'Edit') {
        //             //     Notification.success({
        //             //         message: 'Service is Updated successfully',
        //             //         positionX: 'center',
        //             //         delay: 2000
        //             //     });
        //             // } else {
        //                 Notification.success({
        //                     message: 'Service is Created  successfully',
        //                     positionX: 'center',
        //                     delay: 2000
        //                 });
        //             // }
        //             $("#add_invoice").modal('hide');
        //             // $scope.removeAppointment();
        //
        //             // $scope.removeCategories();
        //             // $scope.getServicesList();
        //         }
        //     }, function (error) {
        //         Notification.error({
        //             message: 'Something went wrong, please try again',
        //             positionX: 'center',
        //             delay: 2000
        //         });
        //     });
        //
        // }

        $scope.schedule = function (data) {
            $http.post($scope.hospitalServerURL + "/getInvoiceDetails?appointmentId="+data.id).then(function (response) {
                $scope.serviceInvoiceList= response.data;
                $scope.totalValue=parseInt(0);
                angular.forEach( $scope.serviceInvoiceList, function (value, key) {
                    $scope.totalValue= parseInt($scope.totalValue + value.totalamt);
                })
                // var invData=response.data;
                // $scope.servicePrice = invData.price;
            });
            $scope.appointmentNo = data.appointmentNo;
            $scope.invoiceNo=data.invoiceNo;
            $scope.fullName = data.customers.firstName+""+data.customers.lastName;
            $scope.emailId =data.customers.email;
            $scope.PhoneNumber =data.customers.phoneNumber;
            $scope.appointmentDate = data.book_datetime;
            if (angular.isUndefined(data.services)) {
                $scope.serviceName = data.services.name;
                $scope.price =data.services.price;
            }
            $scope.servicePrice = data.servicePrice;
            $scope.serviceList = data.invoiceList;
            $("#add_Receipt").modal('show');
        };

        $scope.view = function (data) {

            $http.post($scope.hospitalServerURL + "/getInvoiceDetails?appointmentId="+data.id).then(function (response) {
                $scope.serviceInvoiceList= response.data;
                $scope.totalValue=parseInt(0);
                angular.forEach( $scope.serviceInvoiceList, function (value, key) {
                    $scope.totalValue= parseInt($scope.totalValue + value.totalamt);
                })
                // var invData=response.data;
                // $scope.servicePrice = invData.price;
            });
            $scope.customerObj = data;
            $scope.firstname = data.customers.firstName+""+data.customers.lastName;
            $scope.emailId =data.customers.email;
            $scope.address =data.customers.address;
            $scope.PhoneNumber =data.customers.phoneNumber;
            $scope.city =data.customers.city;
            $scope.zipCode =data.customers.zipCode;
            $scope.appointmentDate = data.book_datetime;
            if (angular.isUndefined(data.services)) {
                $scope.serviceName = data.services.name;
                $scope.price = data.services.price;
            }

            $scope.servicePrice = data.servicePrice;
            $("#add_view").modal('show');
        };

        $scope.addAppointment = function () {
                $http.post($scope.hospitalServerURL + '/getFormSetupValue').then(function (response) {
                    var data = response.data.message;
                    $scope.uhid = data;
                });
                $scope.operation = 'create';
                $scope.countryName = "India";
                $scope.countryState($scope.countryName);
                $scope.stateName = "Karnataka";
                $scope.stateCity($scope.stateName);
                $scope.city = "Bengaluru";
                $scope.date = new Date();
                $scope.serviceName = null;
                // $scope.providersList = [];
                $scope.providerName = null;
                $scope.remove();
                $('#appointment-title').text("Add Appointment");
                $("#add_appointment").modal('show');
            }
            ;
        //}
        $scope.SaveProviders = function (flagName) {
            if (angular.isUndefined($scope.firstName) || $scope.firstName == ''||$scope.firstName== null) {
                Notification.warning({message: ' Please Enter the Name', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.username) || $scope.username == ''||$scope.username==null) {
                Notification.warning({message: 'Please Enter the username ', positionX: 'center', delay: 2000});
            }
            else if (angular.isUndefined($scope.password) || $scope.password == ''||$scope.password==null) {
                Notification.warning({message: ' please enter the password', positionX: 'center', delay: 2000});
            }
           else if($scope.retypePassword!=$scope.password){
                Notification.warning({message: 'Password Should Match',positionX: 'center',delay:2000});
            }
            else {
                var saveProviderDetails;
                saveProviderDetails = {
                    id: $scope.id,
                    firstName: $scope.firstName,
                    lastName: $scope.lastName,
                    email: $scope.email,
                    phoneNumber: $scope.phoneNumber,
                    mobileNumber: $scope.mobileNumber,
                    city: $scope.city,
                    state: $scope.stateName,
                    countryName: $scope.countryName,
                    pinCode: $scope.pinCode,
                    calenderType: $scope.Calender,
                    userName: $scope.userName,
                    password: $scope.password,
                    retypepassword: $scope.retypePassword,
                    address:$scope.address,
                    notes:$scope.notes,
                    serviceslist:$scope.serviceslist,
                    flagType:flagName

                };
                $http.post($scope.hospitalServerURL + "/saveProviderDetails", angular.toJson(saveProviderDetails)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $scope.getProvidersList();
                        $("#addon_providers").modal('hide');
                        if($scope.operation=='Edit'){
                            Notification.success({
                                message: 'Providers is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }else {
                            Notification.success({
                                message: 'Providers is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        $scope.removeProviders();
                        $scope.getProvidersList();
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


        $scope.sendSMS = function(appointment,type){
            var Details;
            Details={
                phoneNumber:appointment.customers.phoneNumber,
                appointmentNo:appointment.appointmentNo,
                type:type
            }
            $http.post("/sms/sendSMSInvoice/",angular.toJson(Details, "Create"))
                .then(function (response) {
                    var data = response.data;
                    $scope.smsData = data;
                });
        };

        $scope.printDivA4 = function (divName) {
            var printContents = document.getElementById(divName).innerHTML;
            var popupWin = window.open('', '_blank', 'width=300,height=300');
            popupWin.document.open();
            popupWin.document.write('<html><head><link rel="stylesheet" type="text/css" media="print" href="poscss/recept_print.css"><link href="css/bootstrap.css" rel="stylesheet"></head><body style="width:595px;" onload="window.print()">' + printContents + '</body></html>');
            popupWin.document.close();
            $('#print_invoice').hide();
            $("#close").hide();
            $("#add_Receipt").modal('hide');
        };

        // $scope.reloadPage = function () {
        //     $window.location.reload();
        // };

        $scope.addMedicine = function () {
            $scope.operation='create';
            // $scope.removeCategories();
            $('#medicine-title').text("Add Medicine");
            $("#add_medicine").modal('show');
        };

        $scope.addServices = function () {
            $scope.operation='create';
            $scope.categoryId=null;
            $scope.currency="INR";
            $scope.name="";
            $scope.duration="";
            $scope.price="";
            $scope.availableTypes="";
            $scope.flag="";
            $scope.attendantsNo="";
            $scope.description="";
            // $scope.removeCategories();
            $('#services-title').text("Add Services");
            $("#add_services").modal('show');
        };


        $scope.saveMedicine = function () {
            if (angular.isUndefined($scope.medicineName) || $scope.medicineName == ''|| $scope.medicineName == null) {
                Notification.warning({message: 'Please Enter the MedicineName', positionX: 'center', delay: 2000});

            } else {
                var saveMedicineDetails;
                saveMedicineDetails = {
                    id: $scope.id,
                    medicineName: $scope.medicineName,
                    code: $scope.code
                };
                $http.post($scope.hospitalServerURL + "/saveMedicineDetails", angular.toJson(saveMedicineDetails)).then(function (response) {
                    var data = response.data;
                    if (data == "") {
                        Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $scope.getMedicineList();
                        $("#add_medicine").modal('hide');
                        if($scope.operation=='Edit'){
                            Notification.success({
                                message: 'Medicines is Updated successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }else {
                            Notification.success({
                                message: 'Medicines is Created  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                        }
                        $scope.removeMedicine();
                        $scope.getMedicineList();
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


        $scope.EditCustomers = function (data) {
            $scope.id = data.id;
            $scope.firstname = data.firstName;
            $scope.lastname = data.lastName;
            $scope.email = data.email;
            $scope.phoneNumber = parseInt(data.phoneNumber);
            $scope.Address = data.address;
            $scope.ZipCode = parseInt(data.zipCode);
            $scope.Notes = data.notes;

            $scope.operation = 'Edit';
            $("#submit").text("Update");
            $('#customers-title').text("Edit Customers");
            $("#add_customers").modal('show');

        }, function (error) {
            Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
        };


        // $scope.EditPatient = function (data) {
        //     $scope.id = data.id;
        //     $scope.name = data.name;
        //     $scope.duration=data.duration;
        //     $scope.price=data.price;
        //     $scope.currency=data.currency;
        //     $scope.categoryId=data.categoryId;
        //     $scope.availableTypes=data.availabilities_type;
        //     $scope.flag=data.flag;
        //     $scope.attendantsNo=data.attendants_number;
        //     $scope.description = data.description;
        $scope.saveRemarks=function(){

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
                notes: $scope.customer.notes,
                zipCode: $scope.zipCode,
                gender: $scope.gender,
                occupation: $scope.occupation,
                age:$scope.age,
                maritalStatus: $scope.maritalStatus,
                // appointmentNo: $scope.appointmentNo
            };
            $http.post($scope.hospitalServerURL + "/saveNewAppointment", angular.toJson(saveAppointmentDetails)).then(function (response) {
                var data = response.data;
                $("#add_remarks").modal('hide');
                $("#add_appointment").modal('hide');

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            });
        };


        $scope.savePatient=function (data) {
            var savePatientDetails;
            savePatientDetails = {
                id: $scope.id,
                patientName: $scope.patientName,
                age:$scope.age,
                gender: $scope.gender,
                sympton: $scope.sympton,
                associatedComplaints: $scope.associatedComplaint,
                hfpc: $scope.historyOfPresentingComplaints,
                pastHistoryOfPatient: $scope.historyOfPatient,
                familyHistory: $scope.familyHistory,
                medicineHistory: $scope.medicineHistory,
                OBGHistory: $scope.obgHistory,
                occupationalHistory: $scope.occupationalHistory,
                bowels: $scope.bowels,
                appetite: $scope.appetite,
                micturation: $scope.micturation,
                sleep: $scope.sleep,
                pulse:$scope.pulse,
                repositoryRate: $scope.repositoryRate,
                bp: $scope.bp,
                temperature: $scope.temperature,
                pallor: $scope.pallor,
                weight: $scope.weight,
                height: $scope.height,
                bmi: $scope.bmi,
                rs: $scope.rs,
                cvs: $scope.cvs,
                jointmobility: $scope.jointMobilit,
                investigation: $scope.investigation,
                diagnosis: $scope.diagnosis,

                prognosis: $scope.prognosis,
                treatment: $scope.treatment,
                medicine : $scope.medicine,
                duration : $scope.duration,
                morning : $scope.morning,
                afternoon : $scope.afternoon,
                night : $scope.night,
                afterFood : $scope.afterFood,
                afterFood1 : $scope.afterFood1,
                afterFood2 : $scope.afterFood2,
                beforeFood : $scope.beforeFood,
                beforeFood1 : $scope.beforeFood1,
                beforeFood2 : $scope.beforeFood2,
                unitOfMeasurement : $scope.unitOfMeasurement,
                customers: data
            };

            $http.post($scope.hospitalServerURL + "/saveNewPatient", angular.toJson(savePatientDetails)).then(function (response) {
                var data = response.data;
                if (data == "") {
                    Notification.error({message: ' Already exists', positionX: 'center', delay: 2000});
                }
                else {
                    $("#add_appointment").modal('hide');
                    $scope.getMedicineList();
                    $scope.getPaginatedAppointmentList();
                    if ($scope.operation == 'Edit') {
                        Notification.success({
                            message: 'Patient is Updated successfully',
                            positionX: 'center',
                            delay: 2000
                        });
                    } else {
                        Notification.success({
                            message: 'Patient is Created  successfully',
                            positionX: 'center',
                            delay: 2000
                        });
                    }
                    $("#add_visit").modal('hide');
                    // $scope.remove();
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

        $scope.calculateBMI=function(height){
            var height=$scope.height;
            var weight=$scope.weight;
            $scope.bmi = parseFloat(weight/(height/100*height/100)).toFixed(2);
        };

        // $scope.getPatientList = function () {
        //     $http.post($scope.hospitalServerURL + '/getPatient').then(function (response) {
        //         var data = response.data.object;
        //         // $scope.currentUserList = data;
        //         console.log(data);
        //         // for(var i=0; i<data.length;i++) {
        //         $scope.id = data.id;
        //         $scope.patientName = data.patientName;
        //         $scope.age = data.age;
        //         $scope.genderName=data.genderName;
        //         $scope.sympton = data.sympton;
        //         $scope.associatedComplaint = data.associatedComplaints;
        //         $scope.historyOfPresentingComplaints = data.hfpc;
        //         $scope.historyOfPatient = data.pastHistoryOfPatient;
        //         $scope.familyHistory = data.familyHistory;
        //         $scope.medicineHistory= data.medicineHistory;
        //         $scope.obgHistory= data.OBGHistory;
        //         $scope.occupationalHistory = data.occupationalHistory;
        //         $scope.bowels = data.bowels;
        //         $scope.appetite = data.appetite;
        //         $scope.micturation = data.micturation;
        //         $scope.sleep = data.sleep;
        //         $scope.pulse = data.pulse;
        //         $scope.repositoryRate = data.repositoryRate;
        //         $scope.bp = data.bp;
        //         $scope.temperature = data.temperature;
        //         $scope.pallor = data.pallor;
        //         $scope.weight = data.weight;
        //         $scope.height = data.height;
        //         $scope.bmi = data.bmi;
        //         $scope.rs = data.rs;
        //         $scope.cvs = data.cvs;
        //         $scope.jointMobilit = data.jointmobility;
        //         $scope.investigation = data.investigation;
        //         $scope.diagnosis = data.diagnosis;
        //
        //         $scope.prognosis = data.prognosis;
        //         $scope.treatment = data.treatment;
        //         // }
        //
        //         // $scope.searchText = val;
        //
        //     }, function (error) {
        //         Notification.error({
        //             message: 'Something went wrong, please try again',
        //             positionX: 'center',
        //             delay: 2000
        //         });
        //     })
        // };
        // $scope.getPatientList();

        $scope.addNewslot = function () {
            if ($scope.serviceName == "" || $scope.serviceName == null || angular.isUndefined($scope.serviceName)) {
                Notification.error({message: ' Please Select Service', positionX: 'center', delay: 2000});
            }
            else if ($scope.providerName == "" || $scope.providerName == null || angular.isUndefined($scope.providerName)) {
                Notification.error({message: ' Please Select Providers', positionX: 'center', delay: 2000});
            }
            else {
                $scope.setSlotsList();
                $("#add_new_Select_modal").modal('show');
            }
        };

        $scope.appendtime=function(time){
            $scope.appntTime=time;
            $("#add_new_Select_modal").modal('hide');

        }
        $scope.saveSlot=function () {
            $("#add_new_Select_modal").modal('hide');
        };
        $scope.addMinutes=function(time, minutes) {
            var date = new Date(new Date('01/01/2015 ' + time).getTime() + minutes * 60000);
            var tempTime = ((date.getHours().toString().length == 1) ? '0' + date.getHours() : date.getHours()) + ':' +
                ((date.getMinutes().toString().length == 1) ? '0' + date.getMinutes() : date.getMinutes()) + ':' +
                ((date.getSeconds().toString().length == 1) ? '0' + date.getSeconds() : date.getSeconds());
            return tempTime;
        }
        $scope.setSlotsList = function () {
            $scope.date=new Date($scope.date);
            $scope.date.setHours(10);
            $http.post($scope.hospitalServerURL + '/getallocatedTimeSlots?date='+$scope.date).then(function (response) {

                var data = response.data;
                $scope.timeslots = data;
                // var starttime = "09:00:00";
                // var interval = "15";
                // var endtime = "17:00:00";
                // $scope.timeslots = [];
                // $scope.timeslots.push({
                //     time: starttime,
                //     status: 'Active'
                // });
                // while (starttime != endtime) {
                //     starttime = $scope.addMinutes(starttime, interval);
                //     $scope.timeslots.push({
                //         time: starttime,
                //         status: 'Active'
                //     });
                // }
                // angular.forEach($scope.timeslots, function (value, key) {
                //     angular.forEach($scope.appointmentTimes, function (val, key) {
                //         if (val.starttime == value.time) {
                //             value.status = 'InActive';
                //         }
                //     })
                // })

            });
        };


        $scope.addNewPatient = function () {
            if ($scope.serviceName == "" || $scope.serviceName == null || angular.isUndefined($scope.serviceName)) {
                Notification.error({message: ' Please Select Service', positionX: 'center', delay: 2000});
            }
            else if ($scope.providerName == "" || $scope.providerName == null || angular.isUndefined($scope.providerName)) {
                Notification.error({message: ' Please Select Providers', positionX: 'center', delay: 2000});
            }
            else {
                $scope.id = null;
                $scope.firstname = "";
                $scope.lastname = "";
                $scope.email = "";
                $scope.mobileNumber = "";
                $scope.phoneNumber = "";
                $scope.address = "";
                $scope.countryName = "India";
                $scope.countryState($scope.countryName);
                $scope.stateName = "Karnataka";
                $scope.stateCity($scope.stateName);
                $scope.city = "Bengaluru";
                $scope.notes = "";
                $scope.zipCode = "";
                $scope.gender = "";
                $scope.occupation = "";
                $scope.age = "";
                $scope.maritalStatus = "";
                $scope.cheif = "";
                $scope.historyofpresenting = "";
                $("#add_new_patient").modal('show');
            }
        };

        $scope.addOldPatient = function () {
            if ($scope.serviceName == "" || $scope.serviceName == null || angular.isUndefined($scope.serviceName)) {
                Notification.error({message: ' Please Select Service', positionX: 'center', delay: 2000});
            }
            else if ($scope.providerName == "" || $scope.providerName == null || angular.isUndefined($scope.providerName)) {
                Notification.error({message: ' Please Select Providers', positionX: 'center', delay: 2000});
            }
            else {
                $scope.firstname = "";
                $scope.lastname = "";
                $scope.email = "";
                $scope.mobileNumber = "";
                $scope.phoneNumber = "";
                $scope.address = "";
                $scope.countryName = "India";
                $scope.countryState($scope.countryName);
                $scope.stateName = "Karnataka";
                $scope.stateCity($scope.stateName);
                $scope.city = "Bengaluru";
                $scope.notes = "";
                $scope.zipCode = "";
                $scope.gender = "";
                $scope.occupation = "";
                $scope.age = "";
                $scope.maritalStatus = "";
                $scope.cheif = "";
                $scope.historyofpresenting = "";
                $("#add_new_old").modal('show');
            }
        };

        $scope.deleteappointment=function(data){
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
                        $http.post($scope.hospitalServerURL + '/deleteappointment?id=' + data.id).then(function (response) {
                            var data = response.data;
                            Notification.success({
                                message: 'Appointments deleted  successfully',
                                positionX: 'center',
                                delay: 2000
                            });
                            $scope.getPaginatedAppointmentList();
                        }, function (error) {
                            Notification.error({
                                message: 'Something went wrong, please try again',
                                positionX: 'center',
                                delay: 2000
                            });
                            $scope.isDisabled = false;
                        });
                    }
                }
            });
        };

        // $scope.operation="";
        // $scope.afterproviders=function(){
        //     $scope.operation='afterproviders'
        // }


    });


