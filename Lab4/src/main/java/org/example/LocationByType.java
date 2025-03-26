package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LocationByType {
    private Map<LocationType, List<Location>> locationsByType;

    public void storeLocationsByType(List<Location> locations) {
        locationsByType = locations.stream().collect(Collectors.groupingBy(Location::getType));
    }

    public Map<LocationType, List<Location>> getLocationsByType() {
        return locationsByType;
    }
}