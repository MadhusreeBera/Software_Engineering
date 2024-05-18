"use strict";

App.controller("SpotifyController", function ($scope, $http) {
  // Base Strategy class

  class SpotifyDataAdapter {
    constructor(data) {
      this.data = data;
    }
    adapt() {
      return data.map(function (item) {
        return {
          trackId: item.id,
          artistName: item.authors[0]?.name, // Using the first author's name; the '?' ensures that this doesn't throw an error if authors array is empty
          collectionName: item.name,
          trackName: item.name, // Assuming you want to use the audiobook's name as the trackName as well
        };
      });
    }
  }

  class Strategy {
    search(searchString) {
      throw new Error("Method 'search' must be implemented.");
    }
  }

  // PodcastStrategy subclass
  class PodcastStrategy extends Strategy {
    search(searchString) {
      var apiUrl = "https://api.spotify.com/v1/search";
      $http({
        method: "GET",
        url: apiUrl,
        headers: {
          Authorization: "Bearer " + accessToken,
        },
        params: {
          q: searchString,
          type: "show",
          market: "US",
          limit: 10,
        },
      })
        .then(function (response) {
          if (response.status === 200) {
            alert(JSON.stringify(response.data.shows.href));
            // alert(response.data);
            // var adaptedData = spotifyAdapter(response.data.shows.items);
            // alert(adaptedData);
            // $scope.searchResults = createTableMarkup(adaptedData);
          } else {
            console.error("Request failed with status: ", response.status);
          }
        })
        .catch(function (error) {
          console.error("Error fetching podcasts: ", error);
        });
    }
  }

  class AudiobookStrategy extends Strategy {
    search(searchString) {
      var apiUrl = "https://api.spotify.com/v1/search";
      $http({
        method: "GET",
        url: apiUrl,
        headers: {
          Authorization: "Bearer " + accessToken,
        },
        params: {
          q: searchString,
          type: "audiobook",
          market: "US",
          limit: 10,
        },
      })
        .then(function (response) {
          if (response.status === 200) {
            // alert(JSON.stringify(response.data.audiobooks.items));
            var adaptedData = spotifyAdapter(response.data.audiobooks.items);
            // alert(adaptedData);
            // const adapter = new DataAdapter(response.data.audiobooks.items);
            // const adaptedData = adapter.adapt();
            $scope.searchResults = createTableMarkup(adaptedData);
          } else {
            console.error("Request failed with status: ", response.status);
          }
        })
        .catch(function (error) {
          console.error("Error fetching audiobooks: ", error);
        });
    }
  }

  var clientId = "56a707f6c6604afcafb8f6840f88d9d6";
  var clientSecret = "bf7c2f1fa387489f8ffaaad7479c35f1";
  var accessToken = null;

  // Utility function to create HTML table markup from data
  function createTableMarkup(data) {
    var markup =
      "<table class='table'><thead><tr><th>Track ID</th><th>Artist Name</th><th>Collection Name</th><th>Track Name</th></tr></thead><tbody>";
    data.forEach(function (item) {
      markup += `<tr><td>${item.trackId}</td><td>${item.artistName}</td><td>${item.collectionName}</td><td>${item.trackName}</td></tr>`;
    });
    markup += "</tbody></table>";
    return markup;
  }

  // Adapter function to adapt Spotify API data to the needed format
  function spotifyAdapter(data) {
    return data.map(function (item) {
      return {
        trackId: item.id,
        artistName: item.authors[0]?.name, // Using the first author's name; the '?' ensures that this doesn't throw an error if authors array is empty
        collectionName: item.name,
        trackName: item.name, // Assuming you want to use the audiobook's name as the trackName as well
      };
    });
  }

  // Function to encode client ID and secret for Spotify API authentication
  function getEncodedCredentials() {
    return btoa(clientId + ":" + clientSecret);
  }

  // Function to retrieve an access token from the Spotify API
  function getAccessToken() {
    $http({
      method: "POST",
      url: "https://accounts.spotify.com/api/token",
      headers: {
        Authorization: "Basic " + getEncodedCredentials(),
        "Content-Type": "application/x-www-form-urlencoded",
      },
      data: "grant_type=client_credentials",
    })
      .then(function (response) {
        if (response.status === 200) {
          accessToken = response.data.access_token;
        } else {
          console.error("Error retrieving access token: ", response.status);
        }
      })
      .catch(function (error) {
        console.error("Error fetching access token: ", error);
      });
  }

  $scope.searchQuery = function () {
    let strategy;
    if ($scope.searchType === "podcast") {
      strategy = new PodcastStrategy();
    } else if ($scope.searchType === "audiobook") {
      strategy = new AudiobookStrategy();
    } else {
      console.error("Unsupported search type:", $scope.searchType);
      return;
    }

    strategy
      .search($scope.searchKeywords)
      .then(function (adaptedData) {
        $scope.searchResults = createTableMarkup(adaptedData);
        // $scope.$apply(); // Not needed if you're already in an Angular context
      })
      .catch(function (error) {
        console.error("Error during search:", error);
      });
  };
  getAccessToken();
});
