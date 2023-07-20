import React, { useEffect, useState } from "react";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  TimeScale
} from "chart.js";
import { Line } from "react-chartjs-2";
import "chartjs-adapter-date-fns";
import { useSelector } from "react-redux";
ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend, TimeScale);

const Chart = ({ sensorId }) => {
  const rawData = useSelector((state) => state.sensorData);
  const sensors = useSelector((state) => state.sensors.available);

  const [sensor, setSensor] = useState(null);
  const [fullScreen, setFullScreen] = useState(false);
  const [sensorData, setSensorData] = useState({
    labels: [],
    datasets: [
      {
        data: [],
        borderColor: "rgb(255, 99, 132)",
        backgroundColor: "rgba(255, 99, 132, 0.5)"
      }
    ]
  });
  // const data = {
  //   labels: chartLabels, //
  //   datasets: [
  //     {
  //       fill: true,
  //       backgroundColor: "transparent",
  //       data: chartData,
  //       borderColor: "rgb(255, 99, 132)"
  //     }
  //   ]
  // };

  const options = {
    maintainAspectRatio: false,
    responsive: true,
    scales: {
      y: {
        beginAtZero: true
      },
      x: {
        type: "time",
        time: {
          unit: "minute",
          unitStepSize: 15,
          tooltipFormat: "dd-MM-yyyy HH:mm",
          displayFormats: {
            minute: "HH:mm"
          }
        }
      }
    },
    plugins: { legend: false }
  };

  useEffect(() => {
    if (sensors && rawData.sensorId) {
      setSensor(sensors.content.find((sensor) => sensor.id === rawData.sensorId));
    }
    if (rawData.data.length > 0) {
      setSensorData({
        labels: rawData.data.map((d) => d.time),
        datasets: [
          {
            data: rawData.data.map((d) => d.value),
            borderColor: "rgb(255, 99, 132)",
            backgroundColor: "rgba(255, 99, 132, 0.5)"
          }
        ]
      });
    }
  }, [rawData, sensors]);

  return (
    <div className={`card dashboard-card shadow-sm border border-opacity-10 ${fullScreen ? "full-screen" : "mb-3"}`}>
      <div className="card-header d-flex justify-content-between align-items-center">
        {sensor && <h5 className="card-title m-0">{sensor.name}</h5>}
        <button className="btn" onClick={() => setFullScreen(!fullScreen)}>
          <i className={`bi ${fullScreen ? "bi-fullscreen-exit" : "bi-arrows-fullscreen"}`}></i>
        </button>
      </div>
      <div className="card-body d-flex justify-content-center align-items-center">
        {rawData.loading ? (
          <>
            <div className="spinner-border" role="status">
              <span className="visually-hidden">Loading...</span>
            </div>
            <strong className="ps-3">Caricamento dati in corso</strong>
          </>
        ) : rawData.errorMsg ? (
          <div class="alert alert-danger" role="alert">
            {rawData.errorMsg}
          </div>
        ) : (
          <Line options={options} data={sensorData} />
        )}
      </div>
    </div>
  );
};

export default Chart;
