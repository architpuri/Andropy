/**
 * Created By Archit Puri 
 * for aptechnolo.andropy
 * on Nov 13, 2019
 */
package aptechnolo.andropy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Archit
 *
 */
@RestController
public class Controller {

	@PostMapping(path = "/api/run")
	public String runCode(@RequestParam("attachedMedia") MultipartFile attachedMedia) {
		try {
			JythonCaller caller = new JythonCaller();
			System.out.println("Chl Gya");
			return caller.invokeClass();
		}catch(Exception e) {
			e.printStackTrace();
			return "Exception";
		}
	}
}
