package services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Shout;
import repositories.ShoutRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ShoutService {
	
	// Managed repository -----------------------------------------------------
	@Autowired
	private ShoutRepository shoutRepository;
	
	
	
	public Collection<Shout> findAll() {
		Collection<Shout> result;
		
		result = this.shoutRepository.findAll();

		return result;		
	}
	
	
	public Shout create() {
	
		UserAccount userAccount = LoginService.getPrincipal();
		String username = userAccount.getUsername();
		
		Shout result = new Shout();
		result.setUsername(username);
		result.setLink("");
		result.setText("");
		
		return result;	
	}
	
	public void save(final Shout shout) {
		this.shoutRepository.save(shout);
	}
	
	public Map<String, Double> computeStatistics() {
		Map<String, Double> result = new HashMap<String, Double>();
		double countAll, countShort, countLong;
		
		countAll = this.shoutRepository.countAllShouts();
		countShort = this.shoutRepository.countShortShouts();
		countLong = this.shoutRepository.countLongtShouts();
		

		result.put("count.all.shouts", countAll);
		result.put("count.short.shouts", countShort);
		result.put("count.long.shouts", countLong);
		
		return result;
	}
	
	public int[] getValues() {
		int[] result = new int[3];
		
		result[0] = this.shoutRepository.countAllShouts();
		result[1] = this.shoutRepository.countShortShouts();
		result[2] = this.shoutRepository.countLongtShouts();
		
		
		return result;
	}

}































