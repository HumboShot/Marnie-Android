package Model;

import android.icu.util.DateInterval;

/**
 * Created by User on 15-05-2017.
 */

public class Stop {
   private int Id;
   private int RouteId;
   private int StationId;
   private DateInterval ArrivalTime;
   private DateInterval DepartureTime;
   private Route Route;
   private Station Station;

    public Stop() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getRouteId() {
        return RouteId;
    }

    public void setRouteId(int routeId) {
        RouteId = routeId;
    }

    public int getStationId() {
        return StationId;
    }

    public void setStationId(int stationId) {
        StationId = stationId;
    }

    public DateInterval getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(DateInterval arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public DateInterval getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(DateInterval departureTime) {
        DepartureTime = departureTime;
    }

    public Model.Route getRoute() {
        return Route;
    }

    public void setRoute(Model.Route route) {
        Route = route;
    }

    public Model.Station getStation() {
        return Station;
    }

    public void setStation(Model.Station station) {
        Station = station;
    }
}
