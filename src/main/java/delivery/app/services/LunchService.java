package delivery.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import delivery.app.entities.Lunch;
import delivery.app.repositories.LunchRepository;

@Service
public class LunchService {
	
	@Autowired
	private LunchRepository lunchRepository;

	
	public List<Lunch> findAllLunches(){
		return this.lunchRepository.findAll();
	}
	public boolean saveLunch(Lunch lunch) {
		if(this.lunchRepository.save(lunch)!= null) {
			return true;
		}
		return false;
			
		
	}

}
