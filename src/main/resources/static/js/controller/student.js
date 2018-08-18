app.controller('studentController',
    function($scope, $http, $location, $filter, Notification, ngTableParams,  $timeout, $window, $rootScope) {
        console.log("aaaaaaaaaaaaa");
        $scope.bshimServerURL = "/hospital";
        $scope.StudentNameText = "";
        $scope.StudentAddress = "";
        $scope.operation = 'Create';
        $scope.StudentAddress=null;
        $scope.StudentName=null;


        $scope.inactiveStatus = "Active";
        $scope.removeStudentDetails = function () {
            $scope.countryId="";

            $scope.StudentNameText = "";
            $scope.StudentAddress = "";


        };
        $scope.addNewStudentPopulate = function(){

        $("#add_new_student_modal").modal('show');
    }

    $scope.saveStudent = function () {
    if ($scope.StudentNameText === ''||$scope.StudentNameText==null||angular.isUndefined($scope.StudentNameText)) {
        Notification.warning({message: 'Enter Student Name', positionX: 'center', delay: 2000});
    }

    else {
        var saveStudentDetails;

        saveStudentDetails = {


            studentName: $scope.StudentNameText,

            studentaddress: $scope.StudentAddress


        };
        $http.post($scope.bshimServerURL + '/saveStudent', angular.toJson(saveStudentDetails, "Create")).then(function (response, status, headers, config) {
            var data = response.data;
            if (data == "") {
                Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
            }
            else {
                $scope.removeStudentDetails();

                $("#add_new_student_modal").modal('hide');
                if (!angular.isUndefined(data) && data !== null) {
                    $scope.studentSearchText = "";
                }
                Notification.success({
                    message: 'Student Created  successfully',
                    positionX: 'center',
                    delay: 2000
                });
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
}})


