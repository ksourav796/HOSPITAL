
app.controller('smsConfigCtrl',function ($scope,$http,Notification,$timeout) {
        $scope.formsetupId="";
        $scope.typePrefix="";
        $scope.nextRef="";
    $scope.hospitalServerURL = "/hospital";
    $scope.editSms = function (formsetupId) {
            $scope.formSetupIdSms=formsetupId.formsetupId;
            $scope.smsMessage=formsetupId.message;
            $("#edit_sms").modal('show');

        };

    $scope.BackServices=function(){
        window.location.href='#!/services_masters';
    };
      $scope.formsetList = function (page) {
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
        if (angular.isUndefined($scope.formSetupSearchText)) {
            $scope.formSetupSearchText = "";
        }
        $http.post('/hospital' + '/getPaginatedFormsetupList?&searchText=' + $scope.formSetupSearchText, angular.toJson(paginationDetails)).then(function (response) {
            var data = response.data;
            $scope.formsetupList = data.list;
            $scope.first = data.firstPage;
            $scope.last = data.lastPage;
            $scope.prev = data.prevPage;
            $scope.next = data.nextPage;
            $scope.pageNo = data.pageNo;
        },function (error){
            Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
        })
    };
        $scope.formsetList();
        $scope.saveSms= function(){
            $scope.isDisabled= true;
            $timeout(function(){
                $scope.isDisabled= false;
            }, 3000)
            var saveSms;
            saveSms = {
                message : $scope.smsMessage,
                enableSms:$scope.enableSms,
                formsetupId:$scope.formSetupIdSms
            }
            $http.post('/sms'+'/saveSms', angular.toJson(saveSms, "Create")).then(function (response) {
                $("#edit_sms").modal('hide');
                var data = response.data;
                $scope.formsetList();
                Notification.success({message: 'SMS Configured Succesfully', positionX: 'center', delay: 2000});
            })
        };


    }
);