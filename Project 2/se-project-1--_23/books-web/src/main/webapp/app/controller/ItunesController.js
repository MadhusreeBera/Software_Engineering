"use strict";

App.controller("ItunesController", function ($scope, $http) {
  class DataAdapter {
    constructor(data) {
      this.data = data;
    }

    adapt() {
      return this.data.map((item) => ({
        trackId: item.trackId,
        artistName: item.artistName,
        collectionName: item.collectionName,
        trackName: item.trackName,
        collectionCensoredName: item.collectionCensoredName,
      }));
    }
  }

  class Strategy {
    search(searchString) {
      throw new Error("Method 'search' must be implemented.");
    }
  }
  class PodcastStrategy extends Strategy {
    search(searchString) {
      var baseUrl = "https://itunes.apple.com/search";
      var term = encodeURIComponent(searchString);
      var country = "US";
      var media = "podcast";
      var limit = 10;
      var url = `${baseUrl}?term=${term}&country=${country}&media=${media}&limit=${limit}`;
      $http
        .get(url)
        .then(function (response) {
          if (response.status !== 200) {
            alert("request failed with status " + response.status);
            throw new Error("Request failed with status " + response.status);
          }

          const adapter = new DataAdapter(response.data.results);
          const adaptedData = adapter.adapt();
          $scope.searchResults = createTableMarkup(adaptedData);
        })
        .catch(function (error) {
          alert("request failed");
          console.error("Error fetching data:", error);
        });
    }
  }

  class AudiobookStrategy extends Strategy {
    search(searchString) {
      var baseUrl = "https://itunes.apple.com/search";
      var term = encodeURIComponent(searchString);
      var country = "US";
      var media = "audiobook";
      var limit = 10;
      var url = `${baseUrl}?term=${term}&country=${country}&media=${media}&limit=${limit}`;
      $http
        .get(url)
        .then(function (response) {
          if (response.status !== 200) {
            alert("request failed with status " + response.status);
            throw new Error("Request failed with status " + response.status);
          }
          const adapter = new DataAdapter(response.data.results);
          const adaptedData = adapter.adapt();
          $scope.searchResults = createTableMarkup(adaptedData);
        })
        .catch(function (error) {
          alert("request failed");
          console.error("Error fetching data:", error);
        });
    }
  }

  function createTableMarkup(data) {
    var markup = "<table class='table'>";
    markup +=
      "<thead><tr><th>Track ID</th><th>Artist Name</th><th>Collection Name</th><th>Track Name</th><th>Collection Censored Name</th></tr></thead>";
    markup += "<tbody>";
    data.forEach(function (item) {
      markup += "<tr>";
      markup += "<td>" + item.trackId + "</td>";
      markup += "<td>" + item.artistName + "</td>";
      markup += "<td>" + item.collectionName + "</td>";
      markup += "<td>" + item.trackName + "</td>";
      markup += "<td>" + item.collectionCensoredName + "</td>";
      markup += "</tr>";
    });
    markup += "</tbody></table>";
    return markup;
  }

  $scope.searchQuery = function () {
    let strategy;
    if ($scope.searchType === "podcast") {
      strategy = new PodcastStrategy();
    } else if ($scope.searchType === "audiobook") {
      strategy = new AudiobookStrategy();
    }

    strategy.search(
      $scope.searchKeywords,
      // $scope.searchType,
      $http,
      function (adaptedData) {
        $scope.searchResults = createTableMarkup(adaptedData);
        $scope.$apply();
      }
    );
  };
});
