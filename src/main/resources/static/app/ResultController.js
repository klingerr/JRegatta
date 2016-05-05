"use strict";

var resultCounter = 0;

angular
    .module('jregatta')
    .controller('ResultController', ResultController);

function ResultController($scope, $routeParams, $location, uiGridConstants, ResultService, $mdToast) {
    const GRID_DEFAULT_COLUMN_COUNT = 4;

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

    $scope.gridOptions.columnDefs = [
        {
            field: 'skipper.ageGroup',
            displayName: 'Altersklasse',
            enableCellEdit: false,
            sort: {
                direction: uiGridConstants.DESC,
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
            field: 'racePoints.1',
            displayName: '1. Wettfahrt',
            enableCellEdit: false
        }, {
            field: 'racePoints.2',
            displayName: '2. Wettfahrt',
            enableCellEdit: false
        }, {
            field: 'racePoints.3',
            displayName: '3. Wettfahrt',
            enableCellEdit: false
        }, {
            field: 'finalPoints',
            displayName: 'Gesamtpunktzahl',
            enableCellEdit: true,
            sort: {
                direction: uiGridConstants.ASC,
                ignoreSort: true,
                priority: 1
            }
        }, {
            field: 'finalPlacement',
            displayName: 'Platz',
            enableCellEdit: false
        }];

    $scope.gridOptions.data = 'results';
    $scope.results = ResultService.query({regattaId: $routeParams.regattaId});

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
            ResultService.update({id: rowEntity.id}, rowEntity);
            $scope.$apply();
        });
    };

    $scope.showMoreColumns = function() {
        console.log("$scope.gridOptions.columnDefs.length: " + $scope.gridOptions.columnDefs.length);

        if ($scope.gridOptions.columnDefs.length == GRID_DEFAULT_COLUMN_COUNT) {
            $scope.gridOptions.columnDefs.push({
                field: 'startDate',
                enableCellEdit: true,
                type: 'date',
                cellFilter: 'date:\'dd.MM.yyyy\''
            })
        } else {
            var deleteColumnCount = $scope.gridOptions.columnDefs.length - GRID_DEFAULT_COLUMN_COUNT;
            console.log("Trying to delete columns: " + deleteColumnCount);
            $scope.gridOptions.columnDefs.splice(GRID_DEFAULT_COLUMN_COUNT, deleteColumnCount);
        }
    }
}
