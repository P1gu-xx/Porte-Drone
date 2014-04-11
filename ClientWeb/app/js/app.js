'use strict';

// Declare app level module which depends on filters, and services
angular.module('myApp', [
  'ngRoute',
  'myApp.filters',
  'myApp.services',
  'myApp.directives',
  'myApp.controllers'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/lesVols', {templateUrl: 'partials/lesVols.html', controller: 'CtrlVols'});
  $routeProvider.otherwise({redirectTo: '/lesVols'});
}]);

