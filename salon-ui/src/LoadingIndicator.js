import "./loadingindicator.css";

export const LoadingIndicator = () => {
  return (
    <div className="spinner-wrap">
      <div className="lds-ellipsis">
        <div></div>
        <div></div>
        <div></div>
        <div></div>
      </div>
    </div>
  );
};
