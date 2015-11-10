var cartApp=angular.module('cartApp',[]);

cartApp.controller('cartCtrl',function ($scope,$http){
	$scope.refreshCart=function(cartId){
		$http.get('/webstore/rest/cart/'+cartId).
			success(function(data){
				$scope.cart=data;
			});
	}
	$scope.clearCart=function(){
		$http.delete('/webstore/rest/cart/'+$scope.cartId).
			sucess(function(data){
				$scope.refreshCart($scope.cartId);
			});
	}
	$scope.initCart = function(cartId) {     
		$scope.cartId=cartId;     
		$scope.refreshCart($scope.cartId);   
	};
	$scope.addToCart = function(productId) {     
		$http.put('/webstore/rest/cart/add/'+productId).
			success(function(data) {     
				$http.get('/webstore/rest/cart/get/cartId').then(function(response){
					$scope.refreshCart(response.data);
				});
				alert("Product Successfully added to the Cart!");     
			});   
	};   
	$scope.removeFromCart = function(productId) {     
		$http.put('/webstore/rest/cart/remove/'+productId).
			success(function(data) {       
				$http.get('/webstore/rest/cart/get/cartId').then(function(response){
					$scope.refreshCart(response.data);
				});   
			});   
		};
});