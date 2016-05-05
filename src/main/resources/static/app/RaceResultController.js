"use strict";

var resultCounter = 0;

angular
    .module('jregatta')
    .controller('RaceResultController', RaceResultController);

function RaceResultController($scope, $routeParams, $location, uiGridConstants, RaceResultService, $mdToast) {

    $scope.showSuccessToast = function (message) {
        $mdToast.show($mdToast.simple()
            .content(message)
            .position('top right')
            .theme("success-toast"));
    };

    $scope.showErrorToast = function (message) {
        $mdToast.show($mdToast.simple()
            .content(message)
            .position('top right')
            .theme("error-toast"));
    };

    $scope.gridOptions = {
        enableFiltering: false,
        enableCellEditOnFocus: true,
        enablePaginationControls: false,
        enableSorting: true,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        enableColumnResizing: false,
        rowHeight: 45
    };

    $scope.gridOptions.columnDefs = [{
            field: 'skipper.ageGroup',
            displayName: 'Altersklasse',
            enableCellEdit: false,
            sort: {
                direction: uiGridConstants.ASC,
                ignoreSort: true,
                priority: 0
            }
        }, {
            field: 'skipper.sailNumber',
            displayName: 'Vorname',
            enableCellEdit: false
        }, {
            field: 'skipper.firstName',
            displayName: 'Vorname',
            enableCellEdit: false
        }, {
            field: 'skipper.lastName',
            displayName: 'Nachname',
            enableCellEdit: false
        }, {
            field: 'skipper.club.shortName',
            displayName: 'Verein',
            enableCellEdit: false
        }, {
            field: 'placement',
            displayName: 'Zieleinlauf',
            enableCellEdit: true,
            sort: {
                direction: uiGridConstants.ASC,
                ignoreSort: true,
                priority: 1
            }
        }, {
            field: 'points',
            displayName: 'Punkte',
            type: 'number',
            enableCellEdit: true
        }, {
            field: 'result',
            displayName: 'Platz',
            type: 'number',
            enableCellEdit: false
        }];

    $scope.gridOptions.data = 'raceResults';
    $scope.raceResults = RaceResultService.query({regattaId: $routeParams.regattaId}, {raceId: $routeParams.raceId});

    $scope.goCertificates = function() {
        $location.path("/regattas/" + $routeParams.regattaId + "/certificates");
        console.log("url: " + "/regattas/" + $routeParams.regattaId + "/certificates");
    };

    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
            console.log('Edited (#'
                + rowEntity.id + '), Column: ('
                + colDef.name + ') New Value: ('
                + newValue + ') Old Value: ('
                + oldValue + ")");
            RaceResultService.update({regattaId: $routeParams.regattaId, raceId: $routeParams.raceId, resultId: rowEntity.id}, rowEntity);
            $scope.$apply();
        });
    };

    $scope.showMoreColumns = function() {
        console.log("$scope.gridOptions.columnDefs.length: " + $scope.gridOptions.columnDefs.length);
    };
}
