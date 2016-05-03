"use strict";

var finishCounter = 0;

angular
    .module('jregatta')
    .controller('FinishController', FinishController);

function FinishController($scope, $routeParams, uiGridConstants, ResultService, $mdToast) {
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
            field: 'placement',
            displayName: 'Platz',
            enableCellEdit: true,
            sort: {
                direction: uiGridConstants.ASC,
                ignoreSort: true,
                priority: 0
            }
        }, {
            field: 'skipper.sailNumber',
            displayName: 'Segelnummer',
            enableCellEdit: true,
        }];

    $scope.gridOptions.data = 'finishs';
    $scope.finishs = ResultService.query({regattaId: $routeParams.regattaId}, {raceId: $routeParams.raceId});

    $scope.msg = {}; // Message Area for Debug Info

    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
            $scope.msg.lastCellEdited = 'Edited (#'
                + rowEntity.id + '), Column: ('
                + colDef.name + ') New Value: ('
                + newValue + ') Old Value: ('
                + oldValue + ")";
            FinishService.update({id: rowEntity.id}, rowEntity);
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
