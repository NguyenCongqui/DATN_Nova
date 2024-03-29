﻿function init() {
  var e = {
      zoom: 15,
      scrollwheel: !1,
      center: new google.maps.LatLng(21.17024, 72.831061),
      styles: [
        {
          featureType: "administrative",
          elementType: "all",
          stylers: [{ saturation: "-100" }],
        },
        {
          featureType: "administrative.province",
          elementType: "all",
          stylers: [{ visibility: "off" }],
        },
        {
          featureType: "landscape",
          elementType: "all",
          stylers: [
            { saturation: -100 },
            { lightness: 65 },
            { visibility: "on" },
          ],
        },
        {
          featureType: "poi",
          elementType: "all",
          stylers: [
            { saturation: -100 },
            { lightness: "50" },
            { visibility: "simplified" },
          ],
        },
        {
          featureType: "road",
          elementType: "all",
          stylers: [{ saturation: "-100" }],
        },
        {
          featureType: "road.highway",
          elementType: "all",
          stylers: [{ visibility: "simplified" }],
        },
        {
          featureType: "road.arterial",
          elementType: "all",
          stylers: [{ lightness: "30" }],
        },
        {
          featureType: "road.local",
          elementType: "all",
          stylers: [{ lightness: "40" }],
        },
        {
          featureType: "transit",
          elementType: "all",
          stylers: [{ saturation: -100 }, { visibility: "simplified" }],
        },
        {
          featureType: "water",
          elementType: "geometry",
          stylers: [
            { hue: "#ffff00" },
            { lightness: -25 },
            { saturation: -97 },
          ],
        },
        {
          featureType: "water",
          elementType: "labels",
          stylers: [{ lightness: -25 }, { saturation: -100 }],
        },
      ],
    },
    t = document.getElementById("map"),
    l = new google.maps.Map(t, e);
  new google.maps.Marker({
    position: new google.maps.LatLng(21.17024, 72.831061),
    map: l,
    title: "Nileforest",
    icon: "img/map-marker.png",
  });
}
google.maps.event.addDomListener(window, "load", init);
