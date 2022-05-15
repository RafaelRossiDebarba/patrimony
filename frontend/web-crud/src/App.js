import RoutesApp from "./routes";

import DepartamentProvider from "./context/DepartamentContext";
import CategoryProvider from "./context/CategoryContext";
import PatrimonyProvider from "./context/PatrimonyContext";

function App() {
  return (
    <DepartamentProvider>
      <CategoryProvider>
        <PatrimonyProvider>
          <RoutesApp />
        </PatrimonyProvider>
      </CategoryProvider>
    </DepartamentProvider>
  );
}

export default App;
