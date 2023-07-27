import com.walmart.api.coupouns.model.Coupoun;
import com.walmart.api.coupouns.repository.CoupounRepository;
import com.walmart.api.coupouns.repository.Impl.CoupounJPARepositoryImpl;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coupoun c = new Coupoun();
		// CoupounRepository repo = new CoupounRepositoryImpl();
		CoupounRepository repo = new CoupounJPARepositoryImpl();

		repo.insertNewCoupoun(c);

	}

}
