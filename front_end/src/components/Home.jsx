import { useSelector } from "react-redux";
import Login from "./users/Login";
import Dashboard from "./Dashboard";

const Home = () => {
  const accessToken = useSelector((state) => state.auth.token);
  return <>{!accessToken ? <Login /> : <Dashboard />}</>;
};
export default Home;
