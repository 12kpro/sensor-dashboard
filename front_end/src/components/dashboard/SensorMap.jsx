import { Attribution, defaults, FullScreen, Rotate, ScaleLine } from "ol/control";
import Map from "ol/Map";
import View from "ol/View";
import { useEffect, useRef, useState } from "react";
import TileLayer from "ol/layer/Tile";
import { OSM, Vector as VectorSource } from "ol/source";
import { useGeographic } from "ol/proj";
import { Point } from "ol/geom";
import { useSelector } from "react-redux";
import Overlay from "ol/Overlay";

const SensorMap = ({ lastSensorData }) => {
  const mapTargetElement = useRef(null);
  const sensors = useSelector((state) => state.sensors.available);

  const [map, setMap] = useState();
  const [fullScreen, setFullScreen] = useState(false);
  useGeographic();
  useEffect(() => {
    const place = [12.15502, 45.9125];

    //    const point = new Point(place);
    const InitializeMap = new Map({
      controls: defaults({ attribution: false, zoom: false, rotate: false }),

      layers: [
        new TileLayer({
          source: new OSM()
        })
      ],

      view: new View({
        center: place,
        zoom: 15,
        minZoom: 0,
        maxZoom: 28
      })
    });

    InitializeMap.setTarget(mapTargetElement.current || "");
    setMap(InitializeMap);
    return () => InitializeMap.setTarget("");
  }, []);

  useEffect(() => {
    if (map && sensors) {
      sensors.content.forEach((sensor) => {
        if (sensor.visible) {
          const marker = new Overlay({
            position: [sensor.lon, sensor.lat],
            positioning: "left-top",
            element: document.createElement("div"),
            stopEvent: false
          });
          marker.getElement().innerHTML = `<i class="bi bi-geo-fill" style="color:red;"></i><span style="color:black;">${sensor.name}</span>`;
          map.addOverlay(marker);
        }
      });
    }
  }, [map, sensors]);

  return (
    <div className={`card dashboard-card shadow-sm border border-opacity-10 ${fullScreen ? "full-screen" : "mb-3"}`}>
      <div className="card-header d-flex justify-content-between align-items-center">
        <h5 className="card-title m-0">Mappa sensori</h5>
        <button className="btn" onClick={() => setFullScreen(!fullScreen)}>
          <i className={`bi ${fullScreen ? "bi-fullscreen-exit" : "bi-arrows-fullscreen"}`}></i>
        </button>
      </div>
      <div className="card-body" ref={mapTargetElement}></div>
    </div>
  );
};
export default SensorMap;
