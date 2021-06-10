export const Card = ({ name, description, price }) => {
  return (
    <div className="card" style={{ width: "18rem" }}>
      <div className="card-header font-weight-bold font-size">{name}</div>
      <div className="card-body">
        <h5 className="card-title font-weight-bold">${price}</h5>
        <p className="card-text">{description}</p>
        <a href="#" className="btn btn-outline-primary btn-lg w-100">
          Book Now
        </a>
      </div>
    </div>
  );
};
