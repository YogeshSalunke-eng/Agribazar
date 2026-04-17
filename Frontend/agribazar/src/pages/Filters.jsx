import "./Pages.css";
const Filters = ({
  crop,
  brand,
  pricing,
  onCropChange,
  onBrandChange,
  onPriceChange,
}) => {
  return (
    <div className="filters">
      <div className="filter">
        <h4>Filters</h4>
      </div>

      <div className="filter-section">
        <p>Crops name</p>

        {["all", "chana", "cotton", "maize", "onion", "potato",
          "soyabean", "sugarcane", "tur", "wheat"].map(c => (
          <label key={c}>
            <input
              type="radio"
              name="crops"
              value={c}
              checked={crop === c}
              onChange={() => onCropChange(c)}
            />
            {c}
          </label>
        ))}
      </div>

      <div className="filter-section">
        <p>Brand name</p>

        {["all","tata","syngenta","bayer","basf","dhanuka","upl"].map(t => (
          <label key={t}>
            <input
              type="radio"
              name="brands"
              value={t}
              checked={brand === t}
              onChange={() => onBrandChange(t)}
            />
            {t}
          </label>
        ))}
      </div>

      <div className="filter-section">
        <p>Price Range</p>

        {["all","0-200","200-500","500-1000","1000-2000","2000+"].map(p => (
          <label key={p}>
            <input
              type="radio"
              name="pricing"
              value={p}
              checked={pricing === p}
              onChange={() => onPriceChange(p)}
            />
            {p}
          </label>
        ))}
      </div>
    </div>
  );
};
export default Filters;