import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { addSensorToBookmark, removeSensorFromBookmark } from "../../../redux/slices/auth";
const SensorRow = ({ edit, sensor, setSensor }) => {
  //const [isBookmarked, setIsBookmarked] = useState(false);
  const dispatch = useDispatch();
  const isBookmarked = useSelector((state) => state.auth.bookmarks).find((id) => id === sensor.id);
  const alarmSymbol = {
    gt: ">",
    lt: "<",
    eq: "="
  };
  const handleToggle = () => {
    if (isBookmarked) {
      dispatch(removeSensorFromBookmark(sensor.id));
    } else {
      dispatch(addSensorToBookmark(sensor.id));
    }
    //setIsBookmarked(!isBookmarked);
  };
  return (
    <tr className="text-center">
      <td>{sensor.name}</td>
      <td className="d-none d-xl-table-cell">{sensor.lat}</td>
      <td className="d-none d-xl-table-cell">{sensor.lon}</td>
      <td className="d-none d-sm-table-cell ">{sensor.um.unit}</td>
      <td className="d-none d-sm-table-cell">{alarmSymbol[sensor.alertCondition] + sensor.alertValue}</td>
      <td className="d-none d-md-table-cell">{sensor.rangeMax}</td>
      <td className="d-none d-md-table-cell">{sensor.rangeMin}</td>
      {edit && (
        <td className="d-none d-md-table-cell">
          <i className={`bi ${sensor.visible ? "bi-eye" : "bi-eye-slash"}`}></i>
        </td>
      )}
      <td>
        {edit && (
          <>
            <button
              className="btn"
              id="bd-tdeme"
              type="button"
              aria-expanded="true"
              aria-label="Toggle tdeme (auto)"
              data-bs-toggle="modal"
              data-bs-target="#SensorEditForm"
              onClick={() => setSensor(sensor)}
            >
              <i className="bi bi-pencil"></i>
            </button>
          </>
        )}
        {!edit && (
          <button
            className="btn"
            id="bd-tdeme"
            type="button"
            aria-expanded="true"
            aria-label="Toggle tdeme (auto)"
            onClick={handleToggle}
          >
            <i className={`bi ${isBookmarked ? "bi-bookmark-star-fill" : "bi-bookmark-plus"}`}></i>
          </button>
        )}
      </td>
    </tr>
  );
};
export default SensorRow;
