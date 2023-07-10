const UserRow = () => {
  return (
    <tr className="text-center">
      <td>Mauro</td>
      <td className="d-none d-xl-table-cell">Simoni</td>
      <td className="d-none d-xl-table-cell">mauro</td>
      <td className="d-none d-md-table-cell">test@test.it</td>
      <td className="d-none d-md-table-cell">Admin</td>
      <td className="d-none d-md-table-cell">
        <button
          class="btn"
          id="bd-tdeme"
          type="button"
          aria-expanded="true"
          aria-label="Toggle tdeme (auto)"
          data-bs-toggle="modal"
          data-bs-target="#UserEditForm"
        >
          <i class="bi bi-pencil"></i>
        </button>
        <button
          class="btn"
          id="bd-tdeme"
          type="button"
          aria-expanded="true"
          aria-label="Toggle tdeme (auto)"
          data-bs-toggle="modal"
          data-bs-target="#confirmUserEdit"
        >
          <i class="bi bi-trash3"></i>
        </button>
      </td>
    </tr>
  );
};
export default UserRow;
