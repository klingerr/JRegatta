"use strict";

angular
    .module('jregatta')
    .controller('SkipperController', SkipperController);

function SkipperController($q, $scope, $routeParams, $location, moment, uiGridConstants, SkipperService, ClubService, RegattaService, $mdToast) {

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
    
    $scope.setHeaderText = function(isMeldeliste) {
        if (isMeldeliste) {
            $scope.gridOptions.exporterPdfHeader = {text: $scope.regatta.name + " - Meldeliste", style: 'headerStyle', alignment: 'center'};
        } else {
            $scope.gridOptions.exporterPdfHeader = {text: $scope.regatta.name + " - Starterliste", style: 'headerStyle', alignment: 'center'};
        }
    };

// get dropdown content before creating the grid
    $scope.clubs = ClubService.query();
    $scope.regatta = RegattaService.get({id: $routeParams.regattaId});
    $q.all([
        $scope.clubs.$promise,
        $scope.regatta.$promise
    ]).then(function () {
        //CODE AFTER RESOURCES ARE LOADED 
        $scope.gridOptions.exporterPdfHeader = {text: $scope.regatta.name + " - Starterliste", style: 'headerStyle', alignment: 'center'};
        console.log("$scope.clubs: " + JSON.stringify($scope.clubs, null, 4));
        console.log("$scope.regatta: " + JSON.stringify($scope.regatta, null, 4));
    });
    
    $scope.gender = [{id: 'M', gender: 'männlich'}, 
                     {id: 'W', gender: 'weiblich'}]

    $scope.gridOptions = {
        enableFiltering: false,
        enableCellEditOnFocus: true,
        enablePaginationControls: false,
        enableSorting: true,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        enableColumnResizing: false,
        rowHeight: 45,
        enableGridMenu: true,
        enableSelectAll: true,
        exporterCsvFilename: 'teilnehmer.csv',
        exporterMenuPdf: true,
        exporterMenuCsv: false,
        exporterMenuAllData: false,
        expandableRowHeaderWidth: 60,
        exporterPdfDefaultStyle: {fontSize: 9},
        exporterPdfTableStyle: {margin: [0, 60, 0, 0]},
        exporterPdfTableHeaderStyle: {fontSize: 10, bold: true, italics: true, color: 'red'},
        exporterPdfHeader: {text: $scope.regatta.name + " - Starterliste", style: 'headerStyle', alignment: 'center'},
        exporterPdfFooter: function (currentPage, pageCount) {
//            return {text: currentPage.toString() + ' of ' + pageCount.toString(), style: 'footerStyle'};
            return {text: 'Org.Büro: _____________________      Wettfahrtleiter: _____________________      Schiedsrichter: _____________________      \r\n\r\n' 
                       +  moment(new Date()).format('DD.MM.YYYY HH:mm'), style: 'footerStyle'};
        },
        exporterPdfCustomFormatter: function (docDefinition) {
            docDefinition.styles.headerStyle = {fontSize: 22, bold: true, margin: [0, 14, 0, 0]};
            docDefinition.styles.footerStyle = {fontSize: 10, bold: true, alignment: 'center'};
            return docDefinition;
        },
        exporterPdfOrientation: 'landscape',
        exporterPdfPageSize: 'A4',
        exporterPdfMaxGridWidth: 675,
        exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),
        onRegisterApi: function (gridApi) {
            $scope.gridApi = gridApi;
        },
        exporterFieldCallback: function (grid, row, col, input) {

            if (col.name == 'birthDay') {
                return moment(input).format('DD.MM.YYYY');
            }

            if (col.name == 'gender') {
                switch (input) {
                    case "M":
                        return 'männlich';
                        break;
                    case "W":
                        return 'weiblich';
                        break;
                    default:
                        return 'unbekannt';
                        break;
                }
            }

            if (col.name === 'entryFee' || col.name === 'lateRegistration' || col.name === 'catering' || col.name === 'lunch') {
                switch (input) {
                    case true:
                        return 'Ja';
                        break;
                    case false:
                        return 'Nein';
                        break;
                    default:
                        return 'unbekannt';
                        break;
                }
            } else {
                return input;
            }
        }
    };

    $scope.gridOptions.columnDefs = [{
            field: 'club.shortName',
            displayName: 'Verein',
            enableCellEdit: true,
            editableCellTemplate: 'ui-grid/dropdownEditor',
            editDropdownIdLabel: 'shortName',
            editDropdownValueLabel: 'shortName',
            editDropdownOptionsArray: $scope.clubs,
            cellFilter: 'griddropdown:this',
            sortCellFiltered: true,
            sort: {
                direction: uiGridConstants.ASC,
                ignoreSort: true,
                priority: 0
            }
        }, {
        	field: 'lastName',
        	displayName: 'Nachname',
        	enableCellEdit: true,
        	sort: {
        		direction: uiGridConstants.ASC,
        		ignoreSort: true,
        		priority: 1
        	}
        }, {
            field: 'firstName',
            displayName: 'Vorname',
            enableCellEdit: true,
            sort: {
                direction: uiGridConstants.ASC,
                ignoreSort: true,
                priority: 2
            }
        }, {
        	field: 'sailNumber',
        	displayName: 'Segelnummer',
        	enableCellEdit: true
        }, {
            name: 'gender',
            displayName: 'Geschlecht',
            editableCellTemplate: 'ui-grid/dropdownEditor',
            editDropdownIdLabel: 'id',
            editDropdownValueLabel: 'gender',
            cellFilter: 'griddropdown:this',
            sortCellFiltered: true,
            editDropdownOptionsArray: $scope.gender
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
        }, {
            field: 'lateRegistration',
            displayName: 'Nachmeldung',
            enableCellEdit: true,
            type: 'boolean',
            visible: false,
//            cellTemplate: '<input type="checkbox" ng-model="row.entity.lateRegistration">'
            cellTemplate: "<div class='ui-grid-cell-contents ng-binding ng-scope' ng-bind='grid.appScope.mapLateRegistration(row)'></div>"
        }, {
            field: 'entryFee',
            displayName: 'Startgeld',
            enableCellEdit: true,
            type: 'boolean',
            visible: false,
//            cellTemplate: '<input type="checkbox" ng-model="row.entity.entryFee">'
            cellTemplate: "<div class='ui-grid-cell-contents ng-binding ng-scope' ng-bind='grid.appScope.mapEntryFee(row)'></div>"
        }, {
            field: 'catering',
            displayName: 'Kaltverpflegung',
            enableCellEdit: true,
            type: 'boolean',
            visible: false,
//            cellTemplate: '<input type="checkbox" ng-model="row.entity.catering" ng-click="grid.appScope.updateRow(row);" ng-true-value="\'TRUE\'" ng-false-value="\'FALSE\'">'
            cellTemplate: "<div class='ui-grid-cell-contents ng-binding ng-scope' ng-bind='grid.appScope.mapCatering(row)'></div>"
        }, {
            field: 'lunch',
            displayName: 'Mittag',
            enableCellEdit: true,
            type: 'boolean',
            visible: false,
//            cellTemplate: '<input type="checkbox" ng-model="row.entity.lunch">'
            cellTemplate: "<div class='ui-grid-cell-contents ng-binding ng-scope' ng-bind='grid.appScope.mapLunch(row)'></div>"
        }];

    $scope.gridOptions.data = 'skipper';
    $scope.skipper = SkipperService.query({regattaId: $routeParams.regattaId});
    console.log("$routeParams.regattaId: " + $routeParams.regattaId);

    // translate Booleans
    $scope.mapLateRegistration = function(row) {
      return row.entity.lateRegistration ? 'Ja' : 'Nein';
    };
    $scope.mapEntryFee = function(row) {
      return row.entity.entryFee ? 'Ja' : 'Nein';
    };
    $scope.mapCatering = function(row) {
      return row.entity.catering ? 'Ja' : 'Nein';
    };
    $scope.mapLunch = function(row) {
      return row.entity.lunch ? 'Ja' : 'Nein';
    };


    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
            console.log('Edited (#'
                + rowEntity.id + '), Column: ('
                + colDef.name + ') New Value: ('
                + newValue + ') Old Value: ('
                + oldValue + ")");
                console.log("skipperId: " + rowEntity.id + " regattaId: " + $routeParams.regattaId);
                SkipperService.update({skipperId: rowEntity.id, regattaId: $routeParams.regattaId}, rowEntity);
            $scope.$apply();
        });
    };

    $scope.goClubs = function() {
        $location.path("/clubs");
    };

    $scope.goRaces = function() {
    	$location.path("/regattas/" + $routeParams.regattaId + "/races");
    };
    
    $scope.newSkipper = function () {
        console.log("newSkipper(): " + $routeParams.regattaId);
        SkipperService.save(
            {lastName: "N-" + ($scope.gridApi.core.getVisibleRows().length + 1),
                firstName: "V-" + ($scope.gridApi.core.getVisibleRows().length + 1),
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


    var isRegistrationVisible = false;
        
    $scope.toggleRegistration = function(data) {
        console.log("data.cb6: " + JSON.stringify(data, null, 4));
        $scope.setHeaderText(data);
        
        if (isRegistrationVisible) {
            isRegistrationVisible = false;
        } else {
            isRegistrationVisible = true;            
        }
        $scope.gridOptions.columnDefs[7].visible = isRegistrationVisible;
        $scope.gridOptions.columnDefs[8].visible = isRegistrationVisible;
        $scope.gridOptions.columnDefs[9].visible = isRegistrationVisible;
        $scope.gridOptions.columnDefs[10].visible = isRegistrationVisible;
        $scope.gridApi.core.refresh();
    };
    
}
