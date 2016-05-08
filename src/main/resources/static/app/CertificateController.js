"use strict";

angular
    .module('jregatta')
    .controller('CertificateController', CertificateController);

function CertificateController($scope, $routeParams, ResultService) {

    $scope.certificates = ResultService.query({regattaId: $routeParams.regattaId});

    $scope.formatSailnumber = function(sailNumber){
      return sailNumber.replace(' ', '\n');
    };
}
