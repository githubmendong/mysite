package poscodx.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poscodx.mysite.repository.GuestbookRepository;
import poscodx.mysite.vo.GuestbookVo;

import java.util.List;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestbookVo> getContentsList() {
		return guestbookRepository.findAll();
	}
	
	public Boolean deleteContents(Long no, String password) {
		return guestbookRepository.deleteByNoAndPassword(no, password);
	}
	
	public Boolean addContents(GuestbookVo vo) {
		return guestbookRepository.insert(vo);
	}
}
