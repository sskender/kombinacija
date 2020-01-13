function getLocation(recipient, errorHandler) {
  if(navigator.geolocation){
    navigator.geolocation.getCurrentPosition(recipient, errorHandler);
  } else {
    errorHandler();
  }
}

function createMap(elementID, centerLat, centerLon){
  return new google.maps.Map(document.getElementById(elementID), {
    zoom: 13,
    center: new google.maps.LatLng(centerLat, centerLon),
    mapTypeId: google.maps.MapTypeId.HYBRID
  });
}

function createContainerMarker(container, destMap, clr){
  return new google.maps.Marker({
    position: new google.maps.LatLng(container.latitude, container.longitude),
    map: destMap,
    title: "Kontejner ("+container.id+")",
    contID: container.id,
    icon: { url: "http://maps.google.com/mapfiles/ms/icons/"+clr+"-dot.png" }
  });
}

function createNeighborhoodMarker(hood, destMap){
  return new google.maps.Marker({
    position: new google.maps.LatLng(hood.latitude, hood.longitude),
    map: destMap,
    title: hood.name,
    hoodID: hood.id
  });
}

function createSelectorMarker(destMap, lat, lon){
  return new google.maps.Marker({
    position: new google.maps.LatLng(lat, lon),
    map: destMap,
    draggable: true
  })
}
