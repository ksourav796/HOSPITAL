app.controller('appReportCtrl',
    function ($scope,$http,Notification,$filter) {
        $scope.hospitalServerURL = "/hospital";

        $scope.getAppointmentList=function () {
        $http.post($scope.hospitalServerURL + '/getAppointment').then(function (response) {
            var data = response.data.object;
            $scope.appointmentList=data;

        })
    }
  $scope.getAppointmentList();
    });