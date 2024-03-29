/**
 * Created By Archit Puri 
 * for aptechnolo.andropy
 * on Nov 13, 2019
 */
package aptechnolo.andropy;
import org.python.core.PyClass;
import org.python.core.PyFile;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.io.InputStream;

/**
 * @author Archit
 *
 */
public class JythonCaller {

    private PythonInterpreter pythonInterpreter;

    public JythonCaller() {

        pythonInterpreter = new PythonInterpreter();

    }

    public String invokeClass() {
        pythonInterpreter.exec("from divider import Divider");
        PyClass dividerDef = (PyClass) pythonInterpreter.get("Divider");
        PyObject divider = dividerDef.__call__();
        PyObject pyObject = divider.invoke("divide",new PyInteger(20),new PyInteger(4));
        return pyObject.toString();
    }
    //Call Any Method from Controller
    public String invokeClass(String path) {
        pythonInterpreter.exec("from divider import Divider");
        PyClass dividerDef = (PyClass) pythonInterpreter.get("Divider");
        PyObject divider = dividerDef.__call__();
        PyObject pyObject = divider.invoke("process",new PyString(path));
        return pyObject.toString();
    }


}
