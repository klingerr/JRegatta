"use strict";

var resultCounter = 0;

angular
    .module('jregatta')
    .controller('ResultController', ResultController);

function ResultController($scope, ResultService, $mdToast) {
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

    $scope.gridOptions.columnDefs = [{
            field: 'id',
//		cellTemplate : '<div><button class="btn btn-primary" xng-click="getExternalScopes().onClick(row.entity.fullName)">Click Here</button></div>',
            enableCellEdit: false
	    }, {
	        field: 'skipper.ageGroup',
	        displayName: 'Altersklasse',
	        enableCellEdit: false
        }, {
            field: 'placement',
            displayName: 'Platz',
            enableCellEdit: false
        }, {
            field: 'points',
            displayName: 'Punkte',
            enableCellEdit: false
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
        }];

    $scope.gridOptions.data = 'results';
    $scope.results = ResultService.query();

    $scope.msg = {}; // Message Area for Debug Info

    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
            $scope.msg.lastCellEdited = 'Edited (#'
                + rowEntity.id + '), Column: ('
                + colDef.name + ') New Value: ('
                + newValue + ') Old Value: ('
                + oldValue + ")";
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
