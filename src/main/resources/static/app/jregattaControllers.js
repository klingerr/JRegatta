var jregattaControllers = angular.module('jregattaControllers', []);

jregattaControllers.controller('RegattaListController', [ '$scope', 'Regatta', function($scope, Regatta) {
	$scope.gridOptions = {
		enableFiltering : false,
		enableCellEditOnFocus : true,
		enablePaginationControls : false,
		enableSorting : true,
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		enableColumnResizing : true,
		rowHeight: 45,
	};

	$scope.gridOptions.columnDefs = [ {
		field : 'id',
		cellTemplate : '<div><button class="btn btn-primary" xng-click="getExternalScopes().onClick(row.entity.fullName)">Click Here</button></div>'
	}, {
		field : 'name',
		enableCellEdit : true
	}, {
		field : 'shortName',
		displayName : 'Kurzname',
		enableCellEdit : true
	}, {
		field : 'buoyages',
		displayName : 'Bojen',
		enableCellEdit : true,
		type : 'number'
	} ];

	// $scope.gridOptions.enableCellEditOnFocus = true;
	$scope.gridOptions.data = 'regattas';
	$scope.regattas = Regatta.query();
} ]);
