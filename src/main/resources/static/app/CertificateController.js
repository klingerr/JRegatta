"use strict";

angular
    .module('jregatta')
    .controller('CertificateController', CertificateController);

function CertificateController($scope, ResultService) {

    $scope.certificates = ResultService.query();

}
