// var taskManagerModule = angular.module('taskManagerApp', ['ngRoute','ngAnimate']);
//

var app = angular.module('myApp',['ngRoute', 'ngAnimate',
    'ngSanitize','ui-notification','ngTable',
    'ngCookies', 'angular.filter','ui.bootstrap']);


app.config(['$routeProvider', function($routeProvider) {
    $routeProvider

        .when("/Student", {
            templateUrl: "partials/Student.html",
            controller: "studentController"
        })
        .when("/login", {
            templateUrl: "partials/login.html",
            controller: "loginController"
        })
        .when("/uominv", {
            templateUrl: "partials/uominv.html",
            controller: "uomController"
        })
        .when("/calender", {
            templateUrl: "partials/calender.html",
            controller: "calenderController"
        })
        .when("/customers", {
            templateUrl: "partials/customers.html",
            controller: "customersController"
        })
        .when("/employee", {
            templateUrl: "partials/employee.html",
            controller: "employeeCtrl"
        })
        .when("/scheduler", {
            templateUrl: "partials/schedular.html",
            controller: "schedulerCtrl"
        })
        .when("/appointment", {
            templateUrl: "partials/appointment.html",
            controller: "appointmentController"
        })
        .when("/services", {
        templateUrl: "partials/services.html",
        controller: "servicesController"
        })

        .when("/categories", {
        templateUrl: "partials/categories.html",
        controller: "categoriesController"
        })
        .when("/medicine", {
        templateUrl: "partials/medicine.html",
        controller: "MedicineController"
        })

        .when("/services_masters", {
            templateUrl: "partials/services_masters.html",
            controller: "services_mastersController"
        })

        .when("/users", {
            templateUrl: "partials/users.html",
            controller: "usersController"
        })
        .when("/settings", {
            templateUrl: "partials/settings.html",
            controller: "settingsController"
        })
        .when("/formsetup", {
            templateUrl: "partials/formsetup.html",
            controller: "formsetupCtrl"
        })

        .when("/provider", {
            templateUrl: "partials/providers.html",
            controller: "providersController"
        })

        .when("/hlcfiles", {
            templateUrl: "partials/hlcfiles.html",
            controller: "hlcController"
        })

        .when("/secretaries", {
            templateUrl: "partials/secretaries.html",
            controller: "secretariesController"
        })

        .when("/admins", {
            templateUrl: "partials/admins.html",
            controller: "adminsController"
        })

        .when("/general", {
            templateUrl: "partials/general.html",
            controller: "generalController"
        })
        .when("/currency", {
            templateUrl: "partials/currency.html",
            controller: "currencyCtrl"
        })

        .when("/country", {
            templateUrl: "partials/country.html",
            controller: "countryCtrl"
        })

        .when("/state", {
            templateUrl: "partials/state.html",
            controller: "stateCtrl"
        })
        .when("/city", {
            templateUrl: "partials/city.html",
            controller: "cityCtrl"
        })
        .when("/smsConfig", {
            templateUrl: "partials/smsConfigurator.html",
            controller: "smsConfigCtrl as rest"
        })

        .when("/businessLogic", {
            templateUrl: "partials/businessLogic.html",
            controller: "businessLogicController"
        })

        .when("/currentUser", {
            templateUrl: "partials/currentUser.html",
            controller: "currentUserController"
        })

        .when("/aboutEasyAppointments", {
            templateUrl: "partials/aboutEasyAppointments.html",
            controller: "aboutEasyAppointmentsController"
        })

        .when("/workingPlan", {
            templateUrl: "partials/workingPlan.html",
            controller: "workingPlanController"
        })

        .when("/breaks", {
            templateUrl: "partials/breaks.html",
            controller: "breaksController"
        })
        .when("/emailServer", {
            templateUrl: "partials/emailserver.html",
            controller: "emailserverCtrl"
        })
        .when("/reports", {
            templateUrl: "partials/reports.html"
        })
        .when("/appointmentReport", {
            templateUrl: "partials/appointmentreport.html",
            controller: "appReportCtrl"
        })
        .when("/reportMailScheduler", {
            templateUrl: "partials/reportMailScheduler.html",
            controller: "reportMailSchedulerCtrl as rest"
        })
        .when("/smsServer", {
            templateUrl: "partials/smsServer.html",
            controller: "messageServerCtrl as rest"
        })
        .otherwise({redirectTo:'/login'});
}]);

app.directive("limitTo", [function () {
    return {
        restrict: "A",
        link: function (scope, elem, attrs) {
            var limit = parseInt(attrs.limitTo);
            angular.element(elem).on("keypress", function (e) {
                if (this.value.length === limit)
                    e.preventDefault();
            });
        }
    };
}]);
/* for only  Alpha without space function
 */
app.directive('alphaWithoutSpace', function() {
    return {
        require: '?ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
            if(!ngModelCtrl) {
                return;
            }

            ngModelCtrl.$parsers.push(function(val) {
                var clean = val.replace( /[^a-zA-Z]/g,'');
                if (val !== clean) {
                    ngModelCtrl.$setViewValue(clean);
                    ngModelCtrl.$render();
                }
                return clean;
            });

            element.bind('keypress', function(event) {
                if(event.keyCode === 32) {
                    event.preventDefault();
                }
            });
        }
    };
});
/* validation
 * for only number with space function
 */
app.directive('numWithSpace', function() {
    return {
        require: '?ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
            if(!ngModelCtrl) {
                return;
            }

            ngModelCtrl.$parsers.push(function(val) {
                var clean = val.replace( /[^\s^0-9]+/g,'');
                if (val !== clean) {
                    ngModelCtrl.$setViewValue(clean);
                    ngModelCtrl.$render();
                }
                return clean;
            });
        }
    };
});

/* validation
 * It allows number,plus,hypen with space function
 */
app.directive('mobileNumWithSpace', function() {
    return {
        require: '?ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
            if(!ngModelCtrl) {
                return;
            }

            ngModelCtrl.$parsers.push(function(val) {
                var clean = val.replace( /[^- ^+^0-9]+/g, '');
                if (val !== clean) {
                    ngModelCtrl.$setViewValue(clean);
                    ngModelCtrl.$render();
                }
                return clean;
            });
        }
    };
});
app.directive('numWithOutSpace', function() {
    return {
        require: '?ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
            if(!ngModelCtrl) {
                return;
            }

            ngModelCtrl.$parsers.push(function(val) {
                var clean = val.replace( /[^+0-9]+/g, '');
                if (val !== clean) {
                    ngModelCtrl.$setViewValue(clean);
                    ngModelCtrl.$render();
                }
                return clean;
            });
        }
    };
});
/* validation
 * It allows number,plus,hypen with space function
 */
app.directive('number', function() {
    return {
        require: '?ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
            if(!ngModelCtrl) {
                return;
            }

            ngModelCtrl.$parsers.push(function(val) {
                var clean = val.replace( /[^/.0-9]+/g, '');
                if (val !== clean) {
                    ngModelCtrl.$setViewValue(clean);
                    ngModelCtrl.$render();
                }
                return clean;
            });
        }
    };
});



/* for only Alpha with space function
 */
app.directive('alphaWithSpace', function() {
    return {
        require: '?ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
            if(!ngModelCtrl) {
                return;
            }

            ngModelCtrl.$parsers.push(function(val) {
                var clean = val.replace( /[^\s a-zA-Z]/g, '');
                if (val !== clean) {
                    ngModelCtrl.$setViewValue(clean);
                    ngModelCtrl.$render();
                }
                return clean;
            });
        }
    };
});

/* for only Alpha and number with space function
 */
app.directive('alphanumWithSpace', function() {
    return {
        require: '?ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
            if(!ngModelCtrl) {
                return;
            }

            ngModelCtrl.$parsers.push(function(val) {
                var clean = val.replace( /[^\sa-zA-Z0-9]/g, '');
                if (val !== clean) {
                    ngModelCtrl.$setViewValue(clean);
                    ngModelCtrl.$render();
                }
                return clean;
            });
        }
    };
});


/* for only Alpha and number without space function
 */
app.directive('alphanumWithoutSpace', function() {
    return {
        require: '?ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
            if(!ngModelCtrl) {
                return;
            }

            ngModelCtrl.$parsers.push(function(val) {
                var clean = val.replace( /[^a-zA-Z:0-9]/g, '');
                if (val !== clean) {
                    ngModelCtrl.$setViewValue(clean);
                    ngModelCtrl.$render();
                }
                return clean;
            });

            element.bind('keypress', function(event) {
                if(event.keyCode === 32) {
                    event.preventDefault();
                }
            });
        }
    };
});

/* for only two digit decimal Function
 */

app.directive('twoDigitsDec', function() {
    return {
        require: '?ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
            if(!ngModelCtrl) {
                return;
            }

            ngModelCtrl.$parsers.push(function(val) {
                if (angular.isUndefined(val)) {
                    var val = '';
                }

                var clean = val.replace(/[^-0-9\.]/g, '');
                var negativeCheck = clean.split('-');
                var decimalCheck = clean.split('.');
                if(!angular.isUndefined(negativeCheck[1])) {
                    negativeCheck[1] = negativeCheck[1].slice(0, negativeCheck[1].length);
                    clean =negativeCheck[0] + '-' + negativeCheck[1];
                    if(negativeCheck[0].length > 0) {
                        clean =negativeCheck[0];
                    }

                }

                if(!angular.isUndefined(decimalCheck[1])) {
                    decimalCheck[1] = decimalCheck[1].slice(0,2);
                    clean =decimalCheck[0] + '.' + decimalCheck[1];
                }

                if (val !== clean) {
                    ngModelCtrl.$setViewValue(clean);
                    ngModelCtrl.$render();
                }
                return clean;
            });

            element.bind('keypress', function(event) {
                if(event.keyCode === 32) {
                    event.preventDefault();
                }
            });
        }
    };
});

app.directive('noSpace', function() {
    return {
        require: '?ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
            element.bind('keypress', function(event) {
                if(event.keyCode === 32) {
                    event.preventDefault();
                }
            });
        }
    };
});

// Change text to uppercase and add dash every 5 char
app.directive('capitalizeWithDash', function() {
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {
            var capitalize = function(inputValue) {
                var number = 5;
                if (inputValue == undefined) inputValue = '';
                var inputUpper = inputValue.toUpperCase();
                var capitalizedArray = [];
                for(var i=0; i<inputUpper.length; i+= number){
                    if(inputUpper[i] == "-"){
                        capitalizedArray.push(inputUpper.substr(i+1,number))
                        i += 1;
                    }else {
                        capitalizedArray.push(inputUpper.substr(i, number))
                    }
                }
                var capitalized = capitalizedArray.join("-");
                if (capitalized !== inputValue) {
                    modelCtrl.$setViewValue(capitalized);
                    modelCtrl.$render();
                }
                return capitalized;
            }
            modelCtrl.$parsers.push(capitalize);
            capitalize(scope[attrs.ngModel]);
        }
    };
});



// $('.main_container').bind('keypress', function(e) {
//     var code = e.keyCode || e.which;
//     if(code == 13) { //Enter keycode
//         //Do something
//     }
// });




// angular.module("core").directive('hnEnterKey', function() {
//     return function(scope, element, attrs) {
//        ("keydown keypress", function(event) {
//
//             var code = e.keyCode || e.which;
//             if(code == 13) { //Enter keycode
//                 //Do something
//             }
//
//
//             var keyCode = event.which || event.keyCode;
//             if (keyCode === 13) {
//                 scope.$apply(function() {
//                     scope.$eval(attrs.hnEnterKey);
//                 });
//
//                 event.preventDefault();
//             }
//         });
//     };
// });


// app.config(['$httpProvider', function ($httpProvider) {
//     var $cookies;
//     angular.injector(['ngCookies']).invoke(['$cookies', function (_$cookies_) {
//         $cookies = _$cookies_;
//     }]);
//     $httpProvider.defaults.headers.common['Authorization'] = $cookies.get('accessToken');
// }]);


app.filter('setDecimal', function ($filter) {
    return function (input, places) {
        if (isNaN(input))
            return input;
        // If we want 1 decimal place, we want to mult/div by 10
        // If we want 2 decimal places, we want to mult/div by 100, etc
        // So use the following to create that factor
        var factor = "1" + Array(+(places > 0 && places + 1)).join("0");
        return Math.round(input * factor) / factor;
    };
});

app.filter('firstLetter', function () {
    return function (input, key, letter) {
        input = input || [];
        var out = [];
        input.forEach(function (item) {
            console.log('item: ', item[key][0].toLowerCase());
            console.log('letter: ', letter);
            if (item[key][0].toLowerCase() == letter.toLowerCase()) {
                out.push(item);
            }
        });
        return out;
    }
});

