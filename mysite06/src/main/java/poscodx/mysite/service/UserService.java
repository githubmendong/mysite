package poscodx.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poscodx.mysite.repository.UserRepository;
import poscodx.mysite.vo.UserVo;

import javax.validation.Valid;

@Service
public class UserService {
	// @Autowired
	// private MailSender mailSender;

	@Autowired
	private UserRepository userRepository;

	public void join(@Valid UserVo vo) {
		System.out.println(vo);
		
		userRepository.insert(vo);
		
		System.out.println(vo);
		
		// mailSender.send(vo.getEmail(), "", "");
	}

	public UserVo getUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	public UserVo getUser(Long no) {
		return userRepository.findByNo(no);
	}

	public void update(UserVo userVo) {
		userRepository.update(userVo);
	}
}
