'use strict';

/**
 * Book controller.
 */
App.controller('Book', function($scope, $timeout, Restangular, $stateParams, $http, $location) {
  /**
   * View scope variables.
   */
   console.log($scope);
  $scope.sortColumn = 3;
  $scope.asc = true;
  $scope.offset = 0;
  $scope.limit = 20;
  $scope.search = {
    text: '',
    read: false
  };
  $scope.loading = false;
  $scope.books = [];
  $scope.total = -1;

  // A timeout promise is used to slow down search requests to the server
  // We keep track of it for cancellation purpose
  var timeoutPromise;

  /**
   * Reload books.
   */
  $scope.loadBooks = function() {
    $scope.offset = 0;
    $scope.total = -1;
    $scope.books = [];
    $scope.pageBooks();
  };

  $scope.top_10_books = function() {
    console.log($scope);
    console.log($http);
    console.log($stateParams);
    var url = '/books-web/api/library/top10'; // Replace with your actual API endpoint

    // Build query parameters based on selected criteria
    var params = {
      sort: $scope.selectedCriteria
    };
    var response=""
    $http.get(url, { params: params })
      .then(function(response) {
        $scope.top10Books = response.data;
        console.log(response);
        $scope.books = response.data.books;
        $scope.total = response.data.total;
        $scope.pageBooks();
      })
      .catch(function(error) {
        console.error('Error fetching top 10 books:', error);
        // Handle errors gracefully, e.g., display an error message to the user
      });
  };

  $scope.filterAuthor = function() {
      console.log($scope);
      console.log($http);
      console.log($stateParams);
      var url = '/books-web/api/library/filter'; // Replace with your actual API endpoint

      // Build query parameters based on selected criteria
      var params = {
        selectedCriteria: 1,
        listCriteria: $scope.author.text
      };
      var response=""
      $http.get(url, { params: params })
        .then(function(response) {
          $scope.filterAuthor = response.data;
          console.log(response);
          $scope.books = response.data.books;
          $scope.total = response.data.total;
          $scope.pageBooks();
        })
        .catch(function(error) {
          console.error('Error fetching filter Author books:', error);
          // Handle errors gracefully, e.g., display an error message to the user
        });
    };

    $scope.filterGenre = function() {
          console.log($scope);
          console.log($http);
          console.log($stateParams);
          var url = '/books-web/api/library/filter'; // Replace with your actual API endpoint

          // Build query parameters based on selected criteria
          var params = {
            selectedCriteria: 2,
            listCriteria: $scope.genre.text
          };
          var response=""
          $http.get(url, { params: params })
            .then(function(response) {
              $scope.filterGenre = response.data;
              console.log(response);
              $scope.books = response.data.books;
              $scope.total = response.data.total;
              $scope.pageBooks();
            })
            .catch(function(error) {
              console.error('Error fetching filtered Genre books:', error);
              // Handle errors gracefully, e.g., display an error message to the user
            });
        };

    $scope.filterRating = function() {
          console.log($scope);
          console.log($http);
          console.log($stateParams);
          var url = '/books-web/api/library/filter'; // Replace with your actual API endpoint

          // Build query parameters based on selected criteria
          var params = {
            selectedCriteria: 3,
            listCriteria: $scope.rating.text
          };
          var response=""
          $http.get(url, { params: params })
            .then(function(response) {
              $scope.filterRating = response.data;
              console.log(response);
              $scope.books = response.data.books;
              $scope.total = response.data.total;
              $scope.pageBooks();
            })
            .catch(function(error) {
              console.error('Error fetching filtered Rating books:', error);
              // Handle errors gracefully, e.g., display an error message to the user
            });
        };


  /**
   * Load books.
   */
  $scope.pageBooks = function(next) {
    if ($scope.loading || $scope.total == $scope.books.length) {
      // Avoid spamming the server
      return;
    }

    if (next) {
      $scope.offset += $scope.limit;
    }

    $scope.loading = true;
    Restangular.one('book/list').get({
      offset: $scope.offset,
      limit: $scope.limit,
      sort_column: $scope.sortColumn,
      asc: $scope.asc,
      search: $scope.search.text,
      read: $scope.search.read ? true : null,
      tag: $stateParams.tag
    }).then(function(data) {
          $scope.books = $scope.books.concat(data.books);
          $scope.total = data.total;
          $scope.loading = false;
        });
  };

  /**
   * Watch for search scope change.
   */
  $scope.$watch('search', function() {
    if (timeoutPromise) {
      // Cancel previous timeout
      $timeout.cancel(timeoutPromise);
    }

    // Call API later
    timeoutPromise = $timeout(function () {
      $scope.loadBooks();
    }, 200);
  }, true);

  // Load tags
  Restangular.one('tag/list').get().then(function(data) {
    $scope.tags = data.tags;
  });
});