import { useDispatch, useSelector } from "react-redux";
import { removeAlert } from "../../redux/slices/alert";
const Alerts = () => {
  const alerts = useSelector((state) => state.alerts);
  const dispatch = useDispatch();

  const handleDismiss = (alertId) => {
    dispatch(removeAlert(alertId));
  };

  return (
    <div className="toast-container position-absolute top-0 end-0 mb-2 me-2">
      {alerts.map((alert) => (
        <div
          className={`toast text-bg-${alert.type} fade show`}
          role="alert"
          aria-live="assertive"
          aria-atomic="true"
          data-bs-autohide="false"
          key={alert.id}
        >
          <div className="toast-header">
            <strong className="me-auto">{alert.sensor}</strong>
            <small className="text-body-secondary">{alert.time}</small>
            <button
              type="button"
              className="btn-close"
              data-bs-dismiss="toast"
              aria-label="Close"
              onClick={() => handleDismiss(alert.id)}
            ></button>
          </div>
          <div className="toast-body">{alert.msg}</div>
        </div>
      ))}
    </div>
  );
};
export default Alerts;
