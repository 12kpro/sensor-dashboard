import SensorTable from "../table/SensorTable";

const Sensors = () => {
  return (
    <div className="row">
      <div className="col-12 col-lg-8 offset-lg-2 pb-4">
        <SensorTable edit />
      </div>
    </div>
  );
};
export default Sensors;
