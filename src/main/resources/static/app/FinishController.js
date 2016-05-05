"use strict";

var finishCounter = 0;

angular
    .module('jregatta')
    .controller('FinishController', FinishController);

function FinishController($q, $scope, $routeParams, uiGridConstants, RaceResultService, SkipperService, $mdToast) {

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

// get dropdown content before creating the grid
    $scope.skippers = SkipperService.query({regattaId: $routeParams.regattaId});
    $q.all([
        $scope.skippers.$promise
    ]).then(function () {
        //CODE AFTER RESOURCES ARE LOADED 
        console.log("$scope.skippers: " + JSON.stringify($scope.skippers, null, 4));
    });


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
            editableCellTemplate: 'ui-grid/dropdownEditor',
            editDropdownIdLabel: 'sailNumber',
            editDropdownValueLabel: 'sailNumber',
            editDropdownOptionsArray: $scope.skippers,
            cellFilter: 'griddropdown:this',
        }];

    $scope.gridOptions.data = 'finishs';
    $scope.finishs = RaceResultService.query({regattaId: $routeParams.regattaId, raceId: $routeParams.raceId}, {raceId: $routeParams.raceId});

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

    $scope.newFinish = function () {
        console.log("newFinish(): " + $routeParams.regattaId);
        RaceResultService.save(
            {placement: "" + ++finishCounter,
            regattaId: $routeParams.regattaId,
            raceId: $routeParams.raceId, 
            race: {id: $routeParams.raceId}},
            function (savedFinish, headers) {
                //success callback
                console.log("success: " + JSON.stringify(savedFinish, null, 4));
                $scope.showSuccessToast('Neuer Zieleinlauf wurde hinzugefügt.');
                $scope.finishs.push(savedFinish);
            },
            function (err) {
                // error callback
                console.log("error: " + JSON.stringify(err, null, 4));
                $scope.showErrorToast('Fehler: Es konnte kein neuer Zieleinlauf hinzugefügt werden!');
            });
    };


    $scope.toggleOfficially = function() {
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
