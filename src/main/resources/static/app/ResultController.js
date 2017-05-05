"use strict";

var resultCounter = 0;

angular
    .module('jregatta')
    .controller('ResultController', ResultController);

function ResultController($q, $scope, $routeParams, $location, uiGridConstants, ResultService, RegattaService, $mdToast) {

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
    $scope.regatta = RegattaService.get({id: $routeParams.regattaId});
    $q.all([
        $scope.regatta.$promise
    ]).then(function () {
        //CODE AFTER RESOURCES ARE LOADED 
        $scope.gridOptions.exporterPdfHeader = {text: $scope.regatta.name + ". - Gesamtergebnis", style: 'headerStyle', alignment: 'center'};
        console.log("$scope.regatta: " + JSON.stringify($scope.regatta, null, 4));
        console.log("$scope.regatta.buoyages: " + $scope.regatta.buoyages);
        console.log("$scope.regatta.buoyages >= 4: " + (Number($scope.regatta.buoyages) >= 4));
        
        // show race 3, 4 and 5 dynamically
        $scope.gridOptions.columnDefs[7].visible = (Number($scope.regatta.buoyages) >= 3);
        $scope.gridOptions.columnDefs[8].visible = (Number($scope.regatta.buoyages) >= 4);
        $scope.gridOptions.columnDefs[9].visible = (Number($scope.regatta.buoyages) >= 5);
    });

    $scope.setHeaderText = function(isOffiziell) {
        if (isOffiziell) {
            $scope.gridOptions.exporterPdfHeader = {text: $scope.regatta.name + ". - Gesamtergebnis", style: 'headerStyle', alignment: 'center'};
        } else {
            $scope.gridOptions.exporterPdfHeader = {text: $scope.regatta.name + ". - Gesamtergebnis (vorläufig)", style: 'headerStyle', alignment: 'center'};
        }
    };

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
        exporterCsvFilename: 'gesamtergebnis.csv',
        exporterMenuPdf: true,
        exporterMenuCsv: true,
        exporterMenuAllData: false,
        expandableRowHeaderWidth: 60,
        exporterPdfDefaultStyle: {fontSize: 9},
        exporterPdfTableStyle: {margin: [0, 30, 0, 0]},
        exporterPdfTableHeaderStyle: {fontSize: 10, bold: true, italics: true, color: 'red'},
        exporterPdfHeader: {text: $scope.regatta.name + " - Gesamtergebnis", style: 'headerStyle', alignment: 'center'},
        exporterPdfFooter: function (currentPage, pageCount) {
//            return {text: currentPage.toString() + ' of ' + pageCount.toString(), style: 'footerStyle'};
            return {text: 'Org.Büro: _____________________      Wettfahrtleiter: _____________________      Schiedsrichter: _____________________\r\n\r\n' 
                        + $scope.regatta.name + ' - ' +  moment(new Date()).format('DD.MM.YYYY HH:mm'), style: 'footerStyle'};
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
        }
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
            enableCellEdit: false,
            type: 'number'
        }, {
            field: 'racePoints.1',
            displayName: '1. Wettfahrt',
            enableCellEdit: false,
            type: 'number'
        }, {
            field: 'racePoints.2',
            displayName: '2. Wettfahrt',
            enableCellEdit: false,
            type: 'number'
        }, {
            field: 'racePoints.3',
            displayName: '3. Wettfahrt',
            enableCellEdit: false,
            type: 'number'
        }, {
        	field: 'racePoints.4',
        	displayName: '4. Wettfahrt',
        	enableCellEdit: false,
        	type: 'number'
        }, {
        	field: 'racePoints.5',
        	displayName: '5. Wettfahrt',
        	enableCellEdit: false,
        	type: 'number'
        }, {
            field: 'finalPoints',
            displayName: 'Gesamtpunkte',
            enableCellEdit: true,
            type: 'number'
        }, {
        	field: 'finalPlacement',
        	displayName: 'Platz',
        	enableCellEdit: false,
        	type: 'number',
            sort: {
                direction: uiGridConstants.ASC,
                ignoreSort: true,
                priority: 1
            }
        }, {
            field: 'placementSortCriteria',
            displayName: 'Sortierung',
            enableCellEdit: false,
            type: 'number',
            visible: false
        }];

    $scope.gridOptions.data = 'results';
    $scope.results = ResultService.query({regattaId: $routeParams.regattaId});

    $scope.goCertificates = function() {
        $location.path("/regattas/" + $routeParams.regattaId + "/certificates");
        console.log("url: " + "/regattas/" + $routeParams.regattaId + "/certificates");
    };

    $scope.goRaces = function() {
    	$location.path("/regattas/" + $routeParams.regattaId + "/races");
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

    $scope.toggleOfficially = function(data) {
        $scope.setHeaderText(data);
    };
}
