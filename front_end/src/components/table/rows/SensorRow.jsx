import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { addSensorToBookmark, removeSensorFromBookmark } from "../../../redux/slices/auth";
const SensorRow = ({ edit, sensor }) => {
  //const [isBookmarked, setIsBookmarked] = useState(false);
  const dispatch = useDispatch();
  const isBookmarked = useSelector((state) => state.auth.bookmarks).find((id) => id === sensor.id);

  // useEffect(() => {
  //   if (userData) {
  //     if (!users.length) {

  //     }
  //     if (!userExperiences.length) {

  //     }
  //     if (!posts.length) {

  //     }
  //   }
  // }, [userData]);

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
      <td>Project Apollo</td>
      <td className="d-none d-xl-table-cell">42</td>
      <td className="d-none d-xl-table-cell">43</td>
      <td className="d-none d-md-table-cell">°C</td>
      <td>15</td>
      <td className="d-none d-md-table-cell">40</td>
      <td className="d-none d-md-table-cell">-5</td>
      {edit && <td className="d-none d-md-table-cell">true</td>}
      <td className="d-none d-md-table-cell">
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
            >
              <i className="bi bi-pencil"></i>
            </button>
            <button
              className="btn"
              id="bd-tdeme"
              type="button"
              aria-expanded="true"
              aria-label="Toggle tdeme (auto)"
              data-bs-toggle="modal"
              data-bs-target="#confirmSensorEdit"
            >
              <i className="bi bi-trash3"></i>
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
