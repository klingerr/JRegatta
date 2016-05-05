"use strict";

var clubCounter = 0;

angular
    .module('jregatta')
    .controller('ClubController', ClubController);

function ClubController($scope, ClubService, uiGridConstants, $mdToast) {
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
            field: 'shortName',
            displayName: 'Kurzname',
            enableCellEdit: true,
            sort: {
                direction: uiGridConstants.ASC,
                ignoreSort: true,
                priority: 0
            }
        }, {
            field: 'name',
            enableCellEdit: true
        }, {
            field: 'address',
            displayName: 'Adresse',
            enableCellEdit: true
        }];

    $scope.gridOptions.data = 'clubs';
    $scope.clubs = ClubService.query();

    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
            console.log('Edited (#'
                + rowEntity.id + '), Column: ('
                + colDef.name + ') New Value: ('
                + newValue + ') Old Value: ('
                + oldValue + ")");
            ClubService.update({id: rowEntity.id}, rowEntity);
            $scope.$apply();
        });
    };

    $scope.newClub = function () {
        console.log("newClub()");
        ClubService.save(
            {name: "Club-" + ++clubCounter, shortName: "R" + clubCounter},
            function (savedClub, headers) {
                //success callback
                console.log("success: " + JSON.stringify(savedClub, null, 4));
                $scope.showSuccessToast('Neuer Verein wurde hinzugefügt.');
                $scope.clubs.push(savedClub);
            },
            function (err) {
                // error callback
                console.log("error: " + JSON.stringify(err, null, 4));
                $scope.showErrorToast('Fehler: Es konnte kein neuer Verein hinzugefügt werden!');
            });
    };

}
