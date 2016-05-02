"use strict";

var skipperCounter = 0;

angular
    .module('jregatta')
    .controller('SkipperController', SkipperController);

function SkipperController($scope, $routeParams, SkipperService, $mdToast) {
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
	        field: 'firstName',
	        displayName: 'Vorname',
	        enableCellEdit: true
        }, {
            field: 'lastName',
            displayName: 'Nachname',
            enableCellEdit: true
        }, {
            field: 'birthday',
            displayName: 'Geburtstag',
            enableCellEdit: true,
            type: 'date'
        }];

    $scope.gridOptions.data = 'skipper';
    $scope.skipper = SkipperService.query($routeParams.regattaId);

    $scope.msg = {}; // Message Area for Debug Info

    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
            $scope.msg.lastCellEdited = 'Edited (#'
                + rowEntity.id + '), Column: ('
                + colDef.name + ') New Value: ('
                + newValue + ') Old Value: ('
                + oldValue + ")";
            SkipperService.update({id: rowEntity.id}, rowEntity);
            $scope.$apply();
        });
    };

    $scope.newSkipper = function () {
        console.log("newSkipper()");
        SkipperService.save(
            {lastName: "N-" + ++skipperCounter, firstName: "V-" + skipperCounter},
            function (savedSkipper, headers) {
                //success callback
                console.log("success: " + JSON.stringify(savedSkipper, null, 4));
                $scope.showSuccessToast('Neuer Teilnehmer wurde hinzugefügt.');
                $scope.skipper.push(savedSkipper);
            },
            function (err) {
                // error callback
                console.log("error: " + JSON.stringify(err, null, 4));
                $scope.showErrorToast('Fehler: Es konnte kein neuer Teilnehmer hinzugefügt werden!');
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
