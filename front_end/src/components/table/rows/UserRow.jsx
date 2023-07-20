const UserRow = ({ user, setUser }) => {
  return (
    <tr className="text-center">
      <td>{user.name}</td>
      <td className="">{user.surname}</td>
      <td className="d-none d-lg-table-cell">{user.username}</td>
      <td className="d-none d-md-table-cell">{user.email}</td>
      <td className="d-none d-lg-table-cell">{user.roles[0].name}</td>
      <td>
        <button
          class="btn"
          id="editUser"
          type="button"
          aria-expanded="true"
          aria-label="Toggle tdeme (auto)"
          data-bs-toggle="modal"
          data-bs-target="#UserEditForm"
          onClick={() => setUser(user)}
        >
          <i class="bi bi-pencil"></i>
        </button>
      </td>
    </tr>
  );
};
export default UserRow;
