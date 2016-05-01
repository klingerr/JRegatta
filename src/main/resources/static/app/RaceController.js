"use strict";

var raceCounter = 0;

angular
    .module('jregatta')
    .controller('RaceController', RaceController);

function RaceController($scope, RaceService, $mdToast) {
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
            field: 'number',
            enableCellEdit: true,
            type: 'number'
        }, {
            field: 'startTime',
            displayName: 'Start',
            enableCellEdit: true
        }, {
            field: 'endTime',
            displayName: 'Ende',
            enableCellEdit: true,
        }];

    $scope.gridOptions.data = 'races';
    $scope.races = RaceService.query();

    $scope.msg = {}; // Message Area for Debug Info

    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
            $scope.msg.lastCellEdited = 'Edited (#'
                + rowEntity.id + '), Column: ('
                + colDef.name + ') New Value: ('
                + newValue + ') Old Value: ('
                + oldValue + ")";
            RaceService.update({id: rowEntity.id}, rowEntity);
            $scope.$apply();
        });
    };

    $scope.newRace = function () {
        console.log("newRace()");
        RaceService.save(
            {number: ++raceCounter},
            function (savedRace, headers) {
                //success callback
                console.log("success: " + JSON.stringify(savedRace, null, 4));
                $scope.showSuccessToast('Neue Wettfahrt wurde hinzugefügt.');
                $scope.races.push(savedRace);
            },
            function (err) {
                // error callback
                console.log("error: " + JSON.stringify(err, null, 4));
                $scope.showErrorToast('Fehler: Es konnte keine neue Wettfahrt hinzugefügt werden!');
            });
    };

}
