import UsersTable from "../table/UsersTable";

const Users = () => {
  return (
    <div className="row">
      <div className="col-12 col-lg-8 offset-lg-2 pb-4">
        <UsersTable edit />
      </div>
    </div>
  );
};
export default Users;
