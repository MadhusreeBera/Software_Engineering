"use strict";

/**
 * Register controller.
 */
App.controller("Register", function ($scope, $rootScope, $state, User, Restangular) {
  $scope.register = function () {
    Restangular
      .one('user/register')
      .put($scope.user).then(function () {
      $rootScope.userInfo = User.userInfo(true);
      $state.transitionTo("login");
    }
    ,function(response) {
      alert(response.data.message);
    }
    );

  };
});
