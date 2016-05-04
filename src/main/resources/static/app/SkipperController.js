"use strict";

var skipperCounter = 0;

angular
    .module('jregatta')
    .controller('SkipperController', SkipperController)
    .filter('mapGender', function () {
        var genderHash = {
            'M': 'männlich',
            'W': 'weiblich'
        };

        return function (input) {
            if (!input) {
                return '';
            } else {
                return genderHash[input];
            }
        };
    });

function SkipperController($q, $scope, $routeParams, $location, uiGridConstants, SkipperService, ClubService, $mdToast) {
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
            field: 'club.name',
            displayName: 'Verein',
            enableCellEdit: true,
            editableCellTemplate: 'ui-grid/dropdownEditor',
//            cellFilter: 'mapGender',
            editDropdownValueLabel: 'name', 
            editDropdownOptionsArray: [{id:1, name:'Test'}, {id:1, name:'Bla'}],
            sort: {
                direction: uiGridConstants.ASC,
                ignoreSort: true,
                priority: 0
            }
        }, {
            field: 'sailNumber',
            displayName: 'Segelnummer',
            enableCellEdit: false,
            sort: {
                direction: uiGridConstants.ASC,
                ignoreSort: true,
                priority: 1
            }
        }, {
            field: 'firstName',
            displayName: 'Vorname',
            enableCellEdit: true
        }, {
            field: 'lastName',
            displayName: 'Nachname',
            enableCellEdit: true
        }, {
            name: 'gender',
            displayName: 'Geschlecht',
            editableCellTemplate: 'ui-grid/dropdownEditor',
//            cellFilter: 'mapGender',
//            editDropdownValueLabel: 'gender',
            editDropdownOptionsArray: [{
                    id: 'M',
                    gender: 'Male'
                }, {
                    id: 'W',
                    gender: 'Female'
                }]
        }, {
            field: 'birthDay',
            displayName: 'Geburtstag',
            enableCellEdit: true,
            type: 'date',
            cellFilter: 'date:\'dd.MM.yyyy\''
        }, {
            field: 'ageGroup',
            displayName: 'Altersgruppe',
            enableCellEdit: false
        }];

    $scope.gridOptions.data = 'skipper';
    $scope.skipper = SkipperService.query({regattaId: $routeParams.regattaId});
//    $scope.clubs = ClubService.query();
//    $scope.clubs = [{id:1, name:'Test'}, {id:1, name:'Bla'}];
    console.log("$routeParams.regattaId: " + $routeParams.regattaId);

//    $q.all([
//        $scope.clubs.$promise
//    ]).then(function () {
//        //CODE AFTER RESOURCES ARE LOADED 
//        console.log("$scope.clubs: " + JSON.stringify($scope.clubs, null, 4));
//    });
    console.log("$scope.skipper: " + JSON.stringify($scope.skipper, null, 4));

    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
            console.log('Edited (#'
                + rowEntity.id + '), Column: ('
                + colDef.name + ') New Value: ('
                + newValue + ') Old Value: ('
                + oldValue + ")");
            SkipperService.update({skipperId: rowEntity.id, regattaId: $routeParams.regattaId}, rowEntity);
            $scope.$apply();
        });
    };

    $scope.goClubs = function() {
        $location.path("/clubs");
        console.log("url: " + "/clubs");
    }

    $scope.newSkipper = function () {
        console.log("newSkipper(): " + $routeParams.regattaId);
        SkipperService.save(
            {lastName: "N-" + ++skipperCounter,
                firstName: "V-" + skipperCounter,
                regattaId: $routeParams.regattaId,
                regatta: {id: $routeParams.regattaId}},
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
