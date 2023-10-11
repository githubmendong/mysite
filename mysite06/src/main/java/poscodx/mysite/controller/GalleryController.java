package poscodx.mysite.controller;

import com.poscodx.mysite.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index() {
		return "gallery/index";
	}
}
