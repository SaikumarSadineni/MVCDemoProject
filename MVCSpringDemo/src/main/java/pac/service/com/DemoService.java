package pac.service.com;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.vo.Demo;

import pac.DAO.FieldsDAO;

@Service
public class DemoService {

	@Autowired
	FieldsDAO objFieldsDAO;

	public void save(Demo objDemo) {
		try {
			objFieldsDAO.save(objDemo);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}

	public ArrayList getCommentsDetails() {
		Demo objDemo = new Demo();
		ArrayList objarrayList =null;
		try {
			objarrayList = new ArrayList();
			objarrayList = objFieldsDAO.getCommentsDetails();

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

		return objarrayList;

	}
	
	public void deleterecord(int id) {
		try {
			objFieldsDAO.delete(id);
		}catch (Exception e) {
			e.getStackTrace();
		}
	}
}
